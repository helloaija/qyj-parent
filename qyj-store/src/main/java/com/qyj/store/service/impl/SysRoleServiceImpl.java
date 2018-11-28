package com.qyj.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qyj.common.page.ResultBean;
import com.qyj.store.common.tree.TreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.store.common.enums.CommonEnums.RelatinTypeEnum;
import com.qyj.store.common.tree.TreeUtil;
import com.qyj.store.common.util.Utils;
import com.qyj.store.dao.SysRelationMapper;
import com.qyj.store.dao.SysRoleMapper;
import com.qyj.store.entity.SysMenuModel;
import com.qyj.store.entity.SysRelationEntity;
import com.qyj.store.entity.SysRoleModel;
import com.qyj.store.service.SysMenuService;
import com.qyj.store.service.SysRoleService;
import com.qyj.common.exception.ValidException;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

/**
 * service实现类-系统用户角色
 * @author shitongle
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private SysRelationMapper sysRelationMapper;

	/**
	 * 获取系统角色分页数据
	 * @param pageParam
	 * @param roleModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean listSysRolePage(SysRoleModel roleModel, PageParam pageParam) throws Exception {
		// 计算个数
		int totalCount = sysRoleMapper.countSysRole(roleModel, pageParam);
		if (totalCount <= 0) {
			return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), 0, null);
		}
		
		pageParam.setTotalCount(totalCount);
		// 计算分页信息
		pageParam.splitPageInstance();

		// 获取系统角色列表
		List<SysRoleModel> sysRoleList = sysRoleMapper.listSysRolePage(roleModel, pageParam);

		return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), totalCount, sysRoleList);
	}

	/**
	 * 添加用户角色
	 * @param roleModel
	 * @param menuIds 关联的菜单id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer addRole(SysRoleModel roleModel, Long... menuIds) throws Exception {
		if (null == roleModel) {
			throw new Exception("请填写用户信息！");
		}
		if (StringUtils.isEmpty(roleModel.getRoleCode())) {
			throw new Exception("角色编码不能为空！");
		}
		if (StringUtils.isEmpty(roleModel.getRoleName())) {
			throw new Exception("角色名称不能为空！");
		}

		SysRoleModel sysRoleModel = new SysRoleModel();
		BeanUtils.copyProperties(roleModel, sysRoleModel);

		sysRoleModel.setCreateTime(new Date());
		sysRoleModel.setUpdateTime(new Date());
		
		// 插入角色
		int insertNum = sysRoleMapper.insertRole(sysRoleModel);
		
		if (menuIds != null && menuIds.length > 0) {
			List<SysRelationEntity> relationEntityList = new ArrayList<SysRelationEntity>();
			SysRelationEntity relationEntity = null;
			for (Long menuId : menuIds) {
				relationEntity = new SysRelationEntity();
				relationEntity.setMainId(sysRoleModel.getId());
				relationEntity.setRelationId(menuId);
				relationEntity.setRelationType(RelatinTypeEnum.ROLEMENU.toString());
				relationEntityList.add(relationEntity);
			}
			// 批量插入关系记录
			sysRelationMapper.insertRelationBatch(relationEntityList);
		}

		return insertNum;
	}

	/**
	 * 删除用户角色
	 * @param ids 用户角色id数组
	 * @throws Exception
	 */
	@Override
	public int delRole(Long... ids) throws Exception {
		if (ids == null || ids.length == 0) {
			throw new Exception("需要删除角色的id为空");
		}
		
		SysRelationEntity relationEntity = null;
		// 删除角色菜单联系
		for (Long roleId : ids) {
			relationEntity = new SysRelationEntity();
			relationEntity.setMainId(roleId);
			relationEntity.setRelationType(RelatinTypeEnum.ROLEMENU.toString());
			sysRelationMapper.deleteRelationByModel(relationEntity);
		}

		// 删除角色
		return sysRoleMapper.delRole(ids);
	}

	/**
	 * 根据id查询系统角色
	 * @param roleId roleId
	 * @throws Exception
	 */
	@Override
	public SysRoleModel queryRoleById(Long roleId) throws Exception {
		SysRoleModel roleModel = new SysRoleModel();
		roleModel.setId(roleId);
		List<SysRoleModel> roleModelList = sysRoleMapper.listSysRolePage(roleModel, null);

		if (Utils.isEmptyCollection(roleModelList)) {
			throw new ValidException("没有角色" + roleId + "信息");
		}

		return roleModelList.get(0);
	}

	/**
	 * 根据id查询系统角色和菜单
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getRoleAndMenuByRoleId(Long roleId) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SysRoleModel role = queryRoleById(roleId);

		if (role == null) {
			throw new ValidException("没有对应的角色信息，roleId:" + roleId);
		}
		resultMap.put("role", role);
		
		List<SysMenuModel> menuList = sysMenuService.querySysMenuList();
		
		SysRelationEntity relationEntity = new SysRelationEntity();
		relationEntity.setMainId(roleId);
		relationEntity.setRelationType(RelatinTypeEnum.ROLEMENU.toString());
		// 查询角色关联的菜单id
		List<SysRelationEntity> relationList = sysRelationMapper.listRelationByModel(relationEntity);

		Set<Long> menuIdSet = new HashSet<Long>();
		for (SysRelationEntity entiry : relationList) {
			menuIdSet.add(entiry.getRelationId());
		}

		for (SysMenuModel model : menuList) {
			if (menuIdSet.contains(model.getId())) {
				model.setChecked(true);
			}
		}

		TreeNode rootNode = new TreeNode(new Long(0), "根目录");
		TreeUtil.loadTreeNode(rootNode, menuList);
		List<TreeNode> tree = new ArrayList<>();
		tree.add(rootNode);

		if (!menuIdSet.isEmpty()) {
			rootNode.setChecked(true);
		}

		resultMap.put("menu", tree);

		return resultMap;
	}
	
	/**
	 * 编辑用户角色
	 * @param roleModel
	 * @param menuIds 关联的菜单id
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean editRole(SysRoleModel roleModel, Long... menuIds) throws Exception {
		if (null == roleModel) {
			throw new ValidException("请填写角色信息！");
		}
		if (StringUtils.isEmpty(roleModel.getRoleCode())) {
			throw new ValidException("角色编码不能为空！");
		}
		if (StringUtils.isEmpty(roleModel.getRoleName())) {
			throw new ValidException("角色名称不能为空！");
		}

		SysRoleModel sysRoleModel = new SysRoleModel();
		BeanUtils.copyProperties(roleModel, sysRoleModel);
		sysRoleModel.setUpdateTime(new Date());

		// 更新角色
		int updateNum = sysRoleMapper.updateRole(sysRoleModel);
		
		SysRelationEntity deleteRelationEntity = new SysRelationEntity();
		deleteRelationEntity.setMainId(roleModel.getId());
		deleteRelationEntity.setRelationType(RelatinTypeEnum.ROLEMENU.toString());
		// 先删除之前的关联
		sysRelationMapper.deleteRelationByModel(deleteRelationEntity);
		
		if (menuIds != null && menuIds.length > 0) {
			List<SysRelationEntity> relationEntityList = new ArrayList<SysRelationEntity>();
			SysRelationEntity relationEntity = null;
			for (Long menuId : menuIds) {
				relationEntity = new SysRelationEntity();
				relationEntity.setMainId(sysRoleModel.getId());
				relationEntity.setRelationId(menuId);
				relationEntity.setRelationType(RelatinTypeEnum.ROLEMENU.toString());
				relationEntityList.add(relationEntity);
			}
			// 批量插入关系记录
			sysRelationMapper.insertRelationBatch(relationEntityList);
		}

		return true;
	}

}

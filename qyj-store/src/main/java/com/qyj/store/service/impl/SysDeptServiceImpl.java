package com.qyj.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.store.common.util.Utils;
import com.qyj.store.dao.BaseRelationMapper;
import com.qyj.store.dao.SysDeptMapper;
import com.qyj.store.entity.SysDeptModel;
import com.qyj.store.service.SysDeptService;
import com.qyj.store.service.SysMenuService;
import com.qyj.store.vo.SysUserBean;
import com.qyj.common.page.PageParam;

/**
 * 部门service实现类
 * @author shitongle
 */
@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {

	// 部门mapper
	@Autowired
	private SysDeptMapper sysDeptMapper;

	// 关联表mapper
	@Autowired
	private BaseRelationMapper baseRelationMapper;

	// 菜单mapper
	@Autowired
	private SysMenuService sysMenuService;
	
	private static final Logger logger = LogManager.getLogger(SysDeptServiceImpl.class);

	/**
	 * 获取部门列表
	 * @return
	 */
	public List<SysDeptModel> querySysDeptList(SysDeptModel queryModel, PageParam pageParam) throws Exception {
		if (null == pageParam) {
			pageParam = new PageParam();
		}
		if (null == queryModel) {
			queryModel = new SysDeptModel();
		}
		return sysDeptMapper.querySysDeptList(queryModel, pageParam);
	}

	/**
	 * 查询部门信息分页总记录数
	 * @param queryModel
	 * @param pageParam
	 * @return
	 */
	public Integer querySysDeptTotal(SysDeptModel queryModel, PageParam pageParam) throws Exception {
		if (null == pageParam) {
			pageParam = new PageParam();
		}
		if (null == queryModel) {
			queryModel = new SysDeptModel();
		}
		return sysDeptMapper.querySysDeptTotal(queryModel, pageParam);
	}

	/**
	 * 添加部门信息
	 * @param userModel
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	public Integer addDept(SysDeptModel deptModel, SysUserBean userBean) throws Exception {

		if (null == deptModel) {
			throw new Exception("请填写部门信息！");
		}

		if (StringUtils.isEmpty(deptModel.getDeptCode())) {
			throw new Exception("角色编码不能为空！");
		}
		if (StringUtils.isEmpty(deptModel.getDeptName())) {
			throw new Exception("角色名称不能为空！");
		}

		SysDeptModel sysDeptModel = new SysDeptModel();
		BeanUtils.copyProperties(deptModel, sysDeptModel);

		sysDeptModel.setCreateTime(new Date());
		sysDeptModel.setCreateUser(userBean.getId());
		sysDeptModel.setUpdateTime(new Date());
		sysDeptModel.setUpdateUser(userBean.getId());

		return sysDeptMapper.insertDept(sysDeptModel);
	}

	/**
	 * 删除部门信息
	 * @param ids 部门id数组
	 * @throws Exception
	 */
	public void delDept(Long... ids) throws Exception {
		if (ids == null || ids.length == 0) {
			return;
		}

		sysDeptMapper.delDept(ids);
	}

	/**
	 * 根据id查询部门
	 * @param deptId 部门id
	 * @throws Exception
	 */
	public SysDeptModel queryDeptById(Long deptId) throws Exception {
		SysDeptModel queryUser = new SysDeptModel();
		queryUser.setId(deptId);
		List<SysDeptModel> DeptModelList = sysDeptMapper.querySysDeptList(queryUser, new PageParam());

		if (Utils.isEmptyCollection(DeptModelList)) {
			throw new Exception("获取部门信息失败！");
		}

		SysDeptModel deptModel = new SysDeptModel();
		BeanUtils.copyProperties(DeptModelList.get(0), deptModel);

		return deptModel;
	}

	/**
	 * 更新部门信息
	 * @param deptModel
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	public void updateDept(SysDeptModel deptModel, SysUserBean userBean) throws Exception {
		if (null == deptModel) {
			throw new Exception("请填写部门信息！");
		}

		if (StringUtils.isEmpty(deptModel.getDeptCode())) {
			throw new Exception("角色编码不能为空！");
		}
		if (StringUtils.isEmpty(deptModel.getDeptName())) {
			throw new Exception("角色名称不能为空！");
		}

		SysDeptModel sysDeptModel = new SysDeptModel();
		BeanUtils.copyProperties(deptModel, sysDeptModel);

		sysDeptModel.setUpdateTime(new Date());
		sysDeptModel.setUpdateUser(userBean.getId());

		sysDeptMapper.insertDept(sysDeptModel);
	}

	/**
	 * 更新部门关联菜单
	 * @param deptId
	 * @param menuIds
	 * @return
	 * @throws Exception
	 */
	public void updateDeptMenu(Long deptId, Long... menuIds) throws Exception {
		logger.info("--- updateDeptMenu begin ---");
		logger.info("updateDeptMenu param deptId is null");
		if (deptId == null) {
			throw new Exception("部门id为空！");
		}
		if (menuIds == null) {
			menuIds = new Long[0];
		}
		baseRelationMapper.delDeptMenuByDeptId(deptId);
		baseRelationMapper.addDeptMenu(deptId, menuIds);
		logger.info("--- updateDeptMenu end ---");
	}

	/**
	 * 根据部门id获取菜单id列表
	 * @param deptId
	 * @throws Exception
	 */
	public List<Long> queryMenuIdListByDeptId(Long deptId) throws Exception {
		logger.info("--- updateDeptMenu start ---");
		logger.info("updateDeptMenu param,deptId:" + deptId);
		List<Long> menuIdList = new ArrayList<Long>();

		List<Map<String, Long>> deptMenuList = baseRelationMapper.queryDeptMenuByDeptId(deptId);

		Long checkedMenuId;
		for (int j = 0; j < deptMenuList.size(); j++) {
			checkedMenuId = deptMenuList.get(j).get("menuId");
			if (checkedMenuId == null) {
				continue;
			}
			menuIdList.add(checkedMenuId);
		}

		logger.info("--- updateDeptMenu end ---");
		return menuIdList;
	}

}

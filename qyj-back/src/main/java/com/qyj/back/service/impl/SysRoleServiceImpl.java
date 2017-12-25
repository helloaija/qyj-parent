package com.qyj.back.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.back.common.util.Utils;
import com.qyj.back.dao.SysRoleMapper;
import com.qyj.back.entity.SysRoleModel;
import com.qyj.back.service.SysRoleService;
import com.qyj.back.vo.SysUserBean;
import com.qyj.common.page.PageBean;

/**
 * 系统用户角色service实现类
 * @author shitongle
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	// 系统用户mapper
	@Autowired
	private SysRoleMapper sysRoleMapper;

	/**
	 * 获取系统用户列表
	 * @return
	 */
	public List<SysRoleModel> querySysRoleList(SysRoleModel queryModel, PageBean pageBean) throws Exception {
		if (null == pageBean) {
			pageBean = new PageBean();
		}
		if (null == queryModel) {
			queryModel = new SysRoleModel();
		}
		return sysRoleMapper.querySysRoleList(queryModel, pageBean);
	}

	/**
	 * 查询系统用户信息分页总记录数
	 * @param queryModel
	 * @param pageBean
	 * @return
	 */
	public Integer querySysRoleTotal(SysRoleModel queryModel, PageBean pageBean) throws Exception {
		if (null == pageBean) {
			pageBean = new PageBean();
		}
		if (null == queryModel) {
			queryModel = new SysRoleModel();
		}
		return sysRoleMapper.querySysRoleTotal(queryModel, pageBean);
	}

	/**
	 * 添加用户信息
	 * @param userModel
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	public Integer addRole(SysRoleModel roleModel, SysUserBean userBean) throws Exception {

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
		sysRoleModel.setCreateUser(userBean.getId());
		sysRoleModel.setUpdateTime(new Date());
		sysRoleModel.setUpdateUser(userBean.getId());

		return sysRoleMapper.insertRole(sysRoleModel);
	}

	/**
	 * 删除用户信息
	 * @param ids 用户id数组
	 * @throws Exception
	 */
	public void delRole(Long... ids) throws Exception {
		if (ids == null || ids.length == 0) {
			return;
		}

		sysRoleMapper.delRole(ids);
	}

	/**
	 * 根据id查询系统用户
	 * @param userId 用户id
	 * @throws Exception
	 */
	public SysRoleModel queryRoleById(Long userId) throws Exception {
		SysRoleModel queryUser = new SysRoleModel();
		queryUser.setId(userId);
		List<SysRoleModel> roleModelList = sysRoleMapper.querySysRoleList(queryUser, new PageBean());

		if (Utils.isEmptyCollection(roleModelList)) {
			throw new Exception("获取用户角色信息失败！");
		}

		SysRoleModel roleModel = new SysRoleModel();
		BeanUtils.copyProperties(roleModelList.get(0), roleModel);

		return roleModel;
	}

}

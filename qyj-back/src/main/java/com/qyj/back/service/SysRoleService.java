package com.qyj.back.service;

import java.util.List;

import com.qyj.back.entity.SysRoleModel;
import com.qyj.back.vo.SysUserBean;
import com.qyj.common.page.PageBean;

/**
 * 系统用户角色service
 * @author shitongle
 *
 */
public interface SysRoleService {
	
	/**
	 * 获取用户角色列表
	 * @param queryModel
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public List<SysRoleModel> querySysRoleList(SysRoleModel queryModel, PageBean pageBean) throws Exception;
	
	/**
	 * 查询系统用户角色分页总记录数
	 * @param queryModel
	 * @param pageBean
	 * @return
	 */
	public Integer querySysRoleTotal(SysRoleModel queryModel, PageBean pageBean) throws Exception;
	
	/**
	 * 添加用户角色信息
	 * @param roleBean
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	public Integer addRole(SysRoleModel roleModel, SysUserBean userBean) throws Exception;
	
	/**
	 * 删除用户角色信息
	 * @param ids 用户角色id数组
	 * @throws Exception
	 */
	public void delRole(Long... ids) throws Exception;
	
	/**
	 * 根据id查询系统用户角色
	 * @param roleId 角色id
	 * @throws Exception
	 */
	public SysRoleModel queryRoleById(Long roleId) throws Exception;
}

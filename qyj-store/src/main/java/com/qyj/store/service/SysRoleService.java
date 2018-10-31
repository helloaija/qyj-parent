package com.qyj.store.service;

import com.qyj.store.entity.SysRoleModel;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

/**
 * 系统用户角色service
 * @author shitongle
 *
 */
public interface SysRoleService {
	
	/**
	 * 获取系统角色分页数据
	 * @param pageParam
	 * @param roleModel
	 * @return
	 * @throws Exception
	 */
	public PageBean listSysRolePage(SysRoleModel roleModel, PageParam pageParam) throws Exception;
	
	/**
	 * 添加用户角色
	 * @param roleModel
	 * @param menuIds 关联的菜单id
	 * @return
	 * @throws Exception
	 */
	public Integer addRole(SysRoleModel roleModel, Long... menuIds) throws Exception;
	
	/**
	 * 删除用户角色信息
	 * @param ids 用户角色id数组
	 * @throws Exception
	 */
	public int delRole(Long... ids) throws Exception;
	
	/**
	 * 根据id查询系统用户角色
	 * @param roleId 角色id
	 * @throws Exception
	 */
	public SysRoleModel queryRoleById(Long roleId) throws Exception;
	
	/**
	 * 添加用户角色
	 * @param roleModel
	 * @param menuIds 关联的菜单id
	 * @return
	 * @throws Exception
	 */
	public Integer editRole(SysRoleModel roleModel, Long... menuIds) throws Exception;
}

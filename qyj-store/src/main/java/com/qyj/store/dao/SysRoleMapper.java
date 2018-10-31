package com.qyj.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qyj.store.entity.SysRoleModel;
import com.qyj.common.page.PageParam;

/**
 * 系统用户角色mapper接口
 * @author shitongle
 *
 */
public interface SysRoleMapper {
	
	/**
	 * 查询系统用户角色信息分页数据
	 * @param queryModel
	 * @param pageBean
	 * @return
	 */
	public List<SysRoleModel> listSysRolePage(@Param("sysRoleModel")SysRoleModel queryModel, @Param("pageParam")PageParam pageParam);

	/**
	 * 查询系统用户角色信息分页总记录数
	 * @param queryModel
	 * @param pageBean
	 * @return
	 */
	public Integer countSysRole(@Param("sysRoleModel")SysRoleModel queryModel, @Param("pageParam")PageParam pageParam);
	
	/**
	 * 插入系统用户角色
	 * @param roleModel 用户角色model
	 * @return
	 */
	public Integer insertRole(SysRoleModel roleModel);
	
	/**
	 * 根据用户id数组删除系统用户角色
	 * @param ids 用户角色id数组
	 * @return
	 */
	public int delRole(@Param("ids")Long... ids);
	
	/**
	 * 更新系统用户角色
	 * @param roleModel
	 * @return
	 */
	public int updateRole(SysRoleModel roleModel);
}

package com.qyj.back.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qyj.back.entity.SysRoleModel;
import com.qyj.common.page.PageBean;

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
	public List<SysRoleModel> querySysRoleList(@Param("sysRoleModel")SysRoleModel queryModel, @Param("pageBean")PageBean pageBean);

	/**
	 * 查询系统用户角色信息分页总记录数
	 * @param queryModel
	 * @param pageBean
	 * @return
	 */
	public Integer querySysRoleTotal(@Param("sysRoleModel")SysRoleModel queryModel, @Param("pageBean")PageBean pageBean);
	
	/**
	 * 插入系统用户角色
	 * @param roleModel 用户角色model
	 * @return
	 */
	public Integer insertRole(@Param("sysRoleModel")SysRoleModel roleModel);
	
	/**
	 * 根据用户id数组删除系统用户角色
	 * @param ids 用户角色id数组
	 * @return
	 */
	public void delRole(@Param("ids")Long... ids);
}

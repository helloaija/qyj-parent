package com.qyj.back.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 关联表mapper
 * @author shitongle
 *
 */
public interface BaseRelationMapper {
	
	/**
	 * 根据部门id删除部门菜单关联
	 * @param deptId
	 */
	public void delDeptMenuByDeptId(@Param("deptId")Long deptId);
	
	/**
	 * 添加部门菜单关联
	 * @param deptId
	 * @param menuIds
	 */
	public void addDeptMenu(@Param("deptId")Long deptId, @Param("menuIds")Long... menuIds);
	
	/**
	 * 根据部门id获取部门菜单关联
	 * @param deptId
	 * @return
	 */
	public List<Map<String, Long>> queryDeptMenuByDeptId(@Param("deptId")Long deptId);
}

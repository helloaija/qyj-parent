package com.qyj.back.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qyj.back.entity.SysDeptModel;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

/**
 * 部门mapper接口
 * @author shitongle
 */
public interface SysDeptMapper {

	/**
	 * 查询部门信息分页数据
	 * @param queryModel
	 * @param pageParam
	 * @return
	 */
	public List<SysDeptModel> querySysDeptList(@Param("sysDeptModel") SysDeptModel queryModel,
			@Param("pageBean") PageParam pageParam);

	/**
	 * 查询部门信息分页总记录数
	 * @param queryModel
	 * @param pageBean
	 * @return
	 */
	public Integer querySysDeptTotal(@Param("sysDeptModel") SysDeptModel queryModel,
			@Param("pageParam") PageParam pageParam);

	/**
	 * 插入部门
	 * @param DeptModel 部门model
	 * @return
	 */
	public Integer insertDept(@Param("sysDeptModel") SysDeptModel DeptModel);

	/**
	 * 根据部门id数组删除部门
	 * @param ids 部门id数组
	 * @return
	 */
	public void delDept(@Param("ids") Long... ids);
}

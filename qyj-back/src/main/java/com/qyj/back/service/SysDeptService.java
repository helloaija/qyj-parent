package com.qyj.back.service;

import java.util.List;

import com.qyj.back.entity.SysDeptModel;
import com.qyj.back.vo.SysUserBean;
import com.qyj.common.page.PageParam;

/**
 * 部门service
 * @author shitongle
 *
 */
public interface SysDeptService {
	
	/**
	 * 获取部门角色列表
	 * @param queryModel
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public List<SysDeptModel> querySysDeptList(SysDeptModel queryModel, PageParam pageParam) throws Exception;
	
	/**
	 * 查询部门分页总记录数
	 * @param queryModel
	 * @param pageBean
	 * @return
	 */
	public Integer querySysDeptTotal(SysDeptModel queryModel, PageParam pageParam) throws Exception;
	
	/**
	 * 添加部门信息
	 * @param deptModel
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	public Integer addDept(SysDeptModel deptModel, SysUserBean userBean) throws Exception;
	
	/**
	 * 删除部门角色信息
	 * @param ids 部门角色id数组
	 * @throws Exception
	 */
	public void delDept(Long... ids) throws Exception;
	
	/**
	 * 根据id查询部门
	 * @param deptId 部门id
	 * @throws Exception
	 */
	public SysDeptModel queryDeptById(Long deptId) throws Exception;
	
	/**
	 * 更新部门信息
	 * @param deptModel
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	public void updateDept(SysDeptModel deptModel, SysUserBean userBean) throws Exception;
	
	/**
	 * 更新部门关联菜单
	 * @param deptId
	 * @param menuIds
	 * @return
	 * @throws Exception
	 */
	public void updateDeptMenu(Long deptId, Long... menuIds) throws Exception;
	
	/**
	 * 根据部门id获取菜单id列表
	 * @param deptId
	 * @throws Exception
	 */
	public List<Long> queryMenuIdListByDeptId(Long deptId) throws Exception;
}

package com.qyj.back.service;

import java.util.List;

import com.qyj.back.entity.SysUserModel;
import com.qyj.back.vo.SysUserBean;
import com.qyj.common.page.PageParam;

/**
 * 系统用户service
 * @author shitongle
 */
public interface SysUserService {

	/**
	 * 获取系统用户列表
	 * @param queryModel
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public List<SysUserModel> querySysUserList(SysUserModel queryModel, PageParam pageParam) throws Exception;

	/**
	 * 查询系统用户信息分页总记录数
	 * @param queryModel
	 * @param pageParam
	 * @return
	 */
	public Integer querySysUserTotal(SysUserModel queryModel, PageParam pageParam) throws Exception;

	/**
	 * 根据用户名和密码查询用户
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public List<SysUserModel> queryUserByNameAndPsw(String userName, String password) throws Exception;

	/**
	 * 添加用户信息
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	public Integer addUser(SysUserBean userBean) throws Exception;

	/**
	 * 删除用户信息
	 * @param ids 用户id数组
	 * @throws Exception
	 */
	public void delUser(Long... ids) throws Exception;

	/**
	 * 根据id查询系统用户
	 * @param userId 用户id
	 * @throws Exception
	 */
	public SysUserBean queryUserById(Long userId) throws Exception;
}

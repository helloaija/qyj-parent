package com.qyj.store.service;

import java.util.List;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.ResultBean;
import com.qyj.store.entity.SysUserModel;
import com.qyj.store.vo.SysUserBean;
import com.qyj.common.page.PageParam;

/**
 * 系统用户service
 * @author shitongle
 */
public interface SysUserService {

	/**
	 * 加载系统用户数据
	 * @param queryModel
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	PageBean listSysUserPage(SysUserModel queryModel, PageParam pageParam);

	/**
	 * 获取系统用户列表
	 * @param queryModel
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	List<SysUserModel> querySysUserList(SysUserModel queryModel, PageParam pageParam) throws Exception;

	/**
	 * 查询系统用户信息分页总记录数
	 * @param queryModel
	 * @param pageParam
	 * @return
	 */
	Integer querySysUserTotal(SysUserModel queryModel, PageParam pageParam) throws Exception;

	/**
	 * 根据用户名和密码查询用户
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	List<SysUserModel> queryUserByNameAndPsw(String userName, String password) throws Exception;

	/**
	 * 添加用户信息
	 * @param userModel 用户信息
	 * @param roleIds 关联的角色id
	 * @return
	 * @throws Exception
	 */
	ResultBean addUser(SysUserModel userModel, Long... roleIds);

	/**
	 * 更新用户信息
	 * @param userModel 用户信息
	 * @param roleIds   关联的角色id
	 * @return
	 * @throws Exception
	 */
	ResultBean updateUser(SysUserModel userModel, Long... roleIds);

	/**
	 * 删除用户信息
	 * @param ids 用户id数组
	 * @throws Exception
	 */
	void delUser(Long... ids) throws Exception;

	/**
	 * 根据id查询系统用户
	 * @param userId 用户id
	 * @throws Exception
	 */
	SysUserBean queryUserById(Long userId) throws Exception;

	/**
	 * 根据userId加载对应的角色
	 * @param userId
	 * @return
	 */
	ResultBean getUserRoleData(Long userId);

	/**
	 * 更新用户信息
	 * @param userModel 用户信息
	 */
	void updateUserInfo(SysUserModel userModel);
}

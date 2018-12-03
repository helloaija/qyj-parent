package com.qyj.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qyj.store.entity.SysUserModel;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

/**
 * 系统用户mapper接口
 * @author shitongle
 *
 */
public interface SysUserMapper {
	
	/**
	 * 查询系统用户信息分页数据
	 * @param queryModel
	 * @param pageParam
	 * @return
	 */
	List<SysUserModel> querySysUserList(@Param("sysUserModel")SysUserModel queryModel, @Param("pageParam")PageParam pageParam);

	/**
	 * 查询系统用户信息分页总记录数
	 * @param queryModel
	 * @param pageParam
	 * @return
	 */
	Integer querySysUserTotal(@Param("sysUserModel")SysUserModel queryModel, @Param("pageParam")PageParam pageParam);
	
	/**
	 * 插入系统用户信息
	 * @param userModel 用户信息model
	 * @return
	 */
	Integer insertUser(SysUserModel userModel);

	/**
	 * 更新系统用户信息
	 * @param userModel 用户信息model
	 * @return
	 */
	Integer updateUser(SysUserModel userModel);
	
	/**
	 * 根据用户id数组删除系统用户信息
	 * @param ids 用户信息model
	 * @return
	 */
	void delUser(@Param("ids")Long... ids);

	/**
	 * 根据userId获取用户信息
	 * @param userId
	 * @return
	 */
	SysUserModel getUserModelById(Long userId);
}

package com.qyj.service.dao;

import com.qyj.facade.entity.QyjUserEntity;

public interface QyjUserMapper {
	
	/**
	 * 根据登录手机号码获取用户
	 * @param phoneNum
	 * @return
	 */
	QyjUserEntity getUserByPhoneNum(String phoneNum);
	
	/**
	 * 插入用户
	 * @param userEntity
	 * @return
	 */
	int insertUser(QyjUserEntity userEntity);
	
	/**
	 * 更新用户
	 * @param userEntity
	 * @return
	 */
	int updateUser(QyjUserEntity userEntity);
}

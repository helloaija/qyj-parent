package com.qyj.service.biz;

import com.qyj.facade.vo.QyjUserBean;

public interface QyjUserBiz {
	
	/**
	 * 根据用户手机号获取用户
	 * @param phoneNum
	 * @return
	 * @throws Exception
	 */
	public QyjUserBean getUserByPhoneNum(String phoneNum) throws Exception;
	
	/**
	 * 插入用户
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	public int insertUser(QyjUserBean userBean) throws Exception;
	
	/**
	 * 更新用户
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	public int updateUser(QyjUserBean userBean) throws Exception;
}

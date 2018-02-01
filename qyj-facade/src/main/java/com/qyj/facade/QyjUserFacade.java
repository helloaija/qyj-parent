package com.qyj.facade;

import com.qyj.facade.vo.QyjUserBean;

/**
 * dubbo接口-用户
 * @author CTF_stone
 *
 */
public interface QyjUserFacade {
	
	/**
	 * 根据用户手机号获取用户
	 * @param phoneNum
	 * @return
	 * @throws Exception
	 */
	public QyjUserBean getUserByPhoneNum(String phoneNum) throws Exception;
	
	/**
	 * 根据openId获取用户
	 * @param phoneNum
	 * @return
	 * @throws Exception
	 */
	public QyjUserBean getUserByOpenId(String openId) throws Exception;
	
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

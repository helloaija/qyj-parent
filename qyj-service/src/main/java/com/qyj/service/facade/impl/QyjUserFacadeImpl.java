package com.qyj.service.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.facade.QyjUserFacade;
import com.qyj.facade.vo.QyjUserBean;
import com.qyj.service.biz.QyjUserBiz;

/**
 * dubbo接口实现-用户
 * @author CTF_stone
 *
 */
@Service("qyjUserFacade")
public class QyjUserFacadeImpl implements QyjUserFacade {
	
	@Autowired
	private QyjUserBiz userBiz;

	/**
	 * 根据用户手机号获取用户
	 * @param phoneNum
	 * @return
	 * @throws Exception
	 */
	@Override
	public QyjUserBean getUserByPhoneNum(String phoneNum) throws Exception {
		return userBiz.getUserByPhoneNum(phoneNum);
	}
	
	/**
	 * 根据openId获取用户
	 * @param phoneNum
	 * @return
	 * @throws Exception
	 */
	@Override
	public QyjUserBean getUserByOpenId(String openId) throws Exception {
		return userBiz.getUserByOpenId(openId);
	}
	
	/**
	 * 插入用户
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertUser(QyjUserBean userBean) throws Exception {
		return userBiz.insertUser(userBean);
	}
	
	/**
	 * 更新用户
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateUser(QyjUserBean userBean) throws Exception {
		return userBiz.updateUser(userBean);
	}

}

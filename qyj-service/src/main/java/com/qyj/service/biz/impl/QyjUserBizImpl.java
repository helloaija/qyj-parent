package com.qyj.service.biz.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.facade.entity.QyjUserEntity;
import com.qyj.facade.vo.QyjUserBean;
import com.qyj.service.biz.QyjUserBiz;
import com.qyj.service.dao.QyjUserMapper;

@Service("qyjUserBiz")
public class QyjUserBizImpl implements QyjUserBiz {

	@Autowired
	private QyjUserMapper userMapper;

	/**
	 * 根据用户手机号获取用户
	 * @param phoneNum
	 * @return
	 * @throws Exception
	 */
	@Override
	public QyjUserBean getUserByPhoneNum(String phoneNum) throws Exception {

		QyjUserEntity userEntity = userMapper.getUserByPhoneNum(phoneNum);
		if (userEntity == null) {
			return null;
		}

		QyjUserBean userBean = new QyjUserBean();

		BeanUtils.copyProperties(userEntity, userBean);

		return userBean;
	}
	
	/**
	 * 根据openId获取用户
	 * @param phoneNum
	 * @return
	 * @throws Exception
	 */
	@Override
	public QyjUserBean getUserByOpenId(String openId) throws Exception {

		QyjUserEntity userEntity = userMapper.getUserByOpenId(openId);
		if (userEntity == null) {
			return null;
		}

		QyjUserBean userBean = new QyjUserBean();

		BeanUtils.copyProperties(userEntity, userBean);

		return userBean;
	}

	/**
	 * 插入用户
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertUser(QyjUserBean userBean) throws Exception {
		QyjUserEntity userEntity = new QyjUserEntity();

		BeanUtils.copyProperties(userBean, userEntity);

		return userMapper.insertUser(userEntity);
	}

	/**
	 * 更新用户
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateUser(QyjUserBean userBean) throws Exception {
		QyjUserEntity userEntity = new QyjUserEntity();

		BeanUtils.copyProperties(userBean, userEntity);

		return userMapper.updateUser(userEntity);
	}

}

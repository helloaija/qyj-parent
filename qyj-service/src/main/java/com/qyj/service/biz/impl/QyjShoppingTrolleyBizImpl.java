package com.qyj.service.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.facade.entity.QyjShoppingTrolleyEntity;
import com.qyj.facade.vo.QyjShoppingTrolleyBean;
import com.qyj.service.biz.QyjShoppingTrolleyBiz;
import com.qyj.service.dao.QyjShoppingTrolleyMapper;

/**
 * 业务接口实现 - 购物车
 * @author CTF_stone
 */
@Service
public class QyjShoppingTrolleyBizImpl implements QyjShoppingTrolleyBiz {
	
	@Autowired
	private QyjShoppingTrolleyMapper shoppingTrolleyMapper;
	
	/**
	 * 添加购物车记录，返回添加记录id
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long addShoppingTrolley(QyjShoppingTrolleyBean bean) throws Exception {
		if (bean == null) {
			throw new Exception("添加的购物车记录为空");
		}
		QyjShoppingTrolleyEntity entity = new QyjShoppingTrolleyEntity();
		BeanUtils.copyProperties(bean, entity);
		
		// 插入购物车记录
		if (shoppingTrolleyMapper.insertShoppingTrolley(entity) <= 0) {
			throw new Exception("添加购物车记录失败");
		}
		return entity.getId();
	}

	/**
	 * 根据条件删除购物车记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean delShoppingTrolley(QyjShoppingTrolleyBean bean) throws Exception {
		if (bean == null) {
			throw new Exception("删除条件为空");
		}
		QyjShoppingTrolleyEntity entity = new QyjShoppingTrolleyEntity();
		BeanUtils.copyProperties(bean, entity);
		
		// 删除购物车记录
		shoppingTrolleyMapper.delShoppingTrolley(entity);
		
		return Boolean.TRUE;
	}
	
	/**
	 * 根据用户id获取购物车记录列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<QyjShoppingTrolleyBean> listShoppingTrolleyByUserId(Long userId) throws Exception {
		// 查询用户下的购物车记录
		List<QyjShoppingTrolleyEntity> entityList = shoppingTrolleyMapper.listShoppingTrolleyByUserId(userId);
		if (entityList == null) {
			return null;
		}
		List<QyjShoppingTrolleyBean> beanList = new ArrayList<QyjShoppingTrolleyBean>();
		QyjShoppingTrolleyBean bean = null;
		for (QyjShoppingTrolleyEntity entity : entityList) {
			bean = new QyjShoppingTrolleyBean();
			BeanUtils.copyProperties(entity, bean);
		}
		return beanList;
	}
}

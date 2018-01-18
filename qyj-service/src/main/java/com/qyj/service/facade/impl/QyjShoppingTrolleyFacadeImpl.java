package com.qyj.service.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.facade.QyjShoppingTrolleyFacade;
import com.qyj.facade.entity.QyjShoppingTrolleyEntity;
import com.qyj.facade.vo.QyjShoppingTrolleyBean;
import com.qyj.service.biz.QyjShoppingTrolleyBiz;

/**
 * Dubbo服务接口实现 - 购物车
 * @author CTF_stone
 */
@Service("qyjShoppingTrolleyFacade")
public class QyjShoppingTrolleyFacadeImpl implements QyjShoppingTrolleyFacade {

	@Autowired
	private QyjShoppingTrolleyBiz shoppingTrolleyBiz;

	/**
	 * 添加购物车记录，返回添加记录id
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long addShoppingTrolley(QyjShoppingTrolleyBean bean) throws Exception {
		return shoppingTrolleyBiz.addShoppingTrolley(bean);
	}

	/**
	 * 根据条件删除购物车记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean delShoppingTrolley(QyjShoppingTrolleyBean bean) throws Exception {
		return shoppingTrolleyBiz.delShoppingTrolley(bean);
	}

	/**
	 * 根据用户id获取购物车记录列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<QyjShoppingTrolleyBean> listShoppingTrolleyByUserId(Long userId) throws Exception {
		return shoppingTrolleyBiz.listShoppingTrolleyByUserId(userId);
	}

	/**
	 * 批量删除购物车记录
	 * @param ids
	 * @param userId
	 * @return
	 */
	@Override
	public Boolean batchDelShoppingTrolley(Long[] ids, Long userId) throws Exception {
		return shoppingTrolleyBiz.batchDelShoppingTrolley(ids, userId);
	}

	/**
	 * 批量更新购物车
	 * @param entityList
	 * @return
	 */
	public int updateShoppingTrolleyList(List<QyjShoppingTrolleyBean> beanList) throws Exception {
		return shoppingTrolleyBiz.updateShoppingTrolleyList(beanList);
	}

	/**
	 * 根据查询条件获取购物车记录
	 * @param paramMap
	 * @return
	 */
	public List<QyjShoppingTrolleyBean> listShoppingTrolleyByMap(Map<String, Object> paramMap) throws Exception {
		return shoppingTrolleyBiz.listShoppingTrolleyByMap(paramMap);
	}
}

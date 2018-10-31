package com.qyj.store.dao;

import java.util.List;

import com.qyj.store.entity.QyjOrderGoodsEntity;

/**
 * 订单商品
 * @author CTF_stone
 *
 */
public interface QyjOrderGoodsMapper {
	
	/**
	 * 根据订单id查询订单商品
	 * @param orderGoods
	 * @return
	 */
	List<QyjOrderGoodsEntity> listOrderGoodsByOrderId(Long orderId);
	
}
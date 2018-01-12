package com.qyj.service.dao;

import java.util.List;

import com.qyj.facade.entity.QyjOrderGoodsEntity;

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
	
	/**
	 * 根据查询条件查询订单商品
	 * @param orderGoods
	 * @return
	 */
	List<QyjOrderGoodsEntity> listOrderByModel(QyjOrderGoodsEntity orderGoods);

	/**
	 * 批量插入订单商品
	 * @param orderGoodsList
	 * @return
	 */
    int insertOrderGoodsList(List<QyjOrderGoodsEntity> orderGoodsList);

}
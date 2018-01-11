package com.qyj.service.dao;

import java.util.List;

import com.qyj.facade.entity.QyjOrderEntity;

/**
 * 订单
 * @author CTF_stone
 *
 */
public interface QyjOrderMapper {
	/**
	 * 根据id获取订单
	 * @param id
	 * @return
	 */
	QyjOrderEntity getOrderById(Long id);

	/**
	 * 根据查询条件获取订单
	 * @param OrderEntity
	 * @return
	 */
    List<QyjOrderEntity> listOrderByModel(QyjOrderEntity orderEntity);

    /**
     * 插入订单
     * @param orderEntity
     * @return
     */
    int insertOrder(QyjOrderEntity orderEntity);

    /**
     * 更新订单
     * @param orderEntity
     * @return
     */
    int updateOrder(QyjOrderEntity orderEntity);
}
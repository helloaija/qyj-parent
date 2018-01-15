package com.qyj.service.dao;

import java.util.List;
import java.util.Map;

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
	 * 根据查询条件获取关联商品的订单列表
	 * @param OrderEntity
	 * @return
	 */
    List<QyjOrderEntity> listOrderAndGoodsByModel(QyjOrderEntity orderEntity);

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
    
    /**
	 * 根据条件统计订单数量
	 * @param paramMap
	 * @return
	 */
	Integer countOrder(Map<String, Object> paramMap);

	/**
	 * 根据条件获取订单列表数据
	 * @param paramMap
	 * @return
	 */
	List<QyjOrderEntity> listOrder(Map<String, Object> paramMap);
}
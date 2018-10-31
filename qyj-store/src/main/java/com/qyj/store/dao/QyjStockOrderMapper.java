package com.qyj.store.dao;

import com.qyj.store.entity.QyjOrderEntity;
import com.qyj.store.entity.QyjStockOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单
 * @author CTF_stone
 *
 */
public interface QyjStockOrderMapper {

    /**
	 * 根据查询条件获取关联商品的订单列表
	 * @param paramMap
	 * @return
	 */
    List<QyjStockOrderEntity> listStockOrderAndProduct(Map<String, Object> paramMap);

	/**
	 * 插入进货单
	 * @param stockOrder
	 * @return
	 */
	int insertStockOrder(QyjStockOrderEntity stockOrder);

    /**
	 * 根据条件统计订单数量
	 * @param paramMap
	 * @return
	 */
	Integer countStockOrder(Map<String, Object> paramMap);

	/**
	 * 获取stockId获取关联产品的进货单
	 * @param stockId
	 * @return
	 */
	QyjStockOrderEntity getStockOrderAndProductByStockId(Long stockId);

	/**
	 * 更新进货单
	 * @param stockOrder
	 * @return
	 */
	int updateStockOrder(QyjStockOrderEntity stockOrder);

	/**
	 * 删除进货单
	 * @param stockId
	 * @return
	 */
	int deleteStockOrder(Long stockId);
}
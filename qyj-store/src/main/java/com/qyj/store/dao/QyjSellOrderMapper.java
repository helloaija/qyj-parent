package com.qyj.store.dao;

import com.qyj.store.entity.QyjSellOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 销售订单
 * @author CTF_stone
 *
 */
public interface QyjSellOrderMapper {

    /**
	 * 根据查询条件获取关联商品的订单列表
	 * @param paramMap
	 * @return
	 */
    List<QyjSellOrderEntity> listSellOrderAndProduct(Map<String, Object> paramMap);

    /**
     * 插入销售单
	 * @param sellOrder
     * @return
     */
	int insertSellOrder(QyjSellOrderEntity sellOrder);

    /**
	 * 根据条件统计订单数量
	 * @param paramMap
	 * @return
	 */
	Integer countSellOrder(Map<String, Object> paramMap);

	/**
     * 获取sellId获取关联产品的销售单
	 * @param sellId
     * @return
     */
	QyjSellOrderEntity getSellOrderAndProductBySellId(Long sellId);

	/**
     * 更新销售单
	 * @param sellOrder
     * @return
     */
	int updateSellOrder(QyjSellOrderEntity sellOrder);

	/**
     * 删除销售单
	 * @param sellId
     * @return
     */
	int deleteSellOrder(Long sellId);
}
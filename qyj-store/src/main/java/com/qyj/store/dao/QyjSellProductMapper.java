package com.qyj.store.dao;

import com.qyj.store.entity.QyjSellProductEntity;

import java.util.List;

/**
 * 销售单商品
 * @author CTF_stone
 *
 */
public interface QyjSellProductMapper {

	/**
	 * 根据订单id查询订单商品
	 * @param sellId
	 * @return
	 */
	List<QyjSellProductEntity> listSellProductBySellId(Long sellId);

	/**
	 * 批量插入订单商品
	 * @param sellProductList
	 * @return
	 */
	int insertSellProductList(List<QyjSellProductEntity> sellProductList);

	/**
     * 批量更新销售单产品
	 * @param sellProductList
     * @return
     */
	int updateSellProductList(List<QyjSellProductEntity> sellProductList);

	/**
	 * 批量删除销售单产品
	 * @param productIdList
	 * @return
	 */
	int deleteSellProductList(List<Long> productIdList);

	/**
     * 根据sellId删除销售单产品
	 * @param sellId
     * @return
     */
	int deleteSellProductBySellId(Long sellId);
}
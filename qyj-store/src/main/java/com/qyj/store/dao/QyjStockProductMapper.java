package com.qyj.store.dao;

import com.qyj.store.entity.QyjOrderGoodsEntity;
import com.qyj.store.entity.QyjStockProductEntity;

import java.util.List;

/**
 * 进货单商品
 * @author CTF_stone
 *
 */
public interface QyjStockProductMapper {

	/**
	 * 根据订单id查询订单商品
	 * @param stockId
	 * @return
	 */
	List<QyjStockProductEntity> listStockProductByStockId(Long stockId);

	/**
	 * 批量插入订单商品
	 * @param stockProductList
	 * @return
	 */
	int insertStockProductList(List<QyjStockProductEntity> stockProductList);

	/**
	 * 批量更新进货单产品
	 * @param stockProductList
	 * @return
	 */
	int updateStockProductList(List<QyjStockProductEntity> stockProductList);

	/**
	 * 批量删除进货单产品
	 * @param productIdList
	 * @return
	 */
	int deleteStockProductList(List<Long> productIdList);

	/**
	 * 根据stockId删除进货单产品
	 * @param stockId
	 * @return
	 */
	int deleteStockProductByStockId(Long stockId);
}
package com.qyj.store.dao;

import java.util.List;

import com.qyj.store.entity.QyjProductDetailEntity;

/**
 * 产品信息mapper
 * 
 * @author CTF_stone
 *
 */
public interface QyjProductDetailMapper {
	
	/**
	 * 批量插入产品详细
	 * @param productDetailList
	 * @return
	 */
	int insertProductDetailList(List<QyjProductDetailEntity> productDetailList);
	
	/**
	 * 根据产品id删除产品详细
	 * @param id
	 * @return
	 */
	int delProductDetailByProductId(Long productId);
	
	/**
	 * 根据产品id获取产品详情（不包括content属性）列表
	 * @param productId
	 * @return
	 */
	List<QyjProductDetailEntity> listProductDetailByProductId(Long productId);
	
	/**
	 * 根据产品id获取产品详情（包括content属性）列表
	 * @param productId
	 * @return
	 */
	List<QyjProductDetailEntity> listProductDetailWithBlobByProductId(Long productId);
	
	/**
	 * 根据删除条件删除产品详情
	 * @param 删除条件
	 * @return
	 */
	int delProductDetailByCondition(String whereCondition);
	
	/**
	 * 批量更新产品详情
	 * @param productDetailList
	 * @return
	 */
	int updateProductDetailList(List<QyjProductDetailEntity> productDetailList);
	
	
	
	int deleteByPrimaryKey(Long id);

	int insert(QyjProductDetailEntity record);

	int insertSelective(QyjProductDetailEntity record);

	QyjProductDetailEntity selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(QyjProductDetailEntity record);

	int updateByPrimaryKeyWithBLOBs(QyjProductDetailEntity record);

	int updateByPrimaryKey(QyjProductDetailEntity record);
}
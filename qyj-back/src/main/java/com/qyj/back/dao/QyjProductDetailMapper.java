package com.qyj.back.dao;

import java.util.List;

import com.qyj.back.entity.QyjProductDetailEntity;

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
	
	int deleteByPrimaryKey(Long id);

	int insert(QyjProductDetailEntity record);

	int insertSelective(QyjProductDetailEntity record);

	QyjProductDetailEntity selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(QyjProductDetailEntity record);

	int updateByPrimaryKeyWithBLOBs(QyjProductDetailEntity record);

	int updateByPrimaryKey(QyjProductDetailEntity record);
}
package com.qyj.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qyj.facade.entity.QyjShoppingTrolleyEntity;

/**
 * Mapper-购物车
 * @author CTF_stone
 *
 */
public interface QyjShoppingTrolleyMapper {
	
	/**
	 * 插入购物车记录
	 * @param entity
	 * @return
	 */
	int insertShoppingTrolley(QyjShoppingTrolleyEntity entity);
	
	/**
	 * 根据id删除购物车记录
	 * @param id
	 * @return
	 */
	int delShoppingTrolley(QyjShoppingTrolleyEntity entity);
	
	/**
	 * 根据userId获取购物车记录列表
	 * @param userId
	 * @return
	 */
	List<QyjShoppingTrolleyEntity> listShoppingTrolleyByUserId(Long userId);
	
	/**
	 * 批量删除购物车记录
	 * @param ids
	 * @param userId
	 * @return
	 */
	int batchDelShoppingTrolley(@Param("ids") Long[] ids, @Param("userId") Long userId);
}

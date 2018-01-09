package com.qyj.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qyj.facade.entity.QyjAddressEntity;

/**
 * 地址mapper
 * @author CTF_stone
 */
public interface QyjAddressMapper {

	/**
	 * 根据主键获取地址
	 * @param id
	 * @return
	 */
	QyjAddressEntity getAddressById(Long id);

	/**
	 * 根据用户id获取地址列表
	 * @param userId
	 * @return
	 */
	List<QyjAddressEntity> listAddressByUserId(Long userId);

	/**
	 * 根据主键删除地址
	 * @param id
	 * @param userId 对应用户id
	 * @return
	 */
	int deleteAddressById(@Param("id")Long id, @Param("userId")Long userId);

	/**
	 * 新增地址
	 * @param record
	 * @return
	 */
	int insertAddress(QyjAddressEntity record);

	/**
	 * 更新对应id的地址
	 * @param record
	 * @return
	 */
	int updateAddressById(QyjAddressEntity record);
	
	/**
	 * 更新用户下的地址为非默认地址
	 * @param userId
	 * @return
	 */
	int updateDefaultAddress(@Param("userId")Long userId);
}
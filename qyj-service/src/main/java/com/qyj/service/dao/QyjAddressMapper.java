package com.qyj.service.dao;

import java.util.List;

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
	 * @return
	 */
	int deleteAddressById(Long id);

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
}
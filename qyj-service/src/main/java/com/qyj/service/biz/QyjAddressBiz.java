package com.qyj.service.biz;

import java.util.List;

import com.qyj.facade.vo.QyjAddressBean;

/**
 * 服务层接口-地址
 * @author CTF_stone
 */
public interface QyjAddressBiz {

	/**
	 * 根据用户id获取地址列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<QyjAddressBean> listAddressByUserId(Long userId) throws Exception;

	/**
	 * 根据id查询地址
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public QyjAddressBean getAddressById(Long addressId) throws Exception;
	
	/**
	 * 新增地址
	 * @param addressBean
	 * @return
	 * @throws Exception
	 */
	public Boolean insertAddress(QyjAddressBean addressBean) throws Exception;
	
	/**
	 * 更新对应id的地址
	 * @param addressBean
	 * @return
	 * @throws Exception
	 */
	public Boolean updateAddressById(QyjAddressBean addressBean) throws Exception;
}

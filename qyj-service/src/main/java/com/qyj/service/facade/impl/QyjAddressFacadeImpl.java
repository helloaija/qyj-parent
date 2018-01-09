package com.qyj.service.facade.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.facade.QyjAddressFacade;
import com.qyj.facade.vo.QyjAddressBean;
import com.qyj.service.biz.impl.QyjAddressBizImpl;

/**
 * 产品Dubbo服务接口实现
 * @author CTF_stone
 */
@Service("qyjAddressFacade")
public class QyjAddressFacadeImpl implements QyjAddressFacade {

	@Autowired
	private QyjAddressBizImpl addressBiz;

	private static final Logger logger = LoggerFactory.getLogger(QyjAddressFacadeImpl.class);

	/**
	 * 根据用户id获取地址列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<QyjAddressBean> listAddressByUserId(Long userId) throws Exception {
		return addressBiz.listAddressByUserId(userId);
	}

	/**
	 * 根据id查询地址
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	@Override
	public QyjAddressBean getAddressById(Long addressId) throws Exception {
		logger.info("getAddressById addressId:{}", addressId);
		return addressBiz.getAddressById(addressId);
	}
	
	/**
	 * 新增地址
	 * @param addressBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean insertAddress(QyjAddressBean addressBean) throws Exception {
		return addressBiz.insertAddress(addressBean);
	}

	/**
	 * 更新对应id的地址
	 * @param addressBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean updateAddressById(QyjAddressBean addressBean) throws Exception {
		return addressBiz.updateAddressById(addressBean);
	}
	
	/**
	 * 根据地址id和用户id删除地址
	 * @param id
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean deleteAddressById(Long id, Long userId) throws Exception {
		return addressBiz.deleteAddressById(id, userId);
	}
}

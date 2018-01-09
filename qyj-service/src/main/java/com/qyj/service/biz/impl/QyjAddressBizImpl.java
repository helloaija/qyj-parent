package com.qyj.service.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.facade.entity.QyjAddressEntity;
import com.qyj.facade.vo.QyjAddressBean;
import com.qyj.service.biz.QyjAddressBiz;
import com.qyj.service.dao.QyjAddressMapper;

/**
 * 服务层接口实现-地址
 * @author CTF_stone
 */
@Service
public class QyjAddressBizImpl implements QyjAddressBiz {
	
	@Autowired
	private QyjAddressMapper addressMapper;

	/**
	 * 根据用户id获取地址列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<QyjAddressBean> listAddressByUserId(Long userId) throws Exception {
		List<QyjAddressEntity> entityList = addressMapper.listAddressByUserId(userId);
		
		if (entityList == null) {
			return null;
		}
		
		List<QyjAddressBean> beanList = new ArrayList<QyjAddressBean>();
		QyjAddressBean bean = null;
		for(QyjAddressEntity entity : entityList) {
			bean = new QyjAddressBean();
			BeanUtils.copyProperties(entity, bean);
			beanList.add(bean);
		}
		
		return beanList;
	}

	/**
	 * 根据id查询地址
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	@Override
	public QyjAddressBean getAddressById(Long addressId) throws Exception {
		QyjAddressEntity entity = addressMapper.getAddressById(addressId);
		if (entity == null) {
			return null;
		}
		QyjAddressBean bean = new QyjAddressBean();
		BeanUtils.copyProperties(entity, bean);
		return bean;
	}
	
	/**
	 * 新增地址
	 * @param addressBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean insertAddress(QyjAddressBean addressBean) throws Exception {
		if (addressBean == null) {
			throw new Exception("需要更新的地址为空！");
		}
		if (addressBean.getUserId() == null) {
			throw new Exception("需要更新的地址用户id为空！");
		}
		
		// 如果这条新增的地址是默认地址，把其他的地址设为非默认
		if (addressBean.getIsDefault() != null && addressBean.getIsDefault()) {
			addressMapper.updateDefaultAddress(addressBean.getUserId());
		}
				
		QyjAddressEntity addressEntity = new QyjAddressEntity();
		BeanUtils.copyProperties(addressBean, addressEntity);
		// 更新地址
		int result = addressMapper.insertAddress(addressEntity);
		if (result <= 0) {
			throw new Exception("新增记录失败！");
		}
		
		return Boolean.TRUE;
	}
	
	/**
	 * 更新对应id的地址
	 * @param addressBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean updateAddressById(QyjAddressBean addressBean) throws Exception {
		if (addressBean == null) {
			throw new Exception("需要更新的地址为空！");
		}
		if (addressBean.getId() == null) {
			throw new Exception("需要更新的地址id为空！");
		}
		if (addressBean.getUserId() == null) {
			throw new Exception("需要更新的地址用户id为空！");
		}
		
		// 如果这条更新的地址是默认地址，把其他的地址设为非默认
		if (addressBean.getIsDefault() != null && addressBean.getIsDefault()) {
			addressMapper.updateDefaultAddress(addressBean.getUserId());
		}
		
		QyjAddressEntity addressEntity = new QyjAddressEntity();
		BeanUtils.copyProperties(addressBean, addressEntity);
		// 更新地址
		int result = addressMapper.updateAddressById(addressEntity);
		if (result <= 0) {
			throw new Exception("没有需要更新的记录！");
		}
		
		return Boolean.TRUE;
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
		if (id == null) {
			throw new Exception("删除的地址id为空！");
		}
		if (userId == null) {
			throw new Exception("登录用户id为空！");
		}
		
		// 删除地址
		int result = addressMapper.deleteAddressById(id, userId);
		
		if (result != 1) {
			throw new Exception("删除地址失败");
		}
		
		return Boolean.TRUE;
	}
}

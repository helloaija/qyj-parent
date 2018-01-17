package com.qyj.facade;

import java.util.List;

import com.qyj.facade.entity.QyjShoppingTrolleyEntity;
import com.qyj.facade.vo.QyjShoppingTrolleyBean;

/**
 * Dubbo服务接口 - 购物车
 * @author CTF_stone
 */
public interface QyjShoppingTrolleyFacade {
	
	/**
	 * 添加购物车记录，返回添加记录id
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	Long addShoppingTrolley(QyjShoppingTrolleyBean bean) throws Exception;

	/**
	 * 根据id删除购物车记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Boolean delShoppingTrolley(QyjShoppingTrolleyBean bean) throws Exception;
	
	/**
	 * 根据用户id获取购物车记录列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<QyjShoppingTrolleyBean> listShoppingTrolleyByUserId(Long userId) throws Exception;
	
	/**
	 * 批量删除购物车记录
	 * @param ids
	 * @param userId
	 * @return
	 */
	Boolean batchDelShoppingTrolley(Long[] ids, Long userId) throws Exception;
}

package com.qyj.service.biz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qyj.facade.entity.QyjShoppingTrolleyEntity;
import com.qyj.facade.vo.QyjShoppingTrolleyBean;

/**
 * 业务接口 - 购物车
 * @author CTF_stone
 */
public interface QyjShoppingTrolleyBiz {
	
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
	
	/**
	 * 批量更新购物车
	 * @param entityList
	 * @return
	 */
	int updateShoppingTrolleyList(List<QyjShoppingTrolleyBean> beanList) throws Exception;
	
	/**
	 * 根据查询条件获取购物车记录
	 * @param paramMap
	 * @return
	 */
	List<QyjShoppingTrolleyBean> listShoppingTrolleyByMap(Map<String, Object> paramMap) throws Exception;
}

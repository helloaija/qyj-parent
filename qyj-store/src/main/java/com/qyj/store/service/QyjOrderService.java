package com.qyj.store.service;

import java.util.List;
import java.util.Map;

import com.qyj.store.vo.QyjOrderBean;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

/**
 * 服务层接口-我的订单
 * @author CTF_stone
 */
public interface QyjOrderService {
	
	/**
	 * 根据id获取订单
	 * @param id
	 * @return
	 */
	QyjOrderBean getOrderById(Long id) throws Exception;

	/**
	 * 根据查询条件查询关联商品的订单
	 * @param queryBean
	 * @return
	 * @throws Exception
	 */
	List<QyjOrderBean> listOrderAndGoodsByModel(QyjOrderBean queryBean) throws Exception;
	
	/**
	 * 获取订单分页数据
	 * @param pageParam 分页信息
	 * @param paramMap 查询参数
	 * @return
	 * @throws Exception
	 */
	PageBean listOrderPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 更新订单
	 * @param orderBean
	 * @return
	 * @throws Exception
	 */
	int updateOrder(QyjOrderBean orderBean) throws Exception;
	
}

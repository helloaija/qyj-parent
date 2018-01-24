package com.qyj.service.facade.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.facade.QyjOrderFacade;
import com.qyj.facade.vo.QyjOrderBean;
import com.qyj.facade.vo.QyjShoppingTrolleyBean;
import com.qyj.service.biz.QyjOrderBiz;

/**
 * Dubbo服务接口实现-订单
 * @author CTF_stone
 *
 */
@Service("qyjOrderFacade")
public class QyjOrderFacadeImpl implements QyjOrderFacade {

	@Autowired
	private QyjOrderBiz orderBiz;
	
	private static final Logger logger = LoggerFactory.getLogger(QyjNewsInfoFacadeImpl.class);
	
	/**
	 * 根据查询条件查询关联商品的订单
	 * @param queryBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<QyjOrderBean> listOrderAndGoodsByModel(QyjOrderBean queryBean) throws Exception {
		return orderBiz.listOrderAndGoodsByModel(queryBean);
	}
	
	/**
	 * 获取订单分页数据
	 * @param pageParam
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean listOrderPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception {
		logger.info("listOrderPage params,pageParam={},paramMap={}", pageParam, paramMap);
		return orderBiz.listOrderPage(pageParam, paramMap);
	}
	
	/**
	 * 更新订单
	 * @param orderBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean updateOrder(QyjOrderBean orderBean) throws Exception {
		logger.info("updateOrder orderBean", orderBean.toString());
		return orderBiz.updateOrder(orderBean);
	}
	
	/**
	 * 保存购物车订单，返回订单id
	 * @param orderBean
	 * @param shoppingTrolleyBeanList
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long saveTrolleyOrder(QyjOrderBean orderBean, List<QyjShoppingTrolleyBean> shoppingTrolleyBeanList) throws Exception {
		return orderBiz.saveTrolleyOrder(orderBean, shoppingTrolleyBeanList);
	}
	
	/**
	 * 保存关联的商品订单，不关联购物车，返回主键id
	 * @param orderBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long saveGoodsOrder(QyjOrderBean orderBean) throws Exception {
		return orderBiz.saveGoodsOrder(orderBean);
	}
	
	/**
	 * 确认支付订单
	 * @param orderId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean confirmPayOrder(Long orderId, Long userId) throws Exception {
		return orderBiz.confirmPayOrder(orderId, userId);
	}

	/**
	 * 取消订单
	 * @param orderId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean cancelOrder(Long orderId, Long userId) throws Exception {
		return orderBiz.cancelOrder(orderId, userId);
	}
}

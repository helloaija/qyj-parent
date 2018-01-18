package com.qyj.service.biz.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.common.utils.CommonEnums.OrderStateEnum;
import com.qyj.common.utils.CommonEnums.ProductStatusEnum;
import com.qyj.common.utils.CommonUtils;
import com.qyj.facade.entity.QyjOrderEntity;
import com.qyj.facade.entity.QyjOrderGoodsEntity;
import com.qyj.facade.entity.QyjShoppingTrolleyEntity;
import com.qyj.facade.vo.QyjOrderBean;
import com.qyj.facade.vo.QyjShoppingTrolleyBean;
import com.qyj.service.biz.QyjOrderBiz;
import com.qyj.service.dao.QyjOrderGoodsMapper;
import com.qyj.service.dao.QyjOrderMapper;
import com.qyj.service.dao.QyjShoppingTrolleyMapper;

/**
 * 服务层接口实现-我的订单
 * @author CTF_stone
 */
@Service
public class QyjOrderBizImpl implements QyjOrderBiz {
	
	private static Logger logger = LoggerFactory.getLogger(QyjOrderBizImpl.class);
	
	@Autowired
	private QyjOrderMapper orderMapper;
	
	@Autowired
	private QyjShoppingTrolleyMapper shoppingTrolleyMapper;
	
	@Autowired
	private QyjOrderGoodsMapper orderGoodsMapper;
	
	/**
	 * 根据查询条件查询关联商品的订单
	 * @param queryBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<QyjOrderBean> listOrderAndGoodsByModel(QyjOrderBean queryBean) throws Exception {
		if (queryBean == null) {
			throw new Exception("查询queryBean为空");
		}
		
		QyjOrderEntity queryEntity = new QyjOrderEntity();
		BeanUtils.copyProperties(queryBean, queryEntity);
		
		// 获取订单数据
		List<QyjOrderEntity> orderEntityList = orderMapper.listOrderAndGoodsByModel(queryEntity);
		if (orderEntityList == null || orderEntityList.isEmpty()) {
			return null;
		}
		
		List<QyjOrderBean> orderBeanList = new ArrayList<QyjOrderBean>();
		for (QyjOrderEntity entity : orderEntityList) {
			orderBeanList.add(entity.toBean());
		}
		
		return orderBeanList;
	}

	/**
	 * 获取订单分页数据
	 * @param pageParam 分页信息
	 * @param paramMap 查询参数
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean listOrderPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception {
		if (pageParam == null) {
			pageParam = new PageParam();
		}
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		// 订单数量
		int totalCount = orderMapper.countOrder(paramMap);
		logger.info("listOrderPage paramMap:{}, totalCount:{}", paramMap, totalCount);

		if (totalCount <= 0) {
			return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), 0, null);
		}

		pageParam.setTotalCount(totalCount);
		// 计算分页信息
		pageParam.splitPageInstance();

		paramMap.put("pageParam", pageParam);

		// 获取分页数据列表
		List<QyjOrderEntity> projectList = orderMapper.listOrder(paramMap);

		return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), totalCount, projectList);
	}

	/**
	 * 更新订单
	 * @param orderBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean updateOrder(QyjOrderBean orderBean) throws Exception {
		if (orderBean == null) {
			throw new Exception("更新订单为空");
		}
		
		QyjOrderEntity orderEntity = new QyjOrderEntity();
		BeanUtils.copyProperties(orderBean, orderEntity);
		
		// 更新订单
		if (orderMapper.updateOrder(orderEntity) <= 0) {
			throw new Exception("没有更新任何订单");
		}
		return Boolean.TRUE;
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
		if (shoppingTrolleyBeanList == null || shoppingTrolleyBeanList.isEmpty()) {
			throw new Exception("订单商品为空");
		}
		Long[] ids = new Long[shoppingTrolleyBeanList.size()];
		for (int i = 0; i < shoppingTrolleyBeanList.size(); i ++ ) {
			ids[i] = shoppingTrolleyBeanList.get(i).getId();
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ids", ids);
		paramMap.put("userId", orderBean.getUserId());
		
		// 查询购物车记录已经对应的产品
		List<QyjShoppingTrolleyEntity> shoppingTrolleyEntityList = shoppingTrolleyMapper.listShoppingTrolleyByMap(paramMap);
		// 订单对应的商品列表
		List<QyjOrderGoodsEntity> orderGoodsEntityList = new ArrayList<QyjOrderGoodsEntity>();
		
		// 订单下商品总价
		BigDecimal totalPrice = new BigDecimal(0);
		QyjOrderGoodsEntity orderGoodsEntity = null;
		for (QyjShoppingTrolleyEntity shoppingTrolleyEntity : shoppingTrolleyEntityList) {
			if (StringUtils.isEmpty(shoppingTrolleyEntity.getProductTitle())) {
				throw new Exception("商品[" + shoppingTrolleyEntity.getProductId() + "]不存在或已经过期");
			}
			if (!ProductStatusEnum.PUTAWAY.toString().equals(shoppingTrolleyEntity.getProductStatus())) {
				throw new Exception("商品[" + shoppingTrolleyEntity.getProductTitle() + "]已经下架");
			}
			if (shoppingTrolleyEntity.getNumber() <= 0) {
				shoppingTrolleyEntity.setNumber(1);
			}
			orderGoodsEntity = new QyjOrderGoodsEntity();
			// 获取商品价格
			orderGoodsEntity.setPrice(shoppingTrolleyEntity.getProductPrice());
			// 产品标题
			orderGoodsEntity.setProductTitle(shoppingTrolleyEntity.getProductTitle());
			orderGoodsEntity.setProductId(shoppingTrolleyEntity.getProductId());
			orderGoodsEntity.setNumber(shoppingTrolleyEntity.getNumber());
			orderGoodsEntity.setUserId(orderBean.getUserId());
			if (shoppingTrolleyEntity.getProductPrice() != null) {
				// 计算总价
				totalPrice = totalPrice.add(shoppingTrolleyEntity.getProductPrice()).multiply(new BigDecimal(shoppingTrolleyEntity.getNumber()));
			}
			
			orderGoodsEntityList.add(orderGoodsEntity);
		}
		
		Date nowDate = new Date();
		// 创建订单
		QyjOrderEntity orderEntity = new QyjOrderEntity();
		BeanUtils.copyProperties(orderBean, orderEntity);
		orderEntity.setCreateTime(nowDate);
		orderEntity.setUpdateTime(nowDate);
		orderEntity.setOrderNumber(CommonUtils.getUid().toString());
		// 订单状态为未支付
		orderEntity.setStatus(OrderStateEnum.UNPAY.toString());
		// 设置订单价格
		orderEntity.setOrderAmount(totalPrice);
		orderEntity.setModifyAmount(totalPrice);
		// 保存订单
		if (orderMapper.insertOrder(orderEntity) <= 0) {
			throw new Exception("保存订单失败");
		}
		
		// 设置商品信息
		for (QyjOrderGoodsEntity orderGoodsEntity2 : orderGoodsEntityList) {
			orderGoodsEntity2.setCreateTime(nowDate);
			orderGoodsEntity2.setOrderId(orderEntity.getId());
		}
		
		if (orderGoodsMapper.insertOrderGoodsList(orderGoodsEntityList) <= 0) {
			throw new Exception("保存订单商品失败");
		}
		
		// 生成订单后删除购物车记录
		shoppingTrolleyMapper.batchDelShoppingTrolley(ids, orderBean.getUserId());
		
		return orderEntity.getId();
	}
}

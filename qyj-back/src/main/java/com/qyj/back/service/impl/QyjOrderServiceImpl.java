package com.qyj.back.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.back.dao.QyjOrderMapper;
import com.qyj.back.entity.QyjOrderEntity;
import com.qyj.back.service.QyjOrderService;
import com.qyj.back.vo.QyjOrderBean;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

/**
 * 服务层接口实现-我的订单
 * @author CTF_stone
 */
@Service
public class QyjOrderServiceImpl implements QyjOrderService {

	private static Logger logger = LoggerFactory.getLogger(QyjOrderServiceImpl.class);

	@Autowired
	private QyjOrderMapper orderMapper;
	
	/**
	 * 根据id获取订单
	 * @param id
	 * @return
	 */
	@Override
	public QyjOrderBean getOrderById(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		
		QyjOrderEntity orderEntity = orderMapper.getOrderById(id);
		if (orderEntity == null) {
			return null;
		}
		
		QyjOrderBean orderBean = new QyjOrderBean();
		BeanUtils.copyProperties(orderEntity, orderBean);
		
		return orderBean;
	}

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
	public int updateOrder(QyjOrderBean orderBean) throws Exception {
		if (orderBean == null) {
			throw new Exception("更新订单为空");
		}

		QyjOrderEntity orderEntity = new QyjOrderEntity();
		BeanUtils.copyProperties(orderBean, orderEntity);

		// 更新订单
		return orderMapper.updateOrder(orderEntity);
	}

}

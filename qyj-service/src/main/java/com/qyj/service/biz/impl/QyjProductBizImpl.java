package com.qyj.service.biz.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.qyj.facade.entity.QyjFileInfoEntity;
import com.qyj.facade.entity.QyjOrderEntity;
import com.qyj.facade.entity.QyjOrderGoodsEntity;
import com.qyj.facade.entity.QyjProductDetailEntity;
import com.qyj.facade.entity.QyjProductEntity;
import com.qyj.facade.vo.QyjFileInfoBean;
import com.qyj.facade.vo.QyjOrderBean;
import com.qyj.facade.vo.QyjOrderGoodsBean;
import com.qyj.facade.vo.QyjProductBean;
import com.qyj.facade.vo.QyjProductDetailBean;
import com.qyj.service.biz.QyjProductBiz;
import com.qyj.service.dao.QyjFileInfoMapper;
import com.qyj.service.dao.QyjOrderGoodsMapper;
import com.qyj.service.dao.QyjOrderMapper;
import com.qyj.service.dao.QyjProductDetailMapper;
import com.qyj.service.dao.QyjProductMapper;

/**
 * 产品表服务层接口
 * 
 * @author CTF_stone
 */
@Service
public class QyjProductBizImpl implements QyjProductBiz {

	@Autowired
	private QyjProductMapper productMapper;
	
	@Autowired
	private QyjFileInfoMapper fileInfoMapper;
	
	@Autowired
	private QyjProductDetailMapper productDetailMapper;
	
	@Autowired
	private QyjOrderMapper orderMapper;
	
	@Autowired
	private QyjOrderGoodsMapper orderGoodsMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(QyjProductBizImpl.class);

	/**
	 * 根据主键删除产品信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByPrimaryKey(Long id) throws Exception {
		return productMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 插入产品信息
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public int insert(QyjProductEntity record) throws Exception {
		return productMapper.insert(record);
	}

	/**
	 * 选择性的插入产品信息
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public int insertSelective(QyjProductEntity record) throws Exception {
		return productMapper.insertSelective(record);
	}

	/**
	 * 根据产品id获取产品信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public QyjProductEntity selectByPrimaryKey(Long id) throws Exception {
		return productMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据产品id选择性的更新产品信息
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKeySelective(QyjProductEntity record) throws Exception {
		return productMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据产品id更新产品信息
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKey(QyjProductEntity record) throws Exception {
		return productMapper.updateByPrimaryKey(record);
	}

	/**
	 * 获取产品分页数据
	 * 
	 * @param pageParam
	 *            分页信息
	 * @param paramMap
	 *            查询参数
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean listProjectPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception {
		if (pageParam == null) {
			pageParam = new PageParam();
		}
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		// 统计产品数量
		int totalCount = productMapper.countProduct(paramMap);

		if (totalCount <= 0) {
			return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), 0, null);
		}

		pageParam.setTotalCount(totalCount);
		// 计算分页信息
		pageParam.splitPageInstance();

		paramMap.put("pageParam", pageParam);

		// 获取分页数据列表
		List<QyjProductEntity> projectList = productMapper.listProduct(paramMap);
		if (projectList != null && !projectList.isEmpty()) {
			logger.info("listProjectPage projectList count:{}, frist ont:{}", projectList.size(), projectList.get(0).toString());
		}

		return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), totalCount, projectList);
	}

	/**
	 * 根据产品id获取产品信息，包括图片、产品详情
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@Override
	public QyjProductBean getProductInfoById(Long productId) throws Exception {
		// 查询产品信息
		QyjProductEntity productEntity = productMapper.selectByPrimaryKey(productId);
		if (productEntity == null) {
			throw new Exception("不存在产品，产品id：" + productId);
		}

		QyjProductBean productBean = new QyjProductBean();
		BeanUtils.copyProperties(productEntity, productBean);

		// 查询文件列表
		List<QyjFileInfoEntity> fileInfoEntityList = fileInfoMapper.listFileInfoByItemId(productId);
		if (fileInfoEntityList != null && !fileInfoEntityList.isEmpty()) {
			List<QyjFileInfoBean> fileInfoBeanList = new ArrayList<QyjFileInfoBean>();
			QyjFileInfoBean fileInfoBean = null;
			for (QyjFileInfoEntity fileInfoEntity : fileInfoEntityList) {
				fileInfoBean = new QyjFileInfoBean();
				BeanUtils.copyProperties(fileInfoEntity, fileInfoBean);
				fileInfoBeanList.add(fileInfoBean);
			}
			productBean.setFileInfoList(fileInfoBeanList);
		}

		// 查询产品详情
		List<QyjProductDetailEntity> productDetailEntityList = productDetailMapper
				.listProductDetailWithBlobByProductId(productId);
		if (productDetailEntityList != null && !productDetailEntityList.isEmpty()) {
			List<QyjProductDetailBean> productDetailBeanList = new ArrayList<QyjProductDetailBean>();
			QyjProductDetailBean productDetailBean = null;
			for (QyjProductDetailEntity productDetailEntity : productDetailEntityList) {
				productDetailBean = new QyjProductDetailBean();
				BeanUtils.copyProperties(productDetailEntity, productDetailBean);
				productDetailBeanList.add(productDetailBean);
			}
			productBean.setProductDetailList(productDetailBeanList);
		}

		return productBean;
	}
	
	/**
	 * 保存订单，返回主键id
	 * @param orderBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long saveOrder(QyjOrderBean orderBean) throws Exception {
		if (orderBean == null) {
			throw new Exception("订单信息为空");
		}
		
		List<QyjOrderGoodsBean> orderGoodsBeanList = orderBean.getOrderGoodsList();
		if (orderGoodsBeanList == null || orderGoodsBeanList.isEmpty()) {
			throw new Exception("商品信息为空");
		}
		
		// 订单下商品总价
		BigDecimal totalPrice = new BigDecimal(0);
		QyjProductEntity productEntity = null;
		for (QyjOrderGoodsBean orderGoodsBean : orderGoodsBeanList) {
			// 查询对应的产品信息
			productEntity = productMapper.selectByPrimaryKey(orderGoodsBean.getProductId());
			if (productEntity == null) {
				throw new Exception("商品[" + orderGoodsBean.getProductId() + "]不存在或已经过期");
			}
			if (!ProductStatusEnum.PUTAWAY.toString().equals(productEntity.getProductStatus())) {
				throw new Exception("商品[" + productEntity.getTitle() + "]已经下架");
			}
			if (orderGoodsBean.getNumber() == null || orderGoodsBean.getNumber() == 0) {
				throw new Exception("商品[" + productEntity.getTitle() + "]没有填写购买数量");
			}
			// 获取商品价格
			orderGoodsBean.setPrice(productEntity.getPrice());
			// 产品标题
			orderGoodsBean.setProductTitle(productEntity.getTitle());
			if (productEntity.getPrice() != null) {
				// 计算总价
				totalPrice = totalPrice.add(productEntity.getPrice()).multiply(new BigDecimal(orderGoodsBean.getNumber()));
			}
		}
		
		Date nowDate = new Date();
		
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
		
		orderBean.setId(orderEntity.getId());
		
		QyjOrderGoodsEntity orderGoodsEntity = null;
		List<QyjOrderGoodsEntity> orderGoodsEntityList = new ArrayList<QyjOrderGoodsEntity>();
		// 设置商品信息
		for (QyjOrderGoodsBean orderGoodsBean : orderGoodsBeanList) {
			orderGoodsEntity = new QyjOrderGoodsEntity();
			BeanUtils.copyProperties(orderGoodsBean, orderGoodsEntity);
			orderGoodsEntity.setCreateTime(nowDate);
			orderGoodsEntity.setOrderId(orderEntity.getId());
			orderGoodsEntity.setUserId(orderEntity.getUserId());
			orderGoodsEntityList.add(orderGoodsEntity);
		}
		if (orderGoodsMapper.insertOrderGoodsList(orderGoodsEntityList) <= 0) {
			throw new Exception("保存订单商品失败");
		}
		
		return orderBean.getId();
	}
}

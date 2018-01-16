package com.qyj.facade;

import java.util.Map;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.facade.vo.QyjOrderBean;
import com.qyj.facade.vo.QyjProductBean;

/**
 * 产品Dubbo服务接口
 * @author CTF_stone
 */
public interface QyjProductFacade {

	/**
	 * 获取产品分页数据
	 * @param pageParam
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public PageBean listProductPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 根据产品id查询产品信息
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public QyjProductBean getProductInfoById(Long productId) throws Exception;
	
	/**
	 * 保存订单，返回主键id
	 * @param orderBean
	 * @return
	 * @throws Exception
	 */
	public Long saveOrder(QyjOrderBean orderBean) throws Exception;
}

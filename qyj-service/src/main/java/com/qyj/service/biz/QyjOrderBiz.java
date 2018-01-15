package com.qyj.service.biz;

import java.util.List;
import java.util.Map;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.facade.vo.QyjOrderBean;

/**
 * 服务层接口-我的订单
 * @author CTF_stone
 */
public interface QyjOrderBiz {

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
	public Boolean updateOrder(QyjOrderBean orderBean) throws Exception;
}

package com.qyj.facade;

import java.util.Map;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

/**
 * Dubbo服务接口-订单
 * @author CTF_stone
 */
public interface QyjOrderFacade {

	/**
	 * 获取订单分页数据
	 * @param pageParam
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	PageBean listOrderPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception;
}

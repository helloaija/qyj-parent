package com.qyj.facade;

import java.util.Map;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

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
}

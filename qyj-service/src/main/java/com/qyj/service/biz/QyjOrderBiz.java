package com.qyj.service.biz;

import java.util.Map;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

/**
 * 服务层接口-我的订单
 * @author CTF_stone
 */
public interface QyjOrderBiz {

	/**
	 * 获取订单分页数据
	 * @param pageParam 分页信息
	 * @param paramMap 查询参数
	 * @return
	 * @throws Exception
	 */
	PageBean listOrderPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception;
}

package com.qyj.service.facade.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.facade.QyjProductFacade;
import com.qyj.service.biz.QyjProductBiz;

/**
 * 产品Dubbo服务接口实现
 * @author CTF_stone
 */
@Service("qyjProductFacade")
public class QyjProductFacadeImpl implements QyjProductFacade {

	@Autowired
	private QyjProductBiz productBiz;
	
	private static final Logger logger = LoggerFactory.getLogger(QyjProductFacadeImpl.class);

	/**
	 * 获取产品分页数据
	 * @param pageParam
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public PageBean listProductPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception {
		logger.info("listProductPage params,pageParam={},paramMap={}", pageParam, paramMap);
		return productBiz.listProjectPage(pageParam, paramMap);
	}

}

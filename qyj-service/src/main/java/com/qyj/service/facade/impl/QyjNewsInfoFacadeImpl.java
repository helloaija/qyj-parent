package com.qyj.service.facade.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.facade.QyjNewsInfoFacade;
import com.qyj.facade.entity.QyjNewsInfoEntity;
import com.qyj.service.biz.QyjNewsInfoBiz;

/**
 * 新闻公告Dubbo服务接口实现
 * @author CTF_stone
 */
@Service("qyjNewsInfoFacade")
public class QyjNewsInfoFacadeImpl implements QyjNewsInfoFacade {

	@Autowired
	private QyjNewsInfoBiz newsInfoBiz;
	
	private static final Logger logger = LoggerFactory.getLogger(QyjNewsInfoFacadeImpl.class);

	/**
	 * 获取新闻公告分页数据
	 * @param pageParam
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public PageBean listNewsInfoPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception {
		logger.info("listProductPage params,pageParam={},paramMap={}", pageParam, paramMap);
		return newsInfoBiz.listNewsInfoPage(pageParam, paramMap);
	}
	
	/**
	 * 根据id获取新闻公告内容
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public QyjNewsInfoEntity getNewsInfoById(Long id) throws Exception {
		return newsInfoBiz.selectByPrimaryKey(id);
	}

	/**
	 * 更新新闻公告浏览次数+1
	 * @param id
	 * @return
	 */
	@Override
	public int updateVisitCountOnce(Long id) throws Exception {
		return newsInfoBiz.updateVisitCountOnce(id);
	}
}

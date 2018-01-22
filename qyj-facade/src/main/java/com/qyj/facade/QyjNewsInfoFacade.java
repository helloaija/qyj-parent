package com.qyj.facade;

import java.util.Map;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.facade.entity.QyjNewsInfoEntity;

/**
 * 新闻公告Dubbo服务接口
 * @author CTF_stone
 */
public interface QyjNewsInfoFacade {

	/**
	 * 获取新闻公告分页数据
	 * @param pageParam
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	PageBean listNewsInfoPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 根据id获取新闻公告内容
	 * @param id
	 * @return
	 * @throws Exception
	 */
	QyjNewsInfoEntity getNewsInfoById(Long id) throws Exception;
	
	/**
	 * 更新新闻公告浏览次数+1
	 * @param id
	 * @return
	 */
	int updateVisitCountOnce(Long id) throws Exception;
}

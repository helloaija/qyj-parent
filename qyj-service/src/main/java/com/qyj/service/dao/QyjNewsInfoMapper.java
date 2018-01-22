package com.qyj.service.dao;

import java.util.List;
import java.util.Map;

import com.qyj.facade.entity.QyjNewsInfoEntity;

/**
 * 新闻资讯mapper
 * @author CTF_stone
 */
public interface QyjNewsInfoMapper {

	QyjNewsInfoEntity selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(QyjNewsInfoEntity record);

	int updateByPrimaryKey(QyjNewsInfoEntity record);

	/**
	 * 根据条件统计产品数量
	 * @param paramMap
	 * @return
	 */
	Integer countNewsInfo(Map<String, Object> paramMap);

	/**
	 * 根据条件获取产品列表数据
	 * @param paramMap
	 * @return
	 */
	List<QyjNewsInfoEntity> listNewsInfo(Map<String, Object> paramMap);
	
	/**
	 * 更新新闻公告浏览次数+1
	 * @param id
	 * @return
	 */
	int updateVisitCountOnce(Long id);
}
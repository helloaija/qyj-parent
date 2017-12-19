package com.qyj.back.dao;

import java.util.List;
import java.util.Map;

import com.qyj.back.entity.QyjNewsInfoEntity;

/**
 * 新闻资讯mapper
 * @author CTF_stone
 *
 */
public interface QyjNewsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QyjNewsInfoEntity record);

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
}
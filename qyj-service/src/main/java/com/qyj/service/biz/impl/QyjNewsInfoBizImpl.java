package com.qyj.service.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.facade.entity.QyjNewsInfoEntity;
import com.qyj.service.biz.QyjNewsInfoBiz;
import com.qyj.service.dao.QyjNewsInfoMapper;

/**
 * 新闻公告表服务层接口
 * @author CTF_stone
 */
@Service
public class QyjNewsInfoBizImpl implements QyjNewsInfoBiz {

	private static final Logger logger = LoggerFactory.getLogger(QyjNewsInfoBizImpl.class);

	@Autowired
	private QyjNewsInfoMapper newsInfoMapper;

	/**
	 * 根据新闻公告id获取新闻公告信息
	 * @param id
	 * @return
	 */
	@Override
	public QyjNewsInfoEntity selectByPrimaryKey(Long id) throws Exception {
		return newsInfoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据新闻公告id选择性的更新新闻公告信息
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKeySelective(QyjNewsInfoEntity record) throws Exception {
		return newsInfoMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据新闻公告id更新新闻公告信息
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKey(QyjNewsInfoEntity record) throws Exception {
		return newsInfoMapper.updateByPrimaryKey(record);
	}

	/**
	 * 根据条件统计新闻公告数量
	 * @param paramMap
	 * @return
	 */
	@Override
	public Integer countNewsInfo(Map<String, Object> paramMap) {
		return newsInfoMapper.countNewsInfo(paramMap);
	}

	/**
	 * 根据条件获取新闻公告列表数据
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<QyjNewsInfoEntity> listNewsInfo(Map<String, Object> paramMap) {
		return newsInfoMapper.listNewsInfo(paramMap);
	}

	/**
	 * 获取新闻公告分页数据
	 * @param pageParam 分页信息
	 * @param paramMap 查询参数
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean listNewsInfoPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception {
		if (pageParam == null) {
			pageParam = new PageParam();
		}
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		// 统计新闻公告数量
		int totalCount = newsInfoMapper.countNewsInfo(paramMap);
		logger.info("listNewsInfoPage paramMap:{}, totalCount:{}", paramMap, totalCount);

		if (totalCount <= 0) {
			return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), 0, null);
		}

		pageParam.setTotalCount(totalCount);
		// 计算分页信息
		pageParam.splitPageInstance();

		paramMap.put("pageParam", pageParam);

		// 获取分页数据列表
		List<QyjNewsInfoEntity> projectList = newsInfoMapper.listNewsInfo(paramMap);

		return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), totalCount, projectList);
	}

	/**
	 * 更新新闻公告浏览次数+1
	 * @param id
	 * @return
	 */
	@Override
	public int updateVisitCountOnce(Long id) throws Exception {
		return newsInfoMapper.updateVisitCountOnce(id);
	}
}

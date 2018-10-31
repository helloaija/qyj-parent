package com.qyj.store.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.store.common.enums.CommonEnums.NewsStatusEnum;
import com.qyj.store.dao.QyjNewsInfoMapper;
import com.qyj.store.entity.QyjNewsInfoEntity;
import com.qyj.store.service.QyjNewsInfoService;
import com.qyj.store.vo.SysUserBean;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

/**
 * 新闻公告表服务层接口
 * @author CTF_stone
 */
@Service
public class QyjNewsInfoServiceImpl implements QyjNewsInfoService {

	private static final Logger logger = LoggerFactory.getLogger(QyjNewsInfoServiceImpl.class);

	@Autowired
	private QyjNewsInfoMapper newsInfoMapper;
	
	/**
	 * 根据主键删除新闻公告信息
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByPrimaryKey(Long id) throws Exception {
		return newsInfoMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 插入新闻公告信息
	 * @param record
	 * @return
	 */
	@Override
	public int insert(QyjNewsInfoEntity record) throws Exception {
		return newsInfoMapper.insert(record);
	}

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
	 * 更新新闻状态
	 * @param userBean
	 * @param newsId
	 * @param newsStatus
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean updateNewsStatus(SysUserBean userBean, Long newsId, String newsStatus) throws Exception {
		if (newsId == null) {
			throw new Exception("新闻id不能为空！");
		}
		if (StringUtils.isEmpty(newsStatus)) {
			throw new Exception("更新的新闻状态不能为空！");
		}
		
		// 查询产品
		QyjNewsInfoEntity newsInfo = newsInfoMapper.selectByPrimaryKey(newsId);
		if (newsInfo == null) {
			throw new Exception("新闻" + newsId + "不存在！");
		}
		
		// 上架
		if (NewsStatusEnum.PUTAWAY.toString().equals(newsStatus)) {
			if (NewsStatusEnum.PUBLISH.toString().equals(newsInfo.getNewsStatus())) {
				Date curDate = new Date();
				QyjNewsInfoEntity updateNewsInfo = new QyjNewsInfoEntity();
				updateNewsInfo.setId(newsId);
				updateNewsInfo.setUpdateTime(curDate);
				updateNewsInfo.setUpdateUser(userBean.getId());
				updateNewsInfo.setNewsStatus(newsStatus);
				int updateResult = newsInfoMapper.updateByPrimaryKeySelective(updateNewsInfo);
				
				if (updateResult == 1) {
					return Boolean.TRUE;
				}
				return Boolean.FALSE;
			}
			if (NewsStatusEnum.PUTAWAY.toString().equals(newsInfo.getNewsStatus())) {
				throw new Exception("新闻" + newsId + "已经上架！");
			}
			if (NewsStatusEnum.SOLDOUT.toString().equals(newsInfo.getNewsStatus())) {
				throw new Exception("新闻" + newsId + "已经下架！");
			}
			throw new Exception("新闻" + newsId + "更新上架状态失败！");
		}
		
		// 下架
		if (NewsStatusEnum.SOLDOUT.toString().equals(newsStatus)) {
			if (NewsStatusEnum.PUBLISH.toString().equals(newsInfo.getNewsStatus())) {
				throw new Exception("新闻" + newsId + "还没有上架！");
			}
			if (NewsStatusEnum.PUTAWAY.toString().equals(newsInfo.getNewsStatus())) {
				Date curDate = new Date();
				QyjNewsInfoEntity updateNewsInfo = new QyjNewsInfoEntity();
				updateNewsInfo.setId(newsId);
				updateNewsInfo.setUpdateTime(curDate);
				updateNewsInfo.setUpdateUser(userBean.getId());
				updateNewsInfo.setNewsStatus(newsStatus);
				int updateResult = newsInfoMapper.updateByPrimaryKeySelective(updateNewsInfo);
				
				if (updateResult == 1) {
					return Boolean.TRUE;
				}
				return Boolean.FALSE;
			}
			if (NewsStatusEnum.SOLDOUT.toString().equals(newsInfo.getNewsStatus())) {
				throw new Exception("新闻" + newsId + "已经下架！");
			}
			throw new Exception("新闻" + newsId + "更新下架状态失败！");
		}
		
		throw new Exception("新闻" + newsId + "更新状态" + newsStatus + "失败！");
	}
}

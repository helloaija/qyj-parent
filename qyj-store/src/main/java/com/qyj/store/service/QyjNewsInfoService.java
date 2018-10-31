package com.qyj.store.service;

import java.util.List;
import java.util.Map;

import com.qyj.store.entity.QyjNewsInfoEntity;
import com.qyj.store.vo.SysUserBean;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

/**
 * 新闻通知公告表服务层接口
 * @author CTF_stone
 */
public interface QyjNewsInfoService {
	
	/**
	 * 根据主键删除新闻公告信息
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id) throws Exception ;

	/**
	 * 插入新闻公告信息
	 * @param record
	 * @return
	 */
	int insert(QyjNewsInfoEntity record) throws Exception;

	/**
	 * 根据新闻公告id获取新闻公告信息
	 * @param id
	 * @return
	 */
	QyjNewsInfoEntity selectByPrimaryKey(Long id) throws Exception;
	
	/**
	 * 根据新闻公告id选择性的更新新闻公告信息
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(QyjNewsInfoEntity record) throws Exception;

	/**
	 * 根据新闻公告id更新新闻公告信息
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(QyjNewsInfoEntity record) throws Exception;
	

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
	 * 获取产品分页数据
	 * @param pageParam 分页信息
	 * @param paramMap 查询参数
	 * @return
	 * @throws Exception
	 */
	PageBean listNewsInfoPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception;

	/**
	 * 更新新闻状态
	 * @param userBean
	 * @param newsId
	 * @param newsStatus
	 * @return
	 * @throws Exception
	 */
	public Boolean updateNewsStatus(SysUserBean userBean, Long newsId, String newsStatus) throws Exception;
}

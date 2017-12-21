package com.qyj.service.biz;

import java.util.Map;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.facade.entity.QyjProductEntity;
import com.qyj.facade.vo.QyjProductBean;

/**
 * 产品表服务层接口
 * 
 * @author CTF_stone
 *
 */
public interface QyjProductBiz {
	/**
	 * 根据主键删除产品信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id) throws Exception;

	/**
	 * 插入产品信息
	 * 
	 * @param record
	 * @return
	 */
	int insert(QyjProductEntity record) throws Exception;

	/**
	 * 选择性的插入产品信息
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(QyjProductEntity record) throws Exception;

	/**
	 * 根据产品id获取产品信息
	 * 
	 * @param id
	 * @return
	 */
	QyjProductEntity selectByPrimaryKey(Long id) throws Exception;

	/**
	 * 根据产品id选择性的更新产品信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(QyjProductEntity record) throws Exception;

	/**
	 * 根据产品id更新产品信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(QyjProductEntity record) throws Exception;
	
	/**
	 * 获取产品分页数据
	 * @param pageParam 分页信息
	 * @param paramMap 查询参数
	 * @return
	 * @throws Exception
	 */
	PageBean listProjectPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 根据产品id获取产品信息，包括图片、产品详情
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	QyjProductBean getProductInfoById(Long productId) throws Exception;
}

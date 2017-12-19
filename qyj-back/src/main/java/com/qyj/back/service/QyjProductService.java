package com.qyj.back.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qyj.back.entity.QyjProductEntity;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

/**
 * 产品表服务层接口
 * 
 * @author CTF_stone
 *
 */
public interface QyjProductService {
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
	 * 保存产品信息包括图片信息
	 * @param record
	 * @return
	 */
	void saveAllProductInfo(QyjProductEntity record, @RequestParam("file")MultipartFile file, MultipartHttpServletRequest files) throws Exception;
}

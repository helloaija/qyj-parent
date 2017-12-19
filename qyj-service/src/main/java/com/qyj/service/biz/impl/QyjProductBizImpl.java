package com.qyj.service.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.facade.entity.QyjProductEntity;
import com.qyj.service.biz.QyjProductBiz;
import com.qyj.service.dao.QyjProductMapper;

/**
 * 产品表服务层接口
 * @author CTF_stone
 */
@Service
public class QyjProductBizImpl implements QyjProductBiz {

	@Autowired
	private QyjProductMapper productMapper;

	/**
	 * 根据主键删除产品信息
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByPrimaryKey(Long id) throws Exception {
		return productMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 插入产品信息
	 * @param record
	 * @return
	 */
	@Override
	public int insert(QyjProductEntity record) throws Exception {
		return productMapper.insert(record);
	}

	/**
	 * 选择性的插入产品信息
	 * @param record
	 * @return
	 */
	@Override
	public int insertSelective(QyjProductEntity record) throws Exception {
		return productMapper.insertSelective(record);
	}

	/**
	 * 根据产品id获取产品信息
	 * @param id
	 * @return
	 */
	@Override
	public QyjProductEntity selectByPrimaryKey(Long id) throws Exception {
		return productMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据产品id选择性的更新产品信息
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKeySelective(QyjProductEntity record) throws Exception {
		return productMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据产品id更新产品信息
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKey(QyjProductEntity record) throws Exception {
		return productMapper.updateByPrimaryKey(record);
	}

	/**
	 * 获取产品分页数据
	 * @param pageParam
	 *            分页信息
	 * @param paramMap
	 *            查询参数
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean listProjectPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception {
		if (pageParam == null) {
			pageParam = new PageParam();
		}
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		// 统计产品数量
		int totalCount = productMapper.countProduct(paramMap);

		if (totalCount <= 0) {
			return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), 0, null);
		}

		pageParam.setTotalCount(totalCount);
		// 计算分页信息
		pageParam.splitPageInstance();

		paramMap.put("pageParam", pageParam);

		// 获取分页数据列表
		List<QyjProductEntity> projectList = productMapper.listProduct(paramMap);

		return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), totalCount, projectList);
	}
}

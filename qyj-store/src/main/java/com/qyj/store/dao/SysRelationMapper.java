package com.qyj.store.dao;

import java.util.List;

import com.qyj.store.entity.SysRelationEntity;

/**
 * mapper - 关系表
 * @author CTF_stone
 *
 */
public interface SysRelationMapper {
	
	/**
	 * 根据查询条件查询关系数据列表
	 * @param relationEntity
	 * @return
	 */
	List<SysRelationEntity> listRelationByModel(SysRelationEntity relationEntity);
	
	/**
	 * 批量插入关系表
	 * @param relationEntityList
	 * @return
	 */
	int insertRelationBatch(List<SysRelationEntity> relationEntityList);
	
	/**
	 * 根据条件删除关系
	 * @param relationEntity
	 * @return
	 */
    int deleteRelationByModel(SysRelationEntity relationEntity);

}
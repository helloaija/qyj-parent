package com.qyj.service.dao;

import com.qyj.facade.entity.QyjProductDetailEntity;

public interface QyjProductDetailEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QyjProductDetailEntity record);

    int insertSelective(QyjProductDetailEntity record);

    QyjProductDetailEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QyjProductDetailEntity record);

    int updateByPrimaryKeyWithBLOBs(QyjProductDetailEntity record);

    int updateByPrimaryKey(QyjProductDetailEntity record);
}
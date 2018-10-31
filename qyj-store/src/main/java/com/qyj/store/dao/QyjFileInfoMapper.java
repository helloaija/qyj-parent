package com.qyj.store.dao;

import java.util.List;

import com.qyj.store.entity.QyjFileInfoEntity;

/**
 * 文件信息mapper
 * @author shitl
 *
 */
public interface QyjFileInfoMapper {
	/**
	 * 插入文件信息
	 * @param record
	 * @return
	 */
    int insert(QyjFileInfoEntity record);
    
    /**
     * 根据itemId删除文件
     * @param itemId
     * @return
     */
    int delFileByItemId(Long itemId);
    
    /**
     * 根据itemId获取文件列表
     * @param itemId
     * @return
     */
    List<QyjFileInfoEntity> listFileInfoByItemId(Long itemId);
}
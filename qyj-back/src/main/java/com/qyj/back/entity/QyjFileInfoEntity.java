package com.qyj.back.entity;

import java.util.Date;

/**
 * 文件信息实体类
 * @author shitl
 *
 */
public class QyjFileInfoEntity {
	/** 主键id */
	private Long id;

	/** 业务类型 */
	private String busType;

	/** 关联的业务id */
	private Long itemId;

	/** 应用地方 */
	private String field;

	/** 文件名称 */
	private String fileName;

	/** 文件类型 */
	private String fileType;

	/** 文件路径 */
	private String filePath;

	/** 创建时间 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "QyjFileInfoEntity [id=" + id + ", busType=" + busType + ", itemId=" + itemId + ", field=" + field
				+ ", fileName=" + fileName + ", fileType=" + fileType + ", filePath=" + filePath + ", createTime="
				+ createTime + "]";
	}
}
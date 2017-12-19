package com.qyj.back.entity;

/**
 * 系统菜单model
 * @author shitongle
 */
public class SysMenuModel extends BaseModel {

	/** 父菜单id */
	private Long parentId;
	/** 菜单名 */
	private String name;
	/** 类型 */
	private String menuType;
	/** 序号 */
	private Integer sortNumber;
	/** 菜单路径 */
	private String url;
	/** 是否启用 */
	private String status;
	/** 备注 */
	private String remark;
	/** 是否选中 */
	private Boolean checked;

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the menuType
	 */
	public String getMenuType() {
		return menuType;
	}

	/**
	 * @param menuType the menuType to set
	 */
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	/**
	 * @return the sortNumber
	 */
	public Integer getSortNumber() {
		return sortNumber;
	}

	/**
	 * @param sortNumber the sortNumber to set
	 */
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}

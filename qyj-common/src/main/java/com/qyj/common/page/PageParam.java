package com.qyj.common.page;

import java.io.Serializable;

/**
 * 分页参数传递工具类
 * @author CTF_stone
 */
public class PageParam implements Serializable {

	private static final long serialVersionUID = 2214184601418066416L;

	/** 当前页 */
	private Integer currentPage = 1;
	/** 每页显示多少条 */
	private Integer pageSize = 10;
	/** 总记录数 */
	private Integer totalCount = 0;
	/** 总页数 */
	private Integer pageCount = 0;
	/** 当前页起始记录 */
	private Integer pageOffset = 0;
	/** 当前页到达的记录 */
	private Integer pageTail = 0;
	/** 自定义条件 */
	private String queryCondition = "";
	/** 排序信息 */
	private String orderByCondition = null;

	public void splitPageInstance() {
		if (pageSize < 1 || null == pageSize) {
			pageSize = 10;
		}
		// 总页数=(总记录数+每页行数-1)/每页行数
		pageCount = (totalCount + pageSize - 1) / pageSize;
		// 当前页大于总页数
		if (currentPage > pageCount) {
			currentPage = pageCount;
		}
		if (0 >= pageOffset.intValue()) {
			// 防止 pageOffset 小于 0
			pageOffset = ((currentPage - 1) * pageSize);
			if (pageOffset < 0)
				pageOffset = 0;
		}
	}

	public String getLimit() {
		if (pageSize > 0) {
			return " limit " + pageOffset + "," + pageSize;
		} else {
			return "";
		}
	}

	public String getOrderByCondition() {
		return orderByCondition == null ? "" : " order by " + orderByCondition;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageOffset() {
		return pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	public Integer getPageTail() {
		return pageTail;
	}

	public void setPageTail(Integer pageTail) {
		this.pageTail = pageTail;
	}

	public String getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}

	public void setOrderByCondition(String orderByCondition) {
		this.orderByCondition = orderByCondition;
	}
	
}

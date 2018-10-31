package com.qyj.store.common.util;

import javax.servlet.http.HttpServletRequest;

import com.qyj.common.page.PageParam;

public class PageUtil {
    
    /**
     * 获取分页信息
     * @param request
     * @return
     */
    public static PageParam getPageParam(HttpServletRequest request) {
        // 当前页
        String pageIndex = request.getParameter("currentPage");
        // 页大小
        String pageSize = request.getParameter("pageSize");
        
        if (pageIndex == null || "".equals(pageIndex)) {
            pageIndex = "0";
        }
        
        if (pageSize == null || "".equals(pageSize)) {
            pageSize = "10";
        }
        
        PageParam pageParams = new PageParam();
        pageParams.setCurrentPage(Integer.parseInt(pageIndex));
        pageParams.setPageSize(Integer.parseInt(pageSize));
        return pageParams;
    }
    
    /**
     * 设置分页信息总记录数
     * @param pageParams
     * @param rowCount
     */
    public static void setRowCount(PageParam pageParam, int rowCount) {
        // 设置总记录数
    	pageParam.setTotalCount(rowCount);
        
        // 加载分页信息
        loadPageParamsData(pageParam);
    }
    
    /**
     * 加载分页信息
     * @param pageParam
     */
    public static void loadPageParamsData(PageParam pageParam) {
    	Integer pageSize = pageParam.getPageSize();
    	Integer totalCount = pageParam.getTotalCount();
    	Integer currentPage = pageParam.getCurrentPage();
    	Integer pageOffset = 0;
    	if (pageSize < 1 || null == pageSize) {
			pageSize = 10;
		}
		// 总页数=(总记录数+每页行数-1)/每页行数
		Integer pageCount = (totalCount + pageSize - 1) / pageSize;
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
		
		pageParam.setPageCount(pageCount);
		pageParam.setPageOffset(pageOffset);
    }
}

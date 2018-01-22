package com.qyj.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.common.page.ResultBean;
import com.qyj.common.utils.CommonEnums.NewsStatusEnum;
import com.qyj.facade.QyjNewsInfoFacade;
import com.qyj.facade.entity.QyjNewsInfoEntity;

@Controller
@RequestMapping("/wechat")
public class QyjNewsInfoController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(QyjNewsInfoController.class);

	@Autowired
	private QyjNewsInfoFacade newsInfoFacade;

	/**
	 * 获取新闻公告分页数据信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/freedom/news/listNewsInfoPage")
	public ResultBean listNewsInfoPage(HttpServletRequest request, HttpServletResponse response) {
		PageParam pageParam = this.initPageParam(request);
		// 新闻公告标题
		String title = request.getParameter("title");
		// 新闻公告类型
		String type = request.getParameter("type");
		// 创建开始时间
		String createTimeBegin = request.getParameter("createTimeBegin");
		// 创建结束时间
		String createTimeEnd = request.getParameter("createTimeEnd");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title", title);
		paramMap.put("type", type);
		paramMap.put("createTimeBegin", createTimeBegin);
		paramMap.put("createTimeEnd", createTimeEnd);
		paramMap.put("newsStatus", NewsStatusEnum.PUTAWAY.toString());
		try {
			pageParam.setOrderByCondition("create_time desc");
			PageBean pageBean = newsInfoFacade.listNewsInfoPage(pageParam, paramMap);
			return new ResultBean("0000", "请求成功", pageBean);
		} catch (Exception e) {
			logger.error("listNewsInfoPage error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 根据主键查询新闻公告信息
	 * @param newsInfoId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/freedom/news/getNewsInfo")
	public ResultBean getNewsInfo(Long newsInfoId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjNewsInfoEntity newsInfo = newsInfoFacade.getNewsInfoById(newsInfoId);
			if (newsInfo == null) {
				return new ResultBean("0002", "该新闻公告已经失踪！", null);
			}
			if (StringUtils.isEmpty(newsInfo.getContent())) {
				return new ResultBean("0002", "该新闻公告内容空空！", null);
			}
			return new ResultBean("0000", "请求成功", newsInfo);
		} catch (Exception e) {
			logger.error("getNewsInfoInfo error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 浏览新闻公告，浏览的时候浏览次数+1
	 * @param newsInfoId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/freedom/news/scanNews")
	public ResultBean scanNews(Long newsInfoId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjNewsInfoEntity newsInfo = newsInfoFacade.getNewsInfoById(newsInfoId);
			if (newsInfo == null) {
				return new ResultBean("0002", "该新闻公告已经失踪！", null);
			}
			if (StringUtils.isEmpty(newsInfo.getContent())) {
				return new ResultBean("0002", "该新闻公告内容空空！", null);
			}
			
			newsInfoFacade.updateVisitCountOnce(newsInfoId);
			return new ResultBean("0000", "请求成功", newsInfo);
		} catch (Exception e) {
			logger.error("getNewsInfoInfo error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

}

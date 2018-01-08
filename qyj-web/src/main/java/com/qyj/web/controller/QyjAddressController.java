package com.qyj.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyj.common.page.ResultBean;
import com.qyj.facade.QyjAddressFacade;
import com.qyj.facade.vo.QyjAddressBean;
import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.utils.SessionUtil;

@Controller
@RequestMapping("/wechat")
public class QyjAddressController {
	private static final Logger logger = LoggerFactory.getLogger(QyjAddressController.class);
	
	@Autowired
	private QyjAddressFacade addressFacade;
	
	/**
	 * 获取用户地址列表
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/address/listAddress")
	public ResultBean listAddress(HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			// 获取地址列表
			List<QyjAddressBean> addressList = addressFacade.listAddressByUserId(userBean.getId());
			
			return new ResultBean("0000", "获取地址列表成功", addressList);
		} catch (Exception e) {
			logger.error("listAddress error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 获取用户地址
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/address/getAddressById")
	public ResultBean getAddressById(Long addressId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			// 获取地址列表
			QyjAddressBean addressBean = addressFacade.getAddressById(addressId);
			if (addressBean != null && userBean.getId().longValue() != addressBean.getUserId().longValue()) {
				return new ResultBean("0002", "在你的信息中不存在地址" + addressId, null);
			}
			
			return new ResultBean("0000", "获取地址成功", addressBean);
		} catch (Exception e) {
			logger.error("getAddressById error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 保存地址
	 * @param addressBean
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/address/saveAddress")
	public ResultBean saveAddress(QyjAddressBean addressBean, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			// 必须是操作已登录账户
			addressBean.setUserId(userBean.getId());
			Date curDate = new Date();
			addressBean.setUpdateTime(curDate);
			
			if (addressBean.getId() != null) {
				if (!addressFacade.updateAddressById(addressBean)) {
					return new ResultBean("0001", "保存地址失败", null);
				}
				
				return new ResultBean("0000", "保存地址成功", null);
			}
			
			addressBean.setCreateTime(curDate);
			if (!addressFacade.insertAddress(addressBean)) {
				return new ResultBean("0001", "新增地址失败", null);
			}
			
			return new ResultBean("0000", "保存地址成功", null);
		} catch (Exception e) {
			return new ResultBean("0001", e.getMessage(), null);
		}
	}
}

package com.qyj.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyj.common.page.ResultBean;
import com.qyj.common.utils.CommonEnums.ProductStatusEnum;
import com.qyj.facade.QyjAddressFacade;
import com.qyj.facade.QyjShoppingTrolleyFacade;
import com.qyj.facade.vo.QyjAddressBean;
import com.qyj.facade.vo.QyjShoppingTrolleyBean;
import com.qyj.facade.vo.QyjShoppingTrolleyListBean;
import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.utils.SessionUtil;

/**
 * 控制器 - 购物车
 * @author CTF_stone
 *
 */
@Controller
@RequestMapping("/wechat")
public class QyjShoppingTrolleyController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(QyjProductController.class);
	
	@Autowired
	private QyjShoppingTrolleyFacade shoppingTrolleyFacade;
	
	@Autowired
	private QyjAddressFacade addressFacade;
	
	/**
	 * 添加购物车记录
	 * @param productId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/shoppingTrolley/addShoppingTrolley")
	public ResultBean addShoppingTrolley(Long productId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			QyjShoppingTrolleyBean bean = new QyjShoppingTrolleyBean();
			bean.setUserId(userBean.getId());
			bean.setProductId(productId);
			bean.setNumber(1);
			bean.setCreateTime(new Date());
			
			if (shoppingTrolleyFacade.addShoppingTrolley(bean) != null) {
				return new ResultBean("0000", "添加购物车成功", null);
			}
			
			return new ResultBean("0002", "添加购物车失败", null);
		} catch (Exception e) {
			logger.error("addShoppingTrolley error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 获取登录用户购物车列表
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/shoppingTrolley/listShoppingTrolley")
	public ResultBean listShoppingTrolley(HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			List<QyjShoppingTrolleyBean> shoppingTrolleyBeanList = shoppingTrolleyFacade.listShoppingTrolleyByUserId(userBean.getId());
			
			return new ResultBean("0000", "获取购物车记录成功", shoppingTrolleyBeanList);
		} catch (Exception e) {
			logger.error("listShoppingTrolley error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 删除购物车记录
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/shoppingTrolley/delShoppingTrolley")
	public ResultBean delShoppingTrolley(Long[] ids, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			if (ids == null || ids.length <= 0) {
				return new ResultBean("0002", "请选择需要删除的记录", null);
			}
			
			shoppingTrolleyFacade.batchDelShoppingTrolley(ids, userBean.getId());
			
			return new ResultBean("0000", "删除成功", null);
		} catch (Exception e) {
			logger.error("delShoppingTrolley error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 批量更新购物车记录
	 * @param beanList
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/shoppingTrolley/updateShoppingTrolleyList")
	public ResultBean updateShoppingTrolleyList(QyjShoppingTrolleyListBean list, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			if (list == null || list.getList() == null || list.getList().isEmpty()) {
				return new ResultBean("0002", "没有需要更新的数据", null);
			}
			
			for (QyjShoppingTrolleyBean bean : list.getList()) {
				bean.setUserId(userBean.getId());
			}
			
			if (shoppingTrolleyFacade.updateShoppingTrolleyList(list.getList()) > 0) {
				return new ResultBean("0000", "更新成功", null);
			}
			
			return new ResultBean("0002", "没有更新任何数据", null);
		} catch (Exception e) {
			logger.error("updateShoppingTrolleyList error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 获取结算购物车数据，返回结算购物车数据和默认送货地址
	 * @param ids 购物车产品id数组
	 * @param addressId 送货地址id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/shoppingTrolley/getTrolleyBalance")
	public ResultBean getTrolleyBalance(Long[] ids, Long addressId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ids", ids);
			paramMap.put("userId", userBean.getId());
			paramMap.put("productStatus", ProductStatusEnum.PUTAWAY.toString());
			// 获取购物车记录
			List<QyjShoppingTrolleyBean> beanList = shoppingTrolleyFacade.listShoppingTrolleyByMap(paramMap);
			
			
			QyjAddressBean addressBean = null;
			// 如果地址id不为空就取该id的地址，否则取默认地址
			if (addressId != null) {
				addressBean = addressFacade.getAddressById(addressId);
			} else {
				List<QyjAddressBean> addressList = addressFacade.listAddressByUserId(userBean.getId());
				if (addressList != null && !addressList.isEmpty()) {
					// 如果没有设置默认地址，就取第一条地址
					addressBean = addressList.get(0);
					for (QyjAddressBean bean : addressList) {
						if (bean.getIsDefault()) {
							addressBean = bean;
							break;
						}
					}
				}
			}
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("beanList", beanList);
			dataMap.put("address", addressBean);
			
			return new ResultBean("0000", "请求成功！", dataMap);
		} catch (Exception e) {
			logger.error("getTrolleyBalance error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
}

package com.qyj.back.controller.bussiness;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qyj.back.common.constant.CommonConstant;
import com.qyj.back.common.enums.CommonEnums.ProductStatusEnum;
import com.qyj.back.common.util.FileUtils;
import com.qyj.back.common.util.SessionUtil;
import com.qyj.back.common.util.Utils;
import com.qyj.back.controller.BaseController;
import com.qyj.back.entity.QyjProductEntity;
import com.qyj.back.service.QyjProductService;
import com.qyj.back.vo.SysUserBean;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.common.page.ResultBean;

@Controller
@RequestMapping("/admin/product")
public class QyjProductController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(QyjProductController.class);

	@Autowired
	private QyjProductService productService;

	/**
	 * 获取产品分页数据信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listProductPage")
	public ResultBean listProductPage(HttpServletRequest request, HttpServletResponse response) {
		PageParam pageParam = this.initPageParam(request);
		// 产品标题
		String productTitle = request.getParameter("title");
		// 产品类型
		String productType = request.getParameter("productType");
		// 创建开始时间
		String createTimeBegin = request.getParameter("createTimeBegin");
		// 创建结束时间
		String createTimeEnd = request.getParameter("createTimeEnd");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title", productTitle);
		paramMap.put("productType", productType);
		paramMap.put("createTimeBegin", createTimeBegin);
		paramMap.put("createTimeEnd", createTimeEnd);
		System.out.println(System.getProperty("catalina.home"));
		try {
			pageParam.setOrderByCondition("create_time desc");
			PageBean pageBean = productService.listProjectPage(pageParam, paramMap);
			return new ResultBean("0000", "请求成功", pageBean);
		} catch (Exception e) {
			logger.error("listProductPage error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 根据主键查询产品信息
	 * @param productId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getProductInfo")
	public ResultBean getProductInfo(Long productId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjProductEntity product = productService.selectByPrimaryKey(productId);
			return new ResultBean("0000", "请求成功", product);
		} catch (Exception e) {
			logger.error("getProductInfo error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 插入产品信息
	 * @param productId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveProductInfo")
	public ResultBean saveProductInfo(QyjProductEntity productEntity, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
			
			Date nowDate = new Date();
			productEntity.setUpdateTime(nowDate);
			productEntity.setUpdateUser(userBean.getId());
			
			// id为空插入数据
			if (productEntity.getId() == null || productEntity.getId() == 0) {
				productEntity.setCreateTime(nowDate);
				productEntity.setCreateUser(userBean.getId());
				productEntity.setProductStatus(ProductStatusEnum.UNPUBLISHED.toString());
				int insertResult = productService.insert(productEntity);
				logger.info("saveProductInfo insert productEntity, info={}, result={}", productEntity.toString(),
						insertResult);
				if (insertResult == 1) {
					return new ResultBean("0000", "新增产品信息成功", insertResult);
				}
				return new ResultBean("0002", "新增产品信息失败", insertResult);
			}

			// 编辑更新数据
			productEntity.setUpdateTime(nowDate);
			int updateResult = productService.updateByPrimaryKey(productEntity);
			logger.info("saveProductInfo update productEntity, info={}, result={}", productEntity.toString(),
					updateResult);
			if (updateResult == 1) {
				return new ResultBean("0000", "更新产品信息成功", updateResult);
			}
			return new ResultBean("0002", "更新产品信息失败", updateResult);
		} catch (Exception e) {
			logger.error("saveProductInfo error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 根据主键删除产品信息
	 * @param productId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delProductInfo")
	public ResultBean delProductInfo(Long productId, HttpServletRequest request, HttpServletResponse response) {
		try {
			int deleteResult = productService.deleteByPrimaryKey(productId);
			if (deleteResult == 1) {
				return new ResultBean("0000", "删除产品信息成功", deleteResult);
			}
			return new ResultBean("0002", "删除产品信息失败", deleteResult);
		} catch (Exception e) {
			logger.error("delProductInfo error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 上传项目缩略图
	 * @param file
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadImage")
	public ResultBean uploadImage(HttpServletResponse response, HttpServletRequest request, MultipartHttpServletRequest files,
			@RequestParam("file")MultipartFile file) {
		try {
			System.out.println(request.getParameter("username"));
			System.out.println(Utils.getWebAppPath());
			
			String todayDir = new SimpleDateFormat("yyyyMMdd").format(new Date());
			
			// 图片保存文件夹地址
			String fileDirPath = Utils.getUploadFilePath() + File.separator + "product" + File.separator + todayDir;
			FileUtils.mkDirs(fileDirPath);
			
			FileCopyUtils.copy(file.getBytes(), new FileOutputStream(fileDirPath + File.separator + file.getOriginalFilename()));
			return new ResultBean("0000", "图片上传成功", null);
		} catch (Exception e) {
			logger.error("uploadImg error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 上传项目缩略图
	 * @param file
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/saveAllProductInfo")
	public ResultBean saveAllProductInfo(HttpServletResponse response, HttpServletRequest request, QyjProductEntity productEntity,
			@RequestParam("file")MultipartFile file, MultipartHttpServletRequest files) {
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
			productEntity.setUpdateUser(userBean.getId());
			
			productService.saveAllProductInfo(productEntity, file, files);
			return new ResultBean("0000", "保存成功", null);
		} catch (Exception e) {
			logger.error("saveAllProductInfo error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
}
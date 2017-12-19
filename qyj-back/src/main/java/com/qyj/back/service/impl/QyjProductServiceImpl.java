package com.qyj.back.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qyj.back.common.enums.CommonEnums.ProductStatusEnum;
import com.qyj.back.common.util.FileUtils;
import com.qyj.back.common.util.Utils;
import com.qyj.back.dao.QyjFileInfoMapper;
import com.qyj.back.dao.QyjProductMapper;
import com.qyj.back.entity.QyjFileInfoEntity;
import com.qyj.back.entity.QyjProductEntity;
import com.qyj.back.service.QyjProductService;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;

/**
 * 产品表服务层接口
 * @author CTF_stone
 */
@Service
public class QyjProductServiceImpl implements QyjProductService {

	private static final Logger logger = LoggerFactory.getLogger(QyjProductServiceImpl.class);

	@Autowired
	private QyjProductMapper productMapper;
	
	@Autowired
	private QyjFileInfoMapper fileInfoMapper;

	/**
	 * 根据主键删除产品信息
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByPrimaryKey(Long id) throws Exception {
		return productMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 插入产品信息
	 * @param record
	 * @return
	 */
	@Override
	public int insert(QyjProductEntity record) throws Exception {
		return productMapper.insert(record);
	}

	/**
	 * 选择性的插入产品信息
	 * @param record
	 * @return
	 */
	@Override
	public int insertSelective(QyjProductEntity record) throws Exception {
		return productMapper.insertSelective(record);
	}

	/**
	 * 根据产品id获取产品信息
	 * @param id
	 * @return
	 */
	@Override
	public QyjProductEntity selectByPrimaryKey(Long id) throws Exception {
		return productMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据产品id选择性的更新产品信息
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKeySelective(QyjProductEntity record) throws Exception {
		return productMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据产品id更新产品信息
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKey(QyjProductEntity record) throws Exception {
		return productMapper.updateByPrimaryKey(record);
	}

	/**
	 * 获取产品分页数据
	 * @param pageParam 分页信息
	 * @param paramMap 查询参数
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean listProjectPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception {
		if (pageParam == null) {
			pageParam = new PageParam();
		}
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		// 统计产品数量
		int totalCount = productMapper.countProduct(paramMap);

		if (totalCount <= 0) {
			return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), 0, null);
		}

		pageParam.setTotalCount(totalCount);
		// 计算分页信息
		pageParam.splitPageInstance();

		paramMap.put("pageParam", pageParam);

		// 获取分页数据列表
		List<QyjProductEntity> projectList = productMapper.listProduct(paramMap);

		return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), totalCount, projectList);
	}

	/**
	 * 保存产品信息包括图片信息
	 * @param record
	 * @return
	 */
	@Override
	public void saveAllProductInfo(QyjProductEntity productEntity, @RequestParam("file") MultipartFile file,
			MultipartHttpServletRequest files) throws Exception {
		Date nowDate = new Date();
		// id为空插入数据
		if (productEntity.getId() == null || productEntity.getId() == 0) {
			productEntity.setCreateTime(nowDate);
			productEntity.setUpdateTime(nowDate);
			productEntity.setCreateUser(productEntity.getUpdateUser());
			productEntity.setProductStatus(ProductStatusEnum.UNPUBLISHED.toString());
			int insertResult = this.insert(productEntity);
			logger.info("saveAllProductInfo insert productEntity, info={}, insertResult={}", productEntity.toString(), insertResult);
		} else {
			// 编辑更新数据
			productEntity.setUpdateTime(nowDate);
			int updateResult = this.updateByPrimaryKey(productEntity);
			logger.info("saveAllProductInfo update productEntity, info={}, result={}", productEntity.toString(),
					updateResult);
		}

		String todayDir = new SimpleDateFormat("yyyyMMdd").format(nowDate);
		// 图片保存文件夹地址
		String fileDirPath = Utils.getUploadFilePath() + File.separator;
		// 相对文件保存地址的路径
		String relativeFileDirPath = "product" + File.separator + todayDir + File.separator;
		FileUtils.mkDirs(fileDirPath + relativeFileDirPath);
		
		// 真实文件名
		String fileName = file.getOriginalFilename();
		// 后缀带点
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		// 保存的文件路径，文件名用日期+随机数
		String saveRelativeFile = relativeFileDirPath + Utils.getUid(3) + "." + suffix;
		QyjFileInfoEntity fileInfo = new QyjFileInfoEntity();
		fileInfo.setFileName(fileName);
		fileInfo.setBusType("PRODUCT");
		fileInfo.setCreateTime(nowDate);
		fileInfo.setField("HEAD");
		fileInfo.setFileType(suffix);
		fileInfo.setItemId(productEntity.getId());
		fileInfo.setFilePath(saveRelativeFile);

		// 保存文件信息
		fileInfoMapper.insert(fileInfo);
		// 保存文件
		FileCopyUtils.copy(file.getBytes(), new FileOutputStream(fileDirPath + saveRelativeFile));
		
		// 文件名称遍历器
		for (Iterator<String> fileNameIt = files.getFileNames(); fileNameIt.hasNext(); ) {
			MultipartFile nextFile = files.getFile(fileNameIt.next());
			// 真实文件名
			fileName = nextFile.getOriginalFilename();
			// 后缀带点
			suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			// 保存的文件路径，文件名用日期+随机数
			saveRelativeFile = relativeFileDirPath + Utils.getUid(3) + "." + suffix;
			fileInfo = new QyjFileInfoEntity();
			fileInfo.setFileName(fileName);
			fileInfo.setBusType("PRODUCT");
			fileInfo.setCreateTime(nowDate);
			fileInfo.setField("TURN");
			fileInfo.setFileType(suffix);
			fileInfo.setItemId(productEntity.getId());
			fileInfo.setFilePath(saveRelativeFile);
			// 保存文件信息
			fileInfoMapper.insert(fileInfo);
			// 保存文件
			FileCopyUtils.copy(nextFile.getBytes(), new FileOutputStream(fileDirPath + saveRelativeFile));
		}
	}
}

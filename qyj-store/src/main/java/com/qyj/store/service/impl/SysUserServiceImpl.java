package com.qyj.store.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.store.common.util.EncryptionUtils;
import com.qyj.store.common.util.Utils;
import com.qyj.store.dao.SysUserMapper;
import com.qyj.store.entity.SysUserModel;
import com.qyj.store.service.SysUserService;
import com.qyj.store.vo.SysUserBean;
import com.qyj.common.page.PageParam;

/**
 * 系统用户service实现类
 * @author shitongle
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	// 系统用户mapper
	@Autowired
	private SysUserMapper sysUserMapper;

	/**
	 * 获取系统用户列表
	 * @return
	 */
	@Override
	public List<SysUserModel> querySysUserList(SysUserModel queryModel, PageParam pageParam) throws Exception {
		if (null == pageParam) {
			pageParam = new PageParam();
		}
		if (null == queryModel) {
			queryModel = new SysUserModel();
		}
		return sysUserMapper.querySysUserList(queryModel, pageParam);
	}

	/**
	 * 查询系统用户信息分页总记录数
	 * @param queryModel
	 * @param pageParam
	 * @return
	 */
	@Override
	public Integer querySysUserTotal(SysUserModel queryModel, PageParam pageParam) throws Exception {
		if (null == pageParam) {
			pageParam = new PageParam();
		}
		if (null == queryModel) {
			queryModel = new SysUserModel();
		}
		return sysUserMapper.querySysUserTotal(queryModel, pageParam);
	}

	/**
	 * 根据用户名和密码查询用户
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SysUserModel> queryUserByNameAndPsw(String userName, String password) throws Exception {
		if (StringUtils.isEmpty(userName)) {
			throw new Exception("用户名不能为空！");
		}
		if (StringUtils.isEmpty(password)) {
			throw new Exception("密码不能为空！");
		}

		String passwordMD5 = EncryptionUtils.getMD5(password, "utf-8", 1);

		SysUserModel queryUser = new SysUserModel();
		queryUser.setUserName(userName);
		queryUser.setPassword(passwordMD5);
		return sysUserMapper.querySysUserList(queryUser, null);
	}

	/**
	 * 添加用户信息
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer addUser(SysUserBean userBean) throws Exception {

		if (null == userBean) {
			throw new Exception("请填写用户信息！");
		}

		if (StringUtils.isEmpty(userBean.getUserName())) {
			throw new Exception("用户名不能为空！");
		}
		if (StringUtils.isEmpty(userBean.getCard())) {
			throw new Exception("身份证号不能为空！");
		}
		if (StringUtils.isEmpty(userBean.getTelphone())) {
			throw new Exception("联系电话不能为空！");
		}

		// 查询用户名是否已经存在
		SysUserModel queryUser = new SysUserModel();
		queryUser.setUserName(userBean.getUserName());
		List<SysUserModel> userModelList = sysUserMapper.querySysUserList(queryUser, new PageParam());
		if (!Utils.isEmptyCollection(userModelList)) {
			throw new Exception("用户名已经存在！");
		}

		SysUserModel userModel = new SysUserModel();
		BeanUtils.copyProperties(userBean, userModel);

		return sysUserMapper.insertUser(userModel);
	}

	/**
	 * 删除用户信息
	 * @param ids 用户id数组
	 * @throws Exception
	 */
	@Override
	public void delUser(Long... ids) throws Exception {
		if (ids == null || ids.length == 0) {
			return;
		}

		sysUserMapper.delUser(ids);
	}

	/**
	 * 根据id查询系统用户
	 * @param userId 用户id
	 * @throws Exception
	 */
	@Override
	public SysUserBean queryUserById(Long userId) throws Exception {
		SysUserModel queryUser = new SysUserModel();
		queryUser.setId(userId);
		List<SysUserModel> userModelList = sysUserMapper.querySysUserList(queryUser, new PageParam());

		if (Utils.isEmptyCollection(userModelList)) {
			throw new Exception("获取用户信息失败！");
		}

		SysUserBean userBean = new SysUserBean();
		BeanUtils.copyProperties(userModelList.get(0), userBean);

		return userBean;
	}

}

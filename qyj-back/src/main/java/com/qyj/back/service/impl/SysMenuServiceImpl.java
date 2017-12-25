package com.qyj.back.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.back.dao.SysMenuMapper;
import com.qyj.back.entity.SysMenuModel;
import com.qyj.back.service.SysMenuService;
import com.qyj.common.page.PageBean;

/**
 * 系统菜单service实现类
 * @author shitongle
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

	// 系统菜单mapper
	@Autowired
	private SysMenuMapper sysMenuMapper;

	/**
	 * 根据查询条件获取系统菜单列表
	 * @param queryModel
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SysMenuModel> querySysMenuList(SysMenuModel queryModel, PageBean pageBean) throws Exception {
		return sysMenuMapper.querySysMenuList(queryModel, pageBean);
	}

}

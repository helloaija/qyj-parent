package com.qyj.back.service;

import java.util.List;

import com.qyj.back.entity.SysMenuModel;
import com.qyj.common.page.PageBean;

/**
 * 系统菜单service
 * @author shitongle
 *
 */
public interface SysMenuService {
	
	/**
	 * 获取系统菜单列表
	 * @param queryModel
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public List<SysMenuModel> querySysMenuList(SysMenuModel queryModel, PageBean pageBean) throws Exception;
	
}

package com.qyj.store.service;

import java.util.List;

import com.qyj.store.entity.SysMenuModel;

/**
 * 系统菜单service
 * @author shitongle
 */
public interface SysMenuService {

	/**
	 * 获取系统所有菜单
	 * @return
	 */
	public List<SysMenuModel> querySysMenuList() throws Exception;
	
	/**
	 * 根据id获取菜单
	 * @param id
	 * @return
	 */
	public SysMenuModel getMenuById(Long id) throws Exception;
	
	/**
	 * 插入菜单
	 * @param menuModel
	 * @return
	 */
	public int insertMenu(SysMenuModel menuModel) throws Exception;
	
	/**
	 * 更新菜单
	 * @param menuModel
	 * @return
	 */
	public int updateMenu(SysMenuModel menuModel) throws Exception;
	
	/**
	 * 删除菜单以及其子菜单
	 * @param id
	 * @return
	 */
	public int deleteMenuAndChildById(Long id) throws Exception;

}

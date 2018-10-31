package com.qyj.store.dao;

import java.util.List;

import com.qyj.store.entity.SysMenuModel;

public interface SysMenuMapper {
	
	/**
	 * 获取系统所有菜单
	 * @return
	 */
	public List<SysMenuModel> querySysMenuList();
	
	/**
	 * 根据id获取菜单
	 * @param id
	 * @return
	 */
	public SysMenuModel getMenuById(Long id);
	
	/**
	 * 插入菜单
	 * @param menuModel
	 * @return
	 */
	public int insertMenu(SysMenuModel menuModel);
	
	/**
	 * 更新菜单
	 * @param menuModel
	 * @return
	 */
	public int updateMenu(SysMenuModel menuModel);
	
	/**
	 * 删除菜单以及其子菜单
	 * @param id
	 * @return
	 */
	public int deleteMenuAndChildById(Long id);
	
}

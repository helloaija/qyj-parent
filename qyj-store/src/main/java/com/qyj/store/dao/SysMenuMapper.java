package com.qyj.store.dao;

import java.util.List;

import com.qyj.store.entity.SysMenuModel;

public interface SysMenuMapper {
	
	/**
	 * 获取系统所有菜单
	 * @return
	 */
	List<SysMenuModel> querySysMenuList();
	
	/**
	 * 根据id获取菜单
	 * @param id
	 * @return
	 */
	SysMenuModel getMenuById(Long id);
	
	/**
	 * 插入菜单
	 * @param menuModel
	 * @return
	 */
	int insertMenu(SysMenuModel menuModel);
	
	/**
	 * 更新菜单
	 * @param menuModel
	 * @return
	 */
	int updateMenu(SysMenuModel menuModel);
	
	/**
	 * 删除菜单以及其子菜单
	 * @param id
	 * @return
	 */
	int deleteMenuAndChildById(Long id);

	/**
	 * 根据userId获取用户对应的菜单
	 * @param userId
	 * @return
	 */
	List<SysMenuModel> listMenuByUserId(Long userId);
	
}

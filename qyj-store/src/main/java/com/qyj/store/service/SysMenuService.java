package com.qyj.store.service;

import com.qyj.store.entity.SysMenuModel;

import java.util.List;

/**
 * 系统菜单service
 * @author shitongle
 */
public interface SysMenuService {

    /**
     * 获取系统所有菜单
     * @return
     */
    List<SysMenuModel> querySysMenuList() throws Exception;

    /**
     * 根据id获取菜单
     * @param id
     * @return
     */
    SysMenuModel getMenuById(Long id) throws Exception;

    /**
     * 插入菜单
     * @param menuModel
     * @return
     */
    int insertMenu(SysMenuModel menuModel) throws Exception;

    /**
     * 更新菜单
     * @param menuModel
     * @return
     */
    int updateMenu(SysMenuModel menuModel) throws Exception;

    /**
     * 删除菜单以及其子菜单
     * @param id
     * @return
     */
    int deleteMenuAndChildById(Long id) throws Exception;

    /**
     * 根据userId获取用户对应的菜单
     * @param userId
     * @return
     */
    List<SysMenuModel> listMenuByUserId(Long userId);

}

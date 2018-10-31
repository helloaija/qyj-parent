package com.qyj.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.store.dao.SysMenuMapper;
import com.qyj.store.entity.SysMenuModel;
import com.qyj.store.service.SysMenuService;

/**
 * 系统菜单service实现类
 * @author shitongle
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	/**
	 * 获取系统所有菜单
	 * @return
	 */
	@Override
	public List<SysMenuModel> querySysMenuList() throws Exception {
		return sysMenuMapper.querySysMenuList();
	}
	
	/**
	 * 根据id获取菜单
	 * @param id
	 * @return
	 */
	@Override
	public SysMenuModel getMenuById(Long id) throws Exception {
		return sysMenuMapper.getMenuById(id);
	}
	
	/**
	 * 插入菜单
	 * @param menuModel
	 * @return
	 */
	@Override
	public int insertMenu(SysMenuModel menuModel) throws Exception {
		return sysMenuMapper.insertMenu(menuModel);
	}
	
	/**
	 * 更新菜单
	 * @param menuModel
	 * @return
	 */
	@Override
	public int updateMenu(SysMenuModel menuModel) throws Exception {
		if (menuModel.getId() == null || menuModel.getId() == 0) {
			throw new Exception("更新的菜单id为空");
		}
		return sysMenuMapper.updateMenu(menuModel);
	}
	
	/**
	 * 删除菜单以及其子菜单
	 * @param id
	 * @return
	 */
	public int deleteMenuAndChildById(Long id) throws Exception {
		return sysMenuMapper.deleteMenuAndChildById(id);
	}

}

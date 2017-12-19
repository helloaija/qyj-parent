package com.qyj.back.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qyj.back.entity.SysMenuModel;
import com.qyj.common.page.PageBean;

public interface SysMenuMapper {
	
	/**
	 * 根据查询条件获取系统菜单列表
	 * @param queryModel
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public List<SysMenuModel> querySysMenuList(@Param("sysMenuModel")SysMenuModel queryModel, @Param("pageBean")PageBean pageBean);
	
}

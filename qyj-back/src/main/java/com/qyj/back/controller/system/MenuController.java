package com.qyj.back.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyj.back.common.tree.TreeNode;
import com.qyj.back.common.tree.TreeUtil;
import com.qyj.back.entity.SysMenuModel;
import com.qyj.back.service.SysMenuService;
import com.qyj.common.page.PageBean;

/**
 * 菜单controller
 * @author shitongle
 *
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController {

	@Autowired
	private SysMenuService sysMenuService;
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	/**
	 * 跳转菜单管理页面
	 * @return
	 */
	@RequestMapping("/toMenuInfoPage")
	public String toMenuInfoPage() {
		return "/page/systemManage/menuManage/menuInfo";
	}
	
		
	/**
	 * 根据权限查询所有菜单
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryMenuTree")
	@ResponseBody
	public List<TreeNode> queryMenuTree(HttpServletRequest request, HttpServletResponse response) {
		List<TreeNode> tree;
		try {
			List<SysMenuModel> sysMenuList = sysMenuService.querySysMenuList(new SysMenuModel(), new PageBean());
			
			TreeNode rootNode = new TreeNode(new Long(0), "根目录");
			
			TreeUtil.loadTreeNode(rootNode, sysMenuList);
			
			tree = new ArrayList<TreeNode>();
			tree.add(rootNode);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		
		return tree;
	}
	
	
	
}

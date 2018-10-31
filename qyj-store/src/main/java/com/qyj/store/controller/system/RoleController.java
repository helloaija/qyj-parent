package com.qyj.store.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyj.store.common.constant.CommonConstant;
import com.qyj.store.common.util.SessionUtil;
import com.qyj.store.controller.BaseController;
import com.qyj.store.entity.SysMenuModel;
import com.qyj.store.entity.SysRoleModel;
import com.qyj.store.service.SysMenuService;
import com.qyj.store.service.SysRoleService;
import com.qyj.store.vo.SysUserBean;
import com.qyj.common.exception.ValidException;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.common.page.ResultBean;

/**
 * 用户角色信息控制器
 * 
 * @author shitongle
 *
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	protected static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	

	/**
	 * 跳转到用户角色信息页面
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loadRolePage")
	public ResultBean loadRolePage(HttpServletRequest request, HttpServletResponse response, SysRoleModel queryRole) {
		try {
			PageParam pageParam = initPageParam(request);
			if (queryRole != null) {
				StringBuffer queryConditionSb = new StringBuffer();
				if (!StringUtils.isEmpty(queryRole.getRoleCode())) {
					queryConditionSb.append(" and roleCode like '%").append(queryRole.getRoleCode()).append("%' ");
				}
				if (!StringUtils.isEmpty(queryRole.getRoleName())) {
					queryConditionSb.append(" and roleName like '%").append(queryRole.getRoleName()).append("%' ");
				}
				
				pageParam.setQueryCondition(queryConditionSb.toString());
			}
			
			PageBean pageBean = sysRoleService.listSysRolePage(null, pageParam);
			
			return new ResultBean("0000", "加载数据成功", pageBean);
		} catch (ValidException e1) {
			logger.error("loadRolePage error：", e1);
			return new ResultBean("0001", e1.getMessage(), null);
		} catch (Exception e) {
			logger.error("loadRolePage error：", e);
			return new ResultBean("0001", "系统异常：" + e.getMessage(), null);
		}
	}
	
	/**
	 * 新增用户角色信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addRole")
	public ResultBean addRole(HttpServletRequest request, HttpServletResponse response, 
			SysRoleModel roleModel, @RequestParam("menuIds") Long... menuIds) {
		
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
			roleModel.setCreateUser(userBean.getId());
			roleModel.setUpdateUser(userBean.getId());
			
			if (sysRoleService.addRole(roleModel, menuIds) <= 0) {
				return new ResultBean("0001", "添加角色失败", null);
			}
			return new ResultBean("0000", "添加角色成功", null);
		} catch (Exception e) {
			logger.error("addRole", e);
			return new ResultBean("0001", e.getMessage(), null);
		}
	}
	
//	/**
//	 * 删除用角色户信息
//	 * 
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/delRole")
//	public ResultBean delRole(@RequestParam("ids[]")Long... ids) {
//		logger.info("--- delRole begin ---");
//		
//		ResultBean resultBean = new ResultBean();
//		try {
//			sysRoleService.delRole(ids);
//		} catch (Exception e) {
//			resultBean.setSuccess(false);
//			resultBean.setMessage(e.getMessage());
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//		
//		logger.info("--- delRole end ---");
//		return resultBean;
//	}
	
	/**
	 * 根据id获取系统用户角色
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRoleById")
	public ResultBean getRoleById(@RequestParam Long roleId) {
		if (roleId == null) {
			return new ResultBean("0002", "参数错误，roleId为空", null);
		}
		try {
			SysRoleModel roleModel = sysRoleService.queryRoleById(roleId);
			if (roleModel == null) {
				return new ResultBean("0002", "没有对应角色" + roleId, null);
			}
			
			// 查询所有系统菜单
			List<SysMenuModel> menuList = sysMenuService.querySysMenuList();
			return new ResultBean("0000", "获取角色成功", roleModel);
		} catch (Exception e) {
			logger.error("getRoleById error", e);
			return new ResultBean("0001", getExceptionMessage(e), null);
		}
		
	}
	
//	/**
//	 * 修改用户角色信息
//	 * 
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/updateRole")
//	public ResultBean updateRole(SysRoleModel roleModel) {
//		logger.info("--- updateRole begin ---");
//		
//		ResultBean resultBean = new ResultBean();
//		try {
//			// sysUserService.updateUser(userBean);
//			resultBean.setData(roleModel);
//		} catch (Exception e) {
//			resultBean.setSuccess(false);
//			resultBean.setMessage(e.getMessage());
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//		
//		logger.info("--- updateRole end ---");
//		return resultBean;
//	}

}

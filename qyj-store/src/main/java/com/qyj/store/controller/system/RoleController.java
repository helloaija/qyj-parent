package com.qyj.store.controller.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
			SysRoleModel roleModel, @RequestParam(value = "menuIds", required = false) Long... menuIds) {
		
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
	
	/**
	 * 删除用角色户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delRole", method = RequestMethod.POST)
	public ResultBean delRole(@RequestParam("ids") Long... ids) {
		ResultBean resultBean = new ResultBean();
		try {
			int result = sysRoleService.delRole(ids);
			if (result <= 0) {
                return resultBean.init("0002", "删除角色失败");
            }
            return resultBean.init("0000", "删除角色成功");
		} catch (Exception e) {
			logger.error("delete role:", e);
            return resultBean.init("0001", getExceptionMessage(e));
		}
	}
	
	/**
	 * 根据id获取系统用户角色
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRoleById", method = RequestMethod.GET)
	public ResultBean getRoleById(@RequestParam Long roleId) {
		if (roleId == null) {
			return new ResultBean("0002", "参数错误，roleId为空", null);
		}
		try {
			Map<String, Object> roleMap = sysRoleService.getRoleAndMenuByRoleId(roleId);

			return new ResultBean("0000", "获取角色成功", roleMap);
		} catch (Exception e) {
			logger.error("getRoleById error", e);
			return new ResultBean("0001", getExceptionMessage(e), null);
		}
		
	}

	/**
	 * 编辑用户角色信息
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editRole", method = RequestMethod.POST)
	public ResultBean editRole(HttpServletRequest request, HttpServletResponse response,
							  SysRoleModel roleModel, @RequestParam(value = "menuIds", required = false) Long... menuIds) {

		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
			roleModel.setUpdateUser(userBean.getId());
			roleModel.setUpdateTime(new Date());

			if (!sysRoleService.editRole(roleModel, menuIds)) {
				return new ResultBean("0001", "编辑角色失败", null);
			}
			return new ResultBean("0000", "编辑角色成功", null);
		} catch (Exception e) {
			logger.error("editRole", e);
			return new ResultBean("0001", getExceptionMessage(e), null);
		}
	}

}

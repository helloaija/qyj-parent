package com.qyj.back.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.Constants;
import com.qyj.back.common.util.PageUtil;
import com.qyj.back.common.util.SessionUtil;
import com.qyj.back.entity.SysRoleModel;
import com.qyj.back.service.SysRoleService;
import com.qyj.back.vo.SysUserBean;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.ResultBean;

/**
 * 用户角色信息控制器
 * 
 * @author shitongle
 *
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

//	// 系统用户角色service
//	@Autowired
//	private SysRoleService sysRoleService;
//	
//	protected static final Logger logger = LoggerFactory.getLogger(RoleController.class);
//	
//	/**
//	 * 跳转到用户角色信息页面
//	 * @return
//	 */
//	@RequestMapping("/toRoleInfoPage")
//	public String toRoleInfoPage() {
//		return "/page/systemManage/roleManage/roleInfo";
//	}
//
//	/**
//	 * 跳转到用户角色信息页面
//	 * 
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/loadRoleInfoList")
//	public EntityPagination<SysRoleModel> loadRoleInfoList(HttpServletRequest request, HttpServletResponse response, SysRoleModel queryRole) {
//		List<SysRoleModel> userList = new ArrayList<SysRoleModel>();
//		EntityPagination<SysRoleModel> entityPagination = new EntityPagination<SysRoleModel>();
//		try {
//			// 分页bean
//			PageBean pageBean = PageUtil.getPageBean(request);
//			
//			// 总记录数
//			Integer total = sysRoleService.querySysRoleTotal(queryRole, pageBean);
//			
//			PageUtil.setRowCount(pageBean, total);
//			
//			userList = sysRoleService.querySysRoleList(queryRole, pageBean);
//			entityPagination.setTotal(total);
//			entityPagination.setRows(userList);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//		return entityPagination;
//	}
//	
//	/**
//	 * 新增用户角色信息
//	 * 
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/addRole")
//	public ResultBean addRole(HttpServletRequest request, HttpServletResponse response, SysRoleModel roleModel) {
//		logger.info("--- addRole begin ---");
//		
//		ResultBean resultBean = new ResultBean();
//		try {
//			SysUserBean userBean = (SysUserBean) SessionUtil.getAttr(request, Constants.LOGIN_USER);
//			sysRoleService.addRole(roleModel, userBean);
//		} catch (Exception e) {
//			resultBean.setSuccess(false);
//			resultBean.setMessage(e.getMessage());
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//		
//		logger.info("--- addRole end ---");
//		return resultBean;
//	}
//	
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
//	
//	/**
//	 * 根据id获取系统用户角色
//	 * 
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/getRoleById")
//	public ResultBean getRoleById(@RequestParam Long userId) {
//		logger.info("--- getRoleById begin ---");
//		
//		ResultBean resultJson = new ResultBean();
//		try {
//			SysRoleModel roleModel = sysRoleService.queryRoleById(userId);
//			resultJson.setData(roleModel);
//		} catch (Exception e) {
//			resultJson.setSuccess(false);
//			resultJson.setMessage(e.getMessage());
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//		
//		logger.info("--- getRoleById end ---");
//		return resultJson;
//	}
//	
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

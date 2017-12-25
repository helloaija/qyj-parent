package com.qyj.back.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户信息控制器
 * 
 * @author shitongle
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

//	// 系统用户service
//	@Autowired
//	private SysUserService sysUserService;
//	
//	protected static final Logger logger = LoggerFactory.getLogger(UserController.class);
//	
//	/**
//	 * 跳转到用户信息页面
//	 * @return
//	 */
//	@RequestMapping("/toUserInfoPage")
//	public String toUserInfoPage() {
//		return "/page/systemManage/userManage/userInfo";
//	}
//
//	/**
//	 * 跳转到用户信息页面
//	 * 
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/loadUserInfoList")
//	public EntityPagination<SysUserModel> loadUserInfoList(HttpServletRequest request, HttpServletResponse response, SysUserModel queryUser) {
//		List<SysUserModel> userList = new ArrayList<SysUserModel>();
//		EntityPagination<SysUserModel> entityPagination = new EntityPagination<SysUserModel>();
//		try {
//			// 分页bean
//			PageBean pageBean = PageUtil.getPageBean(request);
//			
//			// 总记录数
//			Integer total = sysUserService.querySysUserTotal(queryUser, pageBean);
//			
//			PageUtil.setRowCount(pageBean, total);
//			
//			userList = sysUserService.querySysUserList(queryUser, pageBean);
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
//	 * 新增用户信息
//	 * 
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/addUser")
//	public JSONObject addUser(HttpServletRequest request, HttpServletResponse response, SysUserBean userBean) {
//		logger.info("UserController addUser begin ...");
//		
//		JSONObject resultJson = new JSONObject();
//		resultJson.put("success", true);
//		try {
//			sysUserService.addUser(userBean);
//		} catch (Exception e) {
//			resultJson.put("success", false);
//			resultJson.put("msg", e.getMessage());
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//		
//		logger.info("UserController addUser end ...");
//		return resultJson;
//	}
//	
//	/**
//	 * 删除用户信息
//	 * 
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/delUser")
//	public JSONObject delUser(@RequestParam("ids[]")Long... ids) {
//		logger.info("UserController delUser begin ...");
//		
//		JSONObject resultJson = new JSONObject();
//		resultJson.put("success", true);
//		try {
//			sysUserService.delUser(ids);
//		} catch (Exception e) {
//			resultJson.put("success", false);
//			resultJson.put("msg", e.getMessage());
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//		
//		logger.info("UserController delUser end ...");
//		return resultJson;
//	}
//	
//	/**
//	 * 根据id获取系统用户
//	 * 
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/getUserById")
//	public ResultBean getUserById(@RequestParam Long userId) {
//		logger.info("UserController getUserById begin ...");
//		
//		ResultBean resultJson = new ResultBean();
//		try {
//			SysUserBean userBean = sysUserService.queryUserById(userId);
//			resultJson.setData(userBean);
//		} catch (Exception e) {
//			resultJson.setSuccess(false);
//			resultJson.setMessage(e.getMessage());
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//		
//		logger.info("UserController getUserById end ...");
//		return resultJson;
//	}
//	
//	/**
//	 * 修改用户信息
//	 * 
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/updateUser")
//	public ResultBean updateUser(SysUserBean userBean) {
//		logger.info("UserController updateUser begin ...");
//		
//		ResultBean resultBean = new ResultBean();
//		try {
//			// sysUserService.updateUser(userBean);
//			resultBean.setData(userBean);
//		} catch (Exception e) {
//			resultBean.setSuccess(false);
//			resultBean.setMessage(e.getMessage());
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//		
//		logger.info("UserController updateUser end ...");
//		return resultBean;
//	}

}

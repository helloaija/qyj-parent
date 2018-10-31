package com.qyj.store.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.Constants;
import com.qyj.store.common.constant.CommonConstant;
import com.qyj.store.common.tree.TreeNode;
import com.qyj.store.common.tree.TreeUtil;
import com.qyj.store.common.util.PageUtil;
import com.qyj.store.common.util.SessionUtil;
import com.qyj.store.controller.BaseController;
import com.qyj.store.entity.SysDeptModel;
import com.qyj.store.entity.SysMenuModel;
import com.qyj.store.service.SysDeptService;
import com.qyj.store.service.SysMenuService;
import com.qyj.store.vo.SysUserBean;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.common.page.ResultBean;

/**
 * 部门信息控制器
 * @author shitongle
 */
@Controller
@RequestMapping("/admin/dept")
public class DeptController extends BaseController {

//	// 部门service
//	@Autowired
//	private SysDeptService sysDeptService;
//
//	// 菜单service
//	@Autowired
//	private SysMenuService sysMenuService;
//
//	private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
//
//	/**
//	 * 跳转到部门信息页面
//	 * @return
//	 */
//	@RequestMapping("/toDeptInfoPage")
//	public String toDeptInfoPage() {
//		return "/page/systemManage/deptManage/deptInfo";
//	}
//
//	/**
//	 * 跳转到部门信息页面
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/loadDeptInfoList")
//	public ResultBean loadDeptInfoList(HttpServletRequest request, HttpServletResponse response,
//			SysDeptModel queryDept) {
//		List<SysDeptModel> userList = new ArrayList<SysDeptModel>();
//		try {
//			// 分页bean
//			PageParam pageParam = this.initPageParam(request);
//
//			// 总记录数
//			Integer total = sysDeptService.querySysDeptTotal(queryDept, pageParam);
//
//			PageUtil.setRowCount(pageParam, total);
//
//			userList = sysDeptService.querySysDeptList(queryDept, pageParam);
//			
//			return new ResultBean("0000", "请求成功", new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), total, userList));
//		} catch (Exception e) {
//			logger.error("loadDeptInfoList error", e);
//			return new ResultBean("0001", "请求失败", null);
//		}
//	}
//
//	/**
//	 * 新增部门信息
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/addDept")
//	public ResultBean addDept(HttpServletRequest request, HttpServletResponse response, SysDeptModel deptModel) {
//		logger.info("--- addDept begin ---");
//
//		ResultBean resultBean = new ResultBean();
//		try {
//			SysUserBean userBean = (SysUserBean) SessionUtil.getAttr(request, CommonConstant.SESSION_USER);
//			sysDeptService.addDept(deptModel, userBean);
//		} catch (Exception e) {
//			resultBean.setResultCode("0001");
//			resultBean.setResultMessage(e.getMessage());
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//
//		logger.info("--- addDept end ---");
//		return resultBean;
//	}
//
//	/**
//	 * 删除部门信息
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/delDept")
//	public ResultBean delDept(@RequestParam("ids[]") Long... ids) {
//		logger.info("--- delDept begin ---");
//
//		ResultBean resultBean = new ResultBean();
//		try {
//			sysDeptService.delDept(ids);
//		} catch (Exception e) {
//			resultBean.setSuccess(false);
//			resultBean.setMessage(e.getMessage());
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//
//		logger.info("--- delDept end ---");
//		return resultBean;
//	}
//
//	/**
//	 * 根据id获取系统部门
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/getDeptById")
//	public ResultBean getDeptById(@RequestParam Long deptId) {
//		logger.info("--- getDeptById begin ---");
//
//		ResultBean resultJson = new ResultBean();
//		try {
//			SysDeptModel deptModel = sysDeptService.queryDeptById(deptId);
//			resultJson.setData(deptModel);
//		} catch (Exception e) {
//			resultJson.setResultCode("0001");
//			resultJson.setResultMessage(e.getMessage());
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//
//		logger.info("--- getDeptById end ---");
//		return resultJson;
//	}
//
//	/**
//	 * 修改部门信息
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/updateDept")
//	public ResultBean updateDept(SysDeptModel deptModel, HttpServletRequest request) {
//		logger.info("--- updateDept begin ---");
//
//		ResultBean resultBean = new ResultBean();
//		try {
//			SysUserBean userBean = (SysUserBean) SessionUtil.getAttr(request, Constants.LOGIN_USER);
//			sysDeptService.updateDept(deptModel, userBean);
//			resultBean.setData(deptModel);
//		} catch (Exception e) {
//			resultBean.setResultCode("0001");
//			resultBean.setResultMessage(e.getMessage());
//			logger.error(e.getMessage());
//		}
//
//		logger.info("--- updateDept end ---");
//		return resultBean;
//	}
//
//	/**
//	 * 修改部门信息
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/updateDeptMenu")
//	public ResultBean updateDeptMenu(Long deptId, @RequestParam("menuIds[]") Long... menuIds) {
//
//		ResultBean resultBean = new ResultBean();
//		try {
//			sysDeptService.updateDeptMenu(deptId, menuIds);
//		} catch (Exception e) {
//			resultBean.setResultCode("0001");
//			resultBean.setResultMessage(e.getMessage());
//			logger.error("updateDeptMenu error", e);
//		}
//
//		return resultBean;
//	}
//
//	/**
//	 * 修改部门信息
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/queryMenuTree/{deptId}")
//	public ResultBean queryMenuTree(@PathVariable Long deptId) {
//		logger.info("--- updateDeptMenu begin ---");
//
//		ResultBean resultBean = new ResultBean();
//		try {
//			// 获取菜单列表
//			List<SysMenuModel> sysMenuList = sysMenuService.querySysMenuList(new SysMenuModel(), new PageBean());
//			// 选择的菜单id列表
//			List<Long> checkedMenuIdList = sysDeptService.queryMenuIdListByDeptId(deptId);
//
//			Long menuId;
//			for (int i = 0; i < sysMenuList.size(); i++) {
//				menuId = sysMenuList.get(i).getId();
//				for (int j = 0; j < checkedMenuIdList.size(); j++) {
//					if (menuId.longValue() == checkedMenuIdList.get(j).longValue()) {
//						sysMenuList.get(i).setChecked(true);
//						break;
//					}
//				}
//			}
//
//			// 根目录
//			TreeNode rootNode = new TreeNode(new Long(0), "根目录");
//			// 组装菜单树
//			TreeUtil.loadTreeNode(rootNode, sysMenuList);
//
//			resultBean.setResult(rootNode.getChildren());
//		} catch (Exception e) {
//			resultBean.setResultCode("0001");
//			resultBean.setResultMessage(e.getMessage());
//			logger.error(e.getMessage());
//		}
//
//		logger.info("--- updateDeptMenu end ---");
//		return resultBean;
//	}
}

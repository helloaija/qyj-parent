package com.qyj.store.controller.system;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.common.page.ResultBean;
import com.qyj.store.common.constant.CommonConstant;
import com.qyj.store.common.enums.CommonEnums;
import com.qyj.store.common.util.SessionUtil;
import com.qyj.store.controller.BaseController;
import com.qyj.store.entity.SysRoleModel;
import com.qyj.store.entity.SysUserModel;
import com.qyj.store.service.SysUserService;
import com.qyj.store.vo.SysUserBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户信息控制器
 * @author shitongle
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {

    /** 系统用户service */
    private SysUserService sysUserService;

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 加载用户信息列表
     * @return resultBean
     */
    @ResponseBody
    @RequestMapping(value = "/loadUserInfoList", method = RequestMethod.GET)
    public ResultBean loadUserInfoList(HttpServletRequest request, SysUserModel queryRole) {
        PageParam pageParam = this.initPageParam(request);

        if (queryRole != null) {
            StringBuilder queryConditionSb = new StringBuilder();
            if (!StringUtils.isEmpty(queryRole.getUserName())) {
                queryConditionSb.append(" and user_name like '%").append(queryRole.getUserName()).append("%' ");
            }

            pageParam.setQueryCondition(queryConditionSb.toString());
        }

        PageBean pageBean = sysUserService.listSysUserPage(null, pageParam);

        return new ResultBean("0000", "加载数据成功", pageBean);
    }

    /**
     * 获取用户对应角色列表
     * @return resultBean
     */
    @ResponseBody
    @RequestMapping(value = "/getUserRoleData", method = RequestMethod.GET)
    public ResultBean getUserRoleData(Long userId, HttpServletRequest request) {
        return sysUserService.getUserRoleData(userId);
    }

    /**
     * 新增用户信息
     * @param userModel 用户信息
     * @param request request
     * @param roleIds 角色id数组
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResultBean addUser(SysUserModel userModel, HttpServletRequest request,
                              @RequestParam(value = "roleIds", required = false) Long... roleIds) {
        SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
        userModel.setUpdateUser(userBean.getId());
        userModel.setCreateUser(userBean.getId());
        userModel.setCreateTime(new Date());
        userModel.setUpdateTime(new Date());

		return sysUserService.addUser(userModel, roleIds);
	}

    /**
     * 更新用户信息
     * @param userModel 用户信息
     * @param request request
     * @param roleIds 角色id数组
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResultBean updateUser(SysUserModel userModel, HttpServletRequest request,
                              @RequestParam(value = "roleIds", required = false) Long... roleIds) {
        SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
        userModel.setUpdateUser(userBean.getId());
        userModel.setUpdateTime(new Date());

        return sysUserService.updateUser(userModel, roleIds);
    }

	/**
	 * 删除用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delUser", method = RequestMethod.POST)
	public ResultBean delUser(@RequestParam("ids") Long... ids) throws Exception {
        sysUserService.delUser(ids);
		return new ResultBean("0000", "删除用户成功！");
	}

    /**
     * 更新用户状态
     * @param userId
     * @param request
     * @param enable
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserEnable", method = RequestMethod.POST)
    public ResultBean updateUserEnable(Long userId, HttpServletRequest request, String enable) {
        SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
        logger.info("updateUserEnable message. userId={}, enable={}, optUserId={}, optUserName={}",
                userId, enable, userBean.getId(),userBean.getUserName());

        if (userId == null) {
            return new ResultBean("0002", "用户id为空！");
        }

        if (!CommonEnums.UserEnableEnum.DISABLED.toString().equals(enable) &&
                !CommonEnums.UserEnableEnum.USABLE.toString().equals(enable)) {
            return new ResultBean("0002", "参数错误！");
        }

        SysUserModel userModel = new SysUserModel();
        userModel.setId(userId);
        userModel.setEnable(enable);

        sysUserService.updateUserInfo(userModel);

        return new ResultBean("0000", "删除用户成功！");
    }


    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
}

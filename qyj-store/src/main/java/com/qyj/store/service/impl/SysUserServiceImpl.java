package com.qyj.store.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.ResultBean;
import com.qyj.store.common.enums.CommonEnums;
import com.qyj.store.dao.SysRelationMapper;
import com.qyj.store.dao.SysRoleMapper;
import com.qyj.store.entity.SysRelationEntity;
import com.qyj.store.entity.SysRoleModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyj.store.common.util.EncryptionUtils;
import com.qyj.store.common.util.Utils;
import com.qyj.store.dao.SysUserMapper;
import com.qyj.store.entity.SysUserModel;
import com.qyj.store.service.SysUserService;
import com.qyj.store.vo.SysUserBean;
import com.qyj.common.page.PageParam;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户service实现类
 * @author shitongle
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    /** 系统用户mapper */
    private SysUserMapper sysUserMapper;
    private SysRoleMapper sysRoleMapper;
    private SysRelationMapper sysRelationMapper;

    /**
     * 加载系统用户数据
     * @param queryModel xx
     * @param pageParam  xx
     * @return PageBean
     */
    @Override
    public PageBean listSysUserPage(SysUserModel queryModel, PageParam pageParam) {
        // 计算个数
        int totalCount = sysUserMapper.querySysUserTotal(queryModel, pageParam);
        if (totalCount <= 0) {
            return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), 0, null);
        }

        pageParam.setTotalCount(totalCount);
        // 计算分页信息
        pageParam.splitPageInstance();

        // 获取系统角色列表
        List<SysUserModel> sysUserList = sysUserMapper.querySysUserList(queryModel, pageParam);

        return new PageBean(pageParam.getCurrentPage(), pageParam.getPageSize(), totalCount, sysUserList);
    }

    /**
     * 获取系统用户列表
     * @return
     */
    @Override
    public List<SysUserModel> querySysUserList(SysUserModel queryModel, PageParam pageParam) throws Exception {
        if (null == pageParam) {
            pageParam = new PageParam();
        }
        if (null == queryModel) {
            queryModel = new SysUserModel();
        }
        return sysUserMapper.querySysUserList(queryModel, pageParam);
    }

    /**
     * 查询系统用户信息分页总记录数
     * @param queryModel xx
     * @param pageParam  xx
     * @return
     */
    @Override
    public Integer querySysUserTotal(SysUserModel queryModel, PageParam pageParam) throws Exception {
        if (null == pageParam) {
            pageParam = new PageParam();
        }
        if (null == queryModel) {
            queryModel = new SysUserModel();
        }
        return sysUserMapper.querySysUserTotal(queryModel, pageParam);
    }

    /**
     * 根据用户名和密码查询用户
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public List<SysUserModel> queryUserByNameAndPsw(String userName, String password) throws Exception {
        if (StringUtils.isEmpty(userName)) {
            throw new Exception("用户名不能为空！");
        }
        if (StringUtils.isEmpty(password)) {
            throw new Exception("密码不能为空！");
        }

        String passwordMD5 = EncryptionUtils.getMD5(password, "utf-8", 1);

        SysUserModel queryUser = new SysUserModel();
        queryUser.setUserName(userName);
        queryUser.setPassword(passwordMD5);
        return sysUserMapper.querySysUserList(queryUser, null);
    }

    /**
     * 添加用户信息
     * @param userModel 用户信息
     * @param roleIds   关联的角色id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResultBean addUser(SysUserModel userModel, Long... roleIds) {
        ResultBean resultBean = new ResultBean();

        if (!validUserInfo(resultBean, userModel, roleIds)) {
            return resultBean;
        }

        // 查询用户名是否已经存在
        SysUserModel queryUser = new SysUserModel();
        queryUser.setUserName(userModel.getUserName());
        List<SysUserModel> userModelList = sysUserMapper.querySysUserList(queryUser, new PageParam());
        if (!Utils.isEmptyCollection(userModelList)) {
            return resultBean.init("0002", "用户名已经存在！");
        }

        // 默认启用
        userModel.setEnable(CommonEnums.UserEnableEnum.USABLE.toString());
        String passwordMD5 = EncryptionUtils.getMD5(userModel.getUserName(), null, 1);
        // 初始化密码默认为用户名
        userModel.setPassword(passwordMD5);

        // 插入用户数据
        int insertNum = sysUserMapper.insertUser(userModel);
        if (insertNum != 1) {
            return resultBean.init("0002", "保存用户数据失败！");
        }

        List<SysRelationEntity> relationEntityList = new ArrayList<>();
        SysRelationEntity relationEntity = null;
        for (Long roleId : roleIds) {
            relationEntity = new SysRelationEntity();
            relationEntity.setMainId(userModel.getId());
            relationEntity.setRelationId(roleId);
            relationEntity.setRelationType(CommonEnums.RelatinTypeEnum.USERROLE.toString());
            relationEntityList.add(relationEntity);
        }
        // 批量插入关系记录
        sysRelationMapper.insertRelationBatch(relationEntityList);

        return resultBean.init("0000", "新增用户成功！");
    }

    /**
     * 更新用户信息
     * @param userModel 用户信息
     * @param roleIds   关联的角色id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResultBean updateUser(SysUserModel userModel, Long... roleIds) {
        ResultBean resultBean = new ResultBean();

        if (!validUserInfo(resultBean, userModel, roleIds)) {
            return resultBean;
        }

        // 查询用户名是否已经存在
        SysUserModel queryUser = new SysUserModel();
        queryUser.setUserName(userModel.getUserName());
        List<SysUserModel> userModelList = sysUserMapper.querySysUserList(queryUser, new PageParam());
        if (!Utils.isEmptyCollection(userModelList)) {
            if (userModelList.size() != 1 || userModelList.get(0).getId().longValue() != userModel.getId().longValue()) {
                return resultBean.init("0002", "用户名已经存在！");
            }
        }

        // 插入用户数据
        sysUserMapper.updateUser(userModel);

        SysRelationEntity deleteRelationEntity = new SysRelationEntity();
        deleteRelationEntity.setMainId(userModel.getId());
        deleteRelationEntity.setRelationType(CommonEnums.RelatinTypeEnum.USERROLE.toString());
        // 先删除之前的关联
        sysRelationMapper.deleteRelationByModel(deleteRelationEntity);

        List<SysRelationEntity> relationEntityList = new ArrayList<>();
        SysRelationEntity relationEntity = null;
        for (Long roleId : roleIds) {
            relationEntity = new SysRelationEntity();
            relationEntity.setMainId(userModel.getId());
            relationEntity.setRelationId(roleId);
            relationEntity.setRelationType(CommonEnums.RelatinTypeEnum.USERROLE.toString());
            relationEntityList.add(relationEntity);
        }
        // 批量插入关系记录
        sysRelationMapper.insertRelationBatch(relationEntityList);

        return resultBean.init("0000", "新增用户成功！");
    }

    /**
     * 删除用户信息
     * @param ids 用户id数组
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delUser(Long... ids) {
        if (ids == null || ids.length == 0) {
            return;
        }

        sysUserMapper.delUser(ids);

        for (Long id : ids) {
            SysRelationEntity deleteRelationEntity = new SysRelationEntity();
            deleteRelationEntity.setMainId(id);
            deleteRelationEntity.setRelationType(CommonEnums.RelatinTypeEnum.USERROLE.toString());
            // 先删除之前的关联
            sysRelationMapper.deleteRelationByModel(deleteRelationEntity);
        }
    }

    /**
     * 根据id查询系统用户
     * @param userId 用户id
     * @throws Exception
     */
    @Override
    public SysUserBean queryUserById(Long userId) throws Exception {
        SysUserModel queryUser = new SysUserModel();
        queryUser.setId(userId);
        List<SysUserModel> userModelList = sysUserMapper.querySysUserList(queryUser, new PageParam());

        if (Utils.isEmptyCollection(userModelList)) {
            throw new Exception("获取用户信息失败！");
        }

        SysUserBean userBean = new SysUserBean();
        BeanUtils.copyProperties(userModelList.get(0), userBean);

        return userBean;
    }

    /**
     * 根据userId加载对应的角色
     * @param userId
     * @return
     */
    @Override
    public ResultBean getUserRoleData(Long userId) {
        Map<String, Object> dataMap = new HashMap<>();

        // 获取所有的角色
        List<SysRoleModel> roleList = sysRoleMapper.listSysRolePage(null, null);

        if (userId != null) {
            SysUserModel userModel = sysUserMapper.getUserModelById(userId);

            if (userModel == null) {
                return new ResultBean("0002", "获取不到用户信息，userId=" + userId);
            }

            SysRelationEntity relationEntity = new SysRelationEntity();
            relationEntity.setMainId(userId);
            relationEntity.setRelationType(CommonEnums.RelatinTypeEnum.USERROLE.toString());
            // 查询用户关联的角色id
            List<SysRelationEntity> relationList = sysRelationMapper.listRelationByModel(relationEntity);

            Set<Long> roleIdSet = new HashSet<>();
            for (SysRelationEntity relation : relationList) {
                roleIdSet.add(relation.getRelationId());
            }

            List<JSONObject> roleJsonList = new ArrayList<>();
            for (SysRoleModel role : roleList) {
                JSONObject roleJson = (JSONObject) JSON.toJSON(role);
                if (roleIdSet.contains(role.getId())) {
                    // 如果用户选择了该角色，就把改角色标记成已选择selected:true
                    roleJson.put("selected", true);
                }
                roleJsonList.add(roleJson);
            }

            dataMap.put("role", roleJsonList);
            dataMap.put("user", userModel);

            return new ResultBean("0000", "获取用户角色成功", dataMap);
        }

        dataMap.put("role", roleList);
        return new ResultBean("0000", "获取用户角色成功", dataMap);
    }

    /**
     * 更新用户信息
     * @param userModel 用户信息
     */
    @Override
    public void updateUserInfo(SysUserModel userModel) {
        // 更新用户数据
        sysUserMapper.updateUser(userModel);
    }

    /**
     * 校验用户数据
     * @param resultBean 结果
     * @param userModel 用户数据
     * @param roleIds 角色id数组
     * @return
     */
    private boolean validUserInfo(ResultBean resultBean, SysUserModel userModel, Long... roleIds) {
        if (null == userModel) {
            resultBean.init("0002", "请填写用户信息！");
            return false;
        }

        if (StringUtils.isEmpty(userModel.getUserName())) {
            resultBean.init("0002", "用户名不能为空！");
            return false;
        }
        if (StringUtils.isEmpty(userModel.getRealName())) {
            resultBean.init("0002", "真实姓名不能为空！");
            return false;
        }
        if (StringUtils.isEmpty(userModel.getTelPhone())) {
            resultBean.init("0002", "联系电话不能为空！");
            return false;
        }
        if (roleIds == null || roleIds.length == 0) {
            resultBean.init("0002", "请选择用户角色！");
            return false;
        }

        return true;
    }

    @Autowired
    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Autowired
    public void setSysRoleMapper(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    @Autowired
    public void setSysRelationMapper(SysRelationMapper sysRelationMapper) {
        this.sysRelationMapper = sysRelationMapper;
    }
}

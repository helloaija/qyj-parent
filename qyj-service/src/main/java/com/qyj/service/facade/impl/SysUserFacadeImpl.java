package com.qyj.service.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qyj.facade.SysUserFacade;

@Service("SysUserFacade")
public class SysUserFacadeImpl implements SysUserFacade {

	public List<Object> getSysUserById() {
		System.out.println("调用dubbo服务成功");
		return new ArrayList<Object>();
	}

}

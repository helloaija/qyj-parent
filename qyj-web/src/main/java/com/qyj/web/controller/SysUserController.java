package com.qyj.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyj.facade.SysUserFacade;

@Controller
@RequestMapping("/user")
public class SysUserController {
	
	@Autowired
	private SysUserFacade sysUserFacade;
	
	@RequestMapping("/getSysUserById")
	@ResponseBody
	public List<Object> getSysUserById() {
		List<Object> list = sysUserFacade.getSysUserById();
		return list;
	}
	
}


var qyjApp = angular.module("qyjApp");

/**
 * service-登录页面
 */
qyjApp.service('loginService', ["$http",
    function($http) {
		// 获取登录验证码
	    this.getLoginCode = function(phoneNum, loginCode) {
	        return $http({  
	            method: "POST",  
	            url: qyjApp.httpsHeader + "/wechat/freedom/login/getLoginCode",
	            params : {phoneNum: phoneNum, loginCode : loginCode}
	        });
	    }
	    
	    // 登录
	    this.doLogin = function(params) {
	        return $http({  
	            method: "POST",  
	            url: qyjApp.httpsHeader + "/wechat/freedom/login/doLogin",
	            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	            data : params,
	            transformRequest: transformRequest
	        });
	    }
		
		// 序列化参数方法
	    function transformRequest(obj) {
	    	var str = [];
			for ( var s in obj) {
				str.push(encodeURIComponent(s) + "=" + encodeURIComponent(obj[s]));
			}
			return str.join("&");   
	    }
	}
]);
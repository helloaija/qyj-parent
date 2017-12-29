
var qyjApp = angular.module("qyjApp");

/**
 * service-用户账户
 */
qyjApp.service('accountService', ["$http",
    function($http) {
		// 获取登录用户账户信息
	    this.getLoginUserInfo = function() {
	        return $http({  
	            method: "POST",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/account/getLoginUserInfo",
	            headers: {'Content-Type' : 'application/x-www-form-urlencoded'},
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
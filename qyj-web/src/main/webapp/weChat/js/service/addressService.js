
var qyjApp = angular.module("qyjApp");

/**
 * service-账户 地址
 */
qyjApp.service('addressService', ["$http",
    function($http) {
	
		// 获取地址列表
	    this.listAddress = function() {
	        return $http({  
	            method: "GET",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/address/listAddress"
	        });
	    }
    
		// 根据id获取地址
	    this.getAddressById = function(addressId) {
	        return $http({  
	            method: "GET",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/address/getAddressById",
	            params: {'addressId' : addressId}
	        });
	    }
	    
	    // 保存地址
	    this.saveAddress = function(params) {
	        return $http({  
	            method: "POST",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/address/saveAddress",
	            headers: {'Content-Type' : 'application/x-www-form-urlencoded'},
	            data: params,
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
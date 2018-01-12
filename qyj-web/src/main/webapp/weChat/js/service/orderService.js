
var qyjApp = angular.module("qyjApp");

/**
 * service-账户 地址
 */
qyjApp.service('orderService', ["$http",
    function($http) {
	
		// 获取订单列表
	    this.listOrderPage = function() {
	        return $http({  
	            method: "GET",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/order/listOrderPage"
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
	    
	    // 根据id删除地址
	    this.delAddressById = function(addressId) {
	        return $http({  
	            method: "POST",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/address/delAddressById",
	            headers: {'Content-Type' : 'application/x-www-form-urlencoded'},
	            data: {'addressId' : addressId},
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
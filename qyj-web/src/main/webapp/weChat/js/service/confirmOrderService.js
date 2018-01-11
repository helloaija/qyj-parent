
var qyjApp = angular.module("qyjApp");

/**
 * service-确认订单
 */
qyjApp.service('confirmOrderService', ["$http",
    function($http) {
		// 获取订单信息，包括产品、地址
	    this.getProductOrder = function(productId, addressId) {
	        return $http({  
	            method: "GET",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/product/getProductOrder",
	            params: {"productId" : productId, 'addressId' : addressId, r : Math.random()}
	        });
	    }
	    
	    // 保存订单
	    this.saveProductOrder = function(params) {
	        return $http({  
	            method: "POST",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/product/saveProductOrder",
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
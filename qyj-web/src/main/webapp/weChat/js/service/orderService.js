
var qyjApp = angular.module("qyjApp");

/**
 * service-账户 地址
 */
qyjApp.service('orderService', ["$http",
    function($http) {
		// 根据订单id获取订单
		this.getOrderById = function(orderId) {
			return $http({  
	            method: "GET",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/order/getOrderById",
	            params : {orderId: orderId}
	        });
		}
	
		// 获取订单列表
	    this.listOrderPage = function(params) {
	        return $http({  
	            method: "GET",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/order/listOrderPage",
	            params : params
	        });
	    }
    
		// 取消订单
	    this.cancelOrder = function(orderId) {
	        return $http({  
	        	method: "POST",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/order/cancelOrder",
	            headers: {'Content-Type' : 'application/x-www-form-urlencoded'},
	            data: {orderId : orderId},
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
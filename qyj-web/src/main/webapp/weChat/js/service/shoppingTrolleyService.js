
var qyjApp = angular.module("qyjApp");

/**
 * service-购物车
 */
qyjApp.service('shoppingTrolleyService', ["$http",
    function($http) {
	
		// 获取登录用户购物车列表
	    this.listShoppingTrolley = function() {
	        return $http({  
	            method: "GET",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/shoppingTrolley/listShoppingTrolley"
	        });
	    }
    
		// 删除购物车记录
	    this.delShoppingTrolley = function(ids) {
	        return $http({  
	            method: "POST",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/shoppingTrolley/delShoppingTrolley",
	            headers: {'Content-Type' : 'application/x-www-form-urlencoded'},
	            transformRequest: transformRequest,
	            data : {ids : ids}
	        });
	    }
	    
	    // 批量更新购物车记录
	    this.updateShoppingTrolleyList = function(params) {
	        return $http({  
	            method: "POST",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/shoppingTrolley/updateShoppingTrolleyList",
	            headers: {'Content-Type' : 'application/x-www-form-urlencoded'},
	            transformRequest: transformRequest,
	            data : params
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
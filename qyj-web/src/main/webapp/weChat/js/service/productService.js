
var qyjApp = angular.module("qyjApp");
qyjApp.service('productService', ["$http",
    function($http) {
	    this.loadProductList = function (params) {
	    	// 请求获取产品分页数据
	        return $http({
				method : "GET",
				url : qyjApp.httpsHeader + "/wechat/freedom/product/listProductPage",
				params : params
			});
	    };
	    
	    this.getProductInfo = function (productId) {
	    	// 请求获取产品数据
	        return $http({
				method : "GET",
				url : qyjApp.httpsHeader + "/wechat/freedom/product/getProductInfo",
				params : {productId : productId}
			});
	    };
	    
	    // 加入购物车
	    this.addShoppingTrolley = function(productId) {
	        return $http({  
	        	method: "POST",  
	            url: qyjApp.httpsHeader + "/wechat/restrict/shoppingTrolley/addShoppingTrolley",
	            headers: {'Content-Type' : 'application/x-www-form-urlencoded'},
	            data: {productId : productId},
	            transformRequest: transformRequest
	        });
	    };
	    
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
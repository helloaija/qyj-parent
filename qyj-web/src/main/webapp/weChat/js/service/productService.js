
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
	    }
	}
]);
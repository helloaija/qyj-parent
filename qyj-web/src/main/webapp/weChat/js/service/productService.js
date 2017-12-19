
var qyjApp = angular.module("qyjApp");
qyjApp.service('productService', ["$http",
    function($http) {
	    this.loadProductList = function (data) {
	    	// 请求获取产品分页数据
	        return $http.post(qyjApp.httpsHeader + "/wechat/freedom/product/listProductPage", data);
	    }
	}
]);
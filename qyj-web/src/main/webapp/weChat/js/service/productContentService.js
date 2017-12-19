
var qyjApp = angular.module("qyjApp");
qyjApp.service('productContentService', ["$http",
    function($http) {
	    this.getProductInfo = function (productId) {
	    	// 请求获取产品数据
	        return $http({
				method : "GET",
				url : qyjApp.httpsHeader + "/wechat/freedom/product/getProductInfo",
				params : {productId : productId}
			});
	    }
	}
]);
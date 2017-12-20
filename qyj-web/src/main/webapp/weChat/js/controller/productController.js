
var qyjApp = angular.module("qyjApp");

/**
 * 首页-产品列表控制器
 */
qyjApp.controller("productCtrl", [ "$scope", "productService",
    function($scope, productService) {
   		// 获取产品列表信息
   		productService.loadProductList({currentPage : 1, pageSize : 10}).then(function(response) {
   			var resultBean = response.data;
   			// 加载产品列表
   			if (resultBean.resultCode == "0000" && resultBean.result.recordList != null) {
	   			$scope.productList = resultBean.result.recordList;
	   		}
	   	});
	} 
]);

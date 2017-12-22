
var qyjApp = angular.module("qyjApp");

/**
 * 产品详情控制器
 */
qyjApp.controller("productContentCtrl", [ "$scope", "$stateParams", "productContentService",
    function($scope, $stateParams, productContentService) {
		// 轮播时间间隔（毫秒）
		$scope.slideInterval = 5000;
		$scope.slideList = [];
		$scope.filePathHeader = qyjApp.uploadImageHeader;
		
		// 产品详情
		$scope.tabList = [];
	
   		// 获取产品列表信息
		productContentService.getProductInfo($stateParams.productId).then(function(response) {
   			var resultBean = response.data;
   			// 加载产品列表
   			if (resultBean.resultCode == "0000" && resultBean.result) {
	   			$scope.product = resultBean.result;
	   			$scope.tabList = resultBean.result.productDetailList;
	   			$scope.slideList = resultBean.result.fileInfoList;
	   		}
	   	});
	}
]);

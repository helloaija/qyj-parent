
var qyjApp = angular.module("qyjApp");

/**
 * 产品详情控制器
 */
qyjApp.controller("productContentCtrl", [ "$scope", "$stateParams", "productService",
    function($scope, $stateParams, productService) {
		// 轮播时间间隔（毫秒）
		$scope.slideInterval = 5000;
		$scope.slideList = [];
		$scope.uploadFileHeader = qyjApp.uploadFileHeader;
		
		// 产品详情
		$scope.tabList = [];
	
   		// 获取产品列表信息
		productService.getProductInfo($stateParams.productId).then(function(response) {
   			var resultBean = response.data;
   			// 加载产品列表
   			if (resultBean.resultCode == "0000" && resultBean.result) {
	   			$scope.product = resultBean.result;
	   			$scope.tabList = resultBean.result.productDetailList;
	   			$scope.slideList = resultBean.result.fileInfoList;
	   		}
	   	});
		
		// 去除订单详情缓存的地址id、购买数量、买家留言
		sessionStorage.removeItem("addressId");
		sessionStorage.removeItem("goodsNumber");
		sessionStorage.removeItem("buyerMessage");
		
		// 加入购物车
		$scope.addToTrolley = function() {
			// 加入购物车
			productService.addShoppingTrolley($stateParams.productId).then(function(response) {
	   			var resultBean = response.data;
	   			if (resultBean.resultCode == "0000") {
	   				alert("加入购物车成功");
		   		} else {
		   			alert(resultBean.resultMessage);
		   		}
		   	});
		};
	}
]);

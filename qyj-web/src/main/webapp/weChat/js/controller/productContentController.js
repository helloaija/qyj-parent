
var qyjApp = angular.module("qyjApp");

/**
 * 产品详情控制器
 */
qyjApp.controller("productContentCtrl", [ "$scope", "$stateParams", "productContentService",
    function($scope, $stateParams, productContentService) {
   		// 获取产品列表信息
		productContentService.getProductInfo($stateParams.productId).then(function(response) {
   			var resultBean = response.data;
   			// 加载产品列表
   			if (resultBean.resultCode == "0000" && resultBean.result) {
	   			$scope.product = resultBean.result;
	   		}
	   	});
		
		$scope.slideInterval = 5000;
		
		$scope.slideList = [{ image: 'http://www.harworld.com/upfile/201509/20150921173324_product_img.jpg', id:0 },
		             { image: 'http://www.harworld.com/upfile/201509/20150921173324_product_img.jpg', id:1 }];
		
		$scope.tabs = [
		               { title:'Dynamic Title 1', content:'Dynamic content 1' },
		               { title:'Dynamic Title 2', content:'Dynamic content 2', disabled: true }
		             ];

		             $scope.alertMe = function() {
		               setTimeout(function() {
		                 $window.alert('You\'ve selected the alert tab!');
		               });
		             };

		             $scope.model = {
		               name: 'Tabs'
		             };
		
	} 
]);

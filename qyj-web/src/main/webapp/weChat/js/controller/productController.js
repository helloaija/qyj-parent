
var qyjApp = angular.module("qyjApp");

/**
 * 首页-产品列表控制器
 */
qyjApp.controller("productCtrl", ["$rootScope", "$scope", "productService",
    function($rootScope, $scope, productService) {
		$rootScope.homeIndex = 2;
		// 当前页
		var currentPage = 1;
		var pageSize = 10;
		// 产品列表数据
		$scope.productList = [];
		// 加载数据中
		$scope.isBusy = false;
		// 是否加载光光
		$scope.isLoadFinish = false;
		// 列表图片前缀
		$scope.uploadFileHeader = qyjApp.uploadFileHeader;
		
   		$scope.nextPage = function() {
   			if ($scope.isLoadFinish) {
   				return;
   			}
   			$scope.isBusy = true;
   			// 获取产品列表信息
   	   		productService.loadProductList({currentPage : currentPage, pageSize : pageSize}).then(function(response) {
   	   			var resultBean = response.data;
   	   			// 加载产品列表
   	   			if (resultBean.resultCode == "0000" && resultBean.result.recordList != null) {
   	   				$scope.productList = $scope.productList.concat(resultBean.result.recordList);
   	   				currentPage += 1;
   	   				// 当前页大于等于总页数，说明数据已经加载光光
   	   				if (resultBean.result.currentPage >= resultBean.result.pageCount) {
   	   					$scope.isLoadFinish = true;
   	   				}
   		   		}
   	   			$scope.isBusy = false;
   		   	});
   		};
	} 
]);

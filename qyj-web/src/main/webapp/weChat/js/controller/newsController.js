
var qyjApp = angular.module("qyjApp");

/**
 * 首页-新闻公告列表控制器
 */
qyjApp.controller("newsCtrl", ["$rootScope", "$scope", "newsService",
    function($rootScope, $scope, newsService) {
		$rootScope.homeIndex = 3;
		// 当前页
		var currentPage = 1;
		var pageSize = 15;
		
		// 新闻公告列表数据
		$scope.newsList = [];
		// 加载数据中
		$scope.isBusy = false;
		// 是否加载光光
		$scope.isLoadFinish = false;
		
		$scope.nextPage = function() {
			if ($scope.isLoadFinish) {
				return;
			}
			$scope.isBusy = true;
			// 获取新闻公告列表信息
			newsService.loadNewsInfoList({currentPage : currentPage, pageSize : pageSize}).then(function(response) {
	   			var resultBean = response.data;
	   			// 加载新闻公告列表
	   			if (resultBean.resultCode == "0000" && resultBean.result.recordList != null) {
	   				$scope.newsList = $scope.newsList.concat(resultBean.result.recordList);
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


/**
 * 首页-新闻公告列表控制器
 */
qyjApp.controller("newsContentCtrl", [ "$scope", "$stateParams", "newsService",
    function($scope, $stateParams, newsService) {
   		// 获取新闻公告列表信息
		newsService.getNewsInfo($stateParams.newsInfoId).then(function(response) {
   			var resultBean = response.data;
   			// 加载新闻公告列表
   			if (resultBean.resultCode == "0000" && resultBean.result) {
	   			$scope.content = resultBean.result.content;
	   		}
	   	});
	} 
]);

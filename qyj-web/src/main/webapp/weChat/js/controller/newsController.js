
var qyjApp = angular.module("qyjApp");

/**
 * 首页-新闻公告列表控制器
 */
qyjApp.controller("newsCtrl", [ "$scope", "newsService",
    function($scope, newsService) {
   		// 获取新闻公告列表信息
	newsService.loadNewsInfoList({currentPage : 1, pageSize : 10}).then(function(response) {
   			var resultBean = response.data;
   			// 加载新闻公告列表
   			if (resultBean.resultCode == "0000" && resultBean.result.recordList != null) {
	   			$scope.newsList = resultBean.result.recordList;
	   		}
	   	});
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

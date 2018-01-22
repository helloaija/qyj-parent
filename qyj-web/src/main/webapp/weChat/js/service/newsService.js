
var qyjApp = angular.module("qyjApp");

/**
 * service-新闻公告列表
 */
qyjApp.service('newsService', ["$http",
    function($http) {
		// 请求获取新闻通知分页数据
		this.loadNewsInfoList = function(data) {
			return $http({
				method : "GET",
				url : qyjApp.httpsHeader + "/wechat/freedom/news/listNewsInfoPage",
				params : data
			});
		};
		
		// 请求获取新闻通知内容
		this.getNewsInfo = function(newsInfoId) {
			return $http({
				method : "GET",
				url : qyjApp.httpsHeader + "/wechat/freedom/news/getNewsInfo",
				params : {newsInfoId : newsInfoId}
			});
		};
		
		// 请求获取新闻通知内容
		this.scanNews = function(newsInfoId) {
			return $http({
				method : "GET",
				url : qyjApp.httpsHeader + "/wechat/freedom/news/scanNews",
				params : {newsInfoId : newsInfoId}
			});
		};
	}
]);
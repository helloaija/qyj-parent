
var qyjApp = angular.module("qyjApp");

/**
 * 首页-新闻公告列表控制器
 */
qyjApp.controller("homeCtrl", [ "$scope",
    function($scope) {
		// 列表图片前缀
		$scope.httpsHeader = qyjApp.httpsHeader;
	} 
]);



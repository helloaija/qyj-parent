
var qyjApp = angular.module("qyjApp");

/**
 * 首页-新闻公告列表控制器
 */
qyjApp.controller("loginCtrl", [ "$scope",
    function($scope) {
		$scope.doLogin = function() {
			alert("login");
		};
	} 
]);



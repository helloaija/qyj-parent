
var qyjApp = angular.module("qyjApp");

/**
 * 首页-登录页控制器
 */
qyjApp.controller("orderListCtrl", [ "$scope", "orderService", 
    function($scope, orderService) {
	
		orderService.listOrderPage().then(function(response) {
			var resultBean = response.data;
			// 登录成功
			if ("0000" == resultBean.resultCode) {
			} else {
				alert(resultBean.resultMessage);
			}
		});
	} 
]);



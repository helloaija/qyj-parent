var qyjApp = angular.module("qyjApp");

/**
 * 支付结果控制器
 */
qyjApp.controller("payResultCtrl", [ "$scope", "$stateParams",
    function($scope, $stateParams) {
		// 支付结果
		$scope.payResult = JSON.parse($stateParams.result) ? JSON.parse($stateParams.result) : {};
	} 
]).filter("showTimeFilter", function() {
	// 订单状态
	return function(time) {
		if (time) {
			return time.substring(0, time.length - 3);
		} else {
			return "";
		}
    }  
});
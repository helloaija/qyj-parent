
var qyjApp = angular.module("qyjApp");

/**
 * 首页-登录页控制器
 */
qyjApp.controller("orderListCtrl", [ "$scope", "orderService", 
    function($scope, orderService) {
		$scope.orderList = [];
	
		orderService.listOrderPage().then(function(response) {
			var resultBean = response.data;
			// 登录成功
			if ("0000" == resultBean.resultCode) {
				// 订单列表
				$scope.orderList = resultBean.result.recordList;
				angular.forEach(resultBean.result.recordList, function(order, index) {
					var goodsList = order.orderGoodsList;
					// 统计订单总数量
					var totalNumber = 0;
					if (goodsList) {
						angular.forEach(goodsList, function(goods, index) {
							totalNumber = goods.number * 1 + totalNumber;
						});
					}
					order.totalNumber = totalNumber;
				});
			} else {
				alert(resultBean.resultMessage);
			}
		});
	} 
]);



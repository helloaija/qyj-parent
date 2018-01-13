
var qyjApp = angular.module("qyjApp");

/**
 * 首页-登录页控制器
 */
qyjApp.controller("orderListCtrl", [ "$scope", "orderService", 
    function($scope, orderService) {
		// 当前页
		var currentPage = 1;
		var pageSize = 10;
		// 订单列表数据
		$scope.orderList = [];
		// 加载数据中
		$scope.isBusy = false;
		// 是否加载光光
		$scope.isLoadFinish = false;
		// 当前tab
		$scope.tabState = "ALL";
		
		// 加载分页数据
		$scope.nextPage = function() {
   			if ($scope.isLoadFinish) {
   				return;
   			}
   			$scope.isBusy = true;
   			// 获取订单信息
   			orderService.listOrderPage({currentPage : currentPage, pageSize : pageSize, state : $scope.tabState}).then(function(response) {
   				var resultBean = response.data;
   				// 加载订单列表
   				if ("0000" == resultBean.resultCode) {
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
   					
   					currentPage += 1;
   	   				// 当前页大于等于总页数，说明数据已经加载光光
   	   				if (resultBean.result.currentPage >= resultBean.result.pageCount) {
   	   					$scope.isLoadFinish = true;
   	   				}
   					
   					// 订单列表
   					$scope.orderList = $scope.orderList.concat(resultBean.result.recordList);
   				} else {
   					alert(resultBean.resultMessage);
   				}
   				
   				$scope.isBusy = false;
   			});
   		};
   		
   		// 切换tab
   		$scope.tapChange = function(state) {
   			$scope.tabState = state;
   			$scope.isLoadFinish = false;
   			angular.element(document.body).scrollTop(0);
   			$scope.orderList = [];
   			currentPage = 1;
   			$scope.nextPage();
   		}
   		
   		// 取消订单
   		$scope.cancelOrder = function(orderId) {
   			orderService.cancelOrder(orderId).then(function(response) {
   				var resultBean = response.data;
   				// 取消订单成功
   				if ("0000" == resultBean.resultCode) {
   					// 重新加载数据
   					$scope.tapChange($scope.tabState);
   				} else {
   					alert(resultBean.resultMessage);
   				}
   			});
   		}
	} 
]).filter("orderStatusFilter", function() {
	// 订单状态
	return function(status) {
		var statusObj = {"UNPAY" : "待支付", "UNSEND": "代发货", "UNTAKE":"待收货", "END": "已结束"};
		return statusObj[status];
    }  
}).filter("showTimeFilter", function() {
	// 订单状态
	return function(time) {
		return time.substring(0, time.length - 3);
    }  
});



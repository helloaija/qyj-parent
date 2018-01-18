var qyjApp = angular.module("qyjApp");

/**
 * 控制器 - 购物车结算
 */
qyjApp.controller("trolleyBalanceCtrl", ["$scope", "$stateParams", "shoppingTrolleyService",
    function($scope, $stateParams, shoppingTrolleyService) {
		$scope.address = {};
		$scope.shoppingTrolleyList = [];
		// 商品数量
		$scope.totalNumber = 1;
		// 商品总价
		$scope.totalAmount = 0;
		// 获取地址id
		var addressId = sessionStorage.getItem("addressId");
		// 支付对话框
		$scope.showPayDialog = false;
		// 订单
		$scope.order = {};
		
		shoppingTrolleyService.getTrolleyBalance($stateParams.ids, addressId).then(function(response) {
			var resultBean = response.data;
			if ("0000" == resultBean.resultCode) {
				if (!resultBean.result.beanList || resultBean.result.beanList.length <= 0) {
					alert("没有获取到商品数据");
					return;
				}
				
				angular.forEach(resultBean.result.beanList, function(item, index) {
					$scope.totalNumber += item.number;
					$scope.totalAmount += item.number * item.productPrice;
				});
				
				$scope.address = resultBean.result.address;
				$scope.shoppingTrolleyList = resultBean.result.beanList;
			} else {
				alert(resultBean.resultMessage);
			}
		});
		
		// 买家留言
		if (sessionStorage.getItem("buyerMessage")) {
			$scope.buyerMessage = sessionStorage.getItem("buyerMessage");
		}
		
		// 买家留言change事件
		$scope.changeBuyerMessage = function() {
			sessionStorage.setItem("buyerMessage", $scope.buyerMessage);
		};
		
		// 去支付，保存购物车订单
		$scope.saveTrolleyOrder = function() {
			if ($scope.address == null) {
				alert("请选择收获地址");
				return;
			}
			if (!$scope.shoppingTrolleyList || $scope.shoppingTrolleyList.length <= 0) {
				alert("没有需要结算的商品")
				return;
			}
			var orderParams = {};
			orderParams.buyerName = $scope.address.name;
			orderParams.buyerPhone = $scope.address.phone;
			orderParams.buyerAddress = $scope.address.province + $scope.address.city + $scope.address.county + $scope.address.detail;
			orderParams.buyerMessage = $scope.buyerMessage;
			
			angular.forEach($scope.shoppingTrolleyList, function(item, index) {
				orderParams["list[" + index + "].id"] = item.id;
				orderParams["list[" + index + "].number"] = item.number;
			});
			
			shoppingTrolleyService.saveTrolleyOrder(orderParams).then(function(response) {
				var resultBean = response.data;
				if ("0000" == resultBean.resultCode) {
					// 保存成功的订单信息
					$scope.order = resultBean.result;
					// 保存订单成功，打开支付对话框
					$scope.showPayDialog = true;
				} else {
					alert(resultBean.resultMessage);
				}
			});
		};
	}
]);

var qyjApp = angular.module("qyjApp");

/**
 * 订单确认
 */
qyjApp.controller("confirmOrderCtrl", [ "$scope", "$stateParams", "$state", "confirmOrderService", "orderService",
    function($scope, $stateParams, $state, confirmOrderService, orderService) {
		$scope.order = {};
		// 产品
		$scope.product = null;
		// 收货地址
		$scope.address = null;
		// 购买数量
		$scope.goodsNumber = 1;
		// 买家留言
		$scope.buyerMessage = "";
		// 显示支付窗口
		$scope.showPayDialog = false;
		
		// 获取地址id
		var addressId = sessionStorage.getItem("addressId");
		console.log(addressId);
		confirmOrderService.getProductOrder($stateParams.productId, addressId).then(function(response) {
			var resultBean = response.data;
			if ("0000" == resultBean.resultCode) {
				$scope.product = resultBean.result.product;
				$scope.address = resultBean.result.address;
				// 合计
				$scope.totalAmount = $scope.product.price * $scope.goodsNumber;
			} else {
				alert(resultBean.resultMessage);
			}
		});
		
		// 购买数量
		if (sessionStorage.getItem("goodsNumber")) {
			$scope.goodsNumber = sessionStorage.getItem("goodsNumber") * 1;
		}
		// 买家留言
		if (sessionStorage.getItem("buyerMessage")) {
			$scope.buyerMessage = sessionStorage.getItem("buyerMessage");
		}
		
		// 加数量
		$scope.addNumber = function() {
			$scope.goodsNumber += 1;
			$scope.totalAmount = $scope.product.price * $scope.goodsNumber;
			sessionStorage.setItem("goodsNumber", $scope.goodsNumber);
		};
		
		// 减数量
		$scope.minusNumber = function() {
			var num = $scope.goodsNumber - 1;
			if (num < 1) {
				$scope.goodsNumber = 1;
			} else {
				$scope.goodsNumber = num;
			}
			$scope.totalAmount = $scope.product.price * $scope.goodsNumber;
			sessionStorage.setItem("goodsNumber", $scope.goodsNumber);
		};
		
		// 买家留言change事件
		$scope.changeBuyerMessage = function() {
			sessionStorage.setItem("buyerMessage", $scope.buyerMessage);
		};
		
		// 保存订单
		$scope.saveProductOrder = function() {
			if ($scope.address == null) {
				alert("请选择收获地址");
				return;
			}
			var orderParams = {};
			orderParams.buyerName = $scope.address.name;
			orderParams.buyerPhone = $scope.address.phone;
			orderParams.buyerAddress = $scope.address.province + $scope.address.city + $scope.address.county + $scope.address.detail;
			orderParams.buyerMessage = $scope.buyerMessage;
			orderParams["orderGoodsList[0].productId"] = $scope.product.id;
			orderParams["orderGoodsList[0].number"] = $scope.goodsNumber;
			confirmOrderService.saveProductOrder(orderParams).then(function(response) {
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
		
		// 取消支付
		$scope.cancelPay = function() {
			// 在当前历史记录上跳转
			$state.go("payResult", {result:JSON.stringify({code: -1, message: '支付失败'})}, {location:'replace'});
		};
		
		// 确认支付
		$scope.surePay = function() {
			orderService.confirmPayOrder($scope.order.id).then(function(response) {
				var resultBean = response.data;
				// 支付成功
				if ("0000" == resultBean.resultCode) {
					// 在当前历史记录上跳转
					$state.go("payResult", {result:JSON.stringify({code: 0, message: "支付成功"})}, {location:'replace'});
				} else {
					alert(resultBean.resultMessage);
				}
			});
		};
	} 
]);



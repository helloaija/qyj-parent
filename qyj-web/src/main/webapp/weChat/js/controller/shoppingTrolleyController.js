
var qyjApp = angular.module("qyjApp");

/**
 * 控制器-购物车
 */
qyjApp.controller("shoppingTrolleyCtrl", [ "$scope", "shoppingTrolleyService", "dialogService",
    function($scope, shoppingTrolleyService, dialogService) {
		// 是否编辑
		$scope.isEdit = false;
		// 合计
		$scope.totalAmount = 0;
		
		$scope.shoppingTrolleyList = [];
		// 加载购物车记录列表
		shoppingTrolleyService.listShoppingTrolley().then(function(response) {
			var resultBean = response.data;
			if ("0000" == resultBean.resultCode && resultBean.result) {
				var shoppingTrolleyList = resultBean.result;
				
				if (shoppingTrolleyList && shoppingTrolleyList.length > 0) {
					angular.forEach(shoppingTrolleyList, function(item, index) {
						// 默认选中
						item.checked = true;
						$scope.totalAmount += item.productPrice * 1 * item.number;
					});
				}
					
				$scope.shoppingTrolleyList = shoppingTrolleyList;
			}
		});
		
		// 删除购物车记录
		$scope.delShoppingTrolley = function() {
			var ids = [];
			var delShoppingTrolleyIndexs = [];
			angular.forEach($scope.shoppingTrolleyList, function(item, index) {
				if (item.checked) {
					ids.push(item.id);
					delShoppingTrolleyIndexs.push(index);
				}
			});
			
			if (ids.length <= 0) {
				alert("请选择需要删除的记录");
				return;
			}
			
			dialogService.showSelectionDialog({title:"提示",content:"确认删除？",ok:function() {
				shoppingTrolleyService.delShoppingTrolley(ids).then(function(response) {
					var resultBean = response.data;
					if (!resultBean) {
						alert("未知原因，删除地址失败！");
						return;
					}
					if ("0000" == resultBean.resultCode) {
						for (var i = delShoppingTrolleyIndexs.length - 1; i >= 0; i --) {
							// 删除页面数据
							$scope.shoppingTrolleyList.splice(delShoppingTrolleyIndexs[i], 1);
							// 计算合计
							calculateTotalCount();
						}
					} else {
						alert(resultBean.resultMessage);
					}
				});
			}});
		};
		
		// 选择
		$scope.checkItem = function(item) {
			item.checked = !(item.checked);
			// 计算合计
			calculateTotalCount();
		};
		
		// 加数量
		$scope.addNumber = function(item) {
			item.number += 1;
			calculateTotalCount();
		};
		
		// 减数量
		$scope.minusNumber = function(item) {
			var num = item.number - 1;
			if (num < 1) {
				item.number = 1;
			} else {
				item.number = num;
			}
			calculateTotalCount();
		};
		
		// 去结算
		$scope.toBalance = function() {
			if (!$scope.shoppingTrolleyList || $scope.shoppingTrolleyList.length <= 0) {
				alert("没有需要结算的商品");
				return;
			}
			
			var params = {};
			angular.forEach($scope.shoppingTrolleyList, function(item, index) {
				params["list[" + index + "].id"] = item.id;
				params["list[" + index + "].number"] = item.number;
			});
			
			// 更新购买数量到数据库
			shoppingTrolleyService.updateShoppingTrolleyList(params).then(function(response) {
				var resultBean = response.data;
				if ("0000" == resultBean.resultCode) {
					// 跳转页面结算页面
				} else {
					alert(resultBean.resultMessage);
				}
			});
		};
		
		// 计算合计
		function calculateTotalCount() {
			$scope.totalAmount = 0;
			angular.forEach($scope.shoppingTrolleyList, function(item, index) {
				if (item.checked) {
					$scope.totalAmount += item.productPrice * 1 * item.number;
				}
			});
		}
	} 
]);



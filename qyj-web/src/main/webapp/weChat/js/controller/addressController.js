var qyjApp = angular.module("qyjApp");

/**
 * 地址列表管理
 */
qyjApp.controller("addressCtrl", [ "$scope", "addressService",
    function($scope, addressService) {
		$scope.addressList = null;
		addressService.listAddress().then(function(response) {
			var resultBean = response.data;
			// 获取成功
			if ("0000" == resultBean.resultCode) {
				$scope.addressList = resultBean.result;
			}
		});
	} 
]);

/**
 * 编辑地址
 */
qyjApp.controller("addressEditCtrl", ["$scope", "$stateParams", "$state", "addressService", "dialogService",
    function($scope, $stateParams, $state, addressService, dialogService) {
		$scope.address = {};
		
		// id不为空就是编辑
		if ($stateParams.addressId != null && $stateParams.addressId != "") {
			$scope.title = "编辑地址";
			addressService.getAddressById($stateParams.addressId).then(function(response) {
				var resultBean = response.data;
				// 获取成功
				if ("0000" == resultBean.resultCode) {
					$scope.address = resultBean.result;
					$scope.address.area = resultBean.result.province + " " + resultBean.result.city + " " + resultBean.result.county;
				} else {
					alert(resultBean.resultMessage);
				}
			});
		} else {
			$scope.title = "新增地址";
		}
		
		// 保存地址
		$scope.saveAddress = function() {
			$scope.address.area = document.getElementById("addressArea").value;
			if ($scope.address.area == null || $scope.address.area == "") {
				alert("请输入省区市");
				return;
			}
			var areas = $scope.address.area.split(" ");
			$scope.address.province = areas[0];
			$scope.address.city = areas[1];
			$scope.address.county = areas[2];
			if ($scope.address.name == null || $scope.address.name == "") {
				alert("请输入名字");
				return;
			}
			if ($scope.address.phone == null || $scope.address.phone == "") {
				alert("请输入电话号码");
				return;
			}
			if ($scope.address.province == null || $scope.address.province == "") {
				alert("请选择省市区");
				return;
			}
			if ($scope.address.detail == null || $scope.address.detail == "") {
				alert("请输入详细地址");
				return;
			}
			if (!$scope.address.isDefault) {
				$scope.address.isDefault = false;
			}
			
			// 保存地址
			addressService.saveAddress($scope.address).then(function(response) {
				var resultBean = response.data;
				// 保存成功
				if ("0000" == resultBean.resultCode) {
					$state.go("address");
				} else {
					alert(resultBean.resultMessage);
				}
			});
		};
		
		// 删除地址
		$scope.delAddress = function() {
			dialogService.showSelectionDialog({title:"提示",content:"确认删除？",ok:function() {
				if (!$scope.address || !$scope.address.id) {
					alert("地址为空，删除失败！");
					return;
				}
				addressService.delAddressById($scope.address.id).then(function(response) {
					var resultBean = response.data;
					if (!resultBean) {
						alert("未知原因，删除地址失败！");
						return;
					}
					if ("0000" == resultBean.resultCode) {
						$state.go("address");
					} else {
						alert(resultBean.resultMessage);
					}
				});
			}});
		}
	} 
]);


var qyjApp = angular.module("qyjApp");

/**
 * 首页-登录页控制器
 */
qyjApp.controller("loginCtrl", [ "$scope", "$filter", "$state", "loginService", 
    function($scope, $filter, $state, loginService) {
		// 提示信息
		$scope.tipMsg = "";
		// 登录参数
		$scope.loginParam = {phoneNum : "", password : ""};
		
		// 登录
		$scope.doLogin = function() {
			var phoneNum = $scope.loginParam.phoneNum;
			if (phoneNum == null || phoneNum == "") {
				$scope.tipMsg = "请输入手机号码！";
				return;
			}
			if (!$filter('phoneNumFilter')($scope.loginParam.phoneNum)) {
				$scope.tipMsg = "手机号码格式不正确！";
				return;
			}
			var password = $scope.loginParam.password;
			if (password == null || password == "") {
				$scope.tipMsg = "请输入登录密码！";
				return;
			}
			// 执行登录
			loginService.doLogin($scope.loginParam).then(function(response) {
				var resultBean = response.data;
				if (!resultBean) {
					$scope.tipMsg = "登录失败！";
				}
				// 登录成功
				if ("0000" == resultBean.resultCode) {
					// 跳转，页面，参数
					$state.go('home.account', {})
					return;
				}
				$scope.tipMsg = resultBean.resultMessage;
			});
		};
	} 
]);



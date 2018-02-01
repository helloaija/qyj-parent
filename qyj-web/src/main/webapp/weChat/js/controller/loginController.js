
var qyjApp = angular.module("qyjApp");

/**
 * 首页-登录页控制器
 */
qyjApp.controller("loginCtrl", [ "$scope", "$filter", "$state", "$interval", "loginService", 
    function($scope, $filter, $state, $interval, loginService) {
		// 提示信息
		$scope.tipMsg = "";
		// 登录参数
		$scope.loginParam = {phoneNum : "", loginCode : ""};
		// 获取验证按钮显示文字
		$scope.codeBtnText = "获取验证码";
		
		// 倒计时
		var interval = null;
		
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
			var loginCode = $scope.loginParam.loginCode;
			if (loginCode == null || loginCode == "") {
				$scope.tipMsg = "请输入验证码！";
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
					if (history.length > 2) {
						history.back(-1);
					} else {
						$state.go("home.account", {}, {location:'replace'});
					}
					return;
				}
				$scope.tipMsg = resultBean.resultMessage;
			});
		};
		
		/**
		 * 按钮倒计时
		 */
		function btnCountDown(seconds) {
			if (seconds <=0) {
				return;
			}
			$scope.codeBtnText = seconds + "秒";
			var interval = $interval(function() {
				seconds -= 1;
				$scope.codeBtnText = seconds + "秒";
				if (seconds >= 0 && seconds < 1) {
					$scope.codeBtnText = "获取验证码";
					$interval.cancel(interval);
					return;
				}
			}, 1000);
		}
		
		/**
		 * 获取登录验证码
		 */
		$scope.getLoginCode = function() {
			if ("获取验证码" != $scope.codeBtnText) {
				$scope.tipMsg = "请在三分钟之后再获取";
				return;
			}
			
			btnCountDown(3 * 60);
			
			// 发送短信验证码
			loginService.getLoginCode($scope.loginParam.phoneNum, $scope.loginParam.loginCode).then(function(response) {
				var resultBean = response.data;
				if ("0000" != resultBean.resultCode) {
					$interval.cancel(interval);
					$scope.codeBtnText = "获取验证码";
					$scope.tipMsg = resultBean;
				}
			});
		};
	} 
]);



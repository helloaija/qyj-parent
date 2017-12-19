var qyjBackApp = angular.module("qyjBackApp");

qyjBackApp.controller("loginCtrl", loginCtrl);

loginCtrl.$inject = ["$scope", "$state", "loginService"];
function loginCtrl($scope, $state, loginService) {
	$scope.doLogin = function() {
		// 执行登陆
		loginService.doLogin({userName : $scope.userName, password : $scope.password}).then(function(response) {
			var responseData = response.data;
			if (responseData.resultCode == "0000") {
				// 登陆成功
				$state.go("home");
			} else {
				$scope.errorText = responseData.resultMessage;
			}
		});
	}
};

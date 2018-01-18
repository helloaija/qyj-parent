
var qyjApp = angular.module("qyjApp");

/**
 * 首页-用户账户
 */
qyjApp.controller("accountController", ["$rootScope", "$scope", "accountService", 
    function($rootScope, $scope, accountService) {
		$rootScope.homeIndex = 4;
		accountService.getLoginUserInfo().then(function(response) {
			var resultBean = response.data;
			if (resultBean && resultBean.result) {
//				alert(resultBean.result.phoneNum);
			}
		});
	} 
]);



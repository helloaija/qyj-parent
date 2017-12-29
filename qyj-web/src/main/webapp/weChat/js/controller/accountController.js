
var qyjApp = angular.module("qyjApp");

/**
 * 首页-用户账户
 */
qyjApp.controller("accountController", [ "$scope", "accountService", 
    function($scope, accountService) {
		accountService.getLoginUserInfo().then(function(response) {
			var resultBean = response.data;
			if (resultBean && resultBean.result) {
				alert(resultBean.result.phoneNum);
			}
		});
	} 
]);



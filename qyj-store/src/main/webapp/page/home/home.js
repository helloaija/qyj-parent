var qyjStoreApp = angular.module("qyjStoreApp");

qyjStoreApp.controller("homeCtrl", ["$scope", "$http", "$state",
	function($scope, $http, $state) {
		$scope.user = {};
		$scope.menu = {};

		$http({  
	        method: "GET",  
	        url: qyjStoreApp.httpsHeader + "/admin/login/getLoginInfo"
	    }).then(function(response) {
	    	var resultBean = response.data;
	    	if ("0000" == resultBean.resultCode) {
	    		$scope.user = resultBean.result.userBean;
	    		var menuList = resultBean.result.menuList;
	    		if (menuList != null && menuList.length > 0) {
                    for (var i = 0; i < menuList.length; i ++) {
                    	var menu = menuList[i];
                    	$scope.menu[menu.menuCode] = true;
					}
				}
	    	}
	    });

		// 退出登录
		$scope.logOut = function() {
            $http({
                method: "GET",
                url: qyjStoreApp.httpsHeader + "/admin/login/logOut"
            }).then(function(response) {
                var resultBean = response.data;
                if ("0000" == resultBean.resultCode) {
                    $state.go('login');
                }
            });
		}
	}
]);

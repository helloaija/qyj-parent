var qyjStoreApp = angular.module("qyjStoreApp");

qyjStoreApp.controller("homeCtrl", ["$scope", "$http",
	function($scope, $http) {
		$scope.user = {};
		$http({  
	        method: "GET",  
	        url: qyjStoreApp.httpsHeader + "/admin/login/getLoginInfo"
	    }).then(function(response) {
	    	var resultBean = response.data;
	    	if ("0000" == resultBean.resultCode) {
	    		$scope.user = resultBean.result;
	    	}
	    });
	}
]);

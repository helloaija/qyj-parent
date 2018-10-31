var qyjStoreApp = angular.module("qyjStoreApp");

qyjStoreApp.service('loginService', ["$http",
    function($http) {
	    this.doLogin = function (data) {
	    	// 登陆
	        return $http({  
	            method: "POST",  
	            url: qyjStoreApp.httpsHeader + "/admin/login/doLogin",
	            params : data
	        });
	    }
	}
]);

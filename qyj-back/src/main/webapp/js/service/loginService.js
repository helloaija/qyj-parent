var qyjBackApp = angular.module("qyjBackApp");

qyjBackApp.service('loginService', ["$http",
    function($http) {
	    this.doLogin = function (data) {
	    	// 登陆
	        return $http({  
	            method: "POST",  
	            url: qyjBackApp.httpsHeader + "/admin/login/doLogin",  
	            params : data
	        });
	    }
	}
]);

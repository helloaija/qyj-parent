var qyjBackApp = angular.module("qyjBackApp");

var httpsHeader = "http://localhost:8082/qyj-back";
qyjBackApp.service('loginService', ["$http",
    function($http) {
	    this.doLogin = function (data) {
	    	// 登陆
	        return $http({  
	            method: "POST",  
	            url: httpsHeader + "/admin/login/doLogin",  
	            params : data
	        });
	    }
	}
]);

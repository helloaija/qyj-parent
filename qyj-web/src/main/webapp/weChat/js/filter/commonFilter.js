

var qyjApp = angular.module("qyjApp");

qyjApp.filter("phoneNumFilter", function() {
	// 手机号码校验
	return function(phoneNum) {  
        var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;  
        if (!myreg.test(phoneNum)) {  
            return false;  
        } else {  
            return true;  
        }  
    }  
});
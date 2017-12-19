
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

/**
 * 产品服务器
 */
qyjBackApp.service('productService', ["$http",
    function($http) {
		// 请求获取产品分页数据
	    this.loadProductList = function (data) {
	        return $http({  
	            method: "POST",  
	            url: httpsHeader + "/admin/product/listProductPage",  
	            params : data
	        });
	    }
	    
	    // 根据主键查询产品信息
	    this.getProductInfo = function (productId) {
	        return $http({  
	            method: "POST",  
	            url: httpsHeader + "/admin/product/getProductInfo",  
	            params : {productId : productId}
	        });
	    }
	    
	    // 保存新增、编辑的产品信息
	    this.saveProductInfo = function (productInfo) {
	        return $http({  
	            method: "POST",  
	            url: httpsHeader + "/admin/product/saveProductInfo",  
	            params : productInfo
	        });
	    }
	    
	    // 根据主键删除产品信息
	    this.delProductInfo = function (productId) {
	        return $http({  
	            method: "POST",  
	            url: httpsHeader + "/admin/product/delProductInfo",  
	            params : {productId : productId}
	        });
	    }
	    
	    // 根据主键删除产品信息
	    this.uploadImage = function (data) {
	        return $http({  
	            method: "POST",
	            "Content-Type" : "multipart/form-data",
	            url: httpsHeader + "/admin/product/uploadImage",  
	            params : data,
	            file: data.image
	        });
	    }
	}
]);


var qyjStoreApp = angular.module("qyjStoreApp");

/**
 * 产品服务器
 */
qyjStoreApp.service('productService', ["$http",
    function($http) {
		// 请求获取产品分页数据
	    this.loadProductList = function (data) {
	        return $http({  
	            method: "GET",  
	            url: qyjStoreApp.httpsHeader + "/admin/product/listProductPage",
	            params : data
	        });
	    }
	    
	    // 根据主键查询产品信息
	    this.getProductInfo = function (productId) {
	        return $http({  
	            method: "GET",  
	            url: qyjStoreApp.httpsHeader + "/admin/product/getProductInfo",
	            params : {productId : productId}
	        });
	    }
	    
	    // 保存新增、编辑的产品信息
	    this.saveProductInfo = function (productInfo) {
	        return $http({  
	            method: "POST",  
	            url: qyjStoreApp.httpsHeader + "/admin/product/saveProductInfo",
	            params : productInfo
	        });
	    }
	    
	    // 根据主键删除产品信息
	    this.delProductInfo = function (productId) {
	        return $http({  
	            method: "POST",  
	            url: qyjStoreApp.httpsHeader + "/admin/product/delProductInfo",
	            params : {productId : productId}
	        });
	    }
	    
	    // 根据主键删除产品信息
	    this.uploadImage = function (data) {
	        return $http({  
	            method: "POST",
	            "Content-Type" : "multipart/form-data",
	            url: qyjStoreApp.httpsHeader + "/admin/product/uploadImage",
	            params : data,
	            file: data.image
	        });
	    }
	    
	    // 更新产品状态
	    this.updateProductStatus = function (data) {
	    	return $http({  
	            method: "POST",  
	            url: qyjStoreApp.httpsHeader + "/admin/product/updateProductStatus",
	            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	            data : data,
	            transformRequest: function(obj) {
	                var str = [];    
	                for (var s in obj) {
	                  str.push(encodeURIComponent(s) + "=" + encodeURIComponent(obj[s]));    
	                }    
	                return str.join("&");
	              }
	        });
	    }
	}
]);
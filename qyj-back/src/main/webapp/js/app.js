define(["angular", "angular-ui-router", "oclazyload"], function() {
	var qyjBackApp = angular.module("qyjBackApp", ["oc.lazyLoad", "ui.router"]);
	
	qyjBackApp.httpsHeader = "http://localhost:8082/qyj-back";
	
	qyjBackApp.config(["$stateProvider", "$httpProvider",
        function($stateProvider, $httpProvider) {
	   		// 定义路由
	   		$stateProvider.state('login', {
	   			url : "/login",
	   			templateUrl : "login/login.html",
	   			controller : "loginCtrl",
	   			resolve : {
	   				load : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(['../js/controller/loginController.js',
	   					                      '../js/service/loginService.js']);
	   				}]
	   	        }
	   		}).state('home', {
	   			url : "/home",
	   			templateUrl : "../page/home/home.html"
	   		}).state('home.newsList', {
	   			url : "/newsList",
	   			templateUrl : '../page/news/newsManage.html',
	   			controller : "newsCtrl",
	   			resolve : {
	   				newsCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load([
							"ui.grid", "ui.bootstrap", "ueditor",
							"../js/filter/filters.js", "../js/service/commonServices.js",
							"../js/controller/newsController.js", "../js/service/newsService.js"
						]);
	   				}]
	   			}
	   		}).state('home.productList', {
	   			url : "/productList",
	   			templateUrl : '../page/product/productManage.html',
	   			controller : "productManageCtrl",
	   			resolve : {
	   				load : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load([
                            "ui.grid", "ui.bootstrap", "ng-file-upload", "ueditor",
                            "../js/directive/commonDirectives.js",
                            "../js/filter/filters.js", '../js/service/commonServices.js',
                            '../js/service/productService.js', '../js/controller/productController.js'
	   					]);
	   				}]
	   	        }
	   		});
	   		
	   		// 定义请求过滤器
	   		$httpProvider.interceptors.push(["$q", "$location",
	               function($q, $location) {
	   				return {
	   					'request' : function(config) {
	   						config.headers['X-Requested-With'] = 'XMLHttpRequest';
	   						return config || $q.when(config);
	   					},
	   					'requestError' : function(rejection) {
	   						return rejection;
	   					},
	   					'response' : function(response) {
	   						return response || $q.when(response);
	   					},
	   					'responseError' : function(response) {
	   						console.log('responseError:' + response);
	   						if (response.status === 401 || response.status === 403) {
	   							alert("登录过期，请重新登录！");
	   							$location.path("/login");
	   						} else if (response.status === 500) {
	               				$location.path('/500.html');
	   						}
	   						return $q.reject(response);
	   					}
	   				}
	   			}
	   		]);
	   	}
	]);
	
	qyjBackApp.config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
	   $ocLazyLoadProvider.config({
	       modules: [{
	    	   name : "ueditor",
	    	   files : ["../ueditor-1.4.3/ueditor.all.js",
						"../ueditor-1.4.3/ueditor.config.js"]
	       }, {
	    	   name : "ui.grid",
	    	   files : ["../js/base/ui-grid-4.0.8.min.js"]
	       }, {
	    	   name : "ui.bootstrap",
	    	   files : ["../js/base/ui-bootstrap-tpls-2.5.0.min.js"]
	       }, {
	    	   name : "ng-file-upload",
	    	   files : ["../js/base/ng-file-upload.min.js",
                        "../js/base/ng-file-upload-shim.min.js",]
	       }],
	       debug: true
	   })
	}]);
});





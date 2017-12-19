
define(["angular", "angular-ui-router", "oclazyload", "angular-sanitize"], function() {
	var qyjApp = angular.module("qyjApp", ["oc.lazyLoad", "ui.router", "ngSanitize"]);
	
	qyjApp.httpsHeader = "http://localhost:8082/qyj-web";
	
	qyjApp.config(["$stateProvider", "$httpProvider",
        function($stateProvider, $httpProvider) {
	   		// 定义路由
	   		$stateProvider.state("home", {
	   			url : "/home",
	   			templateUrl : "../page/home.html"
	   		}).state("home.productList", {
	   			url : "/productList",
	   			// 产品列表页面
	   			templateUrl : "../page/product/productList.html",
	   			controller : "productCtrl",
	   			resolve : {
	   				productCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(['../js/controller/productController.js',
	   					                      '../js/service/productService.js']);
	   				}]
	   	        }
	   		}).state("home.newsList", {
	   			url : "/newsList", 
	   			// 新闻公告列表页面
	   			templateUrl : "../page/news/newsList.html",
	   			controller : "newsCtrl",
	   			resolve : {
	   				productCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(['../js/controller/newsController.js',
	   					                      '../js/service/newsService.js']);
	   				}]
	   			}
	   		}).state("newsContent", {
	   			url : "/newsContent?newsInfoId",
	   			// 新闻公告内容
	   			templateUrl : "../page/news/newsContent.html",
	   			controller : "newsContentCtrl",
	   			resolve : {
	   				newsContentCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(['../js/controller/newsController.js',
	   					                      '../js/service/newsService.js']);
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
	
	qyjApp.config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
	   $ocLazyLoadProvider.config({
	       modules: [{
	    	   events: true,
	    	   name : "angular-sanitize",
	    	   files : ["../js/base/angular-sanitize.min.js"]
	       }],
	       debug: true
	   })
	}]);
});




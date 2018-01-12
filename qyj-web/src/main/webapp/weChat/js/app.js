
define(["angular", "angular-ui-router", "oclazyload", "angular-sanitize", "angular-animate", "angular-touch"], function(angular) {
	var qyjApp = angular.module("qyjApp", ["oc.lazyLoad", "ui.router", "ngSanitize", "ngAnimate", "ngTouch"]);
	
	qyjApp.httpsHeader = "http://192.168.30.22:8082/qyj-web";
	qyjApp.uploadFileHeader = "http://192.168.30.22:8082/qyj-back/uploadFile/";
	qyjApp.uploadHeader = "http://192.168.30.22:8082/qyj-back/upload/";
	
	qyjApp.config(["$stateProvider", "$httpProvider", "$urlRouterProvider",
        function($stateProvider, $httpProvider, $urlRouterProvider) {
			$urlRouterProvider.when('', '/home/index');
	   		// 定义路由
	   		$stateProvider.state("home", {
	   			url : "/home",
	   			templateUrl : "weChat/page/home.html"
	   		}).state("home.index", {
	   			url : "/index",
	   			// 产品列表页面
	   			templateUrl : "weChat/page/home/homeContent.html",
	   			controller : "homeCtrl",
	   			resolve : {
	   				homeCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(['weChat/js/controller/homeController.js']);
	   				}]
	   	        }
	   		}).state("home.productList", {
	   			url : "/productList",
	   			// 产品列表页面
	   			templateUrl : "weChat/page/product/productList.html",
	   			controller : "productCtrl",
	   			resolve : {
	   				productCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(["ng-infinite-scroll", 'weChat/js/controller/productController.js',
	   					                      'weChat/js/service/productService.js']);
	   				}]
	   	        }
	   		}).state("home.newsList", {
	   			url : "/newsList", 
	   			// 新闻公告列表页面
	   			templateUrl : "weChat/page/news/newsList.html",
	   			controller : "newsCtrl",
	   			resolve : {
	   				newsCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(["ng-infinite-scroll", 'weChat/js/controller/newsController.js',
	   					                      'weChat/js/service/newsService.js']);
	   				}]
	   			}
	   		}).state("home.account", {
	   			url : "/account", 
	   			// 新闻公告列表页面
	   			templateUrl : "weChat/page/account/accountIndex.html",
	   			controller : "accountController",
	   			resolve : {
	   				accountController : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(['weChat/js/controller/accountController.js',
	   					                         "weChat/js/service/accountService.js"]);
	   				}]
	   			}
	   		}).state("newsContent", {
	   			url : "/newsContent?newsInfoId",
	   			// 新闻公告内容
	   			templateUrl : "weChat/page/news/newsContent.html",
	   			controller : "newsContentCtrl",
	   			resolve : {
	   				newsContentCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(['weChat/js/controller/newsController.js',
	   					                      'weChat/js/service/newsService.js']);
	   				}]
	   			}
	   		}).state("productContent", {
	   			url : "/productContent?productId",
	   			// 产品内容
	   			templateUrl : "weChat/page/product/productContent.html",
	   			controller : "productContentCtrl",
	   			resolve : {
	   				productContentCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(["ui-bootstrap",
	   											'weChat/js/controller/productContentController.js',
	   											'weChat/js/service/productContentService.js']);
	   				}]
	   			}
	   		}).state("login", {
	   			url : "/login",
	   			// 登录页
	   			templateUrl : "weChat/page/login/login.html",
	   			controller : "loginCtrl",
	   			resolve : {
	   				loginCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(["weChat/js/filter/commonFilter.js",
	   					                         'weChat/js/controller/loginController.js',
	   					                         "weChat/js/service/loginService.js"
	   					                         ]);
	   				}]
	   	        }
	   		}).state("address", {
	   			url : "/address?isSelect",
	   			// 登录地址列表
	   			templateUrl : "weChat/page/account/addressList.html",
	   			controller : "addressCtrl",
	   			resolve : {
	   				addressCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(["weChat/js/service/addressService.js", 'weChat/js/controller/addressController.js']);
	   				}]
	   			}
	   		}).state("addressEdit", {
	   			url : "/addressEdit?addressId",
	   			// 地址编辑
	   			templateUrl : "weChat/page/account/addressEdit.html",
	   			controller : "addressEditCtrl",
	   			resolve : {
	   				addressEditCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(["ui-bootstrap", 'weChat/js/directive/directives.js', "weChat/js/service/commonServices.js",
	   					                         "weChat/js/service/addressService.js", 'weChat/js/controller/addressController.js']);
	   				}]
	   	        }
	   		}).state("shoppingTrolley", {
	   			url : "/shoppingTrolley",
	   			// 购物车
	   			templateUrl : "weChat/page/account/shoppingTrolley.html"
	   		}).state("orderList", {
	   			url : "/orderList",
	   			// 订单列表
	   			templateUrl : "weChat/page/account/orderList.html"
	   		}).state("confirmOrder", {
	   			url : "/confirmOrder?productId",
	   			// 确认订单页面
	   			templateUrl : "weChat/page/product/confirmOrder.html",
	   			controller : "confirmOrderCtrl",
	   			resolve : {
	   				addressEditCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(["weChat/js/service/confirmOrderService.js", 'weChat/js/controller/confirmOrderController.js']);
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
	    	   files : ["weChat/js/base/angular-sanitize.min.js"]
	       }, {
	    	   events: true,
	    	   name : "angular-animate",
	    	   files : ["weChat/js/base/angular-animate-1.6.7.min.js"]
	       }, {
	    	   events: true,
	    	   name : "ui-bootstrap",
	    	   files : ["weChat/js/base/ui-bootstrap.min.js",
	    	            "weChat/js/base/ui-bootstrap-tpls-2.5.0.min.js"]
	       }, {
	    	   // 滚动加载
	    	   name : "ng-infinite-scroll",
	    	   files : ["weChat/js/base/ng-infinite-scroll-1.0.0.min.js"]
	       }],
	       debug: true
	   })
	}]);
});





define(["angular", "angular-ui-router", "oclazyload", "angular-sanitize"], function() {
	var qyjStoreApp = angular.module("qyjStoreApp", ["oc.lazyLoad", "ui.router", "ngSanitize"/*,
		'ui.grid.pagination', 'ui.grid', 'ui.grid.expandable'*/]);
	
	qyjStoreApp.httpsHeader = "http://192.168.30.22:8083/qyj-store";
	qyjStoreApp.uploadFileHeader = "http://192.168.30.22:8083/qyj-store/uploadFile/";
	
	qyjStoreApp.config(["$urlRouterProvider", "$stateProvider", "$httpProvider",
        function($urlRouterProvider, $stateProvider, $httpProvider) {
			// 默认调到登录页
			$urlRouterProvider.when('', '/login');
	   		// 定义路由
	   		$stateProvider.state('login', {
	   			url : "/login",
	   			// 登录页
	   			templateUrl : "page/login/login.html",
	   			controller : "loginCtrl",
	   			resolve : {
	   				load : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load(['js/controller/loginController.js',
	   					                      'js/service/loginService.js']);
	   				}]
	   	        }
	   		}).state('home', {
	   			url : "/home",
	   			// 首页
	   			templateUrl : "page/home/home.html",
	   			controller : "homeCtrl",
	   			resolve : {
	   				homeCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load([
	   					                         "ui.bootstrap", "page/home/home.js"
						]);
	   				}]
	   			}
	   		}).state('home.storeInfo', {
	   			url : "/storeInfo",
	   			// 新闻管理
	   			templateUrl : 'page/store/storeInfo.html',
	   			controller : "orderInfoCtrl",
	   			resolve : {
	   				newsCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load([
							"ui.grid", "ui.bootstrap", "ueditor",
							"js/filter/filters.js", "js/service/commonServices.js",
							"page/store/storeInfo.js"
						]);
	   				}]
	   			}
	   		}).state('home.productList', {
	   			url : "/productList",
	   			// 产品管理
	   			templateUrl : 'page/product/productManage.html',
	   			controller : "productManageCtrl",
	   			resolve : {
	   				load : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load([
                            "ui.grid", "ui.bootstrap", "ng-file-upload", "ueditor",
                            "js/filter/filters.js", 'js/service/commonServices.js',
                            'js/service/productService.js', 'js/controller/productController.js'
	   					]);
	   				}]
	   	        }
	   		}).state('home.sellOrder', {
	   			url : "/sellOrder",
	   			// 销售单
	   			templateUrl : 'page/sellOrder/sellOrder.html',
	   			controller : "sellOrderCtrl",
	   			resolve : {
	   				orderListCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load([
                            "WdatePicker", "ui-select", "ui-grid-stable", "ui.bootstrap", "js/filter/filters.js",
							'js/service/commonServices.js', 'page/sellOrder/sellOrder.js'
	   					]);
	   				}]
	   	        }
	   		}).state('home.stockOrder', {
                url : "/stockOrder",
                // 进货订单管理
                templateUrl : 'page/stockOrder/stockOrder.html',
                controller : "stockOrderCtrl",
                resolve : {
                    orderListCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            "WdatePicker", "ui-select", "ui-grid-stable", "ui.bootstrap", "js/filter/filters.js", 'js/service/commonServices.js',
                            'page/stockOrder/stockOrder.js'
                        ]);
                    }]
                }
            }).state('home.menu', {
	   			url : "/menu",
	   			// 系统菜单管理
	   			templateUrl : 'page/system/menu/menu.html',
	   			controller : "menuCtrl",
	   			resolve : {
	   				orderListCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load([
                            "tree-control", "js/service/commonServices.js", 'page/system/menu/menu.js'
	   					]);
	   				}]
	   	        }
	   		}).state('home.role', {
	   			url : "/role",
	   			// 角色管理
	   			templateUrl : 'page/system/role/role.html',
	   			controller : "roleCtrl",
	   			resolve : {
	   				orderListCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load([
   					        "ui.grid", "ui.bootstrap", 'js/service/commonServices.js', "tree-control", 'page/system/role/role.js'
	   					]);
	   				}]
	   	        }
	   		}).state('home.user', {
	   			url : "/user",
	   			// 用户管理
	   			templateUrl : 'page/system/user/user.html',
	   			controller : "userCtrl",
	   			resolve : {
	   				orderListCtrl : ['$ocLazyLoad', function($ocLazyLoad) {
	   					return $ocLazyLoad.load([
                            'page/system/user/user.js'
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
	
	qyjStoreApp.config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
	   $ocLazyLoadProvider.config({
	       modules: [{
	    	   name : "ueditor",
	    	   files : ["ueditor-1.4.3/ueditor.all.js",
						"ueditor-1.4.3/ueditor.config.js"]
	       }, {
	    	   // 数据列表
	    	   name : "ui.grid",
	    	   files : ["js/base/ui-grid-4.0.8.js"]
	       }, {
	    	   // ui-bootstrap组件
	    	   name : "ui.bootstrap",
	    	   files : ["js/base/ui-bootstrap-tpls-2.5.0.min.js"]
	       }, {
	    	   // 文件上传
	    	   name : "ng-file-upload",
	    	   files : ["js/base/ng-file-upload.min.js",
                        "js/base/ng-file-upload-shim.min.js",]
	       }, {
	    	   // 组件树
	    	   name : "tree-control",
	    	   files : ["css/tree-control-attribute.css", "css/tree-control.css", "js/base/angular-tree-control.js"]
	       }, {
               // 组件树
               name : "ui-grid-stable",
               files : ["js/base/ui-grid-stable.js"]
           }, {
               // 搜索选择
               name : "ui-select",
               files : ["css/select.min.css", "js/base/select.min.js"]
           }, {
               // 时间空间
               name : "WdatePicker",
               files : ["js/base/My97DatePicker/WdatePicker.js"]
           }],
	       debug: true
	   })
	}]);
});





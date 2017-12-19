var qyjBackApp = angular.module("qyjBackApp", ["ui.router", "ui.grid", "ui.grid.pagination", "ui.grid.selection", "ui.bootstrap", "ngFileUpload"]);

qyjBackApp.config(["$stateProvider", "$httpProvider",
    function($stateProvider, $httpProvider) {
		// 定义路由
		$stateProvider.state('login', {
			url : "/login",
			templateUrl : "../page/login/login.html",
			controller : "loginCtrl"
		}).state('home', {
			url : "/home",
			templateUrl : "../page/home/home.html"
		}).state('home.newsList', {
			url : "/newsList",
			template : '这是新闻列表页面'
		}).state('home.productList', {
			url : "/productList",
			templateUrl : '../page/product/productManage.html',
			controller : "productManageCtrl"
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
//							$location.path('/500.html');
						}
						return $q.reject(response);
					}
				}
			}
		]);
	}
]);
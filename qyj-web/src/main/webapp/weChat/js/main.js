require.config({
	// 配置angular的路径
	paths : {
		"jquery.slim" : "base/jquery.slim.min",
		"popper" : "base/popper.min",
		"angular" : "base/angular-1.6.7.min",
		"angular-ui-router" : "base/angular-ui-router-0.4.3.min",
		"oclazyload" : "base/ocLazyLoad-1.1.0.min",
		"angular-sanitize" : "base/angular-sanitize-1.6.7.min",
		"angular-animate" : "base/angular-animate-1.6.7.min",
		"angular-touch" : "base/angular-touch.min"
	},
	// 这个配置是你在引入依赖的时候的包名
	shim : {
		"jquerySlim" : {
			exports : "jquery.slim"
		},
		"popper" : {
			exports : "popper"
		},
		"angular" : {
			exports : "angular"
		},
		"angular-ui-router" : {
			deps: ["angular"],
			exports : "angular-ui-router"
		},
		'oclazyload': {
			deps: ['angular'],
			exports : "oclazyload"
	    },
	    "angular-sanitize" : {
	    	deps: ['angular'],
			exports : "angular-sanitize"
	    },
	    "angular-animate" : {
	    	deps: ['angular'],
			exports : "angular-animate"
	    },
	    "angular-touch" : {
	    	deps: ['angular'],
			exports : "angular-touch"
	    }
	},
	urlArgs: "bust=" + (new Date()).getTime()
});

define(['angular', 'app'], function(angular, app) {
	'use strict';
	// 使用bootstrap方法启动Angular应用 
	angular.element(document).ready(function() {
		console.log("angular.bootstrap...");
		angular.bootstrap(document, ['qyjApp']);
	});
});


console.log("filter js load...");

var qyjBackApp = angular.module("qyjBackApp");

qyjBackApp.filter("productTypeFilter", function() {
	// 产品类型过滤器
	return function(value) {
		var productTypeMap = {'PESTICIDE': '农药', 'MANURE': '化肥'};
		return productTypeMap[value];
	}
}).filter("productStatusFilter", function() {
	// 产品状态过滤器
	return function(value) {
		var productTypeMap = {'PUBLISH': '发布', 'PUTAWAY': '上架', "SOLDOUT" : "下架"};
		return productTypeMap[value];
	}
}).filter("newsTypeFilter", function() {
	// 新闻公告类型
	return function(value) {
		var newsTypeMap = {'NEWS': '新闻', 'TECHNIQUE': '技术支持', "NOTICE" : "通知公告"};
		return newsTypeMap[value];
	}
}).filter("newsStatusFilter", function() {
	// 新闻公告发布状态
	return function(value) {
		var newsStatusMap = {'PUBLISH': '发布', 'PUTAWAY': '上架', "SOLDOUT" : "下架"};
		return newsStatusMap[value];
	}
});
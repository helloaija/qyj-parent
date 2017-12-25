

var qyjBackApp = angular.module("qyjBackApp");

qyjBackApp.service("newsService", newsService);

function newsService($http) {
	// 请求获取新闻通知分页数据
	this.loadNewsInfoList = function(data) {
		return $http({
			method : "POST",
			url : qyjBackApp.httpsHeader + "/admin/news/listNewsInfoPage",
			params : data
		});
	};
	
	// 根据主键删除新闻通知信息
    this.delNewsInfo = function (newsId) {
        return $http({  
            method: "POST",  
            url: qyjBackApp.httpsHeader + "/admin/news/delNewsInfo",  
            params : {newsInfoId : newsId}
        });
    };
	
    // 根据主键查询新闻通知信息
    this.getNewsInfo = function (newsId) {
        return $http({  
            method: "GET",  
            url: qyjBackApp.httpsHeader + "/admin/news/getNewsInfo",  
            params : {newsInfoId : newsId}
        });
    }
    
    // 保存新增、编辑的新闻通知信息
    this.saveNewsInfo = function (newsInfo) {
        return $http({  
            method: "POST",  
            url: qyjBackApp.httpsHeader + "/admin/news/saveNewsInfo",
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            data : newsInfo,
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
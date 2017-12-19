
var qyjApp = angular.module("qyjApp");

/**
 * 产品详情控制器
 */
qyjApp.controller("productContentCtrl", [ "$scope", "$stateParams", "productContentService",
    function($scope, $stateParams, productContentService) {
   		// 获取产品列表信息
		productContentService.getProductInfo($stateParams.productId).then(function(response) {
   			var resultBean = response.data;
   			// 加载产品列表
   			if (resultBean.resultCode == "0000" && resultBean.result) {
	   			$scope.product = resultBean.result;
	   		}
	   	});
		
		$scope.slideInterval = 5000;
		
		$scope.slideList = [{ image: 'https://img13.360buyimg.com/imgzone/jfs/t5845/159/5846779009/58997/2318fdd3/5966352bN17ac59b2.jpg', 
			text: '亲爱的你，情人节快乐', id:0 },
		             { image: 'https://img13.360buyimg.com/imgzone/jfs/t5845/159/5846779009/58997/2318fdd3/5966352bN17ac59b2.jpg', 
				text: '亲爱的你，情人节快乐', id:1 }];
		
		
	} 
]);

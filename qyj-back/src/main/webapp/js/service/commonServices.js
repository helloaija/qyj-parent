
var qyjBackApp = angular.module("qyjBackApp");

/**
 * 对话框service
 */
qyjBackApp.controller('tipDialogCtrl', function ($scope, $uibModalInstance, items) {
	// 标题
	$scope.title = items.title;
	// 提示内容
	$scope.tipContent = items.content;
	
	// 确认
	$scope.ok = function() {
		items.ok();
		$uibModalInstance.close();
	};

	// 取消
	$scope.cancel = function() {
		console.log("closeWin...");
		$uibModalInstance.dismiss('cancel');
	};
}).service('tipDialogService', ["$uibModal", 
    function($uibModal) {
	    this.showDialog = function (item) {
	    	var modalInstance = $uibModal.open({
	            templateUrl : '../page/common/tipDialog.html',
	            controller : 'tipDialogCtrl', // specify controller for modal
	            size : "md",
	            resolve : {
	                items : function() {
	                    return item;
	                }
	            }
	        });
	    	
	        modalInstance.result.then(function() {
	        	// 调用$uibModalInstance.close();后触发
	        	console.log("ok");
	        }, function() {
	        	// 调用$uibModalInstance.dismiss('cancel');后触发
	        	console.log("cancel");
	        });
	        
	        return modalInstance;
	    }
	}
]);



var qyjBackApp = angular.module("qyjBackApp");

/**
 * 对话框service
 */
qyjBackApp.controller('tipDialogCtrl', function ($scope, $uibModalInstance, items) {
	// 标题
	$scope.title = items.title;
	// 提示内容
	$scope.tipContent = items.content;
	// 按钮
	$scope.buttons = items.buttons;
	
	// 确认
	$scope.ok = function() {
		if (angular.isFunction(items.ok)) {
			items.ok();
		}
		
		$uibModalInstance.close();
	};

	// 取消
	$scope.cancel = function() {
		console.log("closeWin...");
		$uibModalInstance.dismiss('cancel');
	};
}).service('tipDialogService', ["$uibModal", 
    function($uibModal) {
		// 打开选择对话框
	    this.showDialog = function (item) {
	    	// 显示确认和取消按钮
	    	item.buttons = {ok : true, cancel : true};
	    	openDialog(item);
	    };
	    
	    // 打开提示对话框
	    this.showPromptDialog = function(msg) {
	    	var item = {};
	    	item.title = "提示";
	    	item.content = msg;
	    	// 显示确认按钮
	    	item.buttons = {ok : true, cancel : false};
	    	item.ok = null;
	    	openDialog(item);
	    }
	    
	    // 弹出对话框方法
	    function openDialog(item) {
	    	var modalInstance = $uibModal.open({
	            templateUrl : 'page/common/tipDialog.html',
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


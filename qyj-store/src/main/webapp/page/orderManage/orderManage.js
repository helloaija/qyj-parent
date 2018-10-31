var qyjStoreApp = angular.module("qyjStoreApp");

/**
 * 控制器-订单列表
 */
qyjStoreApp.controller("orderListCtrl", ["$scope", "$filter", "$uibModal", "orderService",
    function($scope, $filter, $uibModal, orderService) {
		$scope.orderStatusList = [{"value" : "UNPAY","name":"待支付"},{"value" : "UNSEND","name":"待发货"},{"value" : "UNTAKE","name":"待收货"},
			{"value" : "END","name":"已结束"},{"value" : "CANCEL","name":"取消订单"}];
		// 列表属性
		$scope.gridOptions = {
			data : 'myData',
			columnDefs : [
			{
				field : 'rowIndex',
				displayName : '序号',
				enableColumnMenu : false,
				enableHiding : false,
				width : '5%',
				cellTemplate : '<div>{{grid.appScope.getRowIndex(grid, row)}}</div>'
			}, {
				field : 'orderNumber',
				displayName : '订单编号',
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				width : '10%',
				enableHiding : false,
				suppressRemoveSort : true,
				cellClass : "text-left",
				// 是否可编辑
				enableCellEdit : false
			}, {
				field : "orderAmount",
				displayName : '订单金额',
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				enableHiding : false,
				cellClass : "text-right",
				width : '6%',
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false,
				cellFilter : "currency : ''"
			}, {
				field : "modifyAmount",
				displayName : '调整金额',
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				enableHiding : false,
				cellClass : "text-right",
				width : '6%',
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false,
				cellFilter : "currency : ''"
			}, {
				field : "status",
				displayName : "状态",
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				width : '6%',
				enableHiding : false,
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false,
				cellFilter : "orderStatus"
			}, {
				field : "buyerName",
				displayName : "买家名字",
				width : '6%',
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				enableHiding : false,
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false
			}, {
				field : "buyerPhone",
				displayName : "买家号码",
				width : '8%',
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				enableHiding : false,
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false
			}, {
				field : "userId",
				displayName : "买家用户id",
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				enableHiding : false,
				width : '6%',
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false
			}, {
				field : "createTime",
				displayName : "创建时间",
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				width : '10%',
				enableHiding : false,
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false,
				cellFilter : 'date:"yyyy-MM-dd hh:mm:ss"'
			}, {
				field : "payTime",
				displayName : "支付时间",
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				width : '10%',
				enableHiding : false,
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false,
				cellFilter : 'date:"yyyy-MM-dd hh:mm:ss"'
			}, {
				field : "finishTime",
				displayName : "结束时间",
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				width : '10%',
				enableHiding : false,
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false,
				cellFilter : 'date:"yyyy-MM-dd hh:mm:ss"'
			}, {
				field : 'action',
				displayName : "操作",
				cellTemplate : '<div>' +
							'<button type="button" class="btn btn-link" ng-click="grid.appScope.showOrderDetailWin(row.entity.id)">查看详情</button>' + 
							'<button type="button" class="btn btn-link" ng-if="row.entity.status == \'UNPAY\'"' + 
	        				' ng-click="grid.appScope.toModifyAmount(row.entity)">修改金额</button>' +
	        				'</div>',
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				enableHiding : false,
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false
			} ],
			// 是否排序
			enableSorting : false,
			// 是否使用自定义排序规则
			useExternalSorting : false,
			// 是否显示grid 菜单
			enableGridMenu : false,
			// 是否显示grid footer
			showGridFooter : false,
			// grid水平滚动条是否显示, 0-不显示 1-显示
			enableHorizontalScrollbar : 1,
			// grid垂直滚动条是否显示, 0-不显示 1-显示
			enableVerticalScrollbar : 0,
	
			// -------- 分页属性 ----------------
			// 是否分页，默认为true
			enablePagination : true,
			// 使用默认的底部分页
			enablePaginationControls : true,
			// 每页显示个数可选项
			paginationPageSizes : [ 10, 15, 20 ],
			// 当前页码
			paginationCurrentPage : 1,
			// 每页显示个数
			paginationPageSize : 10,
			// 总数量
			totalItems : 0,
			// 是否使用分页按钮
			useExternalPagination : true,
	
			// ----------- 选中 ----------------------
			// 是否显示选中的总数，默认为true,如果显示，showGridFooter必须为true
			enableFooterTotalSelected : true,
			// 是否点击行任意位置后选中,默认为false,当为true时，checkbox可以显示但是不可选中
			enableFullRowSelection : true,
			// 是否显示选中checkbox框 ,默认为true
			enableRowHeaderSelection : false,
			// 行选择是否可用，默认为true;
			enableRowSelection : true,
			// 选择所有checkbox是否可用，默认为true;
			enableSelectAll : true,
			// 默认true
			enableSelectionBatchEvent : true,
			// GridRow
			isRowSelectable : function(row) {
				// row.grid.api.selection.selectRow(row.entity); // 选中行
			},
			// 默认false,为true时只能按ctrl或shift键进行多选,multiSelect 必须为true;
			modifierKeysToMultiSelect : false,
			// 是否可以选择多个,默认为true;
			multiSelect : false,
			// 默认false,选中后是否可以取消选中
			noUnselect : false,
			// 默认30 ，设置选择列的宽度；
			selectionRowHeaderWidth : 30,
	
			// ---------------api---------------------
			onRegisterApi : function(gridApi) {
				$scope.gridApi = gridApi;
				// 分页按钮事件
				gridApi.pagination.on.paginationChanged($scope, function(newPage,
						pageSize) {
					if (loadPage) {
						loadPage(newPage, pageSize);
					}
				});
				// 行选中事件
				$scope.gridApi.selection.on.rowSelectionChanged($scope, function(
						row, event) {
					if (row) {
						$scope.testRow = row.entity;
					}
				});
			}
		};
	
		// 行号
		$scope.getRowIndex = function(grid, row) {
			return grid.rows.indexOf(row) + 1;
		}
	
		/**
		 * 加载分页数据
		 */
		function loadPage(curPage, pageSize) {
			if (!pageSize) {
				pageSize = $scope.gridOptions.paginationPageSize
			}
			var param = {};
			angular.copy($scope.queryModel, param);
			param.currentPage = curPage;
			param.pageSize = pageSize;
			param.createTimeBegin = $filter('date')(param.createTimeBegin, 'yyyy-MM-dd');
			param.createTimeEnd = $filter('date')(param.createTimeEnd, 'yyyy-MM-dd');
			orderService.listOrderPage(param).then(function(response) {
				var resultBean = response.data;
				if (resultBean.resultCode == "0000") {
					// 请求数据成功
					$scope.gridOptions.totalItems = resultBean.result.totalCount;
					if (resultBean.result.recordList) {
						$scope.myData = resultBean.result.recordList;
					} else {
						$scope.myData = [];
					}
					$scope.gridOptions.paginationCurrentPage = curPage;
				} else {
					// 请求数据异常
					alert(resultBean.resultMessage);
				}
			}, function(response) {
				console.log("responseError:" + response);
			});
		};
	
		// 加载第一页数据
		loadPage(1, $scope.gridOptions.paginationPageSize);
		
		// 查询订单
		$scope.queryOrderList = function() {
			loadPage(1, $scope.gridOptions.paginationPageSize);
		};
		
		// 查看订单详情
		$scope.showOrderDetailWin = function(orderId) {
			orderService.getOrderAndGoods(orderId).then(function(response) {
				var resultBean = response.data;
				if ("0000" == resultBean.resultCode) {
					$uibModal.open({
						templateUrl : 'page/orderManage/orderDetail.html',
						controller : 'orderDetailCtrl',
						backdrop : "static",
						size : "md",
						resolve : {
							showData : function() {
								return resultBean.result;
							}
						}
					});
				} else {
					alert(resultBean.resultMessage);
				}
			});
		};
		
		// 打开修改金额窗口
		$scope.toModifyAmount = function(orderData) {
			var editWin = $uibModal.open({
				templateUrl : 'page/orderManage/modifyOrderAmount.html',
				controller : 'modifyOrderCtrl',
				backdrop : "static",
				size : "md",
				resolve : {
					orderData : function() {
						return orderData;
					}
				}
			});
			
			editWin.result.then(function(result) {
				// 加载第一页数据
				loadPage(1, $scope.gridOptions.paginationPageSize);
			}, function(reason) {
			});
		};
    }
]);

/**
 * 控制器-订单详情
 */
qyjStoreApp.controller("orderDetailCtrl", ["$scope", "$uibModalInstance", "showData",
    function($scope, $uibModalInstance, showData) {
		// 订单详细数据
		$scope.orderDetail = showData;
		// 关闭窗口
		$scope.closeWin = function() {
			$uibModalInstance.close();
		};
	}                                          
]);

/**
 * 控制器-调整订单金额
 */
qyjStoreApp.controller("modifyOrderCtrl", ["$scope", "$uibModalInstance", "orderService", "orderData",
    function($scope, $uibModalInstance, orderService, orderData) {
		// 调整后的金额
		$scope.modifyAmount = orderData.modifyAmount;
		// 订单详细数据
		$scope.order = orderData;
		$scope.errorText = null;
		
		// 调整金额
		$scope.modify = function() {
			if (!angular.isNumber($scope.modifyAmount * 1) || isNaN($scope.modifyAmount * 1)) {
				$scope.errorText = "请输入正确的金额";
				return;
			}
			if ($scope.modifyAmount * 1 <= 0) {
				$scope.errorText = "金额不能小于0";
				return;
			}
			if ($scope.modifyAmount * 1 == orderData.modifyAmount) {
				$scope.errorText = "调整金额未发生变化";
				return;
			}
			
			orderService.updateOrderPrice($scope.order.id, $scope.modifyAmount).then(function(response) {
				var resultBean = response.data;
				if ("0000" == resultBean.resultCode) {
					$uibModalInstance.close();
				} else {
					$scope.errorText = resultBean.resultMessage;
					return;
				}
			});
		};
		
		// 关闭窗口
		$scope.closeWin = function() {
			$uibModalInstance.dismiss('cancel');
		};
	}                                          
]);

/**
 * 服务-订单
 */
qyjStoreApp.service("orderService", ["$http",
    function($http) {
		this.listOrderPage = function(data) {
	    	// 加载订单分页数据
	        return $http({  
	            method: "GET",
	            url: qyjStoreApp.httpsHeader + "/admin/order/listOrderPage",
	            params : data
	        });
	    };
	    
	    this.getOrderAndGoods = function(orderId) {
	    	// 获取订单以及订单下的商品
	        return $http({  
	            method: "GET",
	            url: qyjStoreApp.httpsHeader + "/admin/order/getOrderAndGoods",
	            params : {orderId : orderId}
	        });
	    };
	    
	    // 调整订单价格
	    this.updateOrderPrice = function (orderId, modifyAmount) {
	    	return $http({  
	            method: "POST",  
	            url: qyjStoreApp.httpsHeader + "/admin/order/updateOrderPrice",
	            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	            data : {orderId : orderId, modifyAmount : modifyAmount},
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
]);

/**
 * 过滤器-订单状态
 */
qyjStoreApp.filter("orderStatus", [
	function() {
		return function(status) {
			var statusObject = {"UNPAY":"待支付","UNSEND":"待发货","UNTAKE":"待收货","END":"已结束","CANCEL":"取消订单"};
			var statusText = statusObject[status];
			return statusText ? statusText : "";
		};
	}
]);
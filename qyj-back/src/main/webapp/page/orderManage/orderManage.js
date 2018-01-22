var qyjBackApp = angular.module("qyjBackApp");

qyjBackApp.controller("orderListCtrl", ["$scope", "$filter", "orderService",
    function($scope, $filter, orderService) {
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
				field : "newsStatus",
				displayName : "状态",
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				width : '8%',
				enableHiding : false,
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false,
				cellFilter : "newsStatusFilter"
			}, {
				field : "orderNum",
				displayName : "序号",
				width : '8%',
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				enableHiding : false,
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false
			}, {
				field : "visitCount",
				displayName : "访问量",
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
				width : '12%',
				enableHiding : false,
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false,
				cellFilter : 'date:"yyyy-MM-dd hh:mm:ss"'
			}, {
				field : 'action',
				displayName : "操作",
				width : '12%',
				cellTemplate : '<div>' +
							'<button type="button" class="btn btn-link" ng-click="grid.appScope.showEditNewsInfoWin(row.entity.id)">修改</button>' + 
							'<button type="button" class="btn btn-link" ng-if="row.entity.newsStatus == \'PUBLISH\'"' + 
	        				' ng-click="grid.appScope.setPutawayStatus(row.entity.id)">上架</button>' +
	        				'<button type="button" class="btn btn-link" ng-if="row.entity.newsStatus == \'PUTAWAY\'"' +
	        				' ng-click="grid.appScope.setSoldoutStatus(row.entity.id)">下架</button>' +
							'<button type="button" ng-click="grid.appScope.deleteNewsInfo(row.entity.id)" class="btn btn-link">删除</button></div>',
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
					if (getPage) {
						getPage(newPage, pageSize);
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
		var getPage = function(curPage, pageSize) {
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
		getPage(1, $scope.gridOptions.paginationPageSize);
    }
]);

qyjBackApp.service("orderService", ["$http", 
    function($http) {
		this.listOrderPage = function(data) {
	    	// 加载订单分页数据
	        return $http({  
	            method: "POST",
	            url: qyjBackApp.httpsHeader + "/admin/order/listOrderPage",  
	            params : data
	        });
	    }
	}
]);
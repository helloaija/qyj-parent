
/**
 * 用户登陆控制器
 */
qyjBackApp.controller("loginCtrl", ["$scope", "$state", "loginService",
    function($scope, $state, loginService) {
	
		$scope.doLogin = function() {
			// 执行登陆
			loginService.doLogin({userName : $scope.userName, password : $scope.password}).then(function(response) {
				var responseData = response.data;
				if (responseData.resultCode == "0000") {
					// 登陆成功
					$state.go("home");
				} else {
					$scope.errorText = responseData.resultMessage;
				}
			});
		}
	} 
]);

/**
 * 产品管理控制器
 */
qyjBackApp.controller("productManageCtrl", ["$scope", "$document", "$filter", "i18nService", "$uibModal", "Grid", "productService", "tipDialogService",
    function($scope, $document, $filter, i18nService, $uibModal, Grid, productService, tipDialogService) {
		console.log("productManageCtrl...");
		
		// 中文
		i18nService.setCurrentLang('zh-cn');
		
		// 查询面板-创建结束时间-选择器开关
		$scope.shwCreateTimeEndDatePicker = false;
		// 查询面板-创建开始时间-选择器开关
		$scope.shwCreateTimeBeginDatePicker = false;
		
		// 产品类型下拉列表数据
		$scope.productTypeList = [{name : "农药", value : "PESTICIDE"}, {name : "化肥", value : "MANURE"}];
		
		$scope.query = {};
		
		// 查询数据对象
		$scope.queryProductList = function() {
			getPage(1, $scope.gridOptions.paginationPageSize);
		};
		
		// grid列表属性
		$scope.gridOptions = {
			data : 'myData',
			columnDefs : [ {  
                field : 'rowIndex',  
                displayName : '序号',
                enableColumnMenu : false,
                enableHiding : false, 
                width : '5%',
                cellTemplate : '<div>{{grid.appScope.getRowIndex(grid, row)}}</div>'
             }, {
				field : 'title',
				displayName : '产品标题',
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				enableHiding : false,
				suppressRemoveSort : true,
				cellClass : "text-left",
				// 是否可编辑
				enableCellEdit : false
			
			}, {
				field : "price",
				displayName : '价格(元)',
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				enableHiding : false,
				width : '8%',
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false
			}, {
				field : "productType",
				displayName : "产品类型",
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				width : '8%',
				enableHiding : false,
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false,
				cellFilter : "productTypeFilter"
			}, {
				field : "productStatus",
				displayName : "状态",
				width : '8%',
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				enableHiding : false,
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false,
				cellFilter : "productStatusFilter"
			}, {
				field : "number",
				displayName : "数量",
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
                cellTemplate : '<div><button type="button" class="btn btn-link" ng-click="grid.appScope.showEditProductWin(row.entity.id)">修改</button>' +
                				'<button type="button" ng-click="grid.appScope.deleteProductInfo(row.entity.id)" class="btn btn-link">删除</button></div>',
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
			enableVerticalScrollbar : 1,

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
			// 自定义底部分页代码
			// paginationTemplate:"<div></div>",
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
//				if (row.entity.id > 5) {
//					row.grid.api.selection.selectRow(row.entity); // 选中行
//				}
			},
			// 默认false,为true时只能按ctrl或shift键进行多选,multiSelect 必须为true;
			modifierKeysToMultiSelect : false,
			// 是否可以选择多个,默认为true;
			multiSelect : false,
			// 默认false,选中后是否可以取消选中
			noUnselect : false,
			// 默认30 ，设置选择列的宽度；
			selectionRowHeaderWidth : 30,

			// --------------导出----------------------------------
			exporterAllDataFn : function() {
				return getPage(1, $scope.gridOptions.totalItems);
			},
			exporterCsvColumnSeparator : ',',
			exporterCsvFilename : 'download.csv',
			exporterFieldCallback : function(grid, row, col, value) {
				if (value == 50) {
					value = "可以退休";
				}
				return value;
			},
			exporterHeaderFilter : function(displayName) {
				return 'col: ' + name;
			},
			exporterHeaderFilterUseName : true,
			exporterMenuCsv : true,
			exporterMenuLabel : "Export",
			exporterMenuPdf : true,
			exporterOlderExcelCompatibility : false,
			exporterPdfCustomFormatter : function(docDefinition) {
				docDefinition.styles.footerStyle = {
					bold : true,
					fontSize : 10
				};
				return docDefinition;
			},
			exporterPdfFooter : {
				text : 'My footer',
				style : 'footerStyle'
			},
			exporterPdfDefaultStyle : {
				fontSize : 11,
				// font 设置自定义字体
				font : 'simblack' 
			},
			exporterPdfFilename : 'download.pdf',
			/*
			 * exporterPdfFooter : { columns: [ 'Left part', { text: 'Right
			 * part', alignment: 'right' } ] }, 或
			 */
			exporterPdfFooter : function(currentPage, pageCount) {
				return currentPage.toString() + ' of ' + pageCount;
			},
			exporterPdfHeader : function(currentPage, pageCount) {
				return currentPage.toString() + ' of ' + pageCount;
			},
			exporterPdfMaxGridWidth : 720,
			// 'landscape' 或'portrait' pdf横向或纵向
			exporterPdfOrientation : 'landscape',
			// 'A4' or 'LETTER'
			exporterPdfPageSize : 'A4',
			exporterPdfTableHeaderStyle : {
				bold : true,
				fontSize : 12,
				color : 'black'
			},
			exporterPdfTableLayout : null,
			exporterPdfTableStyle : {
				margin : [ 0, 5, 0, 15 ]
			},
			exporterSuppressColumns : [ 'buttons' ],
			exporterSuppressMenu : false,

			// ---------------api---------------------
			onRegisterApi : function(gridApi) {
				$scope.gridApi = gridApi;
				// 分页按钮事件
				gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
					if (getPage) {
						getPage(newPage, pageSize);
					}
				});
				// 行选中事件
				$scope.gridApi.selection.on.rowSelectionChanged($scope, function(row, event) {
					if (row) {
						$scope.testRow = row.entity;
					}
				});
			}
		};

		/**
		 * 加载分页数据
		 */
		var getPage = function(curPage, pageSize) {
			if (!pageSize) {
				pageSize = $scope.gridOptions.paginationPageSize
			}
			var param = {};
			angular.copy($scope.query, param);
			param.currentPage = curPage;
			param.pageSize = pageSize;
			param.createTimeBegin = $filter('date')(param.createTimeBegin, 'yyyy-MM-dd');
			param.createTimeEnd = $filter('date')(param.createTimeEnd, 'yyyy-MM-dd');
			console.log(param);
			productService.loadProductList(param).then(function(response) {
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

		// 获取分页数据
		getPage(1, $scope.gridOptions.paginationPageSize);
		
		// 行号
		$scope.getRowIndex = function(grid, row) {
			return grid.rows.indexOf(row) + 1;
		}
		
		
		// 创建产品编辑窗口
		$scope.createProductWin = function(productData, winParams) {
			var editWin = $uibModal.open({
				templateUrl : '../page/product/productEditView.html',
				controller : 'productEditCtrl',
				backdrop : "static",
				appendTo : $document.find("#contentView").eq(0),
				size : "md",
				resolve : {
					editParams : function() {
						return productData;
					},
					winParams : winParams,
				}
			});
			
			editWin.result.then(function (result) {
				// result关闭是回传的值
				getPage(1, $scope.gridOptions.paginationPageSize);
	        }, function (reason) {     
	        	//点击空白区域，总会输出backdrop click，点击取消，则会暑促cancel   
	             console.log("editWin.result:" + reason);  
	        });
		}
		
		// 打开新增产品信息窗口
		$scope.showAddProductWin = function() {
			$scope.createProductWin(null, {title : "新增产品", operationType : "add"});
		}
		
		// 打开编辑产品信息窗口
		$scope.showEditProductWin = function(productId) {
			productService.getProductInfo(productId).then(function(response) {
				var resultBean = response.data;
				if (resultBean.resultCode == "0000") {
					// 请求数据成功
					if (!resultBean.result) {
						alert("获取不到产品数据，id：" + productId);
						return;
					}
					$scope.createProductWin(resultBean.result, {title : "编辑产品", operationType : "edit"});
				} else {
					// 请求数据异常
					alert(resultBean.resultMessage);
				}
			});
		}
		
		// 删除产品信息
		$scope.deleteProductInfo = function(productId) {
			tipDialogService.showDialog({title : "提示", content : "确认删除？", ok : function() {
				productService.delProductInfo(productId).then(function(response) {
					var resultBean = response.data;
					if (resultBean.resultCode == "0000") {
						// 请求数据成功
						alert("删除成功");
						getPage(1, $scope.gridOptions.paginationPageSize);
						return;
					} else {
						// 请求数据异常
						alert(resultBean.resultMessage);
						return;
					}
				});
				return;
			}});
			$scope.delProductId = productId;
			return;
			
		}
	} 
]);

/**
 * 产品编辑弹窗控制器
 */
qyjBackApp.controller('productEditCtrl', ["$scope", "$uibModalInstance", "$timeout", "Upload", "productService", "editParams", "winParams", 
    function ($scope, $uibModalInstance, $timeout, Upload, productService, editParams, winParams) {
		// 新增、编辑产品数据
		$scope.edit = {};
		// 弹窗参数
		$scope.winParams = {};
		
		if ("edit" == winParams.operationType) {
			angular.copy(editParams, $scope.edit);
		}
		angular.copy(winParams, $scope.winParams);
		
		// 产品类型下拉列表数据
		$scope.productTypeList = [{name : "农药", value : "PESTICIDE"}, {name : "化肥", value : "MANURE"}];
		
		// 保存产品信息
		$scope.saveProduct1 = function() {
			console.log("saveProduct...");
			productService.saveProductInfo($scope.edit).then(function(response) {
				var resultBean = response.data;
				if (resultBean.resultCode == "0000") {
					$uibModalInstance.close();
				} else {
					// 请求数据异常
					alert(resultBean.resultMessage);
				}
			});
		};
	
		// 关闭窗口
		$scope.closeWin = function() {
			console.log("closeWin...");
			$uibModalInstance.dismiss('cancel');
		};
		
		// 产品图片列表
		$scope.productImages = [];
		$scope.uploadFiles = function (files) {
	        $scope.productImages = files;
	    };
		
		// 保存产品信息
		$scope.saveProduct = function() {
			var fieldData = {};
			angular.copy($scope.edit, fieldData);
			fieldData.files = $scope.productImages;
			
			console.log("saveProduct...");
			Upload.upload({
                url: 'http://localhost:8082/qyj-back/admin/product/saveAllProductInfo',
                fields: fieldData,
                file: $scope.productImages[0]
            }).progress(function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            }).success(function (data, status, headers, config) {
                console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
            });
		};
		
		
		
		
		
		
		
		
		$scope.uploadFiles1 = function (files) {
	        $scope.productImages = files;
	        if (files && files.length) {
	        	Upload.upload({
	                url: 'http://localhost:8082/qyj-back/admin/product/uploadImage',
	                fields: {'username' : 'zouroto', files : files}, // additional data to send
	                file: files[0]
	            }).progress(function (evt) {
	                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
//	                console.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
	            }).success(function (data, status, headers, config) {
	                console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
	            });
	        }
	    };
	}
]);



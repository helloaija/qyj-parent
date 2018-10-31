var qyjStoreApp = angular.module("qyjStoreApp");

qyjStoreApp.controller("roleCtrl", ["$scope", "i18nService", "$uibModal", "roleService",
	function($scope, i18nService, $uibModal, roleService) {
		// 查询条件
	 	$scope.queryModel = {};
		// 中文
		i18nService.setCurrentLang('zh-cn');
		
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
				field : 'roleName',
				displayName : '角色名称',
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				enableHiding : false,
				suppressRemoveSort : true,
				cellClass : "text-left",
				// 是否可编辑
				enableCellEdit : false
			}, {
				field : "roleCode",
				displayName : '角色编码',
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				enableHiding : false,
				width : '8%',
				suppressRemoveSort : true,
				// 是否可编辑
				enableCellEdit : false
			}, {
				field : "remark",
				displayName : "备注",
				// 是否显示列头部菜单按钮
				enableColumnMenu : false,
				width : '8%',
				enableHiding : false,
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
							'<button type="button" class="btn btn-link" ng-click="grid.appScope.showViewRoleWin(row.entity.id)">查看</button>' +
							'<button type="button" class="btn btn-link" ng-click="grid.appScope.showEditRoleWin(row.entity.id)">修改</button>' +
							'<button type="button" ng-click="grid.appScope.deleteRole(row.entity.id)" class="btn btn-link">删除</button></div>',
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
		function getPage(curPage, pageSize) {
			if (!pageSize) {
				pageSize = $scope.gridOptions.paginationPageSize;
			}
			var param = {};
			angular.copy($scope.queryModel, param);
			param.currentPage = curPage;
			param.pageSize = pageSize;
			roleService.loadRolePage(param).then(function(response) {
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
		
		// 查询角色
		$scope.queryRole = function() {
			// 加载第一页数据
			getPage(1, $scope.gridOptions.paginationPageSize);
		}
		
		// 打开新增窗口
		$scope.showAddRoleWin = function() {
			// 获取菜单树数据
			roleService.queryMenuTree().then(function(response) {
				var resultBean = response.data;
				if ("0000" == resultBean.resultCode) {
					createRoleEditWin({menu:resultBean.result}, {title : "新增角色"});
				} else {
					alert(resultBean.resultMessage);
				}
			});
		}
		
		// 创建产品编辑窗口,productData编辑的数据,winParams窗口参数
		function createRoleEditWin(editParams, winParams) {
			var editWin = $uibModal.open({
				templateUrl : 'page/system/role/roleEditView.html',
				controller : 'roleEditCtrl',
				backdrop : "static",
				size : "md",
				resolve : {
					editParams : editParams,
					winParams : winParams
				}
			});
			
			editWin.opened.then(function() {
				// 模态窗口打开之后执行的函数   
	        });
			
			editWin.rendered.then(function() {
				// 模态窗口渲染完场之后执行的函数
			});

			editWin.result.then(function(result) {
				// result关闭是回传的值
				getPage(1, $scope.gridOptions.paginationPageSize);
			}, function(reason) {
				// 点击空白区域，总会输出backdrop click，点击取消，则会暑促cancel
			});
		}
	}
]).controller("roleEditCtrl", ["$scope", "$uibModalInstance", "roleService", "editParams", "winParams",
    function($scope, $uibModalInstance, roleService, editParams, winParams) {
		// 窗口参数
		$scope.winParams = winParams;
		// 角色
		$scope.role = editParams.role;
		// 菜单
		$scope.menu = editParams.menu;
		
		// 展开的节点
		$scope.expandedNodes = listExpandedNodes(editParams.menu);;
//		angular.copy($scope.menu, $scope.expandedNodes);
		
		// 配置树
		$scope.treeOptions = {
			nodeChildren : "children",
			dirSelectable : true,
			multiSelection : false,
			injectClasses : {
				ul : "a1",
				li : "a2",
				liSelected : "a7",
				iExpanded : "a3",
				iCollapsed : "a4",
				iLeaf : "a5",
				label : "a6",
				labelSelected : "a8"
			}
		}
		
		// 保存角色
		$scope.saveRole = function() {
			var params = {};
			angular.copy($scope.role, params);
			params.menuIds = getAllCheckNodeIds();
			roleService.addRole(params).then(function(response) {
				var resultBean = response.data;
				if ("0000" == resultBean.resultCode) {
					$uibModalInstance.close();
				} else {
					alert(resultBean.resultMessage);
				}
			});
		};
		
		// 关闭窗口
		$scope.closeWin = function() {
			$uibModalInstance.dismiss('cancel');
		};
		
		// 选择节点
		$scope.checkNode = function(node) {
			console.log("checkNode node:" + JSON.stringify(node));
			if (node.checked) {
				// 选择节点
				checkNode(node);
			} else {
				// 取消选中节点
				uncheckNode(node);
			}
		};
		
		// 展开全部节点
		function listExpandedNodes(children) {
			var nodes = [];
			
			function getAllChildren(array) {
				angular.forEach(array, function(node, index) {
					nodes.push(node);
					if (node.children && angular.isArray(node.children)) {
						getAllChildren(node.children);
					}
				});
			}
			
			getAllChildren(children);
			
			return nodes;
		}
		
		/**
		 * 取消选择节点，该节点下的所有子孙节点都取消选择
		 */
		function uncheckNode(node) {
			node["checked"] = false;
			function forUncheckNode(array) {
				angular.forEach(array, function(obj) {
					obj["checked"] = false;
					if (obj.children && angular.isArray(obj.children)) {
						forUncheckNode(obj.children);
					}
				});
			}
			
			if (node.children && angular.isArray(node.children)) {
				forUncheckNode(node.children);
			}
		}
		
		/**
		 * 选择节点，该节点的父节点已经祖先节点都会被选中
		 */
		function checkNode(node) {
			var parentNodes = [];
			var level = -1;
			var flag = false;
			var finalLavel = 0;
			function forCheckNode(array, level) {
				level ++;
				angular.forEach(array, function(obj, index) {
					if (flag) {
						return;
					}
					
					parentNodes[level] = obj;
					if (obj.id == node.id) {
						flag = true;
						finalLavel = level;
						return;
					}
					if (obj.children && angular.isArray(obj.children)) {
						forCheckNode(obj.children, level);
					}
				});
			}
			
			forCheckNode($scope.menu, level);
			
			if (flag) {
				for (var i = 0; i <= finalLavel; i++) {
					parentNodes[i]["checked"] = true;
				}
			}
		}
		
		/**
		 * 获取所有选择的节点的id
		 */
		function getAllCheckNodeIds() {
			var ids = [];
			function forGetIds(array) {
				angular.forEach(array, function(obj, index) {
					if (obj["checked"]) {
						if (obj["id"]) {
							ids.push(obj["id"]);
						}
						if (obj.children && angular.isArray(obj.children)) {
							forGetIds(obj.children);
						}
					}
				});
			}
			
			forGetIds($scope.menu);
			
			return ids;
		}
	}
]).service("roleService", ["$http", function($http) {
	// 加载角色数据
    this.loadRolePage = function (params) {
        return $http({  
            method: "GET",  
            url: qyjStoreApp.httpsHeader + "/admin/role/loadRolePage",
            params : params
        });
    }
    
    // 新增角色
    this.addRole = function (params) {
        return $http({  
            method: "POST",  
            url: qyjStoreApp.httpsHeader + "/admin/role/addRole",
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            data : params,
            transformRequest: transformRequest
        });
    }
    
    // 获取用户菜单树
    this.queryMenuTree = function () {
        return $http({  
            method: "GET",  
            url: qyjStoreApp.httpsHeader + "/admin/menu/queryMenuTree"
        });
    }
    
    // 序列化参数方法
    function transformRequest(obj) {
    	var str = [];
		for ( var s in obj) {
			str.push(encodeURIComponent(s) + "=" + encodeURIComponent(obj[s]));
		}
		return str.join("&");   
    }
}]).filter("showText", function() {
	return function(node) {
		if (node.attributes) {
			if ("MENU" == node.attributes.type) {
				return node.text + "(菜单)"
			} else {
				return node.text + "(功能)";
			}
		}
		return node.text;
	}
});

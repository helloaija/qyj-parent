var qyjStoreApp = angular.module("qyjStoreApp");

/**
 * 系统菜单控制器
 */
qyjStoreApp.controller("menuCtrl", ["$rootScope", "$scope", "menuService", "tipDialogService",
	function($rootScope, $scope, menuService, tipDialogService) {
        $rootScope.curMenu = {homeAdminManage : true, homeMenu : true};

		// 类型下拉
		$scope.menuTypeList = [{value:"MENU", name:"菜单"},{value:"FUN", name:"功能"}];
		// 编辑内容
		$scope.edit = {};
		// 是否显示编辑内容
		$scope.showEdit = false;
		// 编辑标题
		$scope.editTitle = "编辑";
		// 当前选择的节点
		$scope.currentNode = null;

        // 展开的节点
        $scope.expandedNodes;
		
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
		
		// 获取菜单树数据
		menuService.queryMenuTree().then(function(response) {
			var resultBean = response.data;
			if ("0000" == resultBean.resultCode) {
				// 加载菜单树数据
				$scope.groupsTreeModel = resultBean.result;
                $scope.expandedNodes = listExpandedNodes(resultBean.result);
			}
		});
		
		// 选择节点 
		$scope.selectNode = function(node) {
			$scope.currentNode = node;
			if (node.id == 0) {
				// 根节点不能编辑
				$scope.showEdit = false;
				return;
			}
			// 获取菜单
			menuService.getMenuById(node.id).then(function(response) {
				var resultBean = response.data;
				if ("0000" == resultBean.resultCode) {
					angular.copy(resultBean.result, $scope.edit);
					$scope.showEdit = true;
					$scope.editTitle = "编辑[" + $scope.edit.name + "]";
				} else {
					alert(resultBean.resultMessage);
				}
			});
		};
		
		// 展开节点
		$scope.expendNode = function(node) {
			console.log("on-node-toggle" + node.name);
		}
		
		// 保存
		$scope.saveMenu = function() {
			if (!$scope.edit) {
				alert("保存数据为空");
				return;
			}
			
			if ($scope.currentNode.id == null || undefined == $scope.currentNode.id) {
				alert("父节点不能为空");
				return;
			}
			if (!$scope.edit.name) {
				alert("名称不能为空");
				return;
			}
			if (!$scope.edit.menuType) {
				alert("类型不能为空");
				return;
			}
            if (!$scope.edit.menuCode) {
                alert("编码不能为空");
                return;
            }
			
			// 有id是编辑
			if ($scope.edit.id) {
				menuService.updateMenu($scope.edit).then(function(response) {
					var resultBean = response.data;
					if ("0000" == resultBean.resultCode) {
						alert("更新成功");
					} else {
						alert(resultBean.resultMessage);
					}
				});
			} else {
				menuService.addMenu($scope.edit).then(function(response) {
					var resultBean = response.data;
					if ("0000" == resultBean.resultCode) {
						var newNode = {};
						angular.copy(resultBean.result, newNode);
						newNode.text = newNode.name;
						addTreeData(newNode);
						$scope.showEdit = false;
						alert("新增成功");
					} else {
						alert(resultBean.resultMessage);
					}
				});
			}
		}
		
		// 新增子节点操作
		$scope.toAddChildNode = function() {
			if ($scope.currentNode == null || $scope.currentNode.id == null || undefined == $scope.currentNode.id) {
				alert("请选择父节点");
				return;
			}
			
			$scope.editTitle = "新增子节点[父节点（" + $scope.currentNode.text + "）]";
			$scope.showEdit = true;
			$scope.edit = {};
			$scope.edit.parentId = $scope.currentNode.id;
		}
		
		// 新增同级节点 
		$scope.toAddBrotherNode = function() {
			
		}
		
		// 删除节点
		$scope.deleteMenu = function() {
			if ($scope.currentNode == null || $scope.currentNode.id == null || undefined == $scope.currentNode.id) {
				alert("请选择需要删除 的节点");
				return;
			}
			
			tipDialogService.showDialog({
				title : "提示",
				content : "确认节点[" + $scope.currentNode.text + "]删除？",
				ok : function() {
					menuService.deleteMenu($scope.currentNode.id).then(function(response) {
						var resultBean = response.data;
						if (resultBean.resultCode == "0000") {
							$scope.showEdit = false;
							deleteNodeData($scope.groupsTreeModel, $scope.currentNode.id);
							// 请求数据成功
							alert("删除成功");
						} else {
							// 请求数据异常
							alert(resultBean.resultMessage);
						}
					});
				}
			});
		}
		
		/**
		 * 添加节点数据，递归调用直到在符合节点的位置
		 */
		function addNodeData(parendNode, addNode) {
			if (parendNode.id == addNode.parentId) {
				if (!parendNode.children) {
					parendNode.children = [];
				}
				parendNode.children.push(addNode);
				return;
			} else {
				if (!parendNode.children) {
					return;
				} else {
					for (var i = 0; i < parendNode.children.length; i ++) {
						addNodeData(parendNode.children[i], addNode);
					}
				}
			}
		}
		
		/**
		 * 添加节点数据
		 */
		function addTreeData(addNode) {
			if (!$scope.groupsTreeModel) {
				return;
			}
			for (var i = 0; i < $scope.groupsTreeModel.length; i ++) {
				var parentNode = $scope.groupsTreeModel[i];
				addNodeData(parentNode, addNode)
			}
		}
		
		/**
		 * 删除节点数据
		 */
		function deleteNodeData(nodeList, nodeId) {
			if (!nodeList) {
				return;
			}
			for (var i = 0; i < nodeList.length; i ++) {
				var parentNode = nodeList[i];
				if (parentNode.id == nodeId) {
					nodeList.splice(i, 1);
					return;
				} else {
					deleteNodeData(nodeList[i].children, nodeId);
				}
			}
		}

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
	}
]).service('menuService', ["$http", 
	function($http) {
		// 获取用户菜单树
	    this.queryMenuTree = function () {
	        return $http({  
	            method: "GET",  
	            url: qyjStoreApp.httpsHeader + "/admin/menu/queryMenuTree"
	        });
	    }
	    
	    // 根据id获取菜单
	    this.getMenuById = function (menuId) {
	        return $http({  
	            method: "GET",  
	            url: qyjStoreApp.httpsHeader + "/admin/menu/getMenuById",
	            params : {menuId: menuId}
	        });
	    }
	    
	    // 新增菜单
	    this.addMenu = function (params) {
	        return $http({  
	            method: "POST",  
	            url: qyjStoreApp.httpsHeader + "/admin/menu/addMenu",
	            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	            data : params,
	            transformRequest: transformRequest
	        });
	    }
	    
	    // 更新菜单
	    this.updateMenu = function (params) {
	        return $http({  
	            method: "POST",  
	            url: qyjStoreApp.httpsHeader + "/admin/menu/updateMenu",
	            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	            data : params,
	            transformRequest: transformRequest
	        });
	    }
	    
	    // 更新菜单
	    this.deleteMenu = function (menuId) {
	        return $http({  
	            method: "POST",  
	            url: qyjStoreApp.httpsHeader + "/admin/menu/deleteMenu",
	            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	            data : {menuId : menuId},
	            transformRequest: transformRequest
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
	}
]);

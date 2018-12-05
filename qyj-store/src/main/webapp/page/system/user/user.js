
var qyjStoreApp = angular.module("qyjStoreApp");

qyjStoreApp.controller("userCtrl", ["$rootScope", "$scope", "$document", "$filter", "i18nService", "$uibModal",
    "tipDialogService", "userService",
    function($rootScope, $scope, $document, $filter, i18nService, $uibModal, tipDialogService, userService) {
        $rootScope.curMenu = {homeAdminManage : true, homeUser : true};

        // 中文
        i18nService.setCurrentLang('zh-cn');

        $scope.query = {};

        // 查询数据对象
        $scope.queryProductList = function() {
            getPage(1, $scope.gridOptions.paginationPageSize);
        };

        var me = $scope;

        var columns = [ {
            field : 'rowIndex',
            displayName : '序号',
            enableColumnMenu : false,
            enableHiding : false,
            width : '3%',
            cellTemplate : '<div>{{grid.appScope.getRowIndex(grid, row)}}</div>'
        }, {
            field : 'userName',
            displayName : '用户名',
            // 是否显示列头部菜单按钮
            enableColumnMenu : false,
            enableHiding : false,
            width : '11%',
            suppressRemoveSort : true,
            cellClass : "text-left",
            // 是否可编辑
            enableCellEdit : false

        }, {
            field : "realName",
            displayName : '真实姓名',
            // 是否显示列头部菜单按钮
            enableColumnMenu : false,
            enableHiding : false,
            width : '7%',
            suppressRemoveSort : true,
            cellClass : "text-left",
            // 是否可编辑
            enableCellEdit : false
        }, {
            field : "telPhone",
            displayName : '联系电话',
            // 是否显示列头部菜单按钮
            enableColumnMenu : false,
            enableHiding : false,
            width : '10%',
            suppressRemoveSort : true,
            cellClass : "text-left",
            // 是否可编辑
            enableCellEdit : false
        }, {
            field : "lastTime",
            displayName : '最后登录时间',
            // 是否显示列头部菜单按钮
            enableColumnMenu : false,
            enableHiding : false,
            width : '10%',
            suppressRemoveSort : true,
            cellClass : "text-right",
            // 是否可编辑
            enableCellEdit : false,
            cellFilter : 'date:"yyyy-MM-dd hh:mm:ss"'
        }, {
            field : "enable",
            displayName : "启用状态",
            // 是否显示列头部菜单按钮
            enableColumnMenu : false,
            width : '5%',
            enableHiding : false,
            suppressRemoveSort : true,
            // 是否可编辑
            enableCellEdit : false,
            cellFilter : "enableFilter"
        }, {
            field : 'action',
            displayName : "操作",
            width : '20%',
            cellTemplate : '<div>' +
                '<button type="button" class="btn btn-link"' +
                ' ng-click="grid.appScope.showEditUserWin(row.entity.id)">修改</button>' +
                '<button type="button" class="btn btn-link"' +
                ' ng-click="grid.appScope.deleteUser(row.entity)">删除</button>' +
                '<button type="button" class="btn btn-link" ng-if="row.entity.enable == \'DISABLED\'"' +
                ' ng-click="grid.appScope.updateUserEnable(row.entity, \'USABLE\')">启用</button>' +
                '<button type="button" class="btn btn-link" ng-if="row.entity.enable == \'USABLE\'"' +
                ' ng-click="grid.appScope.updateUserEnable(row.entity, \'DISABLED\')">禁用</button></div>',
            // 是否显示列头部菜单按钮
            enableColumnMenu : false,
            enableHiding : false,
            suppressRemoveSort : true,
            // 是否可编辑
            enableCellEdit : false
        }, {
            field : 'null',
            displayName : "",
            cellTemplate : '',
            // 是否显示列头部菜单按钮
            enableColumnMenu : false,
            enableHiding : false,
            suppressRemoveSort : true,
            // 是否可编辑
            enableCellEdit : false
        } ];

        // grid列表属性
        $scope.gridOptions = {
            data : 'myData',
            columnDefs : columns,

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
                gridApi.selection.on.rowSelectionChanged($scope, function(row, event) {
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

            userService.loadUserInfoList(param).then(function(response) {
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
                    tipDialogService.showPromptDialog(resultBean.resultMessage);
                }
            }, function(response) {
                // 请求数据异常
                tipDialogService.showPromptDialog(response.data.resultMessage);
                console.log("responseError:" + response);
            });
        };

        // 获取分页数据
        getPage(1, $scope.gridOptions.paginationPageSize);

        // 行号
        $scope.getRowIndex = function(grid, row) {
            return grid.rows.indexOf(row) + 1;
        }

        // 查询
        $scope.queryUser = function() {
            getPage(1, $scope.gridOptions.paginationPageSize);
        }

        // 查询重置
        $scope.resetQuery = function() {
        	$scope.query = {};
		}

        // 新增用户窗口
        $scope.showAddUserWin = function() {
            var addWin = $uibModal.open({
                templateUrl : 'page/system/user/userAddView.html',
                controller : 'userAddCtrl',
                backdrop : "static",
                size : "md"
            });

            addWin.result.then(function (result) {
                // result关闭是回传的值
                getPage(1, $scope.gridOptions.paginationPageSize);
            }, function (reason) {
                // 点击空白区域，总会输出backdrop click，点击取消，则会暑促cancel
                // console.log("editWin.result:" + reason);
            });
        }

        // 编辑用户窗口
        $scope.showEditUserWin = function(userId) {
            userService.getUserRoleData(userId).then(function(response) {
                var resultBean = response.data;
                if (resultBean.resultCode == "0000") {
                    var editWin = $uibModal.open({
                        templateUrl : 'page/system/user/userEditView.html',
                        controller : 'userEditCtrl',
                        backdrop : "static",
                        size : "md",
                        resolve : {
                            roleList : function() {
                                return resultBean.result.role;
                            },
                            userItem : function() {
                                return resultBean.result.user;
                            }
                        }
                    });

                    editWin.result.then(function (result) {
                        // result关闭是回传的值
                        getPage(1, $scope.gridOptions.paginationPageSize);
                    }, function (reason) {
                        // 点击空白区域，总会输出backdrop click，点击取消，则会暑促cancel
                        console.log("editWin.result:" + reason);
                    });
                } else {
                    // 请求数据异常
                    tipDialogService.showPromptDialog(resultBean.resultMessage);
                }
            }, function(response) {
                console.log("responseError:" + response);
            });
        }

        // 删除用户信息
        $scope.deleteUser = function(userItem) {
            tipDialogService.showDialog({title : "提示", content : "[" + userItem.userName + "]确认删除？", ok : function() {
				    var userIds = new Array();
				    userIds.push(userItem.id);
                    userService.delUser(userIds).then(function(response) {
					var resultBean = response.data;
					if (resultBean.resultCode == "0000") {
						// 请求数据成功
						tipDialogService.showPromptDialog("删除成功");
						getPage(1, $scope.gridOptions.paginationPageSize);
						return;
					} else {
						// 请求数据异常
						tipDialogService.showPromptDialog(resultBean.resultMessage);
						return;
					}
				}, function(response) {
				});
			}});
        }

        // 更新用户状态
        $scope.updateUserEnable = function(userItem, state) {
            var shadeModel = tipDialogService.showLoadingShade();
            userService.updateUserEnable(userItem.id, state).then(function(response) {
                shadeModel.close();
                var resultBean = response.data;
                if (resultBean.resultCode == "0000") {
                    userItem.enable = state;
                    // 请求数据成功
                    tipDialogService.showPromptDialog("更新成功");
                    return;
                } else {
                    // 请求数据异常
                    tipDialogService.showPromptDialog(resultBean.resultMessage);
                    return;
                }
            }, function(response) {
            });
        }

    }
]);

/**
 * 控制器-新增订单
 */
qyjStoreApp.controller("userAddCtrl", function($scope, $uibModalInstance, $filter, i18nService, tipDialogService, userService) {

    // grid列表属性
    $scope.roleGridOptions = {
        data : 'myData',
        columnDefs : [ {
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
            width : '12%',
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
            width : '12%',
            suppressRemoveSort : true,
            cellClass : "text-left",
            // 是否可编辑
            enableCellEdit : false
        }, {
            field : 'null',
            displayName : "",
            cellTemplate : '',
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
        enableHorizontalScrollbar : 0,
        // grid垂直滚动条是否显示, 0-不显示 1-显示
        enableVerticalScrollbar : 1,

        // ----------- 选中 ----------------------
        // 是否显示选中的总数，默认为true,如果显示，showGridFooter必须为true
        enableFooterTotalSelected : true,
        // 是否点击行任意位置后选中,默认为false,当为true时，checkbox可以显示但是不可选中
        enableFullRowSelection : true,
        // 是否显示选中checkbox框 ,默认为true
        enableRowHeaderSelection : true,
        // 行选择是否可用，默认为true;
        enableRowSelection : true,
        // 选择所有checkbox是否可用，默认为true;
        enableSelectAll : true,
        // 默认true
        enableSelectionBatchEvent : true,
        // GridRow
        isRowSelectable : function(row) {
            if (row.entity.selected) {
                // 选中行
                row.grid.api.selection.selectRow(row.entity);
            }
        },
        // 默认false,为true时只能按ctrl或shift键进行多选,multiSelect 必须为true;
        modifierKeysToMultiSelect : false,
        // 是否可以选择多个,默认为true;
        multiSelect : true,
        // 默认false,选中后是否可以取消选中
        noUnselect : false,
        // 默认30 ，设置选择列的宽度；
        selectionRowHeaderWidth : 30,

        // ---------------api---------------------
        onRegisterApi : function(gridApi) {
            $scope.gridApi = gridApi;

            // 行选中事件
            gridApi.selection.on.rowSelectionChanged($scope, function(row, event) {
                if (row) {
                    $scope.testRow = row.entity;
                }
            });
        }
    };

    userService.getUserRoleData().then(function (response) {
        var resultBean = response.data;
        if (resultBean.resultCode == "0000") {
            $scope.myData = resultBean.result.role;
        }
    });

    // 保存订单信息
    $scope.saveUser = function() {
        var fieldData = {};
        angular.copy($scope.add, fieldData);

        var selectedRows = $scope.gridApi.selection.getSelectedRows();

        var roleIds = new Array();
        for (var i = 0; i < selectedRows.length; i ++) {
            roleIds.push(selectedRows[i].id);
        }

        fieldData["roleIds"] = roleIds;

        if (!userService.validUser(fieldData)) {
            return;
        }

        userService.addUser(fieldData).then(function(response) {
            var resultBean = response.data;
            if (resultBean.resultCode == "0000") {
                // 请求数据成功
                $uibModalInstance.close();
            } else {
                // 请求数据异常
                tipDialogService.showPromptDialog(resultBean.resultMessage);
            }
        }, function() {
        });
    };

    $scope.closeWin = function() {
        $uibModalInstance.dismiss('cancel');
    }

});

/**
 * 产品编辑弹窗控制器
 */
qyjStoreApp.controller('userEditCtrl', function ($scope, $uibModalInstance, $filter, tipDialogService,
                                                 userService, roleList, userItem) {
    // grid列表属性
    $scope.roleGridOptions = {
        data : 'myData',
        columnDefs : [ {
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
            width : '12%',
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
            width : '12%',
            suppressRemoveSort : true,
            cellClass : "text-left",
            // 是否可编辑
            enableCellEdit : false
        }, {
            field : 'null',
            displayName : "",
            cellTemplate : '',
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
        enableHorizontalScrollbar : 0,
        // grid垂直滚动条是否显示, 0-不显示 1-显示
        enableVerticalScrollbar : 1,

        // ----------- 选中 ----------------------
        // 是否显示选中的总数，默认为true,如果显示，showGridFooter必须为true
        enableFooterTotalSelected : true,
        // 是否点击行任意位置后选中,默认为false,当为true时，checkbox可以显示但是不可选中
        enableFullRowSelection : true,
        // 是否显示选中checkbox框 ,默认为true
        enableRowHeaderSelection : true,
        // 行选择是否可用，默认为true;
        enableRowSelection : true,
        // 选择所有checkbox是否可用，默认为true;
        enableSelectAll : true,
        // 默认true
        enableSelectionBatchEvent : true,
        // GridRow
        isRowSelectable : function(row) {
            if (row.entity.selected) {
                // 选中行
                row.grid.api.selection.selectRow(row.entity);
            }
        },
        // 默认false,为true时只能按ctrl或shift键进行多选,multiSelect 必须为true;
        modifierKeysToMultiSelect : false,
        // 是否可以选择多个,默认为true;
        multiSelect : true,
        // 默认false,选中后是否可以取消选中
        noUnselect : false,
        // 默认30 ，设置选择列的宽度；
        selectionRowHeaderWidth : 30,

        // ---------------api---------------------
        onRegisterApi : function(gridApi) {
            $scope.gridApi = gridApi;

            // 行选中事件
            gridApi.selection.on.rowSelectionChanged($scope, function(row, event) {
                if (row) {
                    $scope.testRow = row.entity;
                }
            });
        }
    };

    $scope.edit = userItem;
    // 加载角色
    $scope.myData = roleList;

    // 保存订单信息
    $scope.saveUser = function() {
        var fieldData = {};
        angular.copy($scope.edit, fieldData);

        var selectedRows = $scope.gridApi.selection.getSelectedRows();

        var roleIds = new Array();
        for (var i = 0; i < selectedRows.length; i ++) {
            roleIds.push(selectedRows[i].id);
        }

        fieldData["roleIds"] = roleIds;

        if (!userService.validUser(fieldData)) {
            return;
        }

        userService.updateUser(fieldData).then(function(response) {
            var resultBean = response.data;
            if (resultBean.resultCode == "0000") {
                // 请求数据成功
                $uibModalInstance.close();
            } else {
                // 请求数据异常
                tipDialogService.showPromptDialog(resultBean.resultMessage);
            }
        }, function() {
        });
    };

    $scope.closeWin = function() {
        $uibModalInstance.dismiss();
    }
});


/**
 * 用户服务器
 */
qyjStoreApp.service('userService', function($http, tipDialogService) {
    // 请求获取用户分页数据
    this.loadUserInfoList = function (data) {
        return $http({
            method: "GET",
            url: qyjStoreApp.httpsHeader + "/admin/user/loadUserInfoList",
            params : data
        });
    }

    // 加载用户对应角色数据
    this.getUserRoleData = function (userId) {
        return $http({
            method: "GET",
            url: qyjStoreApp.httpsHeader + "/admin/user/getUserRoleData",
            params : {userId : userId}
        });
    }

    // 添加用户信息
    this.addUser = function (params) {
        return $http({
            method: "POST",
            url: qyjStoreApp.httpsHeader + "/admin/user/addUser",
            contentType: "application/json",
            params : params
        });
    }

    // 修改用户信息
    this.updateUser = function (params) {
        return $http({
            method: "POST",
            url: qyjStoreApp.httpsHeader + "/admin/user/updateUser",
            contentType: "application/json",
            params : params
        });
    }

    // 删除用户信息
    this.delUser = function (userIds) {
        return $http({
            method: "POST",
            url: qyjStoreApp.httpsHeader + "/admin/user/delUser",
            contentType: "application/json",
            params : {ids : userIds}
        });
    }

    // 更新用户状态
    this.updateUserEnable = function (userId, state) {
        return $http({
            method: "POST",
            url: qyjStoreApp.httpsHeader + "/admin/user/updateUserEnable",
            contentType: "application/json",
            params : {userId : userId, enable : state}
        });
    }

    /**
     * 校验用户信息
     * @param sellOrder
     */
    this.validUser = function(userDate) {
        if (userDate.userName == null || userDate.userName.trim() == "") {
            tipDialogService.showPromptDialog("用户名不能为空！");
            return false;
        }
        if (userDate.realName == null || userDate.realName.trim() == "") {
            tipDialogService.showPromptDialog("真实姓名不能为空！");
            return false;
        }
        if (userDate.telPhone == null || userDate.telPhone.trim() == "") {
            tipDialogService.showPromptDialog("联系电话不能为空！");
            return false;
        }
        if (userDate.roleIds == null || userDate.roleIds.length == 0) {
            tipDialogService.showPromptDialog("请选择用户角色！");
            return false;
        }
        return true;
    }
});

qyjStoreApp.filter("enableFilter", function() {
    // 启用状态过滤器
    return function(value) {
        var enableMap = {'DISABLED': '禁用', 'USABLE': '启用'};
        return enableMap[value];
    }
})

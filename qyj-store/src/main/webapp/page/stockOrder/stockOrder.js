
var qyjStoreApp = angular.module("qyjStoreApp");

qyjStoreApp.controller("stockOrderCtrl", ["$scope", "$document", "$filter", "i18nService", "$uibModal",
	"tipDialogService", "stockOrderService",
    function($scope, $document, $filter, i18nService, $uibModal, tipDialogService, stockOrderService) {
		// 中文
        i18nService.setCurrentLang('zh-cn');

        // 查询面板-创建结束时间-选择器开关
        $scope.shwCreateTimeEndDatePicker = false;
        // 查询面板-创建开始时间-选择器开关
        $scope.shwCreateTimeBeginDatePicker = false;

        // 订单状态下拉列表数据
        $scope.orderStatusList = stockOrderService.getOrderStatusList();

        $scope.query = {};

        // 查询数据对象
        $scope.queryProductList = function() {
            getPage(1, $scope.gridOptions.paginationPageSize);
        };

        var me = $scope;

        // grid列表属性
        $scope.gridOptions = {
            data : 'myData',
            columnDefs : [ {
                field : 'rowIndex',
                displayName : '序号',
                enableColumnMenu : false,
                enableHiding : false,
                width : '3%',
                cellTemplate : '<div>{{grid.appScope.getRowIndex(grid, row)}}</div>'
            }, {
                field : 'orderNumber',
                displayName : '订单编号',
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                enableHiding : false,
                width : '11%',
                suppressRemoveSort : true,
                cellClass : "text-left",
                // 是否可编辑
                enableCellEdit : false

            }, {
                field : "orderAmount",
                displayName : '订单价格',
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                enableHiding : false,
                width : '7%',
                suppressRemoveSort : true,
                cellClass : "text-right",
                // 是否可编辑
                enableCellEdit : false,
                cellFilter : 'currency:"￥"'
            }, {
                field : "modifyAmount",
                displayName : '调整价格',
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                enableHiding : false,
                width : '7%',
                suppressRemoveSort : true,
                cellClass : "text-right",
                // 是否可编辑
                enableCellEdit : false,
                cellFilter : 'currency:"￥"'
            }, {
                field : "hasPayAmount",
                displayName : '已支付金额',
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                enableHiding : false,
                width : '7%',
                suppressRemoveSort : true,
                cellClass : "text-right",
                // 是否可编辑
                enableCellEdit : false,
                cellFilter : 'currency:"￥"'
            }, {
                field : "orderStatus",
                displayName : "订单状态",
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                width : '5%',
                enableHiding : false,
                suppressRemoveSort : true,
                // 是否可编辑
                enableCellEdit : false,
                cellFilter : "productTypeFilter"
            }, {
                field : "supplierName",
                displayName : "供应商名称",
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                enableHiding : false,
                cellClass : "text-left",
                width : '9%',
                suppressRemoveSort : true,
                // 是否可编辑
                enableCellEdit : false
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
                field : "remark",
                displayName : "备注",
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                enableHiding : false,
                cellClass : "text-left",
                suppressRemoveSort : true,
                // 是否可编辑
                enableCellEdit : false
            }, {
                field : 'action',
                displayName : "操作",
                width : '12%',
                cellTemplate : '<div>' +
                    '<button type="button" class="btn btn-link"' +
                    ' ng-click="grid.appScope.showViewProductWin(row.entity.id)">详情</button>' +
                    '<button type="button" class="btn btn-link"' +
                    ' ng-click="grid.appScope.showEditOrderWin(row.entity.id)">修改</button>' +
                    '<button type="button" class="btn btn-link"' +
                    ' ng-click="grid.appScope.deleteStockOrder(row.entity.id, row.entity.orderNumber)">删除</button></div>',
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

            expandableRowTemplate : "page/stockOrder/temp.html",
            expandableRowHeight: 160,
            enableExpandable: true,
            enableExpandableRowHeader: true,
            expandableRowScope: {
                subGridVariable: "subGridScopeVariable"
            },

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
                        $scope.selectedRow = row.entity;
                    }
                });
                // 折叠
                gridApi.expandable.on.rowExpandedStateChanged($scope, function (orderRow) {
                    if (orderRow.isExpanded) {
                        orderRow.entity.subGridOptions = {
                            columnDefs: [
                                {field : 'rowIndex', displayName : '序号', enableColumnMenu : false, enableHiding : false,
                                    width : '5%', cellClass : "sub-grid-background", headerCellClass : "sub-grid-background"
                                }, {name: 'productTitle', displayName : "产品", enableColumnMenu : false, width : '20%',
                                    cellClass : "sub-grid-background text-left", headerCellClass : "sub-grid-background"},
                                {name: 'price', displayName : "价格", enableColumnMenu : false, width : '8%',
                                    cellClass : "sub-grid-background text-right", headerCellClass : "sub-grid-background", cellFilter : 'currency:"￥"'},
                                {name: 'number', displayName : "数量", enableColumnMenu : false, width : '8%',
                                    cellClass : "sub-grid-background text-right", headerCellClass : "sub-grid-background"},
                                {name: 'emptyColumn', displayName : "", enableColumnMenu : false,
                                    cellClass : "sub-grid-background text-right", headerCellClass : "sub-grid-background"}
                            ],
                            enableHorizontalScrollbar : 0,
                            // 是否显示grid 菜单
                            enableGridMenu : false,
                            // 是否显示grid footer
                            showGridFooter : false,
                            // 是否排序
                            enableSorting : false,
                            // 是否使用自定义排序规则
                            useExternalSorting : false
                        };

                        for (var i = 0; i < orderRow.grid.rows.length; i ++) {
                            var r = orderRow.grid.rows[i];
                            if (r.isExpanded && r.entity.id != orderRow.entity.id) {
                                r.isExpanded = false;
                            }
                        }

                        for (var i = 0; i < orderRow.entity.stockProductList.length; i ++) {
                            orderRow.entity.stockProductList[i].rowIndex = i + 1;
                        }

                        orderRow.entity.subGridOptions.data = orderRow.entity.stockProductList;
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
            stockOrderService.loadStockOrderList(param).then(function(response) {
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
                console.log("responseError:" + response);
            });
        };

        // 获取分页数据
        getPage(1, $scope.gridOptions.paginationPageSize);

        // 行号
        $scope.getRowIndex = function(grid, row) {
            return grid.rows.indexOf(row) + 1;
        }

        // 新增订单窗口
        $scope.showAddOrderWin = function() {
            var addWin = $uibModal.open({
                templateUrl : 'page/stockOrder/stockOrderAddView.html',
                controller : 'stockOrderAddCtrl',
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

        // 编辑订单窗口
        $scope.showEditOrderWin = function(stockId) {
            var shadeModel = tipDialogService.showLoadingShade();
            stockOrderService.getStockOrderInfo(stockId).then(function(response) {
                shadeModel.close();
                var resultBean = response.data;
                if (resultBean.resultCode == "0000") {
                    var editWin = $uibModal.open({
                        templateUrl : 'page/stockOrder/stockOrderEditView.html',
                        controller : 'stockOrderEditCtrl',
                        backdrop : "static",
                        size : "md",
                        resolve : {
                            items : function() {
                                return resultBean.result;
                            }
                        }
                    });

                    editWin.result.then(function (result) {
                        // result关闭是回传的值
                        getPage(1, $scope.gridOptions.paginationPageSize);
                    }, function (reason) {
                        // 点击空白区域，总会输出backdrop click，点击取消，则会暑促cancel
                        // console.log("editWin.result:" + reason);
                    });
                } else {
                    // 请求数据异常
                    tipDialogService.showPromptDialog(resultBean.resultMessage);
                }
            }, function(response) {
                shadeModel.close();
                console.log("responseError:" + response);
            });
        }

        // 删除产品信息
        $scope.deleteStockOrder = function(stockId, orderNumber) {
            tipDialogService.showDialog({title : "提示", content : "[" + orderNumber + "]确认删除？", ok : function() {
                var shadeModel = tipDialogService.showLoadingShade();
                stockOrderService.deleteStockOrder(stockId).then(function(response) {
                    shadeModel.close();
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
                    shadeModel.close();
                });
            }});
        }

	}
]);

/**
 * 控制器-新增订单
 */
qyjStoreApp.controller("stockOrderAddCtrl", function($scope, $uibModalInstance, $filter, i18nService, tipDialogService, stockOrderService) {

    // 订单状态下拉列表数据
    $scope.orderStatusList = stockOrderService.getOrderStatusList();

    // 产品选择项
    $scope.productList = new Array();

    $scope.add = {orderStatus : "HASPAY"};
    $scope.add.stockProductList = new Array({});

    // 获取产品下拉列
    $scope.getProductList = function(title) {
        var param = {currentPage : 1, pageSize : 0};
        if (title != null && title.trim() != "") {
            param.title = title.trim();
        }

        stockOrderService.loadProductList(param).then(function(response) {
            var resultBean = response.data;
            if (resultBean.resultCode == "0000") {
                $scope.productList = resultBean.result.recordList;
            }
        });
    }

    // 添加产品
    $scope.addProduct = function() {
        $scope.add.stockProductList.push({});
    }

    // 删除产品
    $scope.deleteProduct = function(index, product) {
        $scope.add.stockProductList.splice(index, 1);
    }

    // 保存订单信息
    $scope.saveOrder = function() {
        var fieldData = {};
        angular.copy($scope.add, fieldData);

        fieldData.finishTime = $filter('date')(fieldData.finishTime, 'yyyy-MM-dd HH:mm:ss');
        fieldData.payTime = $filter('date')(fieldData.payTime, 'yyyy-MM-dd HH:mm:ss');

        if (!stockOrderService.validStockOrder(fieldData)) {
            return;
        }

        if (!stockOrderService.validStockProduct(fieldData.stockProductList)) {
            return;
        }

        for (var i = 0; i < fieldData.stockProductList.length; i ++) {
            var stockProduct = fieldData.stockProductList[i];
            delete stockProduct.productModel;
            Object.keys(stockProduct).forEach(function(key){
                fieldData["stockProductList[" + i + "]." + key] = stockProduct[key];
            });
        }

        delete fieldData.stockProductList;

        stockOrderService.addStockOrder(fieldData).then(function(response) {
            var resultBean = response.data;
            if (resultBean.resultCode == "0000") {
                // 请求数据成功
                $uibModalInstance.close();
            } else {
                // 请求数据异常
                tipDialogService.showPromptDialog(resultBean.resultMessage);
            }
        });
    };

    $scope.closeWin = function() {
        $uibModalInstance.close();
    }

});

/**
 * 产品编辑弹窗控制器
 */
qyjStoreApp.controller('stockOrderEditCtrl', function ($scope, $uibModalInstance, $filter, tipDialogService, stockOrderService, items) {
    for (var i = 0; i < items.stockProductList.length; i ++) {
        var stockProduct = items.stockProductList[i];
        var productModel = {"id" : stockProduct.productId, "title" : stockProduct.productTitle}
        stockProduct.productModel = productModel;
    }

    // 订单状态下拉列表数据
    $scope.orderStatusList = stockOrderService.getOrderStatusList();

    // 编辑产品数据
    $scope.edit = {};
    angular.copy(items, $scope.edit);

    // 获取产品下拉列
    $scope.getProductList = function(title) {
        var param = {currentPage : 1, pageSize : 0};
        if (title != null && title.trim() != "") {
            param.title = title.trim();
        }

        stockOrderService.loadProductList(param).then(function(response) {
            var resultBean = response.data;
            if (resultBean.resultCode == "0000") {
                $scope.productList = resultBean.result.recordList;
            }
        });
    }

    // 添加产品
    $scope.addProduct = function() {
        $scope.edit.stockProductList.push({});
    }

    // 删除产品
    $scope.deleteProduct = function(index, product) {
        $scope.edit.stockProductList.splice(index, 1);
    }

    // 保存产品信息
    $scope.saveOrder = function() {
        var fieldData = {};
        angular.copy($scope.edit, fieldData);

        if (!stockOrderService.validStockOrder(fieldData)) {
            return;
        }

        if (!stockOrderService.validStockProduct(fieldData.stockProductList)) {
            return;
        }

        for (var i = 0; i < fieldData.stockProductList.length; i ++) {
            var stockProduct = fieldData.stockProductList[i];
            delete stockProduct.productModel;
            Object.keys(stockProduct).forEach(function(key){
                fieldData["stockProductList[" + i + "]." + key] = stockProduct[key];
            });
        }

        delete fieldData.stockProductList;

        stockOrderService.updateStockOrder(fieldData).then(function(response) {
            var resultBean = response.data;
            if (resultBean.resultCode == "0000") {
                // 请求数据成功
                $uibModalInstance.close();
            } else {
                // 请求数据异常
                tipDialogService.showPromptDialog(resultBean.resultMessage);
            }
        });
    };

    $scope.closeWin = function() {
        $uibModalInstance.close();
    }
});


/**
 * 产品服务器
 */
qyjStoreApp.service('stockOrderService', function($http, tipDialogService) {
    // 请求获取订单分页数据
    this.loadStockOrderList = function (data) {
        return $http({
            method: "GET",
            url: qyjStoreApp.httpsHeader + "/admin/stockOrder/listStockOrderPage",
            params : data
        });
    }

    // 请求获取产品分页数据
    this.loadProductList = function (data) {
        return $http({
            method: "GET",
            url: qyjStoreApp.httpsHeader + "/admin/product/listProductPage",
            params : data
        });
    }

    // 添加进货单
    this.addStockOrder = function (data) {
        return $http({
            method: "POST",
            url: qyjStoreApp.httpsHeader + "/admin/stockOrder/addStockOrder",
            contentType: "application/json",
            params : data
        });
    }

    // 根据主键查询进货单信息
    this.getStockOrderInfo = function (stockId) {
        return $http({
            method: "GET",
            url: qyjStoreApp.httpsHeader + "/admin/stockOrder/getStockOrderInfo",
            params : {stockId : stockId}
        });
    }

    // 更新进货单
    this.updateStockOrder = function (data) {
        return $http({
            method: "POST",
            url: qyjStoreApp.httpsHeader + "/admin/stockOrder/updateStockOrder",
            contentType: "application/json",
            params : data
        });
    }

    // 删除进货单
    this.deleteStockOrder = function (stockId) {
        return $http({
            method: "POST",
            url: qyjStoreApp.httpsHeader + "/admin/stockOrder/deleteStockOrder",
            contentType: "application/json",
            params : {stockId : stockId}
        });
    }

    // 获取订单状态
    this.getOrderStatusList = function() {
        return new Array({name : "未支付", value : "UNPAY"}, {name : "已支付", value : "HASPAY"});
    }

    /**
     * 校验订单
     * @param stockOrder
     */
    this.validStockOrder = function(stockOrder) {
        if (stockOrder.orderAmount == null || isNaN(stockOrder.orderAmount) || stockOrder.orderAmount < 0) {
            tipDialogService.showPromptDialog("请检查订单金额是否填写正确");
            return false;
        }
        if (stockOrder.modifyAmount == null || isNaN(stockOrder.modifyAmount) || stockOrder.modifyAmount < 0) {
            tipDialogService.showPromptDialog("请检查调整金额是否填写正确");
            return false;
        }
        if (stockOrder.hasPayAmount == null || isNaN(stockOrder.hasPayAmount) || stockOrder.hasPayAmount < 0) {
            tipDialogService.showPromptDialog("请检查已支付金额是否填写正确");
            return false;
        }
        return true;
    }

    /**
     * 校验产品
     * @param stockProductList
     */
    this.validStockProduct =  function (stockProductList) {
        if (stockProductList == null || stockProductList.length == 0) {
            tipDialogService.showPromptDialog("进货单产品不能为空");
            return false;
        }

        var productIdArr = new Array();
        for (var i = 0; i < stockProductList.length; i ++) {
            var stockProduct = stockProductList[i];
            if (stockProduct.productId == null || stockProduct.productTitle == null ||
                stockProduct.productId == "" || stockProduct.productTitle == "") {
                tipDialogService.showPromptDialog("产品信息不能为空");
                return false;
            }
            if (stockProduct.number == null || isNaN(stockProduct.number) || stockProduct.number <= 0) {
                tipDialogService.showPromptDialog("产品数量不正确");
                return false;
            }
            if (stockProduct.price == null || isNaN(stockProduct.price) || stockProduct.price <= 0) {
                tipDialogService.showPromptDialog("产品金额不正确");
                return false;
            }
            if (arrayContain(productIdArr, stockProduct.productId)) {
                tipDialogService.showPromptDialog("产品信息存在重复[" + stockProduct.productTitle + "]");
                return false;
            }
            productIdArr.push(stockProduct.productId);
        }

        return true;
    }

    /**
     * 判断是否包含
     * @param array
     * @param obj
     * @returns {boolean}
     */
    function arrayContain(array, obj) {
        for (var i = 0; i < array.length; i++) {
            // 如果要求数据类型也一致，这里可使用恒等号===
            if (array[i] == obj)
                return true;
        }
        return false;
    }

});

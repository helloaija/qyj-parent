
var qyjStoreApp = angular.module("qyjStoreApp");

qyjStoreApp.controller("orderInfoCtrl", ["$scope", "$document", "$filter", "i18nService", "$uibModal",
	"tipDialogService", "storeInfoService",
    function($scope, $document, $filter, i18nService, $uibModal, tipDialogService, storeInfoService) {
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
                width : '3%',
                cellTemplate : '<div>{{grid.appScope.getRowIndex(grid, row)}}</div>'
            }, {
                field : 'title',
                displayName : '产品标题',
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                enableHiding : false,
                width : '20%',
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
                width : '6%',
                suppressRemoveSort : true,
                cellClass : "text-right",
                // 是否可编辑
                enableCellEdit : false,
                cellFilter : 'currency:"￥"'
            }, {
                field : "productType",
                displayName : "产品类型",
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                width : '5%',
                enableHiding : false,
                suppressRemoveSort : true,
                // 是否可编辑
                enableCellEdit : false,
                cellFilter : "productTypeFilter"
            }, {
                field : "productUnit",
                displayName : "单位",
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                enableHiding : false,
                width : '6%',
                suppressRemoveSort : true,
                // 是否可编辑
                enableCellEdit : false
            }, {
                field : "number",
                displayName : "剩余数量",
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                enableHiding : false,
                width : '6%',
                cellClass : "text-right",
                suppressRemoveSort : true,
                // 是否可编辑
                enableCellEdit : false
            }, {
                field : "soldNumber",
                displayName : "已售数量",
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                enableHiding : false,
                cellClass : "text-right",
                width : '6%',
                suppressRemoveSort : true,
                // 是否可编辑
                enableCellEdit : false
            }, {
                field : "unpayNumber",
                displayName : "未支付数量",
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                cellClass : "text-right",
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
                field : "remark",
                displayName : "产品说明",
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                enableHiding : false,
                cellClass : "text-left",
                suppressRemoveSort : true,
                // 是否可编辑
                enableCellEdit : false
            }/*, {
                field : 'action',
                displayName : "操作",
                width : '12%',
                cellTemplate : '<div>' +
                    '<button type="button" class="btn btn-link"' +
                    ' ng-click="grid.appScope.showViewProductWin(row.entity.id)">详情</button>' +
                    '<button type="button" class="btn btn-link"' +
                    ' ng-click="grid.appScope.showEditProductWin(row.entity.id)">修改</button>' +
                    '<button type="button" class="btn btn-link"' +
                    ' ng-click="grid.appScope.deleteProductInfo(row.entity.id)">删除</button></div>',
                // 是否显示列头部菜单按钮
                enableColumnMenu : false,
                enableHiding : false,
                suppressRemoveSort : true,
                // 是否可编辑
                enableCellEdit : false
            } */],

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
            storeInfoService.loadProductList(param).then(function(response) {
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
	}
]);


/**
 * 产品服务器
 */
qyjStoreApp.service('storeInfoService', ["$http",
    function($http) {
        // 请求获取产品分页数据
        this.loadProductList = function (data) {
            return $http({
                method: "GET",
                url: qyjStoreApp.httpsHeader + "/admin/product/listProductPage",
                params : data
            });
        }

        // 根据主键查询产品信息
        this.getProductInfo = function (productId) {
            return $http({
                method: "GET",
                url: qyjStoreApp.httpsHeader + "/admin/product/getProductInfo",
                params : {productId : productId}
            });
        }
    }
]);

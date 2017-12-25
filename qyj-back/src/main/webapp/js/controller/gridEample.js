// 列表属性
$scope.gridOptions = {
	data : 'myData',
	columnDefs : [ {
		field : 'name',
		displayName : '名字',
		width : '10%',
		// 是否显示列头部菜单按钮
		enableColumnMenu : false,
		enableHiding : false,
		suppressRemoveSort : true,
		// 是否可编辑
		enableCellEdit : false
	
	}, {
		field : "age"
	}, {
		field : "birthday"
	}, {
		field : "salary"
	} ],

	// 是否排序
	enableSorting : false, 
	// 是否使用自定义排序规则
	useExternalSorting : false, 
	// 是否显示grid 菜单
	enableGridMenu : false, 
	// 是否显示grid footer
	showGridFooter : true, 
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
	// 自定义底部分页代码
	// paginationTemplate:"<div></div>", 
	// 总数量
	totalItems : 15, 
	// 是否使用分页按钮
	useExternalPagination : true,

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
		if (row.entity.age > 45) {
			row.grid.api.selection.selectRow(row.entity); // 选中行
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

var getPage = function(curPage, pageSize) {
	var firstRow = (curPage - 1) * pageSize;
	$scope.gridOptions.totalItems = mydefalutData.length;
	$scope.gridOptions.data = mydefalutData.slice(firstRow, firstRow + pageSize);
	// 或者像下面这种写法
	// $scope.myData = mydefalutData.slice(firstRow, firstRow + pageSize);
};

var mydefalutData = [{ name: "Moroni", age: 50, birthday: "Oct 28, 1970", salary: "60,000" },
	{ name: "Tiancum", age: 43, birthday: "Feb 12, 1985", salary: "70,000" },
	{ name: "Jacob", age: 27, birthday: "Aug 23, 1983", salary: "50,000" },
	{ name: "Nephi", age: 29, birthday: "May 31, 2010", salary: "40,000" },
	{ name: "Enos", age: 34, birthday: "Aug 3, 2008", salary: "30,000" },
	{ name: "Moroni", age: 50, birthday: "Oct 28, 1970", salary: "60,000" },
	{ name: "Tiancum", age: 43, birthday: "Feb 12, 1985", salary: "70,000" },
	{ name: "Jacob", age: 27, birthday: "Aug 23, 1983", salary: "40,000" },
	{ name: "Nephi", age: 29, birthday: "May 31, 2010", salary: "50,000" },
	{ name: "Enos", age: 34, birthday: "Aug 3, 2008", salary: "30,000" },
	{ name: "Moroni", age: 50, birthday: "Oct 28, 1970", salary: "60,000" },
	{ name: "Tiancum", age: 43, birthday: "Feb 12, 1985", salary: "70,000" },
	{ name: "Jacob", age: 27, birthday: "Aug 23, 1983", salary: "40,000" },
	{ name: "Nephi", age: 29, birthday: "May 31, 2010", salary: "50,000" },
	{ name: "Enos", age: 34, birthday: "Aug 3, 2008", salary: "30,000" }];

getPage(1, $scope.gridOptions.paginationPageSize);
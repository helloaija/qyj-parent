var qyjStoreApp = angular.module("qyjStoreApp");

qyjStoreApp.directive('loading', function () {
    return {
        restrict: 'E',
        transclude: true,
        template: '<div ng-show="loading" class="loading" id="allDiv" style="position:fixed; top:0px; left:0px; width:100%; height:100%; display:none; background-color:#000; opacity: 0.5; z-index:99999;">'
            + '<img alt="加载中" src="images/loading.gif" style="vertical-align: middle;width:100px; height:100px; position: absolute; top:50%; left:50%; margin-top: -50px; margin-left:-50px;"/></div>',
        link: function (scope, element, attr) {
            scope.$watch('loading', function (val) {
                if (val) {
                    document.getElementById("allDiv").style.display = "block";
                } else {
                    document.getElementById("allDiv").style.display = 'none';
                }
            });
        }
    }
});

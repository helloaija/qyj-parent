var qyjApp = angular.module("qyjApp");

qyjApp.directive('cityPicker', function () {
    return {
        restrict: 'A',
        template: '<span>替换元素</span>',
        replace : true,
        scope : true,
        controller : function() {
        	console.log("controller");
        },
        compile : function(iElem, iAttrs) {
        	
        	
        	return {
				pre : function(scope, iElem, iAttrs) {
					scope.picker = {};
		        	scope.picker.mainDiv = document.createElement("div");bottom: 0;
		        	
		        	scope.picker.mainDiv.innerHTML = '<div class="area_ctrl slideInUp">' +
			            '<div class="area_btn_box">' +
			            '<div class="area_btn larea_cancel">取消</div>' +
			            '<div class="area_btn larea_finish">确定</div>' +
			            '</div>' +
			            '<div class="area_roll_mask">' +
			            '<div class="area_roll">' +
			            '<div>' +
			            '<div class="gear area_province" data-areatype="area_province"></div>' +
			            '<div class="area_grid">' +
			            '</div>' +
			            '</div>' +
			            '<div>' +
			            '<div class="gear area_city" data-areatype="area_city"></div>' +
			            '<div class="area_grid">' +
			            '</div>' +
			            '</div>' +
			            '<div>' +
			            '<div class="gear area_county" data-areatype="area_county"></div>' +
			            '<div class="area_grid">' +
			            '</div>' +
			            '</div>' +
			            '</div>' +
			            '</div>' +
			            '</div>';
		        	
		        	scope.picker.mainDiv.append(scope.picker.innerHTML);
		        	
					iElem.on("click", function() {
						document.body.appendChild(scope.picker.mainDiv);
						
					});
					console.log(name + ': pre-link');
				},
				post : function(scope, iElem, iAttrs) {
					console.log(name + ': post-link');
				}
			}
        }
    };
});
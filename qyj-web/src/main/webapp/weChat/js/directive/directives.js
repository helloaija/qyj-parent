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
//		        	angular.element(scope.picker.mainDiv).css("height", "120px");
		        	
		        	scope.picker.mainDiv.innerHTML = 
		        		'<div>' +
		        			'<div style="width: 100%; display: inline-block;">' +
		        				'<div style="float:left;">取消</div>' +
		        				'<div style="float:right;">确定</div>' +
		        			'</div>' +
		        			'<div style="width: 100%; display: inline-block;position:relative;">' +
			        			'<hr style="border: 0.5px solid #eee;margin-top: 20px;position: absolute;width:100%;"/>' +
		        				'<hr style="border: 0.5px solid #eee;margin-top: 40px;position: absolute;width:100%;"/>' +
		        				'<div style="float:left;width:32%;overflow:hidden;height: 55px;">' +
			        				'<div id="province" style="width:110%;height:100%;overflow-y: scroll;overflow-x: hidden;">' +
			        					'<div style="width:100%; text-align: center;">a1</div>' +
			        					'<div style="width:100%; text-align: center;">a2</div>' +
			        					'<div style="width:100%; text-align: center;">a3</div>' +
			        					'<div style="width:100%; text-align: center;">a4</div>' +
			        					'<div style="width:100%; text-align: center;">a5</div>' +
			        					'<div style="width:100%; text-align: center;">a6</div>' +
			        					'<div style="width:100%; text-align: center;">a7</div>' +
			        					'<div style="width:100%; text-align: center;">a8</div>' +
			        				'</div>' +
		        				'</div>' +
		        				'<div style="float:left;width:32%;overflow:hidden;height: 55px;overflow-y: scroll;overflow-x: hidden;">' +
			        				'<div id="city" style="width:110%;height:100%;overflow-y: scroll;">' +
			        					'<div style="width:100%; text-align: center;">b1</div>' +
			        					'<div style="width:100%; text-align: center;">b2</div>' +
			        					'<div style="width:100%; text-align: center;">b3</div>' +
			        					'<div style="width:100%; text-align: center;">b4</div>' +
			        					'<div style="width:100%; text-align: center;">b5</div>' +
			        					'<div style="width:100%; text-align: center;">b6</div>' +
			        					'<div style="width:100%; text-align: center;">b7</div>' +
			        					'<div style="width:100%; text-align: center;">b8</div>' +
			        				'</div>' +
		        				'</div>' +
		        				'<div style="float:left;width:32%;overflow:hidden;height: 55px;overflow-y: scroll;overflow-x: hidden;">' +
			        				'<div id="county" style="width:110%;height:100%;overflow: auto;">' +
			        					'<div style="width:100%; text-align: center;">c1</div>' +
			        					'<div style="width:100%; text-align: center;">c2</div>' +
			        					'<div style="width:100%; text-align: center;">c3</div>' +
			        					'<div style="width:100%; text-align: center;">c4</div>' +
			        					'<div style="width:100%; text-align: center;">c5</div>' +
			        					'<div style="width:100%; text-align: center;">c6</div>' +
			        					'<div style="width:100%; text-align: center;">c7</div>' +
			        					'<div style="width:100%; text-align: center;">c8</div>' +
			        				'</div>' +
		        				'</div>' +
		        			'</div>' +
			            '</div>';
		        	
		        	var provinceDiv = angular.element(scope.picker.mainDiv).find("#province");
		        	var cityDiv = angular.element(scope.picker.mainDiv).find("#city");
		        	var countyDiv = angular.element(scope.picker.mainDiv).find("#county");
		        	provinceDiv.on("scroll", function() {
		        		var scrollTop = provinceDiv.scrollTop();
		        		setTimeout(function() {
		        			if (scrollTop == provinceDiv.scrollTop()) {
		        				console.log(scrollTop);
		        				if (scrollTop % 20 >= 10) {
		        					scrollTop = scrollTop + 20 - (scrollTop % 20);
		        				} else {
		        					scrollTop = scrollTop - (scrollTop % 20);
		        				}
		        				provinceDiv.scrollTop(scrollTop)
		        				console.log(scrollTop);
		        			}
		        		}, 100);
		        	});
		        	
		        	cityDiv.on("scroll", function() {
		        		var scrollTop = provinceDiv.scrollTop();
		        		setTimeout(function() {
		        			if (scrollTop == provinceDiv.scrollTop()) {
		        				console.log(scrollTop);
		        				if (scrollTop % 20 >= 10) {
		        					scrollTop = scrollTop + 20 - (scrollTop % 20);
		        				} else {
		        					scrollTop = scrollTop - (scrollTop % 20);
		        				}
		        				provinceDiv.scrollTop(scrollTop)
		        				console.log(scrollTop);
		        			}
		        		}, 100);
		        	});
		        	
		        	countyDiv.on("scroll", function() {
		        		var scrollTop = provinceDiv.scrollTop();
		        		setTimeout(function() {
		        			if (scrollTop == provinceDiv.scrollTop()) {
		        				console.log(scrollTop);
		        				if (scrollTop % 20 >= 10) {
		        					scrollTop = scrollTop + 20 - (scrollTop % 20);
		        				} else {
		        					scrollTop = scrollTop - (scrollTop % 20);
		        				}
		        				provinceDiv.scrollTop(scrollTop)
		        				console.log(scrollTop);
		        			}
		        		}, 100);
		        	});
		        	
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
(function($) {

	$('.dd').nestable();
	
	$(".drag-switch").click(function(){
		var value = $(this).prop("checked");
		if(value) {
			$(this).parents(".widget-header").next().find(".dd-list").removeClass("custom-nodrag");
		}
		else {
			$(this).parents(".widget-header").next().find(".dd-list").addClass("custom-nodrag");
		}	
	});
	
	$("#societyConfigSubmitBtn").click(function() {
		
		var isLcCheck = $("#lcSectionId").prop("checked");
		if(isLcCheck){
			var lcGeneralHeadList = $("#lcGeneralHeadList").find("li").map(function(){
				return $(this).attr("data-id");
			}).get().join();
			$("#liabilitesGeneralHeadIds").val(lcGeneralHeadList);
			
			var lcOrderList = $("#lcGeneralHeadList").find("li").map(function(){
				var orderId = $(this).attr("data-orderId");
				if(orderId == "")
					return "0";
				else
					return $(this).attr("data-orderId");
				
			}).get().join();
			$("#liabilitesOrderIds").val(lcOrderList);
		}
		
		var isPaCheck = $("#paSectionId").prop("checked");
		if(isPaCheck) {
			var paGeneralHeadList = $("#paGeneralHeadList").find("li").map(function(){
				return $(this).attr("data-id");
			}).get().join();
			$("#assetGeneralHeadIds").val(paGeneralHeadList);
			
			var paOrderList = $("#paGeneralHeadList").find("li").map(function(){
				var orderId = $(this).attr("data-orderId");
				if(orderId == "")
					return "0";
				else
					return $(this).attr("data-orderId");
			}).get().join();
			$("#assetOrderIds").val(paOrderList);
		}
		
		var isExCheck = $("#exSectionId").prop("checked");
		if(isExCheck) {
			var exGeneralHeadList = $("#exGeneralHeadList").find("li").map(function(){
				return $(this).attr("data-id");
			}).get().join();
			$("#expenseGeneralHeadIds").val(exGeneralHeadList);
			
			var exOrderList = $("#exGeneralHeadList").find("li").map(function(){
				var orderId = $(this).attr("data-orderId");
				if(orderId == "")
					return "0";
				else
					return $(this).attr("data-orderId");
			}).get().join();
			$("#expenseOrderIds").val(exOrderList);
		}
		
		var isInCheck = $("#inSectionId").prop("checked");
		if(isInCheck) {
			var inGeneralHeadList = $("#inGeneralHeadList").find("li").map(function(){
				return $(this).attr("data-id");
			}).get().join();
			$("#incomeGeneralHeadIds").val(inGeneralHeadList);
			
			var inOrderList = $("#inGeneralHeadList").find("li").map(function(){
				var orderId = $(this).attr("data-orderId");
				if(orderId == "")
					return "0";
				else
					return $(this).attr("data-orderId");
			}).get().join();
			$("#incomeOrderIds").val(inOrderList);
		}
		
		var startDate = $("#configStartDate").val();
		if(startDate != '')
			$("#startDate").val(startDate);
		
		var endDate = $("#configEndDate").val();
		if(endDate != '')
			$("#endDate").val(endDate);
		
		var maintenanceCycle = $("#configMaintenanceDate").val();
		if(maintenanceCycle != 0)
			$("#maintenanceCycle").val(maintenanceCycle);
		
		var maintenancePaymentInterestRate = $("#maintenancePaymentInterestRate").val();
		if(maintenancePaymentInterestRate != '')
			$("#maintenancePaymentInterestRate").val(maintenancePaymentInterestRate);
		
		var maintenancePaymentChequeName = $("#maintenancePaymentChequeName").val();
		if(maintenancePaymentChequeName != '')
			$("#maintenancePaymentChequeName").val(maintenancePaymentChequeName);
		
		return true;
	});

	$('.dd-handle a').on('mousedown', function(e) {
		e.stopPropagation();
	});

	$('[data-rel="tooltip"]').tooltip();

	$('#simple-colorpicker-1').ace_colorpicker({
		pull_right : true
	}).on('change', function() {
		var color_class = $(this).find('option:selected').data('class');
		var new_class = 'widget-box';
		if (color_class != 'default')
			new_class += ' widget-color-' + color_class;
		$(this).closest('.widget-box').attr('class', new_class);
	});

	// scrollables
	$('.scrollable').each(function() {
		var $this = $(this);
		$(this).ace_scroll({
			size : $this.attr('data-size') || 100,
		// styleClass: 'scroll-left scroll-margin scroll-thin scroll-dark
		// scroll-light no-track scroll-visible'
		});
	});
	$('.scrollable-horizontal').each(function() {
		var $this = $(this);
		$(this).ace_scroll({
			horizontal : true,
			styleClass : 'scroll-top',// show the scrollbars on top(default is
										// bottom)
			size : $this.attr('data-size') || 500,
			mouseWheelLock : true
		}).css({
			'padding-top' : 12
		});
	});

	$(window).on('resize.scroll_reset', function() {
		$('.scrollable-horizontal').ace_scroll('reset');
	});

	$('#id-checkbox-vertical').prop('checked', false).on(
			'click',
			function() {
				$('#widget-toolbox-1').toggleClass('toolbox-vertical').find(
						'.btn-group').toggleClass('btn-group-vertical').filter(
						':first').toggleClass('hidden').parent().toggleClass(
						'btn-toolbar')
			});

	/**
	 * //or use slimScroll plugin $('.slim-scrollable').each(function () { var
	 * $this = $(this); $this.slimScroll({ height: $this.data('height') || 100,
	 * railVisible:true }); });
	 */

	/**
	 * $('.widget-box').on('setting.ace.widget' , function(e) {
	 * e.preventDefault(); });
	 */

	/**
	 * $('.widget-box').on('show.ace.widget', function(e) {
	 * //e.preventDefault(); //this = the widget-box });
	 * $('.widget-box').on('reload.ace.widget', function(e) { //this = the
	 * widget-box });
	 */

	// $('#my-widget-box').widget_box('hide');

	// widget boxes
	// widget box drag & drop example
	$('.widget-container-col').sortable({
		connectWith : '.widget-container-col',
		items : '> .widget-box',
		handle : ace.vars['touch'] ? '.widget-header' : false,
		cancel : '.fullscreen',
		opacity : 0.8,
		revert : true,
		forceHelperSize : true,
		placeholder : 'widget-placeholder',
		forcePlaceholderSize : true,
		tolerance : 'pointer',
		start : function(event, ui) {
			// when an element is moved, it's parent becomes empty with almost
			// zero height.
			// we set a min-height for it to be large enough so that later we
			// can easily drop elements back onto it
			ui.item.parent().css({
				'min-height' : ui.item.height()
			})
			// ui.sender.css({'min-height':ui.item.height() , 'background-color'
			// : '#F5F5F5'})
		},
		update : function(event, ui) {
			ui.item.parent({
				'min-height' : ''
			})
			// p.style.removeProperty('background-color');

			// save widget positions
			var widget_order = {}
			$('.widget-container-col').each(function() {
				var container_id = $(this).attr('id');
				widget_order[container_id] = []

				$(this).find('> .widget-box').each(function() {
					var widget_id = $(this).attr('id');
					widget_order[container_id].push(widget_id);
					// now we know each container contains which widgets
				});
			});

			ace.data.set('demo', 'widget-order', widget_order, null, true);
		}
	});

	// /////////////////////

	// when a widget is shown/hidden/closed, we save its state for later
	// retrieval
	$(document).on('shown.ace.widget hidden.ace.widget closed.ace.widget',
			'.widget-box', function(event) {
				var widgets = ace.data.get('demo', 'widget-state', true);
				if (widgets == null)
					widgets = {}

				var id = $(this).attr('id');
				widgets[id] = event.type;
				ace.data.set('demo', 'widget-state', widgets, null, true);
			});

	(function() {
		// restore widget order
		var container_list = ace.data.get('demo', 'widget-order', true);
		if (container_list) {
			for ( var container_id in container_list)
				if (container_list.hasOwnProperty(container_id)) {

					var widgets_inside_container = container_list[container_id];
					if (widgets_inside_container.length == 0)
						continue;

					for (var i = 0; i < widgets_inside_container.length; i++) {
						var widget = widgets_inside_container[i];
						$('#' + widget).appendTo('#' + container_id);
					}

				}
		}

		// restore widget state
		var widgets = ace.data.get('demo', 'widget-state', true);
		if (widgets != null) {
			for ( var id in widgets)
				if (widgets.hasOwnProperty(id)) {
					var state = widgets[id];
					var widget = $('#' + id);
					if ((state == 'shown' && widget.hasClass('collapsed'))
							|| (state == 'hidden' && !widget
									.hasClass('collapsed'))) {
						widget.widget_box('toggleFast');
					} else if (state == 'closed') {
						widget.widget_box('closeFast');
					}
				}
		}

		$('#main-widget-container').removeClass('invisible');

		// reset saved positions and states
		$('#reset-widgets').on('click', function() {
			ace.data.remove('demo', 'widget-state');
			ace.data.remove('demo', 'widget-order');
			document.location.reload();
		});

	})();

})(jQuery);
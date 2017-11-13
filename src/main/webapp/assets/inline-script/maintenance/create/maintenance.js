(function($){
	
	$('textarea[data-provide="markdown"]').each(function(){
        var $this = $(this);

		if ($this.data('markdown')) {
		  $this.data('markdown').showEditor();
		}
		else $this.markdown();
		
		$this.parent().find('.btn').addClass('btn-white');
    });
	
	function showErrorAlert (reason, detail) {
		var msg='';
		if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
		else {
			//console.log("error uploading file", reason, detail);
		}
		$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
		 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
	}
	$('#editor2').css({'height':'200px'}).ace_wysiwyg({
		toolbar_place: function(toolbar) {
			return $(this).closest('.widget-box')
			       .find('.widget-header').prepend(toolbar)
				   .find('.wysiwyg-toolbar').addClass('inline');
		},
		toolbar:
		[
			'bold',
			null,
			'insertorderedlist'
		],
		speech_button: false
	});

	$('[data-toggle="buttons"] .btn').on('click', function(e){
		var target = $(this).find('input[type=radio]');
		var which = parseInt(target.val());
		var toolbar = $('#editor1').prev().get(0);
		if(which >= 1 && which <= 4) {
			toolbar.className = toolbar.className.replace(/wysiwyg\-style(1|2)/g , '');
			if(which == 1) $(toolbar).addClass('wysiwyg-style1');
			else if(which == 2) $(toolbar).addClass('wysiwyg-style2');
			if(which == 4) {
				$(toolbar).find('.btn-group > .btn').addClass('btn-white btn-round');
			} else $(toolbar).find('.btn-group > .btn-white').removeClass('btn-white btn-round');
		}
	});

	//RESIZE IMAGE
	
	//Add Image Resize Functionality to Chrome and Safari
	//webkit browsers don't have image resize functionality when content is editable
	//so let's add something using jQuery UI resizable
	//another option would be opening a dialog for user to enter dimensions.
	if ( typeof jQuery.ui !== 'undefined' && ace.vars['webkit'] ) {
		
		var lastResizableImg = null;
		function destroyResizable() {
			if(lastResizableImg == null) return;
			lastResizableImg.resizable( "destroy" );
			lastResizableImg.removeData('resizable');
			lastResizableImg = null;
		}

		var enableImageResize = function() {
			$('.wysiwyg-editor')
			.on('mousedown', function(e) {
				var target = $(e.target);
				if( e.target instanceof HTMLImageElement ) {
					if( !target.data('resizable') ) {
						target.resizable({
							aspectRatio: e.target.width / e.target.height,
						});
						target.data('resizable', true);
						
						if( lastResizableImg != null ) {
							//disable previous resizable image
							lastResizableImg.resizable( "destroy" );
							lastResizableImg.removeData('resizable');
						}
						lastResizableImg = target;
					}
				}
			})
			.on('click', function(e) {
				if( lastResizableImg != null && !(e.target instanceof HTMLImageElement) ) {
					destroyResizable();
				}
			})
			.on('keydown', function() {
				destroyResizable();
			});
	    }

		enableImageResize();

		/**
		//or we can load the jQuery UI dynamically only if needed
		if (typeof jQuery.ui !== 'undefined') enableImageResize();
		else {//load jQuery UI if not loaded
			//in Ace demo ../assets will be replaced by correct assets path
			$.getScript("../assets/js/jquery-ui.custom.min.js", function(data, textStatus, jqxhr) {
				enableImageResize()
			});
		}
		*/
	}
	
	//////////////////////////////////////////////////////////////
	
	$("#addNoteId").click(function() {
		var note = $("#additinalNoteId").val();
		if(note != "") {
			$("#additonalNoteList").append('<li><span>' + note + '</span> <input name="additionalNote" type="hidden" value="' + note + '" /><a style="cursor: pointer;text-decoration: underline;" class="delete-note text-danger">Delete</a></li>');
			$("#additinalNoteId").val("");
		}
	});
	
	$("#clearNoteId").click(function() {
		$("#additonalNoteList").html('');
	});
	
	$("#additonalNoteList").on("click", ".delete-note", function() {
		
		var li = $(this).parent();
		var noteId = li.attr("data-noteId");
		var deleteNoteId = $("#deletedNoteId").val();
		deleteNoteId = deleteNoteId + "," + noteId;
		$("#deletedNoteId").val(deleteNoteId);
		
		$(this).parent().remove();
	});
	
	$("#generateMaintenanceReportBtn").click(function() {
		
	
		var isValid = true;
		try {
			
			var paymentDueDate = $("#paymentDueDate").val();
			if(paymentDueDate == "") {
				showValidationMsg("Payment Due Date","Please enter value for payment due date.");
				isValid = false;
			}
			
			var paymentCycle = $("#paymentCycle").val();
			if(paymentCycle == "") {
				showValidationMsg("Payment Cycle","Please select payment cycle.");
				isValid = false;
			}
			
			if(paymentDueDate != "" && paymentCycle != "") {
				
				var cycleDueDate = new Date(paymentDueDate);
				var cycleEndDate = new Date($.trim(paymentCycle.split("to")[1]));
				
				if(cycleDueDate < cycleEndDate) {
					showValidationMsg("Payment Due Date","Please select date which is greater than cycle end date.");
					isValid = false;
				}
			}
		}
		catch(err) {
			return false;
		}
		return isValid;
	});
	
	$("#paymentDueDateId").change(function(){
		$("#paymentDueDate").val($(this).val());
	});
	
	$("#generateReceiptId").click(function(){
	
		$("#spinnerId").removeClass("hide");
		
		var paymentDueDate = $("#paymentDueDate").val();
		var paymentCycle = $("#paymentCycle").val();
		
		//perform validation
		if(validatePaymentDueDate(paymentCycle, paymentDueDate)) {
			
			var paymentCycleDateArr = paymentCycle.split("to");
			//Object send to server to populate maintenance receipt data
			var cycle = {};
			cycle.cycleId = $("#maintenanceTableId").attr("data-cycleId");
			cycle.paymentDueDate = paymentDueDate;
			cycle.startDate = $.trim(paymentCycleDateArr[0]);
			cycle.endDate = $.trim(paymentCycleDateArr[1]);
			cycle.notes = $("#additonalNoteList").find("li").map(function() {
										var note = {};
										note.noteId = $(this).attr("data-noteId");
										note.noteText = $(this).find("span").text();
										return note;
								   }).get();
			cycle.deleteNoteIds = $("#deletedNoteId").val();
			
			cycle.receipts = [];
			
			var maintenanceHeadNameArr = $("#maintenanceTableId thead tr").find("th:gt(1)").map(function(){
				return $(this).text();
			}).get();
			
			$("#maintenanceTableId tbody").find("tr").map(function() {
				
				var memberReceipt = {};
				memberReceipt.receiptId = $(this).attr("data-receiptId");
				memberReceipt.memberId = $(this).find("td:eq(0)").attr("data-memberId");
				memberReceipt.memberName = $(this).find("td:eq(0)").text();
				memberReceipt.billNumber = $(this).find("td:eq(1)").text();
				memberReceipt.chargeList = [];
				
				var maintenanceHeadIdAndValueArr = $(this).find("td:gt(1)").map(function(){
					var maintenanceHeadIdAndValue = {
							generalHeadId : $(this).attr("data-maintenanceHeadId"),
							value : $(this).text(),
							chargeId : $(this).attr("data-chargeId")
					};
					return maintenanceHeadIdAndValue;
				}).get();
				
				
				if(maintenanceHeadNameArr.length == maintenanceHeadIdAndValueArr.length) {
					for (var i = 0; i < maintenanceHeadNameArr.length; i++) {
						
						var charge = {};
						charge.chargeId = maintenanceHeadIdAndValueArr[i].chargeId;
						charge.srNumber = (i + 1);
						charge.maintenanceHeadId = maintenanceHeadIdAndValueArr[i].generalHeadId;
						charge.chargeValue = maintenanceHeadIdAndValueArr[i].value;
						charge.maintenanceHeadName = maintenanceHeadNameArr[i];
						memberReceipt.chargeList.push(charge);
					}
				}
				
				cycle.receipts.push(memberReceipt);
			});
			
			var cycleJson = JSON.stringify(cycle);
			$.ajax({
				url : 'saveMaintenanceData',
				contentType : "application/json",
				type : 'POST',
				data : cycleJson,
				success : function(response) {
					$('#content').html("");
					$('#content').append(response);
					downloadAllMaintenanceReceipt();
					$("#spinnerId").addClass("hide");
					
					window.location.replace("viewMaintenanceReport");
				},
				error : function(e) {
					showValidationMsg("Error","There is error while saving receipt data.");
					$("#spinnerId").addClass("hide");
				}
			});
		}
	});
	
	$("#sendEmailId").click(function() {
		
		
		var email = {};
		email.cycleId = $("#maintenanceTableId").attr("data-cycleId");
		
		var emailJson = JSON.stringify(email);
		
		$.ajax({
			url : 'sendEmail',
			contentType : "application/json",
			type : 'POST',
			data : emailJson,
			success : function(response) {
				showSuccessMsg("Email", "Mail sending process is running, Please check notificaion report to know staus.");
			},
			error : function(e) {
				showValidationMsg("Error","There is error while sending email to soicety member. Please contact society soft support team.");
			}
		});
	});
	
	function validatePaymentDueDate(paymentCycle, paymentDueDate) {
		var isValid = true;
		try {
			if(paymentDueDate != "" && paymentCycle != "") {
				
				var cycleDueDate = new Date(paymentDueDate);
				var cycleEndDate = new Date($.trim(paymentCycle.split("to")[1]));
				
				if(cycleDueDate < cycleEndDate) {
					showValidationMsg("Payment Due Date","Please select date which is greater than cycle end date.");
					isValid = false;
				}
			}
		}
		catch(err) {
			return false;
		}
		return isValid;
	}
	
})(jQuery);

function downloadAllMaintenanceReceipt() {
    var pdf = new jsPDF('p', 'pt', 'a3');
    // source can be HTML-formatted string, or a reference
    // to an actual DOM element from which the text will be scraped.
    source = $('#content')[0];

    // we support special element handlers. Register them with jQuery-style 
    // ID selector for either ID or node name. ("#iAmID", "div", "span" etc.)
    // There is no support for any other type of selectors 
    // (class, of compound) at this time.
    specialElementHandlers = {
        // element with id of "bypass" - jQuery style selector
        '#bypassme': function (element, renderer) {
            // true = "handled elsewhere, bypass text extraction"
            return true;
        }
    };
    margins = {
        top: 20,
        bottom: 20,
        left: 20,
        width: 800
    };
    // all coords and widths are in jsPDF instance's declared units
    // 'inches' in this case
    pdf.fromHTML(
        source, // HTML string or DOM elem ref.
        margins.left, // x coord
        margins.top, 
        { // y coord
            'width': margins.width, // max width of content on PDF
            'elementHandlers': specialElementHandlers
        },

        function (dispose) {
            // dispose: object with X, Y of the last line add to the PDF 
            //          this allow the insertion of new lines after html
            pdf.save('maintenance_receipt.pdf');
        }, margins
    );
}

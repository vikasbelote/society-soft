(function($){
	
	$("#generateReceiptId").click(function(){
		
		var societyName = $("#societyNameId").val();
		var societyAddress = $("#societyAddressId").val();
		var maintenancePaymentDueInterest = $("#maintenancePaymentDueInterestId").val();
		var maintenancePaymentChequeName = $("#maintenancePaymentChequeNameId").val();
		
		var maintenanceReceiptObjArr = [];
		
		var generalHeadKeyArr = $("#maintenanceTableId thead tr").find("th:gt(0)").map(function(){
			return $(this).text();
		}).get();
		
		$("#maintenanceTableId tbody").find("tr").map(function() {
			
			var maintenanceReceiptObj = {};
			maintenanceReceiptObj.name = $(this).find("td:eq(0)").text();
			
			var generalHeadValueArr = $(this).find("td:gt(0)").map(function(){
				return $(this).text();
			}).get();
			
			var generalHeadMap = [];
			if(generalHeadKeyArr.length == generalHeadValueArr.length) {
				for (var i = 0; i < generalHeadKeyArr.length; i++) {
					var generalHead = {
						name : generalHeadKeyArr[i],
						chargeValue : generalHeadValueArr[i]
					};
					generalHeadMap.push(generalHead);
				}
			}
			maintenanceReceiptObj.generalHeadMapArr = generalHeadMap;
			maintenanceReceiptObjArr.push(maintenanceReceiptObj);
		});
		
		for(var i = 0; i < maintenanceReceiptObjArr.length; i++) {
			var maintenanceReceiptObj = maintenanceReceiptObjArr[i];
			$("#content").append('<p style="font-size: 18px; margin-left: 150px;">'+ societyName +'</p>');
			$("#content").append('<p style="margin-left: 170px;">'+ societyAddress +'</p>');
			$("#content").append('<p style="font-size: 16px;">Shri : ' + maintenanceReceiptObj.name + '</p>');
			
			var tableHtml = "";
			tableHtml = tableHtml + '<table border="1">';
			tableHtml = tableHtml + '<thead><tr><th>Sr No</th><th>Particulars</th><th>Amount</th></tr></thead>';
			tableHtml = tableHtml + '<tbody>';
			var generalHeadMap = maintenanceReceiptObj.generalHeadMapArr;
			var totalValue = 0;
			for(var j = 0; j < generalHeadMap.length; j++) {
				var generalHead = generalHeadMap[j];
				totalValue = parseInt(totalValue) + parseInt(generalHead.chargeValue);
				tableHtml = tableHtml + '<tr>';
				tableHtml = tableHtml + '<td>' + (parseInt(j) + 1) + '</td>';
				tableHtml = tableHtml + '<td>' + generalHead.name + '</td>';
				tableHtml = tableHtml + '<td>' + generalHead.chargeValue + '</td>';
				tableHtml = tableHtml + '</tr>';
				$("#content").append('</tr>');
			}
			tableHtml = tableHtml + '<tr>';
			tableHtml = tableHtml + '<td></td>';
			tableHtml = tableHtml + '<td>Total Payable Amount</td>';
			tableHtml = tableHtml + '<td>' + totalValue + '</td>';
			tableHtml = tableHtml + '</tr>';
			tableHtml = tableHtml + '</tbody>';
			tableHtml = tableHtml + '</table>';
			$("#content").append(tableHtml);
			$("#content").append('<p style="font-size: 16px;">Payment due date : 02/10/2017</p>');
			$("#content").append('<p style="font-size: 16px; text-align: justify;">If the payment is '+
					 'not received on and before due date, Interest @' + maintenancePaymentDueInterest + '% p.a. on entire '+
					 'amount wil be applicable</p>');
			$("#content").append('<p style="font-size: 16px;">cheque should be drawan in the favour of "<strong>' + maintenancePaymentChequeName + '</strong>"</p>');
			$("#content").append('<!-- ADD_PAGE -->');
		}
		
		downloadAllMaintenanceReceipt();
	});
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


jQuery(function($){
	
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

	//$('#editor1').ace_wysiwyg();//this will create the default editor will all buttons

	//but we want to change a few buttons colors for the third style
	

	
	/**
	//make the editor have all the available height
	$(window).on('resize.editor', function() {
		var offset = $('#editor1').parent().offset();
		var winHeight =  $(this).height();
		
		$('#editor1').css({'height':winHeight - offset.top - 10, 'max-height': 'none'});
	}).triggerHandler('resize.editor');
	*/
	

	$('#editor2').css({'height':'200px'}).ace_wysiwyg({
		toolbar_place: function(toolbar) {
			return $(this).closest('.widget-box')
			       .find('.widget-header').prepend(toolbar)
				   .find('.wysiwyg-toolbar').addClass('inline');
		},
		toolbar:
		[
			'bold',
			{name:'italic' , title:'Change Title!', icon: 'ace-icon fa fa-leaf'},
			'strikethrough',
			null,
			'insertunorderedlist',
			'insertorderedlist',
			null,
			'justifyleft',
			'justifycenter',
			'justifyright'
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


});
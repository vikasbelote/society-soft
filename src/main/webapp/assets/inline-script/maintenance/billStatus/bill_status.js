(function($){
	
	$("#markAllMemberId").click(function() {
		
		if($(this).prop("checked")) {
			 var isValid = false;
			 $("#billStatusTableId tbody").find("tr").map(function(){
				 
				 var memberName = $(this).find("td:eq(1)").text();
				 var totalAmountText = $(this).find("td:eq(6)").text();
				 var paidAmountText = $(this).find("td:eq(7)").text();
				 if(paidAmountText == "") {
					 $(this).attr("style", "background-color: #f2dede;");
					 disableSave();
					 isValid = true;
					 return;
				 }
				 var totalAmount = parseFloat(totalAmountText);
				 var paidAmount = parseFloat(paidAmountText);
				 if(paidAmount > totalAmount){
					 $(this).attr("style", "background-color: #f2dede;");
					 disableSave();
					 isValid = true;
					 return;
				 }
				 $(this).find("td:eq(0)").find("input").prop("checked", true);
			 });
			 
			 if(isValid) {
				showValidationMsg("Paid Amount","You supposed to enter corretc Paid amount value.");
			 }
		}
	});
	
	$(".ace-switch-5").click(function() {
		
		if($(this).prop("checked")) {
			var paidAmount = $(this).parents("tr").find("td:eq(7)").text();
			if(paidAmount == "") {
				showValidationMsg("Need Value","You supposed to enter paid amount value first.");
				$(this).prop("checked", false);
				disableSave();
			}
		}
	});
	
	$("#saveBillStatusId").click(function(){
		
		if($(this).hasClass("disabled"))
			return false;
		
		var cycle = {};
		//cycle.cycleId = 1;
		cycle.receipts = $("#billStatusTableId tbody").find("tr").map(function(){
								
								var totalAmount = parseFloat($(this).find("td:eq(6)").text());
								var paidAmount = parseFloat($(this).find("td:eq(7)").text());
								
								var statusCheckbox = $(this).find("td:eq(0)").find("input");
								if(!statusCheckbox.is(":disabled")) {
									var obj = {};
									obj.receiptId = statusCheckbox.attr("data-receiptId");
									
									var isValid = true;
									if(paidAmount < totalAmount)
										isValid = false;
									
									if(statusCheckbox.prop("checked") && isValid) 
										obj.billStatus = true;
									else 
										obj.billStatus = false;
									obj.paidAmount = paidAmount;
									return obj;
								}
							}).get();
		
		if(cycle.receipts.length == 0) {
			showInfoMsg("Info","There is no bill to update.");
			return false;
		}
		
		var cycleJson = JSON.stringify(cycle);
		$.ajax({
			url : 'updateBillStatus',
			contentType : "application/json",
			type : 'POST',
			data : cycleJson,
			success : function(response) {
				showSuccessMsg("Bill Status Data", "Data save successfully.");
			},
			error : function(e) {
				showValidationMsg("Error","There is error while saving receipt data.");
			}
		});
	});
	
})(jQuery);

function onRowChanged(event) {
	//console.log(event.target.textContent);
	var row = $(event.target).parent("tr");

	var totalAmount = parseFloat(row.find("td:eq(6)").text());
	var paidAmount = parseFloat(row.find("td:eq(7)").text());
	
	if(paidAmount > totalAmount) {
		showValidationMsg("Error","Paid amount must be less than or equal to Total amount");
		disableSave();
		row.attr("style", "background-color: #f2dede;");
		row.find("input").prop("checked", false);
	}
	else {
		row.removeAttr("style");
		enableSave();
		row.find("input").prop("checked", true);
	}
}

function enableSave() {
	$("#saveBillStatusId").removeClass("disabled");
}

function disableSave() {
	$("#saveBillStatusId").addClass("disabled");
}

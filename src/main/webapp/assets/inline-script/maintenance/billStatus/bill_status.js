(function($){
	
	$("#markAllMemberId").click(function() {
		
		if($(this).prop("checked")) {
			 $("#billStatusTableId tbody").find("tr").map(function(){
				 $(this).find("td:eq(0)").find("input").prop("checked", true);
			 });
		}
	});
	
	$("#saveBillStatusId").click(function(){
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

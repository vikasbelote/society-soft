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
			
								var statusCheckbox = $(this).find("td:eq(0)").find("input");
								var obj = {};
								obj.receiptId = statusCheckbox.attr("data-receiptId");
								if(statusCheckbox.prop("checked")) 
									obj.billStatus = true;
								else 
									obj.billStatus = false;
								
								return obj;
							}).get();
		
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

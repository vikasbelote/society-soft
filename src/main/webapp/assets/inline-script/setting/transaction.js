(function($){
	
	//custom autocomplete (category selection)
	

	
	
	
	$(".editTransaction").click(function(){
		
		var transactionId = $(this).attr("data-transactionId");
		
		var editTr = $(this).parents('tr');
		var generalHeadId = editTr.find("td:eq(0)").attr("data-generalHeadId");
		var tranTypeId = editTr.find("td:eq(1)").attr("data-transactionTypeId");
		var transactionAmout = editTr.find("td:eq(2)").text();
		var transactionDescription = editTr.find("td:eq(3)").text();
		var transactionDate = editTr.find("td:eq(4)").text();
		
		clear();
		$("#transactionId").val(transactionId);
		$("#generalHeadId").val(generalHeadId);
		$("#transactionTypeId").val(tranTypeId);
		$("#transactionAmount").val(transactionAmout);
		$("#transactionDescription").val(transactionDescription);
		$("#transactionDate").val(transactionDate);
	});
	
	$("#transactionSubmitBtn").click(function(){
		
		var isValid = true;
	
		var generalHeadId = $("#generalHeadId").val();
		var tranTypeId = $("#transactionTypeId").val();
		var transactionAmout = $("#transactionAmount").val();
		var transactionDescription = $("#transactionDescription").val();
		var transactionDate = $("#transactionDate").val();
		
	
		if(generalHeadId == "-1") {
			showValidationMsg("General Head","Please enter value for general head.");
			isValid = false;
		}
		if(tranTypeId == "-1") {
			showValidationMsg("Transaction Type","Please enter value for transaction type.");
			isValid = false;
		}
		if(transactionAmout == "") {
			showValidationMsg("Transaction Amount","Please enter value for transaction amount.");
			isValid = false;
		}
		if(transactionDescription == "") {
			showValidationMsg("Transaction Description","Please enter value for transaction description.");
			isValid = false;
		}
		if(transactionDate == "") {
			showValidationMsg("Transaction Date","Please enter value for transaction date.");
			isValid = false;
		}
		return isValid;
	});
	
	$(".deleteTransaction").click(function(){
		var transactionId = $(this).attr("data-transactionId");
		bootbox.confirm("Are you sure?", function(result) {
			if(result) {
				$("#deleteTransactionId").val(transactionId);
				$("#deleteTransactionBtn").trigger("click");
			}
		});
	});
	
})(jQuery);

function clear() {
	
	$("#generalHeadId").val("-1");
	$("#transactionTypeId").val("-1");
	$("#transactionAmount").val("");
	$("#transactionDescription").val("");
	$("#transactionDate").val("");
}
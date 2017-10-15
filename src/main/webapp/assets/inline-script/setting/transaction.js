(function($){
	
	$(".editTransaction").click(function(){
		
		var transactionId = $(this).attr("data-transactionId");
		
		var editTr = $(this).parents('tr');
		var generalHeadId = editTr.find("td:eq(0)").attr("data-generalHeadId");
		var transactionDescriptionId = editTr.find("td:eq(1)").attr("data-transactionDescriptionId");
		var transactionDescription = editTr.find("td:eq(1)").text();
		var transactionAmout = editTr.find("td:eq(2)").text();
		var transactionDate = editTr.find("td:eq(3)").text();
		var transactionType = editTr.find("td:eq(4)").text();
		
		clear();
		$("#transactionId").val(transactionId);
		$("#generalHeadId").val(generalHeadId);
		$("#transactionDescriptionId").val(transactionDescriptionId);
		$("#transactionDescription").val(transactionDescription);
		$("#transactionAmount").val(transactionAmout);
		$("#transactionDate").val(transactionDate);
		$("#transactionType").val(transactionType);
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
	
//	$("#transactionDescription").change(function() {
//		//alert("Inline Function is called");
//	
//	});
	
	$('#transactionDescription').on('keydown keyup', function() {
		$("#transactionDescriptionId").val("");
		$("#generalHeadId").val("-1");
	});
	
})(jQuery);

function clear() {
	
	$("#generalHeadId").val("-1");
	$("#transactionTypeId").val("-1");
	$("#transactionAmount").val("");
	$("#transactionDescription").val("");
	$("#transactionDate").val("");
}
(function($){
	
	$(".deleteMaintenanceHead").on(ace.click_event, function() {
		var maintenanceHeadId = $(this).attr("data-maintenanceHeadId");
		bootbox.confirm("Are you sure?", function(result) {
			if(result) {
				$("#deleteMaintenanceHeadId").val(maintenanceHeadId);
				$("#deleteMaintenanceHeadBtn").trigger("click");
			}
		});
	});
	
	$("#chargeTypeId").change(function(){
		
		var chargeType = $(this).find("option:selected").text();
		
		if(chargeType == 'Fixed Amount') {
			clear();
			$("#maintenanceHeadDivId").addClass("hide");
			$("#percentageDivId").addClass("hide");
			$("#fixedAmountDivId").removeClass("hide");
		}
		
		if(chargeType == 'Reference Head') {
			clear();
			$("#fixedAmountDivId").addClass("hide");
			$("#maintenanceHeadDivId").removeClass("hide");
			$("#percentageDivId").removeClass("hide");
		}
		
		if(chargeType == 'Per square feet') {
			clear();
			$("#fixedAmountDivId").addClass("hide");
			$("#maintenanceHeadDivId").addClass("hide");
			$("#percentageDivId").removeClass("hide");
		}
		
		if(chargeType == 'No calculation') {
			clear();
			$("#maintenanceHeadDivId").addClass("hide");
			$("#percentageDivId").addClass("hide");
			$("#fixedAmountDivId").addClass("hide");
		}
	});
	
	
	$(".editMaintenanceHead").click(function(){
		
		var maintenanceHeadId = $(this).attr("data-maintenanceHeadId");
		
		var editRow = $(this).parents("tr");
		var maintenanceHeadName = $.trim(editRow.find("td:eq(0)").text());
		var chargeType = $.trim(editRow.find("td:eq(1)").text());
		var chargeTypeId = $.trim(editRow.find("td:eq(1)").attr("data-chargeTypeId"));
		
		$("#maintenanceHeadId").val(maintenanceHeadId);
		$("#maintenanceHeadName").val(maintenanceHeadName);
		$("#chargeTypeId").val(chargeTypeId);
		
		if(chargeType == 'Fixed Amount') {
			clear();
			$("#maintenanceHeadDivId").addClass("hide");
			$("#percentageDivId").addClass("hide");
			$("#fixedAmountDivId").removeClass("hide");
			
			var fixedAmount = $.trim(editRow.find("td:eq(2)").text());
			$("#fixedAmount").val(fixedAmount);
		}
		
		if(chargeType == 'Reference Head') {
			clear();
			$("#fixedAmountDivId").addClass("hide");
			$("#maintenanceHeadDivId").removeClass("hide");
			$("#percentageDivId").removeClass("hide");
			
			var referenceHeadId = $.trim(editRow.find("td:eq(3)").attr("data-referenceHeadId"));
			var percentageAmount = $.trim(editRow.find("td:eq(4)").text());
			
			$("#referenceHeadId").val(referenceHeadId);
			$("#percentageAmount").val(percentageAmount);
		}
		
		if(chargeType == 'Per square feet') {
			clear();
			$("#fixedAmountDivId").addClass("hide");
			$("#maintenanceHeadDivId").addClass("hide");
			$("#percentageDivId").removeClass("hide");
			
			var percentageAmount = $.trim(editRow.find("td:eq(4)").text());
			$("#percentageAmount").val(percentageAmount);
		}
		
		if(chargeType == 'No calculation') {
			clear();
			$("#maintenanceHeadDivId").addClass("hide");
			$("#percentageDivId").addClass("hide");
			$("#fixedAmountDivId").addClass("hide");
		}
		
	});
	
	$("#maintenanceHeadResetId").click(function(){
		$("#maintenanceHeadId").val("");
		$("#calcId").val("");
		
		$("#fixedAmountDivId").addClass("hide");
		$("#percentageDivId").addClass("hide");
		$("#fixedAmountDivId").addClass("hide");
	});
	
	$("#maintenanceHeadSubmitBtn").click(function(){
		
		var isValid = true;
		
		var maintenanceHeadName = $("#maintenanceHeadName").val();
		if(maintenanceHeadName == "") {
			showValidationMsg("Head Name","Please enter value for Maintenance head.");
			isValid = false;
		}
		
		var chargeType = $("#chargeTypeId").find("option:selected").text();
		
		if(chargeType == 'Fixed Amount') {
			var fixedAmount = $("#fixedAmount").val();
			if(fixedAmount == "") {
				showValidationMsg("Fixed Amount","Please enter value for Fixed Amount.");
				isValid = false;
			}
		}
		
		if(chargeType == 'Reference Head') {
			
			var referenceHeadId = $("#referenceHeadId").val();
			if(referenceHeadId == 0) {
				showValidationMsg("Reference Head","Please select value for Reference Head.");
				isValid = false;
			}
			
			var percentageAmount = $("#percentageAmount").val();
			if(percentageAmount == "") {
				showValidationMsg("Percentage Amount","Please enter value for Percentage.");
				isValid = false;
			}
		}
		
		if(chargeType == 'Per square feet') {
			var percentageAmount = $("#percentageAmount").val();
			if(percentageAmount == "") {
				showValidationMsg("Percentage Amount","Please enter value for Percentage.");
				isValid = false;
			}
		}
		
		return isValid;
	});
	
	function clear() {
		$("#fixedAmount").val('');
		$("#referenceHeadId").val("0");
		$("#percentageAmount").val('');
	}
	
})(jQuery);
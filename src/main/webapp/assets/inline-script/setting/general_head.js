(function ($) {
	
	$(".editGeneralHead").click(function(){
		
		var editTr = $(this).parents('tr');
		var generalHeadId = $(this).attr("data-generalHeadId");
		var generalHeadName = editTr.find("td:eq(1)").text();
		var sectionId = editTr.find("td:eq(2)").attr("data-sectionId");
	
		
		clear();
		$("#generalHeadId").val(generalHeadId);
		$("#generalHeadName").val(generalHeadName);
		$("#sectionId").val(sectionId);
		
	});
	
	$("#generalHeadSubmitBtn").click(function() {
		
		var isValid = true;
		
		var generalHeadName = $("#generalHeadName").val();
		var sectionName = $("#sectionId").val();
		
		if(generalHeadName == ""){
			showValidationMsg("General Head","Please enter value for general head.");
			isValid = false;
		}
		if(sectionName == "-1") {
			showValidationMsg("Section Name","Please enter value for section name.");
			isValid = false;
		}
		return isValid;
	});
	
	$(".deleteGeneralHead").on(ace.click_event, function() {
		var generalHeadId = $(this).attr("data-generalHeadId");
		bootbox.confirm("Are you sure?", function(result) {
			if(result) {
				$("#deleteGeneralHeadId").val(generalHeadId);
				$("#deleteGeneralHeadBtn").trigger("click");
			}
		});
	});
	
	
})(jQuery);

function clear() {
	$("#generalHeadName").val("");
	$("#sectionId").val("-1");
}
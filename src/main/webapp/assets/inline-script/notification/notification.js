(function($){
	
	$("#checkFailMemberId").click(function(){
		
		var checked = $(this).prop("checked");
		if(checked){
			$("#emailStatusTableId tbody").find("tr").map(function() {
				
				var status = $.trim($(this).find("td:eq(4)").text());
				if(status == "false")
					$(this).find("td:eq(0) input").prop("checked", true);
			});
		}
		else {
			$("#emailStatusTableId tbody tr").find("input").prop("checked", false);
		}
			
	});
	
})(jQuery);
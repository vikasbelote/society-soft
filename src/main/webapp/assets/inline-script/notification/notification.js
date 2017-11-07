(function($){
	
	$("#checkFailMemberId").click(function(){
		
		var checked = $(this).prop("checked");
		if(checked){
			$("#emailStatusTableId tbody").find("tr").map(function() {
				
				var span = $(this).find("td:eq(4)").find("span");
				if(span.hasClass("label-danger"))
					$(this).find("td:eq(0) input").prop("checked", true);
			});
		}
		else {
			$("#emailStatusTableId tbody tr").find("input").prop("checked", false);
		}
	});
	
	
	$("#sendEmailId").click(function(){
		
		var email = {};
		email.cycleId = $("#emailStatusTableId").attr("data-cycleId");
		email.memberIds = $("#emailStatusTableId tbody").find("tr").map(function() {
			
							var checkbox = $(this).find("td:eq(0)").find("input");
							if(checkbox.prop("checked")) {
								var statusMember = {};
								statusMember.mailStatusId = $(this).attr("data-mailStatusId");
								statusMember.memberId = checkbox.val();
								return statusMember;
							}
						}).get();
		
		
		var emailJson = JSON.stringify(email);
		
		$.ajax({
			url : 'sendEmailToFailedMember',
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
	
})(jQuery);
(function($){
	
	$(".deleteMember").click(function(){
		var memberId = $(this).attr("data-memberId");
		bootbox.confirm("Are you sure want to delete?", function(result) {
			if(result) {
				$("#deleteMemberId").val(memberId);
				$("#deleteMemberBtn").trigger("click");
			}
		});
	});
	
	$(".editMember").click(function(){
		
		var societyMemberDomain = {};
		societyMemberDomain.memberId = $(this).attr("data-memberId");
		societyMemberDomain.personId = $(this).attr("data-personId");
		
		var societyMemberDomainJson = JSON.stringify(societyMemberDomain);
		$.ajax({
			url : 'editMember',
			contentType : "application/json",
			type : 'POST',
			data : societyMemberDomainJson,
			success : function(member) {
				
				$("#firstName").val(member.firstName);
				$("#middleName").val(member.middleName);
				$("#lastName").val(member.lastName);
				$("#mobileNumber").val(member.mobileNumber);
				$("#emailId").val(member.emailId);
				$("#wingNumber").val(member.wingNumber);
				$("#flatNumber").val(member.flatNumber);
				$("#squareFeet").val(member.squareFeet);
				$("#additionalAreaId").val(member.additionalAreaId == null ? 0 : member.additionalAreaId);
				
				$("#memberId").val(member.memberId);
				$("#perosnId").val(member.personId);
			},
			error : function(e) {
				showValidationMsg("Error","There is error while retrieving member data.");
			}
		});
		
	});
	
	$("#resetMemberBtnId").click(function(){
		$("#memberId").val("");
	});
	
	function clear() {
		$("#firstName").val("");
		$("#middleName").val("");
		$("#lastName").val("");
		$("#mobileNumber").val("");
		$("#emailId").val("");
		$("#wingNumber").val("");
		$("#flatNumber").val("");
		$("#squareFeet").val("");
		$("#additionalAreaId").val(0);
	}
	
})(jQuery);
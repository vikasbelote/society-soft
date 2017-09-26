/** ************ */
$('#memberTable').on(
		'click',
		'.show-details-btn',
		function(e) {
			e.preventDefault();
			$(this).closest('tr').next().toggleClass('open');
			$(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down')
					.toggleClass('fa-angle-double-up');
		});
/** ************ */

$("#addMemberAnchor").click(function() {
	$('#updateMemberBtn').addClass("hide");
	$("#saveMemberBtn").removeClass("hide");
	
	clearTextForPersonModel();
});

$('#memberTable').on('click', '#editMemberAnchor', function(e) {

	$('#updateMemberBtn').removeClass("hide");
	$("#saveMemberBtn").addClass("hide");
	
	clearTextForPersonModel();
	
	var editTr = $(this).parents('tr');
	var firstName = editTr.find("td:eq(1)").text();
	var middleName = editTr.find("td:eq(2)").text();
	var lastName = editTr.find("td:eq(3)").text();
	var mobileNumber = editTr.find("td:eq(4)").text();
	var emailId = editTr.find("td:eq(5)").text();
	
	$("#memberFirstName").val(firstName);
	$("#memberMidleName").val(middleName);
	$("#memberLastName").val(lastName);
	$("#memberMobileNumber").val(mobileNumber);
	$("#memberEmailId").val(emailId);
	
	var rowIndex = editTr.index();
	$("#rowIndex").val(rowIndex);
});

$("#saveMemberBtn").click(function() {

	var firstName = $("#memberFirstName").val();
	var middleName = $("#memberMidleName").val();
	var lastName = $("#memberLastName").val();
	var mobileNumber = $("#memberMobileNumber").val();
	var emailId = $("#memberEmailId").val();

	if (firstName === "") {
		$("#modelMsgDiv").removeClass("hide");
		$("#modelMsg").html("Please enter first name");
		$(this).removeAttr('data-dismiss');
		return;
	}

	if (lastName === "") {
		$("#modelMsgDiv").removeClass("hide");
		$("#modelMsg").html("Please enter last name");
		$(this).removeAttr('data-dismiss');
		return;
	}

	if (mobileNumber === "") {
		$("#modelMsgDiv").removeClass("hide");
		$("#modelMsg").html("Please enter mobile number");
		$(this).removeAttr('data-dismiss');
		return;
	}

	if (emailId === "") {
		$("#modelMsgDiv").removeClass("hide");
		$("#modelMsg").html("Please enter email id");
		$(this).removeAttr('data-dismiss');
		return;
	}

	var memberObj = {
		'firstName' : firstName,
		'middleName' : middleName,
		'lastName' : lastName,
		'contactNumber' : mobileNumber,
		'emailId' : emailId
	}

	var memberObjJson = JSON.stringify(memberObj);

	$.ajax({
		url : 'getMemberRow',
		contentType : "application/json",
		type : 'POST',
		data : memberObjJson,
		success : function(response) {
			$('#memberTable tbody').append(response);
			$('#memberTable').removeClass("hide");
		}
	});
	$(this).attr('data-dismiss', 'modal');	
});

$("#updateMemberBtn").click(function() {

	var firstName = $("#memberFirstName").val();
	var middleName = $("#memberMidleName").val();
	var lastName = $("#memberLastName").val();
	var mobileNumber = $("#memberMobileNumber").val();
	var emailId = $("#memberEmailId").val();
	
	if (firstName === "") {
		$("#modelMsgDiv").removeClass("hide");
		$("#modelMsg").html("Please enter first name");
		$(this).removeAttr('data-dismiss');
		return;
	}

	if (lastName === "") {
		$("#modelMsgDiv").removeClass("hide");
		$("#modelMsg").html("Please enter last name");
		$(this).removeAttr('data-dismiss');
		return;
	}

	if (mobileNumber === "") {
		$("#modelMsgDiv").removeClass("hide");
		$("#modelMsg").html("Please enter mobile number");
		$(this).removeAttr('data-dismiss');
		return;
	}

	if (emailId === "") {
		$("#modelMsgDiv").removeClass("hide");
		$("#modelMsg").html("Please enter email id");
		$(this).removeAttr('data-dismiss');
		return;
	}
	
	var rowIndex = $("#rowIndex").val();
	var $editRow = $("#memberTable tbody").find("tr:eq("+ rowIndex +")");
	
	$editRow.find("td:eq(1)").html(firstName);
	$editRow.find("td:eq(2)").html(middleName);
	$editRow.find("td:eq(3)").html(lastName);
	$editRow.find("td:eq(4)").html(mobileNumber);
	$editRow.find("td:eq(5)").html(emailId);
	
	var $detailRow = $editRow.next("tr");
	var $profileInfoRow = $detailRow.find(".profile-user-info").find(".profile-info-row");
	var profileInfoRowArr = $.makeArray($profileInfoRow);
	
	$(profileInfoRowArr[0]).find("span").html(firstName);
	$(profileInfoRowArr[0]).find("input").val(firstName);
	
	$(profileInfoRowArr[1]).find("span").html(middleName);
	$(profileInfoRowArr[1]).find("input").val(middleName);
	
	$(profileInfoRowArr[2]).find("span").html(lastName);
	$(profileInfoRowArr[2]).find("input").val(lastName);
	
	$(profileInfoRowArr[3]).find("span").html(mobileNumber);
	$(profileInfoRowArr[3]).find("input").val(mobileNumber);
	
	$(profileInfoRowArr[4]).find("span").html(emailId);
	$(profileInfoRowArr[4]).find("input").val(emailId);
});

function clearTextForPersonModel() {
	
	$("#memberFirstName").val("");
	$("#memberMidleName").val("");
	$("#memberLastName").val("");
	$("#memberMobileNumber").val("");
	$("#memberEmailId").val("");
	
	$("#modelMsgDiv").addClass("hide");
}


$("#societySubmitBtn").click(function(){
	
	var isValid = true;
	
	var societyName = $("#societyNameId").val();
	if(societyName == ""){
		showValidationMsg("Society Name","Please enter value for society name.");
		isValid = false;
	}
		
	
	var userName = $("#userNameId").val();
	if(userName == "") {
		showValidationMsg("User Name","Please enter value for user name.");
		isValid = false;
	}
		
	
	var userPassword = $("#userPasswordId").val();
	if(userPassword == "") {
		showValidationMsg("Password","Please enter value for password.");
		isValid = false;
	}
		
	
	var firstName = $("#firstNameId").val();
	if(firstName == ""){
		showValidationMsg("First Name","Please enter value for First Name.");
		isValid = false;
	}
		
	
	var lastName = $("#lastNameId").val();
	if(lastName == "") {
		showValidationMsg("Last Name","Please enter value for Last Name.");
		isValid = false;
	}
		
	
	var contactNumber = $("#contactNumberId").val();
	if(contactNumber == ""){
		showValidationMsg("Contact Number","Please enter value for Conatct Number.");
		isValid = false;
	}
		
	
	var emailId = $("#emailIdId").val();
	if(emailId == "") {
		showValidationMsg("Email Id","Please enter value for email Id.");
		isValid = false;
	}
		
	
	var addressText = $("#addressTextId").val();
	if(addressText == "") {
		showValidationMsg("Address Text","Please enter value for address text.");
		isValid = false;
	}
		
	
	var areaName = $("#areaNameId").val();
	if(areaName == ""){
		showValidationMsg("Area Name","Please enter value for area name.");
		isValid = false;
	}
		
	
	var plotNo = $("#plotNoId").val();
	if(plotNo == "") {
		showValidationMsg("Plot No","Please enter value for plot no.");
		isValid = false;
	}
		
	
	var sectorNo = $("#sectorNoId").val();
	if(sectorNo == ""){
		showValidationMsg("Sector No","Please enter value for sector no.");
		isValid = false;
	}
		
	
	var city = $("#cityId").val();
	if(city == "") {
		showValidationMsg("City","Please enter value for city.");
		isValid = false;
	}
		
	
	var pinCode = $("#pinCodeId").val();
	if(pinCode == "") {
		showValidationMsg("Pin Code","Please enter value for pin code.");
		isValid = false;
	}
		
	
	var state = $("#stateId").val();
	if(state == "") {
		showValidationMsg("State","Please enter value for state.");
		isValid = false;
	}
	
	return isValid;
});

function showValidationMsg(titleName, msgText) {
	
	$.gritter.add({
		title: titleName,
		text: msgText,
		sticky: true,
		time: '',
		class_name: 'gritter-error'
	});
}


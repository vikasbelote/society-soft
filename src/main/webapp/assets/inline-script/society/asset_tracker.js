(function($){
	
	$('#modal-file input[type=file]').ace_file_input({
		style:'well',
		btn_choose:'Drop files here or click to choose',
		btn_change:null,
		no_icon:'ace-icon fa fa-cloud-upload',
		droppable:true,
		thumbnail:'large'
	});
	
	$("#addContactId").click(function(){
		
		$('#updateContactBtn').addClass("hide");
		$("#saveContactBtn").removeClass("hide");
		
		clear("modal-contact");
	});
	
	$("#saveContactBtn").click(function(){
		
		var obj = {};
		obj.contactFirstName = $("#firstNameId").val();
		obj.contactMiddleName = $("#middleNameId").val();
		obj.contactLastName = $("#lastNameId").val();
		obj.contactMobileNumber = $("#mobileNumberId").val();
		obj.contactEmailId = $("#emailId").val();
		
		var rowStr = getTableRow(obj,'modal-contact','editContact');
		$("#contactTableId").append(rowStr);
		
		$("#contactTableId").removeClass("hide");
		clear("modal-contact");
	});
	
	$('#contactTableId').on('click', '.editContact', function(e) {
		
		$('#updateContactBtn').removeClass("hide");
		$("#saveContactBtn").addClass("hide");
		
		clear("modal-contact");
		
		var row = $(this).parents('tr');
		var obj = {};
		obj.firstName = row.find("td:eq(0)").text();
		obj.middleName = row.find("td:eq(1)").text();
		obj.lastName = row.find("td:eq(2)").text();
		obj.mobileNumber = row.find("td:eq(3)").text();
		obj.emailId = row.find("td:eq(4)").text();
		
		 $("#firstNameId").val(obj.firstName);
		 $("#middleNameId").val(obj.middleName);
		 $("#lastNameId").val(obj.lastName);
		 $("#mobileNumberId").val(obj.mobileNumber);
		 $("#emailId").val(obj.emailId);
		 
		 var rowIndex = row.index();
		 $("#contactRowIndexId").val(rowIndex);
	});
	
	$("#updateContactBtn").click(function() {
		
		var rowIndex = $("#contactRowIndexId").val();
		var $editRow = $("#contactTableId tbody").find("tr:eq("+ rowIndex +")");
		
		var obj = {};
		obj[0] = $("#firstNameId").val();
		obj[1] = $("#middleNameId").val();
		obj[2] = $("#lastNameId").val();
		obj[3] = $("#mobileNumberId").val();
		obj[4] = $("#emailId").val();
		
		updateRow(obj, $editRow);
	});
	
	$("#addServiceHistoryId").click(function(){
		$('#saveHistoryBtn').removeClass("hide");
		$("#updateHistoryBtn").addClass("hide");
		
		clear("modal-history");
	});
	
	$("#saveHistoryBtn").click(function(){
		
		var obj = {};
		obj.historyDate = $("#historyDateId").val();
		obj.serviceFirstName = $("#serviceFirstNameId").val();
		obj.serviceMiddleName = $("#serviceMiddleNameId").val();
		obj.serviceLastName = $("#serviceLastNameId").val();
		obj.serviceMobileNumber = $("#serviceMobileNumberId").val();
		obj.serviceEmailId = $("#serviceEmailId").val();
		
		var rowStr = getTableRow(obj,'modal-history','editHistory');
		$("#historyTableId").append(rowStr);
		
		$("#historyTableId").removeClass("hide");
		clear("modal-history");
	});
	
	$('#historyTableId').on('click', '.editHistory', function(e) {
		
		$('#updateHistoryBtn').removeClass("hide");
		$("#saveHistoryBtn").addClass("hide");
		
		clear("modal-history");
		
		var row = $(this).parents('tr');
		var obj = {};
		obj.historyDate = row.find("td:eq(0)").text();
		obj.firstName = row.find("td:eq(1)").text();
		obj.middleName = row.find("td:eq(2)").text();
		obj.lastName = row.find("td:eq(3)").text();
		obj.mobileNumber = row.find("td:eq(4)").text();
		obj.emailId = row.find("td:eq(5)").text();
		
		 $("#historyDateId").val(obj.historyDate);
		 $("#serviceFirstNameId").val(obj.firstName);
		 $("#serviceMiddleNameId").val(obj.middleName);
		 $("#serviceLastNameId").val(obj.lastName);
		 $("#serviceMobileNumberId").val(obj.mobileNumber);
		 $("#serviceEmailId").val(obj.emailId);
		 
		 var rowIndex = row.index();
		 $("#historyRowIndexId").val(rowIndex);
	});
	
	$("#updateHistoryBtn").click(function() {
		
		var rowIndex = $("#historyRowIndexId").val();
		var $editRow = $("#historyTableId tbody").find("tr:eq("+ rowIndex +")");
		
		var obj = {};
		obj[0] = $("#historyDateId").val();
		obj[1] = $("#serviceFirstNameId").val();
		obj[2] = $("#serviceMiddleNameId").val();
		obj[3] = $("#serviceLastNameId").val();
		obj[4] = $("#serviceMobileNumberId").val();
		obj[5] = $("#serviceEmailId").val();
		
		updateRow(obj, $editRow);
	});
	
	$("#addFileBtnId").click(function(){
		$("#scanFileDivId input[type=file]").ace_file_input('reset_input');
	});
	
	$("#scanFileDivId input[type=file]").change(function(){
		 var fileName = $("#scanFileDivId").find(".ace-file-name").attr("data-title");
		 var scanFileName = $("#scanFileDivId").find("input[type=hidden]");
		 scanFileName.val(fileName);
	});
	
	$("#saveFileBtn").click(function(){
		
		var rowStr = "<tr>";
		rowStr = rowStr + "<td>";
		rowStr = rowStr + $("#scanFileDivId").html();
		rowStr = rowStr + "</td>";
		rowStr = rowStr + "<td>";
		rowStr = rowStr + "</td>";
		rowStr = rowStr + "<td>";
		rowStr = rowStr + '<a onclick="deleteRow(event)" href="#" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></a>';
		rowStr = rowStr + "</td>";
		rowStr = rowStr + "</tr>";
		
		$("#assetFileTable tbody").append(rowStr);
		$("#assetFileTable").removeClass("hide");
	});
	
	function getTableRow(obj, modelId, rowClass) {
		var rowStr = "<tr>";
		$.each(obj, function(key, value) {
			rowStr = rowStr + "<td>";
			rowStr = rowStr + '<input type="hidden" name="'+key+'" value="'+value+'" />'+value;
			rowStr = rowStr + "</td>";
		});
		rowStr = rowStr + "<td>";
		rowStr = rowStr + '<a href="#'+modelId+'" data-toggle="modal" class="btn btn-xs btn-info '+rowClass+'"><i class="ace-icon fa fa-pencil bigger-120"></i></a>';
		rowStr = rowStr + '<a onclick="deleteRow(event)" href="#" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></a>';
		rowStr = rowStr + "</td>";
		rowStr = rowStr + "</tr>";
		return rowStr;
	}
	
	function clear(elementId) {
		$("#" + elementId).find('input').val('');
	}
	
	function updateRow(obj, row) {
		$.each(obj, function(index, value) {
			var td = row.find("td:eq("+index+")");
			var input = td.find("input");
			input.val(value);
			td.text(value);
			td.append(input);
		});
		row.attr("data-updated", true);
		row.removeAttr("style");
		row.removeAttr("data-deleted");
	 }
	
	//////////////////////Update Asset Data//////////////////////////////
	$("#updateAssetData").click(function(){
		
		var adminAssetTrackerDomain = {};
		adminAssetTrackerDomain.assetId = $("#assetId").val();
		adminAssetTrackerDomain.assetName = $("#assetName").val();
		adminAssetTrackerDomain.assetTagNumber = $("#assetTagNumber").val();
		adminAssetTrackerDomain.vendorName = $("#vendorName").val();
		adminAssetTrackerDomain.categoryId = $("#categoryId").val();
		adminAssetTrackerDomain.assetLocation = $("#assetLocation").val();
		adminAssetTrackerDomain.purchaseDate = $("#purchaseDate").val();
		adminAssetTrackerDomain.assetCost = $("#assetCost").val();
		adminAssetTrackerDomain.assetStatus = $("#assetStatus").val();
		
		adminAssetTrackerDomain.contactDomainList = $("#contactTableId tbody").find("tr").map(function(){
			
			var contact = {};
			contact.contactId = $(this).attr("data-rowId");
			contact.person = {};
			contact.person.personId = $(this).attr("data-personId");
			contact.person.firstName = $(this).find("td:eq(0)").text();
			contact.person.middleName = $(this).find("td:eq(1)").text();
			contact.person.lastName = $(this).find("td:eq(2)").text();
			contact.person.contactNumber = $(this).find("td:eq(3)").text();
			contact.person.emailId = $(this).find("td:eq(4)").text();
			
			var isUpdated = $(this).attr("data-updated");
			if(isUpdated)
				contact.isUpdated = true;
			else
				contact.isUpdated = false;
			
			var isDeleted = $(this).attr("data-deleted");
			if(isDeleted)
				contact.isDeleted = true;
			else
				contact.isDeleted = false;
			
			return contact;
		}).get();
		
		adminAssetTrackerDomain.serviceHistoryDomainList = $("#historyTableId tbody").find("tr").map(function(){
			
			var history = {};
			history.serviceHistoryId = $(this).attr("data-historyId");
			history.historyDate =  $(this).find("td:eq(0)").text();
			history.person = {};
			history.person.personId = $(this).attr("data-personId");
			history.person.firstName = $(this).find("td:eq(1)").text();
			history.person.middleName = $(this).find("td:eq(2)").text();
			history.person.lastName = $(this).find("td:eq(3)").text();
			history.person.contactNumber = $(this).find("td:eq(4)").text();
			history.person.emailId = $(this).find("td:eq(5)").text();
			
			var isUpdated = $(this).attr("data-updated");
			if(isUpdated)
				history.isUpdated = true;
			else
				history.isUpdated = false;
			
			var isDeleted = $(this).attr("data-deleted");
			if(isDeleted)
				history.isDeleted = true;
			else
				history.isDeleted = false;
			
			return history;
		}).get();
		
		adminAssetTrackerDomain.scanFileDomainList = $("#assetFileTable tbody").find("tr").map(function(){
			
			var scanFile = {};
			scanFile.fileId = $(this).attr("data-rowId");
			scanFile.fileName = $(this).find("td:eq(0)").text();
			
			var isDeleted = $(this).attr("data-deleted");
			if(isDeleted)
				scanFile.isDeleted = true;
			else
				scanFile.isDeleted = false;
			
			if(scanFile.fileId != "")
				return scanFile;
			
		}).get();
		
		var assetJson = JSON.stringify(adminAssetTrackerDomain);
		$("#assetJsonId").val(assetJson);
		return true;
	});
	
	
	
})(jQuery);

function deleteRow(event) {
	
	var table = $(event.target).parents('table');
	$(event.target).parents('tr').remove();
	 
	var rowCount = table.find('tbody').find('tr').length;
	if(rowCount == 0)
		table.addClass("hide");
}

function deleteDbRow(event) {
	
	var row = $(event.target).parents('tr');
	var isDeleted = row.attr("data-deleted");
	if(isDeleted)
		return false;

	row.attr("style", "background-color: #f2dede;");
	row.attr("data-deleted", true);
	row.removeAttr("data-updated");
}
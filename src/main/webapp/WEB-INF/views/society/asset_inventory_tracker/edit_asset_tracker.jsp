<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Asset & Inventory Tracker <small> <i
			class="ace-icon fa fa-angle-double-right"></i> This page is used to track Asset & inventory
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form:form id="some-form" commandName="adminAssetTrackerDomain" method="post"
					class="form-horizontal" action="updateAssetDetail" enctype="multipart/form-data">
			<h3 class="header smaller lighter green">Asset info</h3>
			<form:hidden path="assetId" />
			<div class="form-group">
				<label class="col-sm-3 control-label" for="form-field-1">Asset
					Name </label>
				<div class="col-sm-2">
					<form:input cssClass="col-xs-10 col-sm-12"
						path="assetName" />
				</div>

				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">Tag Number</label>
				<div class="col-sm-2">
					<form:input cssClass="col-xs-10 col-sm-12"
						path="assetTagNumber" />
				</div>
			</div> 	
			
			<div class="form-group">
				<label class="col-sm-3 control-label" for="form-field-1">Vendor
					Name </label>
				<div class="col-sm-2">
					<form:input cssClass="col-xs-10 col-sm-12"
						path="vendorName" />
				</div>

				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">Asset Category</label>
				<div class="col-sm-2">
					<form:select path="categoryId" cssClass="col-xs-10 col-sm-12">
						<form:option value="0">Choose Category</form:option>
						<form:options items="${categoryList}" itemLabel="categoryName" itemValue="categoryId" />
					</form:select>
				</div>
			</div> 	
			
			<div class="form-group">
				<label class="col-sm-3 control-label" for="form-field-1">Location
					 </label>
				<div class="col-sm-2">
					<form:input cssClass="col-xs-10 col-sm-12"
						path="assetLocation" />
				</div>

				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">Purchase Date</label>
				<div class="col-sm-2">
					<form:input cssClass="col-xs-10 col-sm-12 date-picker" 
						path="purchaseDate" data-date-format="yyyy-mm-dd" />
				</div>
			</div> 
			
			<div class="form-group">
				<label class="col-sm-3 control-label" for="form-field-1">Asset
					Cost </label>
				<div class="col-sm-2">
					<form:input cssClass="col-xs-10 col-sm-12"
						path="assetCost" />
				</div>

				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">Asset Status</label>
				<div class="col-sm-2">
					<form:input cssClass="col-xs-10 col-sm-12"
						path="assetStatus" />
				</div>
			</div> 
			
			<h3 class="header smaller lighter green">Contact</h3>
			<a id="addContactId" href="#modal-contact" role="button" class="btn btn-sm btn-info"
				data-toggle="modal"> Add Contact </a>
			<input type="hidden" id="contactRowIndexId" />
			<c:choose>
				<c:when test="${not empty adminAssetTrackerDomain.contactDomainList}">
					<table id="contactTableId" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th><strong>First Name</strong></th>
								<th><strong>Middle Name</strong></th>
								<th><strong>Last Name</strong></th>
								<th><strong>Mobile Number</strong></th>
								<th><strong>Email Id</strong></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${adminAssetTrackerDomain.contactDomainList}" var="contact">
								<tr data-rowId="${contact.contactId}" data-personId="${contact.person.personId}">
									<td>${contact.person.firstName}</td>
									<td>${contact.person.middleName}</td>
									<td>${contact.person.lastName}</td>
									<td>${contact.person.contactNumber}</td>
									<td>${contact.person.emailId}</td>
									<td>
										<a href="#modal-contact" data-toggle="modal" class="btn btn-xs btn-info editContact"><i class="ace-icon fa fa-pencil bigger-120"></i></a>
										<a onclick="deleteDbRow(event)" href="#" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<table id="contactTableId" class="table table-bordered table-hover hide">
						<thead>
							<tr>
								<th><strong>First Name</strong></th>
								<th><strong>Middle Name</strong></th>
								<th><strong>Last Name</strong></th>
								<th><strong>Mobile Number</strong></th>
								<th><strong>Email Id</strong></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>	
				
			<h3 class="header smaller lighter green">Service History</h3>
			<a id="addServiceHistoryId" href="#modal-history" role="button" class="btn btn-sm btn-info"
				data-toggle="modal"> Add Service History </a>
			<input type="hidden" id="historyRowIndexId" />	
			<c:choose>
				<c:when test="${not empty adminAssetTrackerDomain.serviceHistoryDomainList}">
					<table id="historyTableId" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th><strong>History Date</strong></th>
								<th><strong>First Name</strong></th>
								<th><strong>Middle Name</strong></th>
								<th><strong>Last Name</strong></th>
								<th><strong>Mobile Number</strong></th>
								<th><strong>Email Id</strong></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${adminAssetTrackerDomain.serviceHistoryDomainList}" var="serviceHistory">
								<tr data-historyId="${serviceHistory.serviceHistoryId}" data-personId="${contact.person.personId}">
									<td>${serviceHistory.historyDate}</td>
									<td>${serviceHistory.person.firstName}</td>
									<td>${serviceHistory.person.middleName}</td>
									<td>${serviceHistory.person.lastName}</td>
									<td>${serviceHistory.person.contactNumber}</td>
									<td>${serviceHistory.person.emailId}</td>
									<td>
										<a href="#modal-history" data-toggle="modal" class="btn btn-xs btn-info editContact"><i class="ace-icon fa fa-pencil bigger-120"></i></a>
										<a onclick="deleteDbRow(event)" href="#" class="btn btn-xs btn-danger "><i class="ace-icon fa fa-trash-o bigger-120"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<table id="historyTableId" class="table table-bordered table-hover hide">
						<thead>
							<tr>
								<th><strong>History Date</strong></th>
								<th><strong>First Name</strong></th>
								<th><strong>Middle Name</strong></th>
								<th><strong>Last Name</strong></th>
								<th><strong>Mobile Number</strong></th>
								<th><strong>Email Id</strong></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
			
			<c:if test="${not empty alertDomainList}">
				<h3 class="header smaller lighter green">Alert</h3>
				<table id="assetAlertTable" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Alert Message</strong></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${adminAssetTrackerDomain.alertDomainList}" var="alert"> 
							<tr>
								<td>${alert.alertMessage}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>	
			</c:if>
				
			<h3 class="header smaller lighter green">Scanned Document</h3>
			<a id="addFileBtnId" href="#modal-file" role="button" class="btn btn-sm btn-info"
				data-toggle="modal"> Add File </a>
				
			<c:choose>
				<c:when test="${not empty adminAssetTrackerDomain.scanFileDomainList}">
					<table id="assetFileTable" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th><strong>File Name</strong></th>
								<th><strong>Uploaded by</strong></th>
								<th></th>
							</tr>
						</thead>
						<tbody>	
							<c:forEach items="${adminAssetTrackerDomain.scanFileDomainList}" var="scanFile">
								<tr class="old-file" data-rowId="${scanFile.fileId}">
									<td>${scanFile.fileName}</td>
									<td>${scanFile.user.userName}</td>
									<td>
										<a onclick="deleteDbRow(event)" href="#" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<table id="assetFileTable" class="table table-bordered table-hover hide">
						<thead>
							<tr>
								<th><strong>File Name</strong></th>
								<th><strong>Uploaded by</strong></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
			<input type="hidden" name="assetJson" id="assetJsonId" />  
			<div class="">
				<div class="col-sm-offset-4">
					<button id="updateAssetData" type="submit"
						class="btn btn-sm btn-success">
						<i class="ace-icon fa fa-check bigger-110"></i>Submit
					</button>
					&nbsp; &nbsp; &nbsp;
					<button class="btn btn-sm" type="reset">
						<i class="ace-icon fa fa-undo bigger-110"></i> Reset
					</button>
				</div>
			</div>		
		</form:form>
	</div>
</div>

<div id="modal-contact" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">Contact for Asset Maintenance</h4>
			</div>

			<div class="modal-body">
				<div class="row">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> First Name </label>
	
							<div class="col-sm-5">
								<input type="text" id="firstNameId" class="col-xs-10 col-sm-12" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Middle Name </label>
	
							<div class="col-sm-5">
								<input type="text" id="middleNameId" class="col-xs-10 col-sm-12" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Last Name </label>
	
							<div class="col-sm-5">
								<input type="text" id="lastNameId" class="col-xs-10 col-sm-12" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Mobile Number </label>
	
							<div class="col-sm-5">
								<input type="text" id="mobileNumberId" class="col-xs-10 col-sm-12 numeric" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Email Id </label>
	
							<div class="col-sm-5">
								<input type="text" id="emailId" class="col-xs-10 col-sm-12" />
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<div class="modal-footer">
				<button class="btn btn-sm" data-dismiss="modal">
					<i class="ace-icon fa fa-times"></i> Cancel
				</button>

				<button id="saveContactBtn" class="btn btn-sm btn-primary"
					data-dismiss="modal">
					<i class="ace-icon fa fa-check"></i> Save
				</button>
				<button id="updateContactBtn" class="btn btn-sm btn-primary hide"
					data-dismiss="modal">
					<i class="ace-icon fa fa-check"></i> Update
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modal-history" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">Service History Record</h4>
			</div>

			<div class="modal-body">
				<div class="row">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> History Date </label>
	
							<div class="col-sm-5">
								<input type="text" id="historyDateId" class="col-xs-10 col-sm-12 date-picker"
								data-date-format="yyyy-mm-dd" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> First Name </label>
	
							<div class="col-sm-5">
								<input type="text" id="serviceFirstNameId" class="col-xs-10 col-sm-12" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Middle Name </label>
	
							<div class="col-sm-5">
								<input type="text" id="serviceMiddleNameId" class="col-xs-10 col-sm-12" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Last Name </label>
	
							<div class="col-sm-5">
								<input type="text" id="serviceLastNameId" class="col-xs-10 col-sm-12" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Mobile Number </label>
	
							<div class="col-sm-5">
								<input type="text" id="serviceMobileNumberId" class="col-xs-10 col-sm-12 numeric" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Email Id </label>
	
							<div class="col-sm-5">
								<input type="text" id="serviceEmailId" class="col-xs-10 col-sm-12" />
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<div class="modal-footer">
				<button class="btn btn-sm" data-dismiss="modal">
					<i class="ace-icon fa fa-times"></i> Cancel
				</button>

				<button id="saveHistoryBtn" class="btn btn-sm btn-primary"
					data-dismiss="modal">
					<i class="ace-icon fa fa-check"></i> Save
				</button>
				<button id="updateHistoryBtn" class="btn btn-sm btn-primary hide"
					data-dismiss="modal">
					<i class="ace-icon fa fa-check"></i> Update
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modal-file" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">Add Asset Scan Document</h4>
			</div>

			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-5">
						<div class="space"></div>
						<div id="scanFileDivId">
							<input type="file" name="scanFile" />
							<input type="hidden" name="scanFileName" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button class="btn btn-sm" data-dismiss="modal">
					<i class="ace-icon fa fa-times"></i> Cancel
				</button>

				<button id="saveFileBtn" class="btn btn-sm btn-primary"
					data-dismiss="modal">
					<i class="ace-icon fa fa-check"></i> Save
				</button>
			</div>
		</div>
	</div>
			</div>

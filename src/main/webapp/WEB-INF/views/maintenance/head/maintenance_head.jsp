<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Maintenance Head <small> <i
			class="ace-icon fa fa-angle-double-right"></i> 
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<c:if test="${maintenanceHeadExist}">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert">
					<i class="ace-icon fa fa-times"></i>
				</button>
				<strong>
					<i class="ace-icon fa fa-times"></i>
					Oh snap!
				</strong>
				This Maintenance Head entry is exist for your society. please choose different Head name.
				<br />
			</div>
		</c:if>
		
		<c:if test="${not empty success}">
			<c:choose>
				<c:when test="${success}">
					<div class="alert alert-block alert-success">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>

						<p>
							<strong> <i class="ace-icon fa fa-check"></i> Well done!
							</strong> Your request is executed successfully.
						</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>
						<strong> <i class="ace-icon fa fa-times"></i> Oh snap!
						</strong> There is problem with executing your request. please contact society soft administrator. <br />
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		
		<form:form id="inserAndUpdateForm" commandName="maintenanceHeadDomain"
			class="form-horizontal" action="maintenanceHead">
			
			<form:hidden path="maintenanceHeadId" />
			<form:hidden path="calcId" />
			
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Head Name
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="maintenanceHeadName" class="col-xs-10 col-sm-4 upper-case" />
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Charge Type
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:select path="chargeTypeId" class="col-xs-10 col-sm-4">
							<c:forEach items="${chargeTypeDomainList}" var="chargeTypeDomain">
								<c:choose>
									<c:when test="${chargeTypeDomain.calcType eq 'No calculation'}">
										<option selected="selected" value="${chargeTypeDomain.calcTypeId}" >${chargeTypeDomain.calcType}</option>
									</c:when>
									<c:otherwise>
										<option value="${chargeTypeDomain.calcTypeId}">${chargeTypeDomain.calcType}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
					</div>
				</div>
			</div>
			
			<div id="fixedAmountDivId" class="form-group hide">
				<label class="col-sm-3 control-label no-padding-right">Fixed Amount
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="fixedAmount" class="col-xs-10 col-sm-4" />
					</div>
				</div>
			</div>
			
			<div id="maintenanceHeadDivId" class="form-group hide">
				<label class="col-sm-3 control-label no-padding-right">Maintenance Head
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:select path="referenceHeadId" class="col-xs-10 col-sm-4">
							<form:option value="0">Choose Maintenance Head</form:option>
							<form:options items="${maintenanceHeadList}" itemLabel="maintenanceHeadName" itemValue="maintenanceHeadId" />
						</form:select>
					</div>
				</div>
			</div>
			
			<div id="percentageDivId" class="form-group hide">
				<label class="col-sm-3 control-label no-padding-right">Percentage
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="percentageAmount" class="col-xs-10 col-sm-4" />
					</div>
				</div>
			</div>
			
			
			<div class="">
				<div class="col-sm-offset-3">
					<button id="maintenanceHeadSubmitBtn" type="submit"
						class="btn btn-sm btn-success">
						<i class="ace-icon fa fa-check bigger-110"></i>Submit
					</button>
					&nbsp; &nbsp; &nbsp;
					<button id="maintenanceHeadResetId" class="btn btn-sm" type="reset">
						<i class="ace-icon fa fa-undo bigger-110"></i> Reset
					</button>
				</div>
			</div>
	    </form:form>
	    <form:form id="deleteForm" commandName="maintenanceHeadDomain"
			class="form-horizontal hide" action="deleteMaintenanceHead">

			<input type="hidden" name="maintenanceHeadId" id="deleteMaintenanceHeadId" />
			<button id="deleteMaintenanceHeadBtn" type="submit">Submit</button>

		</form:form>
	    <div class="space-10"></div>
	    <c:choose>
			<c:when test="${not empty maintenanceHeadList}">
				<table id="simple-table" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Head Name</strong></th>
							<th><strong>Charge Type</strong></th>
							<th><strong>Fixed Amount</strong></th>
							<th><strong>Reference Head</strong></th>
							<th><strong>Percentage</strong></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ maintenanceHeadList }" var="maintenanceHead">
							<tr data-calcId="${maintenanceHead.calcId}">
								<td>${maintenanceHead.maintenanceHeadName}</td>
								<td data-chargeTypeId="${maintenanceHead.chargeTypeId}">${maintenanceHead.chargeType}</td>
								<td>${maintenanceHead.fixedAmount}</td>
								<td data-referenceHeadId="${maintenanceHead.referenceHeadId}">${maintenanceHead.referenceHeadName}</td>
								<td>${maintenanceHead.percentageAmount}</td>
								<td>
										<a id="editMaintenanceHeadId"
											data-maintenanceHeadId="${maintenanceHead.maintenanceHeadId}" href="#"
											class="btn btn-xs btn-info editMaintenanceHead"> <i
											class="ace-icon fa fa-pencil bigger-120"></i>
										</a>
										<button class="btn btn-xs btn-danger deleteMaintenanceHead"
											data-maintenanceHeadId="${maintenanceHead.maintenanceHeadId}">
											<i class="ace-icon fa fa-trash-o bigger-120"></i>
										</button>

								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<div style="text-align: center;">
					<h2>There is no single maintenance head entry recorded.</h2>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
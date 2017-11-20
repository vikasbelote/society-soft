<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Create Maintenance Bill <small> <i
			class="ace-icon fa fa-angle-double-right"></i> Create new maintenance
			bill
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<c:if test="${maintenanceDomain.cycleExist}">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert">
					<i class="ace-icon fa fa-times"></i>
				</button>
				<strong> <i class="ace-icon fa fa-times"></i> Oh snap!
				</strong> Selected payment cycle is already exist. <br />
			</div>
		</c:if>

		<form:form id="inserAndUpdateForm" commandName="maintenanceDomain"
			class="form-horizontal" action="maintaince">
			<form:hidden path="paymentCycleStartDate" />
			<div class="form-group">
				<label class="col-sm-3 control-label" for="form-field-1">Payment
					Cycle </label>
				<div class="col-sm-2">
					<form:select path="paymentCycle">
						<form:option value="">Choose Payment cycle</form:option>
						<form:options items="${cycleDateList}" />
					</form:select>
				</div>

				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">Payment Due Date</label>
				<div class="col-sm-2">
					<form:input cssClass="col-xs-10 col-sm-12 date-picker"
						path="paymentDueDate" data-date-format="yyyy-mm-dd" />
				</div>

			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"
					for="form-field-1">Additional Note</label>
				<div class="col-sm-6">
					<div class="widget-box widget-color-green col-sm-offset-3">
						<div id="additionalNoteHeader"
							class="widget-header widget-header-small">Please enter note
							text here</div>
						<div class="widget-body">
							<div>
								<textarea class="form-control" id="additinalNoteId"></textarea>
							</div>
							<div class="col-sm-offset-2">
								<button type="button" id="addNoteId"
									class="btn btn-sm btn-primary btn-white btn-round center">
									<span class="bigger-110">Add Note</span> <i
										class="icon-on-right ace-icon fa fa-arrow-right"></i>
								</button>
								<button type="button" id="clearNoteId"
									class="btn btn-sm btn-primary btn-white btn-round center">
									<span class="bigger-110">Clear Note</span> <i
										class="icon-on-right ace-icon fa fa-arrow-right"></i>
								</button>
							</div>
							<div class="space-6"></div>
							<div class="widget-main no-padding">
								<div class="h-25 d-inline-block">
									<ol id="additonalNoteList">
										<c:forEach items="${maintenanceDomain.additionalNote}" var="note">
											<li><span>${note}</span></li>
										</c:forEach>
									</ol>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="space-10"></div>
			<div class="">
				<div class="col-sm-offset-4">
					<button id="generateMaintenanceReportBtn" type="submit"
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
		<div class="space-10"></div>
		<c:if test="${not empty maintenanceTable}">
			<a href="downloadReceipt?id=${maintenanceTable.cycleId}" class="btn btn-white btn-success" target="_blank">Download Receipt</a>
			<table id="maintenanceTableId"
				class="table table-bordered table-hover">
				<thead>
					<tr>
						<th><strong>Member</strong></th>
						<th><strong>Bill No</strong></th>
						<c:forEach items="${maintenanceTable.maintenanceHeadDomainList}"
							var="maintenanceHead">
							<th data-generalHeadId="${maintenanceHead.maintenanceHeadId}">${maintenanceHead.maintenanceHeadName}</th>
						</c:forEach>
						<td><strong>Outstanding Amount</strong></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${maintenanceTable.memberList}" var="member">
						<tr>
							<td data-memberId="${member.memberId}">${member.name}</td>
							<td>${member.billNumber}</td>
							<c:forEach items="${member.maintenanceHeadChargeDomainList}" var="charge">
								<td data-maintenanceHeadId="${charge.maintenanceHeadId}">${charge.chargeValue}</td>
							</c:forEach>
							<td>${member.outstandingAmount}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<div class="col-xs-12 hide" id="content"></div>
	</div>
</div>
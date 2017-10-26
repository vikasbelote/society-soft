<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Member Maintenance Table <small> <i
			class="ace-icon fa fa-angle-double-right"></i> This table display
			maintenance of every society member
		</small>
	</h1>
</div>
<div class="row">
	<div id="table-data" class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<a id="generateReportId" href="#maintenanceReportForm" role="button"
			class="btn btn-primary" data-toggle="modal">Generate Report</a>
		<div class="space-6"></div>
		<c:choose>
			<c:when test="${not empty maintenanceTable}">
				<input type="hidden" id="societyNameId" value="${maintenanceTable.societyName}" />
				<input type="hidden" id="societyAddressId" value="${maintenanceTable.societyAdrress}" />
				<input type="hidden" id="maintenancePaymentDueInterestId" value="${maintenanceTable.maintenancePaymentDueInterest}" />
				<input type="hidden" id="maintenancePaymentChequeNameId" value="${maintenanceTable.maintenancePaymentChequeName}" />
				<table id="maintenanceTableId"
					class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Memeber Name</strong></th>
							<c:forEach items="${maintenanceTable.columnList}"
								var="generalHead">
								<th>${generalHead.generalHeadName}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${maintenanceTable.memberList}" var="member">
							<tr>
								<td>${member.name}</td>
								<c:forEach items="${member.generalHeadValues}" var="chargeValue">
									<td contenteditable="true">${chargeValue}</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<div style="text-align: center;">
					<h2>There is no single transaction entry recorded.</h2>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="col-xs-12" id="content"></div>
	<div id="maintenanceReportForm" class="modal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger">Enter additional information for Maintenance Receipt</h4>
				</div>

				<div class="modal-body">
					<div class="row">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Payment Due Date
									 <i class="fa fa-asterisk" style="color: red;"></i>
								</label>
								<div class="col-sm-9">
									<input id="paymentDueDateId" name="paymentDueDate"
										 class=" col-xs-10 col-sm-9 date-picker" data-date-format="yyyy-mm-dd"
										type="text">
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Maintenance Cycle
									 <i class="fa fa-asterisk" style="color: red;"></i>
								</label>
								<div class="col-sm-9">
									<select class=" col-xs-10 col-sm-9">
										<option>--Select--</option>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Additional Note
								</label>
								<div class="col-sm-9">
									<div class="widget-box widget-color-green">
											<div class="widget-header widget-header-small">  </div>

											<div class="widget-body">
												<div class="widget-main no-padding">
													<div class="wysiwyg-editor" id="editor2"></div>
												</div>
										 </div>
									</div>
								</div>
							</div>
								
						</form>
					</div>
				</div>

				<div class="modal-footer">
					<div id="modelMsgDiv" class="pull-left hide">
						<h6 id="modelMsg" class="blue bigger"></h6>
					</div>
					<button class="btn btn-sm" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> Cancel
					</button>

					<button id="saveReportBtn" class="btn btn-sm btn-primary"
						data-dismiss="modal">
						<i class="ace-icon fa fa-check"></i> Save
					</button>

					<button id="updateMemberBtn" class="btn btn-sm btn-primary hide"
						data-dismiss="modal">
						<i class="ace-icon fa fa-check"></i> Update
					</button>
				</div>
			</div>
		</div>
	</div>
</div>


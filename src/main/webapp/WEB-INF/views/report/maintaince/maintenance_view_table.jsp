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
		<c:if test="${not empty cycle}">
			<form class="form-horizontal">
	
				<div class="form-group">
					<label class="col-sm-3 control-label" for="form-field-1">Payment Cycle
					</label>
					<div class="col-sm-2">
						<input readonly="readonly" class="col-xs-10 col-sm-12" type="text" value="${cycle.startDate} to ${cycle.endDate}" />
					</div>
					
					<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">Payment Due Date</label>
					<div class="col-sm-2">	
						<input id="paymentDueDateId" class="col-xs-10 col-sm-12 date-picker" type="text" value="${cycle.paymentDueDate}" data-date-format="yyyy-mm-dd" />
					</div>
									
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="form-field-1">Additional Note</label>
					<div class="col-sm-6">
						<div class="widget-box widget-color-green col-sm-offset-3">
							<div id="additionalNoteHeader" class="widget-header widget-header-small">  </div>
							<div class="widget-body">
								<div>
									<textarea class="form-control" id="additinalNoteId"></textarea>
								</div>
								<div class="col-sm-offset-2">
									<button type="button" id="addNoteId" class="btn btn-sm btn-primary btn-white btn-round center">
										<span class="bigger-110">Add Note</span>
										<i class="icon-on-right ace-icon fa fa-arrow-right"></i>
									</button>
									<button type="button" id="clearNoteId" class="btn btn-sm btn-primary btn-white btn-round center">
										<span class="bigger-110">Clear Note</span>
										<i class="icon-on-right ace-icon fa fa-arrow-right"></i>
									</button>
								</div>
								<div class="space-6"></div>
								<div class="widget-main no-padding">
									<div class="h-25 d-inline-block">
										<ol id="additonalNoteList">
											<c:forEach items="${cycle.notes}" var="note">
												<li data-noteId="${note.noteId}"><span>${note.noteText}</span> <a style="cursor: pointer;text-decoration: underline;" class="delete-note text-danger">Delete</a></li>
											</c:forEach>
										</ol>
									</div>
								</div>
						 	</div>
						</div>
					</div>
				</div>
			</form>
			<div class="space-8"></div>
			<div class="row">
				<div class="col-xs-6 col-sm-offset-1 col-sm-6 center">
					<a id="generateReceiptId" href="#" role="button"
						class="btn btn-sm btn-primary">Generate Receipt</a>
					<a id="sendEmailId" href="#" role="button"
						class="btn btn-sm btn-primary">Email</a>
					<a id="sendMsgId" href="#" role="button"
						class="btn btn-sm btn-primary">Message</a>
				</div>
			</div>
			<div class="space-8"></div>
		</c:if>
		<c:choose>
			<c:when test="${not empty cycle}">
				<input type="hidden" id="paymentDueDate" value="${cycle.paymentDueDate}" />
				<input type="hidden" id="paymentCycle" value="${cycle.startDate} to ${cycle.endDate}"/>
				
				<table id="maintenanceTableId" data-cycleId="${cycle.cycleId}"
					class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Member</strong></th>
							<th><strong>Bill No</strong></th>
							<c:forEach items="${cycle.generalHeadList}"
								var="generalHead">
								<th data-generalHeadId="${generalHead.generalHeadId}">${generalHead.generalHeadName}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cycle.receipts}" var="receipt">
							<tr data-receiptId="${receipt.receiptId}">
								<td data-memberId="${receipt.memberId}">${receipt.memberName}</td>
								<td>${receipt.billNumber}</td>
								<c:forEach items="${receipt.chargeList}" var="charge">
									<td class="numeric" data-chargeId="${charge.chargeId}" data-generalHeadId="${charge.generalHeadId}" 
									    contenteditable="true">${charge.chargeValue}</td>
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
	<div class="col-xs-12 hide" id="content"></div>
</div>
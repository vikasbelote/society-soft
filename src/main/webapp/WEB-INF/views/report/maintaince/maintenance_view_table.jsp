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
	
		<div class="row">
			<div class="col-xs-2">
				<a id="generateReceiptId" href="#" role="button"
					class="btn btn-primary">Generate Receipt</a>
			</div>
			<div class="col-xs-6 center">
				<h3>For the period ${cycle.startDate} to ${cycle.endDate}</h3>
			</div>
			
		</div>
		<c:choose>
			<c:when test="${not empty cycle}">
				<input type="hidden" id="paymentDueDate" value="${cycle.paymentDueDate}" />
				<input type="hidden" id="paymentCycle" value="${cycle.startDate} to ${cycle.endDate}"/>
				
				<table id="maintenanceTableId" data-cycleId="${cycle.cycleId}"
					class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Memeber Name</strong></th>
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
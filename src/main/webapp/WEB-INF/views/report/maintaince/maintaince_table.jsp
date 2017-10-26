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
		<a id="generateReceiptId" href="#" role="button"
			class="btn btn-primary">Generate Receipt</a>
		<div class="space-6"></div>
		<c:choose>
			<c:when test="${not empty maintenanceTable}">
				<input type="hidden" id="societyNameId" value="${maintenanceTable.societyName}" />
				<input type="hidden" id="societyAddressId" value="${maintenanceTable.societyAdrress}" />
				<input type="hidden" id="maintenancePaymentDueInterestId" value="${maintenanceTable.maintenancePaymentDueInterest}" />
				<input type="hidden" id="maintenancePaymentChequeNameId" value="${maintenanceTable.maintenancePaymentChequeName}" />
				<input type="hidden" id="paymentDueDate" value="${maintenanceTable.paymentDueDate}" />
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
	<div class="col-xs-12 hide" id="content"></div>
</div>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty maintenanceCycleReceiptDomain}">
	<c:forEach items="${maintenanceCycleReceiptDomain.receipts}" var="receipt">
		<p style="font-size: 18px; margin-left: 150px;">${maintenanceCycleReceiptDomain.societyName}</p>
		<p style="margin-left: 170px;">${maintenanceCycleReceiptDomain.address}</p>
		<p style="font-size: 16px;">Name : ${receipt.memberName}</p>
		
		<table width="400" border="1">
			<thead>
				<tr>
					<th width="60">Sr No</th>
					<th width="220">Particulars</th>
					<th width="120">Amount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${receipt.chargeList}" var="charge">
					<tr>
						<td>${charge.srNumber}</td>
						<td>${charge.generalHeadName}</td>
						<td>${charge.chargeValue}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<p style="font-size: 16px;">Payment due date : ${maintenanceCycleReceiptDomain.paymentDueDate}</p>
		<p style="font-size: 16px; text-align: justify;">
			If the payment is not received on and before due date, Interest @${maintenanceCycleReceiptDomain.lateInterestRate}% p.a. on entire amount wil be applicable
		</p>
		<p style="font-size: 16px;">cheque should be drawan in the favour of "<strong>${maintenanceCycleReceiptDomain.chequeName}</strong>"</p>
		<!-- ADD_PAGE -->
	</c:forEach>
</c:if>

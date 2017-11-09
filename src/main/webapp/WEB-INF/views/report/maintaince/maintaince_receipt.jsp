<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty maintenanceCycleReceiptDomain}">
	<c:forEach items="${maintenanceCycleReceiptDomain.receipts}" var="receipt">
		<p style="font-size: 18px; margin-left: 150px;">${maintenanceCycleReceiptDomain.societyName}</p>
		<p style="margin-left: 170px;">${maintenanceCycleReceiptDomain.address}</p>
		<p style="font-size: 16px;">Name        : ${receipt.memberName}</p>
		<p style="font-size: 16px;">Bill Number : ${receipt.billNumber}</p>
		<p style="font-size: 16px;">For Period ${maintenanceCycleReceiptDomain.startDate} to ${maintenanceCycleReceiptDomain.endDate}</p>
		
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
				<c:if test="${not empty receipt.chargeList}">
					<tr>
						<td></td>
						<td>Total Payable Value</td>
						<td>${receipt.totalValue}</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		
		<p style="font-size: 16px;">Payment due date : ${maintenanceCycleReceiptDomain.paymentDueDate}</p>
		<p style="font-size: 16px; text-align: justify;">
			If the payment is not received on and before due date, Interest @${maintenanceCycleReceiptDomain.lateInterestRate}% p.a. on entire amount wil be applicable
		</p>
		<p style="font-size: 16px;">cheque should be drawan in the favour of "<strong>${maintenanceCycleReceiptDomain.chequeName}</strong>"</p>
		<p style="font-size: 16px;">Note: </p>
		<div style="margin-left:20px">
	      <ol>
	       	<c:forEach items="${maintenanceCycleReceiptDomain.notes}" var="note">
	       		<li>${note.noteText}</li>
	       	</c:forEach>
	      </ol>
	    </div>
		<!-- ADD_PAGE -->
	</c:forEach>
</c:if>


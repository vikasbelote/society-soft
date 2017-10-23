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
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<c:choose>
			<c:when test="${not empty maintenanceTable}">
				<table id="simple-table" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Memeber Name</strong></th>
							<th><strong>Description</strong></th>
							<th><strong>Amount</strong></th>
							<th><strong>Date</strong></th>
							<th><strong>Type</strong></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<<%-- c:forEach items="${ transactionDomainList }" var="item">
							<tr>
								<td data-generalHeadId="${item.generalHeadId}">${
									item.generalHeadName }</td>
								<td
									data-transactionDescriptionId="${item.transactionDescriptionId}">${
									item.transactionDescription }</td>
								<td>${ item.transactionAmount }</td>
								<td>${ item.transactionDate }</td>
								<td>${ item.transactionType }</td>
								<td><a id="editTransactionId"
									data-transactionId="${ item.transactionId }" href="#"
									class="btn btn-xs btn-info editTransaction"> <i
										class="ace-icon fa fa-pencil bigger-120"></i>
								</a>
									<button class="btn btn-xs btn-danger deleteTransaction"
										data-transactionId="${ item.transactionId }">
										<i class="ace-icon fa fa-trash-o bigger-120"></i>
									</button></td>
							</tr>
						</c:forEach> --%>
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
</div>
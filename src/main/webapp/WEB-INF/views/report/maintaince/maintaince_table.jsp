<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
	/* some elements used in demo only */
	.spinner-preview {
		width: 100px;
		height: 100px;
		text-align: center;
		margin-top: 60px;
	}
	
	.spinner{
	    position: absolute;
	    top: 50%;
	    left: 50%;
	    margin-left: -50px;
	    margin-top: -50px;
	    background-size: 100%;
	}
	
	.dropdown-preview {
		margin: 0 5px;
		display: inline-block;
	}
	.dropdown-preview  > .dropdown-menu {
		display: block;
		position: static;
		margin-bottom: 5px;
	}
</style>

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
		
		<i id="spinnerId" class="ace-icon hide fa fa-spinner fa-spin orange bigger-300 spinner"></i>
		
		
		<div class="row">
			<div class="col-xs-6">
				<a id="generateReceiptId" href="#" role="button"
					class="btn btn-primary">Generate Receipt</a>
			</div>
			<div class="col-xs-6 center">
				<h3>For the period ${maintenanceTable.paymentCycle}</h3>
			</div>
			
		</div>
		<c:choose>
			<c:when test="${not empty maintenanceTable}">
				<input type="hidden" id="paymentDueDate" value="${maintenanceTable.paymentDueDate}" />
				<input type="hidden" id="paymentCycle" value="${maintenanceTable.paymentCycle}" />
				<table id="maintenanceTableId"
					class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Memeber Name</strong></th>
							<c:forEach items="${maintenanceTable.columnList}"
								var="generalHead">
								<th data-generalHeadId="${generalHead.generalHeadId}">${generalHead.generalHeadName}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${maintenanceTable.memberList}" var="member">
							<tr>
								<td data-memberId="${member.memberId}">${member.name}</td>
								<c:forEach items="${member.generalHeadValues}" var="charge">
									<td data-generalHeadId="${charge.generalHeadId}" contenteditable="true">${charge.chargeValue}</td>
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


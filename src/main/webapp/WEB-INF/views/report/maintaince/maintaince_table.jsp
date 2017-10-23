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
							<c:forEach items="${maintenanceTable.columnList}"
								var="generalHead">
								<th>${generalHead.generalHeadName}</th>
							</c:forEach>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${maintenanceTable.memberList}" var="member">
							<tr>
								<td>${member.name}</td>
								<c:forEach items="${member.generalHeadValues}" var="chargeValue">
									<td>${chargeValue}</td>
								</c:forEach>
								<td></td>
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
</div>
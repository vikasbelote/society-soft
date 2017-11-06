<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Email Notification <small> <i
			class="ace-icon fa fa-angle-double-right"></i> 
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<c:choose>
			<c:when test="${not empty cycleList}">
				<table id="maintenanceCycleTableId" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Start Date</strong></th>
							<th><strong>End Date</strong></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cycleList}" var="cycle">
							<tr>
								<td>${cycle.startDate}</td>
								<td>${cycle.endDate}</td>
								<td><a href="viewEmailStatus?id=${cycle.cycleId}">View</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<div style="text-align: center;">
					<h2>There is no single maintenance cycle recorded.</h2>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
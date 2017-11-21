<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		View Maintenance Bill Report <small> <i
			class="ace-icon fa fa-angle-double-right"></i> View bill status as per maintenance cycle
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<c:choose>
			<c:when test="${not empty cycleList}">
				<table id="maintenanceCycleTableId"
					class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Payment Due Date</strong></th>
							<th><strong>Start Date</strong></th>
							<th><strong>End Date</strong></th>
							<th><strong>Status</strong></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cycleList}" var="cycle">
							<tr>
								<td>${cycle.paymentDueDate}</td>
								<td>${cycle.startDate}</td>
								<td>${cycle.endDate}</td>
								<td>
									<c:choose>
										<c:when test="${cycle.isActive}">
											<span class="label label-sm label-success arrowed">Active</span>
										</c:when>
										<c:otherwise>
											<span class="label label-sm label-danger arrowed-in">Not Active</span>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<a class="btn btn-xs btn-success" href="viewCycleBillStatus?id=${cycle.cycleId}" data-rel="tooltip" title="Edit Bill Status"> 
										<i class="ace-icon fa fa-pencil bigger-120"></i>
									</a>
								</td>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		View Maintenance Report <small> <i
			class="ace-icon fa fa-angle-double-right"></i> View existing
			maintenance report
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<c:if test="${not empty deleted}">

			<c:choose>
				<c:when test="${deleted}">
					<div class="alert alert-block alert-success">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>

						<p>
							<strong> <i class="ace-icon fa fa-check"></i> Well done!
							</strong> You successfully delete the cycle details.
						</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>
						<strong> <i class="ace-icon fa fa-times"></i> Oh snap!
						</strong> This general head details is already exist for that section. <br />
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>

		<c:choose>
			<c:when test="${not empty cycleList}">
				<table id="maintenanceCycleTableId"
					class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Payment Due Date</strong></th>
							<th><strong>Start Date</strong></th>
							<th><strong>End Date</strong></th>
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
									<a class="btn btn-xs btn-success" href="viewCycleDetails?id=${cycle.cycleId}" data-rel="tooltip" title="Edit Cycle Details"> 
										<i class="ace-icon fa fa-pencil bigger-120"></i>
									</a>
									<a id="emailMaintenanceId" data-cycleId="${cycle.cycleId}" class="btn btn-xs btn-primary" href="#" data-rel="tooltip" title="Email Receipt to Member">
										<i class="ace-icon fa fa-envelope bigger-120"></i>
									</a>
									<a id="#" data-cycleId="${cycle.cycleId}" class="btn btn-xs btn-pink" href="#" data-rel="tooltip" title="Message Receipt to Member">
										<i class="ace-icon fa fa-comment bigger-120"></i>
									</a>
									<a href="downloadReceipt?id=${cycle.cycleId}" class="btn btn-xs btn-purple" target="_blank" data-rel="tooltip" title="Download Receipt">
										<i class="ace-icon fa fa-cloud-download bigger-120"></i>
									</a>
									<a class="btn btn-xs btn-danger" href="deleteCycleDetais?id=${cycle.cycleId}" data-rel="tooltip" title="Delete Cycle Details">
										<i class="ace-icon fa fa-trash-o bigger-120"></i>
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
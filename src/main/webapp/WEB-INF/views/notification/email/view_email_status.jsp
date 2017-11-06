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
		<a id="sendEmailId" href="#" role="button"
					class="btn btn-primary">Email</a>
		<div class="space-6"></div>
		<c:choose>
			<c:when test="${not empty emailStatusDomainList}">
				<table data-cycleId="${cycleId}" id="emailStatusTableId" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><input id="checkFailMemberId" name="form-field-checkbox" type="checkbox" class="ace" />
														<span class="lbl"><strong>Check Fail Member</strong></span></th>
							<th><strong>Bill Number</strong></th>
							<th><strong>Member Name</strong></th>
							<th><strong>Send Date</strong></th>
							<th><strong>Email Status</strong></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${emailStatusDomainList}" var="emailStatus">
							<tr>
								<td><input type="checkbox" value="${emailStatus.memberId}"/></td>
								<td>${emailStatus.billNumber}</td>
								<td>${emailStatus.memberName}</td>
								<td>${emailStatus.sendDate}</td>
								<td>${emailStatus.mailStatus}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<div style="text-align: center;">
					<h2>There is no email status recorded for this cycle.</h2>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
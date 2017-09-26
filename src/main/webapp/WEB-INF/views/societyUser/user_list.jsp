<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page-header">
	<h1>
		User List <small> <i class="ace-icon fa fa-angle-double-right"></i>
			All user of society
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->

		<div class="row">
			<div class="col-xs-12">
				<table id="simple-table" class="table  table-bordered table-hover">
					<thead>
						<tr>
							<th>User Name</th>
							<th>Role</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Mobile Number</th>
							<th>Email Id</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ userDomainList }" var="userDomain">
							<tr>
								<td>${ userDomain.userName }</td>
								<td>${ userDomain.role.roleName }</td>
								<td>${ userDomain.person.firstName  }</td>
								<td>${ userDomain.person.lastName  }</td>
								<td>${ userDomain.person.contactNumber  }</td>
								<td>${ userDomain.person.emailId }</td>
								<td><a href="./viewUser?id=${ userDomain.userId }">View</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- /.span -->
		</div>
		<!-- /.row -->



	</div>
</div>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Society Member <small> <i
			class="ace-icon fa fa-angle-double-right"></i>This page is used to
			edit society member details.
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		
		<c:if test="${memberExist}">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert">
					<i class="ace-icon fa fa-times"></i>
				</button>
				<strong>
					<i class="ace-icon fa fa-times"></i>
					Oh snap!
				</strong>
				This society member details is already exist. please choose different First and Last name, Mobile Number, email id.
				<br />
			</div>
		</c:if>
		
		
		<c:if test="${not empty deleted}">
			<c:choose>
				<c:when test="${deleted}">
					<div class="alert alert-block alert-success">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>

						<p>
							<strong> <i class="ace-icon fa fa-check"></i> Well done!
							</strong> You successfully delete the member details.
						</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>
						<strong> <i class="ace-icon fa fa-times"></i> Oh snap!
						</strong> There is problem with deleting member details. <br />
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<c:if test="${not empty saveOrUpdate}">
			<c:choose>
				<c:when test="${saveOrUpdate}">
					<div class="alert alert-block alert-success">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>

						<p>
							<strong> <i class="ace-icon fa fa-check"></i> Well done!
							</strong> You successfully save the member details.
						</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>
						<strong> <i class="ace-icon fa fa-times"></i> Oh snap!
						</strong> There is problem with saving member details. <br />
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<form:form id="some-form" commandName="societyMemberDomain"
			class="form-horizontal" action="member">
			
			<form:hidden path="memberId"/>
			<form:hidden path="personId"/>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">First
					Name <i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="firstName" class="col-xs-10 col-sm-4"
							placeholder="Password" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Middle
					Name</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="middleName" class="col-xs-10 col-sm-4"
							placeholder="Password" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Last
					Name <i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="lastName" class="col-xs-10 col-sm-4"
							placeholder="Password" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Mobile
					Number <i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-mobile"></i></span>
						<form:input path="mobileNumber" class="col-xs-10 col-sm-4 numeric"
							placeholder="(845)555-1212" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Email
					Id <i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
						<form:input path="emailId" class="col-xs-10 col-sm-4"
							placeholder="Password" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Wing
					Number </label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-home"></i></span>
						<form:input path="wingNumber" class="col-xs-10 col-sm-4"
							placeholder="Password" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Flat
					Number <i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-home"></i></span>
						<form:input path="flatNumber" class="col-xs-10 col-sm-4"
							placeholder="Password" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Square
					Feet <i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-home"></i></span>
						<form:input path="squareFeet" class="col-xs-10 col-sm-4"
							placeholder="Password" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Additional
					Area </label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-home"></i></span>
						<form:select path="additionalAreaId" class="col-xs-10 col-sm-4">
							<option value="0">Choose Additional Area</option>
							<option value="1">Terrace</option>
							<option value="2">Mezzanine Floors</option>
							<option value="3">Other</option>
						</form:select>
					</div>
				</div>
			</div>

			<div>
				<div class="col-sm-offset-3">
					<button id="saveMemberBtnId" type="submit"
						class="btn btn-success btn-sm">
						<i class="ace-icon fa fa-check bigger-110"></i>Submit
					</button>
					&nbsp; &nbsp; &nbsp;
					<button class="btn btn-sm" type="reset" id="resetMemberBtnId">
						<i class="ace-icon fa fa-undo bigger-110"></i> Reset
					</button>
				</div>
			</div>
		</form:form>
		<form:form id="deleteForm" commandName="societyMemberDomain"
			class="form-horizontal hide" action="deleteMember">

			<input type="hidden" name="memberId" id="deleteMemberId" />
			<button id="deleteMemberBtn" type="submit">Submit</button>

		</form:form>
		<div class="space-8"></div>
		<c:choose>
			<c:when test="${not empty memberList}">
				<table id="simple-table" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>First Name</strong></th>
							<th><strong>Middle Name</strong></th>
							<th><strong>Last Name</strong></th>
							<th><strong>Mobile Number</strong></th>
							<th><strong>Email Id</strong></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ memberList }" var="member">
							<tr>
								<td>${ member.firstName }</td>
								<td>${ member.middleName }</td>
								<td>${ member.lastName }</td>
								<td>${ member.mobileNumber }</td>
								<td>${ member.emailId }</td>
								<td>
										<a id="editMember" data-memberId="${ member.memberId }" data-personId="${ member.personId }"
											href="#"
											class="btn btn-xs btn-info editMember"> <i
											class="ace-icon fa fa-pencil bigger-120"></i>
										</a>
										<button class="btn btn-xs btn-danger deleteMember"
											data-memberId="${ member.memberId }">
											<i class="ace-icon fa fa-trash-o bigger-120"></i>
										</button>

									</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<div style="text-align: center;">
					<h2>There is no single member entry recorded.</h2>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
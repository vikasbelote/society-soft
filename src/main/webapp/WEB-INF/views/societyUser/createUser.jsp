<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		New User <small> <i class="ace-icon fa fa-angle-double-right"></i>
			Detail of society user
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<c:if test="${userExist}">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert">
					<i class="ace-icon fa fa-times"></i>
				</button>
				<strong>
					<i class="ace-icon fa fa-times"></i>
					Oh snap!
				</strong>
				This user details is already exist, please choose different User Name or Mobile Number or Email Id
				<br />
			</div>
		</c:if>

		<form:form id="some-form" commandName="societyUserDomain"
			class="form-horizontal" action="createUser">

			<form:hidden path="userId" />
			<form:hidden path="personId" />
			<form:hidden path="rights" />
			<form:hidden path="roleId" />
			<form:hidden path="roleName" />

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">User
					Name<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="userName" class="col-xs-10 col-sm-4"
							placeholder="User Name" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Password
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-key"></i></span>

						<form:input path="userPassword" class="col-xs-10 col-sm-4"
							placeholder="Password" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">First
					Name <i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="firstName" class="col-xs-10 col-sm-4"
							placeholder="First Name" />
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
							placeholder="Middle Name" />
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
							placeholder="Last Name" />
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
						<form:input path="contactNumber" class="col-xs-10 col-sm-4 numeric"
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
							placeholder="Email Id" />
					</div>
				</div>
			</div>
			<c:if test="${societyUserDomain.roleName eq 'User'}">
				<div class="widget-box widget-color-blue2">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller">User Rights</h4>
					</div>
	
					<div class="widget-body">
						<div class="widget-main padding-8">
							<ul id="tree1">
							</ul>
						</div>
					</div>
				</div>
			</c:if>
			
			<div class="clearfix form-actions">
				<div class="col-sm-offset-3">
					<button id="societyUserSubmitBtn" type="submit"
						class="btn btn-success">
						<i class="ace-icon fa fa-check bigger-110"></i>Submit
					</button>
					&nbsp; &nbsp; &nbsp;
					<button class="btn" type="reset">
						<i class="ace-icon fa fa-undo bigger-110"></i> Reset
					</button>
				</div>
			</div>
		</form:form>
	</div>
</div>
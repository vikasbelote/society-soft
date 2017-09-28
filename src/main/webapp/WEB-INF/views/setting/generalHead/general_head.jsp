<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		General Head <small> <i
			class="ace-icon fa fa-angle-double-right"></i> Head is required to
			map entry in current year
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form:form id="some-form" commandName="genaralHeadDomain"
			class="form-horizontal" action="generalHead">

			<form:hidden path="generalHeadId" />

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">General
					Head <i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="generalHeadName" class="col-xs-10 col-sm-4"
							placeholder="General Head" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Balance
					Sheet Section <i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:select path="sectionId" class="col-xs-10 col-sm-4">
							<form:option value="-1" label="--- Select ---" />
							<form:option value="1" label="Calpital & Liabilities" />
							<form:option value="2" label="Assets" />
						</form:select>
					</div>
				</div>
			</div>

			<div class="clearfix form-actions">
				<div class="col-sm-offset-3">
					<button id="generalHeadSubmitBtn" type="submit"
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
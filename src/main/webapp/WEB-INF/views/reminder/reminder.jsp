<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Reminder <small> <i class="ace-icon fa fa-angle-double-right"></i>
			Send reminder to registered society member
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form:form id="some-form" commandName="societyUserDomain"
			class="form-horizontal" action="createUser">

			<div class="form-group">
				<div class="col-sm-12">
					<div class="wysiwyg-editor" id="editor1"></div>
				</div>
			</div>
			<div class="space-10"></div>
			<div class="form-group">
				<div class="col-sm-12 checkbox">
					<label class="col-sm-offset-2"> <input
						name="form-field-checkbox" type="checkbox" class="ace" /> <span
						class="lbl"> Send this message to particular society member</span>
					</label>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<label class="col-sm-3 control-label no-padding-right"
						for="form-field-1"> Please select member
					</label>

					<div class="col-sm-2">
						<select class="chosen-select form-control col-xs-10 col-sm-5"
							id="form-field-select-3" data-placeholder="Choose a State...">
							<option value=""></option>
							<option value="AL">Alabama</option>
							<option value="AK">Alaska</option>
							<option value="AZ">Arizona</option>
							<option value="AR">Arkansas</option>
							<option value="CA">California</option>
							<option value="CO">Colorado</option>
							<option value="CT">Connecticut</option>
							<option value="DE">Delaware</option>
							<option value="FL">Florida</option>
							<option value="GA">Georgia</option>
							<option value="HI">Hawaii</option>
						</select>
					</div>
				</div>
			</div>
			
			<div class="clearfix form-actions">
				<div class="col-sm-offset-3">
					<button id="societySubmitBtn" type="submit" class="btn btn-success">
						<i class="ace-icon fa fa-envelope bigger-110"></i>Send
					</button>
					&nbsp; &nbsp; &nbsp;
					<button class="btn" type="reset">
						<i class="ace-icon fa fa-undo bigger-110"></i> Reset
					</button>
				</div>
			</div>
		</form:form>

		<!-- PAGE CONTENT END -->
	</div>
</div>

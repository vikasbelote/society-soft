<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Parking Manager <small> <i
			class="ace-icon fa fa-angle-double-right"></i> This page is used to
			configure parking slot alloted to society member.
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<c:if test="${not empty save}">
			<c:choose>
				<c:when test="${save}">
					<div class="alert alert-block alert-success">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>

						<p>
							<strong> <i class="ace-icon fa fa-check"></i> Well done!
							</strong> You successfully save asset information.
						</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>
						<strong> <i class="ace-icon fa fa-times"></i> Oh snap!
						</strong> There is problem with inserting asset information. <br />
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		<!-- PAGE CONTENT BEGINS -->
		<form:form id="some-form" commandName="adminParkingManagerDomain"
			method="post" class="form-horizontal" action="parkingManager">

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right"
					for="form-field-1"> Slot  </label>

				<div class="col-sm-2">
					<input type="text" class="col-xs-10 col-sm-12" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right"
					for="form-field-1-1"> Member </label>

				<div class="col-sm-2">
					<select class="chosen-select col-xs-10 col-sm-5"
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
			<div>
				<div class="col-sm-offset-3">
					<button id="uploadFileId" type="submit" class="btn btn-success btn-sm">
						<i class="ace-icon fa fa-check bigger-110"></i>Submit
					</button>
				</div>
			</div>


		</form:form>

	</div>
</div>
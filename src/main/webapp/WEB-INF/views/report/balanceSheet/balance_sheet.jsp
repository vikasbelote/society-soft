<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Balance Sheet <small> <i
			class="ace-icon fa fa-angle-double-right"></i> This report display
			balance sheet of society account.
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form:form id="inserAndUpdateForm" commandName="balanceSheetDomain"
			class="form-horizontal" action="balanceSheet">

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Current Year
					 <i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-2 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<form:input cssClass="col-xs-10 col-sm-12 date-picker"
							path="currentYearStartDate" data-date-format="yyyy-mm-dd"
							placeholder="Stat Date" />
					</div>
				</div>
				<div class="col-sm-5 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<form:input cssClass="col-xs-10 col-sm-4 date-picker"
							path="currentYearEndDate" data-date-format="yyyy-mm-dd"
							placeholder="End Date" />
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Last Year
					 <i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-2 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<form:input cssClass="col-xs-10 col-sm-12 date-picker"
							path="lastYearStartDate" data-date-format="yyyy-mm-dd"
							placeholder="Stat Date" />
					</div>
				</div>
				<div class="col-sm-5 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<form:input cssClass="col-xs-10 col-sm-4 date-picker"
							path="lastYearEndDate" data-date-format="yyyy-mm-dd"
							placeholder="End Date" />
					</div>
				</div>
			</div>

			<div>
				<div class="col-sm-offset-3">
					<button id="balanceSheetSubmitBtn" type="submit"
						class="btn btn-sm btn-success">
						<i class="ace-icon fa fa-check bigger-110"></i>Submit
					</button>
					&nbsp; &nbsp; &nbsp;
					<button class="btn btn-sm" type="reset">
						<i class="ace-icon fa fa-undo bigger-110"></i> Reset
					</button>
				</div>
			</div>
		</form:form>
		<!-- /.row -->
	</div>
</div>
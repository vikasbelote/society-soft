<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Create Maintenance Report <small> <i
			class="ace-icon fa fa-angle-double-right"></i> Create new maintenance report
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<c:if test="${cycleExist}">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert">
					<i class="ace-icon fa fa-times"></i>
				</button>
				<strong>
					<i class="ace-icon fa fa-times"></i>
					Oh snap!
				</strong>
				Select payment cycle is already exist.
				<br />
			</div>
		</c:if>
		
		<form:form id="inserAndUpdateForm" commandName="maintenanceDomain"
			class="form-horizontal" action="maintaince">
			<h3 class="header smaller lighter green">General Head</h3>
			<form:hidden path="paymentCycleStartDate" />
			<c:forEach items="${generalHeadList}" var="twoGeneralHeadList">
				<div class="form-group">
					<c:if test="${twoGeneralHeadList.size() > 0}">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">${twoGeneralHeadList.get(0).generalHeadName} </label>
						<div class="col-sm-2">
							<input type="text" id="societyNameId" name="generalHeadChargeMap[${twoGeneralHeadList.get(0).generalHeadId}]"
								class="col-xs-10 col-sm-12 numeric" />
						</div>
					</c:if>

					<c:if test="${twoGeneralHeadList.size() > 1}">
						<label class="col-sm-2 control-label" for="form-field-1">${twoGeneralHeadList.get(1).generalHeadName}
						</label>
						<div class="col-sm-2">
							<input type="text" id="societyNameId" name="generalHeadChargeMap[${twoGeneralHeadList.get(1).generalHeadId}]"
								class="col-xs-10 col-sm-12 numeric" />
						</div>
					</c:if>
				</div>
			</c:forEach>
			<h3 class="header smaller lighter green">Other Details</h3>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="form-field-1">Payment Cycle
				</label>
				<div class="col-sm-2">
					<form:select path="paymentCycle">
						<form:option value="">Choose Payment cycle</form:option>
						<form:options items="${cycleDateList}" />
					</form:select>
				</div>
				
				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">Payment Due Date</label>
				<div class="col-sm-2">	
					<form:input cssClass="col-xs-10 col-sm-12 date-picker" path="paymentDueDate" 
					data-date-format="yyyy-mm-dd" />
				</div>
								
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right"
					for="form-field-1">Additional Note</label>
				<div class="col-sm-6">
					<div class="widget-box widget-color-green col-sm-offset-3">
						<div id="additionalNoteHeader" class="widget-header widget-header-small">  </div>
						<div class="widget-body">
							<div class="widget-main no-padding">
								<div class="wysiwyg-editor" id="editor2"></div>
							</div>
					 	</div>
					</div>
				</div>
			</div>
			<div class="space-10"></div>
			<div class="">
				<div class="col-sm-offset-4">
					<button id="generateMaintenanceReportBtn" type="submit"
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
	</div>
</div>
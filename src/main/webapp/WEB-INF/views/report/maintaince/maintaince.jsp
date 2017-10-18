<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Member Maintenance <small> <i
			class="ace-icon fa fa-angle-double-right"></i> This report display
			maintenance of every society member
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form class="form-horizontal" role="form" method="post"
			action="maintaince">

			<c:forEach items="${generalHeadList}" var="twoGeneralHeadList">
				<div class="form-group">
					<c:if test="${twoGeneralHeadList.size() > 0}">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">${twoGeneralHeadList.get(0).generalHeadName} </label>
						<div class="col-sm-2">
							<input type="text" id="societyNameId" name="${twoGeneralHeadList.get(0).generalHeadId}"
								class="col-xs-10 col-sm-12" />
						</div>
					</c:if>

					<c:if test="${twoGeneralHeadList.size() > 1}">
						<label class="col-sm-2 control-label" for="form-field-1">${twoGeneralHeadList.get(1).generalHeadName}
						</label>
						<div class="col-sm-2">
							<input type="text" id="societyNameId" name="${twoGeneralHeadList.get(1).generalHeadId}"
								class="col-xs-10 col-sm-12" />
						</div>
					</c:if>
				</div>
			</c:forEach>
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

		</form>
	</div>
</div>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Transaction <small> <i
			class="ace-icon fa fa-angle-double-right"></i> Entry that is required
			to generate balance sheet
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form:form id="inserAndUpdateForm" commandName="transactionDomain"
			class="form-horizontal" action="recordTransaction">

			<form:hidden path="transactionId" />

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">General
					Head <i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-home"></i></span>
						<form:select path="generalHeadId" class="col-xs-10 col-sm-4">
							<form:option value="-1" label="--- Select ---" />
							<form:options itemLabel="text" itemValue="value" items="${generalHeadList}" />
						</form:select>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Transaction Type
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-home"></i></span>
						<form:select path="transactionTypeId" class="col-xs-10 col-sm-4">
							<form:option value="-1" label="--- Select ---" />
							<form:options itemLabel="text" itemValue="value" items="${transactionTypeList}" />
						</form:select>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Transaction Amount
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="transactionAmount" class="col-xs-10 col-sm-4"
							placeholder="Transaction Amount" />
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Transaction Description
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="transactionDescription" class="col-xs-10 col-sm-4"
							placeholder="Transaction Description" />
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Transaction Date
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="transactionDate" class="col-xs-10 col-sm-4"
							placeholder="Transaction Date" />
					</div>
				</div>
			</div>

			<div class="">
				<div class="col-sm-offset-3">
					<button id="generalHeadSubmitBtn" type="submit"
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
		<form:form id="deleteForm" commandName="genaralHeadDomain"
			class="form-horizontal hide" action="deleteTransaction">

			<input type="hidden" name="transactionId" id="deleteTransactionId" />
			<button id="deleteTransactionBtn" type="submit">Submit</button>

		</form:form>
		<div class="space-8"></div>
		<c:choose>
			<c:when test="${not empty transactionList}">
				<table id="simple-table" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>General Head</strong></th>
							<th><strong>Balance Sheet Section</strong></th>
							<th><strong>Is Default</strong></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ generalHeadList }" var="item">
							<tr>
								<td>${ item.generalHeadName }</td>
								<td data-sectionId="${ item.sectionId }">${ item.sectionName }</td>
								<td>${ item.isDefault }</td>
								<td><c:if test="${not item.isDefault }">
										<a id="editGeneralHeadId"
											data-generalHeadId="${ item.generalHeadId }" href="#"
											class="btn btn-xs btn-info editGeneralHead"> <i
											class="ace-icon fa fa-pencil bigger-120"></i>
										</a>
										<button class="btn btn-xs btn-danger deleteGeneralHead"
											data-generalHeadId="${ item.generalHeadId }">
											<i class="ace-icon fa fa-trash-o bigger-120"></i>
										</button>

									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<div style="text-align: center;">
					<h2>There is no data present for this search criteria.</h2>
				</div>
			</c:otherwise>
		</c:choose>

	</div>
</div>
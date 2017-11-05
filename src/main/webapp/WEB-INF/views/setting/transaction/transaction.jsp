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
		<c:if test="${transactionExist}">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert">
					<i class="ace-icon fa fa-times"></i>
				</button>
				<strong>
					<i class="ace-icon fa fa-times"></i>
					Oh snap!
				</strong>
				This transaction entry is exist for given finaincial year.
				<br />
			</div>
		</c:if>
		
		<form:form id="inserAndUpdateForm" commandName="transactionDomain"
			class="form-horizontal" action="recordTransaction">

			<form:hidden path="transactionId" />
			
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Transaction Description
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:hidden path="transactionDescriptionId" />
						<form:input path="transactionDescription" class="col-xs-10 col-sm-4 upper-case"
							placeholder="Transaction Description" />
					</div>
				</div>
			</div>

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
				<label class="col-sm-3 control-label no-padding-right">Transaction Amount
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="transactionAmount" class="col-xs-10 col-sm-4 numeric"
							placeholder="Transaction Amount" />
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">Transaction Date
					<i class="fa fa-asterisk" style="color: red;"></i>
				</label>
				<div class="col-sm-9 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<form:input cssClass="col-xs-10 col-sm-4 date-picker" path="transactionDate" data-date-format="yyyy-mm-dd"
							placeholder="Transaction Date" />
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
						<form:select path="transactionType" class="col-xs-10 col-sm-4">
							<form:option value="ADD" label="ADD" />
							<form:option value="SUBTRACT" label="SUBTRACT" />
							<form:option value="DISPLAY" label="DISPLAY" />
						</form:select>
					</div>
				</div>
			</div>

			<div class="">
				<div class="col-sm-offset-3">
					<button id="transactionSubmitBtn" type="submit"
						class="btn btn-sm btn-success">
						<i class="ace-icon fa fa-check bigger-110"></i>Submit
					</button>
					&nbsp; &nbsp; &nbsp;
					<button id="transactionFormResetId" class="btn btn-sm" type="reset">
						<i class="ace-icon fa fa-undo bigger-110"></i> Reset
					</button>
				</div>
			</div>
		</form:form>
		<form:form id="deleteForm" commandName="transactionDomain"
			class="form-horizontal hide" action="deleteTransaction">

			<input type="hidden" name="transactionId" id="deleteTransactionId" />
			<button id="deleteTransactionBtn" type="submit">Submit</button>

		</form:form>
		<div class="space-8"></div>
		<c:choose>
			<c:when test="${not empty transactionDomainList}">
				<table id="simple-table" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>General Head</strong></th>
							<th><strong>Description</strong></th>
							<th><strong>Amount</strong></th>
							<th><strong>Date</strong></th>
							<th><strong>Type</strong></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ transactionDomainList }" var="item">
							<tr>
								<td data-generalHeadId="${item.generalHeadId}">${ item.generalHeadName }</td>
								<td data-transactionDescriptionId="${item.transactionDescriptionId}">${ item.transactionDescription }</td>
								<td>${ item.transactionAmount }</td>
								<td>${ item.transactionDate }</td>
								<td>${ item.transactionType }</td>
								<td>
										<a id="editTransactionId"
											data-transactionId="${ item.transactionId }" href="#"
											class="btn btn-xs btn-info editTransaction"> <i
											class="ace-icon fa fa-pencil bigger-120"></i>
										</a>
										<button class="btn btn-xs btn-danger deleteTransaction"
											data-transactionId="${ item.transactionId }">
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
					<h2>There is no single transaction entry recorded.</h2>
				</div>
			</c:otherwise>
		</c:choose>

	</div>
</div>
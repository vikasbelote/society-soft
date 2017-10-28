<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="com.society.constant.SectionEnum"%>

<div class="page-header">
	<h1>
		Society Configuration <small> <i
			class="ace-icon fa fa-angle-double-right"></i> This page is used to
			change society configuation
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<h3 class="header smaller lighter green">Balance Sheet General
			Head Order</h3>
		<div class="row">
			<div class="col-xs-12">
				<div class="" id="main-widget-container">
					<div class="row">
						<div class="col-xs-12 col-sm-6 widget-container-col"
							id="widget-container-col-9">
							<div class="widget-box widget-color-pink" id="widget-box-9">
								<div class="widget-header">
									<h5 class="widget-title">LIABILITIES AND CAPITAL</h5>
									<div class="widget-toolbar no-border">
										<label> <input type="checkbox" id="lcSectionId"
											class="ace ace-switch ace-switch-3 drag-switch" /> <span
											class="lbl middle"></span>
										</label>
									</div>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div class="dd" id="nestable">
											<ol id="lcGeneralHeadList" class="dd-list custom-nodrag">
												<c:forEach items="${balanceSheetGeneralHeadList }"
													var="generalHead">
													<c:set var="LiabilitiesSectionName"
														value="<%=SectionEnum.LC.value()%>" />
													<c:if
														test="${generalHead.sectionName eq LiabilitiesSectionName}">
														<li class="dd-item"
															data-id="${ generalHead.generalHeadId }"
															data-orderId="${generalHead.orderId}">
															<div class="dd-handle">${
																generalHead.generalHeadName }</div>
														</li>
													</c:if>
												</c:forEach>
											</ol>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 widget-container-col"
							id="widget-container-col-9">
							<div class="widget-box widget-color-pink" id="widget-box-9">
								<div class="widget-header">
									<h5 class="widget-title">PROPERTIES AND ASSETS</h5>
									<div class="widget-toolbar no-border">
										<label> <input type="checkbox" id="paSectionId"
											class="ace ace-switch ace-switch-3 drag-switch" /> <span
											class="lbl middle"></span>
										</label>
									</div>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div class="dd" id="nestable">
											<ol id="paGeneralHeadList" class="dd-list custom-nodrag">
												<c:forEach items="${balanceSheetGeneralHeadList }"
													var="generalHead">
													<c:set var="AssetSectionName"
														value="<%=SectionEnum.PA.value()%>" />
													<c:if test="${generalHead.sectionName eq AssetSectionName}">
														<li class="dd-item"
															data-id="${ generalHead.generalHeadId }"
															data-orderId="${generalHead.orderId}">
															<div class="dd-handle">${
																generalHead.generalHeadName }</div>
														</li>
													</c:if>
												</c:forEach>
											</ol>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<h3 class="header smaller lighter green">Income & Expenditure
			General Head Order</h3>
		<div class="row">
			<div class="col-xs-12">
				<div class="" id="main-widget-container">
					<div class="row">
						<div class="col-xs-12 col-sm-6 widget-container-col"
							id="widget-container-col-9">
							<div class="widget-box widget-color-pink" id="widget-box-9">
								<div class="widget-header">
									<h5 class="widget-title">EXPENSES</h5>
									<div class="widget-toolbar no-border">
										<label> <input type="checkbox" id="exSectionId"
											class="ace ace-switch ace-switch-3 drag-switch" /> <span
											class="lbl middle"></span>
										</label>
									</div>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div class="dd" id="nestable">
											<ol id="exGeneralHeadList" class="dd-list custom-nodrag">
												<c:forEach items="${incomeAndExpenseGeneralHeadList}"
													var="generalHead">
													<c:set var="ExpensesSectionName"
														value="<%=SectionEnum.EXPENSES.value()%>" />
													<c:if
														test="${generalHead.sectionName eq ExpensesSectionName}">
														<li class="dd-item"
															data-id="${ generalHead.generalHeadId }"
															data-orderId="${generalHead.orderId}">
															<div class="dd-handle">${
																generalHead.generalHeadName }</div>
														</li>
													</c:if>
												</c:forEach>
											</ol>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 widget-container-col"
							id="widget-container-col-9">
							<div class="widget-box widget-color-pink" id="widget-box-9">
								<div class="widget-header">
									<h5 class="widget-title">INCOME</h5>
									<div class="widget-toolbar no-border">
										<label> <input type="checkbox" id="inSectionId"
											class="ace ace-switch ace-switch-3 drag-switch" /> <span
											class="lbl middle"></span>
										</label>
									</div>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div class="dd" id="nestable">
											<ol id="inGeneralHeadList" class="dd-list custom-nodrag">
												<c:forEach items="${incomeAndExpenseGeneralHeadList}"
													var="generalHead">
													<c:set var="IncomeSectionName"
														value="<%=SectionEnum.INCOME.value()%>" />
													<c:if
														test="${generalHead.sectionName eq IncomeSectionName}">
														<li class="dd-item"
															data-id="${ generalHead.generalHeadId }"
															data-orderId="${generalHead.orderId}">
															<div class="dd-handle">${
																generalHead.generalHeadName }</div>
														</li>
													</c:if>
												</c:forEach>
											</ol>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<h3 class="header smaller lighter green">Maintenance
			Configuration</h3>
		<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-1 control-label no-padding-right"
							for="form-field-1">Start Date</label>
						<div class="col-sm-4">
							<input id="configStartDate" class="col-xs-10 col-sm-5 date-picker" type="text" data-date-format="yyyy-mm-dd" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label no-padding-right"
							for="form-field-1">End Date</label>
						<div class="col-sm-4">
							<input id="configEndDate" class="col-xs-10 col-sm-5 date-picker" type="text" data-date-format="yyyy-mm-dd" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label no-padding-right"
							for="form-field-1"> Maintenance Cycle </label>
						<div class="col-sm-4">
							<select id="configMaintenanceDate" class="col-xs-10 col-sm-5" name="maintenanceCycle">
								<option value="0">--Select--</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="6">6</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label no-padding-right"
							for="form-field-1">Maintenance Payment Interest Rate</label>
						<div class="col-sm-4">
							<input id="maintenancePaymentInterestRate" class="col-xs-10 col-sm-5" type="text"  />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label no-padding-right"
							for="form-field-1">Maintenance Payment Cheque Name</label>
						<div class="col-sm-4">
							<input id="maintenancePaymentChequeName" class="col-xs-10 col-sm-5" type="text" />
						</div>
					</div>
				</form>
			</div>
		</div>

		<form:form id="inserForm" commandName="societyConfigDomain"
			class="form-horizontal" action="societyConfig">

			<form:hidden path="configId" />
			<form:hidden path="liabilitesGeneralHeadIds" />
			<form:hidden path="liabilitesOrderIds" />
			<form:hidden path="assetGeneralHeadIds" />
			<form:hidden path="assetOrderIds" />
			<form:hidden path="expenseGeneralHeadIds" />
			<form:hidden path="expenseOrderIds" />
			<form:hidden path="incomeGeneralHeadIds" />
			<form:hidden path="incomeOrderIds" />
			<form:hidden path="startDate" />
			<form:hidden path="endDate" />
			<form:hidden path="maintenanceCycle" />
			<form:hidden path="maintenancePaymentInterestRate" />
			<form:hidden path="maintenancePaymentChequeName" />

			<div class="clearfix form-actions">
				<div class="col-sm-offset-3">
					<button id="societyConfigSubmitBtn" type="submit"
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
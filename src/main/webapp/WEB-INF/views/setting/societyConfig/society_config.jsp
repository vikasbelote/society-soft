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
		<c:if test="${not empty balanceSheetGeneralHeadList}">
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
														<c:if
															test="${generalHead.sectionName eq AssetSectionName}">
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
		</c:if>
		<c:if test="${not empty incomeAndExpenseGeneralHeadList}">
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
		</c:if>
		<h3 class="header smaller lighter green">Maintenance
			Configuration</h3>
		<div class="row">
			<div class="col-xs-12">

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

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">Start Date</label>
						<div class="col-sm-6">
							<form:input path="startDate" cssClass="col-xs-10 col-sm-5 date-picker" data-date-format="yyyy-mm-dd" />	
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">End Date</label>
						<div class="col-sm-6">
							<form:input path="endDate" cssClass="col-xs-10 col-sm-5 date-picker" data-date-format="yyyy-mm-dd" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1"> Maintenance Cycle </label>
						<div class="col-sm-6">
							<form:select path="maintenanceCycle">
								<form:option value="0">Choose payment cycle</form:option>
								<form:option value="1">1</form:option>
								<form:option value="2">2</form:option>
								<form:option value="3">3</form:option>
								<form:option value="4">4</form:option>
								<form:option value="6">6</form:option>
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">Payment Interest Rate</label>
						<div class="col-sm-6">
							<form:input path="maintenancePaymentInterestRate" cssClass="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">Payment Cheque Name</label>
						<div class="col-sm-6">
							<form:input path="maintenancePaymentChequeName" cssClass="col-xs-10 col-sm-5" />
						</div>
					</div>

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
	</div>
</div>
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
				<div class="invisible" id="main-widget-container">
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
														<li class="dd-item" data-id="${ generalHead.generalHeadId }" data-orderId="${generalHead.orderId}">
															<div class="dd-handle">${ generalHead.generalHeadName }</div>
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
														<li class="dd-item" data-id="${ generalHead.generalHeadId }" data-orderId="${generalHead.orderId}">
															<div class="dd-handle">${ generalHead.generalHeadName }</div>
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
			<div class="col-sm-6">
				<div class="dd" id="nestable">
					<ol class="dd-list">
						<li class="dd-item" data-id="1">
							<div class="dd-handle">Item 1</div>
						</li>
						<li class="dd-item" data-id="11">
							<div class="dd-handle">Item 11</div>
						</li>

						<li class="dd-item" data-id="12">
							<div class="dd-handle">Item 12</div>
						</li>
					</ol>
				</div>
			</div>
		</div>

		<form:form id="inserForm" commandName="societyConfigDomain"
			class="form-horizontal" action="societyConfig">
			
			<form:hidden path="configId" />
			<form:hidden path="liabilitesGeneralHeadIds" />
			<form:hidden path="liabilitesOrderIds" />
			<form:hidden path="assetGeneralHeadIds" />
			<form:hidden path="assetOrderIds" />

			<div class="clearfix form-actions">
				<div class="col-sm-offset-3">
					<button id="societyConfigSubmitBtn" type="submit" class="btn btn-success">
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
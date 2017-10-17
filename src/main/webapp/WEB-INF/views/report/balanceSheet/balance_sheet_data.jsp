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
		<div class="row">
			<h1 class="center">Balance Sheet As on ${balanceSheetData.asOnDate }</h1>
			
			<c:if test="${not empty balanceSheetData.leftSection}">
				<h3 class="header smaller lighter green">Capital & Liabilities</h3>
				<div class="col-xs-12">
					<table id="simple-table" class="table  table-bordered table-hover">
						<thead>
							<tr>
								<th class="col-xs-2">F.Y. ${ balanceSheetData.leftSection.prevYear}</th>
								<th class="col-xs-8">LIABILITIES</th>
								<th class="col-xs-2" colspan="2">F.Y. ${ balanceSheetData.leftSection.currentYear}</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ balanceSheetData.leftSection.generalHeadList }" var="generalHead">
								<tr>	
									<td></td>
									<td><strong style="text-decoration: underline;">${ generalHead.generalHeadName }</strong></td>
									<td></td>
									<td></td>
								</tr>
								<c:set var="isFirstRow" value="true" />
								<c:forEach items="${ generalHead.transactionList }" var="transaction">
									<c:choose>
										<c:when test="${isFirstRow eq 'true'}">
											<c:set var="isFirstRow" value="false" />
											<tr>
												<td>${transaction.lastYearAmount}</td>
												<td>${transaction.description}</td>
												<td>${transaction.currentYearAmount}</td>
												<td>${generalHead.totalCurrentYearGeneralHeadAmount }</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
												<td>${transaction.lastYearAmount}</td>
												<td>${transaction.description}</td>
												<td>${transaction.currentYearAmount}</td>
												<td></td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<tr>
									<td colspan="4"></td>
								</tr>
							</c:forEach>
							<tr>
								<td>${ balanceSheetData.leftSection.grossTotalPrevYear }</td>
								<td colspan="2"><strong>Gross Total ....</strong></td>
								<td>${ balanceSheetData.leftSection.grossTotalCurrentYear }</td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:if>

			<c:if test="${not empty balanceSheetData.rightSection}">
				<h3 class="header smaller lighter green">Assets</h3>
				<div class="col-xs-12">
					<table id="simple-table" class="table  table-bordered table-hover">
						<thead>
							<tr>
								<th class="col-xs-2">F.Y. ${ balanceSheetData.rightSection.prevYear}</th>
								<th class="col-xs-8">ASSETS</th>
								<th class="col-xs-2" colspan="2">F.Y. ${ balanceSheetData.rightSection.currentYear}</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ balanceSheetData.rightSection.generalHeadList }" var="generalHead">
								<tr>	
									<td></td>
									<td><strong style="text-decoration: underline;">${ generalHead.generalHeadName }</strong></td>
									<td></td>
									<td></td>
								</tr>
								<c:set var="isFirstRow" value="true" />
								<c:forEach items="${ generalHead.transactionList }" var="transaction">
									<c:choose>
										<c:when test="${isFirstRow eq 'true'}">
											<c:set var="isFirstRow" value="false" />
											<tr>
												<td>${transaction.lastYearAmount}</td>
												<td>${transaction.description}</td>
												<td>${transaction.currentYearAmount}</td>
												<td>${generalHead.totalCurrentYearGeneralHeadAmount }</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
												<td>${transaction.lastYearAmount}</td>
												<td>${transaction.description}</td>
												<td>${transaction.currentYearAmount}</td>
												<td></td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<tr>
									<td colspan="4"></td>
								</tr>
							</c:forEach>
							<tr>
								<td>${ balanceSheetData.rightSection.grossTotalPrevYear }</td>
								<td colspan="2"><strong>Gross Total ....</strong></td>
								<td>${ balanceSheetData.rightSection.grossTotalCurrentYear }</td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:if>
			<!-- /.span -->
		</div>
		<!-- /.row -->
	</div>
</div>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Income And Expense <small> <i
			class="ace-icon fa fa-angle-double-right"></i> This report display
			income and expense report of society account
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<h1 class="center">Income and Expense report As on ${incomeAndExpenseData.asOnDate }</h1>
			
			<c:if test="${not empty incomeAndExpenseData.leftSection}">
				<h3 class="header smaller lighter green">Expenses</h3>
				<div class="col-xs-12">
					<table id="simple-table" class="table  table-bordered table-hover">
						<thead>
							<tr>
								<th class="col-xs-2">F.Y. ${ incomeAndExpenseData.leftSection.prevYear}</th>
								<th class="col-xs-8">EXPENSES</th>
								<th class="col-xs-2" colspan="2">F.Y. ${ incomeAndExpenseData.leftSection.currentYear}</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ incomeAndExpenseData.leftSection.generalHeadList }" var="generalHead">
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
								<td>${ incomeAndExpenseData.leftSection.grossTotalPrevYear }</td>
								<td colspan="2"><strong>Gross Total ....</strong></td>
								<td>${ incomeAndExpenseData.leftSection.grossTotalCurrentYear }</td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:if>

			<c:if test="${not empty incomeAndExpenseData.rightSection}">
				<h3 class="header smaller lighter green">Income</h3>
				<div class="col-xs-12">
					<table id="simple-table" class="table  table-bordered table-hover">
						<thead>
							<tr>
								<th class="col-xs-2">F.Y. ${ incomeAndExpenseData.rightSection.prevYear}</th>
								<th class="col-xs-8">INCOME</th>
								<th class="col-xs-2" colspan="2">F.Y. ${ incomeAndExpenseData.rightSection.currentYear}</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ incomeAndExpenseData.rightSection.generalHeadList }" var="generalHead">
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
								<td>${ incomeAndExpenseData.rightSection.grossTotalPrevYear }</td>
								<td colspan="2"><strong>Gross Total ....</strong></td>
								<td>${ incomeAndExpenseData.rightSection.grossTotalCurrentYear }</td>
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
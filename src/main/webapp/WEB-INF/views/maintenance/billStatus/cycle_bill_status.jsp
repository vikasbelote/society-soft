<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		View Maintenance Bill Report <small> <i
			class="ace-icon fa fa-angle-double-right"></i> View bill status as
			per maintenance cycle
		</small>
	</h1>
</div>
<!-- /.page-header -->
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		
		<div class="col-sm-12" style="padding-top: 12px; background-color: #EFF3F8;">
			<div class="col-sm-12">
				<label><strong>Payment Due Date</strong> : ${cycle.paymentDueDate}
				</label>
			</div> 
			<div class="col-sm-12">
				<label><strong>Payment Cycle</strong> : For the period ${cycle.startDate} to ${cycle.endDate}
				</label>
			</div>
		</div>
		<div class="col-sm-12" style="padding-top: 12px; padding-bottom: 12px; background-color: #EFF3F8;">
			<div class="col-sm-1">
				<button id="saveBillStatusId" type="button" class="btn btn-success btn-sm pull-right">
							<span class="ace-icon fa fa-floppy-o icon-on-right bigger-110"></span>
							Save
				</button>
			</div>
			<div class="col-sm-3 no-padding-left pull-left">
				<div class="input-group">
					<span class="input-group-addon"> <i
						class="ace-icon fa fa-check"></i>
					</span> <input id="searchMemberTextBoxId" type="text" class="form-control search-query"
						placeholder="Type your member name"> <span
						class="input-group-btn">
						<button id="searchMemberId" type="button" class="btn btn-purple btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
							Search
						</button>
					</span>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="checkbox">
					<label>
						<input name="form-field-checkbox" type="checkbox" class="ace" id="markAllMemberId" />
						<span class="lbl"> Mark all Member</span>
					</label>
				</div>
			</div>
		</div>
		<c:choose>
			<c:when test="${not empty cycle.receipts}">
				<table id="billStatusTableId"
					class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Paid</strong></th>
							<th><strong>Member</strong></th>
							<th><strong>Flat Number</strong></th>
							<th><strong>Bill Number</strong></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cycle.receipts}" var="receipt">
							<tr>
								<td><c:choose>
										<c:when test="${receipt.billStatus}">
											<label> <input name="receiptIds" data-receiptId="${receipt.receiptId}"
												class="ace ace-switch ace-switch-5" type="checkbox"
												checked="checked" /> <span
												class="lbl"></span>
											</label>
										</c:when>
										<c:otherwise> 
											<label> <input name="receiptIds" data-receiptId="${receipt.receiptId}"
												class="ace ace-switch ace-switch-5" type="checkbox" /> <span
												class="lbl"></span>
											</label>
										</c:otherwise>
									</c:choose></td>
								<td>${receipt.memberName}</td>
								<td>${receipt.flatNumber}</td>
								<td>${receipt.billNumber}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<div style="text-align: center;">
					<h2>There is no bill generated for this cycle.</h2>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
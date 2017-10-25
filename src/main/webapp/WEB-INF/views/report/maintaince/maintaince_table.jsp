<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Member Maintenance Table <small> <i
			class="ace-icon fa fa-angle-double-right"></i> This table display
			maintenance of every society member
		</small>
	</h1>
</div>
<div class="row">
	<div id="table-data" class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<a id="generateReceiptId" href="#" class="btn btn-primary">Generate
			Receipt</a>
		<div class="space-6"></div>
		<c:choose>
			<c:when test="${not empty maintenanceTable}">
				<table id="simple-table" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Memeber Name</strong></th>
							<c:forEach items="${maintenanceTable.columnList}"
								var="generalHead">
								<th>${generalHead.generalHeadName}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${maintenanceTable.memberList}" var="member">
							<tr>
								<td>${member.name}</td>
								<c:forEach items="${member.generalHeadValues}" var="chargeValue">
									<td contenteditable="true">${chargeValue}</td>
								</c:forEach>
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
	<div class="col-xs-12" id="content">
		<div>
			<p style="font-size: 18px; margin-left:150px;">
				CRYSTAL TOWER CO-OPERTAIVE HOUSING SOCIETY LTD
			</p>
			<p style="margin-left:160px;">
				(REG NO N.B.O.M/CIDCO/HSG(T.C)/6337/ITR/2015-16,DT-02/02/2016)
			</p>
			<p style="margin-left:170px;">
				Add plot no : 23, sector-22, kamothe, navi-mumbau-410209
			</p>
		</div>
		<div>
			<label style="font-size: 16px;">
				Bill No : Q3-054
			</label>
		</div>
		<div>
			<label style="font-size: 16px;">Flat No : 1001</label>
		</div>
		<div>
			<label style="font-size: 16px;">Shri : Vikas Bhaskar Belote</label>
		</div>
		<div>
			<label>For the period from Oct 17 to Dec 17</label>
		</div>
		<table id="simple-table-1" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>Sr No</th>
					<th>Particulars</th>
					<th>Amount</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Maintenance Charges</td>
					<td>5700</td>
				</tr>
				<tr>
					<td>2</td>
					<td>Sinking Fund</td>
					<td>342</td>
				</tr>
				<tr>
					<td>3</td>
					<td>Repair Fund</td>
					<td>1023</td>
				</tr>
				<tr>
					<td>4</td>
					<td>Culture Program Fund</td>
					<td></td>
				</tr>
				<tr>
					<td>5</td>
					<td>Maintenance Charges</td>
					<td>5700</td>
				</tr>
				<tr>
					<td>6</td>
					<td>Sinking Fund</td>
					<td>342</td>
				</tr>
				<tr>
					<td>7</td>
					<td>Repair Fund</td>
					<td>1023</td>
				</tr>
				<tr>
					<td>8</td>
					<td>Culture Program Fund</td>
					<td></td>
				</tr>
				<tr>
					<td>9</td>
					<td>Maintenance Charges</td>
					<td>5700</td>
				</tr>
				<tr>
					<td>10</td>
					<td>Sinking Fund</td>
					<td>342</td>
				</tr>
				<tr>
					<td>11</td>
					<td>Repair Fund</td>
					<td>1023</td>
				</tr>
				<tr>
					<td>12</td>
					<td>Culture Program Fund</td>
					<td></td>
				</tr>
				<tr>
					<td>13</td>
					<td>Maintenance Charges</td>
					<td>5700</td>
				</tr>
				<tr>
					<td>14</td>
					<td>Sinking Fund</td>
					<td>342</td>
				</tr>
				<tr>
					<td>15</td>
					<td>Repair Fund</td>
					<td>1023</td>
				</tr>
				<tr>
					<td>16</td>
					<td>Culture Program Fund</td>
					<td></td>
				</tr>
				<tr>
					<td>17</td>
					<td>Sinking Fund</td>
					<td>342</td>
				</tr>
				<tr>
					<td>18</td>
					<td>Repair Fund</td>
					<td>1023</td>
				</tr>
				<tr>
					<td>19</td>
					<td>Culture Program Fund</td>
					<td></td>
				</tr>
				<tr>
					<td>20</td>
					<td>Sinking Fund</td>
					<td>342</td>
				</tr>
				<tr>
					<td>21</td>
					<td>Repair Fund</td>
					<td>1023</td>
				</tr>
				<tr>
					<td>22</td>
					<td>Culture Program Fund</td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td>Toatl Amount Payable</td>
					<td>6795</td>
				</tr>
			</tbody>
		</table>
		
		<p style="font-size: 16px; text-align:justify;">
			If the payment is not received on and before due date, Interest @21% p.a. on entire amount wil be applicable.I am able to solve any problem but thing is that we have differne solution
		</p>
		<p style="font-size: 16px;">
			cheque should be drawan in the favour of "<strong>CRYSTAL TOWER CHS LTD</strong>"
		</p>
		<p>Note:</p>
	    <div style="margin-left:20px">
	      <ol>
	        <li>Current bill as per resolution passed in AGM dt. 24.06.2017</li>
	        <li>Members who have paid contribution towards cultural fund has been adjusted against Maintenance charges in this bill</li>
	      </ol>
	    </div>
		<!-- ADD_PAGE -->
		<table id="simple-table-2" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>Sr No</th>
					<th>Particulars</th>
					<th>Amount</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Maintenance Charges</td>
					<td>5700</td>
				</tr>
				<tr>
					<td>2</td>
					<td>Sinking Fund</td>
					<td>342</td>
				</tr>
				<tr>
					<td>3</td>
					<td>Repair Fund</td>
					<td>1023</td>
				</tr>
				<tr>
					<td>4</td>
					<td>Culture Program Fund</td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td>Toatl Amount Payable</td>
					<td>6795</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>


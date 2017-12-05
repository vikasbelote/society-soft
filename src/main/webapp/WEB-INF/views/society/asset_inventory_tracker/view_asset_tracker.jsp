<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		View Asset <small> <i
			class="ace-icon fa fa-angle-double-right"></i> This page is used to
			track Asset & inventory
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<c:if test="${not empty deleted}">

			<c:choose>
				<c:when test="${deleted}">
					<div class="alert alert-block alert-success">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>

						<p>
							<strong> <i class="ace-icon fa fa-check"></i> Well done!
							</strong> You successfully delete the asset details.
						</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>
						<strong> <i class="ace-icon fa fa-times"></i> Oh snap!
						</strong> There is problem with deleting asset details. <br />
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<c:if test="${not empty update}">
			<c:choose>
				<c:when test="${update}">
					<div class="alert alert-block alert-success">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>

						<p>
							<strong> <i class="ace-icon fa fa-check"></i> Well done!
							</strong> You successfully update asset information.
						</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>
						<strong> <i class="ace-icon fa fa-times"></i> Oh snap!
						</strong> There is problem with updating asset information. <br />
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<c:choose>
			<c:when test="${not empty assetList}">
				<table id="maintenanceCycleTableId"
					class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><strong>Asset Name</strong></th>
							<th><strong>Tag Number</strong></th>
							<th><strong>Vendor Name</strong></th>
							<th><strong>Purchase Date</strong></th>
							<th><strong>Asset Cost</strong></th>
							<th><strong>Asset Location</strong></th>
							<th><strong>Status</strong></th>
							<th></th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${assetList}" var="asset">
							<tr>
								<td>${asset.assetName}</td>
								<td>${asset.assetTagNumber}</td>
								<td>${asset.vendorName}</td>
								<td>${asset.purchaseDate}</td>
								<td>${asset.assetCost}</td>
								<td>${asset.assetLocation}</td>
								<td>${asset.assetStatus}</td>
								<td>
									<a class="btn btn-xs btn-success" href="editAssetDetail?id=${asset.assetId}" data-rel="tooltip" title="View Asset Details"> 
										<i class="ace-icon fa fa-pencil bigger-120"></i>
									</a>
									<button class="btn btn-xs btn-danger deleteAsset"
											data-assetId="${asset.assetId}">
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
					<h2>There is no single asset entry recorded.</h2>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<form:form id="some-form" commandName="adminAssetTrackerDomain" method="post"
					class="form-horizontal" action="deleteAssetDetail" cssClass="hide">
					
	<form:hidden path="assetId"/>
	<button id="deleteAssetBtn" type="submit">Submit</button>
</form:form>

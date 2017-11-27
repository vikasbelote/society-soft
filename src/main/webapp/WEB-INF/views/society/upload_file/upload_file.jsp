<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-header">
	<h1>
		Upload File <small> <i
			class="ace-icon fa fa-angle-double-right"></i> upload file
		</small>
	</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		
		
		<c:if test="${not empty uploadSuccess}">
			<c:choose>
				<c:when test="${uploadSuccess}">
					<div class="alert alert-block alert-success">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>

						<p>
							<strong> <i class="ace-icon fa fa-check"></i> Well done!
							</strong> You successfully upload file.
						</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>
						<strong> <i class="ace-icon fa fa-times"></i> Oh snap!
						</strong> There is problem with uploading file. <br />
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<c:if test="${not empty deleteSuccess}">
			<c:choose>
				<c:when test="${deleteSuccess}">
					<div class="alert alert-block alert-success">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>

						<p>
							<strong> <i class="ace-icon fa fa-check"></i> Well done!
							</strong> You successfully delete file.
						</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger">
						<button type="button" class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>
						<strong> <i class="ace-icon fa fa-times"></i> Oh snap!
						</strong> There is problem with deleting file. <br />
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		
		<div class="row">
			<div class="col-sm-12">
				<form:form id="some-form" commandName="adminUploadDomain"
					class="form-horizontal" action="uploadFile" enctype="multipart/form-data">

					
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Select File </label>

						<div class="col-sm-4">
							<input type="file" id="id-input-file-2" name="uploadFile"
								class="col-xs-10 col-sm-5" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> File Type </label>

						<div class="col-sm-4">
							<select name="fileType" class="col-xs-10 col-sm-12">
								<option value="">Choose file type</option>
								<option value="COMMFILE">Common File</option>
								<option value="FLFILE">Flat Specific File</option>
								<option value="RPT">Report File</option>
							</select>
						</div>
					</div>
					<div>
						<div class="col-sm-offset-4">
							<button id="uploadFileId" type="submit"
								class="btn btn-success">
								<i class="ace-icon fa fa-upload bigger-110"></i>Upload
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div class="space-10"></div>
		
		<div class="row">
			<div class="col-sm-12">
				<!-- #section:elements.tab.option -->
				<div class="tabbable">
					<ul class="nav nav-tabs padding-12 tab-color-blue background-blue"
						id="myTab4">
						
							<li class="active"><a data-toggle="tab" href="#commonFile">Common
								File</a></li>
								
							<li><a data-toggle="tab" href="#flatSpecificFile">Flat Specific
							File</a></li>
							
							<li><a data-toggle="tab" href="#reportFile">Report</a></li>
						
					</ul>

					<div class="tab-content">
						<div id="commonFile" class="tab-pane in active">
							<c:choose>
								<c:when test="${not empty commonFileList}">
									<table id="commonFilTableId"
										class="table table-bordered table-hover">
										<thead>
											<tr>
												<th><strong>File</strong></th>
												<th><strong>Uploaded On</strong></th>
												<th><strong>Uploaded By</strong></th>
												<th></th>
												
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${commonFileList}" var="uploadFile">
												<tr>
													<td>${uploadFile.fileName}</td>
													<td>${uploadFile.uploadedDate}</td>
													<td>${uploadFile.userName}</td>
													<td>
														<a href="viewUploadFile?id=${uploadFile.fileId}" class="btn btn-xs btn-purple" data-rel="tooltip" title="View File">
															<i class="ace-icon fa fa-cloud-download bigger-120"></i>
														</a>
														<a class="btn btn-xs btn-danger" href="deleteUploadFile?id=${uploadFile.fileId}" data-rel="tooltip" title="Delete Upload Details">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:when>
								<c:otherwise>
									<div style="text-align: center;">
										<h2>There is no single common file uploaded.</h2>
									</div>
								</c:otherwise>
							</c:choose>


						</div>

						<div id="flatSpecificFile" class="tab-pane">
							<c:choose>
								<c:when test="${not empty flatSpecificFileList}">
									<table id="maintenanceCycleTableId"
										class="table table-bordered table-hover">
										<thead>
											<tr>
												<th><strong>File</strong></th>
												<th><strong>Uploaded On</strong></th>
												<th><strong>Uploaded By</strong></th>
												<th></th>
												
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${flatSpecificFileList}" var="uploadFile">
												<tr>
													<td>${uploadFile.fileName}</td>
													<td>${uploadFile.uploadedDate}</td>
													<td>${uploadFile.userName}</td>
													<td>
														<a href="viewUploadFile?id=${uploadFile.fileId}&type=${uploadFile.fileType}" class="btn btn-xs btn-purple" data-rel="tooltip" title="View File">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:when>
								<c:otherwise>
									<div style="text-align: center;">
										<h2>There is no single flat specific file uploaded.</h2>
									</div>
								</c:otherwise>
							</c:choose>
							
							
						</div>

						<div id="reportFile" class="tab-pane">
							<c:choose>
								<c:when test="${not empty reportFileList}">
									<table id="maintenanceCycleTableId"
										class="table table-bordered table-hover">
										<thead>
											<tr>
												<th><strong>File</strong></th>
												<th><strong>Uploaded On</strong></th>
												<th><strong>Uploaded By</strong></th>
												<th></th>
												
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${reportFileList}" var="uploadFile">
												<tr>
													<td>${uploadFile.fileName}</td>
													<td>${uploadFile.uploadedDate}</td>
													<td>${uploadFile.userName}</td>
													<td>
														<a href="viewUploadFile?id=${uploadFile.fileId}&type=${uploadFile.fileType}" class="btn btn-xs btn-purple" data-rel="tooltip" title="View File">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:when>
								<c:otherwise>
									<div style="text-align: center;">
										<h2>There is no single report file uploaded.</h2>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
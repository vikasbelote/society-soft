	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en">

<!-- Header -->
<tiles:insertAttribute name="header" />

<body class="no-skin">
	<!-- #section:basics/navbar.layout -->
	<tiles:insertAttribute name="navbar" />

	<!-- /section:basics/navbar.layout -->
	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.loadState('main-container')
			} catch (e) {
			}
		</script>

		<!-- #section:basics/sidebar -->
		<tiles:insertAttribute name="sidebar" />
		<!-- /section:basics/sidebar -->
		
		<!-- /.main-content -->
		<div class="main-content">
			<div class="main-content-inner">
				<tiles:insertAttribute name="breadcrumbs" />
				<div class="page-content">
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
		
		<!-- /.footer -->
		<tiles:insertAttribute name="footer" />
	</div>
	<!-- /.main-container -->
	
	<!-- basic script -->
	<script src="./assets/js/jquery.js"></script>
	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document.write("<script src='./assets/js/jquery.mobile.custom.js'>"
					+ "<"+"/script>");
	</script>
	<script src="./assets/js/bootstrap.js"></script>
	
	<!-- page specific plugin scripts -->
	<tiles:insertAttribute name="page-script" />
	
	<!-- ace scripts -->
	<tiles:insertAttribute name="script" />

	<!-- inline scripts related to this page -->
	<tiles:insertAttribute name="inline" />

</body>
</html>

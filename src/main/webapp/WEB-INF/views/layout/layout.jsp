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

	<!-- basic scripts -->
	<tiles:insertAttribute name="script" />

	<!-- inline scripts related to this page -->
	<tiles:insertAttribute name="inline" />

<!-- 	the following scripts are used in demo only for onpage help and you don't need them -->
<!-- <link rel="stylesheet" href="./assets/css/ace.onpage-help.css" />
	<link rel="stylesheet" href="./docs/assets/js/themes/sunburst.css" />

	<script type="text/javascript">
		ace.vars['base'] = '..';
	</script>
	<script src="./assets/js/ace/elements.onpage-help.js"></script>
	<script src="./assets/js/ace/ace.onpage-help.js"></script>
	<script src="./docs/assets/js/rainbow.js"></script>
	<script src="./docs/assets/js/language/generic.js"></script>
	<script src="./docs/assets/js/language/html.js"></script>
	<script src="./docs/assets/js/language/css.js"></script>
	<script src="./docs/assets/js/language/javascript.js"></script> -->
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li><i class="ace-icon fa fa-home home-icon"></i> <a href="home">Home</a>
		</li>
		<c:forEach items="${breadCrumbList}" var="breadCrumb">
			<c:choose>
				<c:when test="${breadCrumb.isLast}">
				    <li class="active">${breadCrumb.menuName}</li>
				</c:when>
				<c:otherwise>
    				<li><a href="#">${breadCrumb.menuName}</a></li>
  				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
	<!-- /.breadcrumb -->

	<!-- #section:basics/content.searchbox -->
	<div class="nav-search" id="nav-search">
		<form class="form-search">
			<span class="input-icon"> <input type="text"
				placeholder="Search ..." class="nav-search-input"
				id="nav-search-input" autocomplete="off" /> <i
				class="ace-icon fa fa-search nav-search-icon"></i>
			</span>
		</form>
	</div>
	<!-- /.nav-search -->

	<!-- /section:basics/content.searchbox -->
</div>
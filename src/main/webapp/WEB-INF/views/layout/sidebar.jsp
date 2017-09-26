<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="sidebar" class="sidebar responsive ace-save-state">
	<script type="text/javascript">
		try {
			ace.settings.loadState('sidebar')
		} catch (e) {
		}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="ace-icon fa fa-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="ace-icon fa fa-pencil"></i>
			</button>

			<!-- #section:basics/sidebar.layout.shortcuts -->
			<button class="btn btn-warning">
				<i class="ace-icon fa fa-users"></i>
			</button>

			<button class="btn btn-danger">
				<i class="ace-icon fa fa-cogs"></i>
			</button>

			<!-- /section:basics/sidebar.layout.shortcuts -->
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span>

			<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
		</div>
	</div>
	<!-- /.sidebar-shortcuts -->

	<ul class="nav nav-list">
		<li class=""><a href="home"> <i
				class="menu-icon fa fa-tachometer"></i> <span class="menu-text">
					Dashboard </span>
		</a> <b class="arrow"></b></li>
		<c:if test="${sessionScope.MENURIGHTS.isEnabledSocietyMenu()}">
			<li class=""><a href="#" class="dropdown-toggle"> <i
					class="menu-icon fa fa-desktop"></i> <span class="menu-text">
						Society </span> <b class="arrow fa fa-angle-down"></b>
			</a> <b class="arrow"></b>

				<ul class="submenu">

					<c:if test="${sessionScope.MENURIGHTS.isEnabledRegistrationMenu()}">
						<li class=""><a href="societyRegistration"> <i
								class="menu-icon fa fa-caret-right"></i> Registration
						</a> <b class="arrow"></b></li>
					</c:if>

					<c:if test="${sessionScope.MENURIGHTS.isEnabledCreateUserMenu()}">
						<li class=""><a href="createUser"> <i
								class="menu-icon fa fa-user"></i> Create User
						</a> <b class="arrow"></b></li>
					</c:if>
					
					<c:if test="${sessionScope.MENURIGHTS.isEnabledCreateUserMenu()}">
						<li class=""><a href="userList"> <i
								class="menu-icon fa fa-user"></i> User List
						</a> <b class="arrow"></b></li>
					</c:if>

				</ul></li>
		</c:if>

		<c:if test="${sessionScope.MENURIGHTS.isEnabledReminderMenu()}">
			<li class=""><a href="reminder"> <i
					class="menu-icon fa fa-envelope"></i> <span class="menu-text">
						Reminder </span>
			</a> <b class="arrow"></b></li>
		</c:if>
		<c:if test="${sessionScope.MENURIGHTS.isEnabledReportMenu()}">
			<li class=""><a href="#" class="dropdown-toggle"> <i
					class="menu-icon fa fa-file"></i> <span class="menu-text">
						Report </span> <b class="arrow fa fa-angle-down"></b>
			</a> <b class="arrow"></b>

				<ul class="submenu">

					<c:if test="${sessionScope.MENURIGHTS.isEnabledBlanceSheetMenu()}">
						<li class=""><a href="balanceSheet"> <i
								class="menu-icon fa fa-caret-right"></i> Balance Sheet
						</a> <b class="arrow"></b></li>
					</c:if>
					<c:if test="${sessionScope.MENURIGHTS.isEnabledIncomeAndExpenseMenu()}">
						<li class=""><a href="balanceSheet"> <i
								class="menu-icon fa fa-caret-right"></i> Income & Expense
						</a> <b class="arrow"></b></li>
					</c:if>
					<c:if test="${sessionScope.MENURIGHTS.isEnabledMaintainceReport()}">
						<li class=""><a href="balanceSheet"> <i
								class="menu-icon fa fa-caret-right"></i> Member Maintenance
						</a> <b class="arrow"></b></li>
					</c:if>

				</ul></li>
		</c:if>
	</ul>
	<!-- /.nav-list -->

	<!-- #section:basics/sidebar.layout.minimize -->
	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i id="sidebar-toggle-icon"
			class="ace-icon fa fa-angle-double-left ace-save-state"
			data-icon1="ace-icon fa fa-angle-double-left"
			data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>

	<!-- /section:basics/sidebar.layout.minimize -->
</div>
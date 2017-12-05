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
		<!-- Society -->
		<c:if test="${sessionScope.MENURIGHTS.isEnabledSocietyMenu()}">
			<li class=""><a href="#" class="dropdown-toggle"> <i
					class="menu-icon fa fa-building"></i> <span class="menu-text">
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

					<c:if test="${sessionScope.MENURIGHTS.isEnableAdminFourmMenu()}">
						<li class=""><a href="member"> <i
								class="menu-icon fa fa-user"></i> Forum
						</a> <b class="arrow"></b></li>
					</c:if>


					<c:if test="${sessionScope.MENURIGHTS.isEnableAdminFilesMenu()}">
						<li class=""><a href="uploadFile"> <i
								class="menu-icon fa fa-user"></i> Upload Files
						</a> <b class="arrow"></b></li>
					</c:if>

					<c:if
						test="${sessionScope.MENURIGHTS.isEnableHelpDeskTrackerMenu()}">
						<li class=""><a href="member"> <i
								class="menu-icon fa fa-user"></i> HelpDesk Tracker
						</a> <b class="arrow"></b></li>
					</c:if>

					<c:if test="${sessionScope.MENURIGHTS.isEnableAssetTrackerMenu()}">
						<li class=""><a href="#" class="dropdown-toggle"> <i
								class="menu-icon fa fa-user"></i> <span class="menu-text">Asset
									& Inventory Tracker</span> <b class="arrow fa fa-angle-down"></b>
						</a> <b class="arrow"></b>
							<ul class="submenu">
								<c:if
									test="${sessionScope.MENURIGHTS.isEnableAssetTrackerCreateMenu()}">
									<li class=""><a href="createAsset"> <i
											class="menu-icon fa fa-user"></i> Create
									</a> <b class="arrow"></b></li>
								</c:if>
								<c:if
									test="${sessionScope.MENURIGHTS.isEnableAssetTrackerViewMenu()}">
									<li class=""><a href="viewAsset"> <i
											class="menu-icon fa fa-user"></i> View
									</a> <b class="arrow"></b></li>
								</c:if>
							</ul></li>
					</c:if>

					<c:if
						test="${sessionScope.MENURIGHTS.isEnableRoomRentTrackerMenu()}">
						<li class=""><a href="member"> <i
								class="menu-icon fa fa-user"></i> Rent Room Tracker
						</a> <b class="arrow"></b></li>
					</c:if>

					<c:if test="${sessionScope.MENURIGHTS.isEnableStaffManagerMenu()}">
						<li class=""><a href="member"> <i
								class="menu-icon fa fa-user"></i> Staff Manager
						</a> <b class="arrow"></b></li>
					</c:if>


					<c:if test="${sessionScope.MENURIGHTS.isEnableParkingMangerMenu()}">
						<li class=""><a href="parkingManager"> <i
								class="menu-icon fa fa-user"></i> Parking Manager
						</a> <b class="arrow"></b></li>
					</c:if>

				</ul></li>
		</c:if>
		<!-- Flat -->
		<c:if test="${sessionScope.MENURIGHTS.isEnabledFlatMenu()}">
			<li class=""><a href="reminder"> <i
					class="menu-icon fa fa-home" style="color: #438eb9;"></i> <span
					class="menu-text"> Flat </span>
			</a> <b class="arrow"></b></li>
		</c:if>
		<!-- Communication -->
		<c:if test="${sessionScope.MENURIGHTS.isEnableCommunicationMenu()}">
			<li class=""><a href="#" class="dropdown-toggle"> <i
					class="menu-icon fa fa-comment" style="color: #87b87f;"></i> <span
					class="menu-text"> Communication </span> <b
					class="arrow fa fa-angle-down"></b>
			</a> <b class="arrow"></b>

				<ul class="submenu">

					<c:if test="${sessionScope.MENURIGHTS.isEnableConversationMenu()}">
						<li class=""><a href="member"> <i
								class="menu-icon fa fa-user"></i> Conversation
						</a> <b class="arrow"></b></li>
					</c:if>

					<c:if test="${sessionScope.MENURIGHTS.isEnableNoticeMenu()}">
						<li class=""><a href="member"> <i
								class="menu-icon fa fa-user"></i> Notices
						</a> <b class="arrow"></b></li>
					</c:if>

					<c:if test="${sessionScope.MENURIGHTS.isEnableIstanvtPoll()}">
						<li class=""><a href="member"> <i
								class="menu-icon fa fa-user"></i> Instant Poll
						</a> <b class="arrow"></b></li>
					</c:if>


				</ul></li>
		</c:if>
		<!-- Reminder -->
		<c:if test="${sessionScope.MENURIGHTS.isEnabledReminderMenu()}">
			<li class=""><a href="reminder"> <i
					class="menu-icon fa fa-envelope"></i> <span class="menu-text">
						Reminder </span>
			</a> <b class="arrow"></b></li>
		</c:if>

		<!-- Maintenance Bill -->
		<c:if test="${sessionScope.MENURIGHTS.isEnabledMaintainceReport()}">
			<li class=""><a href="#" class="dropdown-toggle"> <i
					class="menu-icon fa fa-inr"></i> Maintenance Bill
			</a> <b class="arrow"></b>

				<ul class="submenu">
					<c:if
						test="${sessionScope.MENURIGHTS.isEnableCreateMaintenacneReport()}">
						<li class=""><a href="maintaince"> <i
								class="menu-icon fa fa-caret-right"></i> Create
						</a> <b class="arrow"></b></li>
					</c:if>
					<c:if
						test="${sessionScope.MENURIGHTS.isEnableViewMaintenacneReport()}">
						<li class=""><a href="viewMaintenanceReport"> <i
								class="menu-icon fa fa-caret-right"></i> View
						</a> <b class="arrow"></b></li>
					</c:if>
					<c:if
						test="${sessionScope.MENURIGHTS.isEnableMaintenanceBillStatusMenu()}">
						<li class=""><a href="viewBillStatus"> <i
								class="menu-icon fa fa-caret-right"></i> Bill Status
						</a> <b class="arrow"></b></li>
					</c:if>
					<c:if
						test="${sessionScope.MENURIGHTS.isEnableMaintenanceHeadMenu()}">
						<li class=""><a href="maintenanceHead"> <i
								class="menu-icon fa fa-caret-right"></i> Head
						</a> <b class="arrow"></b></li>
					</c:if>
					<c:if
						test="${sessionScope.MENURIGHTS.isEnableMaintenanceInterestPolicyMenu()}">
						<li class=""><a href="viewMaintenanceReport"> <i
								class="menu-icon fa fa-caret-right"></i> Interest Policy
						</a> <b class="arrow"></b></li>
					</c:if>
					<c:if
						test="${sessionScope.MENURIGHTS.isEnableMaintenancePenalitiesPolicyMenu()}">
						<li class=""><a href="maintaince"> <i
								class="menu-icon fa fa-caret-right"></i> Penalty Policy
						</a> <b class="arrow"></b></li>
					</c:if>
					<c:if
						test="${sessionScope.MENURIGHTS.isEnableMaintenanceRebatePolicyMenu()}">
						<li class=""><a href="viewMaintenanceReport"> <i
								class="menu-icon fa fa-caret-right"></i> Rebate Policy
						</a> <b class="arrow"></b></li>
					</c:if>
					<c:if
						test="${sessionScope.MENURIGHTS.isEnableMaintenanceAdditionalAreaMenu()}">
						<li class=""><a href="viewMaintenanceReport"> <i
								class="menu-icon fa fa-caret-right"></i> Additional Area
						</a> <b class="arrow"></b></li>
					</c:if>


				</ul></li>
		</c:if>
		<!-- Report -->
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
					<c:if
						test="${sessionScope.MENURIGHTS.isEnabledIncomeAndExpenseMenu()}">
						<li class=""><a href="incomeAndExpense"> <i
								class="menu-icon fa fa-caret-right"></i> Income & Expense
						</a> <b class="arrow"></b></li>
					</c:if>

				</ul></li>
		</c:if>
		<!-- Bazzar -->
		<c:if test="${sessionScope.MENURIGHTS.isEnableBazzarMenu()}">
			<li class=""><a href="reminder"> <i
					class="menu-icon fa fa-shopping-bag"></i> <span class="menu-text">
						Bazzar </span>
			</a> <b class="arrow"></b></li>
		</c:if>
		<!-- Helpdesk -->
		<c:if test="${sessionScope.MENURIGHTS.isEnableHelpdekMenu()}">
			<li class=""><a href="reminder"> <i
					class="menu-icon fa fa-life-ring"></i> <span class="menu-text">
						Helpdesk </span>
			</a> <b class="arrow"></b></li>
		</c:if>
		<!-- Event -->
		<c:if test="${sessionScope.MENURIGHTS.isEnableEventMenu()}">
			<li class=""><a href="reminder"> <i
					class="menu-icon fa fa-industry"></i> <span class="menu-text">
						Event </span>
			</a> <b class="arrow"></b></li>
		</c:if>
		<!-- Facility Booking -->
		<c:if test="${sessionScope.MENURIGHTS.isEnableFacilityBookingMenu()}">
			<li class=""><a href="reminder"> <i
					class="menu-icon fa fa-envelope"></i> <span class="menu-text">
						Facility Booking </span>
			</a> <b class="arrow"></b></li>
		</c:if>
		<!-- Directory -->
		<c:if test="${sessionScope.MENURIGHTS.isEnableDirectoryMenu()}">
			<li class=""><a href="reminder"> <i
					class="menu-icon fa fa-folder"></i> <span class="menu-text">
						Directory </span>
			</a> <b class="arrow"></b></li>
		</c:if>

		<!-- Notification -->
		<c:if test="${sessionScope.MENURIGHTS.isEnableNotificationReport()}">
			<li class=""><a href="#" class="dropdown-toggle"> <i
					class="menu-icon fa fa-bell"></i> <span class="menu-text">
						Notification </span> <b class="arrow fa fa-angle-down"></b>
			</a> <b class="arrow"></b>

				<ul class="submenu">
					<c:if
						test="${sessionScope.MENURIGHTS.isEnableEmailNotificationReportMenu()}">
						<li class=""><a href="emailNotification"> <i
								class="menu-icon fa fa-caret-right"></i> Email
						</a> <b class="arrow"></b></li>
					</c:if>

					<c:if
						test="${sessionScope.MENURIGHTS.isEnableMessageNotificationReportMenu()}">
						<li class=""><a href="msgNotification"> <i
								class="menu-icon fa fa-caret-right"></i> Message
						</a> <b class="arrow"></b></li>
					</c:if>
				</ul></li>
		</c:if>
		<!-- Setting -->
		<c:if test="${sessionScope.MENURIGHTS.isEnabledSettingMenu()}">
			<li class=""><a href="#" class="dropdown-toggle"> <i
					class="menu-icon fa fa-cog"></i> <span class="menu-text">
						Setting </span> <b class="arrow fa fa-angle-down"></b>
			</a> <b class="arrow"></b>

				<ul class="submenu">

					<c:if test="${sessionScope.MENURIGHTS.isEnabledGeneralHeadMenu()}">
						<li class=""><a href="generalHead"> <i
								class="menu-icon fa fa-caret-right"></i> General Head
						</a> <b class="arrow"></b></li>
					</c:if>

					<c:if
						test="${sessionScope.MENURIGHTS.isEnabledReocrdTransactionMenu()}">
						<li class=""><a href="recordTransaction"> <i
								class="menu-icon fa fa-caret-right"></i> Record Transaction
						</a> <b class="arrow"></b></li>
					</c:if>

					<c:if
						test="${sessionScope.MENURIGHTS.isEnableSocietyConfigurationMenu()}">
						<li class=""><a href="societyConfig"> <i
								class="menu-icon fa fa-caret-right"></i> Society Configuration
						</a> <b class="arrow"></b></li>
					</c:if>
				</ul></li>
		</c:if>
		<!-- Master -->
		<c:if test="${sessionScope.MENURIGHTS.isEnableMasterMenu()}">
			<li class=""><a href="#" class="dropdown-toggle"> <i
					class="menu-icon fa fa-database" style="color: green"></i> Master
			</a> <b class="arrow"></b>

				<ul class="submenu">
					<c:if
						test="${sessionScope.MENURIGHTS.isEnableMasterAdditionalAreaMenu()}">
						<li class=""><a href="additionalAreaMaster"> <i
								class="menu-icon fa fa-caret-right"></i> Additional Area
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
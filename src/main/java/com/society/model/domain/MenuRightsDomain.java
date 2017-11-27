package com.society.model.domain;

import java.lang.reflect.Field;
import java.util.List;

public class MenuRightsDomain {
	//branch menu
	private boolean isEnabledSocietyMenu = false;
	
	private boolean isEnabledRegistrationMenu = false;

	// 1 Menu Id
	private boolean isEnabledCreateUserMenu = false;
	// 2 Menu Id
	private boolean isEnabledReminderMenu = false;
	//branch menu
	private boolean isEnabledReportMenu = false;
	// 3 Menu Id
	private boolean isEnabledBlanceSheetMenu = false;
	// 4 Menu Id
	private boolean isEnabledIncomeAndExpenseMenu = false;
	// 5 Menu Id
	private boolean isEnabledMaintainceReport = false;
	//6 Menu Id
	private boolean isEnabledUserListMenu = false;
	//branch menu
	private boolean isEnabledSettingMenu = false;
	//7 Menu Id
	private boolean isEnabledGeneralHeadMenu = false;
	//8 Menu Id
	private boolean isEnabledReocrdTransactionMenu = false;
	//9 Menu Id
	private boolean isEnableSocietyConfigurationMenu = false;
	//10 Menu Id
	private boolean isEnableCreateMaintenacneReport = false;
	//11 Menu Id
	private boolean isEnableViewMaintenacneReport = false;
	
	private boolean isEnableNotificationReport = false;
	//12 Menu Id
	private boolean isEnableEmailNotificationReportMenu = false;
	//13 Menu Id
	private boolean isEnableMessageNotificationReportMenu = false;
	
	//14 Menu Id
	private boolean isEnableMaintenanceHeadMenu = false;
	//15 Menu Id
	private boolean isEnableMaintenanceInterestPolicyMenu = false;
	//16 Menu Id
	private boolean isEnableMaintenancePenalitiesPolicyMenu = false;
	//17 Menu Id
	private boolean isEnableMaintenanceRebatePolicyMenu = false;
	//18 Menu Id
	private boolean isEnableMaintenanceAdditionalAreaMenu = false;
	
	//19 Menu Id
	private boolean isEnableSocietyMemberMenu = false;
	
	private boolean isEnableMasterMenu = false;
	//20 Menu Id
	private boolean isEnableMasterAdditionalAreaMenu = false;
	//21 Menu Id
	private boolean isEnableMaintenanceBillStatusMenu = false;
	//22 Menu Id
	private boolean isEnableAdminFourmMenu = false;
	//23 Menu Id
	private boolean isEnableAdminFilesMenu = false;
	//24 Menu Id
	private boolean isEnableHelpDeskTrackerMenu = false;
	
	private boolean isEnableAssetTrackerMenu = false;
	//25 Menu Id
	private boolean isEnableAssetTrackerCreateMenu = false;
	//25 Menu Id
	private boolean isEnableAssetTrackerViewMenu = false;
	//26 Menu Id
	private boolean isEnableMaintenanceContactMenu = false;
	//27 Menu Id
	private boolean isEnableRoomRentTrackerMenu = false;
	//28 Menu Id
	private boolean isEnableStaffManagerMenu = false;
	//29 Menu Id
	private boolean isEnableParkingMangerMenu = false;
	//30 Menu Id
	private boolean isEnabledFlatMenu = false;
	//31 Menu Id
	private boolean isEnableConversationMenu = false;
	//32 Menu Id
	private boolean isEnableNoticeMenu = false;
	//33 Menu Id
	private boolean isEnableIstanvtPoll = false;
	//34 Menu Id
	private boolean isEnableBazzarMenu = false;
	//35 Menu Id
	private boolean isEnableHelpdekMenu = false;
	//36 Menu Id
	private boolean isEnableEventMenu = false;
	//37 Menu Id
	private boolean isEnableFacilityBookingMenu = false;
	//38 Menu Id
	private boolean isEnableDirectoryMenu = false;

	private boolean isEnableCommunicationMenu = false;
	
	
	public void initMenuRights(LoginDomain loginDomain) {

		if (loginDomain.getRoleName().equals("Owner"))
			isEnabledRegistrationMenu = true;

		if (loginDomain.getRoleName().equals("Admin")) {
			
			Class<MenuRightsDomain>  menuRightsDomain = MenuRightsDomain.class;
			Field[] fields = menuRightsDomain.getDeclaredFields();
			for(Field field : fields) {
				try {
					field.set(this, true);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}

		if (loginDomain.getRoleName().equals("User")) {

			List<Integer> menuIdList = loginDomain.getMenuId();
			for (Integer menuId : menuIdList) {
				switch (menuId) {
				case 1: {
					isEnabledCreateUserMenu = true;
					break;
				}
				case 2: {
					isEnabledReminderMenu = true;
					break;
				}
				case 3: {
					isEnabledBlanceSheetMenu = true;
					break;
				}
				case 4: {
					isEnabledIncomeAndExpenseMenu = true;
					break;
				}
				case 6: {
					isEnabledUserListMenu = true;
					break;
				}
				case 7: {
					isEnabledGeneralHeadMenu = true;
					break;
				}
				case 8: {
					isEnabledReocrdTransactionMenu = true;
					break;
				}
				case 9: {
					isEnableSocietyConfigurationMenu = true;
					break;
				}
				case 10: {
					isEnableCreateMaintenacneReport = true;
					break;
				}
				case 11: {
					isEnableViewMaintenacneReport = true;
					break;
				}
				case 12: {
					isEnableEmailNotificationReportMenu = true;
					break;
				}
				case 13: {
					isEnableMessageNotificationReportMenu = true;
					break;
				}
				case 14: {
					isEnableMaintenanceHeadMenu = true;
					break;
				}
				case 15: {
					isEnableMaintenanceInterestPolicyMenu = true;
					break;
				}
				case 16: {
					isEnableMaintenancePenalitiesPolicyMenu = true;
					break;
				}
				case 17: {
					isEnableMaintenanceRebatePolicyMenu = true;
					break;
				}
				case 18: {
					isEnableMaintenanceAdditionalAreaMenu = true;
					break;
				}
				case 19: {
					isEnableSocietyMemberMenu = true;
					break;
				}
				case 20: {
					isEnableMasterAdditionalAreaMenu = true;
					break;
				}
				case 21: {
					isEnableMaintenanceBillStatusMenu = true;
					break;
				}
				}
			}
		}
		
		if(isEnableAssetTrackerViewMenu || isEnableAssetTrackerCreateMenu())
			isEnableAssetTrackerMenu = true;
		
		if(isEnableCreateMaintenacneReport || isEnableViewMaintenacneReport || isEnableMaintenanceHeadMenu || isEnableMaintenanceInterestPolicyMenu || 
				isEnableMaintenancePenalitiesPolicyMenu || isEnableMaintenanceRebatePolicyMenu || isEnableMaintenanceAdditionalAreaMenu || isEnableMaintenanceBillStatusMenu)
			isEnabledMaintainceReport = true;
		
		if(isEnabledRegistrationMenu || isEnabledCreateUserMenu || isEnabledUserListMenu || isEnableSocietyMemberMenu || isEnableAssetTrackerMenu || isEnableAdminFilesMenu)
			isEnabledSocietyMenu = true;
		
		if(isEnabledBlanceSheetMenu || isEnabledIncomeAndExpenseMenu)
			isEnabledReportMenu = true;
		
		if(isEnabledGeneralHeadMenu || isEnabledReocrdTransactionMenu || isEnableSocietyConfigurationMenu)
			isEnabledSettingMenu = true;
		
		if(isEnableEmailNotificationReportMenu || isEnableMessageNotificationReportMenu)
			isEnableNotificationReport = true;
		
		if(isEnableMasterAdditionalAreaMenu)
			isEnableMasterMenu = true;
	}

	public boolean isEnabledSocietyMenu() {
		return isEnabledSocietyMenu;
	}

	public void setEnabledSocietyMenu(boolean isEnabledSocietyMenu) {
		this.isEnabledSocietyMenu = isEnabledSocietyMenu;
	}

	public boolean isEnabledRegistrationMenu() {
		return isEnabledRegistrationMenu;
	}

	public void setEnabledRegistrationMenu(boolean isEnabledRegistrationMenu) {
		this.isEnabledRegistrationMenu = isEnabledRegistrationMenu;
	}

	public boolean isEnabledCreateUserMenu() {
		return isEnabledCreateUserMenu;
	}

	public void setEnabledCreateUserMenu(boolean isEnabledCreateUserMenu) {
		this.isEnabledCreateUserMenu = isEnabledCreateUserMenu;
	}

	public boolean isEnabledReminderMenu() {
		return isEnabledReminderMenu;
	}

	public void setEnabledReminderMenu(boolean isEnabledReminderMenu) {
		this.isEnabledReminderMenu = isEnabledReminderMenu;
	}

	public boolean isEnabledReportMenu() {
		return isEnabledReportMenu;
	}

	public void setEnabledReportMenu(boolean isEnabledReportMenu) {
		this.isEnabledReportMenu = isEnabledReportMenu;
	}

	public boolean isEnabledBlanceSheetMenu() {
		return isEnabledBlanceSheetMenu;
	}

	public void setEnabledBlanceSheetMenu(boolean isEnabledBlanceSheetMenu) {
		this.isEnabledBlanceSheetMenu = isEnabledBlanceSheetMenu;
	}

	public boolean isEnabledIncomeAndExpenseMenu() {
		return isEnabledIncomeAndExpenseMenu;
	}

	public void setEnabledIncomeAndExpenseMenu(boolean isEnabledIncomeAndExpenseMenu) {
		this.isEnabledIncomeAndExpenseMenu = isEnabledIncomeAndExpenseMenu;
	}

	public boolean isEnabledMaintainceReport() {
		return isEnabledMaintainceReport;
	}

	public void setEnabledMaintainceReport(boolean isEnabledMaintainceReport) {
		this.isEnabledMaintainceReport = isEnabledMaintainceReport;
	}

	public boolean isEnabledUserListMenu() {
		return isEnabledUserListMenu;
	}

	public void setEnabledUserListMenu(boolean isEnabledUserListMenu) {
		this.isEnabledUserListMenu = isEnabledUserListMenu;
	}

	public boolean isEnabledGeneralHeadMenu() {
		return isEnabledGeneralHeadMenu;
	}

	public void setEnabledGeneralHeadMenu(boolean isEnabledGeneralHeadMenu) {
		this.isEnabledGeneralHeadMenu = isEnabledGeneralHeadMenu;
	}

	public boolean isEnabledReocrdTransactionMenu() {
		return isEnabledReocrdTransactionMenu;
	}

	public void setEnabledReocrdTransactionMenu(
			boolean isEnabledReocrdTransactionMenu) {
		this.isEnabledReocrdTransactionMenu = isEnabledReocrdTransactionMenu;
	}

	public boolean isEnabledSettingMenu() {
		return isEnabledSettingMenu;
	}

	public void setEnabledSettingMenu(boolean isEnabledSettingMenu) {
		this.isEnabledSettingMenu = isEnabledSettingMenu;
	}

	public boolean isEnableSocietyConfigurationMenu() {
		return isEnableSocietyConfigurationMenu;
	}

	public void setEnableSocietyConfigurationMenu(boolean isEnableSocietyConfigurationMenu) {
		this.isEnableSocietyConfigurationMenu = isEnableSocietyConfigurationMenu;
	}

	public boolean isEnableCreateMaintenacneReport() {
		return isEnableCreateMaintenacneReport;
	}

	public void setEnableCreateMaintenacneReport(boolean isEnableCreateMaintenacneReport) {
		this.isEnableCreateMaintenacneReport = isEnableCreateMaintenacneReport;
	}

	public boolean isEnableViewMaintenacneReport() {
		return isEnableViewMaintenacneReport;
	}

	public void setEnableViewMaintenacneReport(boolean isEnableViewMaintenacneReport) {
		this.isEnableViewMaintenacneReport = isEnableViewMaintenacneReport;
	}

	public boolean isEnableNotificationReport() {
		return isEnableNotificationReport;
	}

	public void setEnableNotificationReport(boolean isEnableNotificationReport) {
		this.isEnableNotificationReport = isEnableNotificationReport;
	}

	public boolean isEnableEmailNotificationReportMenu() {
		return isEnableEmailNotificationReportMenu;
	}

	public void setEnableEmailNotificationReportMenu(boolean isEnableEmailNotificationReportMenu) {
		this.isEnableEmailNotificationReportMenu = isEnableEmailNotificationReportMenu;
	}

	public boolean isEnableMessageNotificationReportMenu() {
		return isEnableMessageNotificationReportMenu;
	}

	public void setEnableMessageNotificationReportMenu(boolean isEnableMessageNotificationReportMenu) {
		this.isEnableMessageNotificationReportMenu = isEnableMessageNotificationReportMenu;
	}

	public boolean isEnableMaintenanceHeadMenu() {
		return isEnableMaintenanceHeadMenu;
	}

	public void setEnableMaintenanceHeadMenu(boolean isEnableMaintenanceHeadMenu) {
		this.isEnableMaintenanceHeadMenu = isEnableMaintenanceHeadMenu;
	}

	public boolean isEnableMaintenanceInterestPolicyMenu() {
		return isEnableMaintenanceInterestPolicyMenu;
	}

	public void setEnableMaintenanceInterestPolicyMenu(boolean isEnableMaintenanceInterestPolicyMenu) {
		this.isEnableMaintenanceInterestPolicyMenu = isEnableMaintenanceInterestPolicyMenu;
	}

	public boolean isEnableMaintenancePenalitiesPolicyMenu() {
		return isEnableMaintenancePenalitiesPolicyMenu;
	}

	public void setEnableMaintenancePenalitiesPolicyMenu(boolean isEnableMaintenancePenalitiesPolicyMenu) {
		this.isEnableMaintenancePenalitiesPolicyMenu = isEnableMaintenancePenalitiesPolicyMenu;
	}

	public boolean isEnableMaintenanceRebatePolicyMenu() {
		return isEnableMaintenanceRebatePolicyMenu;
	}

	public void setEnableMaintenanceRebatePolicyMenu(boolean isEnableMaintenanceRebatePolicyMenu) {
		this.isEnableMaintenanceRebatePolicyMenu = isEnableMaintenanceRebatePolicyMenu;
	}

	public boolean isEnableMaintenanceAdditionalAreaMenu() {
		return isEnableMaintenanceAdditionalAreaMenu;
	}

	public void setEnableMaintenanceAdditionalAreaMenu(boolean isEnableMaintenanceAdditionalAreaMenu) {
		this.isEnableMaintenanceAdditionalAreaMenu = isEnableMaintenanceAdditionalAreaMenu;
	}

	public boolean isEnableSocietyMemberMenu() {
		return isEnableSocietyMemberMenu;
	}

	public void setEnableSocietyMemberMenu(boolean isEnableSocietyMemberMenu) {
		this.isEnableSocietyMemberMenu = isEnableSocietyMemberMenu;
	}

	public boolean isEnableMasterMenu() {
		return isEnableMasterMenu;
	}

	public void setEnableMasterMenu(boolean isEnableMasterMenu) {
		this.isEnableMasterMenu = isEnableMasterMenu;
	}

	public boolean isEnableMasterAdditionalAreaMenu() {
		return isEnableMasterAdditionalAreaMenu;
	}

	public void setEnableMasterAdditionalAreaMenu(boolean isEnableMasterAdditionalAreaMenu) {
		this.isEnableMasterAdditionalAreaMenu = isEnableMasterAdditionalAreaMenu;
	}

	public boolean isEnableMaintenanceBillStatusMenu() {
		return isEnableMaintenanceBillStatusMenu;
	}

	public void setEnableMaintenanceBillStatusMenu(boolean isEnableMaintenanceBillStatusMenu) {
		this.isEnableMaintenanceBillStatusMenu = isEnableMaintenanceBillStatusMenu;
	}

	public boolean isEnableAdminFourmMenu() {
		return isEnableAdminFourmMenu;
	}

	public void setEnableAdminFourmMenu(boolean isEnableAdminFourmMenu) {
		this.isEnableAdminFourmMenu = isEnableAdminFourmMenu;
	}

	public boolean isEnableAdminFilesMenu() {
		return isEnableAdminFilesMenu;
	}

	public void setEnableAdminFilesMenu(boolean isEnableAdminFilesMenu) {
		this.isEnableAdminFilesMenu = isEnableAdminFilesMenu;
	}

	public boolean isEnableHelpDeskTrackerMenu() {
		return isEnableHelpDeskTrackerMenu;
	}

	public void setEnableHelpDeskTrackerMenu(boolean isEnableHelpDeskTrackerMenu) {
		this.isEnableHelpDeskTrackerMenu = isEnableHelpDeskTrackerMenu;
	}

	public boolean isEnableAssetTrackerMenu() {
		return isEnableAssetTrackerMenu;
	}

	public void setEnableAssetTrackerMenu(boolean isEnableAssetTrackerMenu) {
		this.isEnableAssetTrackerMenu = isEnableAssetTrackerMenu;
	}

	public boolean isEnableMaintenanceContactMenu() {
		return isEnableMaintenanceContactMenu;
	}

	public void setEnableMaintenanceContactMenu(boolean isEnableMaintenanceContactMenu) {
		this.isEnableMaintenanceContactMenu = isEnableMaintenanceContactMenu;
	}

	public boolean isEnableRoomRentTrackerMenu() {
		return isEnableRoomRentTrackerMenu;
	}

	public void setEnableRoomRentTrackerMenu(boolean isEnableRoomRentTrackerMenu) {
		this.isEnableRoomRentTrackerMenu = isEnableRoomRentTrackerMenu;
	}

	public boolean isEnableStaffManagerMenu() {
		return isEnableStaffManagerMenu;
	}

	public void setEnableStaffManagerMenu(boolean isEnableStaffManagerMenu) {
		this.isEnableStaffManagerMenu = isEnableStaffManagerMenu;
	}

	public boolean isEnableParkingMangerMenu() {
		return isEnableParkingMangerMenu;
	}

	public void setEnableParkingMangerMenu(boolean isEnableParkingMangerMenu) {
		this.isEnableParkingMangerMenu = isEnableParkingMangerMenu;
	}

	public boolean isEnabledFlatMenu() {
		return isEnabledFlatMenu;
	}

	public void setEnabledFlatMenu(boolean isEnabledFlatMenu) {
		this.isEnabledFlatMenu = isEnabledFlatMenu;
	}

	public boolean isEnableConversationMenu() {
		return isEnableConversationMenu;
	}

	public void setEnableConversationMenu(boolean isEnableConversationMenu) {
		this.isEnableConversationMenu = isEnableConversationMenu;
	}

	public boolean isEnableNoticeMenu() {
		return isEnableNoticeMenu;
	}

	public void setEnableNoticeMenu(boolean isEnableNoticeMenu) {
		this.isEnableNoticeMenu = isEnableNoticeMenu;
	}

	public boolean isEnableIstanvtPoll() {
		return isEnableIstanvtPoll;
	}

	public void setEnableIstanvtPoll(boolean isEnableIstanvtPoll) {
		this.isEnableIstanvtPoll = isEnableIstanvtPoll;
	}

	public boolean isEnableBazzarMenu() {
		return isEnableBazzarMenu;
	}

	public void setEnableBazzarMenu(boolean isEnableBazzarMenu) {
		this.isEnableBazzarMenu = isEnableBazzarMenu;
	}

	public boolean isEnableHelpdekMenu() {
		return isEnableHelpdekMenu;
	}

	public void setEnableHelpdekMenu(boolean isEnableHelpdekMenu) {
		this.isEnableHelpdekMenu = isEnableHelpdekMenu;
	}

	public boolean isEnableEventMenu() {
		return isEnableEventMenu;
	}

	public void setEnableEventMenu(boolean isEnableEventMenu) {
		this.isEnableEventMenu = isEnableEventMenu;
	}

	public boolean isEnableFacilityBookingMenu() {
		return isEnableFacilityBookingMenu;
	}

	public void setEnableFacilityBookingMenu(boolean isEnableFacilityBookingMenu) {
		this.isEnableFacilityBookingMenu = isEnableFacilityBookingMenu;
	}

	public boolean isEnableDirectoryMenu() {
		return isEnableDirectoryMenu;
	}

	public void setEnableDirectoryMenu(boolean isEnableDirectoryMenu) {
		this.isEnableDirectoryMenu = isEnableDirectoryMenu;
	}

	public boolean isEnableCommunicationMenu() {
		return isEnableCommunicationMenu;
	}

	public void setEnableCommunicationMenu(boolean isEnableCommunicationMenu) {
		this.isEnableCommunicationMenu = isEnableCommunicationMenu;
	}

	public boolean isEnableAssetTrackerCreateMenu() {
		return isEnableAssetTrackerCreateMenu;
	}

	public void setEnableAssetTrackerCreateMenu(boolean isEnableAssetTrackerCreateMenu) {
		this.isEnableAssetTrackerCreateMenu = isEnableAssetTrackerCreateMenu;
	}

	public boolean isEnableAssetTrackerViewMenu() {
		return isEnableAssetTrackerViewMenu;
	}

	public void setEnableAssetTrackerViewMenu(boolean isEnableAssetTrackerViewMenu) {
		this.isEnableAssetTrackerViewMenu = isEnableAssetTrackerViewMenu;
	}

	
}

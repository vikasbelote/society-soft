package com.society.model.domain;

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

	public void initMenuRights(LoginDomain loginDomain) {

		if (loginDomain.getRoleName().equals("Owner"))
			isEnabledRegistrationMenu = true;

		if (loginDomain.getRoleName().equals("Admin")) {
			isEnabledCreateUserMenu = isEnabledReminderMenu = isEnabledReportMenu = isEnabledBlanceSheetMenu = isEnabledIncomeAndExpenseMenu = 
					isEnabledMaintainceReport = isEnabledUserListMenu = isEnabledGeneralHeadMenu = isEnabledReocrdTransactionMenu = 
					isEnableSocietyConfigurationMenu = isEnableCreateMaintenacneReport = isEnableViewMaintenacneReport = isEnableEmailNotificationReportMenu = 
					isEnableMessageNotificationReportMenu = isEnableNotificationReport = isEnableMaintenanceHeadMenu = isEnableMaintenanceInterestPolicyMenu =
					isEnableMaintenancePenalitiesPolicyMenu = isEnableMaintenanceRebatePolicyMenu = isEnableMaintenanceAdditionalAreaMenu = isEnableSocietyMemberMenu = 
					isEnableMasterAdditionalAreaMenu = isEnableMasterMenu = isEnableMaintenanceBillStatusMenu = true;
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
		
		if(isEnableCreateMaintenacneReport || isEnableViewMaintenacneReport || isEnableMaintenanceHeadMenu || isEnableMaintenanceInterestPolicyMenu || 
				isEnableMaintenancePenalitiesPolicyMenu || isEnableMaintenanceRebatePolicyMenu || isEnableMaintenanceAdditionalAreaMenu || isEnableMaintenanceBillStatusMenu)
			isEnabledMaintainceReport = true;
		
		if(isEnabledRegistrationMenu || isEnabledCreateUserMenu || isEnabledUserListMenu || isEnableSocietyMemberMenu)
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

	
}

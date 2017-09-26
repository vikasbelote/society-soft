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

	public void initMenuRights(LoginDomain loginDomain) {

		if (loginDomain.getRoleName().equals("Owner"))
			isEnabledRegistrationMenu = true;

		if (loginDomain.getRoleName().equals("Admin")) {
			isEnabledCreateUserMenu = isEnabledReminderMenu = isEnabledReportMenu = isEnabledBlanceSheetMenu = isEnabledIncomeAndExpenseMenu = isEnabledMaintainceReport = isEnabledUserListMenu = isEnabledGeneralHeadMenu = isEnabledReocrdTransactionMenu = true;
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
				case 5: {
					isEnabledMaintainceReport = true;
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
				}
			}
		}
		
		if(isEnabledRegistrationMenu || isEnabledCreateUserMenu || isEnabledUserListMenu)
			isEnabledSocietyMenu = true;
		
		if(isEnabledBlanceSheetMenu || isEnabledIncomeAndExpenseMenu || isEnabledMaintainceReport)
			isEnabledReportMenu = true;
		
		if(isEnabledGeneralHeadMenu || isEnabledReocrdTransactionMenu)
			isEnabledSettingMenu = true;
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
}

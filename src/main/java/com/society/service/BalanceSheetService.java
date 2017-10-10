package com.society.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.TransactionJPA;
import com.society.model.report.BalanceSheet;
import com.society.model.report.GeneralHeadReportModel;
import com.society.model.report.SectionReportModel;
import com.society.model.report.TransactionReportModel;
import com.society.repository.BalanceSheetRepository;

@Service
public class BalanceSheetService {
	
	@Autowired
	private BalanceSheetRepository balanceSheetRepository;
	
	public BalanceSheet getBalanceSheetData() {
		
	    Date lastStartDate = Date.valueOf("2015-04-01");
	    Date lastEndDate = Date.valueOf("2016-03-31");
	    Date currentStartDate = Date.valueOf("2016-04-01");
	    Date currentEndDate = Date.valueOf("2017-03-31");
		
	    String currentYearRange = currentStartDate.getYear() + "-" + currentEndDate.getYear();
	    String lastYearRange = lastStartDate.getYear() + "-" + lastEndDate.getYear();
	    
		List<GeneralHeadJPA> generalHeadList = balanceSheetRepository.getBalanceSheetData1(1);
		
		List<GeneralHeadReportModel> liabilitesGeneralHeadList = new ArrayList<GeneralHeadReportModel>();
		List<GeneralHeadReportModel> assetsGeneralHeadList = new ArrayList<GeneralHeadReportModel>();
		
		for(GeneralHeadJPA generalHeadDB : generalHeadList) {
			
			List<TransactionJPA> transactionListDB = generalHeadDB.getTransactionList();
			if(CollectionUtils.isEmpty(transactionListDB))
				continue;
			
			GeneralHeadReportModel generalHead = new GeneralHeadReportModel();
			generalHead.setGeneralHeadName(generalHeadDB.getGeneralHeadName());
			
			Double totalCurrentYearGeneralHeadAmount = new Double(0);
			Double totalLastYearGeneralHeadAmount = new Double(0);
			List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();
			for(TransactionJPA transactionDB : transactionListDB) {
				
				//check for any existing transaction
				boolean flag = false;
				for(TransactionReportModel checkTransaction : transactionList) {
					
					if(checkTransaction.getDescription().equals(transactionDB.getTransactionDescription().getTransactionDescription())){
						if(checkTransaction.getCurrentYearAmount() != null && checkTransaction.getLastYearAmount() != null)
							flag = true;
					}
				}
				if(flag)
					continue;
				
				TransactionReportModel transaction = new TransactionReportModel();
				transaction.setDescription(transactionDB.getTransactionDescription().getTransactionDescription());
				
				//mapping for last and current year transaction amount
				for(TransactionJPA tempTransactionDB : transactionListDB) {
					
					if(transactionDB.getTransactionDescription().getDescId() == tempTransactionDB.getTransactionDescription().getDescId()){
						Date transactionDate = transactionDB.getTransactionDate();
						if(transactionDate.after(currentStartDate) && transactionDate.before(currentEndDate)){
							transaction.setCurrentYearAmount(tempTransactionDB.getTransactionAmount());
							continue;
						}
						if(transactionDate.after(lastStartDate) && transactionDate.before(lastEndDate)){
							transaction.setLastYearAmount(tempTransactionDB.getTransactionAmount());
							continue;
						}
					}
				}
				transactionList.add(transaction);
				totalCurrentYearGeneralHeadAmount = totalCurrentYearGeneralHeadAmount + transaction.getCurrentYearAmount();
				totalLastYearGeneralHeadAmount = totalLastYearGeneralHeadAmount + transaction.getLastYearAmount();
			}
			generalHead.setTransactionList(transactionList);
			generalHead.setTotalCurrentYearGeneralHeadAmount(totalCurrentYearGeneralHeadAmount);
			generalHead.setTotalLastYearGeneralHeadAmount(totalLastYearGeneralHeadAmount);
			
			if(generalHeadDB.getSection().getSectionId() == 1)
				liabilitesGeneralHeadList.add(generalHead);
			else
				assetsGeneralHeadList.add(generalHead);
		}
		
//		GeneralHeadReportModel generalHeadL1 = new GeneralHeadReportModel();
//		generalHeadL1.setGeneralHeadName("Development Fund Collection");
//		generalHeadL1.setTransactionList(this.getLiabilitisTransactionL1());
//		generalHeadL1.setTotalGeneralHeadAmount(new Double(100));
//
//		GeneralHeadReportModel generalHeadL2 = new GeneralHeadReportModel();
//		generalHeadL2.setGeneralHeadName("Sinking Fund");
//		generalHeadL2.setTransactionList(this.getLiabilitisTransactionL2());
//		generalHeadL2.setTotalGeneralHeadAmount(new Double(200));
//
//		GeneralHeadReportModel generalHeadL3 = new GeneralHeadReportModel();
//		generalHeadL3.setGeneralHeadName("Currnet Liabilities");
//		generalHeadL3.setTransactionList(this.getLiabilitisTransactionL3());
//		generalHeadL3.setTotalGeneralHeadAmount(new Double(300));
//		
//		List<GeneralHeadReportModel> liabilitesGeneralHeadList = new ArrayList<GeneralHeadReportModel>();
//		liabilitesGeneralHeadList.add(generalHeadL1);
//		liabilitesGeneralHeadList.add(generalHeadL2);
//		liabilitesGeneralHeadList.add(generalHeadL3);
//		
//		GeneralHeadReportModel generalHeadA1 = new GeneralHeadReportModel();
//		generalHeadA1.setGeneralHeadName("Fixed Assets");
//		generalHeadA1.setTransactionList(this.getAssetsTransactionA1());
//		generalHeadA1.setTotalGeneralHeadAmount(new Double(100));
//		
//		GeneralHeadReportModel generalHeadA2 = new GeneralHeadReportModel();
//		generalHeadA2.setGeneralHeadName("Investments");
//		generalHeadA2.setTransactionList(this.getAssetsTransactionA2());
//		generalHeadA2.setTotalGeneralHeadAmount(new Double(100));
//		
//		GeneralHeadReportModel generalHeadA3 = new GeneralHeadReportModel();
//		generalHeadA3.setGeneralHeadName("Cash & Bank Balance");
//		generalHeadA3.setTransactionList(this.getAssetsTransactionA3());
//		generalHeadA3.setTotalGeneralHeadAmount(new Double(100));
//		
//		List<GeneralHeadReportModel> assetsGeneralHeadList = new ArrayList<GeneralHeadReportModel>();
//		assetsGeneralHeadList.add(generalHeadA1);
//		assetsGeneralHeadList.add(generalHeadA2);
//		assetsGeneralHeadList.add(generalHeadA3);
		
		Double grossTotalCurrentYearLiabilities = new Double(0);
		Double grossTotalLastYearLiabilities = new Double(0);
		for(GeneralHeadReportModel generalHead : liabilitesGeneralHeadList) {
			
			grossTotalCurrentYearLiabilities = grossTotalCurrentYearLiabilities + generalHead.getTotalCurrentYearGeneralHeadAmount();
			grossTotalLastYearLiabilities = grossTotalLastYearLiabilities + generalHead.getTotalLastYearGeneralHeadAmount();
		}
		
		SectionReportModel liabilities = new SectionReportModel();
		liabilities.setCurrentYear(currentYearRange);
		liabilities.setPrevYear(lastYearRange);
		liabilities.setGrossTotalCurrentYear(grossTotalCurrentYearLiabilities);
		liabilities.setGrossTotalPrevYear(grossTotalLastYearLiabilities);
		liabilities.setGeneralHeadList(liabilitesGeneralHeadList);
		liabilities.setDefaultTransactionList(this.getDefaultTransactionList());
		
		Double grossTotalCurrentYearAssets = new Double(0);
		Double grossTotalLastYearAssets = new Double(0);
		for(GeneralHeadReportModel generalHead : assetsGeneralHeadList) {
			
			grossTotalCurrentYearAssets = grossTotalCurrentYearAssets + generalHead.getTotalCurrentYearGeneralHeadAmount();
			grossTotalLastYearAssets = grossTotalLastYearAssets + generalHead.getTotalLastYearGeneralHeadAmount();
		}
		
		SectionReportModel assets = new SectionReportModel();
		assets.setCurrentYear(currentYearRange);
		assets.setPrevYear(lastYearRange);
		assets.setGrossTotalCurrentYear(grossTotalCurrentYearAssets);
		assets.setGrossTotalPrevYear(grossTotalLastYearAssets);
		assets.setGeneralHeadList(assetsGeneralHeadList);

		BalanceSheet balanceSheet = new BalanceSheet();
		balanceSheet.setLiabilities(liabilities);
		balanceSheet.setAssets(assets);
		balanceSheet.setAsOnDate(currentEndDate.toString());
		return balanceSheet;
	}
	
	private List<TransactionReportModel> getDefaultTransactionList() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Liabilities Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("D" + i);
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transaction.setTotalAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}

	private List<TransactionReportModel> getLiabilitisTransactionL1() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Liabilities Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("L" + i);
			transaction.setCurrentYearAmount(new Double(100 * i * 1));
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}

	private List<TransactionReportModel> getLiabilitisTransactionL2() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Liabilities Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("L" + i);
			transaction.setCurrentYearAmount(new Double(100 * i * 1));
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}

	private List<TransactionReportModel> getLiabilitisTransactionL3() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Liabilities Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("L" + i);
			transaction.setCurrentYearAmount(new Double(100 * i * 1));
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}

	private List<TransactionReportModel> getAssetsTransactionA1() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Assets Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("A" + i);
			transaction.setCurrentYearAmount(new Double(100 * i * 1));
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}
	
	private List<TransactionReportModel> getAssetsTransactionA2() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Assets Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("A" + i);
			transaction.setCurrentYearAmount(new Double(100 * i * 1));
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}
	
	private List<TransactionReportModel> getAssetsTransactionA3() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Assets Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("A" + i);
			transaction.setCurrentYearAmount(new Double(100 * i * 1));
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}

}

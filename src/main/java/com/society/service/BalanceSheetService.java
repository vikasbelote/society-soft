package com.society.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.constant.SectionEnum;
import com.society.model.domain.BalanceSheetDomain;
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
	
	private int getYear(Date date) {
		Calendar calender = Calendar.getInstance();
	    calender.setTime(date);
	    return calender.get(Calendar.YEAR);
	}
	
	public BalanceSheet getBalanceSheetData(BalanceSheetDomain balanceSheetDomain) {
		
	    String currentYearRange = getYear(balanceSheetDomain.getCurrentYearStartDate()) + "-" + getYear(balanceSheetDomain.getCurrentYearEndDate());
	    String lastYearRange = getYear(balanceSheetDomain.getLastYearStartDate()) + "-" + getYear(balanceSheetDomain.getLastYearEndDate());
	    
		List<GeneralHeadJPA> generalHeadList = balanceSheetRepository.getBalanceSheetData(balanceSheetDomain);
		
		List<GeneralHeadReportModel> liabilitesGeneralHeadList = new ArrayList<GeneralHeadReportModel>();
		List<GeneralHeadReportModel> assetsGeneralHeadList = new ArrayList<GeneralHeadReportModel>();
		
		List<Integer> generalHeadIdList = new ArrayList<Integer>();
		for(GeneralHeadJPA generalHeadDB : generalHeadList) {
			
			//check for duplicate general Head
			if(generalHeadIdList.contains(generalHeadDB.getGeneralHeadId()))
				continue;
			generalHeadIdList.add(generalHeadDB.getGeneralHeadId());
			
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
						Date transactionDate = tempTransactionDB.getTransactionDate();
						if(transactionDate.after(balanceSheetDomain.getCurrentYearStartDate()) && transactionDate.before(balanceSheetDomain.getCurrentYearEndDate())){
							transaction.setCurrentYearAmount(tempTransactionDB.getTransactionAmount());
							continue;
						}
						if(transactionDate.after(balanceSheetDomain.getLastYearStartDate()) && transactionDate.before(balanceSheetDomain.getLastYearEndDate())){
							transaction.setLastYearAmount(tempTransactionDB.getTransactionAmount());
							continue;
						}
					}
				}
				transactionList.add(transaction);
				totalCurrentYearGeneralHeadAmount = totalCurrentYearGeneralHeadAmount + (transaction.getCurrentYearAmount() == null ? 0.0 : transaction.getCurrentYearAmount());
				totalLastYearGeneralHeadAmount = totalLastYearGeneralHeadAmount + (transaction.getLastYearAmount() == null ? 0.0 : transaction.getLastYearAmount());
			}
			generalHead.setTransactionList(transactionList);
			generalHead.setTotalCurrentYearGeneralHeadAmount(totalCurrentYearGeneralHeadAmount);
			generalHead.setTotalLastYearGeneralHeadAmount(totalLastYearGeneralHeadAmount);
			
			if(generalHeadDB.getSection().getSectionName().equals(SectionEnum.LC.value()))
				liabilitesGeneralHeadList.add(generalHead);
			else if(generalHeadDB.getSection().getSectionName().equals(SectionEnum.PA.value()))
				assetsGeneralHeadList.add(generalHead);
		}
		
		Double grossTotalCurrentYearLiabilities = new Double(0);
		Double grossTotalLastYearLiabilities = new Double(0);
		for(GeneralHeadReportModel generalHead : liabilitesGeneralHeadList) {
			
			grossTotalCurrentYearLiabilities = grossTotalCurrentYearLiabilities + (generalHead.getTotalCurrentYearGeneralHeadAmount() == null ? 0.0 : generalHead.getTotalCurrentYearGeneralHeadAmount());
			grossTotalLastYearLiabilities = grossTotalLastYearLiabilities + (generalHead.getTotalLastYearGeneralHeadAmount() == null ? 0.0 : generalHead.getTotalLastYearGeneralHeadAmount());
		}
		
		SectionReportModel liabilities = new SectionReportModel();
		liabilities.setCurrentYear(currentYearRange);
		liabilities.setPrevYear(lastYearRange);
		liabilities.setGrossTotalCurrentYear(grossTotalCurrentYearLiabilities);
		liabilities.setGrossTotalPrevYear(grossTotalLastYearLiabilities);
		liabilities.setGeneralHeadList(liabilitesGeneralHeadList);
		
		Double grossTotalCurrentYearAssets = new Double(0);
		Double grossTotalLastYearAssets = new Double(0);
		for(GeneralHeadReportModel generalHead : assetsGeneralHeadList) {
			
			grossTotalCurrentYearAssets = grossTotalCurrentYearAssets + (generalHead.getTotalCurrentYearGeneralHeadAmount() == null ? 0.0 : generalHead.getTotalCurrentYearGeneralHeadAmount());
			grossTotalLastYearAssets = grossTotalLastYearAssets + (generalHead.getTotalLastYearGeneralHeadAmount() == null ? 0.0 : generalHead.getTotalLastYearGeneralHeadAmount());
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
		balanceSheet.setAsOnDate(balanceSheetDomain.getCurrentYearEndDate().toString());
		return balanceSheet;
	}

}

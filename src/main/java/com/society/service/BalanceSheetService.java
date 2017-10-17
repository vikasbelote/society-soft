package com.society.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.constant.SectionEnum;
import com.society.constant.TypeEnum;
import com.society.model.domain.BalanceSheetDomain;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.GeneralHeadOrderJPA;
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
	    
	    List<GeneralHeadReportModel> liabilitesGeneralHeadList = new ArrayList<GeneralHeadReportModel>();
		List<GeneralHeadReportModel> assetsGeneralHeadList = new ArrayList<GeneralHeadReportModel>();
	    
	    List<TransactionJPA> transactionListDB = balanceSheetRepository.getBalanceSheetData(balanceSheetDomain);
	    for(TransactionJPA transactionDB : transactionListDB) {
	    	
	    	//Find General Head for this transaction
	    	GeneralHeadJPA generalHeadDB = transactionDB.getGeneralHead();
	    	GeneralHeadReportModel currentGeneralHeadReportModel = null;
	    	//Liabilities General Head
	    	for(GeneralHeadReportModel generalHeadReportModel : liabilitesGeneralHeadList) {
	    		if(generalHeadReportModel.getGeneralHeadName().equals(generalHeadDB.getGeneralHeadName())){
	    			currentGeneralHeadReportModel = generalHeadReportModel;
	    			break;
	    		}
	    	}
	    	//Assest General Head
	    	if(currentGeneralHeadReportModel == null) {
		    	for(GeneralHeadReportModel generalHeadReportModel : assetsGeneralHeadList) {
		    		if(generalHeadReportModel.getGeneralHeadName().equals(generalHeadDB.getGeneralHeadName())){
		    			currentGeneralHeadReportModel = generalHeadReportModel;
		    			break;
		    		}
		    	}
	    	}
	    	//Create General Head Report Model and add to respective list
	    	if(currentGeneralHeadReportModel == null) {
	    		currentGeneralHeadReportModel = new GeneralHeadReportModel();
	    		currentGeneralHeadReportModel.setGeneralHeadName(generalHeadDB.getGeneralHeadName());
	    		if(generalHeadDB.getSection().getSectionName().equals(SectionEnum.LC.value()))
					liabilitesGeneralHeadList.add(currentGeneralHeadReportModel);
				else if(generalHeadDB.getSection().getSectionName().equals(SectionEnum.PA.value()))
					assetsGeneralHeadList.add(currentGeneralHeadReportModel);
	    	}
	    	
	    	//Transaction
	    	String transactionDescription = transactionDB.getTransactionDescription().getTransactionDescription();
	    	TransactionReportModel existTransaction = null;
	    	if(CollectionUtils.isNotEmpty(currentGeneralHeadReportModel.getTransactionList())) {
	    		for(TransactionReportModel transaction : currentGeneralHeadReportModel.getTransactionList()){
		    		if(transaction.getDescription().equals(transactionDescription)){
		    			existTransaction = transaction;
		    		}
		    	}
	    	}
	    	
	    	if(existTransaction != null) {
	    		Date transactionDate = transactionDB.getTransactionDate();
				if(transactionDate.after(balanceSheetDomain.getCurrentYearStartDate()) && transactionDate.before(balanceSheetDomain.getCurrentYearEndDate())){
					existTransaction.setCurrentYearAmount(transactionDB.getTransactionAmount());
					existTransaction.setCurrentYearType(transactionDB.getTransactionType());
				}
				if(transactionDate.after(balanceSheetDomain.getLastYearStartDate()) && transactionDate.before(balanceSheetDomain.getLastYearEndDate())){
					existTransaction.setLastYearAmount(transactionDB.getTransactionAmount());
					existTransaction.setLastYearType(transactionDB.getTransactionType());
				}
	    	}
	    	else {
	    		TransactionReportModel transaction = new TransactionReportModel();
	    		transaction.setDescription(transactionDB.getTransactionDescription().getTransactionDescription());
	    		
	    		Date transactionDate = transactionDB.getTransactionDate();
				if(transactionDate.after(balanceSheetDomain.getCurrentYearStartDate()) && transactionDate.before(balanceSheetDomain.getCurrentYearEndDate())){
					transaction.setCurrentYearAmount(transactionDB.getTransactionAmount());
					transaction.setCurrentYearType(transactionDB.getTransactionType());
				}
				if(transactionDate.after(balanceSheetDomain.getLastYearStartDate()) && transactionDate.before(balanceSheetDomain.getLastYearEndDate())){
					transaction.setLastYearAmount(transactionDB.getTransactionAmount());
					transaction.setLastYearType(transactionDB.getTransactionType());
				}
				if(currentGeneralHeadReportModel.getTransactionList() == null){
					List<TransactionReportModel> transactionReportModelList = new ArrayList<TransactionReportModel>(); 
					transactionReportModelList.add(transaction);
					currentGeneralHeadReportModel.setTransactionList(transactionReportModelList);
				}
				else {
					currentGeneralHeadReportModel.getTransactionList().add(transaction);
				}
	    	}	
	    }
	    
	    for(int i = 0; i < liabilitesGeneralHeadList.size(); i++) {
	    	
	    	GeneralHeadReportModel generalHead = liabilitesGeneralHeadList.get(i);
	    	
	    	List<TransactionReportModel> transactionList = generalHead.getTransactionList();
	    	Double currentYearGeneralHeadToatl = new Double(0);
			Double lastYearGeneralHeadTotal = new Double(0);
	    	for(TransactionReportModel transaction : transactionList) {
	    		if(transaction.getCurrentYearType() != null && transaction.getCurrentYearType().equals(TypeEnum.ADD.value()))
	    			currentYearGeneralHeadToatl = currentYearGeneralHeadToatl + (transaction.getCurrentYearAmount() == null ? 0.0 : transaction.getCurrentYearAmount());
	    		else if(transaction.getCurrentYearType() != null && transaction.getCurrentYearType().equals(TypeEnum.SUBTRACT.value()))
	    			currentYearGeneralHeadToatl = currentYearGeneralHeadToatl - (transaction.getCurrentYearAmount() == null ? 0.0 : transaction.getCurrentYearAmount());
	    		
	    		if(transaction.getLastYearType() != null && transaction.getLastYearType().equals(TypeEnum.ADD.value()))
	    			lastYearGeneralHeadTotal = lastYearGeneralHeadTotal + (transaction.getLastYearAmount() == null ? 0.0 : transaction.getLastYearAmount());
	    		else if(transaction.getLastYearType() != null && transaction.getLastYearType().equals(TypeEnum.SUBTRACT.value()))
	    			lastYearGeneralHeadTotal = lastYearGeneralHeadTotal - (transaction.getLastYearAmount() == null ? 0.0 : transaction.getLastYearAmount());
	    	}
	    	generalHead.setTotalCurrentYearGeneralHeadAmount(currentYearGeneralHeadToatl);
	    	generalHead.setTotalLastYearGeneralHeadAmount(lastYearGeneralHeadTotal);
	    }
	    
	    for(int i = 0; i < assetsGeneralHeadList.size(); i++) {
	    	
	    	GeneralHeadReportModel generalHead = assetsGeneralHeadList.get(i);
	    	
	    	List<TransactionReportModel> transactionList = generalHead.getTransactionList();
	    	Double currentYearGeneralHeadToatl = new Double(0);
			Double lastYearGeneralHeadTotal = new Double(0);
	    	for(TransactionReportModel transaction : transactionList) {
	    		if(transaction.getCurrentYearType() != null && transaction.getCurrentYearType().equals(TypeEnum.ADD.value()))
	    			currentYearGeneralHeadToatl = currentYearGeneralHeadToatl + (transaction.getCurrentYearAmount() == null ? 0.0 : transaction.getCurrentYearAmount());
	    		else if(transaction.getCurrentYearType() != null && transaction.getCurrentYearType().equals(TypeEnum.SUBTRACT.value()))
	    			currentYearGeneralHeadToatl = currentYearGeneralHeadToatl - (transaction.getCurrentYearAmount() == null ? 0.0 : transaction.getCurrentYearAmount());
	    		
	    		if(transaction.getLastYearType() != null && transaction.getLastYearType().equals(TypeEnum.ADD.value()))
	    			lastYearGeneralHeadTotal = lastYearGeneralHeadTotal + (transaction.getLastYearAmount() == null ? 0.0 : transaction.getLastYearAmount());
	    		else if(transaction.getLastYearType() != null && transaction.getLastYearType().equals(TypeEnum.SUBTRACT.value()))
	    			lastYearGeneralHeadTotal = lastYearGeneralHeadTotal - (transaction.getLastYearAmount() == null ? 0.0 : transaction.getLastYearAmount());
	    	}
	    	generalHead.setTotalCurrentYearGeneralHeadAmount(currentYearGeneralHeadToatl);
	    	generalHead.setTotalLastYearGeneralHeadAmount(lastYearGeneralHeadTotal);
	    }
	   
	    
	    Double grossTotalCurrentYearLiabilities = new Double(0);
		Double grossTotalLastYearLiabilities = new Double(0);
		for(GeneralHeadReportModel generalHead : liabilitesGeneralHeadList) {
			
			grossTotalCurrentYearLiabilities = grossTotalCurrentYearLiabilities + (generalHead.getTotalCurrentYearGeneralHeadAmount() == null ? 0.0 : generalHead.getTotalCurrentYearGeneralHeadAmount());
			grossTotalLastYearLiabilities = grossTotalLastYearLiabilities + (generalHead.getTotalLastYearGeneralHeadAmount() == null ? 0.0 : generalHead.getTotalLastYearGeneralHeadAmount());
		}
		
		//order general head list
		List<GeneralHeadOrderJPA> generalHeadOrderList = balanceSheetRepository.getGeneralHeadOrder(balanceSheetDomain);
		if(CollectionUtils.isNotEmpty(generalHeadOrderList)) {
			liabilitesGeneralHeadList = this.getOrderGeneralHeadReportModel(liabilitesGeneralHeadList, generalHeadOrderList);
			assetsGeneralHeadList = this.getOrderGeneralHeadReportModel(assetsGeneralHeadList, generalHeadOrderList);
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
	
	private List<GeneralHeadReportModel> getOrderGeneralHeadReportModel(List<GeneralHeadReportModel> generalHeadReportModelList, List<GeneralHeadOrderJPA> generalHeadOrderList) {
		
		List<GeneralHeadReportModel> generalHeadList = new ArrayList<GeneralHeadReportModel>();
		for(GeneralHeadOrderJPA generalHeadOrderDB : generalHeadOrderList) {
			for(GeneralHeadReportModel generalHeadReportModel : generalHeadReportModelList) {
				if(generalHeadOrderDB.getGeneralHead().getGeneralHeadName().equals(generalHeadReportModel.getGeneralHeadName())) {
					generalHeadList.add(generalHeadReportModel);
					break;
				}
			}
		}
		return generalHeadList;
	}
	
	public BalanceSheet getBalanceSheetData1(BalanceSheetDomain balanceSheetDomain) {
		
	    String currentYearRange = getYear(balanceSheetDomain.getCurrentYearStartDate()) + "-" + getYear(balanceSheetDomain.getCurrentYearEndDate());
	    String lastYearRange = getYear(balanceSheetDomain.getLastYearStartDate()) + "-" + getYear(balanceSheetDomain.getLastYearEndDate());
	    
		List<GeneralHeadJPA> generalHeadList = balanceSheetRepository.getBalanceSheetData1(balanceSheetDomain);
		
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

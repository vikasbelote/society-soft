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
import com.society.model.domain.CompareSectionReportDomain;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.GeneralHeadOrderJPA;
import com.society.model.jpa.TransactionJPA;
import com.society.model.report.CompareSectionReport;
import com.society.model.report.GeneralHeadReportModel;
import com.society.model.report.SectionReportModel;
import com.society.model.report.TransactionReportModel;
import com.society.repository.CompareSectionReportRepository;

@Service
public class CompareSectionReportService {
	
	@Autowired
	private CompareSectionReportRepository compareSectionReportRepository;
	
	private int getYear(Date date) {
		Calendar calender = Calendar.getInstance();
	    calender.setTime(date);
	    return calender.get(Calendar.YEAR);
	}
	
	public CompareSectionReport getCompareSectionReportData(CompareSectionReportDomain compareSectionReportDomain) {
		
		String currentYearRange = getYear(compareSectionReportDomain.getCurrentYearStartDate()) + "-" + getYear(compareSectionReportDomain.getCurrentYearEndDate());
	    String lastYearRange = getYear(compareSectionReportDomain.getLastYearStartDate()) + "-" + getYear(compareSectionReportDomain.getLastYearEndDate());
	    
	    List<GeneralHeadReportModel> leftSectionHeadList = new ArrayList<GeneralHeadReportModel>();
		List<GeneralHeadReportModel> rightSectionHeadList = new ArrayList<GeneralHeadReportModel>();
	    
	    List<TransactionJPA> transactionListDB = compareSectionReportRepository.getBalanceSheetData(compareSectionReportDomain);
	    for(TransactionJPA transactionDB : transactionListDB) {
	    	
	    	//Find General Head for this transaction
	    	GeneralHeadJPA generalHeadDB = transactionDB.getGeneralHead();
	    	GeneralHeadReportModel currentGeneralHeadReportModel = null;
	    	//Left Section General Head
	    	for(GeneralHeadReportModel generalHeadReportModel : leftSectionHeadList) {
	    		if(generalHeadReportModel.getGeneralHeadName().equals(generalHeadDB.getGeneralHeadName())){
	    			currentGeneralHeadReportModel = generalHeadReportModel;
	    			break;
	    		}
	    	}
	    	//Right Section General Head
	    	if(currentGeneralHeadReportModel == null) {
		    	for(GeneralHeadReportModel generalHeadReportModel : rightSectionHeadList) {
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
	    			leftSectionHeadList.add(currentGeneralHeadReportModel);
				else if(generalHeadDB.getSection().getSectionName().equals(SectionEnum.PA.value()))
					rightSectionHeadList.add(currentGeneralHeadReportModel);
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
				if(transactionDate.after(compareSectionReportDomain.getCurrentYearStartDate()) && transactionDate.before(compareSectionReportDomain.getCurrentYearEndDate())){
					existTransaction.setCurrentYearAmount(transactionDB.getTransactionAmount());
					existTransaction.setCurrentYearType(transactionDB.getTransactionType());
				}
				if(transactionDate.after(compareSectionReportDomain.getLastYearStartDate()) && transactionDate.before(compareSectionReportDomain.getLastYearEndDate())){
					existTransaction.setLastYearAmount(transactionDB.getTransactionAmount());
					existTransaction.setLastYearType(transactionDB.getTransactionType());
				}
	    	}
	    	else {
	    		TransactionReportModel transaction = new TransactionReportModel();
	    		transaction.setDescription(transactionDB.getTransactionDescription().getTransactionDescription());
	    		
	    		Date transactionDate = transactionDB.getTransactionDate();
				if(transactionDate.after(compareSectionReportDomain.getCurrentYearStartDate()) && transactionDate.before(compareSectionReportDomain.getCurrentYearEndDate())){
					transaction.setCurrentYearAmount(transactionDB.getTransactionAmount());
					transaction.setCurrentYearType(transactionDB.getTransactionType());
				}
				if(transactionDate.after(compareSectionReportDomain.getLastYearStartDate()) && transactionDate.before(compareSectionReportDomain.getLastYearEndDate())){
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
	    
	    //General Head total calculation left section
	    for(int i = 0; i < leftSectionHeadList.size(); i++) {
	    	
	    	GeneralHeadReportModel generalHead = leftSectionHeadList.get(i);
	    	
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
	  //General Head total calculation right section
	    for(int i = 0; i < rightSectionHeadList.size(); i++) {
	    	
	    	GeneralHeadReportModel generalHead = rightSectionHeadList.get(i);
	    	
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
	   
	    //order general head list
  		List<GeneralHeadOrderJPA> generalHeadOrderList = compareSectionReportRepository.getGeneralHeadOrder(compareSectionReportDomain);
  		if(CollectionUtils.isNotEmpty(generalHeadOrderList)) {
  			leftSectionHeadList = this.getOrderGeneralHeadReportModel(leftSectionHeadList, generalHeadOrderList);
  			rightSectionHeadList = this.getOrderGeneralHeadReportModel(rightSectionHeadList, generalHeadOrderList);
  		}
	    //Gross total calculation left section
	    Double grossTotalCurrentYearLeftSection = new Double(0);
		Double grossTotalLastYearLeftSection = new Double(0);
		for(GeneralHeadReportModel generalHead : leftSectionHeadList) {
			
			grossTotalCurrentYearLeftSection = grossTotalCurrentYearLeftSection + (generalHead.getTotalCurrentYearGeneralHeadAmount() == null ? 0.0 : generalHead.getTotalCurrentYearGeneralHeadAmount());
			grossTotalLastYearLeftSection = grossTotalLastYearLeftSection + (generalHead.getTotalLastYearGeneralHeadAmount() == null ? 0.0 : generalHead.getTotalLastYearGeneralHeadAmount());
		}
		
		SectionReportModel leftSection = new SectionReportModel();
		leftSection.setCurrentYear(currentYearRange);
		leftSection.setPrevYear(lastYearRange);
		leftSection.setGrossTotalCurrentYear(grossTotalCurrentYearLeftSection);
		leftSection.setGrossTotalPrevYear(grossTotalLastYearLeftSection);
		leftSection.setGeneralHeadList(leftSectionHeadList);
		
		//Gross total calculation Right section
		Double grossTotalCurrentYearRightSection = new Double(0);
		Double grossTotalLastYearRightSection = new Double(0);
		for(GeneralHeadReportModel generalHead : rightSectionHeadList) {
			
			grossTotalCurrentYearRightSection = grossTotalCurrentYearRightSection + (generalHead.getTotalCurrentYearGeneralHeadAmount() == null ? 0.0 : generalHead.getTotalCurrentYearGeneralHeadAmount());
			grossTotalLastYearRightSection = grossTotalLastYearRightSection + (generalHead.getTotalLastYearGeneralHeadAmount() == null ? 0.0 : generalHead.getTotalLastYearGeneralHeadAmount());
		}
		
		SectionReportModel rightSection = new SectionReportModel();
		rightSection.setCurrentYear(currentYearRange);
		rightSection.setPrevYear(lastYearRange);
		rightSection.setGrossTotalCurrentYear(grossTotalCurrentYearRightSection);
		rightSection.setGrossTotalPrevYear(grossTotalLastYearRightSection);
		rightSection.setGeneralHeadList(rightSectionHeadList);

		CompareSectionReport compareSectionReport = new CompareSectionReport();
		compareSectionReport.setLeftSection(leftSection);
		compareSectionReport.setRightSection(rightSection);
		compareSectionReport.setAsOnDate(compareSectionReportDomain.getCurrentYearEndDate().toString());
		return compareSectionReport;
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
		
		//Add newly added general head which is order not inserted into database
		for(GeneralHeadReportModel generalHeadReportModel : generalHeadReportModelList) {
			boolean isNotPresent = true;
			for(GeneralHeadReportModel finalGeneralHeadReportModel : generalHeadList) {
				if(generalHeadReportModel.getGeneralHeadName().equals(finalGeneralHeadReportModel.getGeneralHeadName())) {
					isNotPresent = false;
					break;
				}
			}
			if(isNotPresent)
				generalHeadList.add(generalHeadReportModel);
		}
		return generalHeadList;
	}

}

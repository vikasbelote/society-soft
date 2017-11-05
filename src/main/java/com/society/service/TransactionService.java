package com.society.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.helper.model.DropDownHelper;
import com.society.model.domain.TransactionDescriptionDomain;
import com.society.model.domain.TransactionDomain;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.SocietyConfigJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.model.jpa.TransactionDescriptionJPA;
import com.society.model.jpa.TransactionJPA;
import com.society.model.jpa.TransactionTypeJPA;
import com.society.repository.MaintenanceRepository;
import com.society.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private MaintenanceRepository maintenanceRepository;

	public Map<String, List<DropDownHelper>> getMasterData(Integer societyId) {

		Map<String, List<DropDownHelper>> dropDownMap = new HashMap<String, List<DropDownHelper>>();

		List<GeneralHeadJPA> generalHeadList = transactionRepository.getGeneralHeadList(societyId);

		List<DropDownHelper> generalHeadDropDownList = new ArrayList<DropDownHelper>();
		if (!CollectionUtils.isEmpty(generalHeadList)) {
			for (GeneralHeadJPA generalHead : generalHeadList) {
				DropDownHelper dropDownHelper = new DropDownHelper();
				dropDownHelper.setValue(generalHead.getGeneralHeadId());
				dropDownHelper.setText(generalHead.getGeneralHeadName());
				generalHeadDropDownList.add(dropDownHelper);
			}
		}

		dropDownMap.put("generalHeadList", generalHeadDropDownList);

		List<TransactionTypeJPA> transactionTypeList = transactionRepository.getMasterList(TransactionTypeJPA.class);
		List<DropDownHelper> transactionTypeDropDownList = new ArrayList<DropDownHelper>();

		if (!CollectionUtils.isEmpty(transactionTypeList)) {
			for (TransactionTypeJPA transactionType : transactionTypeList) {
				DropDownHelper dropDownHelper = new DropDownHelper();
				dropDownHelper.setValue(transactionType.getTypeId());
				dropDownHelper.setText(transactionType.getTypeName());
				transactionTypeDropDownList.add(dropDownHelper);
			}
		}
		dropDownMap.put("transactionTypeList", transactionTypeDropDownList);
		return dropDownMap;
	}
	
	public List<TransactionDescriptionDomain> getTransactionDescriptionDomainList(Integer societyId) {
		
		List<TransactionDescriptionJPA> transactionDescriptionList = transactionRepository.getTransactionDescription(societyId);
		if(CollectionUtils.isEmpty(transactionDescriptionList))
			return null;
		
		List<TransactionDescriptionDomain> transactionDescriptionDomainList = new ArrayList<TransactionDescriptionDomain>();
		
		for(TransactionDescriptionJPA tranDescription : transactionDescriptionList) {
			
			TransactionDescriptionDomain transactionDescriptionDomain= new TransactionDescriptionDomain();
			transactionDescriptionDomain.setDescId(tranDescription.getDescId());
			transactionDescriptionDomain.setLabel(tranDescription.getTransactionDescription());
			transactionDescriptionDomain.setGeneralHeadId(tranDescription.getGeneralHead().getGeneralHeadId());
			transactionDescriptionDomain.setGeneralHeadName(tranDescription.getGeneralHead().getGeneralHeadName());
			transactionDescriptionDomainList.add(transactionDescriptionDomain);
		}
		return transactionDescriptionDomainList;
	}
	
	public List<TransactionDomain> getTransactionEntry(Integer societyId) {
		
		List<TransactionJPA> transactionList = transactionRepository.getTransactionEntry(societyId);
		if(CollectionUtils.isEmpty(transactionList))
			return null;
		
		List<TransactionDomain> transactionDomainList = new ArrayList<TransactionDomain>();
		for(TransactionJPA transaction : transactionList) {
			TransactionDomain transactionDomain = new TransactionDomain();
			transactionDomain.setTransactionId(transaction.getTransactionId());
			transactionDomain.setGeneralHeadId(transaction.getGeneralHead().getGeneralHeadId());
			transactionDomain.setGeneralHeadName(transaction.getGeneralHead().getGeneralHeadName());
			transactionDomain.setTransactionDescriptionId(transaction.getTransactionDescription().getDescId());
			transactionDomain.setTransactionDescription(transaction.getTransactionDescription().getTransactionDescription());
			transactionDomain.setTransactionAmount(transaction.getTransactionAmount());
			transactionDomain.setTransactionDate(transaction.getTransactionDate());
			transactionDomain.setTransactionType(transaction.getTransactionType());
			transactionDomainList.add(transactionDomain);
		}
		return transactionDomainList;
	}
	
	public boolean insertTransactionEntry(TransactionDomain transactionDomain) {
		
		if(transactionDomain == null)
			return false;
		
		GeneralHeadJPA generalHead = new GeneralHeadJPA();
		generalHead.setGeneralHeadId(transactionDomain.getGeneralHeadId());
		
		SocietyJPA society = new SocietyJPA();
		society.setSocietyId(transactionDomain.getSocietyId());
		
		TransactionDescriptionJPA transactionDescription = new TransactionDescriptionJPA();
		transactionDescription.setDescId(transactionDomain.getTransactionDescriptionId());
		transactionDescription.setTransactionDescription(transactionDomain.getTransactionDescription());
		transactionDescription.setGeneralHead(generalHead);
		transactionDescription.setSociety(society);
		
		TransactionJPA transaction = new TransactionJPA();
		transaction.setTransactionId(transactionDomain.getTransactionId());
		transaction.setGeneralHead(generalHead);
		transaction.setTransactionDescription(transactionDescription);
		transaction.setTransactionAmount(transactionDomain.getTransactionAmount());
		transaction.setTransactionDate(transactionDomain.getTransactionDate());
		transaction.setTransactionType(transactionDomain.getTransactionType());
		transaction.setSociety(society);
		
		return transactionRepository.insertTransactionEntry(transaction, transactionDescription);
	}
	
	public boolean deleteTransactionEntry(TransactionDomain transactionDomain) {
		if(transactionDomain == null)
			return false;
		
		TransactionJPA transaction = new TransactionJPA();
		transaction.setTransactionId(transactionDomain.getTransactionId());
		
		return transactionRepository.deleteTransactionEntry(transaction);
	}
	
	public boolean checkTransactionExist(TransactionDomain transactionDomain) {
		List<Date> dateList = this.getFinanicalYear(transactionDomain);
		TransactionJPA transaction = transactionRepository.checkTransactionExist(transactionDomain, dateList);
		if(transaction == null)
			return false;
		return true;
	}
	
	private List<Date> getFinanicalYear(TransactionDomain transactionDomain){
		
		SocietyConfigJPA societyConfig = maintenanceRepository.getSocietyConfigDetail(transactionDomain.getGeneralHeadId());
		if(societyConfig == null)
			return null;
		
		Date startDate = societyConfig.getStartDate();
		Calendar c = Calendar.getInstance(); 
		
		c.setTime(startDate);
		int startMonth = c.get(Calendar.MONTH) + 1;
		
		c.setTime(transactionDomain.getTransactionDate());
		int currentYear = c.get(Calendar.YEAR);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		if(startMonth > currentMonth)
			currentYear = currentYear - 1;
		
		String startDateStr = currentYear + "-" + startMonth + "-01";
		Date realStartDate = Date.valueOf(startDateStr);
		c.setTime(realStartDate);
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.DATE, -1);
		Date realEndDate = new Date(c.getTimeInMillis());
		
		List<Date> dateList = new ArrayList<Date>(2);
		dateList.add(realStartDate);
		dateList.add(realEndDate);
		return dateList;
	}
}

package com.society.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.helper.model.DropDownHelper;
import com.society.model.domain.TransactionDomain;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.model.jpa.TransactionJPA;
import com.society.model.jpa.TransactionTypeJPA;
import com.society.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

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
			transactionDomain.setTransactionTypeId(transaction.getTransactionType().getTypeId());
			transactionDomain.setTransactionTypeName(transaction.getTransactionType().getTypeName());
			transactionDomain.setTransactionAmount(transaction.getTransactionAmount());
			transactionDomain.setTransactionDescription(transaction.getTransactionDescription());
			transactionDomain.setTransactionDate(transaction.getTransactionDate());
			transactionDomainList.add(transactionDomain);
		}
		return transactionDomainList;
	}
	
	public boolean insertTransactionEntry(TransactionDomain transactionDomain) {
		
		if(transactionDomain == null)
			return false;
		
		GeneralHeadJPA generalHead = new GeneralHeadJPA();
		generalHead.setGeneralHeadId(transactionDomain.getGeneralHeadId());
		
		TransactionTypeJPA transactionType = new TransactionTypeJPA();
		transactionType.setTypeId(transactionDomain.getTransactionTypeId());
		
		SocietyJPA society = new SocietyJPA();
		society.setSocietyId(transactionDomain.getSocietyId());
		
		TransactionJPA transaction = new TransactionJPA();
		transaction.setTransactionId(transactionDomain.getTransactionId());
		transaction.setGeneralHead(generalHead);
		transaction.setTransactionType(transactionType);
		transaction.setTransactionAmount(transactionDomain.getTransactionAmount());
		transaction.setTransactionDescription(transactionDomain.getTransactionDescription());
		transaction.setTransactionDate(transactionDomain.getTransactionDate());
		transaction.setSociety(society);
		
		return transactionRepository.insertTransactionEntry(transaction);
	}
	
	public boolean deleteTransactionEntry(TransactionDomain transactionDomain) {
		if(transactionDomain == null)
			return false;
		
		TransactionJPA transaction = new TransactionJPA();
		transaction.setTransactionId(transactionDomain.getTransactionId());
		
		return transactionRepository.deleteTransactionEntry(transaction);
	}

}

package com.society.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.helper.model.DropDownHelper;
import com.society.model.jpa.GeneralHeadJPA;
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

}

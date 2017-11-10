package com.society.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.MaintenanceHeadChargeCalcTypeDomain;
import com.society.model.domain.MaintenanceHeadDomain;
import com.society.model.jpa.MaintenanceHeadChargeCalcJPA;
import com.society.model.jpa.MaintenanceHeadChargeCalcTypeJPA;
import com.society.model.jpa.MaintenanceHeadJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.repository.MaintenanceHeadRepository;

@Service
public class MaintenanceHeadService {
	
	@Autowired
	private MaintenanceHeadRepository maintenanceHeadRepository;
	
	public List<MaintenanceHeadChargeCalcTypeDomain> getMaintenanceHeadChargeCalcType() {
		
		List<MaintenanceHeadChargeCalcTypeJPA> chargeTypeList = maintenanceHeadRepository.getMaintenanceHeadChargeCalcType();
		if(CollectionUtils.isEmpty(chargeTypeList))
			return null;
		
		List<MaintenanceHeadChargeCalcTypeDomain> chargeTypeDomainList = new ArrayList<MaintenanceHeadChargeCalcTypeDomain>();
		for(MaintenanceHeadChargeCalcTypeJPA chargeTypeDB : chargeTypeList) {
			MaintenanceHeadChargeCalcTypeDomain chargeTypeDomain = new MaintenanceHeadChargeCalcTypeDomain();
			chargeTypeDomain.setCalcTypeId(chargeTypeDB.getCalcTypeId());
			chargeTypeDomain.setCalcType(chargeTypeDB.getCalcType());
			chargeTypeDomainList.add(chargeTypeDomain);
		}
		return chargeTypeDomainList;
	}
	
	public boolean saveMaintenanceHead(MaintenanceHeadDomain maintenanceHeadDomain) {
		
		if(maintenanceHeadDomain != null) {
			
			MaintenanceHeadChargeCalcTypeJPA headCalcType = new MaintenanceHeadChargeCalcTypeJPA();
			headCalcType.setCalcTypeId(maintenanceHeadDomain.getChargeTypeId());
			
			MaintenanceHeadChargeCalcJPA headCalc = new MaintenanceHeadChargeCalcJPA();
			headCalc.setCalcId(maintenanceHeadDomain.getCalcId());
			headCalc.setCalcType(headCalcType);
			headCalc.setFixedAmount(maintenanceHeadDomain.getFixedAmount());
			headCalc.setPercentageAmount(maintenanceHeadDomain.getPercentageAmount());
			
			if(maintenanceHeadDomain.getReferenceHeadId().equals(0)) {
				headCalc.setReferenceMaintenanceHead(null);
			}
			else {
				MaintenanceHeadJPA referenceMaintenanceHead = new MaintenanceHeadJPA();
				referenceMaintenanceHead.setHeadId(maintenanceHeadDomain.getReferenceHeadId());
				headCalc.setReferenceMaintenanceHead(referenceMaintenanceHead);
			}
			
			SocietyJPA society = new SocietyJPA();
			society.setSocietyId(maintenanceHeadDomain.getSocietyId());
			
			MaintenanceHeadJPA maintenanceHead = new MaintenanceHeadJPA();
			maintenanceHead.setHeadId(maintenanceHeadDomain.getMaintenanceHeadId());
			maintenanceHead.setHeadName(maintenanceHeadDomain.getMaintenanceHeadName());
			maintenanceHead.setCalculation(headCalc);
			maintenanceHead.setSociety(society);
			
			return maintenanceHeadRepository.saveMaintenanceHead(maintenanceHead);
		}
		return false;
		
	}
	
	public List<MaintenanceHeadDomain> getMaintenanceHeadDomainList(Integer societyId) {
		
		List<MaintenanceHeadJPA> maintenanceHeadListDB = maintenanceHeadRepository.getMaintenanceHeadList(societyId);
		if(CollectionUtils.isEmpty(maintenanceHeadListDB))
			return null;
		
		List<MaintenanceHeadDomain> maintenanceDomainList = new ArrayList<MaintenanceHeadDomain>();
		for(MaintenanceHeadJPA maintenanceHeadDB : maintenanceHeadListDB) {
			
			MaintenanceHeadDomain maintenanceHeadDomain = new MaintenanceHeadDomain();
			maintenanceHeadDomain.setMaintenanceHeadId(maintenanceHeadDB.getHeadId());
			maintenanceHeadDomain.setMaintenanceHeadName(maintenanceHeadDB.getHeadName());
			maintenanceHeadDomain.setCalcId(maintenanceHeadDB.getCalculation().getCalcId());
			maintenanceHeadDomain.setChargeTypeId(maintenanceHeadDB.getCalculation().getCalcType().getCalcTypeId());
			maintenanceHeadDomain.setChargeType(maintenanceHeadDB.getCalculation().getCalcType().getCalcType());
			maintenanceHeadDomain.setFixedAmount(maintenanceHeadDB.getCalculation().getFixedAmount());
			maintenanceHeadDomain.setPercentageAmount(maintenanceHeadDB.getCalculation().getPercentageAmount());
			
			if(maintenanceHeadDB.getCalculation().getReferenceMaintenanceHead() != null) {
				maintenanceHeadDomain.setReferenceHeadName(maintenanceHeadDB.getCalculation().getReferenceMaintenanceHead().getHeadName());
				maintenanceHeadDomain.setReferenceHeadId(maintenanceHeadDB.getCalculation().getReferenceMaintenanceHead().getHeadId());
			}
			maintenanceDomainList.add(maintenanceHeadDomain);
		}
		return maintenanceDomainList;
	}
	
	public boolean deleteMaintenanceHead(MaintenanceHeadDomain maintenanceHeadDomain) {
		
		if(maintenanceHeadDomain != null) {
			MaintenanceHeadJPA maintenanceHead = new MaintenanceHeadJPA();
			maintenanceHead.setHeadId(maintenanceHeadDomain.getMaintenanceHeadId());
			return maintenanceHeadRepository.deleteMaintenanceHead(maintenanceHead);
		}
		return false;
	}
	
	public boolean checkMaintenanceHeadExist(MaintenanceHeadDomain maintenanceHeadDomain) {
		
		MaintenanceHeadJPA maintenanceHead = maintenanceHeadRepository.checkMaintenanceHeadExist(maintenanceHeadDomain);
		if(maintenanceHead == null) 
			return false;
		return true;
	}
}

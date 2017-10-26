package com.society.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.GeneralHeadDomain;
import com.society.model.domain.MaintenanceDomain;
import com.society.model.domain.MaintenancePersonDomain;
import com.society.model.domain.MaintenanceTableDomain;
import com.society.model.jpa.AddressJPA;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.SocietyConfigJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.model.jpa.SocietyMemberJPA;
import com.society.repository.MaintenanceRepository;

@Service
public class MaintenanceService {
	
	@Autowired
	private MaintenanceRepository maintenanceRepository;
	
	
	public List<List<GeneralHeadDomain>> getGeneralHeadList(Integer societyId) {
		
		List<GeneralHeadJPA> generalHeadListDB = maintenanceRepository.getGeneralHeadList(societyId);
		if(CollectionUtils.isEmpty(generalHeadListDB))
			return null;
		
		List<GeneralHeadDomain> generalHeadDominList = new ArrayList<GeneralHeadDomain>(generalHeadListDB.size());
		for(GeneralHeadJPA generalHeadDB : generalHeadListDB){
			
			GeneralHeadDomain generalHeadDomain = new GeneralHeadDomain();
			generalHeadDomain.setGeneralHeadId(generalHeadDB.getGeneralHeadId());
			generalHeadDomain.setGeneralHeadName(generalHeadDB.getGeneralHeadName());
			generalHeadDominList.add(generalHeadDomain);
		}
		
		List<List<GeneralHeadDomain>> generalHeadList = new ArrayList<List<GeneralHeadDomain>>();
		for(int i = 0 ; i < generalHeadDominList.size(); i++) {
			
			List<GeneralHeadDomain> intGeneralHeadDominList = new ArrayList<GeneralHeadDomain>(2);
			
			if(i < generalHeadDominList.size())
				intGeneralHeadDominList.add(generalHeadDominList.get(i));
			
			i = i + 1;
			if(i < generalHeadDominList.size())
				intGeneralHeadDominList.add(generalHeadDominList.get(i));
			
			if(CollectionUtils.isNotEmpty(intGeneralHeadDominList))
				generalHeadList.add(intGeneralHeadDominList);
		}
		return generalHeadList;
	}
	
	public Map<Integer, String> getGenralHeadIdChargeMap(Map<String, String[]> generalHeadIdChargeRequestMap) {
		
		if(generalHeadIdChargeRequestMap == null)
			return null;
		
		Map<Integer, String> generalHeadIdChargeMap = new HashMap<Integer, String>();
		for(Map.Entry<String, String[]> generalHeadIdChargeRequest : generalHeadIdChargeRequestMap.entrySet()) {
			Integer key = Integer.valueOf(generalHeadIdChargeRequest.getKey());
			String value = "";
			if(generalHeadIdChargeRequest.getValue().length > 0)
				 value = generalHeadIdChargeRequest.getValue()[0];
			generalHeadIdChargeMap.put(key, value);
		}
		return generalHeadIdChargeMap;
	}
	
	public MaintenanceTableDomain getMaintenanceTableList(MaintenanceDomain maintenanceDomain, Integer societyId) {
		
		SocietyConfigJPA societyConfig = maintenanceRepository.getSocietyConfigDetail(societyId);
		if(societyConfig == null)
			return null;
		
		List<SocietyMemberJPA> societyMemberList = maintenanceRepository.getSocietyMemberList(societyId);
		if(CollectionUtils.isEmpty(societyMemberList))
			return null;
		
		List<GeneralHeadJPA> generalHeadListDB = maintenanceRepository.getGeneralHeadList(maintenanceDomain.getGeneralHeadChargeMap());
		if(CollectionUtils.isEmpty(generalHeadListDB))
			return null;
		
		List<GeneralHeadDomain> generalHeadDominList = new ArrayList<GeneralHeadDomain>(generalHeadListDB.size());
		for(GeneralHeadJPA generalHeadDB : generalHeadListDB){
			
			GeneralHeadDomain generalHeadDomain = new GeneralHeadDomain();
			generalHeadDomain.setGeneralHeadId(generalHeadDB.getGeneralHeadId());
			generalHeadDomain.setGeneralHeadName(generalHeadDB.getGeneralHeadName());
			generalHeadDominList.add(generalHeadDomain);
		}
		
		List<String> chargeValueList = this.getGeneralHeadChargeValueList(generalHeadDominList, maintenanceDomain.getGeneralHeadChargeMap());
		
		MaintenanceTableDomain maintenanceTable = new MaintenanceTableDomain();
		maintenanceTable.setColumnList(generalHeadDominList);
		maintenanceTable.setSocietyName(societyConfig.getSociety().getSocietyName());
		maintenanceTable.setSocietyAdrress(this.getAddress(societyConfig.getSociety().getAddress()));
		maintenanceTable.setMaintenancePaymentDueInterest(societyConfig.getMaintenancePaymentDueInterest());
		maintenanceTable.setMaintenancePaymentChequeName(societyConfig.getMaintenancePaymentChequeName());
		maintenanceTable.setPaymentDueDate(maintenanceDomain.getPaymentDueDate());
		
		List<MaintenancePersonDomain> memberList = new ArrayList<MaintenancePersonDomain>();
		for(SocietyMemberJPA societyMember : societyMemberList) {
			MaintenancePersonDomain maintenancePersonDomain = new MaintenancePersonDomain();
			maintenancePersonDomain.setName(societyMember.getPerson().getFirstName());
			maintenancePersonDomain.setGeneralHeadValues(chargeValueList);
			memberList.add(maintenancePersonDomain);
		}
		maintenanceTable.setMemberList(memberList);
		return maintenanceTable;
	}
	
	private List<String> getGeneralHeadChargeValueList(List<GeneralHeadDomain> generalHeadDominList, Map<Integer, String> generalHeadIdChargeMap) {
		
		List<String> generalHeadChargeValueList = new ArrayList<String>();
		for(GeneralHeadDomain generalHeadDomain : generalHeadDominList) {
			Integer generalHeadId = generalHeadDomain.getGeneralHeadId();
			String chargeValue = generalHeadIdChargeMap.get(generalHeadId);
			generalHeadChargeValueList.add(chargeValue);
		}
		return generalHeadChargeValueList;
	}
	
	private String getAddress(AddressJPA address) {
		
		if(address == null)
			return null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("Add:");
		sb.append(" Plot no : " + address.getPlotNo());
		sb.append(", Sector : " + address.getSectorNo());
		sb.append(", " + address.getAreaName());
		sb.append(", " + address.getCity() + "-" + address.getPinCode());
		return sb.toString();
	}
}

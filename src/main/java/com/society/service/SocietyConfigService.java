package com.society.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.GeneralHeadDomain;
import com.society.model.domain.GeneralHeadOrderDomain;
import com.society.model.domain.SocietyConfigDomain;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.GeneralHeadOrderJPA;
import com.society.model.jpa.SocietyConfigJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.repository.SocietyConfigRepository;

@Service
public class SocietyConfigService {
	
	@Autowired
	private SocietyConfigRepository societyConfigRepository;
	
	public List<GeneralHeadDomain> getGeneralHeadDomain(String reportName, SocietyConfigDomain societyConfigDomain) {
		
		List<GeneralHeadJPA> generalHeadListDB = societyConfigRepository.getGeneralHeadList(reportName);
		if(CollectionUtils.isEmpty(generalHeadListDB))
			return null;
		
		List<GeneralHeadDomain> generalHeadDomainList = new ArrayList<GeneralHeadDomain>();
		for(GeneralHeadJPA generalHeadDB : generalHeadListDB){
			GeneralHeadDomain generalHead = new GeneralHeadDomain();
			generalHead.setGeneralHeadId(generalHeadDB.getGeneralHeadId());
			generalHead.setGeneralHeadName(generalHeadDB.getGeneralHeadName());
			generalHead.setSectionId(generalHeadDB.getSection().getSectionId());
			generalHead.setSectionName(generalHeadDB.getSection().getSectionName());
			generalHeadDomainList.add(generalHead);
		}
		//Get the order of general head belong to report
		List<GeneralHeadOrderDomain> generalHeadOrderDomainList = this.getGeneralHeadOrder(societyConfigDomain);
		return this.getOrderGeneralHeadDomainList(generalHeadDomainList, generalHeadOrderDomainList);
	}
	
	public List<GeneralHeadOrderDomain> getGeneralHeadOrder(SocietyConfigDomain societyConfigDomian) {
		
		if(societyConfigDomian.getConfigId() == null)
			return null;
			
		List<GeneralHeadOrderJPA> generalHeadOrderList = societyConfigRepository.getGeneralHeadOrder(societyConfigDomian);
		if(CollectionUtils.isEmpty(generalHeadOrderList))
			return null;
		
		List<GeneralHeadOrderDomain> generalHeadOrderDomainList = new ArrayList<GeneralHeadOrderDomain>();
		for(GeneralHeadOrderJPA generalHeadOrderDB : generalHeadOrderList) {
			GeneralHeadOrderDomain generalHeadOrderDomain = new GeneralHeadOrderDomain();
			generalHeadOrderDomain.setOrderId(generalHeadOrderDB.getOrderId());
			generalHeadOrderDomain.setConfigId(generalHeadOrderDB.getSocietyConfig().getConfigId());
			generalHeadOrderDomain.setGeneralHeadId(generalHeadOrderDB.getGeneralHead().getGeneralHeadId());
			generalHeadOrderDomain.setSequnceNumber(generalHeadOrderDB.getSequenceNumber());
			generalHeadOrderDomainList.add(generalHeadOrderDomain);
		}
		return generalHeadOrderDomainList;
	}
	
	private List<GeneralHeadDomain> getOrderGeneralHeadDomainList(List<GeneralHeadDomain> generalHeadDomainList, List<GeneralHeadOrderDomain> generalHeadOrderDomainList) {
		
		if(CollectionUtils.isEmpty(generalHeadOrderDomainList))
			return generalHeadDomainList;
		
		List<GeneralHeadDomain> orderGenealHeadDomainList = new ArrayList<GeneralHeadDomain>();
		for(GeneralHeadOrderDomain generalHeadOrderDomain : generalHeadOrderDomainList) {
			
			GeneralHeadDomain generalHeadDomain = new GeneralHeadDomain();
			for(GeneralHeadDomain genealHeadDomainDB : generalHeadDomainList) {
				
				if(generalHeadOrderDomain.getGeneralHeadId() == genealHeadDomainDB.getGeneralHeadId()) {
					generalHeadDomain.setGeneralHeadId(genealHeadDomainDB.getGeneralHeadId());
					generalHeadDomain.setGeneralHeadName(genealHeadDomainDB.getGeneralHeadName());
					generalHeadDomain.setSectionId(genealHeadDomainDB.getSectionId());
					generalHeadDomain.setSectionName(genealHeadDomainDB.getSectionName());
					generalHeadDomain.setOrderId(generalHeadOrderDomain.getOrderId());
					break;
				}
			}
			orderGenealHeadDomainList.add(generalHeadDomain);
		}
		return orderGenealHeadDomainList;
	}
	
	
	public SocietyConfigDomain getSocietyConfig(Integer societyId) {
		
		SocietyConfigDomain societyConfigDomain = new SocietyConfigDomain();
		
		SocietyConfigJPA societyConfig = societyConfigRepository.getSocietyConfig(societyId);
		if(societyConfig == null)
			return societyConfigDomain;
		
		societyConfigDomain.setConfigId(societyConfig.getConfigId());
		
		return societyConfigDomain;
	}
	
	public boolean insertSocietyConfig(SocietyConfigDomain societyConfigDomain) {
		
		SocietyJPA society = new SocietyJPA();
		society.setSocietyId(societyConfigDomain.getSocietyId());
		
		SocietyConfigJPA societyConfig = new SocietyConfigJPA();
		societyConfig.setConfigId(societyConfigDomain.getConfigId());
		societyConfig.setSociety(society);
		
		List<GeneralHeadOrderJPA> generalHeadOrderList = new ArrayList<GeneralHeadOrderJPA>();
		if(StringUtils.isNotEmpty(societyConfigDomain.getLiabilitesGeneralHeadIds())) {
			
			String[] lcSectionArr = societyConfigDomain.getLiabilitesGeneralHeadIds().split(",");
			String[] lcOrderIdArr = societyConfigDomain.getLiabilitesOrderIds().split(",");
			for(int i = 0; i < lcSectionArr.length; i++) {
				
				String generalHeadId = lcSectionArr[i];
				Integer orderId = StringUtils.equals(lcOrderIdArr[i], "0") ? null : Integer.valueOf(lcOrderIdArr[i]);
				
				GeneralHeadJPA generalHeadJPA = new GeneralHeadJPA();
				generalHeadJPA.setGeneralHeadId(Integer.valueOf(generalHeadId));
				
				GeneralHeadOrderJPA generalHeadOrder = new GeneralHeadOrderJPA();
				generalHeadOrder.setOrderId(orderId);
				generalHeadOrder.setSocietyConfig(societyConfig);
				generalHeadOrder.setGeneralHead(generalHeadJPA);
				generalHeadOrder.setSequenceNumber((i + 1));
				
				generalHeadOrderList.add(generalHeadOrder);
			}
		}
		if(StringUtils.isNotEmpty(societyConfigDomain.getAssetGeneralHeadIds())) {
			
			String[] paSectionArr = societyConfigDomain.getAssetGeneralHeadIds().split(",");
			String[] pasOrderIdArr = societyConfigDomain.getAssetOrderIds().split(",");
			for(int i = 0; i < paSectionArr.length; i++) {
				
				String generalHeadId = paSectionArr[i];
				Integer orderId = StringUtils.equals(pasOrderIdArr[i], "0") ? null : Integer.valueOf(pasOrderIdArr[i]);
				
				GeneralHeadJPA generalHeadJPA = new GeneralHeadJPA();
				generalHeadJPA.setGeneralHeadId(Integer.valueOf(generalHeadId));
				
				GeneralHeadOrderJPA generalHeadOrder = new GeneralHeadOrderJPA();
				generalHeadOrder.setOrderId(orderId);
				generalHeadOrder.setSocietyConfig(societyConfig);
				generalHeadOrder.setGeneralHead(generalHeadJPA);
				generalHeadOrder.setSequenceNumber((i + 1));
				
				generalHeadOrderList.add(generalHeadOrder);
			}
		}
		return societyConfigRepository.saveSocietyConfig(generalHeadOrderList, societyConfig);
	}
	
	
}

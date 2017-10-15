package com.society.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.GeneralHeadDomain;
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
	
	public List<GeneralHeadDomain> getGeneralHeadDomain(String reportName) {
		
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
		
		return generalHeadDomainList;
		
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
		if(StringUtils.isNotEmpty(societyConfigDomain.getLiabilitesGeneralHeadIds())){
			String[] lcSectionArr = societyConfigDomain.getLiabilitesGeneralHeadIds().split(",");
			int i = 1;
			for(String generalHeadId : lcSectionArr) {
				
				GeneralHeadJPA generalHeadJPA = new GeneralHeadJPA();
				generalHeadJPA.setGeneralHeadId(Integer.valueOf(generalHeadId));
				
				GeneralHeadOrderJPA generalHeadOrder = new GeneralHeadOrderJPA();
				generalHeadOrder.setSocietyConfig(societyConfig);
				generalHeadOrder.setGeneralHead(generalHeadJPA);
				generalHeadOrder.setSequenceNumber(++i);
				
				generalHeadOrderList.add(generalHeadOrder);
			}
		}
		if(StringUtils.isNotEmpty(societyConfigDomain.getAssetGeneralHeadIds())) {
			String[] paSectionArr = societyConfigDomain.getAssetGeneralHeadIds().split(",");
			int i = 1;
			for(String generalHeadId : paSectionArr) {
				GeneralHeadJPA generalHeadJPA = new GeneralHeadJPA();
				generalHeadJPA.setGeneralHeadId(Integer.valueOf(generalHeadId));
				
				GeneralHeadOrderJPA generalHeadOrder = new GeneralHeadOrderJPA();
				generalHeadOrder.setSocietyConfig(societyConfig);
				generalHeadOrder.setGeneralHead(generalHeadJPA);
				generalHeadOrder.setSequenceNumber(i++);
				
				generalHeadOrderList.add(generalHeadOrder);
			}
		}
		return societyConfigRepository.saveSocietyConfig(generalHeadOrderList, societyConfig);
	}
}

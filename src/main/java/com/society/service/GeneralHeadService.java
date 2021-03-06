package com.society.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.GeneralHeadDomain;
import com.society.model.domain.SectionDomain;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.GeneralHeadSectionJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.repository.GeneralHeadRepository;

@Service
public class GeneralHeadService {
	
	@Autowired
	private GeneralHeadRepository generalHeadRepository;
	
	@Autowired
	private Mapper mapper;
	
	public List<SectionDomain> getSectionList() {
		List<GeneralHeadSectionJPA> sectionListDB = generalHeadRepository.getSectionList();
		if(sectionListDB == null)
			return null;
					
		List<SectionDomain> sectionList = new ArrayList<SectionDomain>();
		for(GeneralHeadSectionJPA sectionDb : sectionListDB) {
			SectionDomain sectionDomain = new SectionDomain();
			sectionDomain.setSectionId(sectionDb.getSectionId());
			sectionDomain.setSectionName(sectionDb.getSectionName());
			sectionList.add(sectionDomain);
		}
		return sectionList;
	}
	
	public List<GeneralHeadDomain> getGeneralHeadList(Integer societyId) {
		
		List<GeneralHeadJPA> generalHeadList = generalHeadRepository.getGeneralHeadList(societyId);
		if(CollectionUtils.isEmpty(generalHeadList)) 
			return null;
		
		List<GeneralHeadDomain> generalHeadDomainList = new ArrayList<GeneralHeadDomain>();
		for(GeneralHeadJPA generalHead : generalHeadList) {
			GeneralHeadDomain generalHeadDomain = new GeneralHeadDomain();
			generalHeadDomain.setGeneralHeadId(generalHead.getGeneralHeadId());
			generalHeadDomain.setGeneralHeadName(generalHead.getGeneralHeadName());
			generalHeadDomain.setIsDefault(generalHead.getIsDefault());
			generalHeadDomain.setSectionId(generalHead.getSection().getSectionId());
			generalHeadDomain.setSectionName(generalHead.getSection().getSectionName());
			generalHeadDomain.setReportName(generalHead.getSection().getReport().getReportName());
			generalHeadDomainList.add(generalHeadDomain);
		}	
		return generalHeadDomainList;
	}
	
	public boolean insertGeneralHead(GeneralHeadDomain generalHeadDomain) {
		
		if(generalHeadDomain == null)
			return false;
		
		GeneralHeadJPA generalHead = new GeneralHeadJPA();
		generalHead.setGeneralHeadId(generalHeadDomain.getGeneralHeadId());
		generalHead.setGeneralHeadName(generalHeadDomain.getGeneralHeadName());
		
		GeneralHeadSectionJPA generalHeadSection = new GeneralHeadSectionJPA();
		generalHeadSection.setSectionId(generalHeadDomain.getSectionId());
		generalHead.setSection(generalHeadSection);
		
		generalHead.setIsDefault(false);
		
		SocietyJPA society = new SocietyJPA();
		society.setSocietyId(generalHeadDomain.getSocietyId());
		generalHead.setSociety(society);
		
		return generalHeadRepository.insertGeneralHead(generalHead);
	}
	
	public boolean deleteGeneralHead(GeneralHeadDomain generalHeadDomain) {
		
		GeneralHeadJPA generalHead = new GeneralHeadJPA();
		generalHead.setGeneralHeadId(generalHeadDomain.getGeneralHeadId());
		return generalHeadRepository.deleteGeneralHead(generalHead);
	}
	
	public boolean checkGeneralHeadExist(GeneralHeadDomain generalHeadDomain) {
		
		GeneralHeadJPA generalHead = generalHeadRepository.checkGeneralHeadExist(generalHeadDomain);
		if(generalHead == null)
			return false;
		return true;
	}

}

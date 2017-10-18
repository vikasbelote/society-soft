package com.society.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.GeneralHeadDomain;
import com.society.model.jpa.GeneralHeadJPA;
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
}

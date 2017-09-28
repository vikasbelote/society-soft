package com.society.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.GenaralHeadDomain;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.repository.GeneralHeadRepository;

@Service
public class GeneralHeadService {
	
	@Autowired
	private GeneralHeadRepository generalHeadRepository;
	
	@Autowired
	private Mapper mapper;
	
	public boolean insertGeneralHead(GenaralHeadDomain genaralHeadDomain) {
		
		GeneralHeadJPA generalHead = new GeneralHeadJPA();
		return generalHeadRepository.insertGeneralHead(generalHead);
	}

}

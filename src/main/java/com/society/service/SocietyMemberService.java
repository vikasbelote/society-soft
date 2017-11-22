package com.society.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.SocietyMemberDomain;
import com.society.model.jpa.AdditionalAreaJPA;
import com.society.model.jpa.PersonJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.model.jpa.SocietyMemberJPA;
import com.society.repository.SocietyMemberRepository;

@Service
public class SocietyMemberService {
	
	@Autowired
	private SocietyMemberRepository memberRepository;
	
	public List<SocietyMemberDomain> getSocietyMemberList(Integer societyId) {
		
		List<SocietyMemberJPA> memberList = memberRepository.getSocietyMemberList(societyId);
		if(CollectionUtils.isEmpty(memberList))
			return null;
		
		List<SocietyMemberDomain> memberDomainList = new ArrayList<SocietyMemberDomain>();
		for(SocietyMemberJPA member : memberList) {
			SocietyMemberDomain memberDomain = new SocietyMemberDomain();
			memberDomain.setMemberId(member.getMemberId());
			if(member.getPerson() != null) {
				memberDomain.setPersonId(member.getPerson().getPersonId());
				memberDomain.setFirstName(member.getPerson().getFirstName());
				memberDomain.setMiddleName(member.getPerson().getMiddleName());
				memberDomain.setLastName(member.getPerson().getLastName());
				memberDomain.setMobileNumber( member.getPerson().getContactNumber());
				memberDomain.setEmailId(member.getPerson().getEmailId());
			}
			memberDomain.setWingNumber(member.getWingNumber());
			memberDomain.setFlatNumber(member.getFlatNumber());
			memberDomain.setSquareFeet(member.getSquareFeet());
			memberDomainList.add(memberDomain);
		}
		return memberDomainList;
	}
	
	public boolean saveOrUpdateSocietyMemberDetails(SocietyMemberDomain memberDomain) {
		
		PersonJPA person = new PersonJPA();
		person.setFirstName(memberDomain.getFirstName());
		person.setMiddleName(memberDomain.getMiddleName());
		person.setLastName(memberDomain.getLastName());
		person.setContactNumber(memberDomain.getMobileNumber());
		person.setEmailId(memberDomain.getEmailId());
		
		SocietyJPA society = new SocietyJPA();
		society.setSocietyId(memberDomain.getSocietyId());
		
		SocietyMemberJPA member = new SocietyMemberJPA();
		member.setMemberId(memberDomain.getMemberId());
		member.setSociety(society);
		member.setPerson(person);
		member.setWingNumber(memberDomain.getWingNumber());
		member.setFlatNumber(memberDomain.getFlatNumber());
		member.setSquareFeet(memberDomain.getSquareFeet());
		
		if(memberDomain.getAdditionalAreaId() != null && memberDomain.getAdditionalAreaId() != 0) {
			AdditionalAreaJPA additionalArea = new AdditionalAreaJPA();
			additionalArea.setAreaId(memberDomain.getAdditionalAreaId());
			member.setAdditionalArea(additionalArea);
		}
		
		return memberRepository.saveOrUpdateSocietyMemberDetails(member);
	}

	public boolean getSocietyMemberDetails(SocietyMemberDomain memberDomain) {
		
		SocietyMemberJPA member = memberRepository.getSocietyMemberDetails(memberDomain);
		if(member == null)
			return false;
		
		if(member.getPerson() != null) {
			memberDomain.setPersonId(member.getPerson().getPersonId());
			memberDomain.setFirstName(member.getPerson().getFirstName());
			memberDomain.setMiddleName(member.getPerson().getMiddleName());
			memberDomain.setLastName(member.getPerson().getLastName());
			memberDomain.setMobileNumber( member.getPerson().getContactNumber());
			memberDomain.setEmailId(member.getPerson().getEmailId());
		}
		memberDomain.setWingNumber(member.getWingNumber());
		memberDomain.setFlatNumber(member.getFlatNumber());
		memberDomain.setSquareFeet(member.getSquareFeet());
		if(member.getAdditionalArea() != null)
			memberDomain.setAdditionalAreaId(member.getAdditionalArea().getAreaId());
		
		return true;
	}
	
	public boolean deleteMemberDetails(Integer memberId) {
		return memberRepository.deleteObjectById(SocietyMemberJPA.class, memberId);
	}
	
	public boolean checkMemberExist(SocietyMemberDomain memberDomain) {
		
		SocietyMemberJPA member = memberRepository.checkMemberExist(memberDomain);
		if(member == null)
			return true;
		return false;
	}
	
}

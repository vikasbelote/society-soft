package com.society.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.SocietyForm;
import com.society.model.jpa.AdditionalAreaJPA;
import com.society.model.jpa.AddressJPA;
import com.society.model.jpa.PersonJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.model.jpa.SocietyMemberJPA;
import com.society.model.jpa.UserJPA;
import com.society.repository.SocietyRepository;

@Service
public class SocietyService {
	
	@Autowired
	private SocietyRepository societyRepository;
	
	public boolean registerSociety(SocietyForm societyForm) {
		
		AddressJPA address = new AddressJPA();
		address.setAddressText(societyForm.getAddressText());
		address.setAreaName(societyForm.getAreaName());
		address.setPlotNo(Integer.valueOf(societyForm.getPlotNo()));
		address.setSectorNo(Integer.valueOf(societyForm.getSectorNo()));
		address.setCity(societyForm.getCity());
		address.setPinCode(Integer.valueOf(societyForm.getPinCode()));
		address.setState(societyForm.getState());
		
		SocietyJPA society = new SocietyJPA();
		society.setSocietyName(societyForm.getSocietyName());
		society.setAddress(address);
		society.setRegistrationNumber(societyForm.getRegistrationNumber());
		
		PersonJPA person = new PersonJPA();
		person.setFirstName(societyForm.getFirstName());
		person.setMiddleName(societyForm.getMiddleName());
		person.setLastName(societyForm.getLastName());
		person.setContactNumber(societyForm.getContactNumber());
		person.setEmailId(societyForm.getEmailId());
		
		UserJPA user = new UserJPA();
		user.setUserName(societyForm.getUserName());
		user.setUserPassword(societyForm.getUserPassword());
		user.setSociety(society);
		user.setPerson(person);
		
		List<SocietyMemberJPA> societyMemberList = new ArrayList<SocietyMemberJPA>();
		
		int countOfMember = societyForm.getMemberFirstNameArr().length;
		for(int i = 0; i < countOfMember; i++) {
			
			String firstName = societyForm.getMemberFirstNameArr()[i];
			String middleName = societyForm.getMemberMiddleNameArr()[i];
			String lastName = societyForm.getMemberLastNameArr()[i];
			String mobileNumber = societyForm.getMemberContactNumberArr()[i];
			String emailId = societyForm.getMemberEmailIdArr()[i];
			
			String wingNumber = societyForm.getWingNumberArr()[i];
			String flatNumber = societyForm.getFlatNumberArr()[i];
			String squareFeet = societyForm.getSquareFeetArr()[i];
			String additionalAreaId = societyForm.getAdditionalAreaArr()[i];
			
			
			PersonJPA member = new PersonJPA();
			member.setFirstName(firstName);
			member.setMiddleName(middleName);
			member.setLastName(lastName);
			member.setContactNumber(mobileNumber);
			member.setEmailId(emailId);
			
			AdditionalAreaJPA additionalArea = new AdditionalAreaJPA();
			additionalArea.setAreaId(NumberUtils.toInt(additionalAreaId));
			
			SocietyMemberJPA societyMember = new SocietyMemberJPA();
			societyMember.setSociety(society);
			societyMember.setPerson(member);
			societyMember.setWingNumber(wingNumber);
			societyMember.setFlatNumber(flatNumber);
			societyMember.setSquareFeet(NumberUtils.toInt(squareFeet));
			societyMember.setAdditionalArea(additionalArea);
			
			societyMemberList.add(societyMember);
		}
		return societyRepository.insertSocietyDetails(society, user, societyMemberList);
	}
}

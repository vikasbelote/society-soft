package com.society.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.SocietyUserDomain;
import com.society.model.domain.UserDomain;
import com.society.model.jpa.AccessRightsId;
import com.society.model.jpa.PersonJPA;
import com.society.model.jpa.RoleJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.model.jpa.SocietyUserAccessRightsJPA;
import com.society.model.jpa.UserJPA;
import com.society.repository.SocietyUserRepository;

@Service
public class SocietyUserService {
	
	@Autowired
	private SocietyUserRepository societyUserRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	public boolean insertSocietyUserDetails(SocietyUserDomain societyUserDomain) {
		
		PersonJPA person = new PersonJPA();
		person.setPersonId(societyUserDomain.getPersonId());
		person.setFirstName(societyUserDomain.getFirstName());
		person.setMiddleName(societyUserDomain.getMiddleName());
		person.setLastName(societyUserDomain.getLastName());
		person.setContactNumber(societyUserDomain.getContactNumber());
		person.setEmailId(societyUserDomain.getEmailId());
		
		SocietyJPA societyJPA = new SocietyJPA();
		societyJPA.setSocietyId(societyUserDomain.getSocietyId());
		
		RoleJPA role = new RoleJPA();
		if(societyUserDomain.getRoleId() == null) 
			role.setRoleId(new Integer(3));
		else
			role.setRoleId(societyUserDomain.getRoleId());
		
		
		UserJPA user = new UserJPA();
		user.setUserId(societyUserDomain.getUserId());
		user.setUserName(societyUserDomain.getUserName());
		user.setUserPassword(societyUserDomain.getUserPassword());
		user.setPerson(person);
		user.setSociety(societyJPA);
		user.setRole(role);
		
		List<SocietyUserAccessRightsJPA> rightList = new ArrayList<SocietyUserAccessRightsJPA>();
		String[] rights = societyUserDomain.getRights();
		if(rights != null) {
			for(String str : rights) {
				
				Integer menuId = Integer.valueOf(str);
				
				AccessRightsId accessRightsId = new AccessRightsId();
				accessRightsId.setMenuId(menuId);
				
				SocietyUserAccessRightsJPA societyUserAccessRights = new SocietyUserAccessRightsJPA();
				societyUserAccessRights.setAccessRightsId(accessRightsId);
				
				rightList.add(societyUserAccessRights);
			}	
		}
		
		return societyUserRepository.insertSocietyUserDeatils(user, rightList);
	}
	
	public List<UserDomain> getUserList(Integer societyId) {
		
		List<UserJPA> userList = societyUserRepository.getSocietyUserList(societyId);
		
		List<UserDomain> userDomainList = new ArrayList<UserDomain>();
		for(UserJPA user : userList) {
			UserDomain userDomain = mapper.map(user, UserDomain.class);
			userDomainList.add(userDomain);
		}
		return userDomainList;
	}
	
	public SocietyUserDomain getUser(Integer userId) {
		
		UserJPA user = societyUserRepository.getSocietyUser(userId);
		if(user == null)
			return null;
		
		SocietyUserDomain societyUserDomain = new SocietyUserDomain();
		societyUserDomain.setUserId(userId);
		societyUserDomain.setUserName(user.getUserName());
		societyUserDomain.setUserPassword(user.getUserPassword());
		societyUserDomain.setPersonId(user.getPerson().getPersonId());
		societyUserDomain.setFirstName(user.getPerson().getFirstName());
		societyUserDomain.setMiddleName(user.getPerson().getMiddleName());
		societyUserDomain.setLastName(user.getPerson().getLastName());
		societyUserDomain.setContactNumber(user.getPerson().getContactNumber());
		societyUserDomain.setEmailId(user.getPerson().getEmailId());
		societyUserDomain.setRoleId(user.getRole().getRoleId());
		societyUserDomain.setRoleName(user.getRole().getRoleName());
		
		if(societyUserDomain.getRoleName().equals("User")) {
			List<SocietyUserAccessRightsJPA> menuRights = societyUserRepository.getUserRights(userId);
			if(CollectionUtils.isNotEmpty(menuRights)) {
				String[] menuIdArr = new String[menuRights.size()];
				int index = 0;
				for(SocietyUserAccessRightsJPA right : menuRights) {
					menuIdArr[index] = String.valueOf(right.getAccessRightsId().getMenuId());
					index++;
				}
				societyUserDomain.setRights(menuIdArr);
			}
		}
		return societyUserDomain;
	}
	
	public boolean checkUserNameExist(SocietyUserDomain societyUserDomain) {
		
		UserJPA user = societyUserRepository.checkUserExist(societyUserDomain);
		if(user != null)
			return true;
		return false;
	}
}

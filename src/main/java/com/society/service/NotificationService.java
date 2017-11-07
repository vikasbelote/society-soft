package com.society.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.EmailStatusDomain;
import com.society.model.domain.MaintenanceCycleReceiptDomain;
import com.society.model.jpa.EmailStatusJPA;
import com.society.model.jpa.MaintenanceCycleJPA;
import com.society.model.jpa.PersonJPA;
import com.society.repository.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	public List<MaintenanceCycleReceiptDomain> getMaintenacneCycleList(Integer societyId) {
		
		List<MaintenanceCycleJPA> maintenacneCycleList = notificationRepository.getMaintenanceCycleList(societyId);
		if(CollectionUtils.isEmpty(maintenacneCycleList))
			return null;
		
		List<MaintenanceCycleReceiptDomain> cycleList = new ArrayList<MaintenanceCycleReceiptDomain>();
		for(MaintenanceCycleJPA maintenanceCycleDB : maintenacneCycleList) {
			MaintenanceCycleReceiptDomain cycle = new MaintenanceCycleReceiptDomain();
			cycle.setCycleId(maintenanceCycleDB.getCycleId());
			cycle.setStartDate(maintenanceCycleDB.getStartDate());
			cycle.setEndDate(maintenanceCycleDB.getEndDate());
			cycleList.add(cycle);
		}
		return cycleList;
	}
	
	public List<EmailStatusDomain> getEmailStatusList(Integer cycleId) {
		
		List<EmailStatusJPA> emailStatusListDB = notificationRepository.getEmailStatusList(cycleId);
		if(CollectionUtils.isEmpty(emailStatusListDB))
			return null;
		
		List<EmailStatusDomain> emailStatusDomainList = new ArrayList<EmailStatusDomain>();
		for(EmailStatusJPA emailStatusDB : emailStatusListDB) {
			EmailStatusDomain emailStatusDomain = new EmailStatusDomain();
			emailStatusDomain.setMailStatusId(emailStatusDB.getMailStatusId());
			emailStatusDomain.setMemberName(this.getMemberName(emailStatusDB.getReceipt().getMember().getPerson()));
			emailStatusDomain.setMemberId(emailStatusDB.getReceipt().getMember().getMemberId());
			emailStatusDomain.setSendDate(emailStatusDB.getSendDate());
			emailStatusDomain.setMailStatus(emailStatusDB.getMailStatus());
			emailStatusDomain.setBillNumber(String.valueOf(emailStatusDB.getReceipt().getReceipId()));
			emailStatusDomainList.add(emailStatusDomain);
		}
		return emailStatusDomainList;
	}
	
	private String getMemberName(PersonJPA person) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(person.getFirstName() + " ");
		sb.append(person.getLastName());
		return sb.toString();
	}
}

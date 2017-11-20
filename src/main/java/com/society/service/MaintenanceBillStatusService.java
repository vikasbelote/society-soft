package com.society.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.MaintenanceCycleReceiptDomain;
import com.society.model.domain.MaintenanceReceiptDomain;
import com.society.model.jpa.MaintenanceCycleJPA;
import com.society.model.jpa.MaintenanceReceiptJPA;
import com.society.model.jpa.PersonJPA;
import com.society.repository.MaintenanceBillStatusRepository;

@Service
public class MaintenanceBillStatusService {

	@Autowired
	private MaintenanceBillStatusRepository billStatusRepository;
	
	public List<MaintenanceReceiptDomain> getReceiptList(Integer cycleId) {
		
		List<MaintenanceReceiptJPA> receiptList = billStatusRepository.getMaintenanceReceiptList(cycleId);
		if(CollectionUtils.isEmpty(receiptList))
			return null;
					
		List<MaintenanceReceiptDomain> receiptDomainList = new ArrayList<MaintenanceReceiptDomain>();
		for(MaintenanceReceiptJPA receipt : receiptList) {
			
			MaintenanceReceiptDomain receiptDomain = new MaintenanceReceiptDomain();
			receiptDomain.setReceiptId(receipt.getReceipId());
			receiptDomain.setMemberName(this.getPersonName(receipt.getMember().getPerson()));
			receiptDomain.setBillNumber(receipt.getBillNumber());
			receiptDomain.setFlatNumber(receipt.getMember().getFlatNumber());
			receiptDomain.setBillStatus(receipt.getBillStatus());
			receiptDomainList.add(receiptDomain);
		}
		return receiptDomainList;
	}
	
	public MaintenanceCycleReceiptDomain getCycleDetails(Integer cycleId) {
		MaintenanceCycleJPA cycle = billStatusRepository.getCycleDetails(cycleId);
		if(cycle == null)
			return null;
		
		MaintenanceCycleReceiptDomain cycleDomain = new MaintenanceCycleReceiptDomain();
		cycleDomain.setPaymentDueDate(cycle.getPaymentDueDate());
		cycleDomain.setStartDate(cycle.getStartDate());
		cycleDomain.setEndDate(cycle.getEndDate());
		
		List<MaintenanceReceiptDomain> receiptDomainList = new ArrayList<MaintenanceReceiptDomain>();
		for(MaintenanceReceiptJPA receipt : cycle.getReceiptList()) {
			
			MaintenanceReceiptDomain receiptDomain = new MaintenanceReceiptDomain();
			receiptDomain.setReceiptId(receipt.getReceipId());
			receiptDomain.setMemberName(this.getPersonName(receipt.getMember().getPerson()));
			receiptDomain.setBillNumber(receipt.getBillNumber());
			receiptDomain.setFlatNumber(receipt.getMember().getFlatNumber());
			receiptDomain.setBillStatus(receipt.getBillStatus());
			receiptDomain.setTotalValue(receipt.getTotalAmount());
			receiptDomain.setOutstandingAmount(receipt.getOutAmount());
			receiptDomain.setIsActive(receipt.getIsActive());
			receiptDomainList.add(receiptDomain);
		}
		cycleDomain.setReceipts(receiptDomainList);
		return cycleDomain;
	}
	
	public boolean updateBillStatus(MaintenanceCycleReceiptDomain cycle) {
		if(cycle == null)
			return false;
		
		List<MaintenanceReceiptJPA> receiptList = new ArrayList<MaintenanceReceiptJPA>();
		for(MaintenanceReceiptDomain receiptDomain : cycle.getReceipts()) {
			MaintenanceReceiptJPA receipt = new MaintenanceReceiptJPA();
			receipt.setReceipId(receiptDomain.getReceiptId());
			receipt.setBillStatus(receiptDomain.getBillStatus());
			receiptList.add(receipt);
		}
		return billStatusRepository.updateBillStatus(receiptList);
	}
	
	private String getPersonName(PersonJPA person) {
		
		if(person == null)
			return "";
		
		StringBuilder sb = new StringBuilder();
		sb.append(person.getFirstName() + " ");
		sb.append(person.getLastName());
		return sb.toString();
	}
	
}

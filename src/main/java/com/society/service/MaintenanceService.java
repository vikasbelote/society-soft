package com.society.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.domain.EmailDomain;
import com.society.model.domain.GeneralHeadDomain;
import com.society.model.domain.MaintenacneChargeDomain;
import com.society.model.domain.MaintenacneNoteDomain;
import com.society.model.domain.MaintenanceCycleReceiptDomain;
import com.society.model.domain.MaintenanceDomain;
import com.society.model.domain.MaintenanceHeadDomain;
import com.society.model.domain.MaintenancePersonDomain;
import com.society.model.domain.MaintenanceReceiptDomain;
import com.society.model.domain.MaintenanceTableDomain;
import com.society.model.jpa.AddressJPA;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.MaintenanceChargeJPA;
import com.society.model.jpa.MaintenanceCycleJPA;
import com.society.model.jpa.MaintenanceCycleNoteJPA;
import com.society.model.jpa.MaintenanceHeadJPA;
import com.society.model.jpa.MaintenanceReceiptJPA;
import com.society.model.jpa.PersonJPA;
import com.society.model.jpa.SocietyConfigJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.model.jpa.SocietyMemberJPA;
import com.society.repository.MaintenanceHeadRepository;
import com.society.repository.MaintenanceRepository;

@Service
public class MaintenanceService {
	
	@Autowired
	private MaintenanceRepository maintenanceRepository;
	
	@Autowired
	private MaintenanceHeadRepository maintenanceHeadRepository;
	
	public List<String> getCycleDateList(MaintenanceDomain maintenanceDomain, Integer societyId) {
		
		SocietyConfigJPA societyConfig = maintenanceRepository.getSocietyConfigDetail(societyId);
		if(societyConfig == null)
			return null;
		
		Integer addMonth = societyConfig.getMaintenanceCycle();
		Date startDate = societyConfig.getStartDate();
		
		if(addMonth == null || addMonth == 0)
			addMonth = 2;
		if(startDate == null) {
			String str = "2000-04-01";  
			startDate = Date.valueOf(str);
		}
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(startDate);
		
		int startMonth = c.get(Calendar.MONTH) + 1;
		
		java.util.Date currentDate = new java.util.Date();
		c.setTime(currentDate);
		int currentYear = c.get(Calendar.YEAR);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		if(startMonth > currentMonth)
			currentYear = currentYear - 1;
		
		String dateStr = currentYear + "-" + startMonth + "-" + 01;
		Date realStartDate = Date.valueOf(dateStr);
		maintenanceDomain.setPaymentCycleStartDate(realStartDate);
		
		List<String> cycleDateList = new ArrayList<String>();
		Integer cycleCount = 12 / addMonth;
		for(int i = 0; i < cycleCount; i++) {
			
			c.setTimeInMillis(realStartDate.getTime());
			c.add(Calendar.MONTH, addMonth);
			c.add(Calendar.DATE, -1);
			Date endDate = new Date(c.getTimeInMillis());
			
			String cycleDate = String.valueOf(realStartDate) + " to " + String.valueOf(endDate);
			
			c.add(Calendar.DATE, 1);
			realStartDate = new Date(c.getTimeInMillis());
			
			cycleDateList.add(cycleDate);
		}
		return cycleDateList;
	}

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
	
	public Map<Integer, String> getGenralHeadIdChargeMap(Map<String, String[]> generalHeadIdChargeRequestMap) {
		
		if(generalHeadIdChargeRequestMap == null)
			return null;
		
		Map<Integer, String> generalHeadIdChargeMap = new HashMap<Integer, String>();
		for(Map.Entry<String, String[]> generalHeadIdChargeRequest : generalHeadIdChargeRequestMap.entrySet()) {
			Integer key = Integer.valueOf(generalHeadIdChargeRequest.getKey());
			String value = "";
			if(generalHeadIdChargeRequest.getValue().length > 0)
				 value = generalHeadIdChargeRequest.getValue()[0];
			generalHeadIdChargeMap.put(key, value);
		}
		return generalHeadIdChargeMap;
	}
	
	public MaintenanceTableDomain getMaintenanceTableList(MaintenanceDomain maintenanceDomain, Integer societyId) {
		
		List<SocietyMemberJPA> societyMemberList = maintenanceRepository.getSocietyMemberList(societyId);
		if(CollectionUtils.isEmpty(societyMemberList))
			return null;
		
		List<MaintenanceHeadJPA> maintenanceHeadListDB = maintenanceHeadRepository.getMaintenanceHeadList(societyId);
		if(CollectionUtils.isEmpty(maintenanceHeadListDB))
			return null;
		
		MaintenanceTableDomain maintenanceTable = new MaintenanceTableDomain();
		
		List<MaintenanceHeadDomain> maintenanceHeadDomainList = new ArrayList<MaintenanceHeadDomain>();
		for(MaintenanceHeadJPA maintenanceHeadDB : maintenanceHeadListDB) {
			MaintenanceHeadDomain maintenanceHeadDomain = new MaintenanceHeadDomain();
			maintenanceHeadDomain.setMaintenanceHeadId(maintenanceHeadDB.getHeadId());
			maintenanceHeadDomain.setMaintenanceHeadName(maintenanceHeadDB.getHeadName());
			maintenanceHeadDomain.setCalcId(maintenanceHeadDB.getCalculation().getCalcId());
			maintenanceHeadDomain.setChargeTypeId(maintenanceHeadDB.getCalculation().getCalcType().getCalcTypeId());
			maintenanceHeadDomain.setChargeType(maintenanceHeadDB.getCalculation().getCalcType().getCalcType());
			maintenanceHeadDomain.setFixedAmount(maintenanceHeadDB.getCalculation().getFixedAmount());
			maintenanceHeadDomain.setPercentageAmount(maintenanceHeadDB.getCalculation().getPercentageAmount());
			
			if(maintenanceHeadDB.getCalculation().getReferenceMaintenanceHead() != null) {
				maintenanceHeadDomain.setReferenceHeadName(maintenanceHeadDB.getCalculation().getReferenceMaintenanceHead().getHeadName());
				maintenanceHeadDomain.setReferenceHeadId(maintenanceHeadDB.getCalculation().getReferenceMaintenanceHead().getHeadId());
			}
			maintenanceHeadDomainList.add(maintenanceHeadDomain);
		}
		maintenanceTable.setMaintenanceHeadDomainList(maintenanceHeadDomainList);
		
		List<MaintenancePersonDomain> memberList = new ArrayList<MaintenancePersonDomain>();
		for(SocietyMemberJPA societyMember : societyMemberList) {
			
			MaintenancePersonDomain maintenancePersonDomain = new MaintenancePersonDomain();
			maintenancePersonDomain.setMemberId(societyMember.getMemberId());
			maintenancePersonDomain.setName(this.getPersonName(societyMember.getPerson()));
			maintenancePersonDomain.setSquareFeet(societyMember.getSquareFeet());
			maintenancePersonDomain.setMaintenanceHeadChargeDomainList(this.calculateMaintenanceHeadChargeList(maintenancePersonDomain,
					maintenanceHeadDomainList));
			memberList.add(maintenancePersonDomain);
		}
		maintenanceTable.setMemberList(memberList);
		return maintenanceTable;
	}
	
	private List<MaintenacneChargeDomain> calculateMaintenanceHeadChargeList(MaintenancePersonDomain maintenancePersonDomain, 
			List<MaintenanceHeadDomain> maintenanceHeadDomainList) {
		
		List<MaintenacneChargeDomain> maintenanceHeadChargeDomainList = new ArrayList<MaintenacneChargeDomain>();
		for(MaintenanceHeadDomain maintenanceHeadDomain :  maintenanceHeadDomainList) {
			
			MaintenacneChargeDomain maintenacneChargeDomain = new MaintenacneChargeDomain();
			maintenacneChargeDomain.setMaintenanceHeadId(maintenanceHeadDomain.getMaintenanceHeadId());
			maintenacneChargeDomain.setMaintenanceHeadName(maintenanceHeadDomain.getMaintenanceHeadName());
			
			String chargeType = maintenanceHeadDomain.getChargeType();
			if(StringUtils.isNotBlank(chargeType)){
				
				if("No calculation".equals(chargeType)){
					
				}
					
				if("Fixed Amount".equals(chargeType)){
					maintenacneChargeDomain.setChargeValue(maintenanceHeadDomain.getFixedAmount());
				}
						
				if("Per square feet".equals(chargeType)){
					double percentage = maintenanceHeadDomain.getPercentageAmount();
					Integer squeareFeet = maintenancePersonDomain.getSquareFeet();
					maintenacneChargeDomain.setChargeValue(squeareFeet * percentage);
				}
					
				if("Reference Head".equals(chargeType)){
					
				}
			}
			maintenanceHeadChargeDomainList.add(maintenacneChargeDomain);
		}
		return maintenanceHeadChargeDomainList;
	}
	
	/*public boolean saveMaintenanceData(MaintenanceCycleReceiptDomain cycleDomain) {
		
		SocietyJPA society = new SocietyJPA();
		society.setSocietyId(cycleDomain.getSocietyId());
		
		MaintenanceCycleJPA cycle = new MaintenanceCycleJPA();
		cycle.setCycleId(cycleDomain.getCycleId());
		cycle.setSociety(society);
		cycle.setPaymentDueDate(cycleDomain.getPaymentDueDate());
		cycle.setStartDate(cycleDomain.getStartDate());
		cycle.setEndDate(cycleDomain.getEndDate());
		
		List<MaintenanceChargeJPA> chargeList = new ArrayList<MaintenanceChargeJPA>();
		List<MaintenanceReceiptJPA> receiptList = new ArrayList<MaintenanceReceiptJPA>();
		for(MaintenanceReceiptDomain receiptDomain : cycleDomain.getReceipts()) {
			
			SocietyMemberJPA member = new SocietyMemberJPA();
			member.setMemberId(receiptDomain.getMemberId());
			
			MaintenanceReceiptJPA receipt = new MaintenanceReceiptJPA();
			receipt.setReceipId(receiptDomain.getReceiptId());
			receipt.setCycle(cycle);
			receipt.setMember(member);
			if(receipt.getReceipId() != null)
				receipt.setBillNumber(receiptDomain.getBillNumber());
				
			for(MaintenacneChargeDomain maintenacneChargeDomain : receiptDomain.getChargeList()) {
				
				GeneralHeadJPA generalHead = new GeneralHeadJPA();
				generalHead.setGeneralHeadId(maintenacneChargeDomain.getGeneralHeadId());
				
				MaintenanceChargeJPA charge = new MaintenanceChargeJPA();
				charge.setChargeId(maintenacneChargeDomain.getChargeId());
				charge.setReceipt(receipt);
				charge.setGeneralHead(generalHead);
				charge.setChargeValue(maintenacneChargeDomain.getChargeValue());
				
				chargeList.add(charge);
			}
			receiptList.add(receipt);
		}
		List<MaintenanceCycleNoteJPA> noteCycle = null;
		if(CollectionUtils.isNotEmpty(cycleDomain.getNotes())) {
			
		    noteCycle = new ArrayList<MaintenanceCycleNoteJPA>();
			for(MaintenacneNoteDomain note : cycleDomain.getNotes()) {
				
				MaintenanceCycleNoteJPA noteDB = new MaintenanceCycleNoteJPA();
				noteDB.setNoteId(note.getNoteId());
				noteDB.setNoteText(note.getNoteText());
				noteDB.setCycle(cycle);
				noteCycle.add(noteDB);
			}
		}
		List<MaintenanceCycleNoteJPA> noteDBList = null;
		if(StringUtils.isNotEmpty(cycleDomain.getDeleteNoteIds())){
			
			noteDBList = new ArrayList<MaintenanceCycleNoteJPA>();
			String[] deletedNoteIdArr = cycleDomain.getDeleteNoteIds().split(",");
			for(String noteId : deletedNoteIdArr) {
				MaintenanceCycleNoteJPA noteDB = new MaintenanceCycleNoteJPA();
				noteDB.setNoteId(NumberUtils.toInt(noteId));
				noteDBList.add(noteDB);
			}
			
		}
		boolean isSucess = maintenanceRepository.saveMaintenanceData(chargeList, noteCycle, noteDBList);
		if(isSucess && cycleDomain.getCycleId() == null) {
			//Update Bill Number after insert into database
			for(MaintenanceReceiptJPA receipt : receiptList) {
				
				MaintenanceReceiptDomain receiptDomain = null;
				for(MaintenanceReceiptDomain tempReceiptDomain : cycleDomain.getReceipts()) {
					
					if(receipt.getMember().getMemberId().equals(tempReceiptDomain.getMemberId())) {
						receiptDomain = tempReceiptDomain;
						break;
					}
				}
				if(receiptDomain != null) {
					receiptDomain.setBillNumber(receipt.getBillNumber());
				}
			}
		}
		return isSucess;
	}*/
	
	public boolean updateCycle(MaintenanceCycleReceiptDomain cycleDomain) {
		
		SocietyConfigJPA societyConfig = maintenanceRepository.getSocietyConfigDetail(cycleDomain.getSocietyId());
		if(societyConfig == null)
			return false;
		
		cycleDomain.setSocietyName(societyConfig.getSociety().getSocietyName());
		cycleDomain.setAddress((this.getAddress(societyConfig.getSociety().getAddress())));
		cycleDomain.setLateInterestRate(societyConfig.getMaintenancePaymentDueInterest());
		cycleDomain.setChequeName(societyConfig.getMaintenancePaymentChequeName());
		
		Iterator<MaintenanceReceiptDomain> receiptIterator = cycleDomain.getReceipts().iterator();
		while(receiptIterator.hasNext()) {
			MaintenanceReceiptDomain receipt = receiptIterator.next();
			
			Double totalValue = new Double(0);
			for(MaintenacneChargeDomain charge : receipt.getChargeList()) {
				totalValue = totalValue + charge.getChargeValue();
			}
			receipt.setTotalValue(totalValue);
		}
		return true;
	}
	
	public boolean checkPaymentCycleExist(MaintenanceDomain maintenanceDomain) {
		
		List<MaintenanceCycleJPA> maintenanceCycleList = maintenanceRepository.checkPaymentCycleExist(maintenanceDomain);
		if(maintenanceCycleList == null)
			throw new RuntimeException("There is problem with retriving data from database.");
		
		if(CollectionUtils.isEmpty(maintenanceCycleList))
			return false;
		
		//split payment cycle
		String[] selectedCycleDateArr = maintenanceDomain.getPaymentCycle().split("to");
		
		Date selectedCycleStartDate = Date.valueOf(selectedCycleDateArr[0].trim());
		Date selectedCycleEndDate = Date.valueOf(selectedCycleDateArr[1].trim());
		
		boolean isValid = false;
		for(MaintenanceCycleJPA cycle : maintenanceCycleList) {
			
			// check exist between selectedCycleStartDate and selectedCycleEndDate
			if(cycle.getStartDate().after(selectedCycleStartDate) && cycle.getStartDate().before(selectedCycleEndDate)) {
				isValid = true;
				break;
			}
			if(cycle.getEndDate().after(selectedCycleStartDate) && cycle.getEndDate().before(selectedCycleEndDate)){
				isValid = true;
				break;
			}
			
			// check equal selected cycle start date
			if(cycle.getStartDate().equals(selectedCycleStartDate)){
				isValid = true;
				break;
			}
			if(cycle.getEndDate().equals(selectedCycleEndDate)){
				isValid = true;
				break;
			}
		}
		return isValid;
	}
	
	public List<MaintenanceCycleReceiptDomain> getMaintenacneCycleList(Integer societyId) {
		
		List<MaintenanceCycleJPA> maintenacneCycleList = maintenanceRepository.getMaintenanceCycleList(societyId);
		if(CollectionUtils.isEmpty(maintenacneCycleList))
			return null;
		
		List<MaintenanceCycleReceiptDomain> cycleList = new ArrayList<MaintenanceCycleReceiptDomain>();
		for(MaintenanceCycleJPA maintenanceCycleDB : maintenacneCycleList) {
			MaintenanceCycleReceiptDomain cycle = new MaintenanceCycleReceiptDomain();
			cycle.setCycleId(maintenanceCycleDB.getCycleId());
			cycle.setPaymentDueDate(maintenanceCycleDB.getPaymentDueDate());
			cycle.setStartDate(maintenanceCycleDB.getStartDate());
			cycle.setEndDate(maintenanceCycleDB.getEndDate());
			cycleList.add(cycle);
		}
		return cycleList;
	}
	
	/*public MaintenanceCycleReceiptDomain getCycleDetails(Integer cycleId) {
		
		Set<MaintenanceReceiptJPA> receiptSet = maintenanceRepository.getMaintenanceReceipt(cycleId);
		if(CollectionUtils.isEmpty(receiptSet))
			return null;
		
		boolean isCycleUpdated = true;
		MaintenanceCycleReceiptDomain cycle = new MaintenanceCycleReceiptDomain();
		cycle.setCycleId(cycleId);
		
		boolean isGeneralHeadColumnPopulated = true;
		List<GeneralHeadDomain> generalHeadList = new ArrayList<GeneralHeadDomain>();
		cycle.setGeneralHeadList(generalHeadList);
		
		List<MaintenanceReceiptDomain> receiptList = new ArrayList<MaintenanceReceiptDomain>();
		for(MaintenanceReceiptJPA receiptDB : receiptSet) {
			
			if(isCycleUpdated) {
				isCycleUpdated = false;
				cycle.setPaymentDueDate(receiptDB.getCycle().getPaymentDueDate());
				cycle.setStartDate(receiptDB.getCycle().getStartDate());
				cycle.setEndDate(receiptDB.getCycle().getEndDate());
			}
			
			MaintenanceReceiptDomain receipt = new MaintenanceReceiptDomain();
			receipt.setReceiptId(receiptDB.getReceipId());
			receipt.setMemberId(receiptDB.getMember().getMemberId());
			receipt.setMemberName(this.getPersonName(receiptDB.getMember().getPerson()));
			receipt.setEmailId(receiptDB.getMember().getPerson().getEmailId());
			receipt.setBillNumber(receiptDB.getBillNumber());
			
			Double totalValue = new Double(0);
			if(CollectionUtils.isNotEmpty(receiptDB.getChargeList())) {
				List<MaintenacneChargeDomain> chargeList = new ArrayList<MaintenacneChargeDomain>();
				for(MaintenanceChargeJPA chargeDB : receiptDB.getChargeList()) {
					MaintenacneChargeDomain charge = new MaintenacneChargeDomain();
					charge.setChargeId(chargeDB.getChargeId());
					charge.setGeneralHeadId(chargeDB.getGeneralHead().getGeneralHeadId());
					charge.setGeneralHeadName(chargeDB.getGeneralHead().getGeneralHeadName());
					charge.setChargeValue(chargeDB.getChargeValue());
					chargeList.add(charge);
					
					totalValue = totalValue + chargeDB.getChargeValue();
					
					if(isGeneralHeadColumnPopulated) {
						GeneralHeadDomain generalHeadDomain = new GeneralHeadDomain();
						generalHeadDomain.setGeneralHeadId(charge.getGeneralHeadId());
						generalHeadDomain.setGeneralHeadName(charge.getGeneralHeadName());
						generalHeadList.add(generalHeadDomain);
					}
				}
				isGeneralHeadColumnPopulated = false;
				receipt.setChargeList(chargeList);
				receipt.setTotalValue(totalValue);
			}
			receiptList.add(receipt);
		}
		cycle.setReceipts(receiptList);
		
		List<MaintenanceCycleNoteJPA> noteList = maintenanceRepository.getAdditionalNote(cycleId);
		if(CollectionUtils.isNotEmpty(noteList)) {
			List<MaintenacneNoteDomain> additinalNoteList = new ArrayList<MaintenacneNoteDomain>();
			for(MaintenanceCycleNoteJPA cycleNote : noteList) {
				
				MaintenacneNoteDomain note = new MaintenacneNoteDomain();
				note.setNoteId(cycleNote.getNoteId());
				note.setNoteText(cycleNote.getNoteText());
				additinalNoteList.add(note);
			}
			cycle.setNotes(additinalNoteList);
		}
		return cycle;
	}*/
	
	/*public MaintenanceCycleReceiptDomain getMemberCycleDetail(EmailDomain email) {
		
		Set<MaintenanceReceiptJPA> receiptSet = maintenanceRepository.getMemberMaintenanceReceipt(email);
		if(CollectionUtils.isEmpty(receiptSet))
			return null;
		
		boolean isCycleUpdated = true;
		MaintenanceCycleReceiptDomain cycle = new MaintenanceCycleReceiptDomain();
		cycle.setCycleId(email.getCycleId());
		
		boolean isGeneralHeadColumnPopulated = true;
		List<GeneralHeadDomain> generalHeadList = new ArrayList<GeneralHeadDomain>();
		cycle.setGeneralHeadList(generalHeadList);
		
		List<MaintenanceReceiptDomain> receiptList = new ArrayList<MaintenanceReceiptDomain>();
		for(MaintenanceReceiptJPA receiptDB : receiptSet) {
			
			if(isCycleUpdated) {
				isCycleUpdated = false;
				cycle.setPaymentDueDate(receiptDB.getCycle().getPaymentDueDate());
				cycle.setStartDate(receiptDB.getCycle().getStartDate());
				cycle.setEndDate(receiptDB.getCycle().getEndDate());
			}
			
			MaintenanceReceiptDomain receipt = new MaintenanceReceiptDomain();
			receipt.setReceiptId(receiptDB.getReceipId());
			receipt.setMemberId(receiptDB.getMember().getMemberId());
			receipt.setMemberName(this.getPersonName(receiptDB.getMember().getPerson()));
			receipt.setEmailId(receiptDB.getMember().getPerson().getEmailId());
			receipt.setBillNumber(receiptDB.getBillNumber());
			
			Double totalValue = new Double(0);
			if(CollectionUtils.isNotEmpty(receiptDB.getChargeList())) {
				List<MaintenacneChargeDomain> chargeList = new ArrayList<MaintenacneChargeDomain>();
				for(MaintenanceChargeJPA chargeDB : receiptDB.getChargeList()) {
					MaintenacneChargeDomain charge = new MaintenacneChargeDomain();
					charge.setChargeId(chargeDB.getChargeId());
					charge.setGeneralHeadId(chargeDB.getGeneralHead().getGeneralHeadId());
					charge.setGeneralHeadName(chargeDB.getGeneralHead().getGeneralHeadName());
					charge.setChargeValue(chargeDB.getChargeValue());
					chargeList.add(charge);
					
					totalValue = totalValue + chargeDB.getChargeValue();
					
					if(isGeneralHeadColumnPopulated) {
						GeneralHeadDomain generalHeadDomain = new GeneralHeadDomain();
						generalHeadDomain.setGeneralHeadId(charge.getGeneralHeadId());
						generalHeadDomain.setGeneralHeadName(charge.getGeneralHeadName());
						generalHeadList.add(generalHeadDomain);
					}
				}
				isGeneralHeadColumnPopulated = false;
				receipt.setChargeList(chargeList);
				receipt.setTotalValue(totalValue);
			}
			receiptList.add(receipt);
		}
		cycle.setReceipts(receiptList);
		List<MaintenanceCycleNoteJPA> noteList = maintenanceRepository.getAdditionalNote(email.getCycleId());
		if(CollectionUtils.isNotEmpty(noteList)) {
			List<MaintenacneNoteDomain> additinalNoteList = new ArrayList<MaintenacneNoteDomain>();
			for(MaintenanceCycleNoteJPA cycleNote : noteList) {
				
				MaintenacneNoteDomain note = new MaintenacneNoteDomain();
				note.setNoteId(cycleNote.getNoteId());
				note.setNoteText(cycleNote.getNoteText());
				additinalNoteList.add(note);
			}
			cycle.setNotes(additinalNoteList);
		}
		return cycle;
	}*/
	
	public boolean deleteCycle(Integer cycleId) {
		return maintenanceRepository.deleteCycleDetails(cycleId);
	}
	
	/*private List<MaintenacneChargeDomain> getGeneralHeadChargeValueList(List<GeneralHeadDomain> generalHeadDominList, 
			Map<Integer, String> generalHeadIdChargeMap) {
		
		List<MaintenacneChargeDomain> generalHeadChargeValueList = new ArrayList<MaintenacneChargeDomain>();
		for(GeneralHeadDomain generalHeadDomain : generalHeadDominList) {
			
			Integer generalHeadId = generalHeadDomain.getGeneralHeadId();
			MaintenacneChargeDomain maintenacneChargeDomain = new MaintenacneChargeDomain();
			maintenacneChargeDomain.setGeneralHeadId(generalHeadId);

			if(NumberUtils.isNumber(generalHeadIdChargeMap.get(generalHeadId)))
				maintenacneChargeDomain.setChargeValue(Double.valueOf(generalHeadIdChargeMap.get(generalHeadId)));
			else
				maintenacneChargeDomain.setChargeValue(new Double(0));
			
			generalHeadChargeValueList.add(maintenacneChargeDomain);
		}
		return generalHeadChargeValueList;
	}*/
	
	private String getAddress(AddressJPA address) {
		
		if(address == null)
			return null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("Add:");
		sb.append(" Plot no : " + address.getPlotNo());
		sb.append(", Sector : " + address.getSectorNo());
		sb.append(", " + address.getAreaName());
		sb.append(", " + address.getCity() + "-" + address.getPinCode());
		return sb.toString();
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

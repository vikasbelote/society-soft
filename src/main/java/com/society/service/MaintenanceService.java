package com.society.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import com.society.model.jpa.MemberOutAmountJPA;
import com.society.model.jpa.PersonJPA;
import com.society.model.jpa.SocietyConfigJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.model.jpa.SocietyMemberJPA;
import com.society.repository.MaintenanceHeadRepository;
import com.society.repository.MaintenanceRepository;

@Service
public class MaintenanceService {
	
	private static final Logger logger = LogManager.getLogger(MaintenanceService.class);
	
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
		
		Map<Integer, Double> outstandingAmountMap = null;
		
		//DB Object section start
		SocietyJPA society = new SocietyJPA();
		society.setSocietyId(societyId);
		
		MaintenanceCycleJPA cycle = new MaintenanceCycleJPA();
		cycle.setSociety(society);
		cycle.setPaymentDueDate(maintenanceDomain.getPaymentDueDate());
		cycle.setIsActive(true);
		
		if(StringUtils.isNotBlank(maintenanceDomain.getPaymentCycle())) {
			String[] cycleDateArr = maintenanceDomain.getPaymentCycle().split("to");
			String startDateStr = StringUtils.trim(cycleDateArr[0]);
			String endDateStr = StringUtils.trim(cycleDateArr[1]);
			Date startDate = Date.valueOf(startDateStr);
			Date endDate = Date.valueOf(endDateStr);
			
			cycle.setStartDate(startDate);
			cycle.setEndDate(endDate);
			maintenanceDomain.setCycleStartDate(startDate);
			maintenanceDomain.setCycleEndDate(endDate);
			
			outstandingAmountMap = maintenanceRepository.getOutstandingAmount(maintenanceDomain);
		}
		//DB Object section end
		
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
			maintenanceHeadDomain.setHeadCode(maintenanceHeadDB.getHeadCode());
			
			if(maintenanceHeadDB.getCalculation().getReferenceMaintenanceHead() != null) {
				maintenanceHeadDomain.setReferenceHeadName(maintenanceHeadDB.getCalculation().getReferenceMaintenanceHead().getHeadName());
				maintenanceHeadDomain.setReferenceHeadId(maintenanceHeadDB.getCalculation().getReferenceMaintenanceHead().getHeadId());
			}
			maintenanceHeadDomainList.add(maintenanceHeadDomain);
		}
		maintenanceTable.setMaintenanceHeadDomainList(maintenanceHeadDomainList);
		
		//DB Object section start
		List<MaintenanceChargeJPA> chargeList = new ArrayList<MaintenanceChargeJPA>();
		List<MaintenanceReceiptJPA> receiptList = new ArrayList<MaintenanceReceiptJPA>();
		//DB Object section end
		List<MaintenancePersonDomain> memberList = new ArrayList<MaintenancePersonDomain>();
		for(SocietyMemberJPA societyMember : societyMemberList) {
			//DB Object section start
			MaintenanceReceiptJPA receipt = new MaintenanceReceiptJPA();
			receipt.setCycle(cycle);
			receipt.setMember(societyMember);
			//DB Object section end
			MaintenancePersonDomain maintenancePersonDomain = new MaintenancePersonDomain();
			maintenancePersonDomain.setMemberId(societyMember.getMemberId());
			maintenancePersonDomain.setName(this.getPersonName(societyMember.getPerson()));
			maintenancePersonDomain.setSquareFeet(societyMember.getSquareFeet());
			
			//update outstanding amount to display in table
			if(MapUtils.isNotEmpty(outstandingAmountMap)) {
				Double amount = outstandingAmountMap.get(societyMember.getMemberId());
				maintenancePersonDomain.setOutstandingAmount(amount);
				//update db object to save into database
				receipt.setOutAmount(amount);
			}
			
			List<MaintenacneChargeDomain> chargeDomainList = this.calculateMaintenanceHeadChargeList(maintenancePersonDomain, maintenanceHeadDomainList);
			//DB Object section start
			double totalAmount = 0;
			for(MaintenacneChargeDomain maintenacneChargeDomain : chargeDomainList) {
				
				MaintenanceHeadJPA maintenanceHead = new MaintenanceHeadJPA();
				maintenanceHead.setHeadId(maintenacneChargeDomain.getMaintenanceHeadId());
				
				MaintenanceChargeJPA charge = new MaintenanceChargeJPA();
				charge.setReceipt(receipt);
				charge.setMaintenanceHead(maintenanceHead);
				charge.setChargeValue(maintenacneChargeDomain.getChargeValue());
				
				chargeList.add(charge);
				totalAmount = totalAmount + (maintenacneChargeDomain.getChargeValue() == null ? 0 : maintenacneChargeDomain.getChargeValue());
			}
			receipt.setBillStatus(false);
			receipt.setIsActive(true);
			receipt.setTotalAmount(totalAmount);
			receiptList.add(receipt);
			//DB Object section end
			maintenancePersonDomain.setMaintenanceHeadChargeDomainList(chargeDomainList);
			memberList.add(maintenancePersonDomain);
		}
		maintenanceTable.setMemberList(memberList);
		//DB Object section start
		List<MaintenanceCycleNoteJPA> noteCycle = null;
		if(CollectionUtils.isNotEmpty(maintenanceDomain.getAdditionalNote())) {
		    noteCycle = new ArrayList<MaintenanceCycleNoteJPA>();
			for(String note : maintenanceDomain.getAdditionalNote()) {
				MaintenanceCycleNoteJPA noteDB = new MaintenanceCycleNoteJPA();
				noteDB.setNoteText(note);
				noteDB.setCycle(cycle);
				noteCycle.add(noteDB);
			}
			cycle.setNoteList(noteCycle);
		}
		
		boolean isSuccess = maintenanceRepository.saveMaintenanceData(null, chargeList, noteCycle);
		if(isSuccess) {
			maintenanceTable.setCycleId(cycle.getCycleId());
			//update bill number
			for(MaintenanceReceiptJPA receipt : receiptList) {
				MaintenancePersonDomain personDomain = null;
				for(MaintenancePersonDomain tempPersonDomain : memberList) {
					if(receipt.getMember().getMemberId().equals(tempPersonDomain.getMemberId())) {
						personDomain = tempPersonDomain;
						break;
					}
				}
				if(personDomain != null) 
					personDomain.setBillNumber(receipt.getBillNumber());
			}
			
			//update previous bill and cycle is_active flag to false
			maintenanceRepository.updateActiveFlag(maintenanceDomain);
		}
		maintenanceTable.setIsMaintenanceDataSave(isSuccess);
		//DB Object section end
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
	
	
	public boolean saveMaintenanceData(MaintenanceCycleReceiptDomain cycleDomain) {
		return maintenanceRepository.updtaeMaintenanceData(cycleDomain);
	}
	
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
				totalValue = totalValue + (charge.getChargeValue() == null ? 0.0 : charge.getChargeValue());
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
			cycle.setIsActive(maintenanceCycleDB.getIsActive());
			cycleList.add(cycle);
		}
		return cycleList;
	}
	
	
	public File getDownloadReceipt(Integer cycleId, String path) {
		
		Set<MaintenanceReceiptJPA> receiptSet = maintenanceRepository.getMaintenanceReceipt(cycleId);
		if(CollectionUtils.isEmpty(receiptSet)) {
			logger.debug("No Maintenance Receipt Data from Database for cycle id" + cycleId);
			return null;
		}
			
		File receiptFile = new File(path);
		Document document = new Document();
		PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(receiptFile));
		    document.open();
		
		    Font addressFont = new Font();
			addressFont.setSize(10);
			
			Font boldFont = new Font();
			boldFont.setStyle(Font.BOLD);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    String currentDate = formatter.format(Calendar.getInstance().getTime());
			
			for(MaintenanceReceiptJPA receiptDB : receiptSet) {
				
				Paragraph societyName = new Paragraph(receiptDB.getCycle().getSociety().getSocietyName());
				societyName.setAlignment(Element.ALIGN_CENTER);
				societyName.setSpacingAfter(5);
				document.add(societyName);
				
				Paragraph address = new Paragraph(this.getAddress(receiptDB.getCycle().getSociety().getAddress()), addressFont);
				address.setAlignment(Element.ALIGN_CENTER);
				address.setSpacingAfter(5);
				document.add(address);
				
				
				PdfPTable nameAndDateTale = new PdfPTable(2);
				nameAndDateTale.setWidthPercentage(100);
				nameAndDateTale.setSpacingAfter(5);
				
//				Paragraph memberName = new Paragraph("Name : " + this.getPersonName(receiptDB.getMember().getPerson()));
//				memberName.setSpacingAfter(5);
//				document.add(memberName);
				
				PdfPCell memberNameCell = new PdfPCell(new Phrase("Name : " + this.getPersonName(receiptDB.getMember().getPerson())));
				memberNameCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
				memberNameCell.setPadding(0);
				memberNameCell.setBorder(PdfPCell.NO_BORDER);
				nameAndDateTale.addCell(memberNameCell);
				
				PdfPCell currentDateCell = new PdfPCell(new Phrase("Date : " + currentDate));
				currentDateCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
				currentDateCell.setPadding(0);
				currentDateCell.setBorder(PdfPCell.NO_BORDER);
				nameAndDateTale.addCell(currentDateCell);
				
				document.add(nameAndDateTale);
				
				Paragraph billNumber = new Paragraph("Bill Number : " + receiptDB.getBillNumber());
				billNumber.setSpacingAfter(5);
				document.add(billNumber);
				
				Paragraph period = new Paragraph("For the period from " + formatter.format(receiptDB.getCycle().getStartDate()) +" to " + formatter.format(receiptDB.getCycle().getEndDate()));
				period.setSpacingAfter(10);
				document.add(period);

				PdfPTable table = new PdfPTable(3); 
				table.setSpacingAfter(10);
				table.setWidthPercentage(100);

				PdfPCell srNo = new PdfPCell(new Paragraph("Sr No", boldFont));
				PdfPCell generalHead = new PdfPCell(new Paragraph("Particulars", boldFont));
				PdfPCell amount = new PdfPCell(new Paragraph("Amount", boldFont));

				table.addCell(srNo);
				table.addCell(generalHead);
				table.addCell(amount);

				int i = 1;
				double totalChargeValue = 0.0;
				for (MaintenanceChargeJPA charge : receiptDB.getChargeList()) {
					
					if(charge.getChargeValue() == null)
						charge.setChargeValue(new Double(0));
					
					Paragraph srNoPara = new Paragraph(String.valueOf(i++));
					Paragraph generalHeadPara = new Paragraph(charge.getMaintenanceHead().getHeadName());
					Paragraph chargeValuePara = new Paragraph(String.valueOf(charge.getChargeValue()));

					PdfPCell srNoCell = new PdfPCell(srNoPara);
					PdfPCell particularsCell = new PdfPCell(generalHeadPara);
					PdfPCell amonutCell = new PdfPCell(chargeValuePara);

					table.addCell(srNoCell);
					table.addCell(particularsCell);
					table.addCell(amonutCell);
					totalChargeValue = totalChargeValue + charge.getChargeValue();
				}
				
				if(receiptDB.getOutAmount() == null)
					receiptDB.setOutAmount(new Double(0));
				
				PdfPCell srNoCell = new PdfPCell(new Paragraph(String.valueOf(i++)));
				PdfPCell particularsCell = new PdfPCell(new Paragraph("Outstanding Amount"));
				PdfPCell amonutCell = new PdfPCell(new Paragraph(String.valueOf(receiptDB.getOutAmount())));
				
				table.addCell(srNoCell);
				table.addCell(particularsCell);
				table.addCell(amonutCell);
				totalChargeValue = totalChargeValue + receiptDB.getOutAmount();
				
				PdfPCell emptyCell = new PdfPCell(new Paragraph(""));
				PdfPCell totalValueLabel = new PdfPCell(new Paragraph("Toatl Payable Value", boldFont));
				PdfPCell totalValue = new PdfPCell(new Paragraph(String.valueOf(totalChargeValue)));

				table.addCell(emptyCell);
				table.addCell(totalValueLabel);
				table.addCell(totalValue);

				document.add(table);

				Paragraph paymentDueDate = new Paragraph("Payment Due Date : " + formatter.format(receiptDB.getCycle().getPaymentDueDate()));
				paymentDueDate.setSpacingAfter(5);
				document.add(paymentDueDate);

				Paragraph lateInterestCharge = new Paragraph(
						"If the payment is not received on and before due date, Interest @" + receiptDB.getCycle().getSociety().getSocietyConfig().getMaintenancePaymentDueInterest() 
						+ "% p.a. on entire amount wil be applicable");
				lateInterestCharge.setSpacingAfter(5);
				lateInterestCharge.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(lateInterestCharge);

				Paragraph chequeName = new Paragraph("cheque should be drawan in the favour of ");
				chequeName.add(new Chunk("\"" + receiptDB.getCycle().getSociety().getSocietyConfig().getMaintenancePaymentChequeName() + "\"", boldFont));
				chequeName.setSpacingAfter(5);
				document.add(chequeName);
				
				Paragraph note = new Paragraph("Note:");
				document.add(note);
				
				com.itextpdf.text.List list = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
				for(MaintenanceCycleNoteJPA noteDB : receiptDB.getCycle().getNoteList()) {
					list.add(new ListItem(noteDB.getNoteText()));
				}
				document.add(list);
				
				document.newPage();
			}
		}
		catch (DocumentException e) {
			logger.error("DocumentException => " + e.getMessage());
			receiptFile = null;
		}
		catch (FileNotFoundException e) {
			logger.error("FileNotFoundException => " + e.getMessage());
			receiptFile = null;
		}
		catch(Exception e) {
			logger.error("Exception => Receipt generation failed for Member Id : " + e.getMessage());
			receiptFile = null;
		}
		finally {
			document.close();
			if(writer != null)
				writer.close();
		}
		return receiptFile;
	}
	
	public MaintenanceCycleReceiptDomain getCycleDetails(Integer cycleId) {
		return this.getCycleDetails(cycleId, null);
	}
	
	public MaintenanceCycleReceiptDomain getCycleDetails(Integer cycleId, List<Integer> memberIdList) {
		
		Set<MaintenanceReceiptJPA> receiptSet = maintenanceRepository.getMaintenanceReceiptTable(cycleId, memberIdList);
		if(CollectionUtils.isEmpty(receiptSet))
			return null;
		
		
		boolean isCycleUpdated = true;
		MaintenanceCycleReceiptDomain cycle = new MaintenanceCycleReceiptDomain();
		cycle.setCycleId(cycleId);
		
		boolean isGeneralHeadColumnPopulated = true;
		List<MaintenanceHeadDomain> maintenanceHeadList = new ArrayList<MaintenanceHeadDomain>();
		cycle.setMaintenanceHeadList(maintenanceHeadList);
		
		List<MaintenanceReceiptDomain> receiptList = new ArrayList<MaintenanceReceiptDomain>();
		for(MaintenanceReceiptJPA receiptDB : receiptSet) {
			
			if(isCycleUpdated) {
				isCycleUpdated = false;
				cycle.setPaymentDueDate(receiptDB.getCycle().getPaymentDueDate());
				cycle.setStartDate(receiptDB.getCycle().getStartDate());
				cycle.setEndDate(receiptDB.getCycle().getEndDate());
				cycle.setIsActive(receiptDB.getIsActive());
			}
			
			MaintenanceReceiptDomain receipt = new MaintenanceReceiptDomain();
			receipt.setReceiptId(receiptDB.getReceipId());
			receipt.setMemberId(receiptDB.getMember().getMemberId());
			receipt.setMemberName(this.getPersonName(receiptDB.getMember().getPerson()));
			receipt.setEmailId(receiptDB.getMember().getPerson().getEmailId());
			receipt.setBillNumber(receiptDB.getBillNumber());
			receipt.setOutstandingAmount(receiptDB.getOutAmount());
			
			Double totalValue = new Double(0);
			if(CollectionUtils.isNotEmpty(receiptDB.getChargeList())) {
				List<MaintenacneChargeDomain> chargeList = new ArrayList<MaintenacneChargeDomain>();
				for(MaintenanceChargeJPA chargeDB : receiptDB.getChargeList()) {
					MaintenacneChargeDomain charge = new MaintenacneChargeDomain();
					charge.setChargeId(chargeDB.getChargeId());
					charge.setMaintenanceHeadId(chargeDB.getMaintenanceHead().getHeadId());
					charge.setMaintenanceHeadName(chargeDB.getMaintenanceHead().getHeadName());
					charge.setChargeValue(chargeDB.getChargeValue());
					chargeList.add(charge);
					
					totalValue = totalValue + (chargeDB.getChargeValue() == null ? 0.0 : chargeDB.getChargeValue());
					
					if(isGeneralHeadColumnPopulated) {
						MaintenanceHeadDomain maintenanceHeadDomain = new MaintenanceHeadDomain();
						maintenanceHeadDomain.setMaintenanceHeadId(charge.getMaintenanceHeadId());
						maintenanceHeadDomain.setMaintenanceHeadName(charge.getMaintenanceHeadName());
						maintenanceHeadList.add(maintenanceHeadDomain);
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
				note.setNoteText(cycleNote.getNoteText());
				additinalNoteList.add(note);
			}
			cycle.setNotes(additinalNoteList);
		}
		return cycle;
	}
	
	public boolean deleteCycle(Integer cycleId) {
		return maintenanceRepository.deleteCycleDetails(cycleId);
	}
	
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

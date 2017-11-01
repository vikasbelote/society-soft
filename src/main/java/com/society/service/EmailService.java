package com.society.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.society.model.domain.MaintenanceCycleReceiptDomain;
import com.society.model.domain.MaintenanceReceiptDomain;
import com.society.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired MaintenanceService maintenanceService;
	
	private void generateMaintenacneReceipt(MaintenanceReceiptDomain receipt, MaintenanceCycleReceiptDomain cycle) {
		
		Document document = new Document();
	      try {
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
	         document.open();
	         document.add(new Paragraph("A Hello World PDF document."));
	         document.close();
	         writer.close();
	      } catch (DocumentException e){
	         e.printStackTrace();
	      } catch (FileNotFoundException e){
	         e.printStackTrace();
	      }
	}
	
	@Async
	public void sendMail(String rootPath) {
		
		String destPath = rootPath + "/maintenance-receipt/bill.pdf";
		
		MaintenanceCycleReceiptDomain cycle = maintenanceService.getCycleDetails(1);
		if(cycle != null) {
			
			for(MaintenanceReceiptDomain receipt : cycle.getReceipts()) {
				
				
				
				
			}
			
		}
		
//		for(int i = 1; i <= 5; i++) {
//			
//			
//			try {
//				System.out.println(Thread.currentThread().getName());
//				Thread.currentThread().sleep(10000);
//				
//				if(i == 3 && Thread.currentThread().getName().equals("society-1")) {
//					throw new RuntimeException("This is exception throwing");
//				}
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			//EmailJPA email = new EmailJPA();
//			//email.setMemberId(i);
//			//emailRepository.saveEmailInformation(email);
//		}
	}

}

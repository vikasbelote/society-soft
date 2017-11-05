package com.society.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.society.model.domain.EmailDomain;
import com.society.model.domain.MaintenacneChargeDomain;
import com.society.model.domain.MaintenanceCycleReceiptDomain;
import com.society.model.domain.MaintenanceReceiptDomain;

@Service
public class EmailService {

	// @Autowired
	// private EmailRepository emailRepository;
	
	private static final Logger logger = LogManager.getLogger(EmailService.class);

	@Autowired
	private MaintenanceService maintenanceService;
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Async
	public void sendMail(EmailDomain email) {

		String destPath = email.getRootPath() + "/receipt.pdf";
		MaintenanceCycleReceiptDomain cycle = maintenanceService.getCycleDetails(email.getCycleId());
		cycle.setSocietyId(email.getSocietyId());
		
		Date todayDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = sdf.format(todayDate);
		
		if (cycle != null && maintenanceService.updateCycle(cycle)) {
			for (MaintenanceReceiptDomain receipt : cycle.getReceipts()) {
				
				// get maintenance receipt PDF file
				File receiptPdf = this.generateMaintenacneReceipt(receipt, cycle, destPath);
				if(receiptPdf != null) {
					logger.info("Sending mail");
					MimeMessage mimeMessage = mailSender.createMimeMessage();
					try {
						MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, true);
						mailMsg.setFrom("admin@societysoft.com");
						mailMsg.setTo(receipt.getEmailId());
						mailMsg.setSubject("Maintenance Receipt");
						
						StringBuilder sb = new StringBuilder();
						sb.append("Hi " + receipt.getMemberName() + ",\n\n");
						sb.append("This is a notice that an Maintenance Receipt has been generated on " + strDate + ".\n\n");
						sb.append("Amount Due: INR " + receipt.getTotalValue() + "\n\n");
						sb.append("Due Date: " + cycle.getPaymentDueDate() + "\n\n");
						sb.append("Thanks & Regards,\n\n");
						sb.append("Society soft admin team");
						
						mailMsg.setText(sb.toString());
						mailMsg.addAttachment("maintenacne-receipt.pdf", receiptPdf);
								
						mailSender.send(mimeMessage);
						logger.info("Mail Send successfuly.");
					} catch (MessagingException e) {
						logger.error("MessagingException => Mail sending failed for Member Id : " + receipt.getMemberId() + " Member Name : " + receipt.getMemberName());
						logger.error(e.getMessage());
					}
					catch(MailException e) {
						logger.error("MailException => Mail sending failed for Member Id : " + receipt.getMemberId() + " Member Name : " + receipt.getMemberName());
						logger.error(e.getMessage());
					}
			      	catch(Exception e){
			      		logger.error("Exception => Mail sending failed for Member Id : " + receipt.getMemberId() + " Member Name : " + receipt.getMemberName());
			      		logger.error(e.getMessage());
			      	}
			        finally {
			        	receiptPdf.deleteOnExit();
			        }
				}
			}
			//Store the response in table for particular society
			// rowId, memberId, societyId, receiptId,billNumber
		} 
		else {
			logger.error("Probelem with fetching cycle data from Database for societyId : " + email.getSocietyId());
		}
	}

	private File generateMaintenacneReceipt(MaintenanceReceiptDomain receipt, MaintenanceCycleReceiptDomain cycle,
			String destPath) {
		File receiptFile;
		PdfWriter writer = null;
		Document document = new Document();
		try {
			
			receiptFile = new File(destPath);
			
			writer = PdfWriter.getInstance(document, new FileOutputStream(receiptFile));
			document.open();

			Paragraph societyName = new Paragraph(cycle.getSocietyName());
			societyName.setAlignment(Element.ALIGN_CENTER);
			societyName.setSpacingAfter(5);
			document.add(societyName);

			Font addressFont = new Font();
			addressFont.setSize(10);
			Paragraph address = new Paragraph(cycle.getAddress(), addressFont);
			address.setAlignment(Element.ALIGN_CENTER);
			address.setSpacingAfter(5);
			document.add(address);

			Paragraph memberName = new Paragraph("Name : " + receipt.getMemberName());
			memberName.setSpacingAfter(10);
			document.add(memberName);

			PdfPTable table = new PdfPTable(3); 
			table.setSpacingAfter(10);
			table.setWidthPercentage(100);

			Font boldFont = new Font();
			boldFont.setStyle(Font.BOLD);

			PdfPCell srNo = new PdfPCell(new Paragraph("Sr No", boldFont));
			PdfPCell generalHead = new PdfPCell(new Paragraph("Particulars", boldFont));
			PdfPCell amount = new PdfPCell(new Paragraph("Amount", boldFont));

			table.addCell(srNo);
			table.addCell(generalHead);
			table.addCell(amount);

			int i = 1;
			for (MaintenacneChargeDomain charge : receipt.getChargeList()) {

				Paragraph srNoPara = new Paragraph(i++);
				Paragraph generalHeadPara = new Paragraph(charge.getGeneralHeadName());
				Paragraph chargeValuePara = new Paragraph(String.valueOf(charge.getChargeValue()));

				PdfPCell srNoCell = new PdfPCell(srNoPara);
				PdfPCell particularsCell = new PdfPCell(generalHeadPara);
				PdfPCell amonutCell = new PdfPCell(chargeValuePara);

				table.addCell(srNoCell);
				table.addCell(particularsCell);
				table.addCell(amonutCell);
			}

			PdfPCell emptyCell = new PdfPCell(new Paragraph(""));
			PdfPCell totalValueLabel = new PdfPCell(new Paragraph("Toatl Payable Value", boldFont));
			PdfPCell totalValue = new PdfPCell(new Paragraph(String.valueOf(receipt.getTotalValue())));

			table.addCell(emptyCell);
			table.addCell(totalValueLabel);
			table.addCell(totalValue);

			document.add(table);

			Paragraph paymentDueDate = new Paragraph("Payment Due Date : " + cycle.getPaymentDueDate());
			paymentDueDate.setSpacingAfter(5);
			document.add(paymentDueDate);

			Paragraph lateInterestCharge = new Paragraph(
					"If the payment is not received on and before due date, Interest @" + cycle.getLateInterestRate()
							+ "% " + "p.a. on entire amount wil be applicable");
			lateInterestCharge.setSpacingAfter(5);
			lateInterestCharge.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(lateInterestCharge);

			Paragraph chequeName = new Paragraph("cheque should be drawan in the favour of ");
			chequeName.add(new Chunk("\"" + cycle.getChequeName() + "\"", boldFont));
			chequeName.setSpacingAfter(5);
			document.add(chequeName);
			
		} catch (DocumentException e) {
			logger.error("DocimentException => Receipt generation failed for Member Id : " + receipt.getMemberId() + " Member Name : " + receipt.getMemberName());
			receiptFile = null;
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException => Receipt generation failed for Member Id : " + receipt.getMemberId() + " Member Name : " + receipt.getMemberName());
			receiptFile = null;
		}
		catch(Exception e) {
			logger.error("Exception => Receipt generation failed for Member Id : " + receipt.getMemberId() + " Member Name : " + receipt.getMemberName());
			receiptFile = null;
		}
		finally {
			document.close();
			if(writer != null)
				writer.close();
		}
		return receiptFile;
	}
}

package com.society.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.TransactionJPA;
import com.society.model.report.BalanceSheet;
import com.society.model.report.GeneralHeadReportModel;
import com.society.model.report.SectionReportModel;
import com.society.model.report.TransactionReportModel;
import com.society.repository.BalanceSheetRepository;

@Service
public class BalanceSheetService {
	
	@Autowired
	private BalanceSheetRepository balanceSheetRepository;
	
	public BalanceSheet getBalanceSheetData() {
		
		List<GeneralHeadJPA> generalHeadList = balanceSheetRepository.getBalanceSheetData1(1);
		for(GeneralHeadJPA generalHead : generalHeadList) {
			List<TransactionJPA> transactionList = generalHead.getTransactionList();
			System.out.println(transactionList.size());
		}

		GeneralHeadReportModel generalHeadL1 = new GeneralHeadReportModel();
		generalHeadL1.setGeneralHeadName("Development Fund Collection");
		generalHeadL1.setTransactionList(this.getLiabilitisTransactionL1());
		generalHeadL1.setTotalGeneralHeadAmount(new Double(100));

		GeneralHeadReportModel generalHeadL2 = new GeneralHeadReportModel();
		generalHeadL2.setGeneralHeadName("Sinking Fund");
		generalHeadL2.setTransactionList(this.getLiabilitisTransactionL2());
		generalHeadL2.setTotalGeneralHeadAmount(new Double(200));

		GeneralHeadReportModel generalHeadL3 = new GeneralHeadReportModel();
		generalHeadL3.setGeneralHeadName("Currnet Liabilities");
		generalHeadL3.setTransactionList(this.getLiabilitisTransactionL3());
		generalHeadL3.setTotalGeneralHeadAmount(new Double(300));
		
		List<GeneralHeadReportModel> liabilitesGeneralHeadList = new ArrayList<GeneralHeadReportModel>();
		liabilitesGeneralHeadList.add(generalHeadL1);
		liabilitesGeneralHeadList.add(generalHeadL2);
		liabilitesGeneralHeadList.add(generalHeadL3);
		
		GeneralHeadReportModel generalHeadA1 = new GeneralHeadReportModel();
		generalHeadA1.setGeneralHeadName("Fixed Assets");
		generalHeadA1.setTransactionList(this.getAssetsTransactionA1());
		generalHeadA1.setTotalGeneralHeadAmount(new Double(100));
		
		GeneralHeadReportModel generalHeadA2 = new GeneralHeadReportModel();
		generalHeadA2.setGeneralHeadName("Investments");
		generalHeadA2.setTransactionList(this.getAssetsTransactionA2());
		generalHeadA2.setTotalGeneralHeadAmount(new Double(100));
		
		GeneralHeadReportModel generalHeadA3 = new GeneralHeadReportModel();
		generalHeadA3.setGeneralHeadName("Cash & Bank Balance");
		generalHeadA3.setTransactionList(this.getAssetsTransactionA3());
		generalHeadA3.setTotalGeneralHeadAmount(new Double(100));
		
		List<GeneralHeadReportModel> assetsGeneralHeadList = new ArrayList<GeneralHeadReportModel>();
		assetsGeneralHeadList.add(generalHeadA1);
		assetsGeneralHeadList.add(generalHeadA2);
		assetsGeneralHeadList.add(generalHeadA3);
		
		SectionReportModel liabilities = new SectionReportModel();
		liabilities.setCurrentYear("2016-2017");
		liabilities.setPrevYear("2015-2016");
		liabilities.setGrossTotalCurrentYear(new Double(100));
		liabilities.setGrossTotalPrevYear(new Double(100));
		liabilities.setGeneralHeadList(liabilitesGeneralHeadList);
		liabilities.setDefaultTransactionList(this.getDefaultTransactionList());
		
		
		SectionReportModel assets = new SectionReportModel();
		assets.setCurrentYear("2016-2017");
		assets.setPrevYear("2015-2016");
		assets.setGrossTotalCurrentYear(new Double(100));
		assets.setGrossTotalPrevYear(new Double(100));
		assets.setGeneralHeadList(assetsGeneralHeadList);

		BalanceSheet balanceSheet = new BalanceSheet();
		balanceSheet.setLiabilities(liabilities);
		balanceSheet.setAssets(assets);
		balanceSheet.setAsOnDate("31-03-2017");
		return balanceSheet;
	}
	
	private List<TransactionReportModel> getDefaultTransactionList() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Liabilities Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("D" + i);
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transaction.setTotalAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}

	private List<TransactionReportModel> getLiabilitisTransactionL1() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Liabilities Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("L" + i);
			transaction.setCurrentYearAmount(new Double(100 * i * 1));
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}

	private List<TransactionReportModel> getLiabilitisTransactionL2() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Liabilities Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("L" + i);
			transaction.setCurrentYearAmount(new Double(100 * i * 1));
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}

	private List<TransactionReportModel> getLiabilitisTransactionL3() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Liabilities Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("L" + i);
			transaction.setCurrentYearAmount(new Double(100 * i * 1));
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}

	private List<TransactionReportModel> getAssetsTransactionA1() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Assets Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("A" + i);
			transaction.setCurrentYearAmount(new Double(100 * i * 1));
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}
	
	private List<TransactionReportModel> getAssetsTransactionA2() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Assets Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("A" + i);
			transaction.setCurrentYearAmount(new Double(100 * i * 1));
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}
	
	private List<TransactionReportModel> getAssetsTransactionA3() {

		List<TransactionReportModel> transactionList = new ArrayList<TransactionReportModel>();

		// Assets Transaction
		for (int i = 0; i <= 2; i++) {

			TransactionReportModel transaction = new TransactionReportModel();
			transaction.setDescription("A" + i);
			transaction.setCurrentYearAmount(new Double(100 * i * 1));
			transaction.setLastYearAmount(new Double(100 * i * 1));
			transactionList.add(transaction);
		}

		return transactionList;
	}

}

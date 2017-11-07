package com.society.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.jpa.EmailStatusJPA;

@Repository
public class EmailRepository extends BaseRepository {
	
	public boolean saveEmailInformation(List<EmailStatusJPA> emailStatusList) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			String hql = "SELECT mailStatusId FROM EmailStatusJPA WHERE society.societyId = :societyId and receipt.receipId = :receiptId";
			
			Iterator<EmailStatusJPA> emailStatusIterator = emailStatusList.iterator();
			while(emailStatusIterator.hasNext()) {
				
				EmailStatusJPA emailStatus = emailStatusIterator.next();
				if(emailStatus.getMailStatusId() == null) {
					Query query = session.createQuery(hql);
					query.setParameter("societyId", emailStatus.getSociety().getSocietyId());
					query.setParameter("receiptId", emailStatus.getReceipt().getReceipId());
					
					Integer emailStatusId = (Integer)query.uniqueResult();
					emailStatus.setMailStatusId(emailStatusId);
				}
				session.saveOrUpdate(emailStatus);
			}
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e) {
			if(session != null)
				session.getTransaction().rollback();
			return false;
		}
		finally {
			if(session != null)
				session.close();
		}
	}
}

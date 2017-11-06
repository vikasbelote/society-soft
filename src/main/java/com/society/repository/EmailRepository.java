package com.society.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.jpa.EmailJPA;
import com.society.model.jpa.EmailStatusJPA;

@Repository
public class EmailRepository extends BaseRepository {
	
	public boolean saveEmailInformation(List<EmailStatusJPA> emailStatusList) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			for(EmailStatusJPA emailStatus : emailStatusList){
				session.save(emailStatus);
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

package com.society.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.jpa.EmailJPA;

@Repository
public class EmailRepository extends BaseRepository {
	
	public boolean saveEmailInformation(EmailJPA email) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(email);
			
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

package com.society.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.jpa.GeneralHeadJPA;

@Repository
public class GeneralHeadRepository extends BaseRepository {
	
	public boolean insertGeneralHead(GeneralHeadJPA generalHead) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.saveOrUpdate(generalHead);
			
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

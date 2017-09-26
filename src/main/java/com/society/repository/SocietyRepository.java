package com.society.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.jpa.RoleJPA;
import com.society.model.jpa.SocietyJPA;
import com.society.model.jpa.SocietyMemberJPA;
import com.society.model.jpa.UserJPA;

@Repository
public class SocietyRepository extends BaseRepository {
	
	public boolean insertSocietyDetails(SocietyJPA society, UserJPA user, List<SocietyMemberJPA> societyMemberList) {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.persist(society);
			
			RoleJPA role = session.load(RoleJPA.class, new Integer(2));
			user.setRole(role);
			session.persist(user);
			
			for(SocietyMemberJPA societyMember : societyMemberList) {
				session.persist(societyMember);
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

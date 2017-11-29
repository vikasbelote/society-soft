package com.society.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.society.model.jpa.AssetTrackerJPA;

@Repository
public class AdminAssetTrackerRepository extends BaseRepository {
	
	public boolean saveAssetTrackerEntry(AssetTrackerJPA asset) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(asset);
			
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

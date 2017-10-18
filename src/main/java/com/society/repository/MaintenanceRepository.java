package com.society.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.society.constant.SectionEnum;
import com.society.model.jpa.GeneralHeadJPA;
import com.society.model.jpa.GeneralHeadSectionJPA;

@Repository
public class MaintenanceRepository extends BaseRepository {
	
	public List<GeneralHeadJPA> getGeneralHeadList(Integer societyId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneralHeadJPA> criteriaQuery = criteriaBuilder.createQuery(GeneralHeadJPA.class);
		Root<GeneralHeadJPA> root = criteriaQuery.from(GeneralHeadJPA.class);
		Join<GeneralHeadJPA, GeneralHeadSectionJPA> sectionList = root.join("section", JoinType.INNER);
		criteriaQuery.select(root);
		
		Predicate nullSocietyIdPredicate = criteriaBuilder.isNull(root.<Integer>get("society").get("societyId"));
		Predicate equalSocietyIdPredicate = criteriaBuilder.equal(root.<Integer>get("society").get("societyId"), societyId);
		Predicate maintenanceSectionPredicate = criteriaBuilder.equal(sectionList.<String>get("sectionName"), SectionEnum.MA.value());
		
		Predicate orSocietyId = criteriaBuilder.or(nullSocietyIdPredicate, equalSocietyIdPredicate);
		
		Predicate orPredicate = criteriaBuilder.and(orSocietyId, maintenanceSectionPredicate);
		
		criteriaQuery.where(orPredicate);
		
		List<GeneralHeadJPA> generalHeadList;
		try {
			generalHeadList = entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch(Exception e) {
			generalHeadList = null;
		}
		return generalHeadList;
	}

}

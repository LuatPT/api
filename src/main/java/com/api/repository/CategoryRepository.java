package com.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.entity.Category;

@Repository
@Transactional
public class CategoryRepository  {

	@Autowired
	private EntityManager entityManager;
	
	public List<Category> findAll() {
		
//		HQL
//		String sql = "SELECT b FROM Category b";
//		Query query= entityManager.createQuery(sql.toString());
//		Native SQL
		String sql = "SELECT * FROM categorys";
		Query query= entityManager.createNativeQuery(sql.toString());
		List<Category> listCategories = null;
		try {
			listCategories = (List<Category>) query.getResultList();
		} catch (Exception e) {
			
		}
		return listCategories;
	}
	
	public Category findById(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT b ");
		sql.append(" FROM ");
		sql.append("    Category b ");
		sql.append(" WHERE ");
		sql.append("    b.categoryId = :id ");

		Query query = entityManager.createQuery(sql.toString());
		query.setParameter("id", id);
		Category categoryEntity = null;
		try {
			categoryEntity = (Category) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return categoryEntity;
	}

	public void save(Category category) {
//		Add
		entityManager.persist(category);
//		Update
//		entityManager.merge(category);
		
	}

	public void delete(Category category) {
		entityManager.remove(category);
	}

}

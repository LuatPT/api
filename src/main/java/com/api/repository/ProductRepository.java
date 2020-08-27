package com.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.entity.Product;

@Repository
@Transactional
public class ProductRepository  {

	@Autowired
	private EntityManager entityManager;
	
	public List<Product> findAll() {
		
//		HQL
		String sql = "SELECT b FROM Product b";
		Query query= entityManager.createQuery(sql.toString());
		List<Product> list = null;
		try {
			list = (List<Product>) query.getResultList();
		} catch (Exception e) {
			
		}
		return list;
	}
	public Product findById(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT b ");
		sql.append(" FROM ");
		sql.append("    Product b ");
		sql.append(" WHERE ");
		sql.append("    b.productId = :id ");

		Query query = entityManager.createQuery(sql.toString());
		query.setParameter("id", id);
		Product productEntity = null;
		try {
			productEntity = (Product) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return productEntity;
	}

	public void save(Product product) {
//		Add
		entityManager.persist(product);
		
//		Update
//		entityManager.merge(product);
		
	}

	public void delete(Product product) {
		entityManager.remove(product);
		String sql = "DELETE FROM products WHERE product_id= "+product.getProductId();
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
}

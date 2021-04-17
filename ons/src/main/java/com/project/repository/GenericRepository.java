package com.project.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class GenericRepository {

	@PersistenceContext
	protected EntityManager entityManager;
	
	public Object save(Object obj) {
		Object updatedObj = entityManager.merge(obj);
		return updatedObj;
	}
	
	//Customer c = dao.fetch(Customer.class, 123);
	//Order o = dao.fetch(Order.class, 111);
	//Product p = (Product) dao.fetch(Order.class, 111);--->compilation error
	public <E> E fetch(Class<E> clazz, Object pk) { // in place in E anything will be fine
		E e = entityManager.find(clazz, pk);
		return e;
	}
}

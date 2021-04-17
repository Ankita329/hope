package com.project.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends GenericRepository {

	public boolean isUserPresent(String email) {
		return (Long) entityManager.createQuery("Select count(u.id) from User u where u.email = : email")
		                            .setParameter("email", email)
		                            .getSingleResult() == 1 ? true : false;
	}
	
	public int fetchIdByEmailAndPassword(String email, String password) {
		return(Integer) entityManager.createQuery("Select u.id from User u where u.email = :em and u.password = :pass")
				                                    .setParameter("em", email)
				                                    .setParameter("pass", password)
				                                    .getSingleResult();
	}
}

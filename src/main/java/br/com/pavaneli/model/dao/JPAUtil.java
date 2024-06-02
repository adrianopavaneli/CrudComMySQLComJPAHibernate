package br.com.pavaneli.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lojajpa");
	
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}

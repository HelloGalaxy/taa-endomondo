package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BasicManager {
	
	protected EntityManagerFactory emf;
	protected EntityManager em;

	protected void beginPersitence() {
		emf = Persistence.createEntityManagerFactory("endomondo");
		em = emf.createEntityManager();
	}

	protected void endPersitence() {
		em.close();
		emf.close();
	}
}

package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BasicManager {
	
	static public EntityManagerFactory emf;
	static public EntityManager em;

	
	static {
		emf = Persistence.createEntityManagerFactory("endomondo");
		em = emf.createEntityManager();
	}
//	protected void beginPersitence() {
//		emf = Persistence.createEntityManagerFactory("endomondo");
//		em = emf.createEntityManager();
//	}
//
//	protected void endPersitence() {
//		em.close();
//		emf.close();
//	}
}

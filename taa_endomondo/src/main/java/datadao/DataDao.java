package datadao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class DataDao<T> {

	protected EntityManager em;

	protected EntityTransaction tx;

	public DataDao(EntityManager em) {
		this.em = em;
	}
}

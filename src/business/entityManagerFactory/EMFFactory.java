package business.entityManagerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFFactory {
	private static EntityManagerFactory instance;

	public synchronized static EntityManagerFactory getInstance() {
		if (instance == null) 
			instance = Persistence.createEntityManagerFactory("CodeRoyalMarket");
		return instance;
	}

	@Override
	public void finalize() {
		if (instance != null)
			instance.close();
	}
}
package business.entityManagerFactory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMFFactory {
	private static EntityManagerFactory instance;

	public synchronized static EntityManagerFactory getInstance() {
		if (instance == null) instance = Persistence.createEntityManagerFactory("CodeRoyalMarket");
		return instance;
	}

	@Override
	public void finalize() {
		if (instance != null)
			instance.close();
	}
}
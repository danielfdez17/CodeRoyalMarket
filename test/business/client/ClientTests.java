package business.client;

import org.junit.BeforeClass;

import business.businessFactory.BusinessFactory;

public class ClientTests {
	protected static BusinessFactory bf;
	protected static ClientAS clientAS;
	protected ClientTransfer client;
	protected static final String nif = "12345678";
	protected static final double balance = 500;
	
	@BeforeClass
	public static void setUp() {
		bf = BusinessFactory.getInstance();
		clientAS = bf.createClientAS();
	}
}

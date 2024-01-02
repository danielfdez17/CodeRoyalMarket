package utilities;

public class Utils {
	
	// RoyalMarket
	public static final String RoyalMarket = "Royal Market";
	
	// toString()
	public static final String JUMP = "\n";
	
	// Fields names
	public static final String Id = "ID";
	public static final String Name = "Name";
	public static final String Nif = "NIF";
	public static final String Balance = "Balance";
	public static final String Stock = "Stock";
	public static final String Price = "Price";
	public static final String Amount = "Amount";
	public static final String Cost = "Cost";
	public static final String Date = "Date";
	public static final String WarehouseId = "Warehouse " + Id;
	public static final String PhoneNumber = "Phone number";
	public static final String ProviderId = "Provider " + Id;
	public static final String ProductId = "Product " + Id;
	public static final String SaleId = "Sale " + Id;
	public static final String ClientId = "Client " + Id;
	
	// Clients
	public static final String ClientsTitle = "Clients";
	public static final String ClientsHeaders[] = {Id, Nif, Name, Balance};
	public static final String CreateClient = "Create client";
	public static final String ReadClient = "Read client";
	public static final String ReadAllClients = "Read all clients";
	public static final String UpdateClient = "Update client";
	public static final String DeleteClient = "Delete client";
	public static final String ActiveClient = "The client is already active";
	public static final String NonexistentClient = "The client with id %s does not exist";
	public static final String ClientSuccessfullyCreated = "Client successfully created\n";
	public static final String ClientSuccessfullyUpdated = "Client successfully updated\n";
	public static final String ClientSuccessfullyDeactivated = "Client with id %s successfully deactivated";
	public static final String ExistentClient = "The client exists\n";
	
	// Products
	public static final String ProductsTitle = "Products";
	public static final String ProductsHeaders[] = {Id, Name, Stock, Price, WarehouseId};
	public static final String CreateProduct = "Create product";
	public static final String ReadProduct = "Read product";
	public static final String ReadAllProducts = "Read all products";
	public static final String ReadAllProductsByProvider = "Read all products by provider";
	public static final String ReadAllProductsBySale = "Read all products by sale";
	public static final String UpdateProduct = "Update product";
	public static final String DeleteProduct = "Delete product";
	public static final String ProductSuccessfullyCreated = "Product successfully created\n";
	public static final String ProductSuccessfullyUpdated = "Product successfully updated\n";
	public static final String ProductSuccessfullyDeactivated = "Product with id %s successfully deactivated\n";
	public static final String ExistentProduct = "The product exists\n";
	public static final String ActiveProduct = "The product id already active";
	public static final String InactiveProduct = "The product with id %s is inactive";
	public static final String NonexistentProduct = "The product with id %s does not exist";
	public static final String ProductWithActiveProviders = "The product has active providers";
	
	// Providers
	public static final String ProvidersTitle = "Providers";
	public static final String ProvidersHeaders[] = {Id, Name, PhoneNumber};
	public static final String CreateProvider = "Create provider";
	public static final String ReadProvider = "Read provider";
	public static final String ReadAllProviders = "Read all provider";
	public static final String ReadAllProvidersByProduct = "Read all providers by product";
	public static final String AssignProduct = "Assign product";
	public static final String UnassignProduct = "Unassign product";
	public static final String ProvideProduct = "Provide product";
	public static final String UpdateProvider = "Update provider";
	public static final String DeleteProvider = "Delete provider";
	public static final String ProviderSuccessfullyCreated = "Provider successfully created\n";
	public static final String ProviderSuccessfullyUpdated = "Provider successfully updated\n";
	public static final String ProviderSuccessfullyDeactivated = "Provider with id %s successfully deactivated\n";
	public static final String ExistentProvider = "The provider exists\n";
	public static final String NonexistentProvider = "The provider with id %s does not exist\n";
	public static final String ActiveProvider = "The provider is already active";
	public static final String InactiveProvider = "The provider with id %s is inactive";
	public static final String ProductAssigned = "The provider with id %s has assigned the product with id %s";
	public static final String ProductSuccessfullyAssigned = "Product successfully assigned\n";
	public static final String ProductSuccessfullyUnassigned = "Product successfully unassigned\n";
	public static final String ProductSuccessfullyProvided = "Product successfully provided\n";
	public static final String ProductAlreadyUnassigned = "Product already unassigned\n";
	public static final String ActiveProductsAssigned = "The provider with id %a has linked products assigned";
	
	// Sales
	public static final String SalesTitle = "Sales";
	public static final String SalesHeaders[] = {Id, Cost, Date, ClientId};
	public static final String OpenSale = "Open sale";
	public static final String ReadSale = "Read sale";
	public static final String ReadAllSales = "Read all sales";
	public static final String ReadAllSalesByClient = "Read all sales by client";
	public static final String ReadAllSalesByProduct = "Read all sales by product";
	public static final String AddProduct = "Add product";
	public static final String RemoveProduct = "Remove product";
	public static final String ReturnSale = "Return sale";
	public static final String ReturnProduct = "Return product";
	public static final String CloseSale = "Close sale";
	public static final String NonexistentSale = "The sale with id %s does not exist";
	public static final String ExistentSale = "The sale exists";
	
	// Warehouses
	public static final String WarehousesTitle = "Warehouses";
	public static final String CreateWarehouse = "Create warehouse";
	public static final String ReadWarehouse = "Read warehouse";
	public static final String ReadAllWarehouses = "Read all warehouses";
	public static final String UpdateWarehouse = "Update warehouse";
	public static final String DeleteWarehouse = "Delete warehouse";
	public static final String NonexistentWarehouse = "The warehouse with id %s does not exist";
	public static final String InactiveWarehouse = "The warehouse with id %s is inactive";
	
	// Workers
	public static final String WorkersTitle = "Workers";
	public static final String CreateFullTimeWorker = "Create full time worker";
	public static final String CreatePartTimeWorker = "Create part time worker";
	public static final String ReadWorker = "Read worker";
	public static final String ReadAllWorkers = "Read all workers";
	public static final String ReadAllWorkersByWarehouse = "Read all workers by warehouse";
	public static final String UpdateFullTimeWorker = "Udpate full time worker";
	public static final String UpdatePartTimeWorker = "Update part time worker";
	public static final String DeleteWorker = "Delete worker";
	
	
	// General 
	public static final String OnlyNumbersFields = "There are textfields that only require numbers";
	public static final String SomeTextFieldsAreEmpty = "There are empty textfields";
	public static final String EmptyTextFields = "Empty textfields";
	public static final String RestoreValues = "Restore values";
	public static final String NotConsideredResponse = "Response not considered";
	public static final String NotConsideredView = "View not considered";
	public static final String NotConsideredCommand = "Command not considered, add it to the list";
	public static final String SyntaxError = "Syntax error";
	public static final String UnexpectedError = "Unexpected error";
	
}

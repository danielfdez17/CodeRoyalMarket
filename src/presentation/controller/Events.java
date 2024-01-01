package presentation.controller;

public enum Events {
	
	MainGUI,
	
	ClientsGUI,
	
	CreateClientGUI,
	CreateClient,
	CreateClientOK,
	CreateClientKO,
	
	ReadClientGUI,
	ReadClient,
	ReadClientOK,
	ReadClientKO,

	ReadAllClientsGUI,
	ReadAllClients,
	ReadAllClientsOK,
	ReadAllClientsKO,

	UpdateClientGUI,
	UpdateClient,
	UpdateClientOK,
	UpdateClientKO,
	ReadClientForUpdate,
	ReadClientForUpdateOK,
	ReadClientForUpdateKO,

	DeleteClientGUI,
	DeleteClient,
	DeleteClientOK,
	DeleteClientKO,
	
	ProductsGUI,
	
	CreateProductGUI,
	CreateProduct,
	CreateProductOK,
	CreateProductKO,
	
	ReadProductGUI,
	ReadProduct,
	ReadProductOK,
	ReadProductKO,
	
	ReadAllProductsGUI,
	ReadAllProducts,
	ReadAllProductsOK,
	ReadAllProductsKO,
	
	ReadAllProductsByProviderGUI,
	ReadAllProductsByProvider,
	ReadAllProductsByProviderOK,
	ReadAllProductsByProviderKO,
	
	ReadAllProductsBySaleGUI,
	ReadAllProductsBySale,
	ReadAllProductsBySaleOK,
	ReadAllProductsBySaleKO,

	UpdateProductGUI,
	UpdateProduct,
	UpdateProductOK,
	UpdateProductKO,
	ReadProductForUpdate,
	ReadProductForUpdateOK,
	ReadProductForUpdateKO,

	DeleteProductGUI,
	DeleteProduct,
	DeleteProductOK,
	DeleteProductKO,
	
	ProvidersGUI,

	CreateProviderGUI,
	CreateProvider,
	CreateProviderOK,
	CreateProviderKO,
	
	ReadProviderGUI,
	ReadProvider,
	ReadProviderOK,
	ReadProviderKO,

	ReadAllProvidersGUI,
	ReadAllProviders,
	ReadAllProvidersOK,
	ReadAllProvidersKO,

	ReadAllProvidersByProductGUI,
	ReadAllProvidersByProduct,
	ReadAllProvidersByProductOK,
	ReadAllProvidersByProductKO,

	AssignProductGUI,
	AssignProduct,
	AssignProductOK,
	AssignProductKO,

	UnassignProductGUI,
	UnassignProduct,
	UnassignProductOK,
	UnassignProductKO,

	ProvideProductGUI,
	ProvideProduct,
	ProvideProductOK,
	ProvideProductKO,

	UpdateProviderGUI,
	UpdateProvider,
	UpdateProviderOK,
	UpdateProviderKO,
	ReadProviderForUpdate,
	ReadProviderForUpdateOK,
	ReadProviderForUpdateKO,

	DeleteProviderGUI,
	DeleteProviderOK,
	DeleteProviderKO,

	SalesGUI,

	OpenSaleGUI,
	OpenSale,
	OpenSaleOK,
	OpenSaleKO,

	ReadSaleGUI,
	ReadSale,
	ReadSaleOK,
	ReadSaleKO,

	ReadAllSalesGUI,
	ReadAllSales,
	ReadAllSalesOK,
	ReadAllSalesKO,
	
	ReadAllSalesByClientGUI,
	ReadAllSalesByClient,
	ReadAllSalesByClientOK,
	ReadAllSalesByClientKO,

	ReadAllSalesByProductGUI,
	ReadAllSalesByProduct,
	ReadAllSalesByProductOK,
	ReadAllSalesByProductKO,

	AddProductGUI,
	AddProduct,
	AddProductOK,
	AddProductKO,

	RemoveProductGUI,
	RemoveProduct,
	RemoveProductOK,
	RemoveProductKO,

	ReturnSaleGUI,
	ReturnSale,
	ReturnSaleOK,
	ReturnSaleKO,

	ReturnProductGUI,
	ReturnProduct,
	ReturnProductOK,
	ReturnProductKO,

	CLoseSaleGUI,
	CLoseSale,
	CLoseSaleOK,
	CLoseSaleKO,

	WarehousesGUI,

	CreateWarehouseGUI,
	CreateWarehouse,
	CreateWarehouseOK,
	CreateWarehouseKO,

	ReadWarehouseGUI,
	ReadWarehouse,
	ReadWarehouseOK,
	ReadWarehouseKO,

	ReadAllWarehousesGUI,
	ReadAllWarehouses,
	ReadAllWarehousesOK,
	ReadAllWarehousesKO,

	UpdateWarehouseGUI,
	UpdateWarehouse,
	UpdateWarehouseOK,
	UpdateWarehouseKO,
	ReadWarehouseForUpdate,
	ReadWarehouseForUpdateOK,
	ReadWarehouseForUpdateKO,

	DeleteWarehouseGUI,
	DeleteWarehouse,
	DeleteWarehouseOK,
	DeleteWarehouseKO,

	WorkersGUI,

	CreateFullTimeWorkerGUI,
	CreateFullTimeWorker,
	CreateFullTimeWorkerOK,
	CreateFullTimeWorkerKO,

	CreatePartTimeWorkerGUI,
	CreatePartTimeWorker,
	CreatePartTimeWorkerOK,
	CreatePartTimeWorkerKO,

	ReadWorkerGUI,
	ReadWorker,
	ReadWorkerOK,
	ReadWorkerKO,

	ReadAllWorkersGUI,
	ReadAllWorkers,
	ReadAllWorkersOK,
	ReadAllWorkersKO,

	ReadAllWorkersByWarehouseGUI,
	ReadAllWorkersByWarehouse,
	ReadAllWorkersByWarehouseOK,
	ReadAllWorkersByWarehouseKO,

	UpdateFullTimeWorkerGUI,
	UpdateFullTimeWorker,
	UpdateFullTimeWorkerOK,
	UpdateFullTimeWorkerKO,
	
	UpdatePartTimeWorkerGUI,
	UpdatePartTimeWorker,
	UpdatePartTimeWorkerOK,
	UpdatePartTimeWorkerKO,
	
	ReadWorkerForUpdate,
	ReadWorkerForUpdateOK,
	ReadWorkerForUpdateKO,

	DeleteWorkerGUI,
	DeleteWorker,
	DeleteWorkerOK,
	DeleteWorkerKO,
		
}
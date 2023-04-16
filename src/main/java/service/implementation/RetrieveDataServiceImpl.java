package service.implementation;

import apiaccess.ApiAccessService;
import apiaccess.implementation.ApiAccessServiceImpl;
import service.RetrieveDataService;

public class RetrieveDataServiceImpl implements RetrieveDataService {

    private static String URL = "https://fakestoreapi.com/";

    private final ApiAccessService apiAccessService;

    public RetrieveDataServiceImpl() {
        apiAccessService = new ApiAccessServiceImpl();
    }

    @Override
    public String retrieveCartData() {
        return apiAccessService.retrieveData(URL + "carts");
    }

    @Override
    public String retrieveUserData() {
        return apiAccessService.retrieveData(URL + "users");
    }

    @Override
    public String retrieveProductData() {
        return apiAccessService.retrieveData(URL + "products");
    }

}

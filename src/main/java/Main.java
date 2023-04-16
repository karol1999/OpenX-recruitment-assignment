import org.json.JSONArray;
import org.json.JSONObject;
import service.CartProductPriceService;
import service.FurthestAwayUserService;
import service.ProductCategoryService;
import service.RetrieveDataService;
import service.implementation.CartProductPriceServiceImpl;
import service.implementation.FurthestAwayUserServiceImpl;
import service.implementation.ProductCategoryServiceImpl;
import service.implementation.RetrieveDataServiceImpl;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        // first assignment -> Retrieves user, product and shopping cart data #1

        RetrieveDataService retrieveDataService = new RetrieveDataServiceImpl();

        String userData = retrieveDataService.retrieveUserData();
        System.out.println("User Data: ");
        System.out.println(userData);

        String cartData = retrieveDataService.retrieveCartData();
        System.out.println("Cart Data: ");
        System.out.println(cartData);

        String productData = retrieveDataService.retrieveProductData();
        System.out.println("Product Data: ");
        System.out.println(productData);

        // Creates a data structure containing all available product categories and the total value of #2
        // products of a given category #2

        ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();

        Map<String, Double> productCategoryValueMap = productCategoryService.getCategoryProductMap(productData);

        System.out.println("Product Category Data:");
        System.out.println("----------------------");

        for (Map.Entry<String, Double> entry : productCategoryValueMap.entrySet()) {
            System.out.printf("Category: %s, Total Value: $%.2f%n", entry.getKey(), entry.getValue());
        }

        // Finds a cart with the highest value, determines its value and full name of its owner #3

        CartProductPriceService cartProductPriceService = new CartProductPriceServiceImpl();

        Map<Integer, Double> cartIdCartProductPriceMap = cartProductPriceService.getCartIdCartProductPriceMap(cartData);

        System.out.println("Highest price is: " + cartProductPriceService.cartProductHighestPrice(cartIdCartProductPriceMap));
        int idHighestPrice = cartProductPriceService.cartProductHighestPriceId(cartIdCartProductPriceMap);
        System.out.println("ID of highest value: " + idHighestPrice);

        cartProductPriceService.printOwnerHighestPrice(userData, idHighestPrice);

        // Finds the two users living furthest away from each other # 4

        FurthestAwayUserService furthestAwayUserService = new FurthestAwayUserServiceImpl();

        furthestAwayUserService.printUsersFurthestAway(userData);

    }

}


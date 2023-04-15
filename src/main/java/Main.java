import apiaccess.ApiAccessService;
import apiaccess.implementation.ApiAccessServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Main {

    private static final String USERS_URL = "https://fakestoreapi.com/users";
    private static final String PRODUCTS_URL = "https://fakestoreapi.com/products";
    private static final String CARTS_URL = "https://fakestoreapi.com/carts";

    public static void main(String[] args) throws Exception {

        // first assignment -> Retrieves user, product and shopping cart data #1

        ApiAccessService apiAccessService = new ApiAccessServiceImpl();

        String userData = apiAccessService.retrieveData(USERS_URL);
        System.out.println("User Data: ");
        System.out.println(userData);

        String cartData = apiAccessService.retrieveData(CARTS_URL);
        System.out.println("Cart Data: ");
        System.out.println(cartData);

        String productData = apiAccessService.retrieveData(PRODUCTS_URL);
        System.out.println("Product Data: ");
        System.out.println(productData);

        // Creates a data structure containing all available product categories and the total value of #2
        // products of a given category #2

        Map<String, Double> productCategoryValueMap = new HashMap<>();
        Map<Integer, Double> idPriceMap = new HashMap<>(); // for exercise number 3

        JSONArray products = new JSONArray(productData);

        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);

            Integer id = product.getInt("id"); // exercise number 3

            String category = product.getString("category");
            double price = product.getDouble("price");

            idPriceMap.put(id, price); // exercise number 3

            if (productCategoryValueMap.containsKey(category)) {
                double totalValue = productCategoryValueMap.get(category) + price;
                productCategoryValueMap.put(category, totalValue);
            } else {
                productCategoryValueMap.put(category, price);
            }

        }

        System.out.println("Product Category Data:");
        System.out.println("----------------------");

        for (Map.Entry<String, Double> entry : productCategoryValueMap.entrySet()) {
            System.out.printf("Category: %s, Total Value: $%.2f%n", entry.getKey(), entry.getValue());
        }

        // Finds a cart with the highest value, determines its value and full name of its owner #3

        // userId -> cart product price
        Map<Integer, Double> cartIdCartProductPriceMap = new HashMap<>();
        List<Double> priceList = new ArrayList<>();

        JSONArray carts = new JSONArray(cartData);

        double cartProductValue = 0.0;

        for (int i = 0; i < carts.length(); i++) {

            JSONObject cart = carts.getJSONObject(i);
            int cartId = cart.getInt("id");
            JSONArray cartProductArray = cart.getJSONArray("products");

            for (int j = 0; j < cartProductArray.length(); j++) {
                JSONObject cartProductEntity = cartProductArray.getJSONObject(j);

                int quantity = cartProductEntity.getInt("quantity");
                int productId = cartProductEntity.getInt("productId");
                double rawProductPrice = 0.0;
                double productPrice;

                for (Map.Entry<Integer, Double> entry : idPriceMap.entrySet()) {
                    if (entry.getKey() == productId) {
                        rawProductPrice = entry.getValue();
                        break;
                    }
                }

                productPrice = quantity*rawProductPrice;
                priceList.add(productPrice);
            }

            double summedProductPrice = 0.0;
            for(Double price : priceList)
                summedProductPrice += price;

            cartIdCartProductPriceMap.put(cartId, summedProductPrice);
            priceList.clear();

        }

        // int id of max value

        System.out.println("Highest price is: " + Collections.max(cartIdCartProductPriceMap.entrySet(), Map.Entry.comparingByValue()).getValue());
        int idHighestPrice = Collections.max(cartIdCartProductPriceMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("ID of highest value: " + idHighestPrice);

        JSONArray users = new JSONArray(userData);
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);

            if(user.getInt("id") == idHighestPrice) {
                System.out.println("Owner is: First Name: " + user.getJSONObject("name").getString("firstname") +
                        ", Last Name: " + user.getJSONObject("name").getString("lastname"));
                break;
            }

        }




    }
}


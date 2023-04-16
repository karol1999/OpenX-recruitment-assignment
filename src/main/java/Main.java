import org.json.JSONArray;
import org.json.JSONObject;
import service.ProductCategoryService;
import service.RetrieveDataService;
import service.implementation.ProductCategoryServiceImpl;
import service.implementation.RetrieveDataServiceImpl;

import java.util.*;

public class Main {

    private static final double EARTH_RADIUS = 6371.0; // kilometers

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

        // userId -> cart product price
        Map<Integer, Double> cartIdCartProductPriceMap = new HashMap<>();
        List<Double> priceList = new ArrayList<>();

        JSONArray carts = new JSONArray(cartData);

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

                for (Map.Entry<Integer, Double> entry : ProductCategoryServiceImpl.idPriceMap.entrySet()) {
                    if (entry.getKey() == productId) {
                        rawProductPrice = entry.getValue();
                        break;
                    }
                }

                productPrice = quantity * rawProductPrice;
                priceList.add(productPrice);
            }

            double summedProductPrice = 0.0;
            for (Double price : priceList)
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

            if (user.getInt("id") == idHighestPrice) {
                System.out.println("Owner is: First Name: " + user.getJSONObject("name").getString("firstname") +
                        ", Last Name: " + user.getJSONObject("name").getString("lastname"));
                break;
            }

        }

        // Finds the two users living furthest away from each other # 4

        double maxDistance = -1;
        String userName1Max = null;
        String userName2Max = null;

        for (int i = 0; i < users.length() - 1; i++) {
            JSONObject user1 = users.getJSONObject(i);

            String username1 = user1.getString("username");
            double lat1 = user1.getJSONObject("address").getJSONObject("geolocation").getDouble("lat");
            double lon1 = user1.getJSONObject("address").getJSONObject("geolocation").getDouble("long");

            for (int j = i + 1; j < users.length(); j++) {
                JSONObject user2 = users.getJSONObject(j);

                String username2 = user2.getString("username");
                double lat2 = user2.getJSONObject("address").getJSONObject("geolocation").getDouble("lat");
                double lon2 = user2.getJSONObject("address").getJSONObject("geolocation").getDouble("long");

                // calculate distance
                double distance = calculateDistance(lat1, lon1, lat2, lon2);

                if (distance > maxDistance) {
                    maxDistance = distance;
                    userName1Max = username1;
                    userName2Max = username2;
                }

            }
        }

        System.out.println();

        System.out.println("User 1: " + userName1Max);
        System.out.println("User 2: " + userName2Max);
        System.out.println("Distance: " + maxDistance);


    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;

    }
}


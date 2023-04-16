package service.implementation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import service.CartProductPriceService;

import java.util.*;

public class CartProductPriceServiceImpl implements CartProductPriceService {

    @Override
    public Map<Integer, Double> getCartIdCartProductPriceMap(String cartData) {

        JSONArray carts;
        List<Double> priceList;
        Map<Integer, Double> cartIdCartProductPriceMap;

        try {

            carts = new JSONArray(cartData);
            priceList = new ArrayList<>();
            cartIdCartProductPriceMap = new HashMap<>();

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
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return cartIdCartProductPriceMap;
    }

    @Override
    public Double cartProductHighestPrice(Map<Integer, Double> cartIdCartProductPriceMap) {
        return Collections.max(cartIdCartProductPriceMap.entrySet(), Map.Entry.comparingByValue()).getValue();
    }

    @Override
    public int cartProductHighestPriceId(Map<Integer, Double> cartIdCartProductPriceMap) {
        return Collections.max(cartIdCartProductPriceMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    @Override
    public void printOwnerHighestPrice(String userData, int idHighestPrice) {

        JSONArray users = null;
        try {
            users = new JSONArray(userData);

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);

                if (user.getInt("id") == idHighestPrice) {
                    System.out.println("Owner is: First Name: " + user.getJSONObject("name").getString("firstname") +
                            ", Last Name: " + user.getJSONObject("name").getString("lastname"));
                    break;
                }

            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

}

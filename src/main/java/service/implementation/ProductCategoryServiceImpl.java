package service.implementation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import service.ProductCategoryService;

import java.util.HashMap;
import java.util.Map;

public class ProductCategoryServiceImpl implements ProductCategoryService {

    public static Map<Integer, Double> idPriceMap = new HashMap<>();

    @Override
    public Map<String, Double> getCategoryProductMap(String productData) {

        JSONArray products;
        Map<String, Double> productCategoryValueMap;

        try {

            products = new JSONArray(productData);
            productCategoryValueMap = new HashMap<>();

            for (int i = 0; i < products.length(); i++) {
                JSONObject product = products.getJSONObject(i);

                Integer id = product.getInt("id");
                String category = product.getString("category");
                double price = product.getDouble("price");

                idPriceMap.put(id, price);

                if (productCategoryValueMap.containsKey(category)) {
                    double totalValue = productCategoryValueMap.get(category) + price;
                    productCategoryValueMap.put(category, totalValue);
                } else {
                    productCategoryValueMap.put(category, price);
                }

            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return productCategoryValueMap;
    }

}

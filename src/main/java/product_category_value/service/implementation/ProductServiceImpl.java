package product_category_value.service.implementation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import product_category_value.model.ProductDataModel;
import product_category_value.service.ProductService;

import java.util.Map;

public class ProductServiceImpl implements ProductService {

    @Override
    public void processProductData(String productData) throws JSONException {
        ProductDataModel productDataModel = new ProductDataModel();
        JSONArray products = new JSONArray(productData);

        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);

            Integer id = product.getInt("id");
            String category = product.getString("category");
            double price = product.getDouble("price");

            productDataModel.addToIdPriceMap(id, price);
            productDataModel.addToProductCategoryValueMap(category, price);
        }

        System.out.println("Product Category Data:");
        System.out.println("----------------------");

        for (Map.Entry<String, Double> entry : productDataModel.getProductCategoryValueMap().entrySet()) {
            System.out.printf("Category: %s, Total Value: $%.2f%n", entry.getKey(), entry.getValue());
        }
    }

}

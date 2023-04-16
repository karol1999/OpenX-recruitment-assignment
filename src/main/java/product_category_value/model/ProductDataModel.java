package product_category_value.model;

import java.util.HashMap;
import java.util.Map;

public class ProductDataModel {

    private Map<String, Double> productCategoryValueMap;
    private Map<Integer, Double> idPriceMap; // # for exercise number 3

    public ProductDataModel() {
        productCategoryValueMap = new HashMap<>();
        idPriceMap = new HashMap<>();
    }

    public Map<String, Double> getProductCategoryValueMap() {
        return productCategoryValueMap;
    }

    public Map<Integer, Double> getIdPriceMap() {
        return idPriceMap;
    }

    public void addToProductCategoryValueMap(String category, double price) {
        if (productCategoryValueMap.containsKey(category)) {
            double totalValue = productCategoryValueMap.get(category) + price;
            productCategoryValueMap.put(category, totalValue);
        } else {
            productCategoryValueMap.put(category, price);
        }
    }

    public void addToIdPriceMap(Integer id, double price) {
        idPriceMap.put(id, price);
    }

}

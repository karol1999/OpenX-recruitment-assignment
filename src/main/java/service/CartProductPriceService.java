package service;

import java.util.Map;

public interface CartProductPriceService {

    Map<Integer, Double> getCartIdCartProductPriceMap(String productData);

    Double cartProductHighestPrice(Map<Integer, Double> cartIdCartProductPriceMap);

    int cartProductHighestPriceId(Map<Integer, Double> cartIdCartProductPriceMap);

    void printOwnerHighestPrice(String userData, int idHighestPrice);
}

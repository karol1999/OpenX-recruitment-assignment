package service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductCategoryServiceImplTest {

    @InjectMocks
    ProductCategoryServiceImpl productCategoryService;

    @Test
    void getCategoryProductMap() {
        // given
        String retrieveData = "[{\"id\": 1, \"category\": \"cat1\", \"price\": 10}, {\"id\": 2,\"category\": \"cat1\", \"price\": 5}]";

        // when
        Map<String, Double> resultCategoryProductMap = productCategoryService.getCategoryProductMap(retrieveData);

        // then
        assertEquals(resultCategoryProductMap.get("cat1"), 15.0);
    }

}
package service.implementation;

import apiaccess.implementation.ApiAccessServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveDataServiceImplTest {

    private static String URL = "https://fakestoreapi.com/";

    @Mock
    private ApiAccessServiceImpl apiAccessService;

    @InjectMocks
    private RetrieveDataServiceImpl retrieveDataService;

    @Test
    void retrieveCartData() {
        // given
        // String retrieveData = "Cart: cart 1";
        // when(apiAccessService.retrieveData(URL + "carts")).thenReturn(retrieveData);

        // when
        // String result = retrieveDataService.retrieveCartData();

        // then
        // assertEquals(result, retrieveData);
    }

    @Test
    void retrieveUserData() {
        // given
        // String retrieveData = "User: user 1";
        // when(apiAccessService.retrieveData(URL + "users")).thenReturn(retrieveData);

        // when
        // String result = retrieveDataService.retrieveCartData();

        // then
        // assertEquals(result, retrieveData);
    }

    @Test
    void retrieveProductData() {
        // given
        // String retrieveData = "Product: product 1";
        // when(apiAccessService.retrieveData(URL + "products")).thenReturn(retrieveData);

        // when
        // String result = retrieveDataService.retrieveCartData();

        // then
        // assertEquals(result, retrieveData);
    }
}
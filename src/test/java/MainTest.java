import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import apiaccess.ApiAccessService;

public class MainTest {

    private static final String USERS_URL = "https://fakestoreapi.com/users";
    private static final String PRODUCTS_URL = "https://fakestoreapi.com/products";
    private static final String CARTS_URL = "https://fakestoreapi.com/carts";

    @BeforeEach
    void setUp() {
        // create a mock instance of ApiAccessService
        ApiAccessService apiAccessService = mock(ApiAccessService.class);
        Main main = new Main();
    }

//    @Test
//    void testRetrieveUserData() throws Exception {
//
//        String expectedUserData = "User data from API";
//
//        // Mock the retrieveData() method of ApiAccessService to return the expected response data
//        when(apiAccessService.retrieveData(USERS_URL)).thenReturn(expectedUserData);
//
//        // Call the retrieveUserData() method in Main class
//        String userData = Main.retrieveUserData(apiAccessService);
//
//        // Assert that the returned userData matches the expectedUserData
//        assertEquals(expectedUserData, userData);
//
//    }

//    @Test
//    void testRetrieveUserData() throws Exception {
//        // Define the expected response data
//        String expectedUserData = "User data from API";
//
//        // Mock the retrieveData() method of ApiAccessService to return the expected response data
//        when(apiAccessService.retrieveData(USERS_URL)).thenReturn(expectedUserData);
//
//        // Use reflection to access and invoke the private method retrieveUserData()
//        Method retrieveUserDataMethod = Main.class.getDeclaredMethod("retrieveUserData", ApiAccessService.class);
//        retrieveUserDataMethod.setAccessible(true); // Set the method to accessible
//        String userData = (String) retrieveUserDataMethod.invoke(main, apiAccessService);
//
//        // Assert that the returned userData matches the expectedUserData
//        assertEquals(expectedUserData, userData);
//    }


}

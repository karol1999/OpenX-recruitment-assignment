import apiaccess.implementation.ApiAccessServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ApiAccessServiceImplTest {

    @Mock
    private HttpURLConnection connection;

    @Mock
    private URL url;

    private ApiAccessServiceImpl apiAccessService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        apiAccessService = new ApiAccessServiceImpl();
    }

    @Test
    void testRetrieveData_Success() throws Exception {

        String expectedData = "Mocked response data";

        when(url.openConnection()).thenReturn(connection);
        when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        InputStream inputStream = new ByteArrayInputStream(expectedData.getBytes());
        when(connection.getInputStream()).thenReturn(inputStream);

        String actualData = apiAccessService.retrieveData("https://fakestoreapi.com/users");

        assertEquals(expectedData, actualData);
    }

    @Test
    void testRetrieveData_Failure() throws Exception {
        when(url.openConnection()).thenReturn(connection);
        when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_BAD_REQUEST);

        assertThrows(Exception.class, () -> {
            apiAccessService.retrieveData("https://example.com");
        });
    }

}

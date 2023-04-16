package apiaccess.implementation;

import apiaccess.ApiAccessService;
import exception.DataRetrievalException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiAccessServiceImpl implements ApiAccessService {

    public String retrieveData(String urlString) {

        try {

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                return content.toString();
            } else {
                throw new DataRetrievalException("Failed to retrieve data from " + urlString + ". Response Code: " +
                        responseCode);
            }

        } catch (IOException exception) {
            throw new RuntimeException("Exception was thrown: " + exception.getMessage());
        }

    }

}

package service.implementation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import service.FurthestAwayUserService;

public class FurthestAwayUserServiceImpl implements FurthestAwayUserService {

    private static final double EARTH_RADIUS = 6371.0; // kilometers

    @Override
    public void printUsersFurthestAway(String userData) {

        JSONArray users = null;

        try {

            users = new JSONArray(userData);

            double maxDistance = -1;
            String userName1Max = null;
            String userName2Max = null;

            for (int i = 0; i < users.length() - 1; i++) {
                JSONObject user1 = users.getJSONObject(i);

                String username1 = user1.getString("username");
                double lat1 = user1.getJSONObject("address").getJSONObject("geolocation").getDouble("lat");
                double lon1 = user1.getJSONObject("address").getJSONObject("geolocation").getDouble("long");

                for (int j = i + 1; j < users.length(); j++) {
                    JSONObject user2 = users.getJSONObject(j);

                    String username2 = user2.getString("username");
                    double lat2 = user2.getJSONObject("address").getJSONObject("geolocation").getDouble("lat");
                    double lon2 = user2.getJSONObject("address").getJSONObject("geolocation").getDouble("long");

                    // calculate distance
                    double distance = getDistance(lat1, lon1, lat2, lon2);

                    if (distance > maxDistance) {
                        maxDistance = distance;
                        userName1Max = username1;
                        userName2Max = username2;
                    }

                }
            }

            System.out.println("User 1: " + userName1Max);
            System.out.println("User 2: " + userName2Max);
            System.out.println("Distance: " + maxDistance);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

}

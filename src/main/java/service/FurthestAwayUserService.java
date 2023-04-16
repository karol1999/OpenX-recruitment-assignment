package service;

public interface FurthestAwayUserService {
    void printUsersFurthestAway(String userData);

    double getDistance(double lat1, double lon1, double lat2, double lon2);
}

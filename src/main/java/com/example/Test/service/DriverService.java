package com.example.Test.service;

import com.example.Test.models.DriverDTO;
import com.example.Test.models.Location;

public interface DriverService {
    void createDriver(DriverDTO driverDetails);

    void updateLocation(String driverId, Location location);

    void switchAvailability(String driverId, boolean isAvailable);
}

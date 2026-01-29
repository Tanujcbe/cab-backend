package com.example.Test.service.serivceImpl;

import com.example.Test.db.InMemoryDB;
import com.example.Test.models.DriverDTO;
import com.example.Test.models.Location;
import com.example.Test.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private InMemoryDB inMemoryDB;

    @Override
    public void createDriver(DriverDTO driverDetails) {
        inMemoryDB.getDriversDetails().put(driverDetails.getId(), driverDetails);
    }

    @Override
    public void updateLocation(String driverId, Location location) {
        DriverDTO driver = inMemoryDB.getDriversDetails().get(driverId);
        if (driver != null) {
            driver.setCurrentLocation(location);
        }
    }

    @Override
    public void switchAvailability(String driverId, boolean isAvailable) {
        DriverDTO driver = inMemoryDB.getDriversDetails().get(driverId);
        if (driver != null) {
            driver.setIsAvailable(isAvailable);
        }
    }
}

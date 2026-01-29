package com.example.Test.service.serivceImpl;

import com.example.Test.db.InMemoryDB;
import com.example.Test.models.DriverDTO;
import com.example.Test.models.Location;
import com.example.Test.models.RideDetailsDto;
import com.example.Test.models.RidersDTO;
import com.example.Test.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RiderServiceImpl implements RiderService {

    @Autowired
    private InMemoryDB inMemoryDB;

    private static final double MAX_DISTANCE = 10.0;

    @Override
    public void createRider(RidersDTO riderDetails) {
        inMemoryDB.getRidersDetails().put(riderDetails.getId(), riderDetails);
    }

    @Override
    public List<RideDetailsDto> rideHistory(String riderId) {
        return inMemoryDB.getRideDetailsList().getOrDefault(riderId, new ArrayList<>());
    }

    @Override
    public RideDetailsDto bookCab(String riderId, Location userLocation) {
        Map<String, DriverDTO> driversDetails = inMemoryDB.getDriversDetails();
        DriverDTO assignedDriver = null;
        double minDistance = Double.MAX_VALUE;

        for (DriverDTO driver : driversDetails.values()) {
            if (Boolean.TRUE.equals(driver.getIsAvailable())) {
                Location driverLocation = driver.getCurrentLocation();
                double distance = Math.sqrt(Math.pow(driverLocation.getX() - userLocation.getX(), 2) +
                        Math.pow(driverLocation.getY() - userLocation.getY(), 2));

                if (distance <= MAX_DISTANCE && distance < minDistance) {
                    minDistance = distance;
                    assignedDriver = driver;
                }
            }
        }

        if (assignedDriver == null) {
            return null;
        }
        RidersDTO rider = inMemoryDB.getRidersDetails().get(riderId);
        if (rider == null) {
            return null;
        }
        RideDetailsDto rideDetailsDto = new RideDetailsDto();
        rideDetailsDto.setId(UUID.randomUUID().toString());
        rideDetailsDto.setRiderDetails(rider);
        rideDetailsDto.setDriverDetails(assignedDriver);
        rideDetailsDto.setDateTime(new Date().toString());
        rideDetailsDto.setRideCompleted(false);

        assignedDriver.setIsAvailable(false);

        inMemoryDB.getRideDetailsList().computeIfAbsent(riderId, k -> new ArrayList<>()).add(rideDetailsDto);

        return rideDetailsDto;
    }

    @Override
    public void endTrip(String riderId, String rideId) {
        List<RideDetailsDto> rides = inMemoryDB.getRideDetailsList().get(riderId);
        if (rides != null) {
            for (RideDetailsDto ride : rides) {
                if (ride.getId().equals(rideId)) {
                    ride.setRideCompleted(true);
                    ride.getDriverDetails().setIsAvailable(true);
                    break;
                }
            }
        }
    }
}

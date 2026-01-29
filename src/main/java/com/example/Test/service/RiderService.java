package com.example.Test.service;

import com.example.Test.models.Location;
import com.example.Test.models.RideDetailsDto;
import com.example.Test.models.RidersDTO;

import java.util.List;

public interface RiderService {

    void createRider(RidersDTO riderDetails);

    List<RideDetailsDto> rideHistory(String riderId);

    RideDetailsDto bookCab(String riderId, Location location);

    void endTrip(String riderId, String rideId);
}

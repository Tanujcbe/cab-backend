package com.example.Test.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDetailsDto {
    private String id;
    private RidersDTO riderDetails;
    private DriverDTO driverDetails;
    private String dateTime;
    private boolean isRideCompleted;
}

package com.example.Test.controllers;

import com.example.Test.models.DriverDTO;
import com.example.Test.models.Location;
import com.example.Test.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
public class Driver {

    @Autowired
    private DriverService driverService;

    @PostMapping
    public void createNewDriver(@RequestBody DriverDTO driver) {
        driverService.createDriver(driver);
    }

    @PutMapping("/{id}/location")
    public void updateDriverLocation(@PathVariable("id") String driverId,
            @RequestBody Location location) {
        driverService.updateLocation(driverId, location);
    }

    @PutMapping("/{id}/availability")
    public void switchAvailability(@PathVariable("id") String driverId,
            @RequestParam("isAvailable") boolean isAvailable) {
        driverService.switchAvailability(driverId, isAvailable);
    }
}

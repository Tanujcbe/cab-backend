package com.example.Test.controllers;

import com.example.Test.models.Location;
import com.example.Test.models.RideDetailsDto;
import com.example.Test.models.RidersDTO;
import com.example.Test.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riders")
public class Riders {

    @Autowired
    private RiderService riderService;

    @PostMapping
    public void createNewRider(@RequestBody RidersDTO riderDetails) {
        riderService.createRider(riderDetails);
    }

    @GetMapping("/{id}/history")
    public List<RideDetailsDto> ridersHistory(@PathVariable("id") String riderId) {
        return riderService.rideHistory(riderId);
    }

    @PostMapping("/{id}/book")
    public RideDetailsDto bookCab(@PathVariable("id") String riderId, @RequestBody Location location) {
        return riderService.bookCab(riderId, location);
    }

    @PostMapping("/{id}/end-trip")
    public void endTrip(@PathVariable("id") String riderId, @RequestParam("rideId") String rideId) {
        riderService.endTrip(riderId, rideId);
    }
}

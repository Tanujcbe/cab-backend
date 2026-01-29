package com.example.Test;

import com.example.Test.models.DriverDTO;
import com.example.Test.models.Location;
import com.example.Test.models.RideDetailsDto;
import com.example.Test.models.RidersDTO;
import com.example.Test.service.DriverService;
import com.example.Test.service.RiderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RiderService riderService, DriverService driverService) {
		return args -> {
			System.out.println("--- Registering Riders ---");
			RidersDTO rider1 = new RidersDTO("Rider1", "R1", new Location(0, 0));
			riderService.createRider(rider1);
			System.out.println("Registered: " + rider1.getName());

			System.out.println("\n--- Registering Drivers ---");
			DriverDTO driver1 = new DriverDTO("Driver1", "D1", new Location(5, 5), true);
			DriverDTO driver2 = new DriverDTO("Driver2", "D2", new Location(50, 50), true); // Out of range
			driverService.createDriver(driver1);
			driverService.createDriver(driver2);
			System.out.println("Registered: " + driver1.getName() + " and " + driver2.getName());

			System.out.println("\n--- Booking Cab for Rider1 (Location 0,0) ---");
			RideDetailsDto ride = riderService.bookCab("R1", new Location(0, 0));
			if (ride != null) {
				System.out.println("Ride Booked! Driver: " + ride.getDriverDetails().getName());
				System.out.println("Ride ID: " + ride.getId());

				System.out.println("\n--- Fetching Rider1 History ---");
				List<RideDetailsDto> history = riderService.rideHistory("R1");
				System.out.println("History count: " + history.size());

				System.out.println("\n--- Ending Trip ---");
				riderService.endTrip("R1", ride.getId());
				System.out.println("Trip ended. Driver availability: " + ride.getDriverDetails().getIsAvailable());
			} else {
				System.out.println("No cab available within range!");
			}

			System.out.println("\n--- Attempting to book when driver is far ---");
			RidersDTO rider2 = new RidersDTO("Rider2", "R2", new Location(100, 100));
			riderService.createRider(rider2);
			RideDetailsDto ride2 = riderService.bookCab("R2", new Location(100, 100));
			if (ride2 == null) {
				System.out.println("Success: No cab found for distant rider.");
			}
		};
	}
}

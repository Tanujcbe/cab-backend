package com.example.Test.db;

import com.example.Test.models.DriverDTO;
import com.example.Test.models.RideDetailsDto;
import com.example.Test.models.RidersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class InMemoryDB {
    private Map<String, RidersDTO> ridersDetails = new HashMap<>();
    private Map<String, DriverDTO> driversDetails = new HashMap<>();
    private Map<String, List<RideDetailsDto>> rideDetailsList = new HashMap<>();
}

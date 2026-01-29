package com.example.Test.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
    private String name;
    private String id;
    private Location currentLocation;
    private Boolean isAvailable;
}
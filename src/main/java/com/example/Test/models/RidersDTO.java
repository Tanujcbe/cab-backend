package com.example.Test.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RidersDTO {
    private String name;
    private String id;
    private Location currentLocation;
}

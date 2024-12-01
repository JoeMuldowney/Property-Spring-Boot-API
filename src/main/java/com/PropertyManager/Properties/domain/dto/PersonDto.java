package com.PropertyManager.Properties.domain.dto;
import com.PropertyManager.Properties.domain.entities.LocationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {

    private Long id;

    private String firstName;

    private String lastName;
    private String phoneNumber;
    private String email;

    private List<LocationEntity> locations = new ArrayList<>();

}

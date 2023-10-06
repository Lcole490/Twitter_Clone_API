package com.team_1.demo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProfileDto {

    private String email;

    private String firstName;

    private String lastName;

    private String phone;


}

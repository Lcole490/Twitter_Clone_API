package com.team_1.demo.dtos;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HashtagDto {

    private String label;

    private Timestamp firstUsed;

    private Timestamp lastUsed;
}

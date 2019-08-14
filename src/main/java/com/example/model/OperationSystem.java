package com.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OperationSystem {
    private Integer id;
    private String name;
    private String version;
    private String manufacturer;
    private Date releaseDate;
}

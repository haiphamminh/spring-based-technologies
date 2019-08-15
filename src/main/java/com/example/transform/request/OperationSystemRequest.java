package com.example.transform.request;

import lombok.Value;

import java.util.Date;

@Value
public class OperationSystemRequest {
    private Long id;
    private String name;
    private String version;
    private String manufacturer;
    private Date releaseDate;
}

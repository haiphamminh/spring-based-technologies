package com.example.transform.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OperationSystemResponse {
    private Long id;
    private String name;
    private String version;
    private String manufacturer;
    private Date releaseDate;
}

package com.example.transform.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationSystemRequest {
    private Long id;
    private String name;
    private String version;
    private String manufacturer;
    private Date releaseDate;
}

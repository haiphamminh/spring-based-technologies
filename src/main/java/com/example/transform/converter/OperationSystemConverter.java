package com.example.transform.converter;

import com.example.model.OperationSystem;
import com.example.transform.request.OperationSystemRequest;
import com.example.transform.response.OperationSystemResponse;
import org.springframework.stereotype.Component;

@Component
public class OperationSystemConverter {
    public OperationSystem convert(OperationSystemRequest request) {
        return OperationSystem.builder()
                              .manufacturer(request.getManufacturer())
                              .name(request.getName())
                              .version(request.getVersion())
                              .releaseDate(request.getReleaseDate())
                              .build();
    }

    public OperationSystemResponse convert(OperationSystem os) {
        return OperationSystemResponse.builder()
                                      .manufacturer(os.getManufacturer())
                                      .name(os.getName())
                                      .version(os.getVersion())
                                      .releaseDate(os.getReleaseDate())
                                      .build();
    }
}

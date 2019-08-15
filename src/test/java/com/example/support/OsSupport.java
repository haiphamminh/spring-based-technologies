package com.example.support;

import com.example.model.OperationSystem;
import com.example.transform.request.OperationSystemRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class OsSupport {
    public static final ObjectMapper mapper = new ObjectMapper();

    private OsSupport() {
    }

    public static OperationSystem buildAppleEntity() {
        return buildEntity(2L, "iOS", "12", "Apple", new Date());
    }

    public static OperationSystem buildEntity(Long id, String name, String version, String manufacturer,
                                              Date releaseDate) {
        return OperationSystem.builder()
                              .id(id)
                              .name(name)
                              .version(version)
                              .manufacturer(manufacturer)
                              .releaseDate(releaseDate)
                              .build();
    }

    public static List<OperationSystem> buildEntities() {
        return Arrays.asList(buildEntity(1L, "Windows 10", "2600", "Microsoft", new Date()),
                             buildEntity(2L, "iOS", "12", "Apple", new Date()),
                             buildEntity(3L, "Red Hat", "8", "IBM", new Date()));
    }

    public static OperationSystemRequest buildRequest() {
        return OperationSystemRequest.builder()
                                     .version("12")
                                     .manufacturer("Apple")
                                     .releaseDate(new Date())
                                     .build();
    }

    public static void verifyOS(OperationSystem expectedOS, OperationSystem actualOS) {
        assertNotNull(expectedOS);
        assertNotNull(expectedOS.getId());
        assertEquals(expectedOS.getName(), actualOS.getName());
        assertEquals(expectedOS.getManufacturer(), actualOS.getManufacturer());
        assertEquals(expectedOS.getReleaseDate(), actualOS.getReleaseDate());
        assertEquals(expectedOS.getVersion(), actualOS.getVersion());
    }

    public static String toJSON(Object value) throws JsonProcessingException {
        return mapper.writeValueAsString(value);
    }

    public static <T> T toObject(String content, Class<T> clazz) throws IOException {
        return mapper.readValue(content, clazz);
    }
}

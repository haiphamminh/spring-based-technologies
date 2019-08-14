package com.example.repository;

import com.example.model.OperationSystem;
import com.example.repository.impl.OperationSystemRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class OperationSystemRepositoryImplTest {
    OperationSystemRepositoryImpl testInst;

    @Before
    public void setup() {
        testInst = new OperationSystemRepositoryImpl();
    }

    @Test
    public void whenGetAllThenReturnThreePredefinedEntities() {
        List<OperationSystem> osList = testInst.findAll();
        assertNotNull(osList);
        assertEquals(osList.size(), 3);
    }

    @Test
    public void whenSaveNewOSThenReturnWithID() {
        OperationSystem os = OperationSystem.builder()
                .name("Windows 8")
                .version("2501")
                .manufacturer("Microsoft")
                .releaseDate(new Date())
                .build();
        OperationSystem savedOS = testInst.save(os);

        verifyOS(savedOS, os);
    }

    @Test
    public void whenSaveExistingOSThenReturnUpdatedOS() {
        OperationSystem os = OperationSystem.builder()
                .name("Windows 8")
                .version("2501")
                .manufacturer("Microsoft")
                .releaseDate(new Date())
                .build();
        OperationSystem savedOS = testInst.save(os);

        savedOS.setName("iOS");
        savedOS.setVersion("12");
        savedOS.setManufacturer("Apple");
        savedOS.setReleaseDate(new Date());

        OperationSystem updatedOS = testInst.save(savedOS);

        verifyOS(updatedOS, savedOS);
    }

    @Test
    public void whenFindByNullIDThenReturnEmptyOptional() {
        Optional<OperationSystem> osOpt = testInst.findById(null);
        assertTrue(osOpt.isEmpty());
    }

    @Test
    public void whenFindByNonExistingIDThenReturnEmptyOptional() {
        Optional<OperationSystem> osOpt = testInst.findById(0);
        assertTrue(osOpt.isEmpty());
    }

    @Test
    public void whenFindByExistingIDThenReturnOS() {
        Optional<OperationSystem> osOpt = testInst.findById(1);
        assertFalse(osOpt.isEmpty());
        OperationSystem os = osOpt.get();
        assertNotNull(os);
        assertTrue(os.getId().equals(1));
    }

    @Test
    public void whenDeleteByNonExistingIDThenTheSizeOfListStillTheSame() {
        testInst.deleteById(null);
        assertEquals(testInst.findAll().size(), 3);
    }

    @Test
    public void whenDeleteByNonExistingIDThenTheSizeOfListIsChanged() {
        testInst.deleteById(1);
        assertEquals(testInst.findAll().size(), 2);
    }

    private void verifyOS(OperationSystem expectedOS, OperationSystem actualOS) {
        assertNotNull(expectedOS);
        assertNotNull(expectedOS.getId());
        assertEquals(expectedOS.getName(), actualOS.getName());
        assertEquals(expectedOS.getManufacturer(), actualOS.getManufacturer());
        assertEquals(expectedOS.getReleaseDate(), actualOS.getReleaseDate());
        assertEquals(expectedOS.getVersion(), actualOS.getVersion());
    }
}

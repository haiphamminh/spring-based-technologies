package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.OperationSystem;
import com.example.repository.OperationSystemRepository;
import com.example.service.impl.OperationSystemServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.support.OsSupport.buildEntities;
import static com.example.support.OsSupport.buildEntity;
import static com.example.support.OsSupport.verifyOS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.doThrow;

@RunWith(PowerMockRunner.class)
public class OperationSystemServiceImplTest {
    @InjectMocks
    OperationSystemServiceImpl testInst;

    @Mock
    OperationSystemRepository osRepository;

    @Test
    public void whenGetAllThenReturnAllItems() {
        List<OperationSystem> osList = buildEntities();
        doReturn(osList).when(osRepository)
                        .findAll();
        assertEquals(osList, testInst.getAll());
        verify(osRepository, times(1)).findAll();
    }

    @Test
    public void whenSaveNewOSThenReturnWithID() {
        OperationSystem newOS = buildEntity(1L, "Windows 8", "2501", "Microsoft", new Date());
        doReturn(newOS).when(osRepository)
                       .save(newOS);
        OperationSystem savedOS = testInst.create(newOS);
        assertNotNull(savedOS);
        assertTrue(savedOS.getId()
                          .equals(1L));
        verifyOS(newOS, savedOS);
        verify(osRepository, times(1)).save(newOS);
    }

    @Test(expected = NotFoundException.class)
    public void whenFindNonExistingIDThenThrowException() {
        doReturn(Optional.empty()).when(osRepository)
                                  .findById(anyLong());
        testInst.getById(0L);
    }

    @Test
    public void whenFindExistingIdThenReturnValidOS() {
        OperationSystem os = buildEntity(1L, "Windows 8", "2501", "Microsoft", new Date());
        doReturn(Optional.of(os)).when(osRepository)
                                 .findById(1L);
        OperationSystem returnedOS = testInst.getById(1L);
        verifyOS(returnedOS, os);
        verify(osRepository, times(1)).findById(1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDeleteByNullIdThenThrowException() {
        doThrow(new IllegalArgumentException()).when(osRepository)
                                               .deleteById(null);
        testInst.deleteById(null);
    }

    @Test
    public void whenDeleteByValidIDThenReturnOK() {
        testInst.deleteById(1L);
        verify(osRepository, times(1)).deleteById(1L);
    }
}

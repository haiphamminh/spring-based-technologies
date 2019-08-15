package com.example.controller;

import com.example.exception.NotFoundException;
import com.example.model.OperationSystem;
import com.example.service.OperationSystemService;
import com.example.support.OsSupport;
import com.example.transform.converter.OperationSystemConverter;
import com.example.transform.request.OperationSystemRequest;
import com.example.transform.response.ErrorResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.example.controller.OperationSystemController.OPERATION_SYSTEM_URL;
import static com.example.support.OsSupport.toJSON;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
public class OperationSystemControllerTest {
    @InjectMocks
    OperationSystemController controller;
    @Mock
    OperationSystemService service;
    @Mock
    OperationSystemConverter converter;

    MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller)
                             .setControllerAdvice(new RestControllerAdviser())
                             .build();
    }

    @Test
    public void testGetAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(OPERATION_SYSTEM_URL)
                                          .accept(MediaType.APPLICATION_JSON)
                                          .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());

        verify(service, times(1)).getAll();
    }

    @Test
    public void testCreate() throws Exception {
        doReturn(new OperationSystem()).when(converter)
                                       .convert(any(OperationSystemRequest.class));

        mvc.perform(MockMvcRequestBuilders.post(OPERATION_SYSTEM_URL)
                                          .accept(MediaType.APPLICATION_JSON)
                                          .contentType(MediaType.APPLICATION_JSON)
                                          .content(toJSON(OsSupport.buildRequest())))
           .andExpect(status().isCreated());

        verify(service, times(1)).create(any(OperationSystem.class));
        verify(converter, times(1)).convert(any(OperationSystemRequest.class));
    }

    @Test
    public void testGetByIdThenReturn404() throws Exception {
        doThrow(new NotFoundException()).when(service)
                                        .getById(anyLong());
        mvc.perform(MockMvcRequestBuilders.get(OPERATION_SYSTEM_URL + "/{id}", 1L)
                                          .contentType(MediaType.APPLICATION_JSON)
                                          .content(toJSON(ErrorResponse.builder()
                                                                       .build())))
           .andExpect(result -> {
               ErrorResponse er = OsSupport.toObject(result.getResponse()
                                                           .getContentAsString(), ErrorResponse.class);
               assertEquals(NotFoundException.ERROR_CODE, er.getErrorCode());
           })
           .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(OPERATION_SYSTEM_URL + "/{id}", 1L)
                                          .contentType(MediaType.APPLICATION_JSON)
                                          .content(toJSON(OsSupport.buildAppleEntity())))
           .andExpect(status().isOk());
    }

    @Test
    public void testDeleteById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(OPERATION_SYSTEM_URL + "/{id}", 1L))
           .andExpect(status().isOk());
    }
}

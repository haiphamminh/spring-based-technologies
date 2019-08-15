package com.example.controller;

import com.example.model.OperationSystem;
import com.example.service.OperationSystemService;
import com.example.transform.converter.OperationSystemConverter;
import com.example.transform.request.OperationSystemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.controller.OperationSystemController.OPERATION_SYSTEM_URL;

@RestController
@RequestMapping(OPERATION_SYSTEM_URL)
@RequiredArgsConstructor
public class OperationSystemController {
    public static final String OPERATION_SYSTEM_URL = "/v1/operation-systems";

    private final OperationSystemService osService;
    private final OperationSystemConverter converter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OperationSystem> getAll() {
        return osService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OperationSystem create(@RequestBody OperationSystemRequest request) {
        return osService.create(converter.convert(request));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperationSystem getById(@PathVariable Long id) {
        return osService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        osService.deleteById(id);
    }
}

package com.example.controller;

import com.example.model.OperationSystem;
import com.example.service.OperationSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/operation-systems")
@RequiredArgsConstructor
public class OperationSystemController {
    private final OperationSystemService osService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OperationSystem> getAll() {
        return osService.getAll();
    }
}

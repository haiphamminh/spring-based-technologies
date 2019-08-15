package com.example.service;

import com.example.model.OperationSystem;

import java.util.List;

public interface OperationSystemService {
    List<OperationSystem> getAll();

    OperationSystem create(OperationSystem os);

    OperationSystem update(OperationSystem os);

    OperationSystem getById(Long id);

    void deleteById(Long id);
}

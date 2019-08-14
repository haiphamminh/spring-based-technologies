package com.example.service;

import com.example.model.OperationSystem;

import java.util.List;
import java.util.Optional;

public interface OperationSystemService {
    List<OperationSystem> getAll();

    OperationSystem create(OperationSystem os);

    Optional<OperationSystem> getById(Integer id);

    void deleteById(Integer id);
}

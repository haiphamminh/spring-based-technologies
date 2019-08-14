package com.example.repository;

import com.example.model.OperationSystem;

import java.util.List;
import java.util.Optional;

public interface OperationSystemRepository {
    List<OperationSystem> findAll();

    OperationSystem save(OperationSystem os);

    Optional<OperationSystem> findById(Integer id);

    void deleteById(Integer id);
}

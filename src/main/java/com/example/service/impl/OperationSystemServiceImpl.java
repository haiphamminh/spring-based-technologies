package com.example.service.impl;

import com.example.exception.NotFoundException;
import com.example.model.OperationSystem;
import com.example.repository.OperationSystemRepository;
import com.example.service.OperationSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationSystemServiceImpl implements OperationSystemService {
    private final OperationSystemRepository osRepository;

    @Override
    public List<OperationSystem> getAll() {
        return osRepository.findAll();
    }

    @Override
    public OperationSystem create(OperationSystem os) {
        return osRepository.save(os);
    }

    @Override
    public Optional<OperationSystem> getById(Integer id) {
        return Optional.ofNullable(osRepository.findById(id))
                .orElseThrow(() -> new NotFoundException("Not found os with id" + id));
    }

    @Override
    public void deleteById(Integer id) {
        osRepository.deleteById(id);
    }
}

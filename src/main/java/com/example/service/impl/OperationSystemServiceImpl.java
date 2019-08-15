package com.example.service.impl;

import com.example.exception.NotFoundException;
import com.example.model.OperationSystem;
import com.example.repository.OperationSystemRepository;
import com.example.service.OperationSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public OperationSystem update(OperationSystem os) {
        OperationSystem currentOS = getById(os.getId());
        currentOS.setManufacturer(os.getManufacturer());
        currentOS.setName(os.getName());
        currentOS.setReleaseDate(os.getReleaseDate());
        currentOS.setVersion(os.getVersion());
        osRepository.save(currentOS);
        return currentOS;
    }

    @Override
    public OperationSystem getById(Long id) {
        return osRepository.findById(id)
                           .orElseThrow(() -> new NotFoundException("Not found OS with id = " + id));
    }

    @Override
    public void deleteById(Long id) {
        osRepository.deleteById(id);
    }
}

package com.example.repository.impl;

import com.example.model.OperationSystem;
import com.example.repository.OperationSystemRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class OperationSystemRepositoryImpl implements OperationSystemRepository {
    private List<OperationSystem> osList = new ArrayList<>();

    public OperationSystemRepositoryImpl() {
        osList.add(
                OperationSystem
                        .builder()
                        .id(1)
                        .name("Windows 10")
                        .version("2600")
                        .manufacturer("Microsoft")
                        .releaseDate(new Date())
                        .build());
        osList.add(
                OperationSystem
                        .builder()
                        .id(2)
                        .name("iOS")
                        .version("12")
                        .manufacturer("Apple")
                        .releaseDate(new Date())
                        .build());
        osList.add(
                OperationSystem
                        .builder()
                        .id(3)
                        .name("Red Hat")
                        .version("8")
                        .manufacturer("IBM")
                        .releaseDate(new Date())
                        .build());
    }

    @Override
    public List<OperationSystem> findAll() {
        return osList;
    }

    @Override
    public OperationSystem save(OperationSystem os) {
        if (os.getId() == null) {
            os.setId(osList.size() + 1);
        } else {
            osList.remove(os);
        }
        osList.add(os);
        return os;
    }

    @Override
    public Optional<OperationSystem> findById(Integer id) {
        return osList.stream()
                .filter(os -> os.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(Integer id) {
        osList.removeIf(os -> os.getId().equals(id));
    }
}

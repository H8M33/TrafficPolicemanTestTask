package com.example.trafficpoliceman.serivce;

import com.example.trafficpoliceman.exception.NoMoreCarNumberException;
import com.example.trafficpoliceman.mapper.CarNumberMapper;
import com.example.trafficpoliceman.model.CarNumberEntity;
import com.example.trafficpoliceman.repository.CarNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarNumberServiceStateless implements CarNumberService{

    private final CarNumberMapper mapper;

    private final CarNumberRepository repository;

    @Override
    public String getRandomNumber() {
        long carNumber = repository.findRandomNumber().orElseThrow(NoMoreCarNumberException::new);
        repository.save(CarNumberEntity.builder().carNumber(carNumber).build());
        return mapper.numbertoString(carNumber);
    }

    @Override
    public String getNextNumber() {
        long carNumber = repository.findNextNumber().orElseThrow(NoMoreCarNumberException::new);
        repository.save(CarNumberEntity.builder().carNumber(carNumber).build());
        return mapper.numbertoString(carNumber);
    }
}

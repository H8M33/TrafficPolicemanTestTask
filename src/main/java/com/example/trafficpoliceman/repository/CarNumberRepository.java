package com.example.trafficpoliceman.repository;

import com.example.trafficpoliceman.model.CarNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarNumberRepository extends JpaRepository<CarNumberEntity, Long> {

    @Query(value = "select n from generate_series(1,1728000) as n " +
            "where not exists (select from car_number_entity where car_number = n) order by random() limit 1;", nativeQuery = true)
    Optional<Long> findRandomNumber();

    @Query(value = "select n from generate_series(1,1728000) as n " +
            "where not exists (select from car_number_entity where car_number = n) and n > (select car_number from car_number_entity order by create_date desc limit 1) order by n " +
            "limit 1;", nativeQuery = true)
    Optional<Long> findNextNumber();

}

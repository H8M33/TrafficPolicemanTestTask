package com.example.trafficpoliceman.controller;

import com.example.trafficpoliceman.api.CarNumberApi;
import com.example.trafficpoliceman.serivce.CarNumberService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarNumberController implements CarNumberApi {

    private final CarNumberService carNumberService;

    public CarNumberController(@Qualifier("carNumberServiceStateless") CarNumberService carNumberService) {
        this.carNumberService = carNumberService;
    }

    @Override
    public String getRandomNumber() {
        return carNumberService.getRandomNumber();
    }

    @Override
    public String getNextNumber() {
        return carNumberService.getNextNumber();
    }
}

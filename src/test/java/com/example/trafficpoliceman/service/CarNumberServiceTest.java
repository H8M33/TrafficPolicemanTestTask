package com.example.trafficpoliceman.service;

import com.example.trafficpoliceman.mapper.CarNumberMapperImpl;
import com.example.trafficpoliceman.serivce.CarNumberService;
import com.example.trafficpoliceman.serivce.CarNumberServiceRuntime;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

public class CarNumberServiceTest {

    private CarNumberService carNumberService;


    @Test
    public void carNumberServiceRuntimeRandomTest(){
        carNumberService = new CarNumberServiceRuntime(new CarNumberMapperImpl());
        HashSet<String> set =new HashSet<>();
        for (int i=0;i<1728000;i++){
            String number = carNumberService.getRandomNumber();
            assert !set.contains(number);
            set.add(number);
        }
    }

    @Test
    public void carNumberServiceRuntimeNextTest(){
        carNumberService = new CarNumberServiceRuntime(new CarNumberMapperImpl());
        HashMap<Character, Integer> map = new HashMap<>();
        //{'A', 'B', 'C', 'E', 'H', 'K', 'M', 'O', 'P', 'T', 'X', 'Y'};
        map.put('A',0);
        map.put('B', 1);
        map.put('C',2);
        map.put('E',3);
        map.put('H', 4);
        map.put('K', 5);
        map.put('M', 6);
        map.put('O', 7);
        map.put('P', 8);
        map.put('T',9);
        map.put('X', 10);
        map.put('Y', 11);
        String number = carNumberService.getRandomNumber();
        int numberValue = Character.getNumericValue(number.charAt(1))*100 +
                Character.getNumericValue(number.charAt(2))*10 +
                Character.getNumericValue(number.charAt(3)) +
                map.get(number.charAt(5))*1000 +
                map.get(number.charAt(4))*12000 +
                map.get(number.charAt(0))*144000;
        String nextNumber = carNumberService.getNextNumber();
        int nextNumberValue = Character.getNumericValue(nextNumber.charAt(1))*100 +
                Character.getNumericValue(nextNumber.charAt(2))*10 +
                Character.getNumericValue(nextNumber.charAt(3)) +
                map.get(nextNumber.charAt(5))*1000 +
                map.get(nextNumber.charAt(4))*12000 +
                map.get(nextNumber.charAt(0))*144000;
        assert nextNumberValue - numberValue == 1;
    }
}

package com.example.trafficpoliceman.mapper;

import org.springframework.stereotype.Component;

@Component
public class CarNumberMapperImpl implements CarNumberMapper {

    private final static char[] CHARACTERS = {'A', 'B', 'C', 'E', 'H', 'K', 'M', 'O', 'P', 'T', 'X', 'Y'};


    @Override
    public String numbertoString(Long number) {
        StringBuilder builder = new StringBuilder();
        builder.insert(0, number % 10);
        number /= 10;
        builder.insert(0, number % 10);
        number /= 10;
        builder.insert(0, number % 10);
        number /= 10;
        builder.insert(3, CHARACTERS[(int) (number % 12)]);
        number /= 12;
        builder.insert(3, CHARACTERS[(int) (number % 12)]);
        number /= 12;
        builder.insert(0, CHARACTERS[(int) (number % 12)]);
        builder.append(" 116 RUS");
        return builder.toString();
    }
}

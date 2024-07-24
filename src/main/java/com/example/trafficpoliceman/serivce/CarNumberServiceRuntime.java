package com.example.trafficpoliceman.serivce;

import com.example.trafficpoliceman.exception.NoMoreCarNumberException;
import com.example.trafficpoliceman.exception.PreviousNumberNotFoundException;
import com.example.trafficpoliceman.mapper.CarNumberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.SortedSet;
import java.util.TreeSet;

@Service
@RequiredArgsConstructor
public class CarNumberServiceRuntime implements CarNumberService {

    private static Long previousNumber;

    private final CarNumberMapper mapper;

    private final static SortedSet<Long> set = new TreeSet<>();

    private final static Long MAX_NUMBER = 1728000L;

    private static Long remainingNumbers = 1728000L;

    @Override
    public String getRandomNumber() {
        previousNumber = System.currentTimeMillis()%MAX_NUMBER;
        return getNextNumber();
    }

    @Override
    public String getNextNumber() {
        if (remainingNumbers > 0) {
            try {
                while (set.contains(previousNumber)) {
                    previousNumber++;
                    previousNumber %= MAX_NUMBER;
                }
                set.add(previousNumber);
                remainingNumbers--;
                return mapper.numbertoString(previousNumber);
            } catch (NullPointerException e) {
                throw new PreviousNumberNotFoundException();
            }
        }
        throw new NoMoreCarNumberException();
    }
}

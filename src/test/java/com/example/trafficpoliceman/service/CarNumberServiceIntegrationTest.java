package com.example.trafficpoliceman.service;

import com.example.trafficpoliceman.mapper.CarNumberMapperImpl;
import com.example.trafficpoliceman.serivce.CarNumberService;
import com.example.trafficpoliceman.serivce.CarNumberServiceRuntime;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.HashSet;

@SpringBootTest
@ContextConfiguration(initializers = {CarNumberServiceIntegrationTest.Initializer.class})
public class CarNumberServiceIntegrationTest {

    private static PostgreSQLContainer sqlContainer;

    @Autowired
    @Qualifier("carNumberServiceStateless")
    private CarNumberService carNumberService;

    static {
        sqlContainer = new PostgreSQLContainer<>("postgres:13")
                .withDatabaseName("integration-tests-db")
                .withUsername("postgres")
                .withPassword("postgres");
        sqlContainer.start();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + sqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + sqlContainer.getUsername(),
                    "spring.datasource.password=" + sqlContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    public void carNumberServiceStatelessRandomTest(){
        HashSet<String> set =new HashSet<>();
        for (int i=0;i<1728000;i++){
            String number = carNumberService.getRandomNumber();
            assert !set.contains(number);
            set.add(number);
        }
    }

    @Test
    public void carNumberServiceStatelessNextTest(){
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


    @AfterAll
    public static void stopContainer(){
        sqlContainer.stop();
    }

}

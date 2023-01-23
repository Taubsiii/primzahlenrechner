package Aufgaben.hiwin.objects;


import Aufgaben.hiwin.services.PrimeService;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PrimeCalculator {

    private final int START_VALUE = 1;

    public List<Prime> getPrimesWithTime(int amount) {
        List<Prime> primes = new ArrayList<>();
        int number = START_VALUE;
        Instant startTime = Instant.now();

        while (primes.size() < amount) {
            if (PrimeService.isPrime(number, primes)) {
                Instant endTime = Instant.now();
                primes.add(new Prime(number, Duration.between(startTime, endTime).getSeconds()));
            }
            number += 1;
        }
        return primes;
    }
}

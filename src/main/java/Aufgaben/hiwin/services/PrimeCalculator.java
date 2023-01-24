package Aufgaben.hiwin.services;


import Aufgaben.hiwin.objects.Prime;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PrimeCalculator {

    private final int START_VALUE = 3;

    /**
     * Calculates prime numbers up to a certain amount
     * @param amount The amount of Primes you want
     * @return A List of the Primes with the wanted amount
     */
    public List<Prime> getPrimesWithTime(int amount) {
        List<Prime> primes = new ArrayList<>();
        int number = START_VALUE;
        Instant startTime = Instant.now();

        if(amount >= 1){
            primes.add(new Prime(2,0));
        }

        while (primes.size() < amount) {
            if (PrimeService.isPrime(number, primes)) {
                Instant endTime = Instant.now();
                primes.add(new Prime(number, Duration.between(startTime, endTime).getSeconds()));
            }
            number += 2;
        }
        return primes;
    }
}

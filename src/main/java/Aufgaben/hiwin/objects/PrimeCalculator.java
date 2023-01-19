package Aufgaben.hiwin.objects;


import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PrimeCalculator {

    private final int START_VALUE = 2;

    public List<Prime> getListThroughTime(List<Prime> primes, int seconds) {
        List<Prime> primesWithSameTime = new ArrayList<>();
        for (Prime prime : primes) {
            if (prime.getCalculationTime() > seconds) {
                break;
            }
            if (prime.getCalculationTime() == seconds) {
                primesWithSameTime.add(prime);
            }
        }
        return primesWithSameTime;
    }

    public List<Prime> getPrimesWithTime(int amount) {
        List<Prime> primes = new ArrayList<>();
        int number = START_VALUE;
        Instant startTime = Instant.now();

        while (primes.size() < amount) {
            if(isPrime(number,primes)){
                Instant endTime = Instant.now();
                primes.add(new Prime(number, Duration.between(startTime,endTime).getSeconds()));
            }
            number++;
        }
        return primes;
    }

    public boolean isPrime(int value, List<Prime> primes) {
        if(value == 2 || value == 3){
            return true;
        }
        for (Prime prime : primes) {
            if (value % prime.getValue() == 0) {
                return false;
            }
        }
        return true;
    }
}

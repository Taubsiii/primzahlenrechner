package Aufgaben.hiwin.services;

import Aufgaben.hiwin.objects.DBEntity;
import Aufgaben.hiwin.objects.Prime;
import Aufgaben.hiwin.objects.PrimeList;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PrimeService {
    /**
     * Returns true if the value is a prime
     * @param value The value you want to evaluate
     * @param primes the list of already found primes to check against
     * @return True if value is a prime
     */
    public static boolean isPrime(int value, List<Prime> primes) {
        final double sqrtOfValue = Math.sqrt(value);
        if (value % 2 == 0) {
            return false;
        }
        for (Prime prime : limitPrimeListToAmount(sqrtOfValue, primes)) {
            if (value % prime.getValue() == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Filters out primes in a list over the value
     * @param value The value to filter by
     * @return A shortened list only with smaller Primes than the value
     */
    private static List<Prime> limitPrimeListToAmount(double value, List<Prime> primes) {
        List<Prime> primesToReturn = new ArrayList<>();
        for (Prime prime : primes) {
            if (prime.getValue() <= value) {
                primesToReturn.add(prime);
            } else if (prime.getValue() > value) {
                break;
            }
        }
        return primesToReturn;
    }

    /**
     * Filters out primes in a list not fitting to the build time of value
     * @param seconds The build time to filter against
     * @return A shortened list with primes having the same buildTime as value
     */
    public List<Prime> getPrimesListThroughTime(List<Prime> primes, long seconds) {
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

    /**
     *
     * @param
     * @return
     */
    public void getPrimesAndZip(PrimeList primes) {
        FileService fileService = new FileService();
        long maxCalcTime = primes.getMaxCalcTime();
        List<File> files = fileService.createFilesFromPrimes(primes);
        fileService.createZipFromFiles(files, String.valueOf(maxCalcTime));
    }

    /**
     * Creates a DBEntity with a list of primes
     * @return A DBEntity to make DB interactions with
     */
    public DBEntity createDBntityThroughPrimes(PrimeList primes, Connection c) {
        DBService dbService = new DBService();

        return new DBEntity(
                dbService.getLastID(c)+1,
                primes.getMaxCalcTime()+"sec",
                primes.getMaxCalcTime(),
                primes.getAmountOfPrimes(),
                primes.getHighestPrime());
    }
}



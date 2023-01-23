package Aufgaben.hiwin.services;

import Aufgaben.hiwin.objects.CalcTimesOfPrime;
import Aufgaben.hiwin.objects.Prime;
import Aufgaben.hiwin.objects.PrimeCalculator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrimeService {


    public static boolean isPrime(int value, List<Prime> primes) {
        final double sqrtOfValue = Math.sqrt(value);
        if (value == 2 || value == 3) {
            return true;
        }
        if (value == 1 || value % 2 == 0) {
            return false;
        }
        for (Prime prime : limitPrimeListToAmount(sqrtOfValue, primes)) {
            if (value % prime.getValue() == 0) {
                return false;
            }
        }
        return true;
    }

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

    public CalcTimesOfPrime getMinMaxOfBuildtime(List<Prime> primes) {
        CalcTimesOfPrime primeTime = new CalcTimesOfPrime(primes.get(0).getCalculationTime(), primes.get(primes.size() - 1).getCalculationTime());
        return primeTime;
    }

    public void getPrimesAndZip(List<Prime> primes) {
        FileService fileService = new FileService();
        PrimeCalculator primeCalculator = new PrimeCalculator();
        CalcTimesOfPrime calcTimes = getMinMaxOfBuildtime(primes);
        List<File> files = fileService.createFilesFromPrimes(primes);
        fileService.createZipFromFiles(files, String.valueOf(calcTimes.getMax()));
    }

}



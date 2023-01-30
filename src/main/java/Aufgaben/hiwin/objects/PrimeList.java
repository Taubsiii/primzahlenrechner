package Aufgaben.hiwin.objects;

import java.util.List;

public class PrimeList {
    List<Prime> primes;
    long minCalcTime;
    long maxCalcTime;
    int amountOfPrimesInList;
    long highestPrime;

    public PrimeList(List<Prime> primes) {
        this.primes = primes;
        if(!primes.isEmpty()){
            this.amountOfPrimesInList = primes.size();
            this.minCalcTime = primes.get(0).getCalculationTime();
            this.maxCalcTime = primes.get(this.amountOfPrimesInList-1).getCalculationTime();
            this.highestPrime = primes.get(this.amountOfPrimesInList-1).getValue();
        }

    }

    public List<Prime> getPrimesAsList() {
        return primes;
    }

    public long getMinCalcTime() {
        return minCalcTime;
    }

    public long getMaxCalcTime() {
        return maxCalcTime;
    }

    public int getAmountOfPrimes() {
        return amountOfPrimesInList;
    }

    public long getHighestPrime() {
        return highestPrime;
    }
}

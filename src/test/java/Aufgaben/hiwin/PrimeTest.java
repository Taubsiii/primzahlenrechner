package Aufgaben.hiwin;

import Aufgaben.hiwin.objects.PrimeList;
import Aufgaben.hiwin.services.PrimeCalculator;
import Aufgaben.hiwin.services.PrimeService;
import org.junit.jupiter.api.Test;

class PrimeTest {
    final int SMALL_AMOUNT = 10;
    final int BIG_AMOUNT = 1000;
    @Test
    void PrimeValidTest() {
        PrimeList compareList = new PrimeCalculator().getPrimesWithTime(SMALL_AMOUNT);
        int[] firstTenPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        for (int i = 0; i < SMALL_AMOUNT; i++) {
            System.out.println("Comparing: " + compareList.getPrimesAsList().get(i).getValue() + " with " + firstTenPrimes[i]);
            assert compareList.getPrimesAsList().get(i).getValue() == firstTenPrimes[i];
        }
    }

    @Test
    void CorrectAmountTest() {
        final int AMOUNT = 1000;
        PrimeList compareList = new PrimeCalculator().getPrimesWithTime(1000);
        System.out.println("Comparing compareList.size()");
        assert compareList.getAmountOfPrimes() == 1000;
    }

    @Test
    void fullServiceTest(){
        PrimeService primeService = new PrimeService();
        PrimeList compareList = new PrimeCalculator().getPrimesWithTime(SMALL_AMOUNT);
        primeService.getPrimesAndZip(compareList);
    }
}

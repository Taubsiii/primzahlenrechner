package Aufgaben.hiwin;

import Aufgaben.hiwin.objects.Prime;
import Aufgaben.hiwin.objects.PrimeCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
public class PrimeTest {
    final int SMALL_AMOUNT = 10;
    final int BIG_AMOUNT = 1000;
    @Test
    void PrimeValidTest() {
        List<Prime> compareList = new PrimeCalculator().getPrimesWithTime(SMALL_AMOUNT);
        int[] firstTenPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        System.out.println("Hello");
        for (int i = 0; i < SMALL_AMOUNT; i++) {
            System.out.println("Comparing: " + compareList.get(i).getValue() + " with " + firstTenPrimes[i]);
            assert compareList.get(i).getValue() == firstTenPrimes[i];
        }
    }

    @Test
    void PrimeTimeTest() {
        List<Prime> compareList = new PrimeCalculator().getPrimesWithTime(1000);
        List<Long> timeList = new ArrayList<>();
        for(Prime prime:compareList){
            timeList.add(prime.getCalculationTime());
        }
        Map<Long,Long> counts = (Map<Long, Long>) timeList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(counts);
        assert counts.get(new Long(0)) > 0;
    }

    @Test
    void CorrectAmountTest() {
        final int AMOUNT = 1000;
        List<Prime> compareList = new PrimeCalculator().getPrimesWithTime(1000);
        System.out.println("Comparing compareList.size()");
        assert compareList.size() == 1000;
    }
}
package Aufgaben.hiwin;

import Aufgaben.hiwin.objects.Prime;
import Aufgaben.hiwin.services.PrimeCalculator;
import Aufgaben.hiwin.services.FileService;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.util.List;

class FileServiceTest {
    final int SMALL_AMOUNT = 10;
    final int BIG_AMOUNT = 1000;

    @Test
    void fileCreationTest() {
        FileService fileService = new FileService();
        assert fileService.createFileFromPrime(new Prime(2, 1)).exists();
    }

    @Test
    void multipleFileCreationTest() {
        FileService fileService = new FileService();
        List<Prime> primes = new PrimeCalculator().getPrimesWithTime(SMALL_AMOUNT);
        List<File> files = fileService.createFilesFromPrimes(primes);
        assert files.size() == 10;
    }

    @Test
    void zipFileCreationTest() {
        FileService fileService = new FileService();
        List<Prime> primes = new PrimeCalculator().getPrimesWithTime(SMALL_AMOUNT);
        List<File> files = fileService.createFilesFromPrimes(primes);
        try {
            fileService.createZipFromFiles(files, "zipFileCreationTest");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void createSingleFileTest() {
        FileService fileService = new FileService();
        List<Prime> primes = new PrimeCalculator().getPrimesWithTime(SMALL_AMOUNT);
        try {
            fileService.createFileFromPrimes(primes);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


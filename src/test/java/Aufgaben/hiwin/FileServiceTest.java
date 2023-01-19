package Aufgaben.hiwin;

import Aufgaben.hiwin.objects.Prime;
import Aufgaben.hiwin.objects.PrimeCalculator;
import Aufgaben.hiwin.services.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootTest
public class FileServiceTest {
    final int SMALL_AMOUNT = 10;
    final int BIG_AMOUNT = 1000;

    @Test
    void FileCreationTest() {
        FileService fileService = new FileService();
        assert fileService.createFileFromPrime(new Prime(2, 1)).exists();
    }

    @Test
    void MultipleFileCreationTest() {
        FileService fileService = new FileService();
        List<Prime> primes = new PrimeCalculator().getPrimesWithTime(SMALL_AMOUNT);
        List<File> files = fileService.createFilesFromPrimes(primes);
        assert files.size() == 10;
    }

    @Test
    void ZipFileCreationTest() {
        FileService fileService = new FileService();
        List<Prime> primes = new PrimeCalculator().getPrimesWithTime(SMALL_AMOUNT);
        List<File> files = fileService.createFilesFromPrimes(primes);
        try {
            fileService.createZipOutOfFiles(files);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


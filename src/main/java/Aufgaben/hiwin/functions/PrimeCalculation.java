package Aufgaben.hiwin.functions;

import Aufgaben.hiwin.objects.DBEntity;
import Aufgaben.hiwin.objects.Prime;
import Aufgaben.hiwin.services.DBService;
import Aufgaben.hiwin.services.FileService;
import Aufgaben.hiwin.services.PrimeCalculator;
import Aufgaben.hiwin.services.PrimeService;

import java.io.File;
import java.sql.Connection;
import java.util.List;

public class PrimeCalculation {

    public void runPrimeCalculator(int amount, boolean createZip, boolean writeIntoDB) {
        DBService dbService = new DBService();
        FileService fileService = new FileService();
        PrimeService primeService = new PrimeService();


        List<Prime> primes = new PrimeCalculator().getPrimesWithTime(amount);
        String zipFileName = "";
        Connection c = dbService.connectToDB("primzahlen");

        //DB
        if (writeIntoDB) {
            try {
                dbService.createTable(c);
                DBEntity dbEntity = primeService.createDBntityThroughPrimes(primes, c);
                dbService.writeDBEntityDBToDB(dbEntity, c);


            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        //Zipping
        if (createZip) {
            try {
                zipFileName = String.valueOf(primeService.getMinMaxOfBuildtime(primes));
                List<File> fileList = fileService.fileToFilelist(fileService.createFileFromPrimes(primes));
                fileService.createZipFromFiles(fileList, zipFileName);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        dbService.disconnectFromDB(c);
        System.exit(0);

    }
}

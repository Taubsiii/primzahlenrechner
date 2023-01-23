package Aufgaben.hiwin.services;

import Aufgaben.hiwin.objects.Prime;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileService {

    public void createZipFromFiles(List<File> files,String nameOfZip) {
        try {
            final FileOutputStream fos = new FileOutputStream("src\\main\\java\\Aufgaben\\hiwin\\files\\" + nameOfZip + ".zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            for (File file : files) {
                FileInputStream fis = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }

                fis.close();
                file.delete();
            }

            zipOut.close();
            fos.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public List<File> createFilesFromPrimesByTime(List<Prime> primes,long seconds) {
        List<File> fileList = new ArrayList<>();

        for (Prime prime : primes) {
            if(prime.getCalculationTime() > seconds){
                break;
            }
            if(prime.getCalculationTime() == seconds){
                fileList.add(createFileFromPrime(prime));
            }
        }
        return fileList;
    }

    public List<File> createFilesFromPrimes(List<Prime> primes) {
        List<File> fileList = new ArrayList<>();

        for (Prime prime : primes) {
            fileList.add(createFileFromPrime(prime));
        }
        return fileList;
    }

    public File createFileFromPrime(Prime prime) {
        final String FILENAME = String.valueOf(prime.getValue());
        try {
            File file = new File("src\\main\\java\\Aufgaben\\hiwin\\files\\" + FILENAME + ".json");
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
            fileWriter.write(prime.toJson());
            fileWriter.flush();
            fileWriter.close();
            return file;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int countFilesInZip(final ZipFile zipFile) {
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();
        int numRegularFiles = 0;
        while (entries.hasMoreElements()) {
            if (! entries.nextElement().isDirectory()) {
                ++numRegularFiles;
            }
        }
        return numRegularFiles;
    }
}

package Aufgaben.hiwin.services;

import Aufgaben.hiwin.objects.Prime;
import Aufgaben.hiwin.objects.PrimeList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileService {


    /**
     * Createda zip from the provided files and saves it in the folder "files"
     *
     * @param nameOfZip The name you want the zip file to have
     * @param files     A list of files to add to the zip
     */
    public void createZipFromFiles(List<File> files, String nameOfZip) {
        try(
                final FileOutputStream fos = new FileOutputStream("src\\main\\java\\Aufgaben\\hiwin\\files\\" +
                nameOfZip + "sec.zip");
                ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            System.out.println("Zipping files into " + nameOfZip + ".zip");

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
            System.out.println("Files have been zipped");

        } catch (Exception e) {
            System.out.println(e);
            System.err.println("Zipping failed");
        }
    }

    /**
     * Transforms a single File to a List of Files to be able to use the same functions
     * @param file
     * @return A List only containing the single file
     */
    public List<File> fileToFilelist(File file){
        List<File> fileList = new ArrayList<>();
        fileList.add(file);
        return fileList;
    }


    /**
     * Filters a list of primes to the provided buildTime
     * @param primes  The list you want to have filtered
     * @param seconds The time in seconds you want to have the list filtered for
     * @return The filtered list with the same buildTime as the seconds variable
     */
    public List<File> createFilesFromPrimesByTime(PrimeList primes, long seconds) {
        List<File> fileList = new ArrayList<>();

        for (Prime prime : primes.getPrimesAsList()) {
            if (prime.getCalculationTime() > seconds) {
                break;
            }
            if (prime.getCalculationTime() == seconds) {
                fileList.add(createFileFromPrime(prime));
            }
        }
        return fileList;
    }


    /**
     * Takes a list of Primes and makes files out of them.
     *
     * @param primes A list of primes you want to have list of files of
     * @return The list of files made from the Primes
     */
    public List<File> createFilesFromPrimes(PrimeList primes) {
        System.out.println("Creating Files from Primes");
        List<File> fileList = new ArrayList<>();

        for (Prime prime : primes.getPrimesAsList()) {
            fileList.add(createFileFromPrime(prime));
        }
        System.out.println("Files have been created");
        return fileList;
    }


    public File createFileFromPrimes(PrimeList primes) {
        final String FILENAME = "primes";
        try {
            File file = new File("src\\main\\java\\Aufgaben\\hiwin\\files\\" + FILENAME + ".json");
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));

            fileWriter.write("{\n");
            fileWriter.write("\"Prime\": [\n");

            for (int i = 0; i < primes.getAmountOfPrimes(); i++) {
                if (primes.getAmountOfPrimes() - 1 == i) {
                    fileWriter.write(primes.getPrimesAsList().get(i).toJson() + "\n");
                    continue;
                }

                fileWriter.write(primes.getPrimesAsList().get(i).toJson() + ",\n");
            }

            fileWriter.write("]\n}");
            fileWriter.flush();
            fileWriter.close();
            return file;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Takes a single Prime and makes a file out of it. The File will be a .json with the attributes inside of it.
     *
     * @param prime The prime you want to have a File of
     * @return A File of the Prime
     */
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
}


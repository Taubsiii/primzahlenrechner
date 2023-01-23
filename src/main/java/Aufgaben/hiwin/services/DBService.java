package Aufgaben.hiwin.services;

import Aufgaben.hiwin.objects.CalcTimesOfPrime;
import Aufgaben.hiwin.objects.Prime;
import Aufgaben.hiwin.objects.PrimeCalculator;

import java.io.File;
import java.sql.*;
import java.util.List;

public class DBService {

    public Connection connectToDB() {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:primzahlen.db");
            System.out.println("DB Connection established");
            return c;

        } catch (Exception e) {
            System.err.println(e);
        }
        return c;
    }

    public boolean createTable(Connection c) {
        Statement stmt = null;

        if (c == null) {
            return false;
        }

        try {
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "CREATE TABLE PRIMES " +
                    "(ID                INT             NOT NULL   PRIMARY KEY  AUTO_INCREMENT," +
                    " FILE_NAME         VARCHAR(255)    NOT NULL    , " +
                    " CALC_TIME         INT             NOT NULL    , " +
                    " AMOUNT_OF_PRIMES  INT             NOT NULL    , " +
                    " BIGGEST_PRIME     INT             NOT NULL    )";

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            System.out.println("Table created successfully");
            return true;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public int getLastID(Connection c) {
        int id = 1;
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM PRIMES;");
            while (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID = " + id);
                rs.close();
                return id;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    public void getPrimesAndWriteIntoDB(List<Prime> primes, Connection c, String filename) {
        FileService fileService = new FileService();
        PrimeCalculator primeCalculator = new PrimeCalculator();
        PrimeService primeService = new PrimeService();
        int id = 1;
        long amountOfPrimes = primes.size();
        long calcTimes = primeService.getMinMaxOfBuildtime(primes).getMax();
        long biggestPrime = primes.get(primes.size() - 1).getValue();

        Statement stmt = null;

        try {
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = createSQLInsertForPrimes(id, filename, calcTimes, amountOfPrimes, biggestPrime);
            System.out.println("Writing into db: " + sql);
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            System.out.println("Table created successfully");

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private String createSQLInsertForPrimes(int id, String filename, long calcTime, long amountOfPrimes, long biggestPrime) {
        return "INSERT INTO PRIMES (ID,FILE_NAME,CALC_TIME,AMOUNT_OF_PRIMES,BIGGEST_PRIME) " +
                "VALUES (" + id + "," + filename + "," + calcTime + "," + amountOfPrimes + "," + biggestPrime + ");";
    }
}

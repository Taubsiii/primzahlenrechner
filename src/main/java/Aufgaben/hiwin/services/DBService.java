package Aufgaben.hiwin.services;

import Aufgaben.hiwin.objects.DBEntity;

import java.sql.*;

public class DBService {

    /**
     * Connects to an existing db in the source folder or creates it if it does not exist
     * @param dbName Takes a name for a database and names it that way
     * @return Returns the active connection to the Database
     */
    public Connection connectToDB(String dbName) {


        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
            Class.forName("org.sqlite.JDBC");
            System.out.println("DB Connection established");
            return c;

        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    public boolean disconnectFromDB(Connection c){
        try {
            c.close();
            System.out.println("Disconnected from DB");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Creates a table named PRIMES if it does not already exist
     * @param c Takes an existing connection
     * @return Returns true if the outcome was positive (created or already existing)
     */
    public boolean createTable(Connection c) {
        Statement stmt = null;

        if (c == null) {
            System.err.println("No connection to DB");
            return false;
        }

        try {
            stmt = c.createStatement();

            String sql = "CREATE TABLE  IF  NOT EXISTS  PRIMES " +
                    "(ID                INT             NOT NULL   PRIMARY KEY  ," +
                    " FILE_NAME         VARCHAR(255)    NOT NULL    , " +
                    " CALC_TIME         INT             NOT NULL    , " +
                    " AMOUNT_OF_PRIMES  INT             NOT NULL    , " +
                    " BIGGEST_PRIME     INT             NOT NULL    )";

            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Table created successfully / Table already exists");
            return true;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Gets the highest ID from the table
     * @param c Takes an active connection to the DB
     * @return Returns an int value of the last index. If table is empty it returns 0
     * @implNote If DELETE is added, change count(*) to another parameter like max(id)
     */
    public int getLastID(Connection c) {
        int id = 0;
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT max(ID) FROM PRIMES;");
            while (rs.next()) {
                id = rs.getInt(1);
                rs.close();
                return id;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    /**
     * Inserts a new DGEntity into the DB
     * @param c Takes an active connection to the DB
     * @param dbEntity
     */
    public boolean writeDBEntityDBToDB(DBEntity dbEntity, Connection c) {
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            String sql = createInsertByDBEntity(dbEntity);
            System.out.println("Writing into db: " + sql);
            stmt.executeUpdate(sql);
            System.out.println("Insert Command Successful");
            stmt.close();
            return true;


        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Writes a SQL Insert command with the data of some DBEntity
     * @param dbEntity
     * @return The full SQL Insert command
     */
    public String createInsertByDBEntity(DBEntity dbEntity) {

        return "INSERT INTO PRIMES (ID,FILE_NAME,CALC_TIME,AMOUNT_OF_PRIMES,BIGGEST_PRIME) " +
                "VALUES (" +
                dbEntity.getTableID() + ",\"" +
                dbEntity.getFileName() + "\"," +
                dbEntity.getCalcTime() + "," +
                dbEntity.getAmountOfPrimes() + "," +
                dbEntity.getBiggestPrime() + ");";
    }

    /**
     * Takes an ID and looks it up on the Database. If it finds something it returns the object. If not it returns null
     * @param id The ID of the Object you want to get
     * @param c The active connection of the DB
     * @return DBEntity or Null
     */
    public DBEntity getEntityByID(Connection c, int id) {
        if (id > getLastID(c)) {
            System.err.println("Index out of bounds");
            return null;
        }
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PRIMES WHERE ID = " + id + ";");
            while (rs.next()) {
                int table_id = rs.getInt(1);
                String fileName = rs.getString(2);
                long calcTime = rs.getLong(3);
                long amountOfPrimes = rs.getLong(4);
                long biggestPrime = rs.getLong(5);
                rs.close();
                return new DBEntity(table_id, fileName, calcTime, amountOfPrimes, biggestPrime);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

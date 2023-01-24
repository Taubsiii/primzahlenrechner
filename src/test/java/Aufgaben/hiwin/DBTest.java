package Aufgaben.hiwin;

import Aufgaben.hiwin.objects.DBEntity;
import Aufgaben.hiwin.services.DBService;
import Aufgaben.hiwin.services.PrimeService;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

class DBTest {
    final int SMALL_AMOUNT = 10;
    final int BIG_AMOUNT = 1000;
    DBEntity mockEntity = new DBEntity(1, "testFile", 5, 40, 71);

    @Test
    void dbConnectionTest() {
        DBService dbService = new DBService();
        Connection c = dbService.connectToDB("mock");
        assert c != null;
        assert dbService.disconnectFromDB(c);
        }

    @Test
    void dbCreateTableTest() {
        DBService dbService = new DBService();
        Connection c = dbService.connectToDB("mock");
        assert c != null;
        assert dbService.createTable(c);
        assert dbService.disconnectFromDB(c);
    }

    @Test
    void dbInsertIntoDBTest() {
        DBService dbService = new DBService();
        PrimeService primeService = new PrimeService();
        Connection c = dbService.connectToDB("mock");
        dbService.writeDBEntityDBToDB(mockEntity, c);
    }


    @Test
    void dbGetLastIDTest() {
        DBService dbService = new DBService();
        Connection c = dbService.connectToDB("mock");
        int test = dbService.getLastID(c);
        System.out.println(test);
    }

    @Test
    void dbCreateInsertTest() {
        DBService dbService = new DBService();
        String sql = dbService.createInsertByDBEntity(mockEntity);
        System.out.println(sql);
        String sqlCompare = "INSERT INTO PRIMES (ID,FILE_NAME,CALC_TIME,AMOUNT_OF_PRIMES,BIGGEST_PRIME) " +
                "VALUES (1,\"testFile\",5,40,71);";
        assert sqlCompare.equals(sql);
    }

    @Test
    void dbGetByIDTest() {
        DBService dbService = new DBService();
        Connection c = dbService.connectToDB("mock");
        DBEntity dbEntity = dbService.getEntityByID(c,1);
        System.out.println(mockEntity);
        System.out.println(dbEntity);
        assert dbEntity.toString().equals(mockEntity.toString());
    }
}

package Aufgaben.hiwin;

import Aufgaben.hiwin.objects.Prime;
import Aufgaben.hiwin.objects.PrimeCalculator;
import Aufgaben.hiwin.services.DBService;
import Aufgaben.hiwin.services.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@SpringBootTest
class DBTest {
    final int SMALL_AMOUNT = 10;
    final int BIG_AMOUNT = 1000;


    @Test
    void dbConnectionTest() {
        DBService dbService = new DBService();
        Connection c = dbService.connectToDB();
        assert c != null;
        try {
            c.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    void dbCreateTableTest() {
        DBService dbService = new DBService();
        Connection c = dbService.connectToDB();
        assert c != null;
        try{
            dbService.createTable(c);
        } catch (Exception e) {
            assert e.toString().contains("already exists");
        }


    }
}

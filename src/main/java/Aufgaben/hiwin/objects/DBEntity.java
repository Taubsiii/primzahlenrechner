package Aufgaben.hiwin.objects;

public class DBEntity {
    int tableID;
    String fileName;
    long calcTime;
    long amountOfPrimes;
    long biggestPrime;

    public DBEntity(int table_id, String fileName, long calcTime, long amountOfPrimes, long biggestPrime) {
        this.tableID = table_id;
        this.fileName = fileName;
        this.calcTime = calcTime;
        this.amountOfPrimes = amountOfPrimes;
        this.biggestPrime = biggestPrime;
    }

    public int getTableID() {
        return tableID;
    }

    public String getFileName() {
        return fileName;
    }

    public long getCalcTime() {
        return calcTime;
    }

    public long getAmountOfPrimes() {
        return amountOfPrimes;
    }

    public long getBiggestPrime() {
        return biggestPrime;
    }

    @Override
    public String toString() {
        return "DBEntity{" +
                "table_id=" + tableID +
                ", fileName='" + fileName + '\'' +
                ", calcTime=" + calcTime +
                ", amountOfPrimes=" + amountOfPrimes +
                ", biggestPrime=" + biggestPrime +
                '}';
    }
}

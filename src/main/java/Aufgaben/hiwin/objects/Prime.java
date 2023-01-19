package Aufgaben.hiwin.objects;
import com.google.gson.Gson;

public class Prime {

    private final int value;
    private long calculationTime;

    public Prime(int value, long calculationTime) {
        this.value = value;
        this.calculationTime = calculationTime;
    }

    public Prime(int value) {
        this.value = value;
    }

    public long getCalculationTime() {
        return calculationTime;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Prime{" +
                "value=" + value +
                ", calculationTime=" + calculationTime +
                '}';
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

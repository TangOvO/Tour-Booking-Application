package AiLvYou.entity;

public class Discount {
    private int routineID;

    private String discount1;

    private String discount2;

    private String discount3;

    private String discount4;

    @Override
    public String toString() {
        return "Discount{" +
                "routineID=" + routineID +
                ", discount1='" + discount1 + '\'' +
                ", discount2='" + discount2 + '\'' +
                ", discount3='" + discount3 + '\'' +
                ", discount4='" + discount4 + '\'' +
                '}';
    }

    public int getRoutineID() {
        return routineID;
    }

    public void setRoutineID(int routineID) {
        this.routineID = routineID;
    }

    public String getDiscount1() {
        return discount1;
    }

    public void setDiscount1(String discount1) {
        this.discount1 = discount1;
    }

    public String getDiscount2() {
        return discount2;
    }

    public void setDiscount2(String discount2) {
        this.discount2 = discount2;
    }

    public String getDiscount3() {
        return discount3;
    }

    public void setDiscount3(String discount3) {
        this.discount3 = discount3;
    }

    public String getDiscount4() {
        return discount4;
    }

    public void setDiscount4(String discount4) {
        this.discount4 = discount4;
    }
}

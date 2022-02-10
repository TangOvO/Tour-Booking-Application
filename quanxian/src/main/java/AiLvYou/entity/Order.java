package AiLvYou.entity;

import java.sql.Date;
import java.util.List;

public class Order {
    //订单ID
    private long orderID;
    //游客ID
    private long clientID;
    // 线路ID
    private long routineID;
    // 交款状态
    private boolean isPaied;
    // 交款金额
    private double payment;
    // 交款日期
    private Date date;
    // 出行状态
    private boolean isTraveled;
    // 评价状态
    private boolean isCommented;
    // 出行日期
    private String travelDate;
    //人数
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public long getRoutineID() {
        return routineID;
    }

    public void setRoutineID(long routineID) {
        this.routineID = routineID;
    }

    public boolean isPaied() {
        return isPaied;
    }

    public boolean getIsPaied() {
        return isPaied;
    }

    public void setIsPaied(boolean paied) {
        isPaied = paied;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isTraveled() {
        return isTraveled;
    }

    public boolean getIsTraveled() {
        return isTraveled;
    }

    public void setIsTraveled(boolean traveled) {
        isTraveled = traveled;
    }

    public boolean isCommented() {
        return isCommented;
    }

    public boolean getIsCommented() {
        return isCommented;
    }

    public void setIsCommented(boolean commented) {
        isCommented = commented;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", clientID=" + clientID +
                ", routineID=" + routineID +
                ", isPaied=" + isPaied +
                ", payment=" + payment +
                ", date=" + date +
                ", isTraveled=" + isTraveled +
                ", isCommented=" + isCommented +
                ", travelDate='" + travelDate + '\'' +
                '}';
    }
}

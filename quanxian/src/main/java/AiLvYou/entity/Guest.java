package AiLvYou.entity;

public class Guest {
    //订单ID
    private long orderID;
    //姓名
    private String name;
    // 身份证号
    private long identity;
    //电话号码
    private long phoneNumber;
    // 身份（成人，儿童）
    private String status;

    public Guest(long orderID, String name, long identity, long phoneNumber, String status) {
        this.orderID = orderID;
        this.name = name;
        this.identity = identity;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Guest() {

    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdentity() {
        return identity;
    }

    public void setIdentity(long identity) {
        this.identity = identity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "orderID=" + orderID +
                ", name='" + name + '\'' +
                ", identity=" + identity +
                ", phoneNumber=" + phoneNumber +
                ", status='" + status + '\'' +
                '}';
    }
}

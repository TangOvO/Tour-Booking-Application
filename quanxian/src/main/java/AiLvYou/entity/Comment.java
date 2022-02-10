package AiLvYou.entity;

import java.sql.Date;

public class Comment {
    // 线路ID
    private long routineID;
    // 客户ID
    private long clientID;
    // 客户名字
    private String name;
    // 分数
    private double score;
    // 具体评论
    private String contant;
    // 评价日期
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRoutineID() {
        return routineID;
    }

    public void setRoutineID(long routineID) {
        this.routineID = routineID;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getContant() {
        return contant;
    }

    public void setContant(String contant) {
        this.contant = contant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

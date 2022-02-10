package AiLvYou.entity;

import java.sql.Date;

public class History {
    //路线ID
    private long routineID;
    // 用户ID
    private long clientID;
    //  时间
    private Date time;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

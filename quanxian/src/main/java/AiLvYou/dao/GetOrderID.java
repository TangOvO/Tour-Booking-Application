package AiLvYou.dao;

public class GetOrderID {

    static SnowflakeWorker snowflakeWorker = new SnowflakeWorker();

    public static long getID() {
        return snowflakeWorker.nextId();
    }
}

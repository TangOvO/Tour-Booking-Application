package AiLvYou.dao;

/**
 * snowflake是Twitter开源的分布式ID生成算法，
 * 结果是一个long型的ID。其核心思想是：使用41bit作为毫秒数，
 * 10bit作为机器的ID（5个bit是数据中心，5个bit的机器ID），
 * 12bit作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID），
 * 最后还有一个符号位，永远是0。
 * 具体实现的代码可以参看https://github.com/twitter/snowflake。
 * @author user
 *
 */

public final class SnowflakeWorker {
    private long workerId = 0;
    private long dataCenterId = 0;
    private long sequence = 0L;
    private static final long twepoch = 1288834974657L;
    private static final long workerIdBits = 1L;
    private static final long dataCenterIdBits = 1L;
    private static final long maxWorkerId = -1L ^ (-1L << (int)workerIdBits);
    private static final long maxDataCenterId = -1L ^ (-1L << (int)dataCenterIdBits);
    private static final long sequenceBits = 12L;
    private static final long workerIdShift = sequenceBits;
    private static final long dataCenterIdShift = sequenceBits + workerIdBits;
    private static final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private static final long sequenceMask = -1L ^ (-1L << (int)sequenceBits);
    private long lastTimestamp = -1L;
    public SnowflakeWorker() {
        this(1, 1) ;
    }
    public SnowflakeWorker(long workerId, long dataCenterId) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("dataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    public synchronized long nextId(){
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << (int)timestampLeftShift) |
                (dataCenterId << (int)dataCenterIdShift) | (workerId << (int)workerIdShift) | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

}


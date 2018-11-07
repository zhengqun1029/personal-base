package com.personal.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * project: com.personal.ssm
 * 雪花算法实现
 * @author zhenghanbin time: 2018/11/6 19:08
 */
public class SnowflakeIdWorker{

    private final long twepoch = 1288834974657L;
    private final long workerIdBits = 5L;
    private final long dataCenterIdBits = 5L;
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    private final long sequenceBits = 12L;
    private final long workerIdShift = sequenceBits;
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long workerId;
    private long dataCenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private static volatile SnowflakeIdWorker idWorker;

    private SnowflakeIdWorker(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    public static SnowflakeIdWorker getInstance(long workerId, long dataCenterId) {
        if (idWorker == null) {
            synchronized (SnowflakeIdWorker.class) {
                if(idWorker == null) {
                    idWorker = new SnowflakeIdWorker(workerId, dataCenterId);
                }
            }
        }
        return idWorker;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
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
        return ((timestamp - twepoch) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        //  单线程数据重复性及效率测试
        List b = new ArrayList();
        Set a = new HashSet();
        SnowflakeIdWorker idWorker = SnowflakeIdWorker.getInstance(0, 0);
        Long timeStart = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
            try {
                a.add(id);
            } catch (Exception e) {
                System.out.println(b.size() + "***************************");
            }
        }
        Long timeend = System.currentTimeMillis();
        System.out.println(timeend - timeStart);
    }

}

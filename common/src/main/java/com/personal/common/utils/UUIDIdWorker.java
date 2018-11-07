package com.personal.common.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * project: com.personal.ssm
 *
 * @author zhenghanbin time: 2018/11/7 9:16
 */
public class UUIDIdWorker{

    private static volatile UUIDIdWorker uuidIdWorker;

    private UUIDIdWorker() {}

    public static UUIDIdWorker getInstance() {
        if (uuidIdWorker == null) {
            synchronized (UUIDIdWorker.class) {
                if (uuidIdWorker == null) {
                    uuidIdWorker = new UUIDIdWorker();
                }
            }
        }
        return uuidIdWorker;
    }

    /**
     * java原生UUID
     * @return
     */
    public String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * java原生UUID
     * @return
     */
    public String getID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        Set a = new HashSet();
        UUIDIdWorker uuidIdWorker = UUIDIdWorker.getInstance();
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i ++) {
            String id = uuidIdWorker.getID();
            System.out.println(id);
            a.add(id);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

}

package com.scaler.lld.designPatterns;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPoolImpl implements ConnectionPool {

    private ConcurrentHashMap<String, ConcurrentLinkedQueue<DatabaseConnection>> hm;
    private static int totSize;
    private static ConnectionPoolImpl instance;
    private String available = "available";
    private String blocked = "blocked";
    private boolean flag = false;

    @Override
    public void initializePool() {
        if (!this.flag) {
            flag = true;
            for (int i = 0; i < totSize; i++) {
                hm.get(available).add(new DatabaseConnection());
            }
        }
    }

    private ConnectionPoolImpl() {
        this.hm = new ConcurrentHashMap<>();
        this.hm.put(available, new ConcurrentLinkedQueue<>());
        this.hm.put(blocked, new ConcurrentLinkedQueue<>());
        initializePool();


    }

    @Override
    public DatabaseConnection getConnection() {
        if (instance != null) {
            if (!hm.get(available).isEmpty()) {
                DatabaseConnection con = hm.get(available).remove();
                hm.get(blocked).add(con);
                return con;
            }
        }
        return null;
    }

    @Override
    public void releaseConnection(DatabaseConnection connection) {
        if (hm.get(blocked).contains(connection)) {
            hm.get(blocked).remove(connection);
            hm.get(available).add(connection);
        }

    }

    @Override
    public int getAvailableConnectionsCount() {
        return hm.get(available).size();
    }

    @Override
    public int getTotalConnectionsCount() {
        return totSize;
    }

    public static ConnectionPoolImpl getInstance(int maxConnections) {
        if (instance == null) {
            Lock lc = new ReentrantLock();
            lc.lock();
            if (instance == null) {
                totSize = maxConnections;
                instance = new ConnectionPoolImpl();


            }
            lc.unlock();
        }

        return instance;

    }

    public static void resetInstance() {
        ConnectionPoolImpl.instance = null;
        totSize = 0;
    }
}

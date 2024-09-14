package com.scaler.lld.designPatterns;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileBasedConfigurationManagerImpl extends FileBasedConfigurationManager {
    private static FileBasedConfigurationManagerImpl instance;
    private FileBasedConfigurationManagerImpl(){};

    @Override
    public String getConfiguration(String key) {
        // TODO Auto-generated method stub

        return this.properties.getProperty(key);
    }

    @Override
    public <T> T getConfiguration(String key, Class<T> type) {
        if(this.properties.getProperty(key) == null) throw new IllegalArgumentException("wrong");
        return convert(this.properties.getProperty(key),type);
    }

    @Override
    public void setConfiguration(String key, String value) {
        this.properties.setProperty(key, value);
    }

    @Override
    public <T> void setConfiguration(String key, T value) {
        // TODO Auto-generated method stub
        if (value.getClass() == String.class) this.properties.setProperty(key,(String)value);
        else throw new IllegalArgumentException("Wrong argument type");


    }

    @Override
    public void removeConfiguration(String key) {
        // TODO Auto-generated method stub
        this.properties.remove(key);
    }

    @Override
    public void clear() {
        this.properties.clear();
    }

    public static FileBasedConfigurationManager getInstance() {
        if (instance == null) {
            Lock lc = new ReentrantLock();
            lc.lock();
                if (instance == null) {

                    instance = new FileBasedConfigurationManagerImpl();
                }
                lc.unlock();
            }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

}
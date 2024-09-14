package com.scaler.lld.designPatterns;

import org.springframework.boot.logging.LogLevel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LoggerImpl implements Logger {
    private String logFilePath;
    private static LoggerImpl instance;
    private FileWriter fw;
    private PrintWriter pw;


    private LoggerImpl() {
    }


    public static LoggerImpl getInstance() {
        if (instance == null) {
            Lock lc = new ReentrantLock();
            lc.lock();
            if (instance == null) instance = new LoggerImpl();
            lc.unlock();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }


    public void log(LogLevel level, String message) {
        if(this.logFilePath.isEmpty()) throw new IllegalStateException("Illegal State");
        String mess = level + " " + java.time.LocalDateTime.now() + " " + message;
        pw.println(mess);
    }


    @Override
    public void setLogFile(String filePath)  {
        this.logFilePath = filePath;

        try {
            this.fw = new FileWriter(this.logFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.pw = new PrintWriter(fw);
    }

    @Override
    public String getLogFile() {
        return this.logFilePath;
    }

    @Override
    public void flush() {
        pw.flush();

    }

    @Override
    public void close()  {
        pw.close();

        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.logFilePath = "";
    }
}


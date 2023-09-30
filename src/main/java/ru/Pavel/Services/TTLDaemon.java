package ru.Pavel.Services;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TTLDaemon {
    private TTLService ttlService = new TTLService();
    private TimeUnit healthySleep;
    public TTLDaemon(){
        healthySleep = TimeUnit.HOURS;
        startThread();
    }
    private void startThread(){
        Thread ttlThread = new Thread(() -> {
            while (true) {
                ttlService.run();
                try {
                    healthySleep.sleep(12);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        ttlThread.start();
    }
}

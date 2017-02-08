package service;


import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by roman on 5/16/15.
 */
public class JobController {

    final private static double STARTUP = 1486538524000D;
    final private static double ATTACK = 172_800_000D;
    final private static double MAX = 50D;
    final private static double [] dailyVolumes = {
            4D,  2D,  0D,  0D,  1D,  2D,  2D,  4D,  6D,  6D,  6D,  7D,
            9D,  9D,  9D, 11D, 14D, 18D, 18D, 18D, 16D, 16D, 11D,  8D
    };
    private double ratio = 1D;
    final private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    final private ThreadPoolExecutor executorService;

    public JobController(ThreadPoolExecutor executorService) {
        this.executorService = executorService;
        init();
    }

    private void init() {
        scheduler.scheduleAtFixedRate(this::resetDaily, 0L, 6L, TimeUnit.HOURS);
        scheduler.scheduleAtFixedRate(this::resetHourly, 0L, 1L, TimeUnit.HOURS);
    }

    public void resetDaily() {
        double factor = getCurrentFactor();
        System.out.println("Current factor: " + factor);
        ratio = (factor / 4) + new Random().nextDouble();
        System.out.println("New ratio: " + ratio);
    }

    public void resetHourly() {
        Calendar now = Calendar.getInstance();
        double currentVolume = dailyVolumes[now.get(Calendar.HOUR_OF_DAY)];
        int newPoolSize = (int) (currentVolume * ratio);
        executorService.setCorePoolSize(newPoolSize);
        executorService.setMaximumPoolSize(newPoolSize);
        System.out.println("New pool size: " + newPoolSize);
    }

    private double getCurrentFactor() {
        long time = System.currentTimeMillis();
        return Math.min(MAX, MAX / ATTACK * (time - STARTUP));
    }

}

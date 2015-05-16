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

    final private static double [] daily = {
            4D,  2D,  0D,  0D,  1D,  2D,  2D,  4D,  6D,  6D,  6D,  7D,
            9D,  9D,  9D, 11D, 14D, 18D, 18D, 18D, 16D, 16D, 11D,  8D
    };
    private double ratio = 1D;
    final private ThreadPoolExecutor executor;
    final private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public JobController(ThreadPoolExecutor executorService) {
        executor = executorService;
    }

    public void init() {
        scheduler.scheduleAtFixedRate(this::resetDaily, 0L, 1L, TimeUnit.DAYS);
        scheduler.scheduleAtFixedRate(this::resetHourly, 0L, 1L, TimeUnit.HOURS);
    }

    public void resetDaily() {
        ratio = 2D * new Random().nextDouble();
        System.out.println("New ratio - " + ratio);
    }

    public void resetHourly() {
        Calendar now = Calendar.getInstance();
        double currentVolume = daily[now.get(Calendar.HOUR_OF_DAY)];
        int newPoolSize = (int) (currentVolume * ratio);
        executor.setCorePoolSize(newPoolSize);
        executor.setMaximumPoolSize(newPoolSize);
        System.out.println("New pool size - " + newPoolSize);
    }

}

package tools;

/**
 * Created by Roman on 04.05.2015.
 */
public class ThreadTool {
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}

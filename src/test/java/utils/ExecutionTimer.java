package utils;

public class ExecutionTimer {

    private ExecutionTimer() {}

    private static final ThreadLocal<Long> START_TIME =
            new ThreadLocal<>();

    public static void startTimer() {

        START_TIME.set(
                System.currentTimeMillis());

    }

    public static long stopTimer() {

        return System.currentTimeMillis()
                - START_TIME.get();

    }

}
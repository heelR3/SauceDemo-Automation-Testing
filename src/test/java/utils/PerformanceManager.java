package utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PerformanceManager {

    private PerformanceManager() {}

    private static final ConcurrentMap<String, Long>
            chromeResults =
            new ConcurrentHashMap<>();

    private static final ConcurrentMap<String, Long>
            edgeResults =
            new ConcurrentHashMap<>();

    public static synchronized void recordExecution(
            String testCaseId,
            String browser,
            long duration) {

        if ("chrome".equalsIgnoreCase(browser)) {

            chromeResults.put(
                    testCaseId,
                    duration);

        }

        else if ("edge".equalsIgnoreCase(browser)) {

            edgeResults.put(
                    testCaseId,
                    duration);

        }

        if (chromeResults.containsKey(testCaseId)
                && edgeResults.containsKey(testCaseId)) {

            ExcelWriter.writePerformanceComparison(
                    testCaseId,
                    chromeResults.get(testCaseId),
                    edgeResults.get(testCaseId));
        }
    }
}
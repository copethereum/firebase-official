package common.core;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;

public class TestNGTestPooler {

    private static List<ITestResult> testsPool = new ArrayList<ITestResult>();
    private static int poolSize = Integer.parseInt(MTFProperties.testng.getProperty("test.pool.size"));

    public static synchronized boolean allowTestRun(ITestResult tr) {
        if(testsPool.size() < poolSize) {
            testsPool.add(tr);
            return true;
        } else {
            return false;
        }
    }

    public static synchronized boolean removeTestFromPool(ITestResult tr) {
        boolean removed = testsPool.remove(tr);
        return removed;
    }

}

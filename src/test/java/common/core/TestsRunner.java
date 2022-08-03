package common.core;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class TestsRunner implements IHookable {

    @Override
    public void run(IHookCallBack cb, ITestResult tr) {
        while(!TestNGTestPooler.allowTestRun(tr)) {}
            runTestMethod(cb, tr);
            TestNGTestPooler.removeTestFromPool(tr);
    }

    private void runTestMethod(IHookCallBack cb, ITestResult tr) {
        cb.runTestMethod(tr);
    }

}

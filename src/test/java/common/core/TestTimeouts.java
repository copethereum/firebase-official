package common.core;

import java.util.concurrent.TimeUnit;

public class TestTimeouts {

    public final static String DRIVER_IMPLICITLY_WAIT_DEFAULT = "driver.implicitly.wait.default";
    public final static String DRIVER_WAIT_DEFAULT = "driver.wait.default";
    public final static String API_TIMEOUT = "request.timeout";

    public static long getTimeout(String timeout, TimeUnit timeUnit) {
        timeout = TestProperties.timeouts.getProperty(timeout);
        return timeUnit.convert(Long.parseLong(timeout), TimeUnit.MILLISECONDS);
    }

    public static int getTimeout(String timeout) {
        timeout = TestProperties.timeouts.getProperty(timeout);
        return Integer.parseInt(timeout);
    }

}

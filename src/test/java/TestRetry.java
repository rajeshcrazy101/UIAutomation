import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetry implements IRetryAnalyzer {
    private int maxRetryCount=2;
    private int count=0;

    @Override
    public boolean retry(ITestResult result) {
        if(count < maxRetryCount)
        {
            count++;
            return true;
        }
        return false;
    }
}

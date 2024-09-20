package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testProject.createExcelFile;

import java.io.File;

public class Listeners implements ITestListener {
    private ExtentReports extent;
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Initialize the report
        String path = System.getProperty("user.dir") + "\\report\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Video Platform Test Results");
        reporter.config().setDocumentTitle("Automation Test Report");
        reporter.config().setTheme(Theme.DARK);
        
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Helen");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create test entry in report
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // Set the current test in ThreadLocal
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass("Test passed");
        test=extent.createTest(result.getName());
        test.log(Status.PASS,"Testcase is Passed:"+result.getName());
        
        
        
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());

        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            String screenshotDirectory = System.getProperty("user.dir") + "/screenshots/";
            String fileName = result.getMethod().getMethodName() + "_" + System.currentTimeMillis() + ".png";
            String filePath = screenshotDirectory + fileName;

            try {
            	createExcelFile.getScreenshot(filePath, driver);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (new File(filePath).exists()) {
                extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
            } else {
                extentTest.get().fail("Screenshot file path is invalid or file does not exist.");
                test.log(Status.FAIL,"Testcase is Failed:"+result.getName());
                test.log(Status.FAIL,"Testcase is Failed cause id:"+result.getThrowable());
            }
        } else {
            extentTest.get().fail("Driver is null. Screenshot could not be captured.");
            test.log(Status.FAIL,"Testcase is Failed:"+result.getName());
            test.log(Status.FAIL,"Testcase is Failed cause id:"+result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the report
        extent.flush();
    }
    
    
}

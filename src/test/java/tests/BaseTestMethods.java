package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class BaseTestMethods {

    protected WebDriver driver;

    //public static final String RESOURCES_DIR = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    //public static final String REPORT_DIR = RESOURCES_DIR.concat("reports" + File.separator);
    //public static final String SCREENSHOT_DIR = RESOURCES_DIR.concat("screenshots" + File.separator);

    @BeforeMethod
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //cleanDirectory(SCREENSHOT_DIR);
        //cleanDirectory(REPORT_DIR);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void cleanUp(ITestResult testResult) {
        //takeScreenShot(testResult);
        if (driver != null) {
            driver.close();
        }
    }

    /*private void cleanDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);
        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0) {
            System.out.printf("All files are deleted in Directory: %s%n", directoryPath);
        } else {
            System.out.printf("Unable to delete the files in Directory:%s%n", directoryPath);
        }
    }*/

   /* private void takeScreenShot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName();
            try {
                FileUtils.copyFile(screenshot, new File(SCREENSHOT_DIR.concat(testName).concat(".jpg")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }*/
}

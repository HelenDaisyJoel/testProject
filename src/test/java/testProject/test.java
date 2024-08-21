package testProject;

import org.openqa.selenium.WebDriver; // Import WebDriver
import org.openqa.selenium.chrome.ChromeDriver; // Import ChromeDriver

public class test {

    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
    	 System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chrome\\chromedriver.exe");

        // Initialize WebDriver with ChromeDriver
        WebDriver driver = new ChromeDriver();
        System.out.println("Testing");

        // Close the browser
        driver.quit();
    }
}

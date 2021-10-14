`GroovyTestcontainers.java`

```
@Grapes([
@Grab('io.github.bonigarcia:webdrivermanager:5.0.3'),
@Grab('org.seleniumhq.selenium:selenium-chrome-driver:3.141.59'),
@Grab("org.testcontainers:selenium:1.16.0"),
@GrabConfig(systemClassLoader=true)
])

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testcontainers.containers.BrowserWebDriverContainer 

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


class GroovyTestcontainers {

    WebDriver driver;

    String dothething(Integer i) { 
        WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();

        def container = new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions())
        container.start()
        driver = container.getWebDriver()

        // Exercise
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String title = driver.getTitle();

        TakesScreenshot screenshot =((TakesScreenshot)driver);
        File screenshotFile=screenshot.getScreenshotAs(OutputType.FILE);
        new File('screenshot.png').bytes = screenshotFile.bytes

        // Verify
        driver.quit();
    }

    static void main(String[] args) {
        new GroovyTestcontainers().dothething()
    }
}
```

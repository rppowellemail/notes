# Groovy(Java) Webdriver

References:

* https://github.com/bonigarcia/webdrivermanager
* https://bonigarcia.dev/webdrivermanager/

```
@Grapes([
@Grab('io.github.bonigarcia:webdrivermanager:5.0.3'),
@Grab('org.seleniumhq.selenium:selenium-chrome-driver:3.141.59'),
@Grab('org.junit.jupiter:junit-jupiter:5.5.0'),
@Grab("org.assertj:assertj-core:3.21.0"),
@GrabConfig(systemClassLoader=true)
])

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class GroovySelenium001 {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void seleniumtest() {
        // Exercise
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String title = driver.getTitle();

        // Verify
        assertThat(title).contains("Selenium WebDriver");
    }
}
```

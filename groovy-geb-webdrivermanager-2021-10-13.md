

```
@Grapes([
  @Grab("org.gebish:geb-core:5.0"),
  @Grab('io.github.bonigarcia:webdrivermanager:5.0.3'),
  @Grab('org.seleniumhq.selenium:selenium-chrome-driver:3.141.59')
])

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver

WebDriverManager.chromedriver().setup()

def configuration = new geb.Configuration(driver: { new ChromeDriver() })

geb.Browser.drive(configuration) {
	go "https://grydeske.dk"
	assert $(".topbanner h2").text() == "Home of Jacob Aae Mikkelsen"
}
println "Success :)"
```

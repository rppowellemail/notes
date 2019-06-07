```
// all-in-one minimalistic Geb Example
// See also: https://gist.github.com/mkutz/58d3ae2f07c8c519c8900ab5b25f5e87

@Grapes([
    @Grab("org.gebish:geb-core:2.3.1"),
    @Grab(group='io.github.bonigarcia', module='webdrivermanager', version='3.4.0'),
    @Grab(group='org.seleniumhq.selenium', module='selenium-chrome-driver', version='3.141.0'),
    @Grab(group='org.seleniumhq.selenium', module='selenium-support', version='3.141.0'),
    @Grab(group='net.lightbody.bmp', module='browsermob-core', version='2.1.5')
])

import geb.Browser
import geb.Configuration
import io.github.bonigarcia.wdm.WebDriverManager
import java.net.Inet4Address
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import net.lightbody.bmp.BrowserMobProxy
import net.lightbody.bmp.BrowserMobProxyServer
import net.lightbody.bmp.client.ClientUtil
import net.lightbody.bmp.core.har.Har
import net.lightbody.bmp.core.har.Har
import net.lightbody.bmp.core.har.HarEntry
import net.lightbody.bmp.core.har.HarEntry
import net.lightbody.bmp.proxy.CaptureType
import org.openqa.selenium.OutputType
import org.openqa.selenium.Proxy
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

def datetimestamp() {
    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss-SSS").withZone(ZoneId.systemDefault()).format(Instant.now())
}

BrowserMobProxy browserMobProxy = new BrowserMobProxyServer();
browserMobProxy.setTrustAllServers(true);
browserMobProxy.start();
browserMobProxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT)

println "BrowserMob Proxy running on port: " + browserMobProxy.getPort()

String hostIp = Inet4Address.getLocalHost().getHostAddress();

Proxy seleniumProxy = ClientUtil.createSeleniumProxy(browserMobProxy);

System.out.println("HttpProxy:" + seleniumProxy.getHttpProxy());
System.out.println("SslProxy:" + seleniumProxy.getSslProxy());

Browser.drive(
    new Configuration(
        driver: {
            WebDriverManager.chromedriver().setup();
            ChromeOptions o = new ChromeOptions()
            //o.addArguments('headless')
            o.setCapability(CapabilityType.PROXY, seleniumProxy);
            new ChromeDriver(o)
        },
        //reportsDir: "geb-reports"
    )
) {

    browserMobProxy.newHar();
    go "http://gebish.org"
    assert title == "Geb - Very Groovy Browser Automation" 
    $("div.menu a.manuals").click() 
    waitFor { !$("#manuals-menu").hasClass("animating") } 
    $("#manuals-menu a")[0].click() 
    assert title.startsWith("The Book Of Geb") 

    filename = "screenshot-" + datetimestamp() + ".png"
    new File(filename) << driver.getScreenshotAs(OutputType.FILE).bytes
    println "Saving screenshot $filename"

    //report "geb home page"
}

Har har = browserMobProxy.getHar();
har.getLog().getEntries().eachWithIndex { it, i ->
    println "$i ${it.getRequest().getUrl()}"
}
har.writeTo(new File("har-"+datetimestamp() + ".har"))
browserMobProxy.stop();
```

```
groovy -Dgroovy.grape.report.downloads=true -Divy.message.logger.level=4 MinimalGebExample-2019-06-07-003.groovy
```

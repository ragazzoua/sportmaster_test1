package resources.Driver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class BrowserDriver {

    public void settingBrowserDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();

        capabilities.setCapability("enableVNC", true);
        RemoteWebDriver driver = new RemoteWebDriver(
                URI.create("http://127.0.0.1:4444/wd/hub").toURL(), capabilities);


        WebDriverRunner.setWebDriver(driver);

        Configuration.baseUrl = "https://www.sportmaster.ru";
        Configuration.startMaximized = true;
    }
}

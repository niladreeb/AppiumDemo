package appiumtests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;

public class AndroidTest {
    AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Emulator");
        cap.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");

        cap.setCapability("appPackage", "my.test.app");
        cap.setCapability("appActivity", "my.test.app.features.splash.SplashActivity");
        cap.setCapability(MobileCapabilityType.NO_RESET, true);

        driver = new AndroidDriver<MobileElement>(url, cap);
    }

    @Test
    public void testScreen() throws InterruptedException {
        System.out.println("Application started");
        assertEquals("my.test.app.features.welcome.WelcomeActivity", driver.currentActivity());

        MobileElement title = driver.findElementById("welcomeTitle");
        assertEquals(title.getText(), "Welcome to appium demo app");

        MobileElement subTitle = driver.findElementById("welcomeSubtitle");
        assertEquals(subTitle.getText(), "Test your appium skills here");

        MobileElement login = driver.findElementById("button1");
        assertEquals("Click me", login.getText());
        login.click();

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        assertEquals("my.test.app.features.main.MainActivity", driver.currentActivity());

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        MobileElement register = driver.findElementById("button2");
        assertEquals("I'm clickable", register.getText());
        register.click();

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        assertEquals("my.test.app.features.main.SomeActivity", driver.currentActivity());
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        MobileElement forgotPassword = driver.findElementById("button3");
        assertEquals("Test", forgotPassword.getText());
        forgotPassword.click();

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        assertEquals("my.test.app.features.test.TestActivity", driver.currentActivity());
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        assertEquals("my.test.app.features.welcome.WelcomeActivity", driver.currentActivity());
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}

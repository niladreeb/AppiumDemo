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

        cap.setCapability("appPackage", "com.demo.appiumdemo");
        cap.setCapability("appActivity", "com.demo.appiumdemo.MainActivity");
        cap.setCapability(MobileCapabilityType.NO_RESET, true);

        driver = new AndroidDriver<MobileElement>(url, cap);
    }

    @Test
    public void testScreen() throws InterruptedException {
        System.out.println("Application started");
        assertEquals(".MainActivity", driver.currentActivity());

        MobileElement title = driver.findElementById("welcome");
        assertEquals(title.getText(), "Welcome to\nAppium Demo app");

        MobileElement enter = driver.findElementById("enter");
        assertEquals("ENTER", enter.getText());
        enter.click();

        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        assertEquals(".Main2Activity", driver.currentActivity());

        MobileElement button1 = driver.findElementById("button1");
        assertEquals("1", button1.getText());

        MobileElement buttonPlus = driver.findElementById("plus");
        assertEquals("+", buttonPlus.getText());

        MobileElement button2 = driver.findElementById("button2");
        assertEquals("2", button2.getText());

        MobileElement button5 = driver.findElementById("button5");
        assertEquals("5", button5.getText());

        MobileElement buttonEquals = driver.findElementById("equals");
        assertEquals("=", buttonEquals.getText());

        button1.click();
        button2.click();
        buttonPlus.click();
        button2.click();
        button5.click();
        buttonEquals.click();

        MobileElement expression = driver.findElementById("expression");
        assertEquals("12+25", expression.getText());

        MobileElement result = driver.findElementById("result");
        assertEquals("37", result.getText());

    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}

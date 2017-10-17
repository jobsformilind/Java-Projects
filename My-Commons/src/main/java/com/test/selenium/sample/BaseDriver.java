
import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverTest {
	private static final String FIRFOX_PATH = "Tools/Firefox/firefox.exe";
	private static final String CHROME_PATH = "Tools/chromedriver.exe";
	private static final String PHANTOM_PATH = "Tools/phantomjs.exe";

	public static void main(String[] args) throws Exception {
		initDrivers();
		WebDriver webDriver = getDriver("chrome");
		for (int i = 0; i < 2; i++) {
			doABP(i, webDriver);
		}
		closeDriver(webDriver);
	}

	private static void doABP(int cnt, WebDriver webDriver) {
		try {
		webDriver.get("http://abpmajha.abplive.in/");
		Thread.sleep(1000);
		webDriver.findElement(By.id("PDI_answer45091451")).click();
		webDriver.findElement(By.id("pd-vote-button9850489")).click();
		String text = webDriver.findElement(By.className("pds-question-top")).getText();
		System.out.println(cnt + " : " + text);
		webDriver.manage().deleteAllCookies();
		} catch(Exception ex) {
			System.out.println("Exception occured.." + ex.getMessage());
		}
	}

	private static WebDriver getDriver(String type) {
		WebDriver webDriver = null;
		if ("firefox".equals(type)) {
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
			webDriver = new FirefoxDriver(firefoxProfile);
		} else if ("chrome".equals(type)) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("-incognito");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			webDriver = new ChromeDriver(capabilities);
		} else if ("phantom".equals(type)) {
			PhantomJSDriverService service = PhantomJSDriverService.createDefaultService(getPhantomJSCapabilities());
			webDriver = new PhantomJSDriver(service, getPhantomJSCapabilities());
		}
		
		try {
			webDriver.manage().window().maximize();
		} catch (Exception e) {
		}		
		return webDriver;
	}

	private static DesiredCapabilities getPhantomJSCapabilities() {
		ArrayList<String> cliArgsCap = new ArrayList<String>();
		cliArgsCap.add("--disk-cache=true");
		cliArgsCap.add("--load-images=false");
		cliArgsCap.add("--webdriver-loglevel=NONE");
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.phantomjs();
		desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
		desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "Accept-Encoding", "deflate, sdch");
		desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + CapabilityType.SUPPORTS_ALERTS, "true");
		desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + CapabilityType.SUPPORTS_FINDING_BY_CSS, "true");
		desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + CapabilityType.SUPPORTS_JAVASCRIPT, "true");
		desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "true");
		return desiredCapabilities;
	}

	private static void closeDriver(WebDriver webDriver) throws Exception {
		webDriver.manage().deleteAllCookies();
		webDriver.close();
		webDriver.quit();
	}

	private static void initDrivers() {
		System.setProperty("webdriver.chrome.driver", new File(CHROME_PATH).getAbsolutePath());
		System.setProperty("webdriver.firefox.bin", new File(FIRFOX_PATH).getAbsolutePath());
		System.setProperty("phantomjs.binary.path", new File(PHANTOM_PATH).getAbsolutePath());
	}

}

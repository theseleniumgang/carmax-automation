/**
 *   File Name: BasicTest.java<br>
 *
 *   Gavandi, Ronak<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Nov 2, 2016
 *
 */

package com.sqa.rg;

import java.util.concurrent.*;

import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;

import com.sqa.rg.helpers.*;

/**
 * BasicTest //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Gavandi, Ronak
 * @version 1.0.0
 * @since 1.0
 *
 */
public class BasicAutoTest {
	private String baseURL;
	private WebDriver driver;
	private Logger log = Logger.getLogger(BasicAutoTest.class);

	/**
	 * @param baseURL
	 * @param driver
	 */
	public BasicAutoTest(String baseURL) {
		this.baseURL = baseURL;
	}

	/**
	 * @return the baseURL
	 */
	public String getBaseURL() {
		return this.baseURL;
	}

	/**
	 * @return the driver
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * @return the log
	 */
	public Logger getLog() {
		return this.log;
	}

	@BeforeClass
	public void setupTest() {
		getLog().info("Setting up driver.");
		this.driver = new FirefoxDriver();
		getLog().trace("Setting implicit wait to 30 seconds.");
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getLog().trace("Setting window to fullscreen.");
		this.driver.manage().window().maximize();
		getLog().info("Going to baseURL");
		this.driver.get(this.baseURL);
		getLog().debug("Clearing cookies.");
		this.driver.manage().deleteAllCookies();
	}

	public boolean takeScreenShot(String fileName) {
		return AutoBasics.takeScreenshot(this.driver, "screenshots/", fileName, getLog());
	}

	@AfterClass
	public void tearDown() {
		getLog().info("Closing all driver windows.");
		this.driver.quit();
	}
}

/**
 *   File Name: AutoBasics.java<br>
 *
 *   Gavandi, Ronak<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Nov 2, 2016
 *
 */

package com.sqa.rg.helpers;

import java.io.*;
import java.util.*;

import org.apache.commons.io.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;

/**
 * AutoBasics //ADDD (description of class)
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
public class AutoBasics {
	private static final String DEFAULT_CONFIG_PROPERTIES_LOCATION = "src/main/resources/config.properties";
	private static final String DEFAULT_CONFIG_SAVE_LOCATION = "src/main/resources/saved.properties";
	private static final String DEFAULT_SCREENSHOT_FILENAME = "screenshot";
	private static final String DEFAULT_SCREENSHOT_SAVE_LOCATION = "screenshots/";
	private static final String FILE_EXTENSION = ".jpg";

	public static Properties evalProperties() throws IOException {
		return evalProperties(DEFAULT_CONFIG_PROPERTIES_LOCATION);
	}

	public static Properties evalProperties(String fileLocation) throws IOException {
		Properties props = new Properties();
		File file = new File(fileLocation);
		FileInputStream fis = new FileInputStream(file);
		props.load(fis);
		return props;
	}

	public static String evalProperty(Properties props, String propKey) {
		return props.getProperty(propKey);
	}

	public static String evalProperty(String propKey) throws IOException {
		return evalProperty(DEFAULT_CONFIG_PROPERTIES_LOCATION, propKey);
	}

	public static String evalProperty(String fileLocation, String propKey) throws IOException {
		Properties props = evalProperties(fileLocation);
		return evalProperty(props, propKey);
	}

	public static List<WebElement> getByTagName(WebDriver driver, String tagName) {
		List<WebElement> elements = driver.findElements(By.tagName(tagName));
		return elements;
	}

	public static List<WebElement> getCSSPropertyBasedElements(WebDriver driver, By locator, String prop,
			String value) {
		List<WebElement> elements = driver.findElements(locator);
		List<WebElement> matchingElements = new ArrayList<WebElement>();
		String elementValue;
		for (int i = 0; i < elements.size(); i++) {
			elementValue = elements.get(i).getCssValue(prop);
			if (elementValue.equalsIgnoreCase(value)) {
				matchingElements.add(elements.get(i));
			}
		}
		return elements;
	}

	public static List<WebElement> getLinks(WebDriver driver) {
		List<WebElement> elements = getByTagName(driver, "a");
		return elements;
	}

	public static List<WebElement> getPictures(WebDriver driver) {
		List<WebElement> elements = getByTagName(driver, "img");
		return elements;
	}

	public static List<String> getTextContents(WebDriver driver, By locator) {
		List<WebElement> elements = driver.findElements(locator);
		List<String> elementTexts = new ArrayList<String>();
		String text;
		for (int i = 0; i < elements.size(); i++) {
			text = elements.get(i).getText();
			if (!text.equals("")) {
				elementTexts.add(text);
			}
		}
		return elementTexts;
	}

	public static boolean isElementPresent(WebDriver driver, By locator, Logger log) {
		try {
			driver.findElement(locator);
		} catch (Exception e) {
			log.warn("Element " + locator.toString() + " was not located on page " + driver.getTitle() + ".");
			return false;
		}
		log.info("Element " + locator.toString() + " was located on page [" + driver.getTitle() + "] successfully.");
		return true;
	}

	public static boolean takeScreenshot(WebDriver driver) {
		return takeScreenshot(driver, DEFAULT_SCREENSHOT_SAVE_LOCATION, DEFAULT_SCREENSHOT_FILENAME, null);
	}

	public static boolean takeScreenshot(WebDriver driver, String location, String fileName) {
		return takeScreenshot(driver, location, fileName, null);
	}

	public static boolean takeScreenshot(WebDriver driver, String location, String fileName, Logger log) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(location + fileName + FILE_EXTENSION));
			log.info("screenshot saved with filename '" + fileName + FILE_EXTENSION + "' at location '" + location
					+ "'");
		} catch (Exception e) {
			if (log != null) {
				log.error("failed to save a screenshot at " + location + fileName + FILE_EXTENSION);
			}
			return false;
		}
		return true;
	}

	public static boolean writeProperties(Properties props, String key, String value) throws IOException {
		return writeProperties(props, DEFAULT_CONFIG_SAVE_LOCATION, key, value);
	}

	public static boolean writeProperties(Properties props, String fileLocation, String key, String value)
			throws IOException {
		try {
			props.setProperty(key, value);
			File saveFile = new File(fileLocation);
			FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
			props.store(fileOutputStream, "Saved Config Details Ron.");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
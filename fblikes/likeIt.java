package fblikes;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


public class likeIt {

	public static void main(String[] args) {

		Scanner scanny = new Scanner(System.in);
		System.out.println("Please enter your username: ");
		String userName = scanny.nextLine(); // get username input
		System.out.println("Please enter your password: ");
		String password = scanny.nextLine(); // get password input

		ChromeOptions options = new ChromeOptions(); // create new options object
		options.addArguments("--disable-notifications"); // add proper argument

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe"); // run the chromedriver in the package

		WebDriver driver = new ChromeDriver(options); // create a new WebDriver

		driver.get("https://www.facebook.com"); // open fb
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys(userName); // input username
		driver.findElement(By.id("pass")).sendKeys(password); // input password
		driver.findElement(By.id("loginbutton")).click(); // click the login button

		List<WebElement> likeButtons = driver.findElements(By.className("_666k")); // find all instances of the like
		try {
			TimeUnit.SECONDS.sleep(5); // wait 5 seconds for the page to finish loading
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // button

		int indexToClick = (int) (Math.random() * likeButtons.size()); // pick a random index
		Actions actions = new Actions(driver);
		actions.moveToElement(likeButtons.get(indexToClick));
		try {
			likeButtons.get(indexToClick).isDisplayed();
		} catch (NoSuchElementException e) {
			actions.moveToElement(likeButtons.get(indexToClick));
			throw new RuntimeException("Element not found on page, retrying scroll... ");
		} finally {
			try {
				likeButtons.get(indexToClick).click(); // click the like button at that index
			} catch (ElementNotVisibleException e) {
				System.out.println(
						"The program has failed to find the like button. Please run the code again for better results. ");
				scanny.close();
			}
		}


	}

}

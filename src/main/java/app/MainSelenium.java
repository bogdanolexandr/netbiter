package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import utils.Settings;

public class MainSelenium {
	
	private String webdriver = Settings.getValue("webdriver");
	private String webdriverPath = Settings.getValue("webdriver_path");
	private static MailSender sender = new MailSender();
	
	public static void main(String[] args) throws IOException, InterruptedException {	
		
		Dimension d = new Dimension (1000, 400);
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(d);

		try {
			while (true) {
				driver.get("http://10.0.2.93/cgi-bin/read.cgi?page=login.html");
				login(driver);		
				checkColor(driver);
				logOut(driver);
				
				driver.get("http://10.0.2.91/cgi-bin/read.cgi?page=templates/main.html&id=1&path=Page;id=1");
				login(driver);
				checkColor(driver);
				logOut(driver);
		
			}
		} catch (Exception e) {
			driver.quit();
			main(args);
		}
	
	
	}

	

	private static void logOut(WebDriver driver) {
		driver.findElement(By.cssSelector("body > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td:nth-child(2) > a")).click();
	}

	private static void checkColor(WebDriver driver) {
		try {
			String rgba = driver.findElement(By.id("menu_alarm")).getCssValue("color");
			if (!rgba.equals("rgba(0, 0, 0, 1)")) {
				beep(driver);
				sender.sendMail("alarm");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void login(WebDriver driver) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#userid")).sendKeys("admin");
		driver.findElement(By.cssSelector("#passwd")).sendKeys("admin");
		driver.findElement(By.cssSelector("#loginButton")).click();
		Thread.sleep(15000);
	}
	
	private static void beep(WebDriver driver) throws FileNotFoundException, JavaLayerException {
		FileInputStream file = new FileInputStream("src/main/resources/fire_smoke_alarm.mp3");
		Player player = new Player(file);
		player.play();
	}
}

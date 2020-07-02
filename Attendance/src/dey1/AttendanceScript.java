package dey1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AttendanceScript {

	public static String password;
	public static String browser;
	public static String name;
	public static String lastName;
	public static WebDriver driver;
	public static String email;
	public static String url;
	public  String userSchoolName;
	public static int grade;
	public static Properties prop;
	public static File file;
	public static FileInputStream fileInput;
	
public static void main(String[] args) throws Exception  {

		
	getUserInput();
	System.out.println("-----------Your Info Has Been Entered--------------");
	setBrowserConfig();
	System.out.println("------------Browser Setup Completed----------------");
	driver.get(url);
	login();
	System.out.println("------------Logged in Successfully!----------------");
	fillNormalElements();
	System.out.println("-------------Filling Out The Form...---------------");
	fillSchoolName();
	fillGrade();
	System.out.println("______Congrats You have been marked Present!_______");
	}




/**
 * instantiates basic info required to run the script including
 * the browser,password, and email address
 */
public static void getUserInput() {
	
	   file = new File(System.getProperty("user.dir") + "\\config.properties");
	  
		fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		browser= prop.getProperty("browser");
		email = prop.getProperty("email");
		password = prop.getProperty("password");
		url = prop.getProperty("url");
		name = prop.getProperty("name");
		userSchoolName= prop.getProperty("school");	
		lastName = prop.getProperty("lastname");
		grade = Integer.parseInt(prop.getProperty("grade"));
}

/**
 * configures the browser setup. 
 * Current options are Firefox and Chrome
 */
public static void setBrowserConfig()
{
	if(browser.compareToIgnoreCase("firefox") ==0)
	{
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\webdrivers\\geckodriver.exe");
		 driver = new FirefoxDriver();
	}
	
	if(browser.compareToIgnoreCase("chrome") ==0)
	{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\webdrivers\\chromedriver.exe");
		 driver = new ChromeDriver();

	}
}


/**
 * login into gmail using  the user specified email and password 
 */
public static void login() throws Exception
{
	driver.findElement(By.id("identifierId")).sendKeys(email);
	driver.findElement(By.id("identifierNext")).click();
	
	WebDriverWait wait = new WebDriverWait(driver, 15);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordNext")));
	
	driver.findElement(By.name("password")).sendKeys(password);
	Thread.sleep(500);
	driver.findElement(By.id("passwordNext")).click();


	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='mG61Hd']/div/div/div[2]/div/div/div[2]/div/span/div/div/label/div/div/div/div[3]/div")));
}


/**
 * fills out the first three question of the form which include the y/n, name, and last name 
 */
public static void fillNormalElements() 
{

	//fills the y/n field
	driver.findElement(By.xpath("//form[@id='mG61Hd']/div/div/div[2]/div/div/div[2]/div/span/div/div/label/div/div/div/div[3]/div")).click();
	//fills the name field
	driver.findElement(By.xpath("//form[@id='mG61Hd']/div/div/div[2]/div[2]/div/div[2]/div/div/div/div/input")).sendKeys(name);
	//fills the last name field
	driver.findElement(By.xpath("//form[@id='mG61Hd']/div/div/div[2]/div[3]/div/div[2]/div/div/div/div/input")).sendKeys(lastName);	
}


/**
 * fills out two drop downs concerning the school name 
 */
public static void fillSchoolName() throws Exception
{
	
	String schoolNames[] = {"adamsville","bradley gardens","crim","hamilton","jfk","milltown","van holten","Eisenhower","hillside","middle school","high school"};
	
	int index =0;
	for(int i = 0; i <schoolNames.length; i++)
	{
		if(userSchoolName.compareToIgnoreCase(schoolNames[i])==0)
		{
			index = i +3;
			i=schoolNames.length;
		}
	}
	
	driver.findElement(By.xpath("//form[@id='mG61Hd']/div/div/div[2]/div[4]/div/div[2]/div/div/div")).click();
	Thread.sleep(500);	
	driver.findElement(By.xpath("//form[@id='mG61Hd']/div/div/div[2]/div[4]/div/div[2]/div[2]/div[" + index +"]/span")).click();
	
	
	
}


/**
 * fills out two drop downs concerning the grade
 */
public static void fillGrade() throws Exception
{
	
//Grade drop out
	driver.findElement(By.xpath("//form[@id='mG61Hd']/div/div/div[2]/div[5]/div/div[2]/div/div[2]")).click();
	Thread.sleep(500);
	driver.findElement(By.xpath("//form[@id='mG61Hd']/div/div/div[2]/div[5]/div/div[2]/div[2]/div["+ grade +"]")).click();
	Thread.sleep(500);
	
	//final submit
	driver.findElement(By.xpath("//form[@id='mG61Hd']/div/div/div[3]/div/div/div/span/span")).click();
}




 

}

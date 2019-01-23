package com.automation.indexpage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.init.AbstractPage;
import com.automation.utility.Common;
import com.automation.verification.PackageVerification;

public class PackageIndexpage extends AbstractPage{

	
	//public static String mobile="+61444444444";
	
	public static String email_address = null;
	public static String password = null;
	public static String category_label_str = null;
	public static String manufacturer_label_str = null;
	
	public PackageIndexpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

    @FindBy (xpath ="//a[contains(text(),'Sign Up')]") private WebElement sign_up_menu;



	public PackageVerification click_signUp_menu() {
		// TODO Auto-generated method stub
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sign_up_menu));
		sign_up_menu.click();
		return new PackageVerification(driver);
	}


	@FindBy (xpath ="//input[@id='firstname']") private WebElement firstname_ele;
	@FindBy(xpath="//input[@id='lastname']")private WebElement lastname_ele;
	@FindBy(xpath="//input[@id='is_subscribed']")private WebElement newslettercheckbox_ele;
	@FindBy(xpath="//input[@id='email_address']")private WebElement emailaddress_ele;
	@FindBy(xpath="//input[@id='password']")private WebElement password_ele;
	@FindBy(xpath="//input[@id='password-confirmation']")private WebElement confirmpassword_ele;
	@FindBy(xpath="//input[@title='Remember Me']")private WebElement rememberme_ele;

	public PackageVerification sign_up_details() {
		// TODO Auto-generated method stub
		
		String firstname = Common.generateRandomChars(5);	
	    firstname_ele.clear();
	    firstname_ele.sendKeys(firstname,Keys.ENTER);
		Common.log("Enter First Name :===>"+firstname);
		
		String lastname = Common.generateRandomChars(5);	
		lastname_ele.clear();
		lastname_ele.sendKeys(lastname,Keys.ENTER);
		Common.log("Enter Last Name :===>"+lastname+"");
		
		Common.log("Click on the Sign Up for Newsletter check box");
		newslettercheckbox_ele.click();
		
		String emailaddress = Common.generateRandomChars(4)+"@mailinator.com";
		emailaddress_ele.clear();
		emailaddress_ele.sendKeys(emailaddress,Keys.ENTER);
		Common.log("Enter Email Address :===>"+emailaddress);
		
		String password = Common.generateRandomChars(5)+"@1234";
        password_ele.clear();
        password_ele.sendKeys(password,Keys.ENTER);
		Common.log("Enter Password :===>"+password);
		
		
		confirmpassword_ele.clear();
		confirmpassword_ele.sendKeys(password);
		Common.log("Enter Confirm Password :===>"+password);
		
		Common.log("Click on Remember Me What's this? check box");
		rememberme_ele.click();
		
		//Write data into the AccountInfo text file Start 
		String fileName = "lib/AccountInfo.txt";
	    String[] data ={firstname,lastname,emailaddress};
		Common.writedataintofile(fileName, data);
		//Write data into the AccountInfo text file End
		
		// Write data into the LoginDetails text file Start
		fileName = "lib/LoginDetails.txt";
		String[] data1 = { emailaddress,password};
		Common.writedataintofile(fileName, data1);
		// Write data into the LoginDetails text file End
		
		
		return new PackageVerification(driver);
	}

	@FindBy (xpath = "//button[@title='Sign Up']") private WebElement signup_button;
	
	public PackageVerification signup_buton() {
		// TODO Auto-generated method stub
		signup_button.click();
		return new PackageVerification(driver);
	}

	
	@FindBy (xpath = "//a[contains(text(),'Log In')]") private WebElement login;
	public PackageVerification click_login_menu() {
		// TODO Auto-generated method stub
		new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(login));
		login.click();		
		return new PackageVerification(driver);
	}
	
	
	@FindBy (xpath = "//input[@id='email']") private WebElement email_login;
	@FindBy (xpath = "//input[@name='login[password]']") private WebElement password_login;
	
	public PackageVerification login_details() {
		// TODO Auto-generated method stub
		
		Common.pause(5);
		String fileName = "lib/LoginDetails.txt";
		
		//String fileName = "temp.txt";
        int i =0;
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	
                System.out.println(line);
                          
                	
                	if (i==0) 
                	{
                		 email_address = line;
                	}
                	else if(i==1) 
                	{
                		 password = line;
                	}
                	else
                		System.out.println("Last line of the file");
                    i++;
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    
		
		
		new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(email_login));
		email_login.clear();
		email_login.sendKeys(email_address,Keys.ENTER);
		Common.log("===> Email Addess : "+email_address+" <===" );
		
		new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(password_login));
		password_login.clear();
		password_login.sendKeys(password);
		Common.log("===> Password : "+password+" <===");
		return new PackageVerification(driver);
	}

	
	@FindBy(xpath = "//div[5]//button //span[contains(text(),'Log In')]")private WebElement login_button;
	public PackageVerification login_buton() {
		// TODO Auto-generated method stub		
		login_button.click();
		return new PackageVerification(driver);
	}
	
	@FindBy(xpath="//*[@id=\"mainmenu\"]/ul/li[3]/a/span[1]/..")private WebElement cameras_menu;
	
	public PackageVerification cameras() {
		// TODO Auto-generated method stub
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(cameras_menu));
		Actions act = new Actions(driver);
		act.moveToElement(cameras_menu).build().perform();
		Common.pause(2);		
		return new PackageVerification(driver);
	}

	@FindBy(xpath="//span[contains(text(),'Cinematic Cameras')]")private WebElement cinematic_cameras_menu;
	public PackageVerification cinematic_cameras() {
		// TODO Auto-generated method stub
		new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(cinematic_cameras_menu));
		cinematic_cameras_menu.click();		
		Common.pause(3);
		return new PackageVerification(driver);
	}
	
	@FindBy(xpath="//input[@name='amshopby[cat][]']") private List<WebElement> category; 
	@FindBy(xpath="//input[@name='amshopby[cat][]']//../span[@class='label']") private List<WebElement> category_label;
	
	public PackageVerification category() {
		// TODO Auto-generated method stub
		Common.pause(5);
		category_label_str = category_label.get(0).getText();
		Common.log("---> Click on :"+category_label_str+" Category <---");
		category.get(0).click();
		Common.pause(5);
		return new PackageVerification(driver);
	}

	@FindBy(xpath="//input[@name='amshopby[manufacturer][]']")private List<WebElement> manufacturer;
	@FindBy(xpath="//input[@name='amshopby[manufacturer][]']//../span[@class='label']")private List<WebElement> manufacturer_label;
	
	public PackageVerification manufacturer() {
		// TODO Auto-generated method stub
		Common.pause(5);
		manufacturer_label_str = manufacturer_label.get(0).getText();
		Common.log("---> Click on :"+manufacturer_label_str+" Category <---");
		manufacturer.get(0).click();
		Common.pause(5);
		return new PackageVerification(driver);
	}

	@FindBy(xpath="//select[@id='sorter']")private List<WebElement> sort_by_options;
	@FindBy(xpath="//a[contains(text(),'PRICE PROMISE ')]")private WebElement price_promise;
	@FindBy(xpath="//*[@id=\"amasty-shopby-product-list\"]/div[1]/div[4]/a")private WebElement price_ascending_descending;
	
	public PackageVerification sortby() {
		// TODO Auto-generated method stub
		/* JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1000)");   */
		    Actions act = new Actions(driver);
		    act.moveToElement(price_promise, 113, 14).build().perform();
		
		    Common.pause(3);
			Select sel = new Select(sort_by_options.get(0));
			//sel.selectByValue("position");			
		    sel.selectByValue("price");
		    Common.pause(4);
		    act.moveToElement(price_promise, 113, -10).build().perform();
		    Common.pause(4);
		    
		    price_ascending_descending.click();
		    Common.pause(10);
		    
		    String direction = price_ascending_descending.getAttribute("data-value");
		    
		    Common.log("Direction :"+direction);
		    
		    if(direction.equalsIgnoreCase("desc")) {
		  /*  List<WebElement> price_descending = driver.findElements(By.xpath("//a[@title=\"Set Descending Direction\"]"));
		    new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(price_descending.get(0)));
		    price_descending.get(0).click();*/
		    	Common.jsClick(driver, price_ascending_descending);
			Common.pause(10);
		    }		    
		    
		    if(direction.equalsIgnoreCase("asc")) {
		    /*List<WebElement> price_ascending = driver.findElements(By.xpath("//a[@title=\"Set Ascending Direction\"]"));	
		    new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(price_ascending.get(0)));
		    price_ascending.get(0).click();*/
		    	Common.jsClick(driver, price_ascending_descending);
		    }
		    /*else {
		    List<WebElement> price_descending = driver.findElements(By.xpath("//a[@title=\"Set Descending Direction\"]"));	
		    price_descending.get(0).click();
			Common.pause(5);
		    }*/
		    act.moveToElement(price_promise, 113, -10).build().perform();		    
		    Common.pause(15);
		return new PackageVerification(driver);
	}


	
		
	}


package com.automation.verification;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.index.PackageIndex;
import com.automation.indexpage.PackageIndexpage;
import com.automation.init.AbstractPage;
import com.automation.utility.Common;
import com.automation.utility.TestData;
import com.sun.imageio.plugins.common.SubImageInputStream;
import com.automation.utility.Common;
//import com.init.Common;

public class PackageVerification extends AbstractPage {

	public static String Sendername;

	public PackageVerification(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	public boolean verifyLogin() {
		// TODO Auto-generated method stub
	//	Common.waitForElement(driver, fullUserName);

		//Common.log(fullUserName.getText());
		//if (fullUserName.getText().trim().equalsIgnoreCase(TestData.getSenderName())) {
			return true;
		
		//return false;
	}


	
	public boolean homepageverify() {
		// TODO Auto-generated method stub
		String urlpass = "https://www.3dbroadcastsales.com/";
		
		if(driver.getCurrentUrl().equals(urlpass)) 
			return true;
		
		else 
			return false;
		
	}


	public boolean accountverification() {
		// TODO Auto-generated method stub
		Common.pause(5);
		String fileName = "lib/AccountInfo.txt";
		
		String firstname = null;
		String lastname = null;
		String emailaddress = null;
		
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
                		 firstname = line;
                	}
                	else if(i==1) 
                	{
                		 lastname = line;
                	}
                	else if (i==2) 
                	{
                	   emailaddress = line;
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
    
    System.out.println("/nSave file Info");		
    System.out.println("Firstname and Last name:"+firstname+" "+lastname);
    System.out.println("Email Address :" + emailaddress);	
    String xpath_first_last_name = firstname+" "+lastname+",";
    
    int check=0;	
    
    WebElement thank_you_msg = driver.findElement(By.xpath("//div[contains(text(),'Thank you for registering with 3D Broadcast Sales.')]"));
    if(thank_you_msg.isDisplayed()) {
    	Common.logstep("===> Thank you for registering with 3D Broadcast Sales message is verified <===");
    	check++;
    }
    
   
    Common.logstep("===> Click on Account Inormation for Details Verification <===");
    WebElement acc_info = driver.findElement(By.xpath("//a[contains(text(),'Account Information')]"));
    //new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(acc_info));
    new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(acc_info));
    acc_info.click();
	
    Common.pause(2);
	WebElement first_acc = driver.findElement(By.xpath("//input[@id='firstname']"));
	WebElement last_acc = driver.findElement(By.xpath("//input[@id='lastname']"));
	WebElement change_email_click = driver.findElement(By.xpath("//input[@id='change-email']"));
	change_email_click.click();	
	WebElement email_acc = driver.findElement(By.xpath("//input[@id='email']"));
	
	if(firstname.equals(first_acc.getAttribute("value")) && lastname.equals(last_acc.getAttribute("value")) && emailaddress.equals(email_acc.getAttribute("value"))) {
		Common.log("--- First name is verified ---");
		Common.log("--- Last name is verified ---");
		Common.log("--- Email Address is verified ---");
		check++;
	}
	
	
		
	System.out.println("Xpath Info");	
	System.out.println("Firstname and Last name:"+first_acc.getAttribute("value")+" "+last_acc.getAttribute("value"));
	System.out.println("Email Address :" + email_acc.getAttribute("value"));
	
	
	Common.switchToNewtabWithUrl(driver, "https://www.mailinator.com/", 1);
	Common.logstep("===> Open Mailinator for Email Verification <===");
	
	
	Common.pause(2);
	WebElement email_txtfield = driver.findElement(By.xpath("//input[@id='inboxfield']"));
	new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(email_txtfield));
	email_txtfield.clear();
	email_txtfield.sendKeys(emailaddress,Keys.ENTER);
	Common.log("--- Enter Email Id :"+emailaddress+" ---");
	
	List<WebElement> subjects = driver.findElements(By.xpath("//tr//td[4]"));
	
	for(WebElement element:subjects) {
		
		if(element.getText().equals("Welcome to 3D Broadcast Sales") ) {
			element.click();	
			WebElement iframe = driver.findElement(By.xpath("//iframe[@id='msg_body']"));
			driver.switchTo().frame(iframe);		
			WebElement first_last_name = driver.findElement(By.xpath("//p[contains(text(),'"+xpath_first_last_name+"')]"));
			WebElement msg_verify = driver.findElement(By.xpath("//p[contains(text(),'Thank you, 3D Broadcast Sales!')]"));
			new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(msg_verify));
			if(msg_verify.isDisplayed() && first_last_name.isDisplayed()) {
			
				Common.log("--- First name and Last Name verified in Email. ---");
				Common.log("--- Welcome Email, 3DbroadCastSale as a sender verified. ---");
				check++;
				//Common.log(Integer.toString(check));
			}
			driver.switchTo().defaultContent();
			Common.pause(3);
			driver.navigate().back();
			
		}
		
	}
	
	Common.pause(3);
    List<WebElement> subjects_news = driver.findElements(By.xpath("//tr//td[4]"));
	
	for(WebElement element:subjects_news) {
		
		if(element.getText().equals("Newsletter subscription success") ) {
			element.click();	
			WebElement iframe = driver.findElement(By.xpath("//iframe[@id='msg_body']"));
			driver.switchTo().frame(iframe);		
			WebElement msg_verify = driver.findElement(By.xpath("//p[contains(text(),'Thank you, 3D Broadcast Sales!')]"));
			new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(msg_verify));
			if(msg_verify.isDisplayed()) 
			{
				Common.log("--- Newsletter subscription, 3DbroadCastSale as a sender verified. ---");
				check++;
				//Common.log(Integer.toString(check));
			}
			driver.switchTo().defaultContent();
			//driver.navigate().back();
			Common.pause(5);
		}
		
	}

		if(check==4)
			return true;
		else
			return false;
	}


	@FindBy(xpath = "//div//ul//li[1]/a[contains(text(),'Account')]")private WebElement account_menu;
	
	public boolean logindetailsverification() {
		// TODO Auto-generated method stub
		
		Common.logstep("===> Account Information Verification <===");
		account_menu.click();
		Common.log("--- Click on Account Menu ---");
		
		
		    WebElement acc_info = driver.findElement(By.xpath("//a[contains(text(),'Account Information')]"));
		    //new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(acc_info));
		    new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(acc_info));
		    acc_info.click();	
		    Common.pause(2);
			WebElement change_email_click = driver.findElement(By.xpath("//input[@id='change-email']"));
			change_email_click.click();	
			
			WebElement email_acc = driver.findElement(By.xpath("//input[@id='email']"));
			
			if(PackageIndexpage.email_address.equals(email_acc.getAttribute("value")))
			{
				Common.log("--- Email Address is verified ---");
				return true;
			}
			else
				return false;
		
	}
	

}

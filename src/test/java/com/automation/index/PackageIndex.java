package com.automation.index;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.indexpage.PackageIndexpage;
import com.automation.init.SeleniumInit;
import com.automation.utility.Common;
import com.automation.utility.Common_demo;
import com.automation.utility.TestData;


public class PackageIndex extends SeleniumInit{

	public static int step, numberOfFailure=1;	
	
	@Test
	public void SignUp()
	{
		step=1; numberOfFailure=0;
		
		
		Common.logcase(" ");
		
		Common.logcase("To verify That user is able to SignUp with the valid details");
			
		Common.logstep("Step"+(step++)+": Open the Url--> https://staging.3dbroadcastsales.com/");
		
		if(packageVerification.homepageverify()) {
			Common.log("=====> 3dbroadcastsales home page open <=====");
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else {
			Common.log("=====> 3dbroadcastsales home page open <=====");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
		}
		
		
		Common.logstep("Step"+(step++)+": Click on the SignUp menu");
		packageVerification=packageIndexpage.click_signUp_menu();
		
		Common.logstep("Step"+(step++)+": Enter the Details for the SignUp");
		packageVerification=packageIndexpage.sign_up_details();
		
		Common.logstep("Step"+(step++)+": Click on SignUp button");
		packageVerification=packageIndexpage.signup_buton();
		
		Common.logstep("Step"+(step++)+": User Sign Up verification.");
		
		if(packageVerification.accountverification()){
			
			   Common.logStatus("Pass");
			   Common.AssertPassed();
			   Assert.assertTrue(true);
		}
		else
		  {
		       Common.logStatus("Fail");
		       Common.AssertFailed();
		       Common.makeScreenshot(driver, "LoginFailed");
		       Assert.assertTrue(false);
		  } 
					  
	}//End of Sign Up function
	
	@Test
	public void SignIn()
	{
		step=1; 		
		
		Common.logcase(" ");
		
		Common.logcase("To verify That user is able to Login with the valid details");
			
		Common.logstep("Step"+(step++)+": Open the Url--> https://staging.3dbroadcastsales.com/");
		
		if(packageVerification.homepageverify()) {
			Common.log("=====> 3dbroadcastsales home page open <=====");
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else {
			Common.log("=====> 3dbroadcastsales home page open <=====");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
		}
		
		
		Common.logstep("Step"+(step++)+": Click on the Login menu");
		packageVerification=packageIndexpage.click_login_menu();
		
		Common.logstep("Step"+(step++)+": Enter the Details for Login");
		packageVerification=packageIndexpage.login_details();
		
		Common.logstep("Step"+(step++)+": Click on Login button");
		packageVerification=packageIndexpage.login_buton();
		
		Common.logstep("Step"+(step++)+": User Login verification.");
		
		if(packageVerification.logindetailsverification()){
			
			   Common.logStatus("Pass");
			   Common.AssertPassed();
			   Assert.assertTrue(true);
		}
		else
		  {
		       Common.logStatus("Fail");
		       Common.AssertFailed();
		       Common.makeScreenshot(driver, "LoginFailed");
		       Assert.assertTrue(false);
		  } 
					  
	}//End of Sign Up function
	

	
	
	
		
}//End of Class

package com.automation.index;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.indexpage.PackageIndexpage;
import com.automation.init.SeleniumInit;
import com.automation.utility.Common;
import com.automation.utility.Common_demo;
import com.automation.utility.TestData;


public class PackageIndex extends SeleniumInit {

	public static int step, numberOfFailure = 1;

	@Test
	public void TC_SignUp_01() {
		step = 1;
		numberOfFailure = 0;

		Common.logcase(" ");

		Common.logcase("To verify That user is able to SignUp with the valid details");

		Common.logstep("Step" + (step++) + ": Open the Url--> https://staging.3dbroadcastsales.com/");

		if (packageVerification.homepageverify()) {
			Common.log("=====> 3dbroadcastsales home page open <=====");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			Common.log("=====> 3dbroadcastsales home page open <=====");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignUpFailed");
		}

		Common.logstep("Step" + (step++) + ": Click on the SignUp menu");
		packageVerification = packageIndexpage.click_signUp_menu();

		Common.logstep("Step" + (step++) + ": Enter the Details for the SignUp");
		packageVerification = packageIndexpage.sign_up_details();

		Common.logstep("Step" + (step++) + ": Click on SignUp button");
		packageVerification = packageIndexpage.signup_buton();

		Common.logstep("Step" + (step++) + ": User Sign Up verification.");

		if (packageVerification.accountverification()) {

			Common.logStatus("Pass");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignUpFailed");
			Assert.assertTrue(false);
		}

	}// End of TC_SignUp_01 function

	@Test
	public void TC_LogIn_02() {
		step = 1;

		Common.logcase(" ");

		Common.logcase("To verify That user is able to Login with the valid details");

		Common.logstep("Step" + (step++) + ": Open the Url--> https://staging.3dbroadcastsales.com/");

		if (packageVerification.homepageverify()) {
			Common.log("=====> 3dbroadcastsales home page open <=====");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			Common.log("=====> 3dbroadcastsales home page open <=====");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
		}

		Common.logstep("Step" + (step++) + ": Click on the Login menu");
		packageVerification = packageIndexpage.click_login_menu();

		Common.logstep("Step" + (step++) + ": Enter the Details for Login");
		packageVerification = packageIndexpage.login_details();

		Common.logstep("Step" + (step++) + ": Click on Login button");
		packageVerification = packageIndexpage.login_buton();

		Common.logstep("Step" + (step++) + ": User Login verification.");

		if (packageVerification.logindetailsverification()) {

			Common.logStatus("Pass");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			Assert.assertTrue(false);
		}

	}// End of TC_LogIn_02 function

	@Test
	public void TC_Menu_Page_03() {
		step = 1;

		Common.logcase(" ");

		Common.logcase("Verify Any Menu page with Below Functionality.");
		Common.logcase("• Shopping options");
		Common.logcase("• Sort By");
		Common.logcase("• Show per page");

		Common.logstep("Step" + (step++) + ": Open the Url--> https://staging.3dbroadcastsales.com/");

		if (packageVerification.homepageverify()) {
			Common.log("=====> 3dbroadcastsales home page open <=====");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			Common.log("=====> 3dbroadcastsales home page open <=====");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "MenuPageFailed");
		}

		Common.logstep("Step" + (step++) + ": Mouse Hover on [Cameras] menu");
		packageVerification = packageIndexpage.cameras();

		Common.logstep("Step" + (step++) + ": Click on [Cinematic Cameras]sub menu");
		packageVerification = packageIndexpage.cinematic_cameras();
		
		if (packageVerification.pageandproductdetailsverification()) {

			Common.logStatus("Pass");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "MenuPageFailed");
			Assert.assertTrue(false);
		}
		
	}// End of TC_Menu_Page_03 function

}// End of Class

package com.automation.index;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.indexpage.PackageIndexpage;
import com.automation.init.SeleniumInit;
import com.automation.utility.Common;
import com.automation.utility.Common_demo;
import com.automation.utility.TestData;

public class PackageIndex extends SeleniumInit {

	public static int step, numberOfFailure = 1;
	SoftAssert softassertion = new SoftAssert();

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
			TestData.SheetResultcellupdate(1, 3, "Pass");
			Assert.assertTrue(true);

		} else {
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignUpFailed");
			TestData.SheetResultcellupdate(1, 3, "Failed");
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
			System.out.println("Test Pass.......");
			TestData.SheetResultcellupdate(2, 3, "Pass");
			Common.logStatus("Pass");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			System.out.println("Test Failed.......");
			TestData.SheetResultcellupdate(2, 3, "Failed");
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			Assert.assertTrue(false);
		}

	}// End of TC_LogIn_02 function

	@Test
	public void TC_Menu_Page_03() {
		step = 1;
		int flag = 0;
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
			flag++;
			Common.log("=====> 3dbroadcastsales home page open <=====");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "MenuPage_HomePage_open_Failed");
		}

		Common.logstep("Step" + (step++) + ": Mouse Hover on [Cameras] menu");
		packageVerification = packageIndexpage.cameras();

		Common.logstep("Step" + (step++) + ": Click on [Cinematic Cameras]sub menu");
		packageVerification = packageIndexpage.cinematic_cameras();

		if (packageVerification.pageandproductdetailsverification()) {
			System.out.println("Test Pass.......");
			Common.logStatus("Pass");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			flag++;
			System.out.println("Test Failed.......");
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "MenuPage_Product_Details_Failed");
			// Assert.assertTrue(false);
			softassertion.assertTrue(false);
		}

		Common.logstep("Step" + (step++) + ": Click on any [Category]");
		packageVerification = packageIndexpage.category();

		if (packageVerification.category_shoping_option()) {
			System.out.println("Test Pass.......");
			Common.logStatus("Pass");
			Common.AssertPassed();
			// Assert.assertTrue(true);
			softassertion.assertTrue(true);
		} else {
			flag++;
			System.out.println("Test Failed.......");
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "MenuPage_Category_Failed");
			// Assert.assertTrue(false);
			softassertion.assertTrue(false);
		}

		Common.logstep("Step" + (step++) + ": Click on any [Manufacturer]");
		packageVerification = packageIndexpage.manufacturer();

		if (packageVerification.Manufacturer_shoping_option()) {
			System.out.println("Test Pass.......");
			Common.logStatus("Pass");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			flag++;
			System.out.println("Test Failed.......");
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "MenuPage_Manufacture_Failed");
			// Assert.assertTrue(false);
			softassertion.assertTrue(false);
		}

		Common.logstep("Step" + (step++) + ": Click on Sort By option : Price : Ascending");
		packageVerification = packageIndexpage.sortby_asc();

		if (packageVerification.sortby_ascending()) {
			System.out.println("Test Pass.......");
			Common.logStatus("Pass");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			flag++;
			System.out.println("Test Failed.......");
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "MenuPage_sortby_ascending_Failed");
			// Assert.assertTrue(false);
			softassertion.assertTrue(false);
		}
		
		Common.logstep("Step" + (step++) + ": Click on Sort By option : Price : Descending ");
		
		packageVerification = packageIndexpage.sortby_Desc();

		if (packageVerification.sortby_descending()) {
			System.out.println("Test Pass.......");
			Common.logStatus("Pass");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			flag++;
			System.out.println("Test Failed.......");
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "MenuPage_sortby_descending_Failed");
			// Assert.assertTrue(false);
			softassertion.assertTrue(false);
		}
		
		
        Common.logstep("Step" + (step++) + ": Click on Show per Page");
		
		packageVerification = packageIndexpage.showperpage();

		if (packageVerification.showperpage_verified()) {
			System.out.println("Test Pass.......");
			Common.logStatus("Pass");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			flag++;
			System.out.println("Test Failed.......");
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "MenuPage_show_per_page_Failed");
			// Assert.assertTrue(false);
			softassertion.assertTrue(false);
		}
		
		if (flag > 0) {
			TestData.SheetResultcellupdate(3, 3, "Failed");
		}
		// softassertion.assertAll();
	}// End of TC_Menu_Page_03 function

	@Test
	public void TC_Wish_Compare_04() {
		
		
		step = 1;
		int flag = 0;
		Common.logcase(" ");

		Common.logcase("Verify User is able to add/remove the Products into Wish List and Compare List");

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
			System.out.println("Test Pass.......");
			//TestData.SheetResultcellupdate(3, 3, "Pass");
			Common.logStatus("Pass");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			flag++;
			System.out.println("Test Failed.......");
			//TestData.SheetResultcellupdate(3, 3, "Failed");
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			//Assert.assertTrue(false);
			softassertion.assertTrue(false);
		}
		
		Common.logstep("Step" + (step++) + ": Mouse Hover on [Cameras] menu");
		packageVerification = packageIndexpage.cameras();

		Common.logstep("Step" + (step++) + ": Click on [Cinematic Cameras]sub menu");
		packageVerification = packageIndexpage.cinematic_cameras();
		
		Common.logstep("Step" + (step++) + ": Add Two Products to WishList");
		packageVerification = packageIndexpage.addtowishlist();
		
		Common.logstep("Step" + (step++) + ": Go To to WishList");
		packageVerification = packageIndexpage.gotowishlist();
		
		if (packageVerification.wishlist_verification()) {
			System.out.println("Test Pass.......");
			//TestData.SheetResultcellupdate(3, 3, "Pass");
			Common.logStatus("Pass");
			Common.AssertPassed();
			Assert.assertTrue(true);
		} else {
			flag++;
			System.out.println("Test Failed.......");
			//TestData.SheetResultcellupdate(3, 3, "Failed");
			Common.logStatus("Fail");
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			//Assert.assertTrue(false);
			softassertion.assertTrue(false);
		}

		
	}//End of TC_Wish_Compare_04 function
	
	
	
}// End of Class

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

public class PackageIndexpage extends AbstractPage {

	// public static String mobile="+61444444444";

	public static String email_address = null;
	public static String password = null;
	public static String category_label_str = null;
	public static String manufacturer_label_str = null;
	public static String[] name_glob = new String[10];
	public static String[] price_glob=new String[10];

	public PackageIndexpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	private WebElement sign_up_menu;

	public PackageVerification click_signUp_menu() {
		// TODO Auto-generated method stub
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(sign_up_menu));
		sign_up_menu.click();
		return new PackageVerification(driver);
	}

	@FindBy(xpath = "//input[@id='firstname']")
	private WebElement firstname_ele;
	@FindBy(xpath = "//input[@id='lastname']")
	private WebElement lastname_ele;
	@FindBy(xpath = "//input[@id='is_subscribed']")
	private WebElement newslettercheckbox_ele;
	@FindBy(xpath = "//input[@id='email_address']")
	private WebElement emailaddress_ele;
	@FindBy(xpath = "//input[@id='password']")
	private WebElement password_ele;
	@FindBy(xpath = "//input[@id='password-confirmation']")
	private WebElement confirmpassword_ele;
	@FindBy(xpath = "//input[@title='Remember Me']")
	private WebElement rememberme_ele;

	public PackageVerification sign_up_details() {
		// TODO Auto-generated method stub

		String firstname = Common.generateRandomChars(5);
		firstname_ele.clear();
		firstname_ele.sendKeys(firstname, Keys.ENTER);
		Common.log("Enter First Name :--->" + firstname+" <---");

		String lastname = Common.generateRandomChars(5);
		lastname_ele.clear();
		lastname_ele.sendKeys(lastname, Keys.ENTER);
		Common.log("Enter Last Name :--->" + lastname + " <---");

		Common.log("Click on the Sign Up for Newsletter check box");
		newslettercheckbox_ele.click();

		String emailaddress = Common.generateRandomChars(4) + "@mailinator.com";
		emailaddress_ele.clear();
		emailaddress_ele.sendKeys(emailaddress, Keys.ENTER);
		Common.log("Enter Email Address :--->" + emailaddress+" <---");

		String password = Common.generateRandomChars(5) + "@1234";
		password_ele.clear();
		password_ele.sendKeys(password, Keys.ENTER);
		Common.log("Enter Password :--->" + password+" <---");

		confirmpassword_ele.clear();
		confirmpassword_ele.sendKeys(password);
		Common.log("Enter Confirm Password :--->" + password+" <---");

		Common.log("---> Click on Remember Me What's this? check box <---");
		rememberme_ele.click();

		// Write data into the AccountInfo text file Start
		String fileName = "lib/AccountInfo.txt";
		String[] data = { firstname, lastname, emailaddress };
		Common.writedataintofile(fileName, data);
		// Write data into the AccountInfo text file End

		// Write data into the LoginDetails text file Start
		fileName = "lib/LoginDetails.txt";
		String[] data1 = { emailaddress, password };
		Common.writedataintofile(fileName, data1);
		// Write data into the LoginDetails text file End

		return new PackageVerification(driver);
	}

	@FindBy(xpath = "//button[@title='Sign Up']")
	private WebElement signup_button;

	public PackageVerification signup_buton() {
		// TODO Auto-generated method stub
		signup_button.click();
		return new PackageVerification(driver);
	}

	@FindBy(xpath = "//a[contains(text(),'Log In')]")
	private WebElement login;

	public PackageVerification click_login_menu() {
		// TODO Auto-generated method stub
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(login));
		login.click();
		return new PackageVerification(driver);
	}

	@FindBy(xpath = "//input[@id='email']")
	private WebElement email_login;
	@FindBy(xpath = "//input[@name='login[password]']")
	private WebElement password_login;

	public PackageVerification login_details() {
		// TODO Auto-generated method stub

		Common.pause(5);
		String fileName = "lib/LoginDetails.txt";

		// String fileName = "temp.txt";
		int i = 0;
		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				// System.out.println(line);
				if (i == 0) {
					email_address = line;
				} else if (i == 1) {
					password = line;
				} else
					System.out.println("Last line of the file");
				i++;
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(email_login));
		email_login.clear();
		email_login.sendKeys(email_address, Keys.ENTER);
		Common.log("---> Email Addess : " + email_address + " <---");

		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(password_login));
		password_login.clear();
		password_login.sendKeys(password);
		Common.log("---> Password : " + password + " <---");
		return new PackageVerification(driver);
	}

	@FindBy(xpath = "//div[5]//button //span[contains(text(),'Log In')]")
	private WebElement login_button;

	public PackageVerification login_buton() {
		// TODO Auto-generated method stub
		login_button.click();
		return new PackageVerification(driver);
	}

	@FindBy(xpath = "//*[@id=\"mainmenu\"]/ul/li[3]/a/span[1]/..")
	private WebElement cameras_menu;

	public PackageVerification cameras() {
		// TODO Auto-generated method stub
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(cameras_menu));
		Actions act = new Actions(driver);
		act.moveToElement(cameras_menu).build().perform();
		Common.pause(2);
		return new PackageVerification(driver);
	}

	@FindBy(xpath = "//span[contains(text(),'Cinematic Cameras')]")
	private WebElement cinematic_cameras_menu;

	public PackageVerification cinematic_cameras() {
		// TODO Auto-generated method stub
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(cinematic_cameras_menu));
		cinematic_cameras_menu.click();
		Common.pause(3);
		return new PackageVerification(driver);
	}

	@FindBy(xpath = "//input[@name='amshopby[cat][]']")
	private List<WebElement> category;
	@FindBy(xpath = "//input[@name='amshopby[cat][]']//../span[@class='label']")
	private List<WebElement> category_label;

	public PackageVerification category() {
		// TODO Auto-generated method stub
		Common.pause(5);
		category_label_str = category_label.get(0).getText();
		Common.log("---> Click on :" + category_label_str + " Category <---");
		category.get(0).click();
		Common.pause(5);
		return new PackageVerification(driver);
	}

	@FindBy(xpath = "//input[@name='amshopby[manufacturer][]']")
	private List<WebElement> manufacturer;
	@FindBy(xpath = "//input[@name='amshopby[manufacturer][]']//../span[@class='label']")
	private List<WebElement> manufacturer_label;

	public PackageVerification manufacturer() {
		// TODO Auto-generated method stub
		Common.pause(5);
		manufacturer_label_str = manufacturer_label.get(0).getText();
		Common.log("---> Click on :" + manufacturer_label_str + " Category <---");
		manufacturer.get(0).click();
		Common.pause(5);
		return new PackageVerification(driver);
	}

	@FindBy(xpath = "//select[@id='sorter']")
	private List<WebElement> sort_by_options;
	@FindBy(xpath = "//a[contains(text(),'PRICE PROMISE ')]")
	private WebElement price_promise;
	@FindBy(xpath = "//*[@id=\"amasty-shopby-product-list\"]/div[1]/div[4]/a")
	private WebElement price_ascending_descending;

	public PackageVerification sortby_asc() {
		// TODO Auto-generated method stub
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("window.scrollBy(0,1000)");
		 */
		Actions act = new Actions(driver);
		act.moveToElement(price_promise, 113, 14).build().perform();

		Common.pause(3);
		Select sel = new Select(sort_by_options.get(0));
		// sel.selectByValue("position");
		sel.selectByValue("price");
		Common.pause(4);
		act.moveToElement(price_promise, 113, -10).build().perform();
		Common.pause(4);
		price_ascending_descending.click();
		Common.pause(10);
		String direction = price_ascending_descending.getAttribute("data-value");
		
		if (direction.equalsIgnoreCase("desc")) {
			Common.jsClick(driver, price_ascending_descending);
			Common.pause(10);
		}

		if (direction.equalsIgnoreCase("asc")) {
			direction = price_ascending_descending.getAttribute("data-value");
			Common.log("Direction :" + direction);
			Common.jsClick(driver, price_ascending_descending);
		}
		
		act.moveToElement(price_promise, 113, -10).build().perform();
		
		Common.pause(8);
		return new PackageVerification(driver);
	}

	public PackageVerification sortby_Desc() {
		// TODO Auto-generated method stub
		Actions act = new Actions(driver);
		act.moveToElement(price_promise, 113, 14).build().perform();

		Common.pause(3);
		Select sel = new Select(sort_by_options.get(0));
		// sel.selectByValue("position");
		sel.selectByValue("price");
		Common.pause(4);
		act.moveToElement(price_promise, 113, -10).build().perform();
		//price_ascending_descending.click();
		Common.pause(10);
		String direction = price_ascending_descending.getAttribute("data-value");

		if (direction.equalsIgnoreCase("asc")) {
			Common.log("Direction :" + "desc");
		}
		if (direction.equalsIgnoreCase("desc")) {
			direction = price_ascending_descending.getAttribute("data-value");
			Common.log("Direction :" + direction);
			Common.jsClick(driver, price_ascending_descending);
		}
		act.moveToElement(price_promise, 113, -10).build().perform();
		Common.pause(8);
		return new PackageVerification(driver);
	}

	//@FindBy(xpath="//select[@id='limiter']")private List<WebElement> show_per_page;
	//@FindBy(xpath="//a[@class='product-item-link']")private List<WebElement> items_per_page1;
	//@FindBy(xpath="//a[contains(text(),'Privacy and Cookie Policy')]")private WebElement privacy_cookie;
	
	public PackageVerification showperpage() {
		// TODO Auto-generated method stub
		int items;
		Actions act = new Actions(driver);
		
		List<WebElement> show_per_page1 = driver.findElements(By.xpath("//select[@id='limiter']"));
		Select show1 = new Select(show_per_page1.get(1));
		show1.selectByValue("12");
		Common.pause(5);
		List<WebElement> items_per_page1 = driver.findElements(By.xpath("//a[@class='product-item-link']"));
		WebElement privacy_cookie1 = driver.findElement(By.xpath("//a[contains(text(),'Privacy and Cookie Policy')]"));
		act.moveToElement(privacy_cookie1).build().perform();
		Common.pause(5);
		items = items_per_page1.size();
		packageVerification.itemsperpage(items);
		
		
		List<WebElement> show_per_page2 = driver.findElements(By.xpath("//select[@id='limiter']"));
		Select show2 = new Select(show_per_page2.get(1));
		show2.selectByValue("24");
		Common.pause(5);
		WebElement privacy_cookie2 = driver.findElement(By.xpath("//a[contains(text(),'Privacy and Cookie Policy')]"));
		List<WebElement> items_per_page2 = driver.findElements(By.xpath("//a[@class='product-item-link']"));
		act.moveToElement(privacy_cookie2).build().perform();
		Common.pause(5);
		items = items_per_page2.size();
		packageVerification.itemsperpage(items);
		
		List<WebElement> show_per_page3 = driver.findElements(By.xpath("//select[@id='limiter']"));
		Select show3 = new Select(show_per_page3.get(1));
		show3.selectByValue("48");
		Common.pause(5);
		WebElement privacy_cookie3 = driver.findElement(By.xpath("//a[contains(text(),'Privacy and Cookie Policy')]"));
		List<WebElement> items_per_page3 = driver.findElements(By.xpath("//a[@class='product-item-link']"));
		act.moveToElement(privacy_cookie3).build().perform();
		Common.pause(5);
		items = items_per_page3.size();
		packageVerification.itemsperpage(items);
		
		List<WebElement> show_per_page4 = driver.findElements(By.xpath("//select[@id='limiter']"));
		Select show4 = new Select(show_per_page4.get(1));
		show4.selectByValue("all");
		Common.pause(5);
		WebElement privacy_cookie4 = driver.findElement(By.xpath("//a[contains(text(),'Privacy and Cookie Policy')]"));
		List<WebElement> items_per_page4 = driver.findElements(By.xpath("//a[@class='product-item-link']"));
		act.moveToElement(privacy_cookie4).build().perform();
		Common.pause(5);
		items = items_per_page4.size();
		packageVerification.itemsperpage(items);
		
		return new PackageVerification(driver);
	}

	
	public PackageVerification addtowishlist() {
		// TODO Auto-generated method stub
		
		Actions act = new Actions(driver);
		
		List<WebElement> items_wish_compare_list1 = driver.findElements(By.xpath("//a[@class='product-item-link']"));
		List<WebElement> items_prices_wish_compare_list1=driver.findElements(By.xpath("//span[@class='price']"));
		int prices = items_prices_wish_compare_list1.size()/2;
		System.out.println("Number of Prices :" +prices);
		System.out.println("Number of Prices :" +items_wish_compare_list1.size());
		
		//if(items_wish_compare_list1.size()==prices) {
		//	Common.log("---> All items have Prices <---");
			
			String item1_name = items_wish_compare_list1.get(0).getText();
			String item1_price = items_prices_wish_compare_list1.get(1).getText();
			name_glob[0] = item1_name;
			price_glob[0]=item1_price;
			
			Common.log("---> First Item Name :" +item1_name +" <---");
			Common.log("---> First Item Price :"+item1_price+" <---");
			Common.pause(8);
			act.moveToElement(items_wish_compare_list1.get(0)).build().perform();
			Common.pause(2);
			List<WebElement> item1 = driver.findElements(By.xpath("//span[@class='icon ib ib-hover ic ic-heart ']"));
			item1.get(0).click();
			Common.log("---> first Item added to the Wish List <---");
			Common.pause(8);
			driver.navigate().back();
			Common.pause(8);
			
			List<WebElement> items_wish_compare_list2 = driver.findElements(By.xpath("//a[@class='product-item-link']"));
			List<WebElement> items_prices_wish_compare_list2=driver.findElements(By.xpath("//span[@class='price']"));
			String item2_name = items_wish_compare_list2.get(1).getText();
			String item2_price = items_prices_wish_compare_list2.get(3).getText();
			Common.log("---> Second Item Name :" +item2_name +" <---");
			Common.log("---> Second Item Price :"+item2_price+" <---");
			name_glob[1] = item2_name;
			price_glob[1]=item2_price;
			Common.pause(8);
			act.moveToElement(items_wish_compare_list2.get(1)).build().perform();
			Common.pause(2);
			
			List<WebElement> item2 = driver.findElements(By.xpath("//span[@class='icon ib ib-hover ic ic-heart ']"));
			//new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfAllElements(item2));
			item2.get(1).click();
			Common.log("---> Second Item added to the Wish List <---");
			Common.pause(8);
			driver.navigate().back();
			Common.pause(8);
			
		
			List<WebElement> items_wish_compare_list3 = driver.findElements(By.xpath("//a[@class='product-item-link']"));
			List<WebElement> items_prices_wish_compare_list3=driver.findElements(By.xpath("//span[@class='price']"));
			String item3_name = items_wish_compare_list3.get(2).getText();
			String item3_price = items_prices_wish_compare_list3.get(5).getText();
			Common.log("---> First Item Name :" +item3_name +" <---");
			Common.log("---> First Item Price :"+item3_price+" <---");
			name_glob[2] = item3_name;
			price_glob[2]=item3_price;
			Common.pause(8);
			act.moveToElement(items_wish_compare_list3.get(2)).build().perform();
			Common.pause(2);
			List<WebElement> item3 = driver.findElements(By.xpath("//span[@class='icon ib ib-hover ic ic-compare ']"));
			item3.get(2).click();
			Common.log("---> First Item added to the Compare List <---");
			Common.pause(8);
			driver.navigate().back();
			Common.pause(8);
			
			List<WebElement> items_wish_compare_list4 = driver.findElements(By.xpath("//a[@class='product-item-link']"));
			List<WebElement> items_prices_wish_compare_list4=driver.findElements(By.xpath("//span[@class='price']"));
			String item4_name = items_wish_compare_list4.get(3).getText();
			String item4_price = items_prices_wish_compare_list4.get(7).getText();
			Common.log("---> Second Item Name :" +item4_name +" <---");
			Common.log("---> second Item Price :"+item4_price+" <---");
			name_glob[3] = item4_name;
			price_glob[3]=item4_price;
			Common.pause(8);
			act.moveToElement(items_wish_compare_list4.get(3)).build().perform();
			Common.pause(2);
			List<WebElement> item4 = driver.findElements(By.xpath("//span[@class='icon ib ib-hover ic ic-compare ']"));
			item4.get(3).click();
			Common.log("---> Second Item added to the Compare List <---");
			Common.pause(8);
			driver.navigate().back();
			Common.pause(8);
			
			/*for(String name : name_glob) {
				System.out.println(name);
			}
			for(String price : price_glob) {
				System.out.println(price);
			}*/
			
		//}
		//else {
		//	Common.log("---> All items doesn't have the prices <---");
		//}
		
		return new PackageVerification(driver);
	}

	@FindBy(xpath="//li[@class=\"link wishlist\"]//a")private WebElement wishlist;
	
	public PackageVerification gotowishlist() {
		// TODO Auto-generated method stub
		
		new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(wishlist));
		wishlist.click();
		Common.pause(6);
		return new PackageVerification(driver);
	}

}

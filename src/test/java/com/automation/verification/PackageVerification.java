package com.automation.verification;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.automation.indexpage.PackageIndexpage;
import com.automation.init.AbstractPage;
import com.automation.utility.Common;
//import com.init.Common;

public class PackageVerification extends AbstractPage {

	public static String Sendername;
	public static int items_per_page_var=0;
	
	public PackageVerification(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean homepageverify() {
		// TODO Auto-generated method stub
		String urlpass = "https://www.3dbroadcastsales.com/";

		if (driver.getCurrentUrl().equals(urlpass))
			return true;

		else
			return false;

	}

	public boolean accountverification() {
		// TODO Auto-generated method stub
		
		Common.logveri("---> Verifycation Start <---");
		Common.pause(5);
		String fileName = "lib/AccountInfo.txt";

		String firstname = null;
		String lastname = null;
		String emailaddress = null;

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

				System.out.println(line);

				if (i == 0) {
					firstname = line;
				} else if (i == 1) {
					lastname = line;
				} else if (i == 2) {
					emailaddress = line;
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

		System.out.println("/nSave file Info");
		System.out.println("Firstname and Last name:" + firstname + " " + lastname);
		System.out.println("Email Address :" + emailaddress);
		String xpath_first_last_name = firstname + " " + lastname + ",";

		int check = 0;

		WebElement thank_you_msg = driver
				.findElement(By.xpath("//div[contains(text(),'Thank you for registering with 3D Broadcast Sales.')]"));
		if (thank_you_msg.isDisplayed()) {
			Common.log("---> Thank you for registering with 3D Broadcast Sales message is verified <---");
			check++;
		}

		Common.logveri("---> Click on Account Inormation for Details Verification <---");
		WebElement acc_info = driver.findElement(By.xpath("//a[contains(text(),'Account Information')]"));
		// new
		// WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(acc_info));
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(acc_info));
		acc_info.click();

		Common.pause(2);
		WebElement first_acc = driver.findElement(By.xpath("//input[@id='firstname']"));
		WebElement last_acc = driver.findElement(By.xpath("//input[@id='lastname']"));
		WebElement change_email_click = driver.findElement(By.xpath("//input[@id='change-email']"));
		change_email_click.click();
		WebElement email_acc = driver.findElement(By.xpath("//input[@id='email']"));

		if (firstname.equals(first_acc.getAttribute("value")) && lastname.equals(last_acc.getAttribute("value"))
				&& emailaddress.equals(email_acc.getAttribute("value"))) {
			Common.log("---> First name is verified <---");
			Common.log("---> Last name is verified <---");
			Common.log("---> Email Address is verified <---");
			check++;
		}

		System.out.println("Xpath Info");
		System.out.println(
				"Firstname and Last name:" + first_acc.getAttribute("value") + " " + last_acc.getAttribute("value"));
		System.out.println("Email Address :" + email_acc.getAttribute("value"));

		Common.switchToNewtabWithUrl(driver, "https://www.mailinator.com/", 1);
		Common.logveri("---> Open Mailinator for Email Verification <---");

		Common.pause(2);
		WebElement email_txtfield = driver.findElement(By.xpath("//input[@id='inboxfield']"));
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(email_txtfield));
		email_txtfield.clear();
		email_txtfield.sendKeys(emailaddress, Keys.ENTER);
		Common.log("---> Enter Email Id :" + emailaddress + " <---");

		List<WebElement> subjects = driver.findElements(By.xpath("//tr//td[4]"));

		for (WebElement element : subjects) {

			if (element.getText().equals("Welcome to 3D Broadcast Sales")) {
				element.click();
				WebElement iframe = driver.findElement(By.xpath("//iframe[@id='msg_body']"));
				driver.switchTo().frame(iframe);
				WebElement first_last_name = driver
						.findElement(By.xpath("//p[contains(text(),'" + xpath_first_last_name + "')]"));
				WebElement msg_verify = driver
						.findElement(By.xpath("//p[contains(text(),'Thank you, 3D Broadcast Sales!')]"));
				new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(msg_verify));
				if (msg_verify.isDisplayed() && first_last_name.isDisplayed()) {

					Common.log("---> First name and Last Name verified in Email. <---");
					Common.log("---> Welcome Email, 3DbroadCastSale as a sender verified. <---");
					check++;
					// Common.log(Integer.toString(check));
				}
				driver.switchTo().defaultContent();
				Common.pause(3);
				driver.navigate().back();

			}

		}

		Common.pause(3);
		List<WebElement> subjects_news = driver.findElements(By.xpath("//tr//td[4]"));

		for (WebElement element : subjects_news) {

			if (element.getText().equals("Newsletter subscription success")) {
				element.click();
				WebElement iframe = driver.findElement(By.xpath("//iframe[@id='msg_body']"));
				driver.switchTo().frame(iframe);
				WebElement msg_verify = driver
						.findElement(By.xpath("//p[contains(text(),'Thank you, 3D Broadcast Sales!')]"));
				new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(msg_verify));
				if (msg_verify.isDisplayed()) {
					Common.log("---> Newsletter subscription, 3DbroadCastSale as a sender verified. <---");
					check++;
					// Common.log(Integer.toString(check));
				}
				driver.switchTo().defaultContent();
				// driver.navigate().back();
				Common.pause(5);
			}

		}

		Common.logveri("---> Verifycation End <---");
		if (check == 4)
			return true;
		else
			return false;
	}

	@FindBy(xpath = "//div//ul//li[1]/a[contains(text(),'Account')]")
	private WebElement account_menu;

	public boolean logindetailsverification() {
		// TODO Auto-generated method stub
        Common.logveri("---> Verification Start <---");
		Common.logveri("---> Account Information Verification <---");
		Common.pause(8);
		new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(account_menu));
		account_menu.click();
		Common.log("---> Click on Account Menu <---");

		WebElement acc_info = driver.findElement(By.xpath("//a[contains(text(),'Account Information')]"));
		// new
		// WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(acc_info));
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(acc_info));
		acc_info.click();
		Common.pause(2);
		WebElement change_email_click = driver.findElement(By.xpath("//input[@id='change-email']"));
		change_email_click.click();

		WebElement email_acc = driver.findElement(By.xpath("//input[@id='email']"));

		if (PackageIndexpage.email_address.equals(email_acc.getAttribute("value"))) {
			Common.log("---> Email Address is verified <---");
			Common.logveri("---> Verification End <---");
			return true;
		} else {
			Common.logveri("---> Verification End <---");
			return false;
		}

	}

	@FindBy(xpath = "//div[@class='main-container']//span[contains(text(),'Cinematic Cameras')]")
	private WebElement pagename;
	@FindBy(xpath = "//strong[contains(text(),'Shopping Options')]//..//div//div//div[1][contains(text(),'Category')]")
	private WebElement category;
	@FindBy(xpath = "//strong[contains(text(),'Shopping Options')]//..//div//div//div[1][contains(text(),'Manufacturer')]")
	private WebElement manufacturer;
	@FindBy(xpath = "//a[@class='product-item-link']")
	private List<WebElement> products;
	@FindBy(xpath = "//span[@class='price']")
	private List<WebElement> prices;
	@FindBy(xpath = "//span[contains(text(),'Add to Basket')]")
	private List<WebElement> addtobasket;
	@FindBy(xpath = "//img[@class='product-image-photo']")
	private List<WebElement> products_images;
	@FindBy(xpath = "//span[@class='icon ib ib-hover ic ic-heart ']")
	private List<WebElement> wishlist;
	@FindBy(xpath = "//span[@class='icon ib ib-hover ic ic-compare ']")
	private List<WebElement> compare;

	public boolean pageandproductdetailsverification() {
		// TODO Auto-generated method stub
		Common.logveri("---> Verification Start <---");
		int flag = 0;

		// flag=1
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(pagename));
		if (pagename.isDisplayed()) {
			Common.log("---> Page Name has been Verified <---");
			flag++;
		}

		// flag=2
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(category));
		if (category.isDisplayed()) {
			Common.log("---> Shopping Options [Category] is Displayed <---");
			flag++;
		}

		// flag=3
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(manufacturer));
		if (manufacturer.isDisplayed()) {
			Common.log("---> Shopping Options [Manufaturer] is Displayed <--");
			flag++;
		}

		// flag=4
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElements(products));
		if (!products.isEmpty()) {
			Common.log("---> Product Name is :" + products.get(0).getText() + " <---");
			Common.log("---> Product Name is Dispayed <---");
			flag++;
		}

		// flag=5
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElements(prices));
		if (!prices.isEmpty()) {
			Common.log("---> Product Price[inc VAT] is :" + prices.get(0).getText() + " <---");
			Common.log("---> Product Price[exc VAT] is :" + prices.get(1).getText() + " <---");
			Common.log("---> Product price is Dispayed <---");
			flag++;
		}

		// flag=6
		new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfAllElements(addtobasket));
		if (!addtobasket.isEmpty()) {
			Common.log("---> Product Add to Basket Button is Displayed <---");
			flag++;
		}
		// flag=7
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElements(products_images));
		if (!products_images.isEmpty()) {
			Common.log("---> Product image is Displayed <---");
			flag++;
		}

		// flag=8
		if (wishlist.size() > 0 && compare.size() > 0) {
			Common.log("---> Product WishList icon is Displayed <---");
			Common.log("---> Product Compare icon is Displayed <---");
			flag++;
		}

		// flag=9
		List<WebElement> products_size = driver.findElements(By.xpath("//a[@class='product-item-link']"));
		List<WebElement> prices_size = driver.findElements(By.xpath("//span[@class='price']"));
		List<WebElement> addtobasket_size = driver.findElements(By.xpath("//span[contains(text(),'Add to Basket')]"));
		List<WebElement> products_images_size = driver.findElements(By.xpath("//img[@class='product-image-photo']"));
		List<WebElement> wishlist_size = driver
				.findElements(By.xpath("//span[@class='icon ib ib-hover ic ic-heart ']"));
		List<WebElement> compare_size = driver
				.findElements(By.xpath("//span[@class='icon ib ib-hover ic ic-compare ']"));

		int check = 1;

		int count = products_size.size();
		Common.log("---> Number of items :" + products_size.size() + " <---");
		// There are some products with Price On Call
		Common.log("---> Number of prices :" + prices_size.size() + " <---");
		if (count == addtobasket_size.size())
			check++;
		Common.log("---> Number of add to basket button :" + addtobasket_size.size() + " <---");
		if (count == products_images_size.size())
			check++;
		Common.log("---> Number of images :" + products_images_size.size() + " <---");
		if (count == wishlist_size.size())
			check++;
		Common.log("---> Number of wishlist icon :" + wishlist_size.size() + " <---");
		if (count == compare_size.size())
			check++;
		Common.log("---> Number of compare icon :" + compare_size.size() + " <---");

		if (check == 5) {
			Common.log("---> Number of Products||Prices||AddtoBasket Button, Verified<---");
			Common.log("---> Product Images||WishList||Compare List, Verified <---");
			Common.log("---> All Products has the above Details/Elements, Verified  <---");
			flag++;
		}

		Actions image_hover = new Actions(driver);
		image_hover.moveToElement(products_images.get(0)).build().perform();
		Common.pause(5);
		Common.logveri("---> Verification End <---");
		if (flag == 9)
			return true;
		else
			return false;
	}

	List<WebElement> product_list = driver.findElements(By.xpath("//a[@class='product-item-link']"));

	public boolean category_shoping_option() {
		// TODO Auto-generated method stub
		Common.logveri("---> Verification Start <---");
		int flag = product_list.size();
		System.out.println("Flag =" + flag);

		Common.log("String to match :" + PackageIndexpage.category_label_str.substring(0, 6));

		for (WebElement element : product_list) {

			Common.log("---> Product Name :" + element.getText()+" <---");

			if (StringUtils.containsIgnoreCase(element.getText(),
					PackageIndexpage.category_label_str.substring(0, 6))) {
				flag--;
			}
		}

		System.out.println("Flag =" + flag);
        
		Common.logveri("---> Verification End <---");
		if (flag == 0)
			return true;
		else
			return false;
	}

	public boolean Manufacturer_shoping_option() {
		// TODO Auto-generated method stub
		Common.logveri("---> Verification Start <---");
		int flag = product_list.size();
		System.out.println("Flag =" + flag);

		Common.log("String to match :" + PackageIndexpage.manufacturer_label_str.substring(0, 10));

		for (WebElement element : product_list) {

			Common.log("---> Product Name :" + element.getText()+" <---");

			if (StringUtils.containsIgnoreCase(element.getText(),
					PackageIndexpage.manufacturer_label_str.substring(0, 6))) {
				flag--;
			}
		}

		System.out.println("Flag =" + flag);
		Common.logveri("---> Verification End <---");  
		if (flag == 0)
			return true;
		else
			return false;
	}

	@FindBy(xpath = "//span[@class='price']")
	private List<WebElement> prices_sortby;

	public boolean sortby_ascending() {
		// TODO Auto-generated method stub
		Common.logveri("---> Verification Start <---");
		int flag = prices_sortby.size()/2;
		Common.log("Flag = " + flag);
		for (int i = 1; i < prices_sortby.size(); i = i + 2) {
			Common.log("---> Product Price :" + prices_sortby.get(i).getText() + " <---");

			if (i == prices_sortby.size() - 1) {
				break;
			}
			int indx1;

			indx1 = prices_sortby.get(i).getText().indexOf(".");
			String value1 = prices_sortby.get(i).getText().substring(0, indx1).replace("£", "").trim().replace(",", "")
					.trim();
			Common.log("---> Value1 :" + value1 + " <---");

			indx1 = prices_sortby.get(i + 2).getText().indexOf(".");
			String value2 = prices_sortby.get(i + 2).getText().substring(0, indx1).replace("£", "").trim()
					.replace(",", "").trim();
			// Common.log("---> Value2 :"+value2+" <---");

			int val1 = Integer.parseInt(value1);
			int val2 = Integer.parseInt(value2);

			/*
			 * int sum = val1+val2; System.out.println(prices_sortby.size()%2);
			 */

			if (val1 <= val2) {
				flag--;
			}
		}

		Common.log("Flag = " + flag);
		System.out.println(prices_sortby.size() % 2);
		Common.logveri("---> Verification End <---");
		if (flag <= 1)
			return true;
		else
			return false;
	}

	
	
	public boolean sortby_descending() {
		// TODO Auto-generated method stub
		Common.logveri("---> Verification Start <---");
		int flag = prices_sortby.size()/2;
		Common.log("Flag = " + flag);
		for (int i = 1; i < prices_sortby.size(); i = i + 2) {
			Common.log("---> Product Price :" + prices_sortby.get(i).getText() + " <---");

			if (i == prices_sortby.size() - 1) {
				break;
			}
			int indx1;

			indx1 = prices_sortby.get(i).getText().indexOf(".");
			String value1 = prices_sortby.get(i).getText().substring(0, indx1).replace("£", "").trim().replace(",", "")
					.trim();
			Common.log("---> Value1 :" + value1 + " <---");

			indx1 = prices_sortby.get(i + 2).getText().indexOf(".");
			String value2 = prices_sortby.get(i + 2).getText().substring(0, indx1).replace("£", "").trim()
					.replace(",", "").trim();
			// Common.log("---> Value2 :"+value2+" <---");

			int val1 = Integer.parseInt(value1);
			int val2 = Integer.parseInt(value2);

			/*
			 * int sum = val1+val2; System.out.println(prices_sortby.size()%2);
			 */

			if (val1 >= val2) {
				flag--;
			}
		}

		Common.log("Flag = " + flag);
		Common.logveri("---> Verification End <---");
		if (flag <= 1)
			return true;
		else
			return false;
	}

	
	
	public void itemsperpage(int items) {
		// TODO Auto-generated method stub
		List<WebElement> items_list = driver.findElements(By.xpath("//a[@class='product-item-link']"));
		Common.log("Items Per Page :" + items_list.size());
		Common.log("Items Per Page in variable :" + items);
		//System.out.println("items_per_page_var :" +items_per_page_var);
		if(items_list.size()==items) {
			items_per_page_var++;
			System.out.println("items_per_page_var :" +items_per_page_var);
			Common.logveri("---> Verification Done <---");
		}
	}

	public boolean showperpage_verified() {
		// TODO Auto-generated method stub
		if(items_per_page_var==4) { 
	    Common.logveri("---> Final Verification Done <---");	
		return true;
		}
		else {
		Common.logveri("---> Final Verification is not Done <---");			
		return false;}
	}

	
	
	public boolean wishlist_verification() {
		Common.logveri("---> Verification Start <---");	
		String name=null;
		String price=null;
		//List<WebElement> items = driver.findElements(By.xpath("//input[@class='input-text qty']"));
		List<WebElement> items_list = driver.findElements(By.xpath("//div//ol//li//div//strong[@class=\"product-item-name\"]"));
		List<WebElement> items_prices =driver.findElements(By.xpath("//span[@class='price']"));
		int number_items = items_list.size()/2;
		int flag = number_items;
		System.out.println("Number of Items and Flag :"+ number_items + " " +flag);
		
		
		/*for(String name1 : PackageIndexpage.name_glob) {
			System.out.println(name1);
		}
		for(String price1 : PackageIndexpage.price_glob) {
			System.out.println(price1);
		}
		*/
		int j=1;
		
		for(int i=0;i<number_items;i++) {
			name = items_list.get(i).getText();
			price = items_prices.get(j).getText();
			j=j+2;
			/*Common.log("---> Item :"+ name +" <---");
			Common.log("---> Price :"+ price+" <---" );*/
			
			if(name.equalsIgnoreCase(PackageIndexpage.name_glob[i]) && price.equalsIgnoreCase(PackageIndexpage.price_glob[i])) {
				Common.log("---> Item :"+ name +" <---");
				Common.log("---> Price :"+ price+" <---" );
				flag--;
			}
		}
		Common.logveri("---> Verification End <---");	
		if(flag==0)
		return true;
		else
		return false;
	}
	
	
}

package com.swm.stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import com.swm.util.WebConnector;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
//import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


//import static org.assertj.core.api.Assertions.assertThat;

public class GenericSteps {
	
	int currentStepDefIndex = 0;
	String className;

	public String user = "ishans1@gmail.com";
	public String pwd = "Ishan123";
	public String user1 = "Driver 1 First Name ";
	Map<String,String> map = new HashMap<String,String>();
	public List<Map<String,String>> list;
	WebConnector con;	
	
	public GenericSteps(WebConnector con)
	{
		this.con = con;
	}
	
	@After
	public void afterScenario()
	{
		con.quit();	
	}
	
	@Given("^User opens ([^\"]*) and navigates to ([^\"]*)$")
	public void user_opens_browser_and_navigates_to_urlKey(String browserName, String url)
	{
		System.out.println("This is the " + browserName + " browser ");
		con.openBrowser(browserName);	
		con.navigateUrl(url);
	}
	
	@When("^User logs in with ([^\"]*) and ([^\"]*)$")
	public void user_logs_in_with_username_and_password(String user, String pwd, DataTable table) throws InterruptedException 
	{
		map = table.asMap(String.class, String.class);
		con.type(user, map.get("userName"));
		con.type(pwd, map.get("password"));
		Thread.sleep(2000);
	}
	
	@And("^Click on ([^\"]*)$")
	public void Click_on(String locatorKey)
	{
		con.click(locatorKey);
	}

	@And("^User enters text in ([^\"]*)$")
	public void user_enters(String locatorKey, DataTable table)
	{
		map = table.asMap(String.class, String.class);
		con.type(locatorKey, map.get("text"));
	}

	@Then("^([^\"]*) is displayed as ([^\"]*)$")
	public void element_is_displayed(String header, String data) 
	{
		String text = con.getTextValue(header);
		System.out.println(text);
/*		if(text.equals(data))
		{
			assertThat(true);
		}
		else
			assertThat(false);*/
	}

	@And("^Click subMenu with its menu$")
	public void click_subMenu_with_its_Menu_as(DataTable table)
	{
		map = table.asMap(String.class, String.class);
		String menu_xpath = con.custom_xpath(con.prop.getProperty("menu_part1_xpath"), 
				map.get("menu"), con.prop.getProperty("common_xpath"));
		String subMenu_xpath = con.custom_xpath(con.prop.getProperty("menu_part1_xpath"), 
				map.get("subMenu"), con.prop.getProperty("common_xpath"));
		con.driver.findElement(By.xpath(menu_xpath)).click();
		con.driver.findElement(By.xpath(subMenu_xpath)).click();		
	}
	
	@And("^Logout by User$")
	public void logOut_by(DataTable table)
	{
		map = table.asMap(String.class, String.class);
		String xpath = con.custom_xpath(con.prop.getProperty("menu_part1_xpath"), 
				map.get("user"), con.prop.getProperty("common_xpath"));
		con.driver.findElement(By.xpath(xpath)).click();
		con.click("logOutBtn_xpath");
	}
	
	@When("^User opens profile$")
	public void user_Profile(DataTable table)
	{
		map = table.asMap(String.class, String.class);
		String xpath = con.custom_xpath(con.prop.getProperty("menu_part1_xpath"), 
				map.get("user"), con.prop.getProperty("common_xpath"));
		con.driver.findElement(By.xpath(xpath)).click();
		con.click("myProfile_xpath");
	}	
	
	@And("Wait {int}")
	public void wait(Integer wait) throws InterruptedException
	{
		// Wait is in milliseconds
		
	}
	
	@Then("^Check error message$")
	public void check_error_msg(DataTable table)
	{	
		map = table.asMap(String.class, String.class);		
		String err_xpath = con.custom_xpath(con.prop.getProperty("errorMsg_xpath"), 
				map.get("errorText"), con.prop.getProperty("common_xpath"));
		con.driver.findElement(By.xpath(err_xpath)).isDisplayed();
		//con.isElementPresent(err_xpath);
	}
	
}

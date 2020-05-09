	package com.swm.runner;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.junit.runner.RunWith;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

//import io.cucumber.testng.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		dryRun=false,
		strict=true,
		monochrome=false,
		features= {"src/test/java/com/swm/features/"},
		glue = {"com.swm.stepDefinitions"},
/*	    plugin= {
				"html:target/site/cucumber-html",
				"json:target/cucumber1.json"}*/
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

@Test
public class Runner extends AbstractTestNGCucumberTests
{
	public Properties prop;

	String fileName="Report.html";
	Date d = new Date();
	String folderName=d.toString().replace(":", "_");
	String reportPath = System.getProperty("user.dir")+"\\reports\\" + folderName;
	// directory of the report folder
	//File file = new File(reportPath);
	boolean dir = new File(reportPath).mkdirs();
	String value = "reports/" + folderName + "/ExtentHtml.html";
	
	@BeforeSuite
	public void setrepPath() 
	{
		System.setProperty("extent.reporter.html.start","true");
		System.setProperty("extent.reporter.logger.start","true");
	    System.setProperty("extent.reporter.spark.start","true");
			
		System.setProperty("extent.reporter.html.config","src/test/resources/extent-config.xml");
		System.setProperty("extent.reporter.spark.config","src/test/resources/extent-config.xml");
		System.setProperty("extent.reporter.logger.config","src/test/resources/extent-config.xml");
		System.setProperty("extent.reporter.html.out",value);
	    System.setProperty("extent.reporter.logger.out","test-output/Logger");
			
		System.setProperty("extent.reporter.spark.out","test-output/Spark");
		System.setProperty("screenshot.dir","test-output");

	}
	
}
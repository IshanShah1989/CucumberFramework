package com.swm.reports;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.swm.util.BaseUtil;

public class ExtentReportsUtil extends BaseUtil{
	
	String fileName = reportLocation+"extentreport.html";
	
	
	//Create extent reports
	public void ExtentReport()
	{
		rep = new ExtentReports();
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Test Report for Selenium Cucumber");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Test Report");
		
		rep.attachReporter(htmlReporter);
	}
	
	public void extentReportScreenshot()
	{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(reportLocation+"screenshot.png"));
			scenarioDef.fail("Attached Screenshot for failure");
			//put screenshot file in reports
			//scenario.log(Status.FAIL,"Screenshot-> "+ scenario.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reportFlush()
	{
		rep.flush();
	}

}

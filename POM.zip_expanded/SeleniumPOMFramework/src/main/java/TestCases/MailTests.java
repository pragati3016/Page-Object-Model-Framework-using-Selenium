package TestCases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageClasses.LandingPage;
import PageClasses.RediffMailPage;
import PageClasses.WriteMailPage;
import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import utilities.TestDataProvider;

public class MailTests extends BaseTestClass{
	
	LandingPage landingPage;
	RediffMailPage rediffMailPage;
	WriteMailPage writeMailPage;
	
	@Test(dataProvider="rediffMailTestData")
	public void writeMailTest(Hashtable<String, String> testData) {
		
		logger = report.createTest("Write Email Test Case");
		
		invokeBrowser("chrome");
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		rediffMailPage = landingPage.clickRediffMailLink();
		writeMailPage = rediffMailPage.doSignIn(testData.get("Username"), testData.get("Password"));
		writeMailPage = writeMailPage.writeMail(testData.get("Email ID"), testData.get("Subject"));
	}

	@DataProvider
	public Object[][] rediffMailTestData(){
		return TestDataProvider.getTestData("RediffPortFolioLaunch.xlsx", "RediffMailData", "writeMail");
	}
}

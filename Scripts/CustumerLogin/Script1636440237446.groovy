import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import utileria.accion as accion

//Open Browser
WebUI.openBrowser("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login")
WebUI.maximizeWindow();


//Select custumer Option
if(accion.present("//strong[contains(text(),'XYZ Bank')]")) {
	accion.click("//button[contains(text(),'Customer Login')]");
	
//Select custumer
if(accion.present("//label[contains(text(),'Your Name :')]")) {
	accion.click("//select[@id='userSelect']");
	accion.click("//option[contains(text(),'Hermoine Granger')]");
	accion.click("//button[contains(text(),'Login')]");
	}
	
//make a deposit
if(accion.present("//button[@ng-click='deposit()']")) {
	accion.click("//button[@ng-click='deposit()']");
	accion.setText("//input[@type='number']", '80');
	accion.click("//button[@type='submit']");

	if(accion.present("//span[contains(text(),'Deposit Successful')]")) {
		accion.agregarPuntoDeVerificacion("Deposit Successful", true, true);	
	}
	
}
	
//make a withdrawl
if(accion.present("//button[@ng-click='withdrawl()']")) {
	accion.click("//button[@ng-click='withdrawl()']");
	accion.setText("//input[@type='number']", '80');
	accion.click("//button[@type='submit']");
	
	if(accion.present("//span[contains(text(),'Transaction successful')]")) {
		accion.agregarPuntoDeVerificacion("Deposit Successful", true, true);
		accion.click("//button[contains(text(),'Logout')]");
		accion.click("//button[contains(text(),'Home')]");
	}
	
}
	}else {
	accion.agregarPuntoDeVerificacion("The page did not open", true, true);
}


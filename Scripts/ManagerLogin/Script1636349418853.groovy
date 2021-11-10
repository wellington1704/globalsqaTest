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

WebUI.callTestCase(findTestCase("Test Cases/CustumerLogin"), [:], FailureHandling.STOP_ON_FAILURE);

TestData registerdata = findTestData("registerdata");

int rows = registerdata.getRowNumbers();

String firstName;
String lastName;
String postCode;


for(int i = 1; i<= rows; i++) {
	String status = registerdata.getObjectValue('ejecutar', i).toString();
	//if status = true
	if(status.equalsIgnoreCase("TRUE")) {
		
		//if the register custumer form appear
		if(accion.present("//button[contains(text(),'Bank Manager Login')]")) {
			
			firstName = registerdata.getObjectValue('firstName', i).toString();
			lastName = registerdata.getObjectValue("lastName", i).toString();
			postCode = registerdata.getObjectValue("postCode", i).toString();
			
			accion.agregarPuntoDeVerificacion("Register Screen is displayed", true, true);
			
			
			//clic on Bank Manager Login
	     	accion.click("//button[contains(text(),'Bank Manager Login')]");
			
			//register custumer
			if(accion.present("//button[@ng-click='addCust()']")) {
				accion.click("//button[@ng-click='addCust()']");
				accion.setText("//input[@placeholder='First Name']", firstName);		
				accion.setText("//input[@placeholder='Last Name']", lastName);
				accion.setText("//input[@placeholder='Post Code']", postCode);
				accion.click("//button[@type='submit']");
				
				accion.agregarPuntoDeVerificacion("custumer form completed", true, true);
				
				//Custumer register confirmation
				accion.click("//button[@ng-click='showCust()']");
				accion.setText("//input[@placeholder='Search Customer']", lastName);
				if(accion.present("//td[contains(text(),'91000')]")) {
				accion.agregarPuntoDeVerificacion("customer registed", true, true);	
				
				//Sign off
				accion.click("//button[contains(text(),'Home')]");
			}
			
			else {
				accion.agregarPuntoDeVerificacion("Register Screen isn't displayed", true, true);
				
			}
			
			}
		}
	}
}
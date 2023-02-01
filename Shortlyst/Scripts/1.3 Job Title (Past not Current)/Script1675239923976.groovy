import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
//Input the Job Title Filters --------------------------------------------------------------------------
// Input Backend as the job
//------------------------------------------------------------------------------------------------------
//initiate distance for scrolling
// Row Looping
// scroll down thru leads page
// increament scroll distance
// Collumn Looping
//				if(expRow == 5) {
//					WebUI.executeJavaScript('window.scrollTo(1000, 2000)', [])
//				}
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW

WebUI.openBrowser('')

WebUI.navigateToUrl('https://app.shortlyst.ai/dashboard')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/Leads Backend/Page_Shortlyst Login/input_Sign In_username'), 'kukuh.hafiyyan@shortlyst.ai')

WebUI.setEncryptedText(findTestObject('Object Repository/Leads Backend/Page_Shortlyst Login/input_Sign In_password'), '11Gr9OhrofHE65Xw/KGKTQ==')

WebUI.click(findTestObject('Object Repository/Leads Backend/Page_Shortlyst Login/button_Continue'))

WebUI.click(findTestObject('Button/Page_Shortlyst/project navbar'))

WebUI.click(findTestObject('Button/Page_Shortlyst/Katalon Test Project'))

WebUI.click(findTestObject('Button/Page_Shortlyst/reset filter button'))

WebUI.click(findTestObject('Button/Page_Shortlyst/add job titles button'))

WebUI.setText(findTestObject('Button/Page_Shortlyst/input job title fields'), 'backend')

inputedJob = WebUI.getAttribute(findTestObject('Button/Page_Shortlyst/input job title fields'), 'value').toLowerCase()

println(inputedJob)

WebUI.sendKeys(findTestObject('Object Repository/Leads Backend/Page_Shortlyst/input_Learn More_add__input--text fs-14 for_d0dd67'), 
    Keys.chord(Keys.ENTER))

WebUI.selectOptionByValue(findTestObject('Button/Page_Shortlyst/dropdown current or past'), 'current_or_past', false)

WebUI.delay(10)

int y = 0

for (int row = 1; row < 5; row++) {
    WebUI.scrollToPosition(0, y)

    y = (y + 451)

    for (int collumn = 1; collumn < 4; collumn++) {
        int count = 0

        WebUI.click(findTestObject(('Leads Backend/Leads Card/Page_Shortlyst/leads ' + row) + collumn))

        WebUI.click(findTestObject('Leads Backend/Leads Experience/Page_Shortlyst/Experience Tab'), FailureHandling.STOP_ON_FAILURE)

        filterExist = WebUI.getText(findTestObject('Leads Backend/Leads Experience/Page_Shortlyst/Experience Tab'))

        if (filterExist.toLowerCase().contains(inputedJob)) {
            println(inputedJob)

            for (int expRow = 1; expRow < 3; expRow++) {
                WebUI.click(findTestObject('Leads Backend/Leads Experience/Page_Shortlyst/Experience ' + expRow))

                jobTitle = WebUI.getText(findTestObject('Leads Backend/Leads Experience/Page_Shortlyst/Experience Job Title ' + 
                        expRow))

                expYear = WebUI.getText(findTestObject('Leads Backend/Leads Experience/Page_Shortlyst/Experience Year ' + 
                        expRow))

                if (expYear.toLowerCase().contains('present')) {
                    count = (count + 1)
                } else {
                    if (jobTitle.toLowerCase().contains(inputedJob)) {
                        println(((inputedJob + ' found on profile ') + row) + collumn)

                        break
                    } else {
                        count = (count + 1)
                    }
                }
            }
            
            if (count == 2) {
                throw new com.kms.katalon.core.exception.StepFailedException((inputedJob + ' as past job is Not Found on Profile ' + row) + 
                collumn) 
            }
        } else {
            throw new com.kms.katalon.core.exception.StepFailedException((inputedJob + ' as past job is Not Found on Profile ' + row) + collumn)
        }
        
        WebUI.click(findTestObject('Button/Page_Shortlyst/button close leads slider'))
    }
}

WebUI.closeBrowser()

WebUI.getAttribute(findTestObject(null), '')


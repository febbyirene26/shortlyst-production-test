import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl('https://app.shortlyst.ai/dashboard')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/Leads Backend/Page_Shortlyst Login/input_Sign In_username'), 'kukuh.hafiyyan@shortlyst.ai')

WebUI.setEncryptedText(findTestObject('Object Repository/Leads Backend/Page_Shortlyst Login/input_Sign In_password'), '11Gr9OhrofHE65Xw/KGKTQ==')

WebUI.click(findTestObject('Object Repository/Leads Backend/Page_Shortlyst Login/button_Continue'))

WebUI.click(findTestObject('Button/Page_Shortlyst/project navbar'))

WebUI.click(findTestObject('Button/Page_Shortlyst/Katalon Test Project'))

WebUI.click(findTestObject('Button/Page_Shortlyst/reset filter button'))

//Input the Job Title Filters --------------------------------------------------------------------------
WebUI.click(findTestObject('Button/Page_Shortlyst/add job titles button'))

WebUI.setText(findTestObject('Button/Page_Shortlyst/input job title fields'), 'backend' // Input Backend as the job
    )

WebUI.sendKeys(findTestObject('Object Repository/Leads Backend/Page_Shortlyst/input_Learn More_add__input--text fs-14 for_d0dd67'), 
    Keys.chord(Keys.ENTER))

WebUI.selectOptionByValue(findTestObject('Button/Page_Shortlyst/dropdown current or past'), 'past_not_current', false)

//------------------------------------------------------------------------------------------------------
WebUI.delay(10)

int y = 0 //initiate distance for scrolling

// Row Looping
for (int row = 1; row < 5; row++) {
    WebUI.scrollToPosition(0, y // scroll down thru leads page
        )

    y = (y + 451 // increament scroll distance
    )

    // Collumn Looping
    for (int collumn = 1; collumn < 4; collumn++) {
        WebUI.click(findTestObject(('Leads Backend/Leads Card/Page_Shortlyst/leads ' + row) + collumn))

        WebUI.click(findTestObject('Leads Backend/Leads Experience/Page_Shortlyst/Experience Tab'), FailureHandling.STOP_ON_FAILURE)

        int finalExpRow = 0

        filterExist = WebUI.getText(findTestObject('Leads Backend/Leads Experience/Page_Shortlyst/Experience Tab'))

        if (filterExist.toLowerCase().contains('present') && filterExist.toLowerCase().contains('backend')) {
            for (int expRow = 1; expRow < 6; expRow++) {
                WebUI.click(findTestObject('Leads Backend/Leads Experience/Page_Shortlyst/Experience ' + expRow))

                jobTitle = WebUI.getText(findTestObject('Leads Backend/Leads Experience/Page_Shortlyst/Experience ' + expRow))

                expYear = WebUI.getText(findTestObject('Leads Backend/Leads Experience/Page_Shortlyst/Experience ' + expRow))

                if (expYear.toLowerCase().contains('present')) {
                    if (jobTitle.toLowerCase().contains('backend')) {
                        println('backend found!')

                        break
                    } else {
                        continue
                    }
                } else {
                    throw new com.kms.katalon.core.exception.StepFailedException(('Backend Not Found on Profile ' + row) + 
                    collumn)
                }
            }
        } else {
            throw new com.kms.katalon.core.exception.StepFailedException(('Present or Backend Not Found on Profile ' + row) + 
            collumn)
        }
        
        WebUI.click(findTestObject('Button/Page_Shortlyst/button close leads slider'))
    }
}

WebUI.closeBrowser()


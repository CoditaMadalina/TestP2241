package org.example.pom;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class FormPom {

    static public WebDriver driver;
    static public JavascriptExecutor js;

    @FindBy(xpath = "//*[text()='Forms']")
    WebElement forms;

    @FindBy(xpath = "//*[text()='Practice Form']")
    WebElement practiceForm;

    @FindBy(xpath = "//*[@id='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//*[@id='lastName']")
    WebElement lastName;

    @FindBy(xpath = "//*[@id='userEmail']")
    WebElement userEmail;

    @FindBy(xpath = "//*[@id='userNumber']")
    WebElement userNumber;

    @FindBy(xpath = "//*[@id='dateOfBirthInput']")
    WebElement dateOfBirthInput;

    @FindBy(xpath = "//*[@id='subjectsInput']")
    WebElement subjectInput;

    @FindBy(xpath = "//*[@id='state']")
    WebElement state;

    @FindBy(xpath = "//*[@id='city']")
    WebElement city;

    @FindBy(xpath = "//*[@id='submit']")
    WebElement submit;


    public FormPom(WebDriver driverParam) {
        driver = driverParam;
        js=(JavascriptExecutor)driver;
        PageFactory.initElements(driver, this);
    }

    public void clickForms() {
        closeAdvert();
        scrollToElement(forms);
        pause(1000);
        forms.click();
    }

    public void clickPracticeForm(){
        practiceForm.click();
        closeAdvert();
    }

    @Step("Set first name")
    public void setFirstName(String firstNameParam){
        takeScreenshot("Before set first name");
        firstName.clear();
        firstName.sendKeys(firstNameParam);
        takeScreenshot("After set first name");
    }

    @Step("Set last name")
    public void setLastName(String lastNameParam){
        takeScreenshot("Before set last name");
        lastName.clear();
        lastName.sendKeys(lastNameParam);
        takeScreenshot("After set last name");
    }

    @Step("Set email")
    public void setEmail(String emailParam){
        takeScreenshot("Before set email");
        userEmail.clear();
        userEmail.sendKeys(emailParam);
        takeScreenshot("After set email");
    }

    @Step("Set number")
    public void setNumber(String numberParam){
        takeScreenshot("Before set number");
        userNumber.clear();
        userNumber.sendKeys(numberParam);
        takeScreenshot("After set number");
    }

    @Step("Set gender")
    public void setGender(String genderParam){
        takeScreenshot("Before set gender");
        WebElement gender = driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']/../input"));
        gender.click();
        takeScreenshot("After set gender");
    }

    public String getTableData(String labelParam){
        String value = driver.findElement(By.xpath("//table//*[text()='" + labelParam + "']/../*[2]")).getText();
        return value;
    }

    @Step("Set state")
    public void setState(String stateParam){
        takeScreenshot("Before set state");
        scrollToElement(state);
        state.click();
        WebElement stateOption = state.findElement(By.xpath(".//*[text()='" + stateParam + "']"));
        stateOption.click();
        takeScreenshot("After set state");
    }

    @Step("Set city")
    public void setCity(String cityParam){
        takeScreenshot("Before set city");
        city.click();
        WebElement cityOption = city.findElement(By.xpath(".//*[text()='" + cityParam + "']"));
        cityOption.click();
        takeScreenshot("After set city");
    }

    @Step("Set hobbies")
    public void setHobbies(String hobbiesParam){
        takeScreenshot("Before set hobbies");
        WebElement ddState = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()='" + hobbiesParam + "']/../input"));
        ddState.sendKeys("");
        takeScreenshot("After set hobbies");
    }

    @Step("Set DOB")
    public void setDate(String dateParam){
        takeScreenshot("Before set DOB");
        dateOfBirthInput.sendKeys(Keys.CONTROL + "a");
        dateOfBirthInput.sendKeys(dateParam);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        takeScreenshot("After set DOB");
    }

    @Step("Set subject")
    public void setSubject(String subjectParam){
        takeScreenshot("Before set subject");
        subjectInput.sendKeys(subjectParam);
        subjectInput.sendKeys(Keys.ENTER);
        takeScreenshot("After set subject");
    }

    @Step("Set submit")
    public void clickSubmit(){
        takeScreenshot("Before set submit");
        submit.click();
        takeScreenshot("After set submit");
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void closeAdvert() {
        try {
            js.executeScript("var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
        try {
            js.executeScript("var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
    }


    public void pause(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void takeScreenshot(String stepName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(stepName, "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            Allure.addAttachment("Screenshot Error", e.toString());
        }
    }
}

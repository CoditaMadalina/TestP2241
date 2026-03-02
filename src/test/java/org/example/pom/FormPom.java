package org.example.pom;

import org.example.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void setFirstName(String firstNameParam){
        firstName.clear();
        firstName.sendKeys(firstNameParam);
    }

    public void setLastName(String lastNameParam){
        lastName.clear();
        lastName.sendKeys(lastNameParam);
    }

    public void setEmail(String emailParam){
        userEmail.clear();
        userEmail.sendKeys(emailParam);
    }

    public void setNumber(String numberParam){
        userNumber.clear();
        userNumber.sendKeys(numberParam);
    }

    public void setGender(String genderParam){
        WebElement gender = driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']/../input"));
        gender.click();
    }

    public String getTableData(String labelParam){
        String value = driver.findElement(By.xpath("//table//*[text()='" + labelParam + "']/../*[2]")).getText();
        return value;
    }

    public void setState(String stateParam){
        scrollToElement(state);
        state.click();
        WebElement stateOption = state.findElement(By.xpath(".//*[text()='" + stateParam + "']"));
        stateOption.click();
    }

    public void setCity(String cityParam){
        city.click();
        WebElement cityOption = city.findElement(By.xpath(".//*[text()='" + cityParam + "']"));
        cityOption.click();
    }

    public void setHobbies(String hobbiesParam){
        WebElement ddState = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()='" + hobbiesParam + "']/../input"));
        ddState.sendKeys("");
    }

    public void setDate(String dateParam){
        dateOfBirthInput.sendKeys(Keys.CONTROL + "a");
        dateOfBirthInput.sendKeys(dateParam);
        dateOfBirthInput.sendKeys(Keys.ENTER);
    }


    public void setSubject(String subjectParam){
        subjectInput.sendKeys(subjectParam);
        subjectInput.sendKeys(Keys.ENTER);
    }

    public void clickSubmit(){
        submit.click();
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
}

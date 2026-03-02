package org.example.testng;

import org.example.pom.FormPom;
import org.example.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class FormTest {

    static public WebDriver driver ;


    static public String URL = "https://demoqa.com/";
    static public String FIRST_NAME = "Madalina";
    static public String LAST_NAME = "Codita";
    static public String EMAIL = "madalinacodita71@gmail.com";
    static public String GENDER = "Female";
    static public String NUMBER = "0604672285";
    static public String DATE = "6 JAN 2007";
    static public String SUBJECT = "Maths";
    static public String HOBBIES = "Sports";
    static public String STATE = "Rajasthan";
    static public String CITY = "Jaipur";



    @BeforeMethod
    public void beforeMethod(){
        driver = Driver.getAutoLocalDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void formTest(){
        System.out.println("Start test");
        driver.get(URL);
        FormPom formPom = new FormPom(driver);
        formPom.closeAdvert();
        formPom.pause(1800);
        formPom.clickForms();
        formPom.pause(2000);
        formPom.clickPracticeForm();
        formPom.pause(2000);
        formPom.setFirstName(FIRST_NAME);
        formPom.setLastName(LAST_NAME);
        formPom.setEmail(EMAIL);
        formPom.setGender(GENDER);
        formPom.setNumber(NUMBER);
        formPom.setDate(DATE);
        formPom.setSubject(SUBJECT);
        formPom.setHobbies(HOBBIES);
        formPom.setState(STATE);
        formPom.setCity(CITY);
        formPom.pause(1000);
        formPom.clickSubmit();
        formPom.pause(5000);
        String actualName = formPom.getTableData("Student Name");
        Assert.assertEquals(actualName, FIRST_NAME+" "+LAST_NAME);
        String actualEmail = formPom.getTableData("Student Email");
        Assert.assertEquals(actualEmail, EMAIL);
        String actualGender = formPom.getTableData("Gender");
        Assert.assertEquals(actualGender, GENDER);
        String actualNumber = formPom.getTableData("Mobile");
        Assert.assertEquals(actualNumber, NUMBER);
        String actualSubject = formPom.getTableData("Subjects");
        Assert.assertEquals(actualSubject, SUBJECT);
        String actualStateCity = formPom.getTableData("State and City");
        Assert.assertEquals(actualStateCity, STATE+" "+CITY);
        System.out.println("Finish test");
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}

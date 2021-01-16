package pages;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtilities;
import utilities.Waits;

public class SignUp {
    private WebDriver driver;
    private Fairy fairy= Fairy.create();
    private Person person;
    public SignUp(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
        person= fairy.person();

    }


    @FindBy(how = How.XPATH,using="//h3[normalize-space()='Create an account']")
    private WebElement createAccountInitialPageHeading;

    @FindBy(how = How.XPATH,using = "//input[@id='email_create']")
    private WebElement emailField;

    @FindBy(how = How.XPATH, using = "//span[normalize-space()='Create an account']")
    private WebElement createAccountBtn;

    @FindBy(how = How.XPATH,using = "//input[@id='id_gender1']")
    private WebElement maleGenderCheckBox;

    @FindBy(how = How.XPATH,using = "//input[@id='customer_firstname']")
    private WebElement firstName;

    @FindBy(how = How.XPATH,using = "//input[@id='customer_lastname']")
    private WebElement lastName;

    @FindBy(how = How.XPATH,using = "//input[@id='email']")
    private WebElement formEmailField;

    @FindBy(how = How.XPATH,using = "//input[@id='passwd']")
    private WebElement setPasswordField;

    @FindBy(how = How.XPATH, using = "//select[@id='days']")
    private WebElement selectDateDropDown;

    @FindBy(how = How.XPATH,using = "//select[@id='months']")
    private WebElement selectMonthDropDown;

    @FindBy(how = How.XPATH,using = "//select[@id='years']")
    private WebElement selectYearDropDown;

    @FindBy(how = How.XPATH,using = "//input[@id='newsletter']")
    private WebElement newsletterCheckBox;

    @FindBy(how = How.XPATH,using = "//input[@id='firstname']")
    private WebElement billingAddressFirstNameField;

    @FindBy(how = How.XPATH,using = "//input[@id='lastname']")
    private WebElement billingAddressLastNamefield;

    @FindBy(how = How.XPATH,using = "//input[@id='address1']")
    private WebElement addressLine1Field;

    @FindBy(how = How.XPATH,using = "//input[@id='city']")
    private WebElement cityField;

    @FindBy(how = How.XPATH,using = "//select[@id='id_state']")
    private WebElement selectStateDropDown;

    @FindBy(how = How.XPATH,using = "//input[@id='postcode']")
    private WebElement zipPostCodeField;

    @FindBy(how = How.XPATH,using = "//input[@id='phone_mobile']")
    private WebElement mobileField;

    @FindBy(how = How.XPATH,using = "//span[normalize-space()='Register']")
    private WebElement registerBtn;

    @FindBy(how = How.XPATH,using = "//label[normalize-space()='Choose a delivery address:']")
    private WebElement chooseAddress;

    public void fillEmailToOpenSignUpPage(){
        CommonUtilities.setValue(emailField,person.getEmail());
        CommonUtilities.clickWebElement(createAccountBtn);
        Waits.waitAjax(driver);
    }

    public void fillSignUpForm(){
        CommonUtilities.selectCheckBoxOrRadioButton(maleGenderCheckBox);
        CommonUtilities.setValue(firstName,person.getFirstName());
        CommonUtilities.setValue(lastName,person.getLastName());
        CommonUtilities.setValue(setPasswordField,person.getPassword());
        CommonUtilities.selectDropdown(selectDateDropDown,"5");
        CommonUtilities.selectDropdown(selectMonthDropDown,"4");
        CommonUtilities.selectDropdown(selectYearDropDown,"1994");
        CommonUtilities.selectCheckBoxOrRadioButton(newsletterCheckBox);
        CommonUtilities.setValue(billingAddressFirstNameField,person.getFullName());
        CommonUtilities.setValue(addressLine1Field,person.getAddress().getAddressLine1());
        CommonUtilities.setValue(cityField,person.getAddress().getCity());
        CommonUtilities.selectDropdown(selectStateDropDown,"1");
        CommonUtilities.setValue(zipPostCodeField,person.getAddress().getPostalCode());
        CommonUtilities.setValue(mobileField,person.getTelephoneNumber());
        CommonUtilities.clickWebElement(registerBtn);
        Waits.waitAjax(driver);
    }

    public String getChooseAddress(){
        return chooseAddress.getText();
    }


}


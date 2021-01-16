package stepDefinition;

import enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.SignUp;
import utilities.TestContext;
import utilities.Waits;

public class SignUpDef {

    private TestContext context;
    private SignUp signUp;

    public SignUpDef(TestContext context){
        this.context=context;
        signUp=context.getPageObjectManager().getSignUp();
    }

    @And("enter personal details on signup page")
    public void enter_personal_details_on_signup_page() {
        signUp.fillEmailToOpenSignUpPage();
        signUp.fillSignUpForm();
    }


}

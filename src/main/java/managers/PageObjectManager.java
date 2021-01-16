package managers;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageObjectManager {
    private WebDriver driver;
    private HomePage pagesHomePage;
    private ProductListing pagesProductListing;
    private Cart pagesCart;
    private Checkout pagesCheckout;
    private Confirmation pagesConfirmation;

    public PageObjectManager(WebDriver driver){
        this.driver=driver;
    }

    public HomePage getHomePage() {
        return (pagesHomePage==null)?pagesHomePage=new HomePage(driver):pagesHomePage;
    }

    public ProductListing getProductListing() {
        return (pagesProductListing==null)?pagesProductListing=new ProductListing(driver):pagesProductListing;
    }

    public Cart getCart() {
        return (pagesCart==null)?pagesCart=new Cart(driver):pagesCart;
    }

    public Checkout getCheckout() {
        return (pagesCheckout==null)?pagesCheckout=new Checkout(driver):pagesCheckout;
    }

    public Confirmation getConfirmation() {
        return (pagesConfirmation==null)?pagesConfirmation=new Confirmation(driver):pagesConfirmation;
    }


}

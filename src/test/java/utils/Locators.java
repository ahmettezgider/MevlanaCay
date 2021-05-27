package utils;

import org.openqa.selenium.By;

public interface Locators {

    // url
    String urlDe = "https://www.mevlanacay.de/";
    String urlEn = "https://www.mevlanacay.de/en/";
    String urlTr = "https://www.mevlanacay.de/tr/";


    By headerEmailSubmissionEmail = By.cssSelector("div.mctb-bar input[name='email']");
    By headerEmailSubmissionSubmit = By.cssSelector("div.mctb-bar input[type='submit']");
    // Header and Footer Selectors
    By slides = By.cssSelector("rs-slides rs-slide");
    By logo = By.cssSelector("div.logo");
    By menuTop = By.cssSelector("nav#menu");
    By responsiveMenuToggle = By.cssSelector(".responsive-menu-toggle");
    By menuTopMainLinks = By.xpath("//nav[@id='menu']/ul/li");
    By mainMenu = By.cssSelector("ul#menu-ana-menu>li");
    By mainMenuSubMenu = By.cssSelector("ul.sub-menu");
    By mobileMenuClose = By.cssSelector("a.close");
    By languageLocator = By.xpath("(//nav[@id='menu']/ul/li)[6]");
    By menuOverlay = By.cssSelector("div.body_overlay");
    By subHeaders = By.xpath("//div[@id='Subheader']//li");
    By linksMainMenu = By.cssSelector("ul#menu-ana-menu>li a");
    By linksFooterMenu = By.cssSelector("footer[id='Footer'] ul[id^='menu-footer-menu'] a");
    //By linksFooterMenu = By.cssSelector("#Footer .widgets_wrapper a");

    // Homepage and Menu selectors
    By cookieDialog = By.cssSelector("div#cf-root");
    By cookieDialog1 = By.xpath("//div[@id='cf-root']");


    // products (shop) page selectors
    By productListShop = By.cssSelector("ul.products li");
    By productNameShop = By.cssSelector("div.desc h4");
    By productPriceShop = By.cssSelector("bdi");
    By iconBasket = By.cssSelector("i.icon-basket");
    By iconLink = By.cssSelector("i.icon-link");

    // product Detail Page Selectors
    By productNameDetail = By.cssSelector("h1[itemprop='name']");
    By productPriceDetail = By.cssSelector("p.price");
    By inputQuantityDetail = By.cssSelector("input[name='quantity']");
    By buttonAddToCart = By.cssSelector("button[name='add-to-cart']");
    By tabListDetail = By.cssSelector("ul[role='tablist']");
    By productImageContainer = By.xpath("(//figure/div)[1]");
    By productBigImage = By.cssSelector("img[role='presentation']");
    By productSubImages = By.cssSelector("ol.flex-control-nav li");
    By productImageLens = By.cssSelector("a.woocommerce-product-gallery__trigger");
    By tabDescription = By.cssSelector("tab-description");
    By tabAdditionalInfo = By.cssSelector("tab-additional_information");
    By tabReview = By.cssSelector("li[aria-controls='tab-reviews']");
    By starsProductReviewDetail = By.cssSelector("p.stars a");
    By commentProductReviewDetail = By.cssSelector("textarea#comment");
    By nameProductReviewDetail = By.cssSelector("input#author");
    By emailproductReviewDetail = By.cssSelector("input#email");
    By buttonSubmitReview = By.cssSelector("div#review_form input[type='submit']");

    // Cart
    By notificationAlertCart = By.cssSelector("div.alert_wrapper");
    By tableCart = By.cssSelector("table.cart");
    By tableCartProductNames = By.cssSelector("td.product-name");
    By tableCartPrices = By.cssSelector("td.product-price");
    By tableCartQuantities = By.cssSelector("td.product-quantity input");
    By tableCartSubTotals = By.cssSelector("td.product-subtotal");
    By tableCartRemove = By.cssSelector("td.product-remove > a");


    By inputQuantityInCartTable = By.cssSelector("div.quantity input");
    By nameInCartTable = By.cssSelector("td.product-name");
    By priceInCartTable = By.cssSelector("td.product-price");
    By subtotalInCartTable = By.cssSelector("td.product-subtotal");
    By removeInCartTable = By.cssSelector("product-remove a");

    By cartSubTotal = By.cssSelector("tr.cart-subtotal bdi");
    By cartTotal = By.cssSelector("tr.order-total bdi");
    By cartShipping = By.cssSelector("tr.shipping");
    By proceedButton = By.cssSelector("div.wc-proceed-to-checkout a");

    // order form
    By orderFormFirstName = By.cssSelector("input#billing_first_name");
    By orderFormLastName = By.cssSelector("input[name='billing_last_name']");
    By orderFormCompany = By.cssSelector("input#billing_company");
    By orderFormCountrySelect = By.cssSelector("span#select2-billing_country-container");
    By orderFormCountrySelectList = By.cssSelector("ul#select2-billing_country-results > li");
    By orderFormAddress1 = By.cssSelector("input#billing_address_1");
    By orderFormAddress2 = By.cssSelector("input#billing_address_2");
    By orderFormPostcode = By.cssSelector("input[name='billing_postcode']");
    By orderFormCity = By.cssSelector("input#billing_city");
    By orderFormPhone = By.cssSelector("input#billing_phone");
    By orderFormEmail = By.cssSelector("input#billing_email");
    By orderFormOrderComments = By.cssSelector("textarea#order_comments");
    By orderFormAboneCheckBox = By.cssSelector("input#mailchimp_woocommerce_newsletter");
    By orderFormTerms = By.cssSelector("input#terms");
    By orderFormPaymentTransfer = By.cssSelector("div#payment input#payment_method_bacs");
    By orderFormPaymentPlus = By.cssSelector("div#payment input#payment_method_paypal_plus");
    By orderFormPaymentPaypal = By.cssSelector("div[data-pm='PayPal']");  // in iframe
    By orderFormPaymentBank = By.cssSelector("div[data-pm='Bank']"); // in iframe
    By orderFormPaymentVisa = By.cssSelector("div[data-pm='Credit_Card']"); // in iframe
    By orderFormOrderButton = By.cssSelector("button#place_order");
    By getOrderFormAlert = By.cssSelector("div[role='alert']");
        // section.woocommerce-bacs-bank-details
        // li.iban


    // Contact us page locators
    By inputNameContact = By.cssSelector("input[name='your-name']");
    By inputEmailContact = By.cssSelector("input[name='your-email']");
    By inputSubjectContact = By.cssSelector("input[name='your-subject']");
    By textareaMessajeContact = By.cssSelector("textarea[name='your-message']");
    By buttonSubmitContact = By.cssSelector("div[role='form'] input[type='submit']");
    By notificationFormContact = By.xpath("//div[@role='form']/form//div[last()]");

    // Registration Form
    By registrationBar = By.cssSelector("div.mctb-bar");
    By inputMailRegistration = By.cssSelector("div.mctb-bar input[name='email']");
    By submitRegistration = By.cssSelector("div.mctb-bar input[type='submit']");

}

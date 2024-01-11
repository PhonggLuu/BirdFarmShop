/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp391.birdfarmshop.util;

public class Constants {

    public static final String GOOGLE_CLIENT_ID = "563213089252-rga4rc627cobtc25fjmjuom2168u1hrp.apps.googleusercontent.com";
    public static final String GOOGLE_CLIENT_SECRET = "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";
    public static final String GOOGLE_REDIRECT_URI = "http://localhost:8080/birdfarmshop/LoginGoogleController";
    public static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static final String GOOGLE_GRANT_TYPE = "authorization_code";

    public static final String FACEBOOK_APP_ID = "334333278988069";
    public static final String FACEBOOK_APP_SECRET = "5db2f58049271630d5afd5038a3821d6";
    public static final String FACEBOOK_REDIRECT_URL = "https://localhost:8080/birdfarmshop/LoginFacebookController";
    public static final String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";

    public static final String JWT_KEY = "birdfarmshop-swp-fptuniversity-token-base-authentication";
    
    public static final String ACCOUNT_SHOP ="thegioivetcanh@gmail.com";
    public static final String SECRET_KEY ="zpxzaesjzzglhnyx";
    public static final String HOST= "http://localhost:8080/birdfarmshop/";
    
    public static final String C3_KEY = "AKIAVOSH4FPLQHGLLMA5";
    public static final String C3_SECRET_KEY = "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";
    public static final String C3_HOST = "https://birdfarmshop.s3.ap-southeast-1.amazonaws.com/";
    
    public static final String VNPAY_KEY = "VSQ6NENZ";
    public static final String VNPAY_SECRET_KEY = "FZHSMOLLELQBQCZDCQWEANKTEEITDIIL";
    public static final String VNPAY_PAYMENT_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String VNPAY_RETURN = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String VNPAY_RETUNRURL= "http://localhost:8080/birdfarmshop/MainController?action=NavToReturnVNPAY";
    
}

package com.walmart.services.checkout.testng.buynowsuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.DataProviderArguments;
import com.util.JsonUtil;
import com.walmart.services.checkout.testng.buynowsuite.parameter.CheckoutApiV2TestParameters;
import com.walmart.services.checkout.testng.buynowsuite.parameter.Error;
import com.walmart.services.checkout.testng.buynowsuite.parameter.PlaceOrderRequest;
import com.walmart.services.checkout.testng.buynowsuite.parameter.PlaceOrderResponse;
import com.walmart.services.checkout.testng.buynowsuite.parameter.PrepareOrderRequest;
import com.walmart.services.checkout.testng.buynowsuite.parameter.PrepareOrderResponse;
import com.walmart.services.exceltoobjects.testng.ExcelDataToObjectConvertor;

@Test(enabled=true)
public class AffiliateCheckoutAPI2TestSuite {
	
	String env;
	String serverURL;
	String encryptionKey;
	
	public static  String propFileName = "affilaite_checkoutv2.properties";
	List<HttpMessageConverter<?>> list;
	
	/*
	 *  QA URL'S
	 *  https://api.walmartlabs.com/v2/qa/oauth2/token
	    https://api.walmartlabs.com/v2/qa/order/prepare
		https://api.walmartlabs.com/v2/qa/order/place
	 */
	@BeforeClass
	public void setup() throws IOException {
		
		// -Dcom.walmart.platform.config.runOnEnv="prod"
		env = System.getProperty("com.walmart.platform.config.runOnEnv");
		
		if(StringUtils.isBlank(env)) {
			env = "qa";
		} 
		
		//		Properties prop = new Properties();
		//		InputStream inputStream  = getClass().getClassLoader().getResourceAsStream("/"+env+"/"+propFileName);
		//		
		//		
		//		if(inputStream==null) {
		//			throw new FileNotFoundException("Property File {}"+propFileName+" .. Not Found");
		//		}
		//		prop.load(inputStream);
		//		serverURL = prop.getProperty("checkoutapiv2.serverURL");
		
		if("qa".equals(env)) {
			serverURL = "https://api.walmartlabs.com/v2/qa/";
			encryptionKey = "-----BEGIN PUBLIC KEY-----"+
							"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnIcQ+jEF1cfxJ0PjBvUE"+
							"kHRZMEJo2xli4Fx4TalXqK+a0eQU8+4q9383GZvxBWV57qi0xpLSr5mLwydR7eyy"+
							"n71vq8R5k3I2ww64zTb4sOc/R2NYwaY8AoRmsuzSj3RtvyiI5B1W/QOIXMYFMaAa"+
							"HE8KLylrBvHvoNBDQ79AYKWPFjmqL9m6SoPQzQFqQL6yYnt6BGsb2MaC0F7jP1LZ"+
							"JwrHoB3y3614eyBEVHi9jwhbU5EE/tQK09IsiCupM3MZcZp7PkVe1dBgvv8fOcFe"+
							"nZrGAZjP7C3wWLaSpM3ODwtay9PF+xTM109kXVfU/8FcevOe9U4Gy2/uDzYq5XKj"+
							"ywIDAQAB"+
							"-----END PUBLIC KEY-----";
		} else if("prod".equals(env)) {
			serverURL = "https://api.walmartlabs.com/v2/";
		} else {
			serverURL = "https://api.walmartlabs.com/v2/qa/";
			encryptionKey = "-----BEGIN PUBLIC KEY-----"+
					"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnIcQ+jEF1cfxJ0PjBvUE"+
					"kHRZMEJo2xli4Fx4TalXqK+a0eQU8+4q9383GZvxBWV57qi0xpLSr5mLwydR7eyy"+
					"n71vq8R5k3I2ww64zTb4sOc/R2NYwaY8AoRmsuzSj3RtvyiI5B1W/QOIXMYFMaAa"+
					"HE8KLylrBvHvoNBDQ79AYKWPFjmqL9m6SoPQzQFqQL6yYnt6BGsb2MaC0F7jP1LZ"+
					"JwrHoB3y3614eyBEVHi9jwhbU5EE/tQK09IsiCupM3MZcZp7PkVe1dBgvv8fOcFe"+
					"nZrGAZjP7C3wWLaSpM3ODwtay9PF+xTM109kXVfU/8FcevOe9U4Gy2/uDzYq5XKj"+
					"ywIDAQAB"+
					"-----END PUBLIC KEY-----";
		}
		
		ObjectMapper lax = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
		MappingJackson2HttpMessageConverter c = new MappingJackson2HttpMessageConverter(); 
		c.setObjectMapper(lax); 
		list = new ArrayList<HttpMessageConverter<?>>(); 
		list.add(c); 

	}
	
	@DataProvider(name = "AffiliateCheckoutApiV2DataProvider")
	public static Object[][] dataProvider(Method testMethod) throws Throwable {

		DataProviderArguments dataProviderArguments = testMethod.getAnnotation(DataProviderArguments.class);
		String fileName = dataProviderArguments.fileName();
		String records = dataProviderArguments.records();
		
		return ExcelDataToObjectConvertor.getTestNGObjectArrayFromExcel("/com/walmart/services/checkout/testng/providers/"+fileName, "CheckoutApiV2TestParameters", records, CheckoutApiV2TestParameters.class, false);
	}
	
	@Test(enabled = true,  dataProvider = "AffiliateCheckoutApiV2DataProvider")
	//@DataProviderArguments(fileName = "AffiliateBuyNowTestSuite.xlsx", records = "1-19,39-41,46-47")
	@DataProviderArguments(fileName = "AffiliateBuyNowTestSuite.xlsx", records = "49")
	public void prepareOrderPositiveTests(CheckoutApiV2TestParameters checkoutApiV2TestParameters) throws IOException {
		
		 System.out.println("Data Read from the Excel {}"+checkoutApiV2TestParameters.getDescription());
		
		 String access_token = OauthUtils.getOauthToken(serverURL);
		
		 RestTemplate restTemplate = new RestTemplate();
		 restTemplate.setMessageConverters(list);
	      
		 MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		 
		 headers.add("Authorization", "Bearer "+access_token);
		 headers.add("X-Walmart-User-IP", "127.0.0.1");
		 headers.add("X-Walmart-User-Device-Type", "browser");
		 headers.add("X-Walmart-User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
		 
		 System.out.println("PrepareOrderRequest {} "+JsonUtil.convertToString(checkoutApiV2TestParameters.getPrepareOrderRequest()));
		 
	     HttpEntity<PrepareOrderRequest> requestEntity = new HttpEntity<PrepareOrderRequest>(checkoutApiV2TestParameters.getPrepareOrderRequest(),headers);
	     ResponseEntity<PrepareOrderResponse> prepareOrderResponseEntity = null;
	      try{
	    	  prepareOrderResponseEntity = restTemplate.exchange(serverURL+"order/prepare", HttpMethod.POST,requestEntity, PrepareOrderResponse.class);
	      } catch(Exception ex) {
	    	  System.out.println("Exception Occured {}"+JsonUtil.convertToString(ex));
	      }
	          
	     
	     if(prepareOrderResponseEntity.getBody()!=null) {
	    	 System.out.println("prepareOrderResponse {} "+JsonUtil.convertToString(prepareOrderResponseEntity.getBody()));
	     }
	    
	     //validate the response..
	     Assert.assertEquals(checkoutApiV2TestParameters.getResponseStatusExpected(), prepareOrderResponseEntity.getStatusCode().toString());
		
	     if(checkoutApiV2TestParameters.getErrorCodeExpected()!=null){
	    	 validateErrorCode(checkoutApiV2TestParameters.getErrorCodeExpected(),prepareOrderResponseEntity.getBody().getErrors());
	     }
	     
	     if(checkoutApiV2TestParameters.getValidateTotals()!=null && checkoutApiV2TestParameters.getValidateTotals()) {
	    	 validateTotals(prepareOrderResponseEntity.getBody(),checkoutApiV2TestParameters);
	     }     
	 	
	}
	
	

	private void validateTotals(PrepareOrderResponse body,CheckoutApiV2TestParameters checkoutApiV2TestParameters) {
		
		
		Assert.assertNotNull(body.getTotals());
		Assert.assertNotNull(body.getTotals().getSubTotal(),"SubTotal Should be Present");
		Assert.assertNotNull(body.getTotals().getGrandTotal(), "GrandTotal Should Be Present");
		
		if(checkoutApiV2TestParameters.getValidateFees() !=null && checkoutApiV2TestParameters.getValidateFees()) {
			Assert.assertNotNull(body.getTotals().getFeesTotal());
			Assert.assertNotNull(body.getFees());
			
			Assert.assertNotNull(body.getTaxes());
			Assert.assertNotNull(body.getTotals().getTaxTotal());
		}
		
		if(checkoutApiV2TestParameters.getValidateThresholdShipping()!=null && checkoutApiV2TestParameters.getValidateThresholdShipping()) {
		
			Assert.assertEquals(0, body.getTotals().getShippingTotal().compareTo(BigDecimal.ZERO));
		}
		
		if(checkoutApiV2TestParameters.getValidateThresholdShipping()!=null && checkoutApiV2TestParameters.getValidateShipPrice()) {
			
			Assert.assertEquals(1, body.getTotals().getShippingTotal().compareTo(BigDecimal.ZERO));
		}
		
	}
	
	

	@Test(enabled = true,  dataProvider = "AffiliateCheckoutApiV2DataProvider", expectedExceptions = {org.springframework.web.client.ResourceAccessException.class})
	@DataProviderArguments(fileName = "AffiliateBuyNowTestSuite.xlsx", records = "20")
	public void prepareOrderInvalidTokenTest(CheckoutApiV2TestParameters checkoutApiV2TestParameters) throws IOException {
		
		System.out.println("Data Read from the Excel {}"+checkoutApiV2TestParameters.getDescription());
		
		// String access_token = OauthUtils.getOauthToken(serverURL);
		
		 RestTemplate restTemplate = new RestTemplate();
	      
		 MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		 
		 headers.add("Authorization", "Bearer fafaeowqfdsafdasfas");
		 
		 System.out.println("PrepareOrderRequest {} "+JsonUtil.convertToString(checkoutApiV2TestParameters.getPrepareOrderRequest()));
	     HttpEntity<PrepareOrderRequest> requestEntity = new HttpEntity<PrepareOrderRequest>(checkoutApiV2TestParameters.getPrepareOrderRequest(),headers);
	     ResponseEntity<PrepareOrderResponse> prepareOrderResponseEntity = restTemplate.exchange(serverURL+"order/prepare", HttpMethod.POST,requestEntity, PrepareOrderResponse.class);
	     
	     if(prepareOrderResponseEntity.getBody()!=null) {
	    	 System.out.println("prepareOrderResponse {} "+JsonUtil.convertToString(prepareOrderResponseEntity.getBody()));
	     }
	    
	     //validate the response..
	     Assert.assertEquals(checkoutApiV2TestParameters.getResponseStatusExpected(), prepareOrderResponseEntity.getStatusCode().toString());
		
	     if(checkoutApiV2TestParameters.getErrorCodeExpected()!=null){
	    	 validateErrorCode(checkoutApiV2TestParameters.getErrorCodeExpected(),prepareOrderResponseEntity.getBody().getErrors());
	     }
	}
	
	
	@Test(enabled = true,  dataProvider = "AffiliateCheckoutApiV2DataProvider")
	@DataProviderArguments(fileName = "AffiliateBuyNowTestSuite.xlsx", records = "21-38,42-45")
	public void placeOrderPositiveTests(CheckoutApiV2TestParameters checkoutApiV2TestParameters) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		System.out.println("Data Read from the Excel {}"+checkoutApiV2TestParameters.getDescription());
		
		 String access_token = OauthUtils.getOauthToken(serverURL);
		
		 RestTemplate restTemplate = new RestTemplate();
	      
		 MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		 
		 headers.add("Authorization", "Bearer "+access_token);
		 
		 System.out.println("PrepareOrderRequest {} "+JsonUtil.convertToString(checkoutApiV2TestParameters.getPrepareOrderRequest()));
	     HttpEntity<PrepareOrderRequest> requestEntity = new HttpEntity<PrepareOrderRequest>(checkoutApiV2TestParameters.getPrepareOrderRequest(),headers);
	     ResponseEntity<PrepareOrderResponse> prepareOrderResponseEntity = restTemplate.exchange(serverURL+"order/prepare", HttpMethod.POST,requestEntity, PrepareOrderResponse.class);
	     
	     if(prepareOrderResponseEntity.getBody()!=null) {
	    	 System.out.println("prepareOrderResponse {} "+JsonUtil.convertToString(prepareOrderResponseEntity.getBody()));
	     }
	    
	     //validate the response..
	     Assert.assertEquals("200", prepareOrderResponseEntity.getStatusCode().toString());
	     
	     checkoutApiV2TestParameters.getPlaceOrderRequest().setToken(prepareOrderResponseEntity.getBody().getToken().toString());
	     
	     
	     //File keyFile = new File("/Users/satmuri/workspace/master/checkout/checkoutservice/src/test/resources/pentest_public.cert");
	     
	     //Encrypt the Card Details..
	     PublicKey publicKey =  EncryptionUtil.readPublicKey(encryptionKey);
	     
	     //String encryptedCardNumber =  EncryptionUtil.encryptCardNumber("4716118355162725", publicKey);
	    String encryptedCardNumber =  EncryptionUtil.encryptCardNumber(checkoutApiV2TestParameters.getPlaceOrderRequest().getPaymentInfo().getCardInfo().getCardNumber(), publicKey);
	    		 
	    //"cjln+msVIlSJTmNN9i3gqMlwWV0diWNySMjSgSzetK3LTp80/IYNPF21wjgBBjaPtNnIzhrHcx+G/kejkN20MVYrrKG5goe6uoiSuPl/RbMv1OKon/b5Y+PKWB1HY9xiwLD1haDH939mi0lxbfpnDgULQR6MoaqRjn7hvaZIDjpb80R6aGP6Q4IpfmVcLvhfrBPmzc4tt2Zywx1CT3TxcuHZrujD5rl8L1vrCiqZFMvV9Z/h/r07HSHCsU/x+qUZDEb5B73qm68yZ9bS2dcJGw7l7grK+PFfRc66uSYcneWRHKWs8uhw9XiVuJXmxTUT5dbvHD8MZBVMS8XRPfeVUw==";
	    		 
	     
	     //Set the Encrypted Card Number
	     checkoutApiV2TestParameters.getPlaceOrderRequest().getPaymentInfo().getCardInfo().setCardNumber(encryptedCardNumber);
	     
		 System.out.println(" PlaceOrderRequest {} "+JsonUtil.convertToString(checkoutApiV2TestParameters.getPlaceOrderRequest()));
	     HttpEntity<PlaceOrderRequest> placeOrderReqEntity = new HttpEntity<PlaceOrderRequest>(checkoutApiV2TestParameters.getPlaceOrderRequest(),headers);
	     ResponseEntity<PlaceOrderResponse> placeOrderResponseEntity = restTemplate.exchange(serverURL+"order/place", HttpMethod.POST,placeOrderReqEntity, PlaceOrderResponse.class);
	     
	     if(placeOrderResponseEntity.getBody()!=null) {
	    	 System.out.println("Place Order Response {} "+JsonUtil.convertToString(placeOrderResponseEntity.getBody()));
	     }
	     
	     
	     Assert.assertEquals(checkoutApiV2TestParameters.getResponseStatusExpected(), placeOrderResponseEntity.getStatusCode().toString());
		
	     if(checkoutApiV2TestParameters.getErrorCodeExpected()!=null){
	    	 validateErrorCode(checkoutApiV2TestParameters.getErrorCodeExpected(),placeOrderResponseEntity.getBody().getErrors());
	     }
	}

	private void validateErrorCode(Integer errorCodeExpected, List<Error> errors) {
		
		boolean errorCodePresent = false;
		
		if(!CollectionUtils.isEmpty(errors)) {
			
			for(Error error:errors) {	
				  if(errorCodeExpected == error.getCode()) {
					  errorCodePresent = true;
					  break;
				  }
			}
		}
		
		if(!errorCodePresent) {
			Assert.fail("Expected Error Code {}"+errorCodeExpected+ " is not present in response");
		}
		
	}

}

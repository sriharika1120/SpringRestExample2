package com.walmart.services.cart.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.CustomResponseErrorHandler;
import com.util.DataProviderArguments;
import com.util.JsonUtil;
import com.walmart.services.cart.tests.parameter.CartParameters;
import com.walmart.services.cart.tests.parameter.CartRequest;
import com.walmart.services.cart.tests.parameter.CartResponse;
import com.walmart.services.exceltoobjects.testng.ExcelDataToObjectConvertor;

@Test(enabled=true)
public class CartAPITestSuite {
	
	String env;
	String serverURL;
	String encryptionKey;
	
	public static  String propFileName = "CartAPITestSuite.properties";
	List<HttpMessageConverter<?>> list = null;
	
	
	@BeforeClass
	public void setup() throws IOException {
		
		// -Dcom.walmart.platform.config.runOnEnv="prod"
		env = System.getProperty("com.walmart.platform.config.runOnEnv");
		
		if(StringUtils.isBlank(env)) {
			env = "qa";
		} 
		
		if("qa".equals(env)) {
			serverURL = "https://www.walmart.com/api/";
			/* encryptionKey = "-----BEGIN PUBLIC KEY-----"+
							"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnIcQ+jEF1cfxJ0PjBvUE"+
							"kHRZMEJo2xli4Fx4TalXqK+a0eQU8+4q9383GZvxBWV57qi0xpLSr5mLwydR7eyy"+
							"n71vq8R5k3I2ww64zTb4sOc/R2NYwaY8AoRmsuzSj3RtvyiI5B1W/QOIXMYFMaAa"+
							"HE8KLylrBvHvoNBDQ79AYKWPFjmqL9m6SoPQzQFqQL6yYnt6BGsb2MaC0F7jP1LZ"+
							"JwrHoB3y3614eyBEVHi9jwhbU5EE/tQK09IsiCupM3MZcZp7PkVe1dBgvv8fOcFe"+
							"nZrGAZjP7C3wWLaSpM3ODwtay9PF+xTM109kXVfU/8FcevOe9U4Gy2/uDzYq5XKj"+
							"ywIDAQAB"+
							"-----END PUBLIC KEY-----"; */
		} else if("prod".equals(env)) {
			serverURL = "https://www.walmart.com/api/";
		} else {
			serverURL = "https://www.walmart.com/api/";
			/* encryptionKey = "-----BEGIN PUBLIC KEY-----"+
				
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnIcQ+jEF1cfxJ0PjBvUE"+
					"kHRZMEJo2xli4Fx4TalXqK+a0eQU8+4q9383GZvxBWV57qi0xpLSr5mLwydR7eyy"+
					"n71vq8R5k3I2ww64zTb4sOc/R2NYwaY8AoRmsuzSj3RtvyiI5B1W/QOIXMYFMaAa"+
					"HE8KLylrBvHvoNBDQ79AYKWPFjmqL9m6SoPQzQFqQL6yYnt6BGsb2MaC0F7jP1LZ"+
					"JwrHoB3y3614eyBEVHi9jwhbU5EE/tQK09IsiCupM3MZcZp7PkVe1dBgvv8fOcFe"+
					"nZrGAZjP7C3wWLaSpM3ODwtay9PF+xTM109kXVfU/8FcevOe9U4Gy2/uDzYq5XKj"+
					"ywIDAQAB"+
					"-----END PUBLIC KEY-----"; */
		}
		
		ObjectMapper lax = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
		MappingJackson2HttpMessageConverter c = new MappingJackson2HttpMessageConverter(); 
		c.setObjectMapper(lax); 
		list = new ArrayList<HttpMessageConverter<?>>(); 
		list.add(c); 

	}
	
	@DataProvider(name = "CartAPITestSuiteDataProvider")
	public static Object[][] dataProvider(Method testMethod) throws Throwable {

		DataProviderArguments dataProviderArguments = testMethod.getAnnotation(DataProviderArguments.class);
		String fileName = dataProviderArguments.fileName();
		String records = dataProviderArguments.records();
		
		return ExcelDataToObjectConvertor.getTestNGObjectArrayFromExcel("/com/walmart/services/checkout/testng/providers/"+fileName, "CartParameters", records, CartParameters.class, false);
	}
	
	@Test(enabled = true,  dataProvider = "CartAPITestSuiteDataProvider")
    @DataProviderArguments(fileName = "cartTestData.xlsx", records = "1-2")
	public void createCartTests(CartParameters cartParameters) throws IOException {
		
		 System.out.println("Data Read from the Excel {}"+cartParameters.getDescription());
				
		 //Setting the New CustomerId
		 UUID customerId = UUID.randomUUID();
		 
		 cartParameters.getCartRequest().setCustomerId(customerId);
		 
		 RestTemplate restTemplate = new RestTemplate();
		 restTemplate.setErrorHandler(new CustomResponseErrorHandler());
		 restTemplate.setMessageConverters(list);
	      
		 MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		 
		// headers.add("Authorization", "Bearer "+access_token);
	
		 headers.add("Accept","application/json");
		 headers.add("WM_SVC.VERSION", "1.0.0");
		 headers.add("Content-Type", "application/json");
		 
		 System.out.println("CartRequest {} "+JsonUtil.convertToString(cartParameters.getCartRequest()));
		 
	     HttpEntity<CartRequest> requestEntity = new HttpEntity<CartRequest>(cartParameters.getCartRequest(),headers);
	     ResponseEntity<CartResponse> cartResponseEntity = null;
	      try{
	    	  cartResponseEntity = restTemplate.exchange(serverURL+"cart", HttpMethod.POST,requestEntity, CartResponse.class);
	      } catch(Exception ex) {
	    	  System.out.println("Exception Occured {}"+ex.getMessage());
	      }
	          
	     
	     if(cartResponseEntity.getBody()!=null) {
	    	 System.out.println("CartResponse {} "+JsonUtil.convertToString(cartResponseEntity.getBody()));
	     }
	    
	     Assert.assertEquals(cartParameters.getItemCountExp(), cartResponseEntity.getBody().getCart().getItemCount());
			
	     Assert.assertEquals(cartParameters.getSavedItemCountExp(), cartResponseEntity.getBody().getSflList().getItemCount());

	     Assert.assertEquals(cartParameters.getCartRequest().getCustomerType(), cartResponseEntity.getBody().getCart().getType());
	    
	     validateResponseLocation(cartParameters,cartResponseEntity.getBody());
	     
	  }

	private void validateResponseLocation(CartParameters cartParameters,
			CartResponse cartResponse) {
	
		Assert.assertEquals(cartParameters.getCartRequest().getLocation().getPostalCode(), cartResponse.getCart().getLocation().getPostalCode());
		
		Assert.assertEquals(cartParameters.getCartRequest().getLocation().getCity(), cartResponse.getCart().getLocation().getCity());

		Assert.assertEquals(cartParameters.getCurrencyCountExp(), cartResponse.getCart().getCurrencyCode());
		
		Assert.assertEquals(cartParameters.getCountryCodeExp(), cartResponse.getCart().getCurrencyCode());
		
		Assert.assertEquals(cartParameters.getSflstatus(), cartResponse.getSflList().getStatus());

	}

}

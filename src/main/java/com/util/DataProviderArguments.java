package com.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Additional arguments required for the DataProvider annotation
 * 
 * @author kgopin1
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DataProviderArguments {

	/**
	 * The excel file name located in the data directory in the classpath
	 * 
	 * @return The excel file name
	 */
	String fileName();

	/**
	 * The record numbers which are to be used for the test case. Following formats are supported <br/>
	 * 1,2,3,10,11,12 <br/>
	 * 1-3,10-12 <br/>
	 * 
	 * @return The record numbers
	 */
	String records();
}
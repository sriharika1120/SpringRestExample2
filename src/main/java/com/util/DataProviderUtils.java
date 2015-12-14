package com.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author kgopin1
 * 
 */
public class DataProviderUtils {

	/**
	 * Get the record numbers from the test method annotation
	 * 
	 * @param testMethod
	 * @return
	 */
	public static List<Integer> getRecordNumbers(Method testMethod) {

		List<Integer> recordNumbers = new ArrayList<Integer>();

		DataProviderArguments dataProviderArguments = testMethod.getAnnotation(DataProviderArguments.class);
		String records = dataProviderArguments != null ? dataProviderArguments.records() : null;

		if (records != null) {

			String[] data = records.split(",");
			for (int i = 0; i < data.length; i++) {
				if (StringUtils.isNotBlank(data[i])) {
					if (data[i].indexOf("-") > 0) {
						String[] range = data[i].split("-");
						for (int j = Integer.parseInt(range[0].trim()); j <= Integer.parseInt(range[1].trim()); j++) {
							recordNumbers.add(j);
						}
					} else {
						recordNumbers.add(Integer.parseInt(data[i].trim()));
					}
				}
			}
		}

		return recordNumbers;
	}

	/**
	 * Read the first row and return them as header records
	 * 
	 * @param rows
	 * @return
	 */
	public static List<String> getHeaders(Iterator<Row> rows) {
		List<String> headers = new ArrayList<String>();

		// Get the first record
		Row row = rows.next();
		Iterator<Cell> cells = row.cellIterator();

		// Read the headers
		while (cells.hasNext()) {
			Cell cell = cells.next();
			headers.add(cell.getStringCellValue());
		}

		return headers;
	}
}

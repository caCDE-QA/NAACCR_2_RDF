package org.mayo.edu.naacr.helper;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Makes sure the row is valid.   
 */
public class SheetMethods {

	public static void rowCheck(Sheet pharmSheet, Row row, int numRows)  {
	    int cols = 0; // No of columns
	    int tmp = 0;

	    // This trick ensures that we get the data properly even if it doesn't start from first few rows
	    for(int i = 0; i < 10 || i < numRows; i++) {
	        row = pharmSheet.getRow(i);
	        if(row != null) {
	            tmp = pharmSheet.getRow(i).getPhysicalNumberOfCells();
	            if(tmp > cols) cols = tmp;
	        }
	    }
	}
}

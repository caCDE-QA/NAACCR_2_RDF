package org.mayo.edu.naacr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.mayo.edu.naacr.helper.Constants;
import org.mayo.edu.naacr.helper.SheetMethods;
import org.mayo.edu.naacr.types.RowValue;
import org.mayo.edu.naacr.types.ValueSet;

/**
 * This program takes a spreadsheet containing NAACCR data elements 
 * and converts them into RDF triples.
 * 
 * It hack of a program with no testing and should be treated as such.
 * 
 * TODO - create a config file for name of input/output files
 */
public class NAACCR_2_RDF {
	
    static Row row = null;

    static HashMap<String, RowValue> dataElements;
    
	public static void main (String[] args) {
		try {
			String userDir = System.getProperty("user.dir");
			String fileIn = userDir +"\\files\\NAACCR-DD-subset.xlsx";
		    
		    Workbook wb = WorkbookFactory.create(new File(fileIn));
		    
		    RDF_Composer composer = new RDF_Composer();
		    String rdfText = composer.getHeader();
		    
		    dataElements = new HashMap<String, RowValue>();
		    
		    Sheet elementSheet = wb.getSheetAt(Constants.SHEET_META);
		    getElements(elementSheet);
		    
		    Sheet descSheet = wb.getSheetAt(Constants.SHEET_DESC);
		    getDescriptions(descSheet);
		    
	        Sheet valueSheet = wb.getSheetAt(Constants.SHEET_VS);
	        getValueSets(valueSheet);

		    rdfText = rdfText + composer.getRDF(dataElements);

		    File fileOut = new File( userDir +"/files/naaccr.ttl");
			if (!fileOut.exists()) {
				fileOut.createNewFile();
			}
 
			FileWriter fw = new FileWriter(fileOut.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(rdfText);
			bw.close();

			System.out.println("done");
	    }
		catch(Exception e)  {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads the values from the spreadsheet and puts them into the Objects
	 * @param elementSheet
	 * @return
	 */
	private static void getElements(Sheet elementSheet)  {
	    int numRows;
	    numRows = elementSheet.getPhysicalNumberOfRows();

	    SheetMethods.rowCheck(elementSheet, row, numRows);

	    for(int r = 1; r < numRows; r++) {
	        row = elementSheet.getRow(r);
	        if(row != null) {
	            String itemNum = getCellValue(Constants.COL_ITEM);
	            
	            if(itemNum!=null && !itemNum.equals(""))  {
            	    RowValue de = new RowValue();
                    de.setCode(itemNum);
            		de.setName(getCellValue(Constants.COL_NAME));
            		de.setAlt_name(getCellValue(Constants.COL_ALT_NAME));
            		de.setLength(getCellValue(Constants.COL_LEN));
            		de.setSource(getCellValue(Constants.COL_SRC));
                    de.setYear(getCellValue(Constants.COL_YEAR));
                    de.setVersion(getCellValue(Constants.COL_VER));
                    de.setYear_ret(getCellValue(Constants.COL_YR_RET));
                    de.setVer_ret(getCellValue(Constants.COL_VER_RET));
                    de.setColumn(getCellValue(Constants.COL_COL));
                    
                    dataElements.put(itemNum, de);
	            }
	        }
	    }
	}
	
	/**
     * Reads the values from the spreadsheet and puts them into the Objects
     * @param elementSheet
     * @return
     */
    private static void getDescriptions(Sheet elementSheet)  {
        
        for(Map.Entry<String, RowValue> entry : dataElements.entrySet())   {
            RowValue rowValue = entry.getValue();
            String code = rowValue.getCode();
            int rowNum = findCodeRow(elementSheet, code);
            
            row = elementSheet.getRow(rowNum);
            if(row != null) {
                rowValue.setDescription(getCellValue(Constants.COL_DESC));
                dataElements.put(code, rowValue);
            }
        }
    }
    
    /**
     * Reads the valueSets from the spreadsheet and puts them into the Objects
     * @param elementSheet
     * @return
     */
    private static void getValueSets(Sheet elementSheet)  {
        
        for(Map.Entry<String, RowValue> entry : dataElements.entrySet())   {
            RowValue rowValue = entry.getValue();
            String termName = rowValue.getName();  
            
            String code = rowValue.getCode();
            int rowNum = findNameRow(elementSheet, termName);
            
            row = elementSheet.getRow(rowNum);
            if(row != null) {
                String name = null;
                ArrayList<ValueSet> valueSets = new ArrayList<ValueSet>();
                while( (name==null || name.equals("")) && row!=null)  {
                    String cellValue = getCellValue(Constants.COL_SET_VALUE);
                    if(cellValue!=null && !( cellValue.equals("") || cellValue.equals(" ")) )  {
                        String vsCode = getCellValue(Constants.COL_SET_VALUE);
                        if(!vsCode.equals("Custom codes for historic use only")  && !vsCode.equals("..."))  {
                            ValueSet vs = new ValueSet(vsCode, getCellValue(Constants.COL_SET_DESC));
                            valueSets.add(vs);
                            rowValue.setValueSets(valueSets);
                            dataElements.put(code, rowValue);
                        }
                        rowNum++;
                        row = elementSheet.getRow(rowNum);
                        if(row!=null)  {
                            name = getCellValue(Constants.COL_SET_NAME);
                        }
                        else {
                            name = null;
                        }
                    }
                    else  {
                        name = "no value sets";
                        rowNum++;
                    }
                }
            }
        }
    }
    
	/**
	 * Useful for debugging
	 */
	private static void printDataElements()  {
	    for(Map.Entry<String, RowValue> entry : dataElements.entrySet()) {
		    entry.getValue().println();
		}
	}
	
	/**
	 * Gets the cell value.  If number, turn into String.  
	 * If not String or number, return blank String.
	 * @param colNum
	 * @return
	 */
	private static String getCellValue(int colNum)  {
	    String cellValue = "";
	    
	    Cell cell = row.getCell(colNum);
	    
	    if(cell == null)  {
            cellValue = "";
        }
	    else if(cell.getCellType() == 0)  {
	        cellValue = "" +cell.getNumericCellValue();
	        cellValue = cellValue.replace(".0", "");
	    }
	    else if(cell.getCellType() == 1)
	        cellValue = cell.getStringCellValue();
	    
	    if(cellValue==null)  {
	        cellValue = "";
	    }

	    cellValue = cellValue.replace("\"", "'");
	    return cellValue;
	}
	
	
	private static int findCodeRow(Sheet sheet, String cellContent) {
	    for (Row row : sheet) {
	        for (Cell cell : row) {
	            if (cell!=null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	                String cellValue = "" +cell.getNumericCellValue();
	                cellValue = cellValue.replace(".0", "");
	                if (cellValue.trim().equals(cellContent)) {
	                    return row.getRowNum();  
	                }
	            }
	        }
	    }               
	    return 0;
	}
	
	
	   private static int findNameRow(Sheet sheet, String cellContent) {
	        for (Row row : sheet) {
	            for (Cell cell : row) {
	                if (cell!=null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
	                    String cellValue = cell.getStringCellValue();
	                    if (cellValue.trim().equals(cellContent)) {
	                        return row.getRowNum();  
	                    }
	                }
	            }
	        }               
	        return 0;
	    }

}

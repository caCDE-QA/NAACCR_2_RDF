package org.mayo.edu.naacr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.mayo.edu.naacr.helper.Text;
import org.mayo.edu.naacr.types.RowValue;
import org.mayo.edu.naacr.types.ValueSet;

/**
 * The class constructs the RDF triples.   
 *
 */
public class RDF_Composer {

	StringBuffer rdfText;
	
	public RDF_Composer() {
		
	}

	public String getHeader()  {
	    return Text.HEADER + Text.NEW;
	}
	
	
	public String getRDF(HashMap<String, RowValue> dataElements)  {
		rdfText = new StringBuffer();

		for(Map.Entry<String, RowValue> entry : dataElements.entrySet())  {
		    setDataElementText(entry.getValue());
		    
		}

		return rdfText.toString();
	}
	
	
	private void setDataElementText(RowValue de)   {
		String deCode = de.getCode();
		String deName = de.getName();
		String deAltName = de.getAlt_name();
		String deLength = de.getLength();
		String deSource = de.getSource();
		String deYear = de.getYear();
		String deVersion = de.getVersion();
		String deYearRet = de.getYear_ret();
		String deVerRet = de.getVer_ret();
		String deCol = de.getColumn();
		String deDesc = de.getDescription();
		
		ArrayList<ValueSet> valueSets = de.getValueSets();

		rdfText.append(Text.URI_ELE +alterValue(deCode) +Text.END);
		rdfText.append(Text.CON +Text.DD);
		rdfText.append(Text.TYPE_ELE);
		rdfText.append(Text.LABEL +deName +Text.STRING);
		rdfText.append(Text.ALT_LABEL +deAltName +Text.STRING);
		rdfText.append(Text.DEF +deDesc + Text.STRING);
		rdfText.append(Text.DE_CDE_NOTE +de.getCode() +Text.STRING);
		rdfText.append(Text.DEL +de.getName() +Text.STRING);
		rdfText.append(Text.DED +deDesc +Text.STRING);
		rdfText.append(Text.YEAR +deYear +Text.STRING);
		rdfText.append(Text.VER +deVersion +Text.STRING);
		rdfText.append(Text.YR_RET +deYearRet +Text.STRING);
		rdfText.append(Text.VER_RET +deVerRet +Text.STRING);
		rdfText.append(Text.LEN +deLength +Text.STRING);
		rdfText.append(Text.SOURCE +deSource +Text.STRING);
		rdfText.append(Text.COL_NUM +deCol +Text.STRING);
		rdfText.append(Text.PER +Text.NEW +Text.NEW); 

		if(valueSets != null)  {
		    getValueSetText(de);
		}
		
	}
	
	
	private void getValueSetText(RowValue de)   {
	    String deCode = de.getCode();
	    String deName = de.getName();

	    ArrayList<ValueSet> valueSets = de.getValueSets();
	        
	    rdfText.append(Text.URI_ELE +alterValue(deCode) +Text.VD +Text.END);
	    rdfText.append(Text.CON +deCode +Text.SEMI);
	    rdfText.append(Text.TYPE_EVD);
	    rdfText.append(Text.LABEL +deName +Text.VS +Text.STRING);
	    rdfText.append(Text.DE_VD_NOTE +deCode +Text.VD +Text.STRING);
	    rdfText.append(Text.PER +Text.NEW +Text.NEW); 

	    for (ValueSet valueSet : valueSets) {
	        String vsValue = valueSet.getValue();
	        String vsDef = valueSet.getDescription();

            rdfText.append(Text.URI_ELE +alterValue(deCode) +Text.PER +alterValue(vsValue) +Text.END);
            rdfText.append(Text.IVD +deCode +Text.VD +Text.SEMI);
            rdfText.append(Text.TYPE_PERM);
            rdfText.append(Text.LABEL +vsValue +Text.STRING);
            rdfText.append(Text.MEAN +vsDef +Text.STRING);
            rdfText.append(Text.DE_VM_NOTE +deCode +Text.PER +vsValue +Text.STRING);
            rdfText.append(Text.PER +Text.NEW +Text.NEW); 
	    }
	}
	
	
	private String alterValue(String value)  {
	    return value.replaceAll(" ", "");
	}
	
}





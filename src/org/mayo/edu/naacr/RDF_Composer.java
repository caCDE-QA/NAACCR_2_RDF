package org.mayo.edu.naacr;

import java.util.HashMap;
import java.util.Map;

import org.mayo.edu.naacr.helper.Text;
import org.mayo.edu.naacr.types.RowValue;

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
		
		rdfText.append(Text.URI_ELE +deCode +Text.END);
		rdfText.append(Text.CON);
		rdfText.append(Text.TYPE_ELE);
		rdfText.append(Text.LABEL +deName +Text.STRING);
		rdfText.append(Text.ALT_LABEL +deAltName +Text.STRING);
		rdfText.append(Text.DEF +deDesc + Text.STRING);
		rdfText.append(Text.DE_NOTE +de.getCode() +Text.STRING);
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

	}
	
	
}





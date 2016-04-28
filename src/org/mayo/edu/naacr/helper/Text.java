package org.mayo.edu.naacr.helper;

/**
 * The Strings used to create the RDF triples   
 */
public class Text {

    public static String BASE = "<http://www.naaccr.org/naaccr";
    
	public static String HEADER = "# baseURI: " +BASE +"> . \n"
									+ "\n"
									+ "# c14n-version: 3 \n"
									+ "@prefix dc: <http://purl.org/dc/elements/1.1/> . \n"
									+ "@prefix dcterms: <http://purl.org/dc/terms/> . \n"
									+ "@prefix mms: <http://rdf.cdisc.org/mms#> . \n"
									+ "@prefix owl: <http://www.w3.org/2002/07/owl#> . \n"
									+ "@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> . \n"
									+ "@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> . \n"
									+ "@prefix skos: <http://www.w3.org/2004/02/skos/core#> . \n"
									+ "@prefix xsd: <http://www.w3.org/2001/XMLSchema#> . \n"
									+ "@prefix naaccr: " +BASE +"#> . \n"
									+ "\n";

	public static String URI_ELE = BASE +"#";
	
	public static String CON = "mms:context naaccr:";
	
	public static String IVD = "mms:inValueDomain naaccr:";
	
	public static String LABEL = "rdfs:label \"";
	
	public static String ALT_LABEL = "skos:altLabel \"";
	
	public static String TYPE_ELE = "rdf:type mms:DataElement ; \n";
	
	public static String TYPE_EVD = "rdf:type mms:EnumeratedValueDomain ; \n";
	
	public static String TYPE_PERM = "rdf:type mms:PermissibleValue ; \n";
	
	public static String TYPE_VD = "rdf:type mms:ValueDomain ; \n";
	
    public static String DEF = "skos:definition \"";
	
    public static String MEAN = "mms:meaningdescription \"";
    
	public static String STRING = "\"^^xsd:string ; \n";
	
    public static String DEL = "mms:dataElementLabel \"";

	public static String DED = "mms:dataElementDescription \"";
	
    public static String DE_CDE_NOTE = "skos:notation \"CDE_ID:";
    
    public static String DE_VD_NOTE = "skos:notation \"VD_ID:";
    
    public static String DE_VM_NOTE = "skos:notation \"VM_ID:";

    public static String YEAR = "naaccr:year \"";
	
    public static String VER = "naaccr:version \"";
    
    public static String YR_RET = "naaccr:year_retired \"";
    
    public static String VER_RET = "naaccr:version_retired \"";
    
    public static String LEN = "naaccr:length \"";
    
    public static String SOURCE = "naaccr:source \"";
    
    public static String COL_NUM = "naaccr:columnNum \"";
    
    public static String DD = "DataDictionary ; \n";
    
    public static String VD = "_vd";
    
    public static String VS = " Value Set";
    
	public static String PER = ".";
	
	public static String HASH = "# \n";
	
	public static String END = "> \n";
	
	public static String NEW = "\n";
	
	public static String SEMI = "; \n";
	
	public static String COL = ": ";

	
}

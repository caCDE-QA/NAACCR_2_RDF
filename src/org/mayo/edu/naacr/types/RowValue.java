package org.mayo.edu.naacr.types;

import java.util.ArrayList;

/**
 * The values for each column within a row.    
 */
public class RowValue {

    String code;
    String name;
    String alt_name;
    String length;
    String source;
    String year;
    String version;
    String year_ret;
    String ver_ret;
    String column;
    String description;
    ArrayList<ValueSet> valueSets;
      

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public String getAlt_name() {
        return alt_name;
    }
    public String getLength() {
        return length;
    }
    public String getSource() {
        return source;
    }
    public String getYear() {
        return year;
    }
    public String getVersion() {
        return version;
    }
    public String getYear_ret() {
        return year_ret;
    }
    public String getVer_ret() {
        return ver_ret;
    }
    public String getColumn() {
        return column;
    }
    public String getDescription() {
        return description;
    }
    public ArrayList<ValueSet> getValueSets() {
        return valueSets;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAlt_name(String alt_name) {
        this.alt_name = alt_name;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public void setYear_ret(String year_ret) {
        this.year_ret = year_ret;
    }
    public void setVer_ret(String ver_ret) {
        this.ver_ret = ver_ret;
    }
    public void setColumn(String column) {
        this.column = column;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setValueSets(ArrayList<ValueSet> valueSets) {
        this.valueSets = valueSets;
    }
    
    public void println()  {
        System.out.print( "\t" +code );
        System.out.print( "\t" +name );
        System.out.print( "\t" +alt_name );
        System.out.print( "\t" +length );
        System.out.print( "\t" +source );
        System.out.print( "\t" +year );
        System.out.print( "\t" +version );
        System.out.print( "\t" +year_ret );
        System.out.print( "\t" +ver_ret );
        System.out.print( "\t" +column );
        System.out.print( "\t" +description );
        System.out.print( "\n" );
    }
    

}

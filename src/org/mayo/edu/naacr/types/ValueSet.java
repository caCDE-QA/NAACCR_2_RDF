package org.mayo.edu.naacr.types;

/**
 * The value set code and value
 */
public class ValueSet {

    String value;
    String description;
    
    public ValueSet(String value, String description) {
        super();
        this.value = value;
        this.description = description;
    }
    
    public String getValue() {
        return value;
    }
    public String getDescription() {
        return description;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}

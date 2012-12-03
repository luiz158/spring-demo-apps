package com.sivalabs.springdemo.entities;

/**
 * @author Siva
 *
 */
public enum Gender
{
	MALE("MALE"), FEMALE("FEMALE");
	
	private String value;

	Gender(String value) { this.value = value; }

    public String getValue() 
    { 
    	/*Gender[] values = Gender.values();
    	for (Gender gender : values)
		{
			if(this.value == gender.value){
				return this.value;
			}
		}
    	return null;*/ 
    	return this.value;
    }
    
    
}

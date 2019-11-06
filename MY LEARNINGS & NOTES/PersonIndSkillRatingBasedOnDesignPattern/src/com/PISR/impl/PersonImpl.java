package com.PISR.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.PISR.Util.Utility;
import com.PISR.interfacce.PersonInterfacce;
import com.PISR.model.Person;

public class PersonImpl implements PersonInterfacce {

	
	

	
	public void getNameById(String Id) throws IOException{
	//	System.out.println("Inside getNameByID()");
	List<String> listOfReturnedLines= Utility.getContentOfInputFileFromFilePath("C:\\users\\neha\\Desktop\\PersonSkillSample.txt");
		
	System.out.println("able to get all the lines by util method"+listOfReturnedLines);
	
	
	for(int i=0;i<listOfReturnedLines.size();i++){
		String line=listOfReturnedLines.get(i);
        String[] splitedLine=line.split(",");
        String name=splitedLine[0];
        String adharId=splitedLine[1];
		Person person =new Person();
		person.setName(name);
		person.setAdharId(adharId);
		
		System.out.println(person.getName());
		
	}
	
	
		} // Ends getNameById here.
	
	
	
	public void getPhoneNoById(String Id) throws IOException{
		
		List<String> listOfReturnedLines= Utility.getContentOfInputFileFromFilePath("C:\\users\\neha\\Desktop\\PersonSkillSample.txt");
		
	     for(int i=0;i<listOfReturnedLines.size();i++){
			String line=listOfReturnedLines.get(i);
	        String[] splitedLine=line.split(",");
	       String restOfTheLine=splitedLine[2];
	        String[] restOfTheLineSplittedFurther=restOfTheLine.split(";");
	        String phoneNo=restOfTheLineSplittedFurther[0];
			Person person =new Person();
			person.setPhoneNo(phoneNo);
			
			System.out.println(person.getPhoneNo());	
	}
	}

}


package com.PISR.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.PISR.Util.Utility;
import com.PISR.interfacce.IndustoryTypeSkillRatingInterfacce;
import com.PISR.model.IndustoryTypeSkillsRating;
import com.PISR.model.Person;


public class IndustorySkillRatingImpl implements IndustoryTypeSkillRatingInterfacce {

	
	public void getNameIdPhoneBySkills(List<String> listOfLines) throws IOException{
	/*	List<String> listOfReturnedLines= Utility.getContentOfInputFileFromFilePath("C:\\users\\neha\\Desktop\\PersonSkillSample.txt");
		
	     for(int i=0;i<listOfReturnedLines.size();i++){
			String line=listOfReturnedLines.get(i);
	        String[] splitedLine=line.split(",");
	   
	        
	        String name=splitedLine[0];
	        String adharId=splitedLine[1];
			Person person =new Person();
			System.out.println("byskills "+person.getName());
			person.setAdharId(adharId);
			*/
		
	}
	

	public void getNameIdPhoneBySkills(String skill) throws IOException {
		// TODO Auto-generated method stub
		
		
		List<String> listOfReturnedLines= Utility.getContentOfInputFileFromFilePath("C:\\users\\neha\\Desktop\\PersonSkillSample.txt");
		
		
	List<Person>  listOfPerson=Utility.getListOfPerson(listOfReturnedLines);
	Person person ;
	//System.out.println("listOfPerson "+listOfPerson);
	 
	for (int i = 0; i < listOfPerson.size(); i++) {
	
		 person =listOfPerson.get(i);
	//  System.out.println("person "+person);
   
	// List<IndustoryTypeSkillsRating> listofIndskillsRating= listOfIndustoryTypeSkillsRatingPerPerson.get(i);

	 
	//System.out.println("getname 99 "+person.getName());
		System.out.println("Name:-"+person.getName());
		System.out.println("Adhar Id:-"+person.getAdharId());
		System.out.println("Phone no:-"+person.getPhoneNo());
	 
		//System.out.println("jj"+ person.getListOfIndustoryTypeSkillsRatingPerPerson());
	
		
		//System.out.println("$%$ "+listOfIndSkillRate.size());
			 
		/* for (int j = 0; j < listOfIndSkillRate.size(); j++) { 
			// IndustoryTypeSkillsRating industoryTypeSkillsRating=person.this.getListOfIndustoryTypeSkillsRatingPerPerson.get(j);	
		// System.out.println("i m here");
			 
		}*/
		 
		   
		// System.out.println("*************************************");
			 
		List<IndustoryTypeSkillsRating> listOfIndSkillRate =person.getListOfIndustoryTypeSkillsRatingPerPerson();
			System.out.println(listOfIndSkillRate.get(i).getSkills().length());
			
			System.out.println(listOfIndSkillRate.size());
			
			for (int j = 0; j < listOfIndSkillRate.size(); j++) {
				IndustoryTypeSkillsRating industoryTypeSkillsRating	=listOfIndSkillRate.get(j);
				//System.out.println(listOfIndSkillRate.get(0));
				System.out.println(industoryTypeSkillsRating.getIndustoryType());
			System.out.println(industoryTypeSkillsRating.getSkills());
				System.out.println(industoryTypeSkillsRating.getRating());
				
			}
			
			
		}
	
	
	
	}


	
}

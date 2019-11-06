package com.PISR.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.PISR.model.IndustoryTypeSkillsRating;
import com.PISR.model.Person;

public class Utility {

	public static List<String> getContentOfInputFileFromFilePath(String path) throws IOException{
		List<String> listOfLines= new ArrayList<String>();
				
	
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

			String line;
			while((line=bufferedReader.readLine())!=null){
				listOfLines.add(line);
			//System.out.println(line);
//			
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found");
			e.printStackTrace();
		}
		//System.out.println("returning list of lines  "+listOfLines);
		return listOfLines;
			}

		
	
	public static List<Person> getListOfPerson(List<String> listOfLines) throws IOException{
	
	List<IndustoryTypeSkillsRating> listofIndskillsRating= new LinkedList<IndustoryTypeSkillsRating>();
	List<Person> listOfPerson =new ArrayList<Person>();
	
	for(int i=0;i<listOfLines.size();i++){
		Person person=new Person();
		String line=listOfLines.get(i);
		String[] splittedLineByComma=line.split(";");
		String nameIdPhone=splittedLineByComma[0];
		String[] nameIdPhoneSplited=nameIdPhone.split(",");
		String name=nameIdPhoneSplited[0];
		String id=nameIdPhoneSplited[1];
		String phoneNo=nameIdPhoneSplited[2];
		
		person.setName(name);
		person.setAdharId(id);
		person.setPhoneNo(phoneNo);
		
		//System.out.println("adhar id is "+person.getAdharId());
		
	
		
		
		 
		
		
		
		String[] splittedLineByHash=line.split("#");
		String[] splittedLineByHashFurther=splittedLineByHash[0].split(";");
		String industoryType=splittedLineByHashFurther[1];
	   // System.out.println("industoryType is "+industoryType);
		 
	//  System.out.println("splittedLineByHash[1]  "+ splittedLineByHash[1]);
	  String splittedLineByHashOne=splittedLineByHash[1];
	 String[] arrayOfSkillsRatings= splittedLineByHashOne.split("-");
	 
	System.out.println(" arrayOfSkillsRatings 77 "+arrayOfSkillsRatings[0]);
	
	System.out.println("arrayOfSkillsRatings.length "+arrayOfSkillsRatings.length);
	
	             for (int j=0; j<arrayOfSkillsRatings.length; j++) {
	            	 IndustoryTypeSkillsRating industoryTypeSkillsRating=new IndustoryTypeSkillsRating();
	          	   industoryTypeSkillsRating.setIndustoryType(industoryType);
	            	String skillRatingArray= arrayOfSkillsRatings[j];
	            	//System.out.println(" skillRatingArray "+skillRatingArray);
	            	String[] skillRatingOneByOne=skillRatingArray.split("&");
	            	String skill=skillRatingOneByOne[0];
	            	String rating=skillRatingOneByOne[1];
	            	System.out.println("skill added ********* "+skill);
	            	System.out.println("rating added ^^^^^^^^^"+rating);
	            	 industoryTypeSkillsRating.setSkills(skill); 
	            	 industoryTypeSkillsRating.setRating(rating);
	            	
	            	 listofIndskillsRating.add(industoryTypeSkillsRating);
	            	// System.out.println("lets check list (((((( "+listofIndskillsRating.get(i).getSkills());
	            	
	             }
	             
	             for (int j = 0; j < listofIndskillsRating.size(); j++) {
	            	  System.out.println("listofIndskillsRating is till now "+listofIndskillsRating.get(j).getSkills());
	  	            
				}
	           	
	             
	          //  System.out.println("listofIndskillsRating is bom bom  "+listofIndskillsRating.get(0).getSkills());
	             person.setListOfIndustoryTypeSkillsRatingPerPerson(listofIndskillsRating);
            	 //System.out.println("person here "+person); 
	             
	           //  System.out.println("listofIndskillsRating is "+listofIndskillsRating);
	            
	            	//System.out.println("person "+person); 
	         //    System.out.println("lets check list (((((( "+listofIndskillsRating.get(i).getSkills());
	            	listOfPerson.add(person);
	            	//System.out.println("listOfPerson "+listOfPerson);
			}
	//System.out.println(" returning listOfPerson "+listOfPerson.size());
	return listOfPerson;
		}
	
	
	
	
	
}

package com.PISR.model;

import java.util.List;

public class Person {

	String Name;
	String AdharId;
	String PhoneNo;
	
	List<IndustoryTypeSkillsRating> listOfIndustoryTypeSkillsRatingPerPerson;

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the adharId
	 */
	public String getAdharId() {
		return AdharId;
	}

	/**
	 * @param adharId the adharId to set
	 */
	public void setAdharId(String adharId) {
		AdharId = adharId;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return PhoneNo;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}

	/**
	 * @return the listOfIndustoryTypeSkillsRatingPerPerson
	 */
	public List<IndustoryTypeSkillsRating> getListOfIndustoryTypeSkillsRatingPerPerson() {
		return listOfIndustoryTypeSkillsRatingPerPerson;
	}

	/**
	 * @param listOfIndustoryTypeSkillsRatingPerPerson the listOfIndustoryTypeSkillsRatingPerPerson to set
	 */
	public void setListOfIndustoryTypeSkillsRatingPerPerson(
			List<IndustoryTypeSkillsRating> listOfIndustoryTypeSkillsRatingPerPerson) {
		this.listOfIndustoryTypeSkillsRatingPerPerson = listOfIndustoryTypeSkillsRatingPerPerson;
	}
}

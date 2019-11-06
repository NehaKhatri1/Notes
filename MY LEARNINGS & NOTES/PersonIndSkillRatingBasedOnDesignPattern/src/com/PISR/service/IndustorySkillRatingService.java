package com.PISR.service;

import java.io.IOException;

import com.PISR.impl.IndustorySkillRatingImpl;
import com.PISR.interfacce.IndustoryTypeSkillRatingInterfacce;

public class IndustorySkillRatingService {
	
public static void main(String[] args){
	IndustoryTypeSkillRatingInterfacce industoryTypeSkillRatingInterfacce=new IndustorySkillRatingImpl();
	   try {
		industoryTypeSkillRatingInterfacce.getNameIdPhoneBySkills("Java");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
package com.PISR.service;

import java.io.IOException;

import com.PISR.impl.PersonImpl;

import com.PISR.interfacce.PersonInterfacce;
import com.PISR.model.Person;

public class personService { 

	
	public static void main(String[] args){
		PersonInterfacce personinterface;
		personinterface=new PersonImpl();
		try {
			personinterface.getNameById(null);
			personinterface.getPhoneNoById(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

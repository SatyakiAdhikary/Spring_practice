package org.jsp.customerfoodorderapp.controller;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class Test 
{
	public static void main(String[] args) 
	{
		EntityManager manager=Persistence.createEntityManagerFactory("development").createEntityManager();
		System.out.println(manager);
		
	}

}

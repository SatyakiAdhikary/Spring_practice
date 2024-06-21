package org.jsp.hibernatetemplatedemo.controller;

import java.io.Closeable;
import java.util.Scanner;

import org.jsp.hibernatetemplatedemo.dao.MerchantDao;
import org.jsp.hibernatetemplatedemo.dto.Merchant;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class MerchantController 
{
	public static void main(String[] args) 
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		MerchantDao merchantdao=context.getBean(MerchantDao.class);
		System.out.println("1.Save Merchant");
		System.out.println("2.Update Merchant");
		System.out.println("3.Find By Id");
		System.out.println("4.deleted By Id");
		System.out.println("5.Find All");
		Scanner sc=new Scanner(System.in);
		switch(sc.nextInt())
		{
		case 1:{
			
			System.out.println("Enter the name,phone,email,gstNumber,password");
			Merchant merchant=new Merchant();
			 merchant.setName(sc.next());
			 merchant.setPhone(sc.nextLong());
			 merchant.setEmail(sc.next());
			 merchant.setGstNumber(sc.next());
			
			 merchant.setPassword(sc.next());
			 merchant =merchantdao.saveMerchant(merchant);
			 System.out.println("merchant saved with Id:"+merchant.getId());
			 break;
			
			
		}
		
		case 2:
		{
		
			System.out.println("Enter the id, name,phone,email,gstNumber,password");
			Merchant merchant=new Merchant();
			merchant.setId(sc.nextInt());
			merchant.setName(sc.next());
			merchant.setPhone(sc.nextLong());
			merchant.setEmail(sc.next());
			merchant.setGstNumber(sc.next());
			merchant.setPassword(sc.next());
			merchant =merchantdao.updateMerchant(merchant);
			if(merchant!=null)
			{
				System.out.println("merchant updated with Id:"+merchant.getId()+"updated");
			}
			else
			{
				System.err.println("Invalid merchant id");
			}
			break;
			
		}
		
		case 3:
		{
			System.out.println("Enter the merchant Id To displaty details");
			Merchant m=merchantdao.findById(sc.nextInt());
			if(m!=null)
			{
				System.out.println(m);
			}
			else
			{
				System.err.println("Invalid merchant Id");
			}
			break;
		}
		
		case 4: {
			System.out.println("Enter the Merchant Id to delete the record");
			Merchant deleted = merchantdao.delete(sc.nextInt());
			if (deleted!=null)
			{
				System.out.println("Merchant with entered Id deleted");
			}
			else
			{
				System.err.println("Cannot delete merchant as id is Invalid");
			}
			break;
		}
		
		case 5:
		{
		  for(Merchant m:merchantdao.findAll())
		  {
			  System.out.println(m);
		  }
		  break;
		}
		
		default :
		{
			System.err.println("Invalid Choice");
		}
		}
		
		
		sc.close();
		((ClassPathXmlApplicationContext) context).close();
	
		
	}
}
																							


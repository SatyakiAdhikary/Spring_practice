package org.jsp.customerfoodorderapp.controller;

import java.io.Closeable;
import java.util.Scanner;

import org.jsp.customerfoodorderapp.CustomerOrderConfig;
import org.jsp.customerfoodorderapp.dao.CustomerDao;
import org.jsp.customerfoodorderapp.dto.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerController 
{
	public static void main(String[] args) 
	{
		ApplicationContext context= new AnnotationConfigApplicationContext(CustomerOrderConfig.class);
		CustomerDao customerdao=context.getBean("customerDao",CustomerDao.class);
		System.out.println("1.Save Customer");
		System.out.println("2.Update Customer");
		System.out.println("3.Find By Id");
		System.out.println("4.Verify By Phone and password");
		System.out.println("5.verfiy By Email and password");
		System.out.println("6.Delete customer");
		Scanner sc=new Scanner(System.in);
		
		switch(sc.nextInt())
		{
		case 1:
		{
			System.out.println("Enter the name,phone,email,age,gender and password  to save Customer");
			Customer c= new Customer();
			c.setName(sc.next());
			c.setPhone(sc.nextLong());
			c.setEmail(sc.next());
			c.setAge(sc.nextDouble());
			c.setGender(sc.next());
			c.setPassword(sc.next());
			c=customerdao.saveCustomer(c);
			System.out.println("Customer Saved with id:"+c.getId());
			break;
			
		}
		
		
		case 2:
		{
			System.out.println("Enter the id,name,phone,email,age,gender and password  to save Customer");
			Customer c=new Customer();
			c.setId(sc.nextInt());
			c.setName(sc.next());
			c.setPhone(sc.nextLong());
			c.setEmail(sc.next());
			c.setAge(sc.nextDouble());
			c.setGender(sc.next());
			c.setPassword(sc.next());
			c=customerdao.updateCustomer(c);
			if(c!=null)
			{
				System.out.println("Customer with id:"+c.getId()+ "updated");
				System.out.println(c);
			}
			else
			{
				System.err.println("Invalid id");
			}
			break;
		}
		
		
		case 3:
		{
			System.out.println("Enter the id to display details");
			int id=sc.nextInt();
		     Customer c=customerdao.findById(id);
		     if(c!=null)
		     {
		    	 System.out.println("Customer found with id");
		    	 System.out.println(c);
		     }
		     else
		     {
		    	 System.err.println("Invalid id");
		     }
		     break;
		}
		
		
		
		case 4:
		{
			System.out.println("Enter the phone and password to verify customer");
			long phone=sc.nextLong();
			String password=sc.next();
			Customer c=customerdao.verify(phone, password);
			if(c!=null)
			{
				System.out.println("Customer verification successfull");
				System.out.println(c);
			}
			else
			{
				System.err.println("Invalid phone and password");
			}
			break;
		}
		
		case 5:
		{
			System.out.println("Enter the email and password to verify customer");
			String email=sc.next();
			String password=sc.next();
			Customer c=customerdao.verify(email, password);
			if(c!=null)
			{
				System.out.println("Customer verification successfull");
				System.out.println(c);
			}
			else
			{
				System.err.println("Invalid email and password");
			}
			break;
		}
		
		
		case 6:
		{
			System.out.println("Enter the id to delete Customer");
			int id=sc.nextInt();
			Customer c=customerdao.deleteCustomer(id);
			if(c!=null)
			{
				System.out.println("Customer deleted");
				System.out.println(c);
					
			}
			else
			{
				System.err.println("invalid id");
			}
			break;
		}
		
		
		default :
		{
			System.err.println("invalid choice");
		}
		}
		
		sc.close();
		{
		 ((AnnotationConfigApplicationContext) context).close();
		}
		
		
		
		
		
	}

}

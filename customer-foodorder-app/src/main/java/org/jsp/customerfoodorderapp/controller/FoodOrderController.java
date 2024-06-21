package org.jsp.customerfoodorderapp.controller;

import java.io.Closeable;
import java.util.List;
import java.util.Scanner;

import org.jsp.customerfoodorderapp.CustomerOrderConfig;
import org.jsp.customerfoodorderapp.dao.CustomerDao;
import org.jsp.customerfoodorderapp.dao.FoodOrderDao;
import org.jsp.customerfoodorderapp.dto.Customer;
import org.jsp.customerfoodorderapp.dto.FoodOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FoodOrderController 
{
	public static void main(String[] args) 
	{
		ApplicationContext context= new AnnotationConfigApplicationContext(CustomerOrderConfig.class);
		FoodOrderDao orderdao=context.getBean("order",FoodOrderDao.class);
		System.out.println("1.Place FoodOrder");
		System.out.println("2.Update Foodorder");
		System.out.println("3.Find FoodOrderBy Id");
		System.out.println("4.Find FoodOrdersBy customerId");
		System.out.println("5.Find FoodOrdersBy customer phone and password");
		System.out.println("6.Delete foodrder");
		Scanner sc=new Scanner(System.in);



		switch(sc.nextInt())
		{
		case 1:
		{
			System.out.println("Enter customer_id,Food_itme,cost and address to place order ");
			int customer_id=sc.nextInt();
			FoodOrder order=new FoodOrder();
			order.setFooditem(sc.next());
			order.setCost(sc.nextDouble());
			order.setAddress(sc.next());
			order=orderdao.saveOrder(order, customer_id);
			System.out.println("Foodorder saved with id:"+order.getId());
			break;
		}


		case 2:
		{
			System.out.println("Enter id,Food_itme,cost and address to place order ");
			FoodOrder order=new FoodOrder();
			order.setId(sc.nextInt());
			order.setFooditem(sc.next());
			order.setCost(sc.nextDouble());
			order.setAddress(sc.next());
			order=orderdao.modifyOrder(order);

			if(order!=null)
			{
				System.out.println("Order updated with id:"+order.getId()+"Updated");
				System.out.println(order);
			}
			else
			{
				System.err.println("Invalid id");
			}
			break;
		}


		case 3:
		{
			System.out.println("Enter the id to find Foodorder");
			int id=sc.nextInt();
			FoodOrder order=orderdao.findById(id);
			if(order!=null)
			{
				System.out.println("Id is found");
				System.out.println(order);
			}
			else
			{
				System.err.println("invalid id");
			}
			break;
		}


		case 4:
		{
			System.out.println("Enter rhe customer id to find foodOrder");
			int customer_id=sc.nextInt();
			List<FoodOrder> orders=orderdao.findByCustomerId(customer_id);
			if(orders.isEmpty())
			{
				System.err.println("Invalid Id");
			}
			else
			{
				for(FoodOrder f:orders)
				{
					System.out.println(f);
				}
			}
			break;
		}


		case 5:
		{
			System.out.println("Enter the Customer phone and password to find foodorders");
			long phone=sc.nextLong();
			String password=sc.next();
			List<FoodOrder> orders=orderdao.findByCustomerPhoneAndPassword(phone, password);
			if(orders.isEmpty())
			{
				System.err.println("Invalid phone and password");
			}
			else
			{
				for(FoodOrder f:orders)
				{
					System.out.println(f);
				}
			}
			break;
		}





		case 6:
		{
			System.out.println("Enter the customerid to delete Foodorder");
			int customer_id=sc.nextInt();
			FoodOrder order=orderdao.deleteFoodOrder(customer_id);
			if(order!=null)
			{
				System.out.println("Customer deleted");
				System.out.println(order);

			}
			else
			{
				System.err.println("invalid id");
			}
			break;
		}


		default :
		{
			System.err.println("Invalid choice");
		}
		}


		
		sc.close();
		{
			((AnnotationConfigApplicationContext) context).close();
			
		}



	}	


}	





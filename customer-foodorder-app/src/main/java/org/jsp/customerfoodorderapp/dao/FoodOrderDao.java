package org.jsp.customerfoodorderapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.customerfoodorderapp.dto.Customer;
import org.jsp.customerfoodorderapp.dto.FoodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value="order")
public class FoodOrderDao 
{
	@Autowired
	private EntityManager entityManager;
	
	
	public FoodOrder saveOrder(FoodOrder order,int customer_id)
	{
		EntityTransaction transaction=entityManager.getTransaction();
		Customer customer=entityManager.find(Customer.class, customer_id);
		
		customer.getFoodOrders().add(order);
		order.setCustomer(customer);
		entityManager.persist(order);
		transaction.begin();
		transaction.commit();
		return order;
		}
		
	

      public FoodOrder modifyOrder(FoodOrder order)
      {
    	  EntityTransaction transaction=entityManager.getTransaction();
    	  FoodOrder dborder=entityManager.find(FoodOrder.class, order.getId());
    	  if(dborder!=null)
    	  {
    		  dborder.setAddress(order.getAddress());
    		  dborder.setCost(order.getCost());
    		  dborder.setFooditem(order.getFooditem());
    		  transaction.begin();
    		  transaction.commit();
    		  return dborder;
    	  }
    	  else
    	  {
    		  return null;
    	  }
      }
      
      
      public FoodOrder findById(int id)
      {
    	  Query q=entityManager.createQuery("select o from FoodOrder o where o.id=?1");
    	  q.setParameter(1, id);
    	  return (FoodOrder) q.getSingleResult();
      }
      
      
      
      public List<FoodOrder> findByCustomerId(int customer_id)
      {
    	  Query q=entityManager.createQuery("select c.foodOrders from Customer c where c.id=?1" );
    	  q.setParameter(1, customer_id);
    	  return q.getResultList();
    	  
      }
      
      public List<FoodOrder> findByCustomerPhoneAndPassword(long phone,String password) 
      {
    	  Query q=entityManager.createQuery("select c.foodOrders from Customer c where c.phone=?1 and c.password=?2" );
    	  q.setParameter(1, phone);
    	  q.setParameter(2, password);
    	  
    	  return q.getResultList();
		
	}
      
      
      public FoodOrder deleteFoodOrder(int customer_id)
  	{
  		EntityTransaction transaction=entityManager.getTransaction();
  		FoodOrder  order=entityManager.find(FoodOrder.class, customer_id);
  		if(order!=null)
  		{
  			entityManager.remove(order);
  			transaction.begin();
  			transaction.commit();
  			return order;
  		}
  		else
  		{
  			return null;
  		}
  		
  	}
	
	
	
	

}


package org.jsp.customerfoodorderapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.customerfoodorderapp.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class CustomerDao 
{
	@Autowired
	private EntityManager entityManager;
	
	public  Customer saveCustomer(Customer customer)
	{
		EntityTransaction transaction=entityManager.getTransaction();
		entityManager.persist(customer);
		transaction.begin();
		transaction.commit();
		return customer;
	}
	
	
	public Customer  updateCustomer(Customer customer)
	{
		EntityTransaction transaction=entityManager.getTransaction();
		Customer dbcustomer=entityManager.find(Customer.class,customer.getId());
		if(dbcustomer!=null)
		{
			dbcustomer.setName(customer.getName());
			dbcustomer.setAge(customer.getAge());
			dbcustomer.setEmail(customer.getEmail());
			dbcustomer.setGender(customer.getGender());
			dbcustomer.setPhone(customer.getPhone());
			dbcustomer.setPassword(customer.getPassword());
			transaction.begin();
			transaction.commit();
			return dbcustomer;
			
			
		}
		else
		{
			return null;
		}
	}
	
	
	public Customer verify(long phone,String password)
	{
		Query query=entityManager.createQuery("select c from Customer c where c.phone=?1 and c.password=?2");
		query.setParameter(1, phone);
		query.setParameter(2, password);
		try
		{
			return (Customer) query.getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	
	
	public Customer verify(String email,String password)
	{
		Query query=entityManager.createQuery("select c from Customer c where c.email=?1 and c.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		try
		{
			return (Customer) query.getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	
	
	public Customer findById(int id)
	{
		return entityManager.find(Customer.class, id);
	}
	
	
	public Customer deleteCustomer(int id)
	{
		EntityTransaction transaction=entityManager.getTransaction();
		Customer customer=entityManager.find(Customer.class, id);
		if(customer!=null)
		{
			entityManager.remove(customer);
			transaction.begin();
			transaction.commit();
			return customer;
		}
		else
		{
			return null;
		}
		
	}
	

}

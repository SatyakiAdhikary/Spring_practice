package org.jsp.employeewebapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.employeewebapp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao 
{
	@Autowired
	private EntityManager entityManager;
	
	public Employee saveEmployee(Employee employee)
	{
		EntityTransaction transaction=entityManager.getTransaction();
		entityManager.persist(employee);
		transaction.begin();
		transaction.commit();
		return employee;
		
	}
	
	
	public Employee updateEmployee(Employee employee)
	{
		EntityTransaction transaction=entityManager.getTransaction();
		Employee dbemployee=entityManager.find(Employee.class, employee.getId());
		if(dbemployee!=null)
		{
			dbemployee.setName(employee.getName());
			dbemployee.setPhone(employee.getPhone());
			dbemployee.setEmail(employee.getEmail());
			dbemployee.setGender(employee.getGender());
			dbemployee.setDesg(employee.getDesg());
			dbemployee.setSalary(employee.getSalary());
			dbemployee.setPassword(employee.getPassword());
			transaction.begin();
			transaction.commit();
			return dbemployee;
			
		}
		else
		{
			return null;
		}
	}
	
	
	public Employee findById(int id)
	{
		Query q=entityManager.createQuery("select e from Employee e where e.id=?1");
		q.setParameter(1, id);
		return (Employee) q.getSingleResult();
	}
	
	
	public Employee verify(long phone,String password)
	{
		Query q=entityManager.createQuery("select e from Employee e where e.phone=?1 and e.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try
		{
		return (Employee) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	
	
	
	public Employee verify(String email,String password)
	{
		Query q=entityManager.createQuery("select e from Employee e where e.email=?1 and e.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try
		{
		return (Employee) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	
	public Employee verify(int id,String password)
	{
		Query q=entityManager.createQuery("select e from Employee e where e.id=?1 and e.password=?2");
		q.setParameter(1, id);
		q.setParameter(2, password);
		try
		{
		return (Employee) q.getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	
	
	public Employee deleteEmployee(int id)
	{
		EntityTransaction transaction=entityManager.getTransaction();
		Employee employee=entityManager.find(Employee.class, id);
		if(employee!=null)
		{
			entityManager.remove(employee);
			transaction.begin();
			transaction.commit();
			return employee;
		}
		else
		{
			return null;
		}
	}
	

}

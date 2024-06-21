package org.jsp.hibernatetemplatedemo.dao;

import java.util.List;

import org.jsp.hibernatetemplatedemo.dto.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MerchantDao 
{
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Transactional
	public Merchant saveMerchant(Merchant merchant)
	{
		hibernateTemplate.save(merchant);
		return merchant;
	}


	public Merchant findById(int id)
	{
		return hibernateTemplate.get(Merchant.class, id);
	}


	@Transactional 
	public Merchant updateMerchant(Merchant merchant)
	{
		Merchant dbmerchant=findById(merchant.getId());
		if(dbmerchant!=null)
		{
			dbmerchant.setEmail(merchant.getEmail());
			dbmerchant.setGstNumber(merchant.getGstNumber());
			dbmerchant.setName(merchant.getName());
			dbmerchant.setPhone(merchant.getPhone());
			dbmerchant.setPassword(merchant.getPassword());
			return dbmerchant;
		}
		else
		{
			return null;
		}
	}


	@Transactional
	public Merchant delete(int id) {
		Merchant dbMerchant = findById(id);
		if (dbMerchant != null) {
			hibernateTemplate.delete(dbMerchant);
			return dbMerchant;
		}
		else
		{
			return null;
		}
	}

	public List<Merchant> findAll()
	{
		return hibernateTemplate.loadAll(Merchant.class);

	}


}

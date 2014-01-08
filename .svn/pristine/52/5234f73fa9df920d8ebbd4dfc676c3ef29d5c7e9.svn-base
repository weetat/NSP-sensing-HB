package com.ncs.nsp.sensing.manager;

import java.io.Serializable;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.ncs.nsp.sensing.entity.SBIUser;

public class SBIUserManager extends HibernateManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3265765539978533419L;

	public SBIUser querySBIUser(String user_id) {
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Transaction tx=null;
		SBIUser result = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query =session.createQuery("select tt from SBIUser tt where tt.user_id = :user_id");
			query.setParameter("user_id", user_id);
			result = (SBIUser) query.uniqueResult();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e; // or display error message
		}

		return result;

	}
}

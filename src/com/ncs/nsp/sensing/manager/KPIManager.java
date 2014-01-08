package com.ncs.nsp.sensing.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.ncs.nsp.sensing.entity.KPI;
import com.ncs.nsp.sensing.entity.KPIStat;

public class KPIManager extends HibernateManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<KPI> queryKPIFinancialList() {
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Transaction tx=null;
		List<KPI> result = new ArrayList<KPI>();
		try {
			tx=session.getTransaction();
			session.beginTransaction();
			Query query =session.createQuery("select distinct tt.kpi_fy from KPI tt order by tt.kpi_fy asc");
			List<String>tmpList=query.list();
			for(String tmpfy:tmpList){
				KPI kpi = new KPI();
				kpi.setKpi_fy(tmpfy);
				result.add(kpi);
			}
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e; // or display error message
		}

		return result;

	}
	
	public List<KPI> queryCommonKPIFinancialList() {
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Transaction tx=null;
		List<KPI> result = new ArrayList<KPI>();
		try {
			tx = session.getTransaction();
			tx.begin();

			Query query = session.createQuery("select distinct tt.kpi_desc from KPI tt order by tt.kpi_desc asc");
			List<String>tmpList=query.list();
			for(String tmpdesc:tmpList){
				KPI kpi = new KPI();
				kpi.setKpi_desc(tmpdesc);
				result.add(kpi);
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e; // or display error message
		}

		return result;

	}
	
	public KPI getKPI(String kpi_id) throws Exception{

		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Transaction tx=null;
		KPI kpi =null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("select tt from KPI tt where tt.kpi_id= :kpi_id");
			query.setParameter("kpi_id", kpi_id);
			kpi= (KPI) query.uniqueResult();
			tx.commit();
		}catch (Exception e) {
			if (tx != null && tx.isActive())
			tx.rollback();
			e.printStackTrace();
			throw e; 
			
		}
		return kpi;
	}
	
	public List<KPI> getKPIByKpiFy(String kpi_fy) throws Exception{
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Transaction tx=null;
		List<KPI>kpilist =null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("select tt from KPI tt where tt.kpi_fy= :kpi_fy");
			query.setParameter("kpi_fy", kpi_fy);
			kpilist= query.list();
			tx.commit();
		}catch (Exception e) {
			if (tx != null && tx.isActive())
			tx.rollback();
			e.printStackTrace();
			throw e; 
			
		}
		return kpilist;
	}
	
	public List<KPIStat> getKPIStatByKpiId(String kpi_id) throws Exception{
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Transaction tx=null;
		List<KPIStat>kpistat =null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("select tt from KPIStat tt where tt.kpi_id= :kpi_id order by tt.id asc");
			query.setParameter("kpi_id", kpi_id);
			kpistat= query.list(); 
			tx.commit();
		}catch (Exception e) {
			if (tx != null && tx.isActive())
			tx.rollback();
			e.printStackTrace();
			throw e; 
		}
		return kpistat;
	}
	
	
	public int getMaxKPIStatByKpiId(String kpi_id) throws Exception{
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Transaction tx=null;
		int max_kpistat =-1;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("select MAX(tt.kpi_mth) from KPIStat tt where tt.kpi_id= :kpi_id");
			query.setParameter("kpi_id", kpi_id);
			if(query.uniqueResult()!=null)
				max_kpistat= ((Integer)query.uniqueResult()).intValue(); 
			else
				max_kpistat= 4;
			tx.commit();
		}catch (Exception e) {
			if (tx != null && tx.isActive())
			tx.rollback();
			e.printStackTrace();
			throw e; 
			
		}
		return max_kpistat;
	}
	
	public KPIStat getKPIStatById(long id) throws Exception{

		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Transaction tx=null;
		KPIStat kpistat =null;
		try {
			//tx = session.getTransaction();
			//tx.begin();
			Query query = session.createQuery("select tt from KPIStat tt where tt.id= :id");
			query.setParameter("id", id);
			kpistat= (KPIStat) query.uniqueResult();
			//tx.commit();
		}catch (Exception e) {
			if (tx != null && tx.isActive())
			tx.rollback();
			e.printStackTrace();
			throw e; 
			
		}
		return kpistat;
	}
	
	
	public void updateKPIStatByKPIStat(KPIStat kpistat) throws Exception{

		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Transaction tx=null;
		KPIStat newKPIStat =null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("select tt from KPIStat tt where tt.id= :id");
			query.setParameter("id", kpistat.getId());
			newKPIStat= (KPIStat) query.uniqueResult();
			newKPIStat.setKpi_stat(kpistat.getKpi_stat());
		    newKPIStat.setKpi_date_modified(new Date());
			tx.commit();
		}catch (Exception e) {
			if (tx != null && tx.isActive())
			tx.rollback();
			e.printStackTrace();
			throw e; 
			
		}
		
	}
	
	public KPIStat getKPIStatByKpiIdAndMthAndYear(String kpi_id,int kpi_yr,int kpi_mth) throws Exception{
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Transaction tx=null;
		KPIStat kpistat =null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("select tt from KPIStat tt where tt.kpi_id= :kpi_id and tt.kpi_yr= :kpi_yr and tt.kpi_mth= :kpi_mth");
			query.setParameter("kpi_id", kpi_id);
			query.setParameter("kpi_yr", kpi_yr);
			query.setParameter("kpi_mth", kpi_mth);
			kpistat= (KPIStat) query.uniqueResult();
			tx.commit();
		}catch (RuntimeException e) {
			if (tx != null && tx.isActive())
			tx.rollback();
			e.printStackTrace();
			throw e; 
			
		}
		return kpistat;
	}
	
	public void updateKPIStat(List<KPIStat>newKpiStat) throws Exception{

		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Transaction tx=null;
		tx = session.getTransaction();
		tx.begin();			
		
		try {			
		 for(KPIStat kpistat:newKpiStat){
			//updateKPIStatByKPIStat(kpistat);
			KPIStat newKPIStat = getKPIStatById(kpistat.getId());
		    newKPIStat.setKpi_stat(kpistat.getKpi_stat());
		    newKPIStat.setKpi_date_modified(new Date());
		    session.merge(newKPIStat);	
		  }
		tx.commit();
			  
		 }catch (RuntimeException e) {
			//if (tx != null && tx.isActive())
			//tx.rollback();
			//e.printStackTrace();
			throw e; 
			
		}
	}
	
	
	public void createKPIStat (KPIStat kpi_stat) throws Exception{
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Transaction tx=null;

		try {
			tx = session.getTransaction();
			tx.begin();
			session.persist(kpi_stat); // insertion
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}
	

}

package com.ncs.nsp.sensing.action.kpi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.ncs.nsp.sensing.action.CommonAction;
import com.ncs.nsp.sensing.entity.KPI;
import com.ncs.nsp.sensing.manager.KPIManager;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class KPIActionView extends CommonAction implements Preparable,ServletRequestAware{

	protected List <KPI> financialYearList;
	protected List <String> financialYearHdr;
	protected List<String> calendarList;
	protected String financialYear;
	
	
	public List <KPI> getFinancialYearList() {
		return financialYearList;
	}

	public void setFinancialYearList(List <KPI> financialYearList) {
		this.financialYearList = financialYearList;
	}
   
	
	public String execute() throws Exception {
		return SUCCESS;
	}

	public List<String> getFinancialYearHdr() {
		return financialYearHdr;
	}

	public void setFinancialYearHdr(List<String> financialYearHdr) {
		this.financialYearHdr = financialYearHdr;
	}

	public List<String> getCalendarList() {
		return calendarList;
	}

	public void setCalendarList(List<String> calendarList) {
		this.calendarList = calendarList;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	
	public void prepare() throws Exception {
		KPIManager kpiManager = new KPIManager();
		financialYearList=kpiManager.queryKPIFinancialList();
		setFinancialYear(this.getText("global.currentyear"));
	}
    
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

package com.ncs.nsp.sensing.action.kpi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.commons.logging.*;

//import sg.com.ncs.util.Logger;

import com.ncs.nsp.sensing.common.CommonCalendar;
import com.ncs.nsp.sensing.entity.KPI;
import com.ncs.nsp.sensing.entity.KPIStat;
import com.ncs.nsp.sensing.manager.KPIManager;

@SuppressWarnings("serial")
public class KPIAction  extends KPIActionView implements ServletRequestAware{

	protected List<KPI> kpiList;
	protected List<KPIStat> kpistatList;
	protected HashMap<String,List<KPIStat>> kpistatMap=new HashMap<String,List<KPIStat>>();
	private Log logger = LogFactory.getLog(KPIAction.class);

	
	public List<KPI> getKpiList() {
		return kpiList;
	}

	public void setKpiList(List<KPI> kpiList) {
		this.kpiList = kpiList;
	}

	
	public List<KPIStat> getKpistatList() {
		return kpistatList;
	}

	public void setKpistatList(List<KPIStat> kpistatList) {
		this.kpistatList = kpistatList;
	}

	public HashMap<String, List<KPIStat>> getKpistatMap() {
		return kpistatMap;
	}

	public void setKpistatMap(HashMap<String, List<KPIStat>> kpistatMap) {
		this.kpistatMap = kpistatMap;
	}
	
	
	public void prepare() throws Exception {
		
		logger.info("---- prepare KPIAction start ---");
		HttpServletRequest request = ServletActionContext.getRequest();
		KPIManager kpiManager = new KPIManager();
		financialYearList=kpiManager.queryKPIFinancialList();
		List<String>tmplist=new ArrayList<String>();
		for(KPI kpi:financialYearList){
			String fy=this.getText("global."+kpi.getKpi_fy()+".val");
			if(fy!=null){
				String [] splitstr=fy.split("/");
				tmplist=Arrays.asList(splitstr);
				CommonCalendar.calendarYear.put(kpi.getKpi_fy(), tmplist); 
			}
		}
		
		logger.info("---> prep req :" + request.getParameter("financialYear"));
		setFinancialYear(request.getParameter("financialYear")!=null && 
						!request.getParameter("financialYear").equals("-1") ? 
								request.getParameter("financialYear"):
								this.getText("global.currentyear"));
		logger.info("---> prep req fn :" + financialYear);
		financialYearHdr=CommonCalendar.getFinancialYear(
				Integer.parseInt(CommonCalendar.calendarYear.get(financialYear.trim()).get(0)),
				Integer.parseInt(CommonCalendar.calendarYear.get(financialYear.trim()).get(1))
				);
		
		
		 try {
			kpiList = kpiManager.getKPIByKpiFy(financialYear.trim());
			logger.info("---> prep :" + kpiList.size());
			setKpiList(kpiList);
			if(kpiList!=null&&kpiList.size()>0){
				 for(KPI tmp:kpiList){
					logger.info("kpi id:" + tmp.getKpi_id()); 
					kpistatList=kpiManager.getKPIStatByKpiId(tmp.getKpi_id());
					List<KPIStat> b = new ArrayList<KPIStat>(kpistatList);
					Collections.copy(b,kpistatList);
					//Logger.info("---> prep kpilist id: " +tmp.getKpi_id() );
					//Logger.info("---> prep : " +kpistatList.size() );
					if(kpistatList!=null && (kpistatList.size()>0 && kpistatList.size()<12)){
						KPIStat tmpKpistat = (KPIStat) kpistatList.get(kpistatList.size()-1);
						String tmpKpiId  =tmpKpistat.getKpi_id();
						int tmpKpiMth =tmpKpistat.getKpi_mth();
						int tmpkpiYr = tmpKpistat.getKpi_yr();
						int minusSize= 12-kpistatList.size();
						int newKpiMth=0;
						for(int i=0;i<minusSize;i++){
							KPIStat kpistat = new KPIStat();
							kpistat.setKpi_id(tmpKpiId);
							if(tmpKpiMth<=12){
								kpistat.setKpi_mth(tmpKpiMth++);
								kpistat.setKpi_yr(tmpkpiYr);
								kpistat.setKpi_stat(new BigDecimal(0.00));
							}else{
								kpistat.setKpi_mth(newKpiMth++);
								kpistat.setKpi_yr(tmpkpiYr+1);
								kpistat.setKpi_stat(new BigDecimal(0.00));
							}
							b.add(kpistat);
						}
						kpistatMap.put(tmp.getKpi_id(), b);
					}else{
						if(kpistatList!=null && (kpistatList.size()>0)){
					    	kpistatMap.put(tmp.getKpi_id(), kpistatList);
					 	}else{
					 		int tmpKpiMth=0;
					 		if(request.getParameter("financialYear")!=null && 
									!request.getParameter("financialYear").equals("-1")){
					 			String [] splitstr=this.getText("global."+request.getParameter("financialYear")+".val").split("/");
								tmplist=Arrays.asList(splitstr);
									
					 		}else{
					 			String [] splitstr=this.getText("global."+this.getText("global.currentyear")+".val").split("/");
					 			tmplist=Arrays.asList(splitstr);
							}
					 		String tmpyr = tmplist.get(0);
					 		int tmpkpiYr= Integer.parseInt(tmpyr);
					 		for(int i=0;i<12;i++){
								KPIStat kpistat = new KPIStat();
								kpistat.setKpi_id(tmp.getKpi_id());
								if(tmpKpiMth<=12){
								 kpistat.setKpi_mth(tmpKpiMth++);
								 kpistat.setKpi_yr(tmpkpiYr);
								 kpistat.setKpi_stat(new BigDecimal(0.00));
								}else{
								 kpistat.setKpi_mth(tmpKpiMth++);
								 kpistat.setKpi_yr(tmpkpiYr+1);
								 kpistat.setKpi_stat(new BigDecimal(0.00));	
								}
								b.add(kpistat);
							}
					 		kpistatMap.put(tmp.getKpi_id(), b);
					 	}
					}
				} 
			  setKpistatList(kpistatList);
			  setKpistatMap(kpistatMap);
			}
		 } catch (Exception e) {
			 logger.error("Exception : ",e);
		 }
		 
		logger.info("---- prepare KPIAction end ---");
		
		
	}

	
	public String execute() throws Exception {
		if(!validateInput()){
			return INPUT;
		}
		return SUCCESS;
	}
	
	public boolean validateInput(){
		if(financialYear !=null && "-1".equals(financialYear)){
			 this.addFieldError("financialYear", getText("error.mandatory.fields.financialYear"));
			 return false;
		 }
		return true;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}

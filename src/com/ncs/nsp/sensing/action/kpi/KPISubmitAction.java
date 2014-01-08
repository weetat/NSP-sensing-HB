package com.ncs.nsp.sensing.action.kpi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

//import sg.com.ncs.util.Logger;

import com.ncs.nsp.sensing.common.CommonCalendar;
import com.ncs.nsp.sensing.entity.KPI;
import com.ncs.nsp.sensing.entity.KPIStat;
import com.ncs.nsp.sensing.manager.KPIManager;

@SuppressWarnings("serial")
public class KPISubmitAction extends KPIAction implements ServletRequestAware {
	
	private Log logger = LogFactory.getLog(KPISubmitAction.class);

	public String execute() throws Exception {
		
		logger.info("----  KPISubmitAction start ---");
		HttpServletRequest request = ServletActionContext.getRequest();
		KPIManager kpiManager = new KPIManager();
		List<KPI>tmpkpi=kpiManager.getKPIByKpiFy(financialYear.trim());
		if(!validateInput(tmpkpi,request)){
			return INPUT;
		}
		
		List<KPIStat> listTmp=new ArrayList<KPIStat>();
		calendarList=CommonCalendar.calendarYear.get(financialYear.trim());
		setFinancialYear(financialYear.trim());
		
		for(KPI kpi:tmpkpi){
			logger.info("---> kpi id :" + kpi.getKpi_id());
			String [] parameterValues =request.getParameterValues(kpi.getKpi_id()+"_kpi_stat");
			String [] hiddenValues =request.getParameterValues(kpi.getKpi_id()+"_hidden");
			int indexParam=0;
			int max_mth=kpiManager.getMaxKPIStatByKpiId(kpi.getKpi_id());
			int startMth=1;
			int endMth=max_mth+1;
			if(parameterValues!=null){
			for(String paramValue : parameterValues){
				//logger.info("---> paramValue :" + paramValue);
				//logger.info("----------------- updating KPI stat to database -----------------");
				//logger.info("---> stat val :" + paramValue);
				//logger.info("---> id val :" + (hiddenValues!=null? hiddenValues[indexParam]:"new id"));
				//logger.info("---> endMth val :" + endMth);
				if((!paramValue.equals("0")||paramValue.equals("0")) 
						 &&(hiddenValues!=null&&!hiddenValues[indexParam].equals("0"))
						){
						KPIStat kpistat = new KPIStat();
						kpistat.setId(Long.parseLong(hiddenValues[indexParam]));
						kpistat.setKpi_stat(new BigDecimal(paramValue));
						listTmp.add(kpistat);
					}else{
						KPIStat kpistat = new KPIStat();
						kpistat.setKpi_id(kpi.getKpi_id());
						if(endMth>12){
							KPIStat tmpkpistat = kpiManager.getKPIStatByKpiIdAndMthAndYear(kpi.getKpi_id(), Integer.parseInt(calendarList.get(1)), startMth);
							if(tmpkpistat==null){
							 kpistat.setKpi_mth(startMth);
							 kpistat.setKpi_yr(Integer.parseInt(calendarList.get(1)));
							 startMth++;
							}
						}else{
							KPIStat tmpkpistat = kpiManager.getKPIStatByKpiIdAndMthAndYear(kpi.getKpi_id(), Integer.parseInt(calendarList.get(1)), startMth);
							if(tmpkpistat==null){
							 kpistat.setKpi_mth(endMth);
							 kpistat.setKpi_yr(Integer.parseInt(calendarList.get(0)));
							}
						}
						if(!paramValue.equals("")){
							kpistat.setKpi_stat(new BigDecimal(paramValue));
						}else{
							kpistat.setKpi_stat(new BigDecimal(0.00));
						}
						kpistat.setKpi_date_modified(new Date());	
						kpiManager.createKPIStat(kpistat);
						//logger.info("----------------- insert KPI stat to database -----------------");
						endMth++;
					}
				indexParam++;
			 }
			}
			if(parameterValues!=null){
				kpiManager.updateKPIStat(listTmp);
				//logger.info("----------------- update KPI stat to database -----------------");
			}
		}
		logger.info("----  KPISubmitAction end ---");
		
		return SUCCESS;
	}

	public boolean validateInput(List<KPI>tmpkpi,HttpServletRequest request){
		
		for(KPI kpi:tmpkpi){
			String [] parameterValues =request.getParameterValues(kpi.getKpi_id()+"_kpi_stat");
			if (parameterValues == null ) {
				addFieldError("kpi_stat_err", getText("error.mandatory.fields.kpiStat"));	
				return false;
			}else {
				for(String paramValue : parameterValues){
				String pattern1 = "^[0-9]*\\.?[0-9]+$";
				Pattern pattern = Pattern.compile(pattern1);
				Matcher mat = pattern.matcher(paramValue);
				 if (!mat.find()) {
					setFinancialYear(financialYear.trim());
					addFieldError("kpi_stat_err",getText("error.kpistat.nonnumeric"));
					return false;
				  }
				}
			}
		}
		return true;
	} 
	
	
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}

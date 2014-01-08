package com.ncs.nsp.sensing.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class CommonCalendar {
	
	public static HashMap<String,List<String>> calendarYear=new HashMap<String,List<String>>();
	
	/**
	 * 
	 * @param startYear
	 * @param endYear
	 * @return List<String>
	 */
	public static List<String> getFinancialYear(int startYear,int endYear){
		
		 int[] earlyMonths = {Calendar.MAY,Calendar.JUNE,Calendar.JULY,
				 			  Calendar.AUGUST,Calendar.SEPTEMBER,Calendar.OCTOBER,
				 			  Calendar.NOVEMBER, Calendar.DECEMBER,Calendar.JANUARY,
				 			  Calendar.FEBRUARY,Calendar.MARCH,Calendar.APRIL}; // May to Apr
		 int month=-1;
		 List<String> result = new ArrayList<String>();
		 for (int i : earlyMonths) {
		     month = i;
		     Calendar cal = Calendar.getInstance();
		     cal.clear();
		     if(month>=Calendar.JANUARY &&month<=Calendar.APRIL){
		    	 cal.set(endYear,month,1); 	 
		    	 
		     }else{
		    	 cal.set(startYear,month,1);
		     }
		     DateFormat todayFormat= new SimpleDateFormat("MMM''yy");
			 result.add(todayFormat.format(cal.getTime().getTime())); 
		  }
		 
		 return result;
		 
	}
	
	/**
	 * 
	 * @param startYear
	 * @param endYear
	 * @return List<Integer>
	 */
	public static List<Integer> getFinancialMonth(int startYear,int endYear){
		
		 int[] earlyMonths = {Calendar.MAY,Calendar.JUNE,Calendar.JULY,
				 			  Calendar.AUGUST,Calendar.SEPTEMBER,Calendar.OCTOBER,
				 			  Calendar.NOVEMBER, Calendar.DECEMBER,Calendar.JANUARY,
				 			  Calendar.FEBRUARY,Calendar.MARCH,Calendar.APRIL}; // May to Apr
		 int month=-1;
		 List<Integer> result = new ArrayList<Integer>();
		 for (int i : earlyMonths) {
		     month = i;
		     result.add(month); 
		  }
		 
		 return result;
		 
	}

}

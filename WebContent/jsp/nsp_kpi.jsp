<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="text.pagetitle"/></title>
</head>
<body>
<s:form name="nspKpiForm" method="post" action="KPIAction" namespace="/kpi">
<table cellspacing="0" cellpadding="0" border="0" align="center" width="419" id="Registration">
<tbody>
	<tr>										
		<td valign="top" bgcolor="#F8F8F8"><strong> <s:text name="text.financialyear"/></strong></td>
		<td valign="top" bgcolor="#F8F8F8">
			<s:select headerKey="-1" headerValue="Select Financial Year" list="financialYearList"  key="financialYear" listKey="kpi_fy" listValue="kpi_fy" theme="simple" value="%{financialYear}" id="financialYear" onchange="callKPIaction();"/>
			<br>
			<s:fielderror ><s:param>financialYear</s:param></s:fielderror>
		</td>
	</tr>
</tbody>
</table>
</s:form>
<s:form name="nspKpiform" method="post" action="KPISubmitAction" namespace="/kpi" theme="simple">
<s:hidden name="financialYear"/>
<table>
<tr>
<th>KPI</th>
<s:iterator value="financialYearHdr">
   <th><s:property value="%{[0].toString()}" /></th>
</s:iterator>
</tr>
<s:if test="%{kpiList.size() gt 0}">
<s:iterator value="kpiList" id="kpi" >
<!-- start KPI list -->
<tr>
	<s:if test="%{kpistatList.size() gt 0}">
	<td><s:property value="#kpi.kpi_desc" /></td>
	<s:iterator value="kpistatMap" id="kpistatMap">
		<s:iterator value="%{#kpistatMap.value}" id="kpistatList" >
		<td>
			<s:hidden name="%{#kpistatList.kpi_id}_hidden" value="%{#kpistatList.id}" theme="simple"/>
			<s:textfield name="%{#kpistatList.kpi_id}_kpi_stat" cssStyle="width:80px" value="%{#kpistatList.kpi_stat}" theme="simple"/></td>
		</s:iterator>
	</s:iterator>
	</s:if>
	<s:else>
	<tr>
	<td><s:property value="#kpi.kpi_desc" /></td>
	<td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
	<td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
	<td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
	<td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
	<td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
	<td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
	<td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
	<td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
	<td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
	<td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
	<td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
	<td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
	</tr>
	</s:else>
</tr>
<!-- end KPI list -->
</s:iterator>
</s:if>
</table>
<p>&nbsp;</p>
<p align="center"><s:submit /></p>
</s:form>
<p>&nbsp;</p>
</body>
<script type="text/javascript">
  function callKPIaction(){
	 document.nspKpiForm.submit();
	 return true;
  }
</script>
</html>
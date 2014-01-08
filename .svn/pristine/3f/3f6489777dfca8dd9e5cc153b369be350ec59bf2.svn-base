<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,com.ncs.nsp.sensing.entity.KPIStat" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="text.pagetitle"/></title>
</head>
<body>
<h1><s:text name="text.pagetitle"/></h1>
<s:form name="nspKpiFormSearch" method="post" action="KPIAction" namespace="/kpi">
<table cellspacing="0" cellpadding="0" border="0" align="center" width="419">
<tbody>
	<tr>										
		<td valign="top" bgcolor="#F8F8F8"><strong> <s:text name="text.financialyear"/></strong></td>
		<td valign="top" bgcolor="#F8F8F8">
			<s:select headerKey="-1" headerValue="Select Financial Year" list="financialYearList"  key="financialYear" listKey="kpi_fy" listValue="kpi_fy" theme="simple" value="%{financialYear}" id="financialYear" onchange="callKPIaction();"/>
			<br>
			<s:fielderror cssStyle="color: #a7161f;"><s:param>financialYear</s:param></s:fielderror>
			<s:fielderror cssStyle="color: #a7161f;"><s:param>kpi_stat_err</s:param></s:fielderror>
		</td>
	</tr>
</tbody>
</table>
</s:form>
<s:form name="nspKpiform" method="post" action="KPISubmitAction" namespace="/kpi" theme="simple">
<s:hidden name="financialYear"/>
<table>
<tr>
<th><s:text name="text.subpagetitle"/></th>
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
	 <s:iterator value="%{#kpistatMap.value}" id="kpistatList">
	  <s:if test='%{#kpistatMap.key.contains(#kpi.kpi_id)}'>
	  <td>
			<s:hidden name="%{#kpistatList.kpi_id}_hidden" value="%{#kpistatList.id}" theme="simple"/>
			<s:textfield name="%{#kpistatList.kpi_id}_kpi_stat" cssStyle="width:80px" value="%{#kpistatList.kpi_stat}" theme="simple"/>
	  </td>
	  </s:if>
	 </s:iterator>
	</s:iterator>
	</s:if>
	<s:else>
	<tr>
	<td><s:property value="#kpi.kpi_desc" /></td>
	 <s:iterator value="kpistatMap" id="kpistatMap">
	 <s:iterator value="%{#kpistatMap.value}" id="kpistatList">
	  <s:if test='%{#kpistatMap.key.contains(#kpi.kpi_id)}'>
	  <td>
			<s:hidden name="%{#kpistatList.kpi_id}_hidden" value="%{#kpistatList.id}" theme="simple"/>
			<s:textfield name="%{#kpistatList.kpi_id}_kpi_stat" cssStyle="width:80px" value="%{#kpistatList.kpi_stat}" theme="simple"/>
	  </td>
	  </s:if>
	 </s:iterator>
	</s:iterator>
	<%-- <td><s:textfield name="%{#kpi.kpi_id}_kpi_stat" cssStyle="width:80px" value="0" theme="simple"/></td>
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
	</tr>--%>
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
	 document.nspKpiFormSearch.submit();
	 return true;
  }
</script>
</html>
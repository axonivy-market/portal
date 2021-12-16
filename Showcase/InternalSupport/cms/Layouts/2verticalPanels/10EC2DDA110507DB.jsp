<%@ page import="ch.ivyteam.ivy.page.engine.jsp.IvyJSP"%>
<jsp:useBean id="ivy" class="ch.ivyteam.ivy.page.engine.jsp.IvyJSP" scope="session"/> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
       "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="<%=ivy.style()%>" />
<title>ivyTeam at Work</title>
</head>

<body>
<table width="100%" border="0">
<tr>
<td width="100%" align ="middle">
<%=ivy.cms.co("/Project/Banner")%>
</td>
</tr>
</table>
<table width="100%" border="0">
<tr>
<td>
<h1>
<%=ivy.content("Caption", "String")%>
</h1>
<HR size="4">
</td>
</tr>
</table>
<table width="100%" height="70%" border="0">
<tr>
<td>
<jsp:include page='<%=ivy.panel("Panel1")%>' flush="true"/>
</td>
</tr>
<tr>
<td>
<jsp:include page='<%=ivy.panel("Panel2")%>' flush="true"/>
</td>
</tr>
</table>
<table width="100%" border="0">
<tr>
<td ALIGN="middle">
<FONT SIZE="-3">
<%=ivy.cms.co("/Project/Footer")%>
</FONT>
</td>
</tr>
</table>
</body>
</html>
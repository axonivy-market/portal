<%@ page import="ch.ivyteam.ivy.page.engine.jsp.IvyJSP"%>
<jsp:useBean id="ivy" class="ch.ivyteam.ivy.page.engine.jsp.IvyJSP" scope="session"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
       "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="expires" content="0">
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" type="text/css" href="<%=ivy.style()%>"/>
	<title>Axon.ivy</title>
</head>

<body>
	<div id="CompletePage">
		<div id="PageHeader">
			<div id="PageHeaderLogo"><%=ivy.cms.co("/Project/Banner")%></div>
			<div><%=ivy.cms.co("/Project/HeaderText")%></div>
		</div>
		
		<div id="PageCaption">
			<h2><%=ivy.content("Caption","String")%></h2>
		</div>
		
		<div id="PageContent">
			<jsp:include page='<%=ivy.panel("Panel1")%>' flush="true"/>
		</div>
		
		<div id="PageFooter">
			<%=ivy.cms.co("/Project/FooterText")%>
		</div>
	</div>
</body>
</html>

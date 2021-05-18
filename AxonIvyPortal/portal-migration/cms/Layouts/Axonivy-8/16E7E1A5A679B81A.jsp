<!DOCTYPE html>
<%@ page import="ch.ivyteam.ivy.page.engine.jsp.IvyJSP"%> 
<%@ page import="ch.ivyteam.ivy.Advisor"%>
<%@ page import="ch.ivyteam.util.Version"%>
<jsp:useBean id="ivy" class="ch.ivyteam.ivy.page.engine.jsp.IvyJSP" scope="session"/>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><%=Advisor.getAdvisor().getApplicationName()%> <%=Advisor.getAdvisor().getVersion().getVersionString(Version.DETAIL_PATCH,Version.FORM_SHORT)%></title>
  <link rel="shortcut icon" href="<%=ivy.cms.cr("/Images/Icon")%>" />
  <link rel="stylesheet" type="text/css" href="<%=ivy.style()%>"/>
</head>

<body>
<div class="navbar navbar-top" role="navigation">
  <div class="container" id="headerdiv">
	  <a class="ivy-logo" href="<%=ivy.html.applicationHomeRef()%>">
        <img src="<%=ivy.cms.cr("/Images/Logo")%>" alt="Axon.ivy">
      </a>
  </div>
</div>

<div class="container">
<!-- Content -->
<h3>
<%=ivy.content("Caption","String")%>&nbsp;
</h3>

<jsp:include page='<%=ivy.panel("Panel1")%>' flush="true"/>

<!-- Footer -->
<hr/>
<footer>
  <div id="layoutFooter">
    Powered by <%=Advisor.getAdvisor().getApplicationName()%> <%=Advisor.getAdvisor().getVersion()%>. Copyright 2001 - <%=(new ch.ivyteam.ivy.scripting.objects.Date()).getYear()%> <a href="https://www.axonivy.com/">AXON Ivy AG</a>
  </div>
</footer>
</div>
</body>
</html>
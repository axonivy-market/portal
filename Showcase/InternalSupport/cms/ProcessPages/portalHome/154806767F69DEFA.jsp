<% response.setHeader("Expires", "0");%>
<%@ page import="ch.ivyteam.ivy.page.engine.jsp.IvyJSP"%><jsp:useBean id="ivy" class="ch.ivyteam.ivy.page.engine.jsp.IvyJSP" scope="session"/>
<jsp:useBean id="userMenuBean" class="ch.ivy.addon.portal.generic.bean.UserMenuBean" scope="page"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
	<title></title>
</head>
<body onload="redirectToHomePage()">
	<script>
	function redirectToHomePage() {
		window.location = "${userMenuBean.getHomePageURL()}";
	}
	</script>
</body>
</html>
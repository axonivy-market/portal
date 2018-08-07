<%@ page import="ch.ivyteam.ivy.page.engine.jsp.IvyJSP"%>
<jsp:useBean id="ivy" class="ch.ivyteam.ivy.page.engine.jsp.IvyJSP" scope="session"/> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
       "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<script>
	//<![CDATA[
	//Because of each task has to point to its end page. We use this end page to redirect to another page we want by store the url in cookie.
	//the last position stored in cookie by actions before create task or open task.
	//this end page designed to get the last position in cookie and redirect to them. all other scenario will not redirect to any pages and a message 
	// will be logged for developer know the issue!.
	// COOKIE KEY : last-position
	// COOKIE VALUE: url of the page we want to redirect
	function setCookie(cname,cvalue) {	   
	    document.cookie = cname+"="+cvalue + ';path=/';
	}
	
	function getCookie(cname) {
	    var name = cname + "=";
	    var ca = document.cookie.split(';');
	    for(var i=0; i < ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0)==' ') c = c.substring(1);
	        if (c.indexOf(name) == 0) {
	            return c.substring(name.length, c.length);
	        }
	    }
	    return "";
	}
	
	function deleteCookie( name ) {
	  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	}	
	function redirect(){		
		var cookie = getCookie('last-position');
		if(cookie != ''){
			window.location.href = cookie;
		}else{
			//no end point url defined , use app portal home link as default
			window.location.href = '<%=ivy.html.startref("Start Processes/InternalSupportPortalHome/start.ivp")%>';
		}
	}
	window.onload = function() {
	  redirect();
	};
	//]]>
</script>
</body>
</html>
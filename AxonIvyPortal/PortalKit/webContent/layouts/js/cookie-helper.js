function setCookie(cname, cvalue) {
	document.cookie = cname + "=" + cvalue + ';path=/';
}
function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for ( var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1);
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}
function deleteCookie(name) {
	document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}
function redirectToLastPage() {
	var cookie = getCookie('lastWorkingApp');
	if (cookie != '') {
		window.location.href = cookie;
	}else{
		console.log('no end point url defined');
	}
}
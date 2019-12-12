[Ivy]
15C67FEA143420EE 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 PortalUrlCallbackProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdMethod f3 '' #zField
Ps0 @GridStep f4 '' #zField
Ps0 @UdProcessEnd f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @PushWFArc f5 '' #zField
>Proto Ps0 Ps0 PortalUrlCallbackProcess #zField
Ps0 f0 guid 15C67E57F20669EF #txt
Ps0 f0 method start(String) #txt
Ps0 f0 inParameterDecl '<String callbackUrl> param;' #txt
Ps0 f0 inParameterMapAction 'out.callbackUrl=param.callbackUrl;
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 211 51 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 109 64 211 64 #arcP
Ps0 f3 guid 15C67E8753E2C68C #txt
Ps0 f3 method redirect() #txt
Ps0 f3 inParameterDecl '<> param;' #txt
Ps0 f3 outParameterDecl '<> result;' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect()</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 147 26 26 -24 15 #rect
Ps0 f3 @|UdMethodIcon #fIcon
Ps0 f4 actionTable 'out=in;
' #txt
Ps0 f4 actionCode 'import org.apache.commons.lang3.StringUtils;
import javax.faces.context.ExternalContext;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import ch.ivyteam.ivy.request.OpenRedirectVulnerabilityUtil;
import java.net.URLDecoder;

HttpServletRequest request = null;
ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
if (context != null){
	request = context.getRequest() as HttpServletRequest;
}

String url = URLDecoder.decode(in.callbackUrl, "UTF-8");

if (request != null && StringUtils.isNotBlank(url) && OpenRedirectVulnerabilityUtil.isValid(url, request)){	
	context.redirect(url);
} else {
	PortalNavigator navigator = new PortalNavigator();
	navigator.navigateToPortalHome();
}' #txt
Ps0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check url and redirect</name>
    </language>
</elementInfo>
' #txt
Ps0 f4 160 138 128 44 -58 -8 #rect
Ps0 f4 @|StepIcon #fIcon
Ps0 f6 339 147 26 26 0 12 #rect
Ps0 f6 @|UdProcessEndIcon #fIcon
Ps0 f7 expr out #txt
Ps0 f7 288 160 339 160 #arcP
Ps0 f5 109 160 160 160 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalUrlCallback.PortalUrlCallbackData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f4 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect

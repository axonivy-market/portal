[Ivy]
1725F66D3F3CECB5 9.2.0 #module
>Proto >Proto Collection #zClass
Us0 UserGuideProcess Big #zClass
Us0 RD #cInfo
Us0 #process
Us0 @TextInP .type .type #zField
Us0 @TextInP .processKind .processKind #zField
Us0 @TextInP .xml .xml #zField
Us0 @TextInP .responsibility .responsibility #zField
Us0 @UdInit f0 '' #zField
Us0 @UdProcessEnd f1 '' #zField
Us0 @GridStep f3 '' #zField
Us0 @UdMethod f5 '' #zField
Us0 @GridStep f6 '' #zField
Us0 @UdProcessEnd f7 '' #zField
Us0 @PushWFArc f8 '' #zField
Us0 @PushWFArc f9 '' #zField
Us0 @UdMethod f10 '' #zField
Us0 @PushWFArc f11 '' #zField
Us0 @PushWFArc f4 '' #zField
Us0 @UdProcessEnd f12 '' #zField
Us0 @PushWFArc f13 '' #zField
>Proto Us0 Us0 UserGuideProcess #zField
Us0 f0 guid 1725F66D3FA76EAE #txt
Us0 f0 method start() #txt
Us0 f0 inParameterDecl '<> param;' #txt
Us0 f0 outParameterDecl '<> result;' #txt
Us0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Us0 f0 83 51 26 26 -15 15 #rect
Us0 f0 @|UdInitIcon #fIcon
Us0 f1 339 51 26 26 0 12 #rect
Us0 f1 @|UdProcessEndIcon #fIcon
Us0 f3 actionTable 'out=in;
' #txt
Us0 f3 actionCode 'import org.apache.commons.lang3.StringUtils;
import internaltest.UserForGuide;
import ch.ivy.addon.portalkit.bo.Guide;
import ch.ivyteam.ivy.security.IUser;

in.users.clear();
List<IUser> users = ivy.wf.getApplication().getSecurityContext().users().paged().page(1);
for (IUser user : users) {
	UserForGuide userForGuide = new UserForGuide();
	userForGuide.setName(user.getName());
	userForGuide.setGuidePropertyValue(user.getProperty("SHOW_GUIDE"));
	userForGuide.setIsGuideShown(StringUtils.isNotBlank(userForGuide.getGuidePropertyValue()) ? Boolean.valueOf(userForGuide.getGuidePropertyValue()) : true);
	in.users.add(userForGuide);
}' #txt
Us0 f3 security system #txt
Us0 f3 160 138 112 44 0 -8 #rect
Us0 f3 @|StepIcon #fIcon
Us0 f5 guid 1725F79C36B6435A #txt
Us0 f5 method changeGuidePropertyValue(internaltest.UserForGuide) #txt
Us0 f5 inParameterDecl '<internaltest.UserForGuide user> param;' #txt
Us0 f5 inParameterMapAction 'out.user=param.user;
' #txt
Us0 f5 outParameterDecl '<> result;' #txt
Us0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>changeGuidePropertyValue()</name>
    </language>
</elementInfo>
' #txt
Us0 f5 83 243 26 26 -59 17 #rect
Us0 f5 @|UdMethodIcon #fIcon
Us0 f6 actionTable 'out=in;
' #txt
Us0 f6 actionCode 'import ch.ivy.addon.portalkit.bean.GuideBean;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.bo.Guide;
import ch.ivyteam.ivy.security.IUser;
IUser user = ivy.wf.getSecurityContext().users().find(in.user.name);
if (user.equals(ivy.session.getSessionUser())) {
	GuideBean guideBean =	ManagedBeans.get("guideBean") as GuideBean;
	guideBean.setDontShowAgain(!in.user.isGuideShown);
} else {
	user.setProperty("SHOW_GUIDE", String.valueOf(in.user.isGuideShown));
}
' #txt
Us0 f6 security system #txt
Us0 f6 168 234 112 44 0 -8 #rect
Us0 f6 @|StepIcon #fIcon
Us0 f7 339 243 26 26 0 12 #rect
Us0 f7 @|UdProcessEndIcon #fIcon
Us0 f8 109 256 168 256 #arcP
Us0 f9 280 256 339 256 #arcP
Us0 f10 guid 1726E1633BCB044F #txt
Us0 f10 method load() #txt
Us0 f10 inParameterDecl '<> param;' #txt
Us0 f10 outParameterDecl '<> result;' #txt
Us0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>load()</name>
    </language>
</elementInfo>
' #txt
Us0 f10 83 147 26 26 -9 16 #rect
Us0 f10 @|UdMethodIcon #fIcon
Us0 f11 109 64 339 64 #arcP
Us0 f4 109 160 160 160 #arcP
Us0 f12 339 147 26 26 0 12 #rect
Us0 f12 @|UdProcessEndIcon #fIcon
Us0 f13 272 160 339 160 #arcP
>Proto Us0 .type ch.ivy.addon.portalkit.test.UserGuide.UserGuideData #txt
>Proto Us0 .processKind HTML_DIALOG #txt
>Proto Us0 -8 -8 16 16 16 26 #rect
>Proto Us0 '' #fIcon
Us0 f5 mainOut f8 tail #connect
Us0 f8 head f6 mainIn #connect
Us0 f6 mainOut f9 tail #connect
Us0 f9 head f7 mainIn #connect
Us0 f0 mainOut f11 tail #connect
Us0 f11 head f1 mainIn #connect
Us0 f10 mainOut f4 tail #connect
Us0 f4 head f3 mainIn #connect
Us0 f3 mainOut f13 tail #connect
Us0 f13 head f12 mainIn #connect

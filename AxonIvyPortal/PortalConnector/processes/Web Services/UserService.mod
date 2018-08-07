[Ivy]
[>Created: Wed Aug 23 09:54:43 ICT 2017]
138D76541AF4C4CF 3.20 #module
>Proto >Proto Collection #zClass
Ue0 UserSettingService Big #zClass
Ue0 WS #cInfo
Ue0 #process
Ue0 @TextInP .webServiceName .webServiceName #zField
Ue0 @TextInP .implementationClassName .implementationClassName #zField
Ue0 @TextInP .authenticationType .authenticationType #zField
Ue0 @TextInP .resExport .resExport #zField
Ue0 @TextInP .type .type #zField
Ue0 @TextInP .processKind .processKind #zField
Ue0 @AnnotationInP-0n ai ai #zField
Ue0 @TextInP .xml .xml #zField
Ue0 @TextInP .responsibility .responsibility #zField
Ue0 @StartWS ws0 '' #zField
Ue0 @StartWS f0 '' #zField
Ue0 @GridStep f2 '' #zField
Ue0 @PushWFArc f3 '' #zField
Ue0 @GridStep f8 '' #zField
Ue0 @PushWFArc f5 '' #zField
Ue0 @StartWS f7 '' #zField
Ue0 @StartWS f11 '' #zField
Ue0 @GridStep f14 '' #zField
Ue0 @PushWFArc f15 '' #zField
Ue0 @GridStep f16 '' #zField
Ue0 @PushWFArc f17 '' #zField
Ue0 @StartWS f19 '' #zField
Ue0 @GridStep f21 '' #zField
Ue0 @StartWS f22 '' #zField
Ue0 @GridStep f23 '' #zField
Ue0 @PushWFArc f24 '' #zField
Ue0 @PushWFArc f26 '' #zField
Ue0 @StartWS f28 '' #zField
Ue0 @GridStep f30 '' #zField
Ue0 @PushWFArc f31 '' #zField
Ue0 @GridStep f44 '' #zField
Ue0 @StartWS f45 '' #zField
Ue0 @PushWFArc f46 '' #zField
Ue0 @CallSub f59 '' #zField
Ue0 @Alternative f58 '' #zField
Ue0 @EndWS f35 '' #zField
Ue0 @PushWFArc f60 '' #zField
Ue0 @PushWFArc f61 '' #zField
Ue0 @PushWFArc f33 '' #zField
Ue0 @PushWFArc f4 '' #zField
Ue0 @PushWFArc f6 '' #zField
Ue0 @PushWFArc f10 '' #zField
Ue0 @PushWFArc f13 '' #zField
Ue0 @PushWFArc f25 '' #zField
Ue0 @PushWFArc f27 '' #zField
Ue0 @PushWFArc f32 '' #zField
Ue0 @StartWS f9 '' #zField
Ue0 @GridStep f1 '' #zField
Ue0 @PushWFArc f12 '' #zField
Ue0 @PushWFArc f18 '' #zField
>Proto Ue0 Ue0 UserSettingService #zField
Ue0 ws0 inParamDecl '<java.lang.String user,java.lang.String appName> param;' #txt
Ue0 ws0 inParamTable 'out.appName=param.appName;
out.user=param.user;
' #txt
Ue0 ws0 outParamDecl '<java.util.List<ch.ivy.ws.addon.WSException> errors,ch.ivy.ws.addon.types.IvyUserSetting userSetting> result;
' #txt
Ue0 ws0 outParamTable 'result.errors=in.errors;
result.userSetting=in.userSetting;
' #txt
Ue0 ws0 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 ws0 callSignature findUserSetting(String,String) #txt
Ue0 ws0 useUserDefinedException false #txt
Ue0 ws0 taskData '#
#Fri Feb 27 09:50:26 ICT 2015
TaskTriggered.PRI=2
' #txt
Ue0 ws0 caseData '#
#Fri Feb 27 09:50:26 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ue0 ws0 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 ws0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findUserSetting</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 ws0 @C|.responsibility Everybody #txt
Ue0 ws0 35 51 26 26 14 0 #rect
Ue0 ws0 @|StartWSIcon #fIcon
Ue0 f0 inParamDecl '<ch.ivy.ws.addon.types.IvyUserSetting userSetting,java.lang.String user,java.lang.String appName> param;' #txt
Ue0 f0 inParamTable 'out.appName=param.appName;
out.user=param.user;
out.userSetting=param.userSetting;
' #txt
Ue0 f0 outParamDecl '<java.util.List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Ue0 f0 outParamTable 'result.errors=in.errors;
' #txt
Ue0 f0 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f0 callSignature saveUserSetting(ch.ivy.ws.addon.types.IvyUserSetting,String,String) #txt
Ue0 f0 useUserDefinedException false #txt
Ue0 f0 taskData '#
#Fri Feb 27 09:33:53 ICT 2015
TaskTriggered.PRI=2
' #txt
Ue0 f0 caseData '#
#Fri Feb 27 09:33:53 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ue0 f0 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveUserSetting</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f0 @C|.responsibility Everybody #txt
Ue0 f0 187 51 26 26 14 0 #rect
Ue0 f0 @|StartWSIcon #fIcon
Ue0 f2 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f2 actionTable 'out=in;
' #txt
Ue0 f2 actionCode 'import ch.ivy.ws.addon.bo.UserSettingServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	UserSettingServiceResult ussResult = WsServiceFactory.getUserSettingService().findUserSetting(in.user, in.appName);
	in.userSetting = ussResult.getUserSetting();
	in.errors = ussResult.getErrors();

}catch(WSException e){
	in.errors.add(e);
}' #txt
Ue0 f2 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find user
settings</name>
        <nameStyle>10,7
8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f2 30 148 36 24 20 -2 #rect
Ue0 f2 @|StepIcon #fIcon
Ue0 f3 expr out #txt
Ue0 f3 48 77 48 148 #arcP
Ue0 f8 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f8 actionTable 'out=in;
' #txt
Ue0 f8 actionCode 'import ch.ivy.ws.addon.bo.UserSettingServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	WsServiceFactory.getUserSettingService().saveUserSetting(in.user,in.userSetting, in.appName); 
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ue0 f8 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save user
settings</name>
        <nameStyle>10,7
8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f8 182 148 36 24 20 -2 #rect
Ue0 f8 @|StepIcon #fIcon
Ue0 f5 expr out #txt
Ue0 f5 200 77 200 148 #arcP
Ue0 f7 inParamDecl '<java.util.List<java.lang.String> applications,java.lang.String user> param;' #txt
Ue0 f7 inParamTable 'out.applications=param.applications;
out.user=param.user;
' #txt
Ue0 f7 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,java.util.List<ch.ivy.ws.addon.types.IvyEmailSetting> settings> result;
' #txt
Ue0 f7 outParamTable 'result.errors=in.errors;
result.settings=in.emailSettings;
' #txt
Ue0 f7 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f7 callSignature getEmailSetttings(List<String>,String) #txt
Ue0 f7 useUserDefinedException false #txt
Ue0 f7 taskData TaskTriggered.PRI=2 #txt
Ue0 f7 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getEmailSetttings</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f7 @C|.responsibility Everybody #txt
Ue0 f7 355 51 26 26 14 0 #rect
Ue0 f7 @|StartWSIcon #fIcon
Ue0 f11 inParamDecl '<java.lang.String user,List<ch.ivy.ws.addon.types.IvyEmailSetting> settings> param;' #txt
Ue0 f11 inParamTable 'out.emailSettings=param.settings;
out.user=param.user;
' #txt
Ue0 f11 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors> result;' #txt
Ue0 f11 outParamTable 'result.errors=in.errors;
' #txt
Ue0 f11 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f11 callSignature setEMailSettings(String,List<ch.ivy.ws.addon.types.IvyEmailSetting>) #txt
Ue0 f11 useUserDefinedException false #txt
Ue0 f11 taskData '#
#Fri Feb 27 09:50:24 ICT 2015
TaskTriggered.PRI=2
' #txt
Ue0 f11 caseData '#
#Fri Feb 27 09:50:24 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ue0 f11 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setEMailSettings</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f11 @C|.responsibility Everybody #txt
Ue0 f11 515 51 26 26 14 0 #rect
Ue0 f11 @|StartWSIcon #fIcon
Ue0 f14 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f14 actionTable 'out=in;
' #txt
Ue0 f14 actionCode 'import ch.ivy.ws.addon.bo.UserSettingServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	UserSettingServiceResult ussResult = WsServiceFactory.getUserSettingService().getEMailSettings(in.applications,in.user);
	in.emailSettings = ussResult.getEmailSettings();
	in.errors = ussResult.getErrors();

}catch(WSException e){
	in.errors.add(e);
}' #txt
Ue0 f14 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get email
settings</name>
        <nameStyle>10,7
8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f14 350 148 36 24 20 -2 #rect
Ue0 f14 @|StepIcon #fIcon
Ue0 f15 expr out #txt
Ue0 f15 368 77 368 148 #arcP
Ue0 f16 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f16 actionTable 'out=in;
' #txt
Ue0 f16 actionCode 'import ch.ivy.ws.addon.bo.UserSettingServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	in.errors = WsServiceFactory.getUserSettingService().setEMailSettings(in.emailSettings,in.user);
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ue0 f16 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set email
settings</name>
        <nameStyle>10,7
8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f16 510 148 36 24 20 -2 #rect
Ue0 f16 @|StepIcon #fIcon
Ue0 f17 expr out #txt
Ue0 f17 528 77 528 148 #arcP
Ue0 f19 inParamDecl '<List<java.lang.String> applications,java.lang.String user> param;' #txt
Ue0 f19 inParamTable 'out.applications=param.applications;
out.user=param.user;
' #txt
Ue0 f19 outParamDecl '<java.util.List<ch.ivy.ws.addon.types.IvySubstitute> substitutes,List<ch.ivy.ws.addon.WSException> errors,java.util.List<ch.ivy.ws.addon.types.IvyUser> applicationUsers> result;
' #txt
Ue0 f19 outParamTable 'result.substitutes=in.substitutes;
result.errors=in.errors;
result.applicationUsers=in.applicationUsers;
' #txt
Ue0 f19 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f19 callSignature getSubstitutes(List<String>,String) #txt
Ue0 f19 useUserDefinedException false #txt
Ue0 f19 taskData '#
#Mon Mar 02 11:56:54 ICT 2015
TaskTriggered.PRI=2
' #txt
Ue0 f19 caseData '#
#Mon Mar 02 11:56:54 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ue0 f19 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getSubstitutes</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f19 @C|.responsibility Everybody #txt
Ue0 f19 667 51 26 26 14 0 #rect
Ue0 f19 @|StartWSIcon #fIcon
Ue0 f21 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f21 actionTable 'out=in;
' #txt
Ue0 f21 actionCode 'import ch.ivy.ws.addon.bo.SubstituteServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	SubstituteServiceResult result =	WsServiceFactory.getAbsenceService().setSubstitutes(in.user,in.substitutes);
	in.errors = result.errors;
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ue0 f21 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set
substitutes</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f21 798 148 36 24 20 -2 #rect
Ue0 f21 @|StepIcon #fIcon
Ue0 f22 inParamDecl '<java.lang.String username,java.util.List<ch.ivy.ws.addon.types.IvySubstitute> substitutes> param;' #txt
Ue0 f22 inParamTable 'out.substitutes=param.substitutes;
out.user=param.username;
' #txt
Ue0 f22 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Ue0 f22 outParamTable 'result.errors=in.errors;
' #txt
Ue0 f22 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f22 callSignature setSubstitutes(String,List<ch.ivy.ws.addon.types.IvySubstitute>) #txt
Ue0 f22 useUserDefinedException false #txt
Ue0 f22 taskData '#
#Mon Mar 02 11:58:40 ICT 2015
TaskTriggered.PRI=2
' #txt
Ue0 f22 caseData '#
#Mon Mar 02 11:58:40 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ue0 f22 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setSubstitutes</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f22 @C|.responsibility Everybody #txt
Ue0 f22 803 51 26 26 14 0 #rect
Ue0 f22 @|StartWSIcon #fIcon
Ue0 f23 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f23 actionTable 'out=in;
' #txt
Ue0 f23 actionCode 'import ch.ivy.ws.addon.bo.SubstituteServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	SubstituteServiceResult absResult = WsServiceFactory.getAbsenceService().getSubstitutes(in.user, in.applications);
	in.substitutes = absResult.getSubstitutes();
	in.applicationUsers = absResult.getApplicationUsers();
	in.errors = absResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ue0 f23 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get
substitutes</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f23 662 148 36 24 20 -2 #rect
Ue0 f23 @|StepIcon #fIcon
Ue0 f24 expr out #txt
Ue0 f24 680 77 680 148 #arcP
Ue0 f26 expr out #txt
Ue0 f26 816 77 816 148 #arcP
Ue0 f28 inParamDecl '<java.lang.Long serverId,List<java.lang.String> applications,java.lang.String user> param;' #txt
Ue0 f28 inParamTable 'out.applications=param.applications;
out.serverId=param.serverId;
out.user=param.user;
' #txt
Ue0 f28 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyLanguageSetting> settings> result;
' #txt
Ue0 f28 outParamTable 'result.errors=in.errors;
result.settings=in.languagesSettings;
' #txt
Ue0 f28 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f28 callSignature getLanguagesSettings(Long,List<String>,String) #txt
Ue0 f28 useUserDefinedException false #txt
Ue0 f28 taskData TaskTriggered.PRI=2 #txt
Ue0 f28 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getLanguagesSettings</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f28 @C|.responsibility Everybody #txt
Ue0 f28 947 51 26 26 14 0 #rect
Ue0 f28 @|StartWSIcon #fIcon
Ue0 f30 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f30 actionTable 'out=in;
' #txt
Ue0 f30 actionCode 'import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.bo.LanguagesSettingsServiceResult;
try{
	LanguagesSettingsServiceResult rs = WsServiceFactory.getLanguagesSettingsService().getLanguagesSettings(in.user, in.applications, in.serverId);
	out.languagesSettings = rs.settings;
	out.errors = rs.errors;
}catch(WSException e){
	in.errors.add(e);
}
' #txt
Ue0 f30 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get langues Settings</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f30 942 148 36 24 20 -2 #rect
Ue0 f30 @|StepIcon #fIcon
Ue0 f31 expr out #txt
Ue0 f31 960 77 960 148 #arcP
Ue0 f44 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f44 actionTable 'out=in;
' #txt
Ue0 f44 actionCode 'import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.bo.LanguagesSettingsServiceResult;
LanguagesSettingsServiceResult rs = WsServiceFactory.getLanguagesSettingsService().setLanguagesSettings(in.user,in.languagesSettings);
out.errors = rs.errors;' #txt
Ue0 f44 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set langues Settings</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f44 1118 148 36 24 20 -2 #rect
Ue0 f44 @|StepIcon #fIcon
Ue0 f45 inParamDecl '<java.util.List<ch.ivy.ws.addon.types.IvyLanguageSetting> settings,java.lang.String user> param;' #txt
Ue0 f45 inParamTable 'out.languagesSettings=param.settings;
out.user=param.user;
' #txt
Ue0 f45 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors> result;' #txt
Ue0 f45 outParamTable 'result.errors=in.errors;
' #txt
Ue0 f45 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f45 callSignature setLanguagesSettings(List<ch.ivy.ws.addon.types.IvyLanguageSetting>,String) #txt
Ue0 f45 useUserDefinedException false #txt
Ue0 f45 taskData '#
#Wed Mar 04 10:59:16 ICT 2015
TaskTriggered.PRI=2
' #txt
Ue0 f45 caseData '#
#Wed Mar 04 10:59:16 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ue0 f45 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setLanguagesSettings</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f45 @C|.responsibility Everybody #txt
Ue0 f45 1123 51 26 26 14 0 #rect
Ue0 f45 @|StartWSIcon #fIcon
Ue0 f46 expr out #txt
Ue0 f46 1136 77 1136 148 #arcP
Ue0 f59 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f59 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Ue0 f59 doCall true #txt
Ue0 f59 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Ue0 f59 requestMappingAction 'param.errors=in.errors;
' #txt
Ue0 f59 responseActionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f59 responseMappingAction 'out=in;
' #txt
Ue0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log errors if any</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f59 510 316 36 24 20 -2 #rect
Ue0 f59 @|CallSubIcon #fIcon
Ue0 f58 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f58 514 250 28 28 14 0 #rect
Ue0 f58 @|AlternativeIcon #fIcon
Ue0 f35 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f35 515 387 26 26 14 0 #rect
Ue0 f35 @|EndWSIcon #fIcon
Ue0 f60 expr in #txt
Ue0 f60 528 278 528 316 #arcP
Ue0 f61 expr out #txt
Ue0 f61 528 340 528 387 #arcP
Ue0 f33 expr out #txt
Ue0 f33 48 172 514 264 #arcP
Ue0 f33 1 48 264 #addKink
Ue0 f33 1 0.34529032978949215 0 0 #arcLabel
Ue0 f4 expr out #txt
Ue0 f4 200 172 514 264 #arcP
Ue0 f4 1 200 264 #addKink
Ue0 f4 1 0.2843157963664156 0 0 #arcLabel
Ue0 f6 expr out #txt
Ue0 f6 368 172 514 264 #arcP
Ue0 f6 1 368 264 #addKink
Ue0 f6 1 0.08983318105871259 0 0 #arcLabel
Ue0 f10 expr out #txt
Ue0 f10 528 172 528 250 #arcP
Ue0 f13 expr out #txt
Ue0 f13 680 172 542 264 #arcP
Ue0 f13 1 680 264 #addKink
Ue0 f13 1 0.07057049570929884 0 0 #arcLabel
Ue0 f25 expr out #txt
Ue0 f25 816 172 542 264 #arcP
Ue0 f25 1 816 264 #addKink
Ue0 f25 1 0.2532881176134045 0 0 #arcLabel
Ue0 f27 expr out #txt
Ue0 f27 960 172 542 264 #arcP
Ue0 f27 1 960 264 #addKink
Ue0 f27 1 0.3464891361126101 0 0 #arcLabel
Ue0 f32 expr out #txt
Ue0 f32 1136 172 542 264 #arcP
Ue0 f32 1 1136 264 #addKink
Ue0 f32 1 0.3908254353030045 0 0 #arcLabel
Ue0 f9 inParamDecl '<List<java.lang.String> apps,java.lang.String username,java.lang.String password> param;' #txt
Ue0 f9 inParamTable 'out.applications=param.apps;
out.password=param.password;
out.user=param.username;
' #txt
Ue0 f9 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Ue0 f9 outParamTable 'result.errors=in.errors;
' #txt
Ue0 f9 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f9 callSignature changePassword(List<String>,String,String) #txt
Ue0 f9 useUserDefinedException false #txt
Ue0 f9 taskData TaskTriggered.PRI=2 #txt
Ue0 f9 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>changePassword(apps, username, password)</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f9 @C|.responsibility Everybody #txt
Ue0 f9 1359 55 26 26 14 0 #rect
Ue0 f9 @|StartWSIcon #fIcon
Ue0 f1 actionDecl 'ch.ivy.ws.addon.UserServiceData out;
' #txt
Ue0 f1 actionTable 'out=in;
' #txt
Ue0 f1 actionCode 'import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	in.errors = WsServiceFactory.getUserSettingService().changePassword(in.applications, in.user, in.password);
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ue0 f1 type ch.ivy.ws.addon.UserServiceData #txt
Ue0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>change password</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ue0 f1 1354 152 36 24 20 -2 #rect
Ue0 f1 @|StepIcon #fIcon
Ue0 f12 expr out #txt
Ue0 f12 1372 81 1372 152 #arcP
Ue0 f18 expr out #txt
Ue0 f18 1372 176 542 264 #arcP
Ue0 f18 1 1372 264 #addKink
Ue0 f18 1 0.43253012048192774 0 0 #arcLabel
>Proto Ue0 .webServiceName ch.ivy.ws.addon.UserService #txt
>Proto Ue0 .authenticationType 'HTTP Basic' #txt
>Proto Ue0 .type ch.ivy.ws.addon.UserServiceData #txt
>Proto Ue0 .processKind WEB_SERVICE #txt
>Proto Ue0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>findUserSetting</swimlaneLabel>
        <swimlaneLabel>saveUserSetting</swimlaneLabel>
        <swimlaneLabel>getEMailSettings</swimlaneLabel>
        <swimlaneLabel>setEMailSettings</swimlaneLabel>
        <swimlaneLabel>substituteService</swimlaneLabel>
        <swimlaneLabel>languagesSettingService</swimlaneLabel>
        <swimlaneLabel>changePassword</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>152</swimlaneSize>
    <swimlaneSize>168</swimlaneSize>
    <swimlaneSize>168</swimlaneSize>
    <swimlaneSize>160</swimlaneSize>
    <swimlaneSize>280</swimlaneSize>
    <swimlaneSize>392</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor gradient="true">-26164</swimlaneColor>
    <swimlaneColor gradient="true">-6710785</swimlaneColor>
    <swimlaneColor gradient="true">-13108</swimlaneColor>
    <swimlaneColor gradient="true">-3342388</swimlaneColor>
    <swimlaneColor gradient="true">-3355393</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-3342388</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ue0 -8 -8 16 16 16 26 #rect
>Proto Ue0 '' #fIcon
Ue0 ws0 mainOut f3 tail #connect
Ue0 f3 head f2 mainIn #connect
Ue0 f0 mainOut f5 tail #connect
Ue0 f5 head f8 mainIn #connect
Ue0 f7 mainOut f15 tail #connect
Ue0 f15 head f14 mainIn #connect
Ue0 f11 mainOut f17 tail #connect
Ue0 f17 head f16 mainIn #connect
Ue0 f19 mainOut f24 tail #connect
Ue0 f24 head f23 mainIn #connect
Ue0 f22 mainOut f26 tail #connect
Ue0 f26 head f21 mainIn #connect
Ue0 f28 mainOut f31 tail #connect
Ue0 f31 head f30 mainIn #connect
Ue0 f45 mainOut f46 tail #connect
Ue0 f46 head f44 mainIn #connect
Ue0 f58 out f60 tail #connect
Ue0 f60 head f59 mainIn #connect
Ue0 f59 mainOut f61 tail #connect
Ue0 f61 head f35 mainIn #connect
Ue0 f2 mainOut f33 tail #connect
Ue0 f33 head f58 in #connect
Ue0 f8 mainOut f4 tail #connect
Ue0 f4 head f58 in #connect
Ue0 f14 mainOut f6 tail #connect
Ue0 f6 head f58 in #connect
Ue0 f16 mainOut f10 tail #connect
Ue0 f10 head f58 in #connect
Ue0 f23 mainOut f13 tail #connect
Ue0 f13 head f58 in #connect
Ue0 f21 mainOut f25 tail #connect
Ue0 f25 head f58 in #connect
Ue0 f30 mainOut f27 tail #connect
Ue0 f27 head f58 in #connect
Ue0 f44 mainOut f32 tail #connect
Ue0 f32 head f58 in #connect
Ue0 f9 mainOut f12 tail #connect
Ue0 f12 head f1 mainIn #connect
Ue0 f1 mainOut f18 tail #connect
Ue0 f18 head f58 in #connect

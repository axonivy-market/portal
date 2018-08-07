[Ivy]
[>Created: Thu Jul 23 10:03:54 ICT 2015]
14EB8CDD8931D241 3.17 #module
>Proto >Proto Collection #zClass
Cs0 CaseGeneralInformationProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @GridStep f3 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @PushWFArc f4 '' #zField
>Proto Cs0 Cs0 CaseGeneralInformationProcess #zField
Cs0 f1 type ch.ivy.addon.portalkit.singleapp.cases.CaseGeneralInformation.CaseGeneralInformationData #txt
Cs0 f1 70 182 20 20 13 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseGeneralInformation.CaseGeneralInformationData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'import ch.ivy.addon.portalkit.bo.Contact;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.UserUtils;


in.caseInfo = CaseUtils.getCaseById(in.caseId);

String email = CaseUtils.getEmailAddress(in.caseInfo);

in.contact = new Contact(email);
in.contact.phone = ivy.session.getSessionUser().getProperty(UserUtils.PHONE);
in.contact.mobilePhone = ivy.session.getSessionUser().getProperty(UserUtils.MOBILE);

if (in.caseInfo != null) {
	  in.tasks= TaskUtils.filterCurrentTasksByCase(in.caseInfo);
	  if (in.caseInfo.getCreatorUser() != null) { 
			in.creatorName = CaseUtils.getDisplayNameInFormat(in.caseInfo.getCreatorUser().getDisplayName(),in.caseInfo.getCreatorUserName());
		}
}


' #txt
Cs0 f3 type ch.ivy.addon.portalkit.singleapp.cases.CaseGeneralInformation.CaseGeneralInformationData #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init data</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 62 116 36 24 20 -2 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 80 140 80 182 #arcP
Cs0 f0 guid 14C6F34C8BCE860B #txt
Cs0 f0 type ch.ivy.addon.portalkit.singleapp.cases.CaseGeneralInformation.CaseGeneralInformationData #txt
Cs0 f0 method start(Number) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Number caseId> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Number)</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 70 54 20 20 13 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 80 74 80 116 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.singleapp.cases.CaseGeneralInformation.CaseGeneralInformationData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f0 mainOut f4 tail #connect
Cs0 f4 head f3 mainIn #connect

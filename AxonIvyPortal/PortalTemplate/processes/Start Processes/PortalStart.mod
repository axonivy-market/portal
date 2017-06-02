[Ivy]
[>Created: Fri Jun 02 17:34:23 ICT 2017]
1549F58C18A6C562 3.20 #module
>Proto >Proto Collection #zClass
Pt0 PortalStart Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .resExport .resExport #zField
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @MessageFlowInP-0n messageIn messageIn #zField
Pt0 @MessageFlowOutP-0n messageOut messageOut #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @StartRequest f0 '' #zField
Pt0 @GridStep f20 '' #zField
Pt0 @PushWFArc f14 '' #zField
Pt0 @CallSub f35 '' #zField
Pt0 @PushWFArc f36 '' #zField
>Proto Pt0 Pt0 PortalStart #zField
Pt0 f0 outLink PortalStart.ivp #txt
Pt0 f0 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f0 inParamDecl '<java.lang.String parameters,java.lang.String portalNavigator> param;' #txt
Pt0 f0 inParamTable 'out.parameters=param.parameters;
out.portalPage=param.parameters.isEmpty() ? ch.ivy.addon.portal.generic.navigation.PortalPage.HOME_PAGE : ch.ivy.addon.portal.generic.navigation.PortalPage.valueOf(param.portalNavigator);
' #txt
Pt0 f0 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f0 guid 1549F58C18C4ED13 #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature PortalStart(String,String) #txt
Pt0 f0 persist false #txt
Pt0 f0 startName <%=ivy.cms.co("/Processes/portalHome")%> #txt
Pt0 f0 taskData 'TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/PortalInternalProcess/PortalInternalProcessDescription")%>
TaskTriggered.EXTYPE=0
TaskTriggered.CATEGORY=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXPRI=2
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/portalSettingSaved")%>
TaskTriggered.ROL=Everybody
TaskTriggered.EXROL=Everybody' #txt
Pt0 f0 caseData 'case.name=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/portalSettingSaved")%>
case.description=<%\=ivy.cms.co("/Processes/Cases/PortalInternalProcess/PortalInternalProcessDescription")%>
case.category=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
businessCase.attach=true' #txt
Pt0 f0 showInStartList 1 #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalStart.ivp</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Everybody #txt
Pt0 f0 51 115 26 26 -31 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f20 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f20 actionTable 'out=in;
' #txt
Pt0 f20 actionCode 'import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.workflow.StandardProcessType;

GlobalSettingService service = new GlobalSettingService();
String isDefaultEndPageToPortal = service.findGlobalSettingValue(GlobalVariable.DEFAULT_END_PAGE_TO_PORTAL);

if (#isDefaultEndPageToPortal is initialized || isDefaultEndPageToPortal.toBoolean()) {
	String defaultProcessLibraryName = "ch.ivyteam.ivy.project.portal:portalTemplate";
	ivy.wf.setStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES, defaultProcessLibraryName);
}

SecurityServiceUtils.setSessionAttribute(SessionAttribute.LAST_PAGE.toString(), in.portalPage);' #txt
Pt0 f20 security system #txt
Pt0 f20 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set default end page 
to Portal and store the 
last page to session</name>
        <nameStyle>67
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f20 192 98 144 60 -54 -24 #rect
Pt0 f20 @|StepIcon #fIcon
Pt0 f14 expr out #txt
Pt0 f14 77 128 192 128 #arcP
Pt0 f35 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f35 processCall 'Functional Processes/PortalStartPageCallable:call(ch.ivy.addon.portal.generic.PortalStartData)' #txt
Pt0 f35 doCall true #txt
Pt0 f35 requestActionDecl '<ch.ivy.addon.portal.generic.PortalStartData data> param;
' #txt
Pt0 f35 requestMappingAction 'param.data=in;
' #txt
Pt0 f35 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f35 responseMappingAction 'out=in;
' #txt
Pt0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalStartPageCallable</name>
    </language>
</elementInfo>
' #txt
Pt0 f35 392 106 144 44 -66 -8 #rect
Pt0 f35 @|CallSubIcon #fIcon
Pt0 f36 expr out #txt
Pt0 f36 336 128 392 128 #arcP
>Proto Pt0 .type ch.ivy.addon.portal.generic.PortalStartData #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f0 mainOut f14 tail #connect
Pt0 f14 head f20 mainIn #connect
Pt0 f20 mainOut f36 tail #connect
Pt0 f36 head f35 mainIn #connect

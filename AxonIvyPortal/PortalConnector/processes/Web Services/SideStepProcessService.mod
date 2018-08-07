[Ivy]
[>Created: Fri Jun 02 19:13:32 ICT 2017]
15C62875FD98F850 3.20 #module
>Proto >Proto Collection #zClass
Pe0 SideStepProcessService Big #zClass
Pe0 WS #cInfo
Pe0 #process
Pe0 @TextInP .webServiceName .webServiceName #zField
Pe0 @TextInP .implementationClassName .implementationClassName #zField
Pe0 @TextInP .authenticationType .authenticationType #zField
Pe0 @TextInP .resExport .resExport #zField
Pe0 @TextInP .type .type #zField
Pe0 @TextInP .processKind .processKind #zField
Pe0 @AnnotationInP-0n ai ai #zField
Pe0 @TextInP .xml .xml #zField
Pe0 @TextInP .responsibility .responsibility #zField
Pe0 @StartWS f8 '' #zField
Pe0 @GridStep f10 '' #zField
Pe0 @PushWFArc f11 '' #zField
Pe0 @CallSub f59 '' #zField
Pe0 @EndWS f35 '' #zField
Pe0 @PushWFArc f61 '' #zField
Pe0 @PushWFArc f0 '' #zField
>Proto Pe0 Pe0 SideStepProcessService #zField
Pe0 f8 inParamDecl '<java.lang.Boolean isUrlBuiltFromSystemProperties,ch.ivy.ws.addon.service.SideStepSearchCriteria sideStepSearchCriteria,java.lang.String language> param;' #txt
Pe0 f8 inParamTable 'out.isUrlBuiltFromSystemProperties=param.isUrlBuiltFromSystemProperties;
out.language=param.language;
out.sideStepSearchCriteria=param.sideStepSearchCriteria;
' #txt
Pe0 f8 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,java.util.List<ch.ivy.ws.addon.types.IvySideStep> sideSteps> result;
' #txt
Pe0 f8 outParamTable 'result.errors=in.errors;
result.sideSteps=in.sideSteps;
' #txt
Pe0 f8 actionDecl 'ch.ivy.ws.addon.SideStepServiceData out;
' #txt
Pe0 f8 callSignature findSideStepsByCriteria(Boolean,ch.ivy.ws.addon.service.SideStepSearchCriteria,String) #txt
Pe0 f8 useUserDefinedException false #txt
Pe0 f8 taskData TaskTriggered.PRI=2 #txt
Pe0 f8 type ch.ivy.ws.addon.SideStepServiceData #txt
Pe0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSideStepsByCriteria(Boolean,SideStepSearchCriteria,String)</name>
    </language>
</elementInfo>
' #txt
Pe0 f8 @C|.responsibility Everybody #txt
Pe0 f8 115 51 26 26 14 0 #rect
Pe0 f8 @|StartWSIcon #fIcon
Pe0 f10 actionDecl 'ch.ivy.ws.addon.SideStepServiceData out;
' #txt
Pe0 f10 actionTable 'out=in;
' #txt
Pe0 f10 actionCode 'import ch.ivy.ws.addon.bo.SideStepServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	SideStepServiceResult result = WsServiceFactory.getSideStepService().findSideStepsByCriteria(in.sideStepSearchCriteria, in.language, in.isUrlBuiltFromSystemProperties);
	in.sideSteps = result.getSideSteps();
	in.errors = result.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Pe0 f10 type ch.ivy.ws.addon.SideStepServiceData #txt
Pe0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find by criteria</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f10 110 132 36 24 20 -2 #rect
Pe0 f10 @|StepIcon #fIcon
Pe0 f11 expr out #txt
Pe0 f11 128 77 128 132 #arcP
Pe0 f59 type ch.ivy.ws.addon.SideStepServiceData #txt
Pe0 f59 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Pe0 f59 doCall true #txt
Pe0 f59 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Pe0 f59 requestMappingAction 'param.errors=in.errors;
' #txt
Pe0 f59 responseActionDecl 'ch.ivy.ws.addon.SideStepServiceData out;
' #txt
Pe0 f59 responseMappingAction 'out=in;
' #txt
Pe0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log errors if any</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f59 110 212 36 24 20 -2 #rect
Pe0 f59 @|CallSubIcon #fIcon
Pe0 f35 type ch.ivy.ws.addon.SideStepServiceData #txt
Pe0 f35 115 283 26 26 14 0 #rect
Pe0 f35 @|EndWSIcon #fIcon
Pe0 f61 expr out #txt
Pe0 f61 128 236 128 283 #arcP
Pe0 f0 expr out #txt
Pe0 f0 128 156 128 212 #arcP
>Proto Pe0 .webServiceName ch.ivy.ws.addon.SideStepProcessService #txt
>Proto Pe0 .authenticationType 'HTTP Basic' #txt
>Proto Pe0 .type ch.ivy.ws.addon.SideStepServiceData #txt
>Proto Pe0 .processKind WEB_SERVICE #txt
>Proto Pe0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>find ProcessStarts by criteria</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>264</swimlaneSize>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Pe0 -8 -8 16 16 16 26 #rect
>Proto Pe0 '' #fIcon
Pe0 f8 mainOut f11 tail #connect
Pe0 f11 head f10 mainIn #connect
Pe0 f59 mainOut f61 tail #connect
Pe0 f61 head f35 mainIn #connect
Pe0 f10 mainOut f0 tail #connect
Pe0 f0 head f59 mainIn #connect

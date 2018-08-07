[Ivy]
[>Created: Fri Jun 23 15:02:12 ICT 2017]
15CD2EB4E7714D02 3.20 #module
>Proto >Proto Collection #zClass
Pe0 WebStartableService Big #zClass
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
Pe0 @GridStep f10 '' #zField
Pe0 @CallSub f59 '' #zField
Pe0 @EndWS f35 '' #zField
Pe0 @PushWFArc f61 '' #zField
Pe0 @PushWFArc f0 '' #zField
Pe0 @StartWS f1 '' #zField
Pe0 @PushWFArc f2 '' #zField
>Proto Pe0 Pe0 WebStartableService #zField
Pe0 f10 actionDecl 'ch.ivy.ws.addon.WebStartableServiceData out;
' #txt
Pe0 f10 actionTable 'out=in;
' #txt
Pe0 f10 actionCode 'import ch.ivy.ws.addon.bo.WebStartableServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	WebStartableServiceResult result = WsServiceFactory.getWebStartableService().findWebStartablesByCriteria(in.webStartableSearchCriteria, in.language, in.isUrlBuiltFromSystemProperties	);
	in.webStartables = result.getWebStartables();
	in.errors = result.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Pe0 f10 type ch.ivy.ws.addon.WebStartableServiceData #txt
Pe0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find web startables
by criteria</name>
        <nameStyle>31,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f10 110 132 36 24 20 -2 #rect
Pe0 f10 @|StepIcon #fIcon
Pe0 f59 type ch.ivy.ws.addon.WebStartableServiceData #txt
Pe0 f59 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Pe0 f59 doCall true #txt
Pe0 f59 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Pe0 f59 requestMappingAction 'param.errors=in.errors;
' #txt
Pe0 f59 responseActionDecl 'ch.ivy.ws.addon.WebStartableServiceData out;
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
Pe0 f35 type ch.ivy.ws.addon.WebStartableServiceData #txt
Pe0 f35 115 283 26 26 14 0 #rect
Pe0 f35 @|EndWSIcon #fIcon
Pe0 f61 expr out #txt
Pe0 f61 128 236 128 283 #arcP
Pe0 f0 expr out #txt
Pe0 f0 128 156 128 212 #arcP
Pe0 f1 inParamDecl '<java.lang.Boolean isUrlBuiltFromSystemProperties,java.lang.String language,ch.ivy.ws.addon.service.WebStartableSearchCriteria webStartableSearchCriteria> param;' #txt
Pe0 f1 inParamTable 'out.isUrlBuiltFromSystemProperties=param.isUrlBuiltFromSystemProperties;
out.language=param.language;
out.webStartableSearchCriteria=param.webStartableSearchCriteria;
' #txt
Pe0 f1 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyWebStartable> webStartables> result;
' #txt
Pe0 f1 outParamTable 'result.errors=in.errors;
result.webStartables=in.webStartables;
' #txt
Pe0 f1 actionDecl 'ch.ivy.ws.addon.WebStartableServiceData out;
' #txt
Pe0 f1 callSignature findWebStartablesByCriteria(Boolean,String,ch.ivy.ws.addon.service.WebStartableSearchCriteria) #txt
Pe0 f1 useUserDefinedException false #txt
Pe0 f1 taskData TaskTriggered.PRI=2 #txt
Pe0 f1 type ch.ivy.ws.addon.WebStartableServiceData #txt
Pe0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findWebStartablesByCriteria(Boolean,String,WebStartableSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Pe0 f1 @C|.responsibility Everybody #txt
Pe0 f1 113 49 30 30 12 16 #rect
Pe0 f1 @|StartWSIcon #fIcon
Pe0 f2 expr out #txt
Pe0 f2 128 79 128 132 #arcP
>Proto Pe0 .webServiceName ch.ivy.ws.addon.WebStartableService #txt
>Proto Pe0 .authenticationType 'HTTP Basic' #txt
>Proto Pe0 .type ch.ivy.ws.addon.WebStartableServiceData #txt
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
Pe0 f59 mainOut f61 tail #connect
Pe0 f61 head f35 mainIn #connect
Pe0 f10 mainOut f0 tail #connect
Pe0 f0 head f59 mainIn #connect
Pe0 f1 mainOut f2 tail #connect
Pe0 f2 head f10 mainIn #connect

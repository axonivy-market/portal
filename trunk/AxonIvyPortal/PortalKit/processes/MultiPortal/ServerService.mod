[Ivy]
[>Created: Tue Jan 03 17:17:34 ICT 2017]
155A5DD23BBDDA3B 3.18 #module
>Proto >Proto Collection #zClass
Se0 ServerService Big #zClass
Se0 B #cInfo
Se0 #process
Se0 @TextInP .resExport .resExport #zField
Se0 @TextInP .type .type #zField
Se0 @TextInP .processKind .processKind #zField
Se0 @AnnotationInP-0n ai ai #zField
Se0 @MessageFlowInP-0n messageIn messageIn #zField
Se0 @MessageFlowOutP-0n messageOut messageOut #zField
Se0 @TextInP .xml .xml #zField
Se0 @TextInP .responsibility .responsibility #zField
Se0 @CallSub f5 '' #zField
Se0 @StartSub f0 '' #zField
Se0 @PushWFArc f2 '' #zField
Se0 @EndSub f1 '' #zField
Se0 @PushWFArc f3 '' #zField
Se0 @PushWFArc f7 '' #zField
Se0 @GridStep f15 '' #zField
Se0 @ErrorBoundaryEvent Et0 ErrorBoundaryEvent #zField
Se0 @PushWFArc f4 '' #zField
>Proto Se0 Se0 ServerService #zField
Se0 f5 type ch.ivyteam.wf.processes.ServerServiceData #txt
Se0 f5 processCall ServiceIntegrators/ServerServiceIntegrator:getExternalHost(String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Se0 f5 doCall true #txt
Se0 f5 requestActionDecl '<java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Se0 f5 requestMappingAction 'param.endpoint=in.endpoint;
param.server=in.server;
' #txt
Se0 f5 responseActionDecl 'ch.ivyteam.wf.processes.ServerServiceData out;
' #txt
Se0 f5 responseMappingAction 'out=in;
out.errors=result.errors;
out.externalHost=result.externalPath;
' #txt
Se0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get external host</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f5 110 228 36 24 20 -2 #rect
Se0 f5 @|CallSubIcon #fIcon
Se0 f0 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Se0 f0 inParamTable 'out.server=param.server;
' #txt
Se0 f0 outParamDecl '<java.lang.String externalHost,List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Se0 f0 outParamTable 'result.externalHost=in.externalHost;
result.errors=in.errors;
' #txt
Se0 f0 actionDecl 'ch.ivyteam.wf.processes.ServerServiceData out;
' #txt
Se0 f0 callSignature getExternalHost(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Se0 f0 type ch.ivyteam.wf.processes.ServerServiceData #txt
Se0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getExternalHost(Server)</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f0 115 83 26 26 14 0 #rect
Se0 f0 @|StartSubIcon #fIcon
Se0 f2 expr out #txt
Se0 f2 128 252 128 339 #arcP
Se0 f1 type ch.ivyteam.wf.processes.ServerServiceData #txt
Se0 f1 115 339 26 26 14 0 #rect
Se0 f1 @|EndSubIcon #fIcon
Se0 f3 expr out #txt
Se0 f3 128 180 128 228 #arcP
Se0 f7 expr out #txt
Se0 f7 128 109 128 156 #arcP
Se0 f15 actionDecl 'ch.ivyteam.wf.processes.ServerServiceData out;
' #txt
Se0 f15 actionTable 'out=in;
' #txt
Se0 f15 actionCode 'import ch.ivy.addon.portalkit.enums.WebServiceEndPoint;

in.endpoint = in.server.getPath() + WebServiceEndPoint.SERVER.toString();
' #txt
Se0 f15 type ch.ivyteam.wf.processes.ServerServiceData #txt
Se0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get actual
endpoint</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f15 110 156 36 24 26 -17 #rect
Se0 f15 @|StepIcon #fIcon
Se0 Et0 actionDecl 'ch.ivyteam.wf.processes.ServerServiceData out;
' #txt
Se0 Et0 actionTable 'out=in;
' #txt
Se0 Et0 actionCode 'import java.util.Calendar;
import ch.ivy.ws.addon.WsErrorType;
import ch.ivy.ws.addon.WsException;

WsException exception;
exception.errorCode = 11001;
exception.errorType = WsErrorType.WARNING;
Calendar c = Calendar.getInstance();
c.setTime(new java.util.Date());
exception.errorDateTime = c;
exception.userText = ivy.cms.co("/errors/11001/userText", [in.server.name]);
exception.server = in.server.name;
in.errors.add(exception);
ivy.log.error(error.stackTrace);' #txt
Se0 Et0 type ch.ivyteam.wf.processes.ServerServiceData #txt
Se0 Et0 attachedToRef 155A5DD23BBDDA3B-f15 #txt
Se0 Et0 109 169 26 26 14 0 #rect
Se0 Et0 @|ErrorBoundaryEventIcon #fIcon
Se0 f4 109 182 115 352 #arcP
Se0 f4 1 80 182 #addKink
Se0 f4 2 80 352 #addKink
Se0 f4 1 0.5185185185185185 0 0 #arcLabel
>Proto Se0 .type ch.ivyteam.wf.processes.ServerServiceData #txt
>Proto Se0 .processKind CALLABLE_SUB #txt
>Proto Se0 0 0 32 24 18 0 #rect
>Proto Se0 @|BIcon #fIcon
Se0 f5 mainOut f2 tail #connect
Se0 f2 head f1 mainIn #connect
Se0 f0 mainOut f7 tail #connect
Se0 f7 head f15 mainIn #connect
Se0 f15 mainOut f3 tail #connect
Se0 f3 head f5 mainIn #connect
Se0 Et0 mainOut f4 tail #connect
Se0 f4 head f1 mainIn #connect

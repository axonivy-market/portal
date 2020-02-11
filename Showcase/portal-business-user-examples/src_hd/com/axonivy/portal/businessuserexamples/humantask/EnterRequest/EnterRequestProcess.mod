[Ivy]
170325E7955A8B73 7.5.0 #module
>Proto >Proto Collection #zClass
Es0 EnterRequestProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @AnnotationInP-0n ai ai #zField
Es0 @MessageFlowInP-0n messageIn messageIn #zField
Es0 @MessageFlowOutP-0n messageOut messageOut #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdInit f0 '' #zField
Es0 @UdProcessEnd f1 '' #zField
Es0 @UdEvent f3 '' #zField
Es0 @UdExitEnd f4 '' #zField
Es0 @GridStep f6 '' #zField
Es0 @PushWFArc f7 '' #zField
Es0 @PushWFArc f2 '' #zField
Es0 @GridStep f8 '' #zField
Es0 @PushWFArc f9 '' #zField
Es0 @PushWFArc f5 '' #zField
>Proto Es0 Es0 EnterRequestProcess #zField
Es0 f0 guid 14FADF4C8E9956A6 #txt
Es0 f0 method start() #txt
Es0 f0 inParameterDecl '<> param;' #txt
Es0 f0 outParameterDecl '<com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest procurementRequestData,com.axonivy.portal.businessuserexamples.humantask.LogEntry logEntry> result;' #txt
Es0 f0 outParameterMapAction 'result.procurementRequestData=in.procurementRequestData;
result.logEntry=in.logEntry;
' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f0 83 51 26 26 -16 15 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f1 339 51 26 26 0 12 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f3 guid 14FADF4C908E8C33 #txt
Es0 f3 actionTable 'out=in;
' #txt
Es0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f3 83 147 26 26 -15 15 #rect
Es0 f3 @|UdEventIcon #fIcon
Es0 f4 339 147 26 26 0 12 #rect
Es0 f4 @|UdExitEndIcon #fIcon
Es0 f6 actionTable 'out=in;
out.procurementRequestData=new com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest();
out.procurementRequestData.requester.email=ivy.session.getSessionUser().eMailAddress;
' #txt
Es0 f6 actionCode 'import org.apache.commons.lang3.StringUtils;


if (StringUtils.isBlank(out.procurementRequestData.requester.email))
{
	out.procurementRequestData.requester.email = "developer@axonivy.com";
}' #txt
Es0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init data</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f6 168 42 112 44 -21 -8 #rect
Es0 f6 @|StepIcon #fIcon
Es0 f7 expr out #txt
Es0 f7 109 64 168 64 #arcP
Es0 f2 expr out #txt
Es0 f2 280 64 339 64 #arcP
Es0 f8 actionTable 'out=in;
out.logEntry.activity=ivy.cms.co("/Dialogs/procurementRequest/requestedBy");
out.logEntry.timestamp=new DateTime();
out.logEntry.user.fullName=ivy.session.getSessionUser().fullName;
' #txt
Es0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init LogEntry</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f8 168 138 112 44 -33 -8 #rect
Es0 f8 @|StepIcon #fIcon
Es0 f9 expr out #txt
Es0 f9 109 160 168 160 #arcP
Es0 f5 expr out #txt
Es0 f5 280 160 339 160 #arcP
>Proto Es0 .type com.axonivy.portal.businessuserexamples.humantask.EnterRequest.EnterRequestData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f0 mainOut f7 tail #connect
Es0 f7 head f6 mainIn #connect
Es0 f6 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
Es0 f3 mainOut f9 tail #connect
Es0 f9 head f8 mainIn #connect
Es0 f8 mainOut f5 tail #connect
Es0 f5 head f4 mainIn #connect

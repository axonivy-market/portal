[Ivy]
170325EE2B44A5AC 7.5.0 #module
>Proto >Proto Collection #zClass
Vs0 VerifyRequestProcess Big #zClass
Vs0 RD #cInfo
Vs0 #process
Vs0 @TextInP .type .type #zField
Vs0 @TextInP .processKind .processKind #zField
Vs0 @AnnotationInP-0n ai ai #zField
Vs0 @MessageFlowInP-0n messageIn messageIn #zField
Vs0 @MessageFlowOutP-0n messageOut messageOut #zField
Vs0 @TextInP .xml .xml #zField
Vs0 @TextInP .responsibility .responsibility #zField
Vs0 @UdInit f0 '' #zField
Vs0 @UdProcessEnd f1 '' #zField
Vs0 @UdExitEnd f4 '' #zField
Vs0 @PushWFArc f2 '' #zField
Vs0 @GridStep f6 '' #zField
Vs0 @PushWFArc f5 '' #zField
Vs0 @UdEvent f8 '' #zField
Vs0 @UdEvent f11 '' #zField
Vs0 @PushWFArc f9 '' #zField
Vs0 @UdExitEnd f3 '' #zField
Vs0 @GridStep f10 '' #zField
Vs0 @PushWFArc f12 '' #zField
Vs0 @PushWFArc f7 '' #zField
>Proto Vs0 Vs0 VerifyRequestProcess #zField
Vs0 f0 guid 14FAE07217F2117A #txt
Vs0 f0 method start(com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest) #txt
Vs0 f0 inParameterDecl '<com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest procurementRequest> param;' #txt
Vs0 f0 inParameterMapAction 'out.procurementRequest=param.procurementRequest;
' #txt
Vs0 f0 outParameterDecl '<Boolean dataOk,com.axonivy.portal.businessuserexamples.humantask.LogEntry logEntry> result;' #txt
Vs0 f0 outParameterMapAction 'result.dataOk=in.ok;
result.logEntry=in.logEntry;
' #txt
Vs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(ProcurementRequest)</name>
    </language>
</elementInfo>
' #txt
Vs0 f0 83 51 26 26 -75 15 #rect
Vs0 f0 @|UdInitIcon #fIcon
Vs0 f1 339 51 26 26 0 12 #rect
Vs0 f1 @|UdProcessEndIcon #fIcon
Vs0 f4 339 147 26 26 0 12 #rect
Vs0 f4 @|UdExitEndIcon #fIcon
Vs0 f2 expr out #txt
Vs0 f2 109 64 339 64 #arcP
Vs0 f6 actionTable 'out=in;
out.logEntry.activity=ivy.cms.co("/Dialogs/procurementRequest/verifiedBy");
out.logEntry.timestamp=new DateTime();
out.logEntry.user.fullName=ivy.session.getSessionUser().fullName;
out.logEntry.user.role=ivy.task.activator.getMemberName();
' #txt
Vs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init LogEntry</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Vs0 f6 168 138 112 44 -33 -8 #rect
Vs0 f6 @|StepIcon #fIcon
Vs0 f5 expr out #txt
Vs0 f5 280 160 339 160 #arcP
Vs0 f8 guid 152A1A71D5D17CAC #txt
Vs0 f8 actionTable 'out=in;
out.ok=false;
' #txt
Vs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>decline</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Vs0 f8 59 211 26 26 -20 15 #rect
Vs0 f8 @|UdEventIcon #fIcon
Vs0 f11 guid 152A1A7AEA3D662F #txt
Vs0 f11 actionTable 'out=in;
out.ok=true;
' #txt
Vs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>verify</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Vs0 f11 59 147 26 26 -13 15 #rect
Vs0 f11 @|UdEventIcon #fIcon
Vs0 f9 expr out #txt
Vs0 f9 85 160 168 160 #arcP
Vs0 f3 339 211 26 26 0 12 #rect
Vs0 f3 @|UdExitEndIcon #fIcon
Vs0 f10 actionTable 'out=in;
out.logEntry.activity=ivy.cms.co("/Dialogs/procurementRequest/declinedBy");
out.logEntry.timestamp=new DateTime();
out.logEntry.user.fullName=ivy.session.getSessionUser().fullName;
out.logEntry.user.role=ivy.task.activator.getMemberName();
' #txt
Vs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init LogEntry</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Vs0 f10 168 202 112 44 -33 -8 #rect
Vs0 f10 @|StepIcon #fIcon
Vs0 f12 expr out #txt
Vs0 f12 85 224 168 224 #arcP
Vs0 f12 0 0.5658436222109136 0 0 #arcLabel
Vs0 f7 expr out #txt
Vs0 f7 280 224 339 224 #arcP
Vs0 f7 0 0.5658436222109136 0 0 #arcLabel
>Proto Vs0 .type com.axonivy.portal.businessuserexamples.humantask.VerifyRequest.VerifyRequestData #txt
>Proto Vs0 .processKind HTML_DIALOG #txt
>Proto Vs0 -8 -8 16 16 16 26 #rect
>Proto Vs0 '' #fIcon
Vs0 f0 mainOut f2 tail #connect
Vs0 f2 head f1 mainIn #connect
Vs0 f6 mainOut f5 tail #connect
Vs0 f5 head f4 mainIn #connect
Vs0 f11 mainOut f9 tail #connect
Vs0 f9 head f6 mainIn #connect
Vs0 f8 mainOut f12 tail #connect
Vs0 f12 head f10 mainIn #connect
Vs0 f10 mainOut f7 tail #connect
Vs0 f7 head f3 mainIn #connect

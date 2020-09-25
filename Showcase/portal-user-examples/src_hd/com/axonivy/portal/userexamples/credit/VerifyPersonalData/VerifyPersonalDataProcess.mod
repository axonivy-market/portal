[Ivy]
17079E5EB3A9C649 9.2.0 #module
>Proto >Proto Collection #zClass
As0 VerifyPersonalDataProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @MessageFlowInP-0n messageIn messageIn #zField
As0 @MessageFlowOutP-0n messageOut messageOut #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @UdInit f0 '' #zField
As0 @UdProcessEnd f1 '' #zField
As0 @GridStep f6 '' #zField
As0 @PushWFArc f7 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @UdExitEnd f4 '' #zField
As0 @GridStep f8 '' #zField
As0 @UdEvent f3 '' #zField
As0 @PushWFArc f9 '' #zField
As0 @PushWFArc f5 '' #zField
>Proto As0 As0 VerifyPersonalDataProcess #zField
As0 f0 guid 15B3845CC516F9AE #txt
As0 f0 method start() #txt
As0 f0 inParameterDecl '<> param;' #txt
As0 f0 outParameterDecl '<> result;' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
As0 f0 83 51 26 26 -16 15 #rect
As0 f0 @|UdInitIcon #fIcon
As0 f1 339 51 26 26 0 12 #rect
As0 f1 @|UdProcessEndIcon #fIcon
As0 f6 actionTable 'out=in;
out.dossier=ivy.repo.get(com.axonivy.portal.userexamples.credit.CreditDossier.class) as com.axonivy.portal.userexamples.credit.CreditDossier;
' #txt
As0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Load dossier</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f6 168 42 112 44 -40 -8 #rect
As0 f6 @|StepIcon #fIcon
As0 f7 expr out #txt
As0 f7 109 64 168 64 #arcP
As0 f2 expr out #txt
As0 f2 280 64 339 64 #arcP
As0 f4 339 155 26 26 0 12 #rect
As0 f4 @|UdExitEndIcon #fIcon
As0 f8 actionTable 'out=in;
' #txt
As0 f8 actionCode ivy.repo.save(in.dossier); #txt
As0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Save dossier</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f8 168 146 112 44 -37 -8 #rect
As0 f8 @|StepIcon #fIcon
As0 f3 guid 1707A0CC20A9711C #txt
As0 f3 actionTable 'out=in;
' #txt
As0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
As0 f3 83 155 26 26 -15 12 #rect
As0 f3 @|UdEventIcon #fIcon
As0 f9 expr out #txt
As0 f9 109 168 168 168 #arcP
As0 f5 expr out #txt
As0 f5 280 168 339 168 #arcP
>Proto As0 .type com.axonivy.portal.userexamples.credit.VerifyPersonalData.VerifyPersonalDataData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f0 mainOut f7 tail #connect
As0 f7 head f6 mainIn #connect
As0 f6 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
As0 f3 mainOut f9 tail #connect
As0 f9 head f8 mainIn #connect
As0 f8 mainOut f5 tail #connect
As0 f5 head f4 mainIn #connect

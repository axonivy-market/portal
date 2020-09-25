[Ivy]
1703246ACFD80155 9.2.0 #module
>Proto >Proto Collection #zClass
As0 ApproveLevel1Process Big #zClass
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
As0 @UdEvent f3 '' #zField
As0 @UdExitEnd f4 '' #zField
As0 @GridStep f6 '' #zField
As0 @PushWFArc f7 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @GridStep f8 '' #zField
As0 @PushWFArc f9 '' #zField
As0 @PushWFArc f5 '' #zField
As0 @UdEvent f10 '' #zField
As0 @PushWFArc f11 '' #zField
>Proto As0 As0 ApproveLevel1Process #zField
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
As0 f3 guid 15B3845CC6181BED #txt
As0 f3 actionTable 'out=in;
out.dossier.decision.level1Approved=true;
' #txt
As0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>approved</name>
        <nameStyle>8,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f3 83 147 26 26 -25 15 #rect
As0 f3 @|UdEventIcon #fIcon
As0 f4 339 147 26 26 0 12 #rect
As0 f4 @|UdExitEndIcon #fIcon
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
As0 f8 168 138 112 44 -36 -8 #rect
As0 f8 @|StepIcon #fIcon
As0 f9 expr out #txt
As0 f9 109 160 168 160 #arcP
As0 f5 expr out #txt
As0 f5 280 160 339 160 #arcP
As0 f10 guid 15B3852F3A0CCC17 #txt
As0 f10 actionTable 'out=in;
out.dossier.decision.level1Approved=false;
out.dossier.decision.needsLevel2Approval=false;
' #txt
As0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>rejected</name>
        <nameStyle>8,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f10 81 224 26 26 -22 15 #rect
As0 f10 @|UdEventIcon #fIcon
As0 f11 expr out #txt
As0 f11 107 237 224 182 #arcP
As0 f11 1 224 237 #addKink
As0 f11 0 0.7579026201260659 0 0 #arcLabel
>Proto As0 .type com.axonivy.portal.userexamples.credit.ApproveLevel1.ApproveLevel1Data #txt
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
As0 f10 mainOut f11 tail #connect
As0 f11 head f8 mainIn #connect

[Ivy]
1709948259287151 9.2.0 #module
>Proto >Proto Collection #zClass
Cs0 ContractCreationProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdEvent f3 '' #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @GridStep f6 '' #zField
Cs0 @UdInit f0 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @PushWFArc f2 '' #zField
>Proto Cs0 Cs0 ContractCreationProcess #zField
Cs0 f3 guid 170994825CA514EC #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 83 147 26 26 -15 15 #rect
Cs0 f3 @|UdEventIcon #fIcon
Cs0 f4 211 147 26 26 0 12 #rect
Cs0 f4 @|UdExitEndIcon #fIcon
Cs0 f5 109 160 211 160 #arcP
Cs0 f1 339 67 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f6 actionTable 'out=in;
out.contractDate=new java.util.Date();
out.dossier=ivy.repo.get(com.axonivy.portal.userexamples.credit.CreditDossier.class) as com.axonivy.portal.userexamples.credit.CreditDossier;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Load dossier</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 168 58 112 44 -40 -8 #rect
Cs0 f6 @|StepIcon #fIcon
Cs0 f0 guid 17099515308117D2 #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 67 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f7 expr out #txt
Cs0 f7 109 80 168 80 #arcP
Cs0 f2 expr out #txt
Cs0 f2 280 80 339 80 #arcP
>Proto Cs0 .type com.axonivy.portal.userexamples.credit.ContractCreation.ContractCreationData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f0 mainOut f7 tail #connect
Cs0 f7 head f6 mainIn #connect
Cs0 f6 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect

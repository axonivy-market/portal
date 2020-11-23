[Ivy]
1754AA136C748BA5 9.2.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseInformationInIFrameProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @UdEvent f3 '' #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @GridStep f6 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @PushWFArc f2 '' #zField
>Proto Cs0 Cs0 CaseInformationInIFrameProcess #zField
Cs0 f0 guid 1754AA136CE42DB5 #txt
Cs0 f0 method start(Long,Boolean) #txt
Cs0 f0 inParameterDecl '<Long caseId,Boolean showBackButton> param;' #txt
Cs0 f0 inParameterMapAction 'out.currentCaseId=param.caseId;
out.showBackButton=param.showBackButton;
' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Long,Boolean)</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 467 51 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f3 guid 1754AA136D75539A #txt
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
Cs0 f6 actionTable 'out=in;
' #txt
Cs0 f6 actionCode 'in.currentCase = ivy.wf.findCase(in.currentCaseId);
in.readOnly = !in.currentCase.isBusinessCase();' #txt
Cs0 f6 security system #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check read only condition</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 224 42 160 44 -70 -8 #rect
Cs0 f6 @|StepIcon #fIcon
Cs0 f7 109 64 224 64 #arcP
Cs0 f2 384 64 467 64 #arcP
>Proto Cs0 .type ch.ivy.addon.portal.component.iframe.CaseInformationInIFrame.CaseInformationInIFrameData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f0 mainOut f7 tail #connect
Cs0 f7 head f6 mainIn #connect
Cs0 f6 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect

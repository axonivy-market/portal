[Ivy]
153CBA4AE9F19221 3.20 #module
>Proto >Proto Collection #zClass
Ds0 DataCreationProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ds0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ds0 @TextInP .resExport .resExport #zField
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @RichDialogInitStart f0 '' #zField
Ds0 @RichDialogProcessEnd f1 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @RichDialogProcessStart f3 '' #zField
Ds0 @RichDialogEnd f4 '' #zField
Ds0 @PushWFArc f5 '' #zField
>Proto Ds0 Ds0 DataCreationProcess #zField
Ds0 f0 guid 153CBA4AED16956D #txt
Ds0 f0 type ch.ivy.addon.portalkit.test.DataCreation.DataCreationData #txt
Ds0 f0 method start() #txt
Ds0 f0 disableUIEvents true #txt
Ds0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ds0 f0 outParameterDecl '<java.lang.Integer numOfCats,java.lang.Integer numOfSubCats,java.lang.Integer numOfCasesPerSubCat,java.lang.Boolean attachToBussinessCase> result;
' #txt
Ds0 f0 outParameterMapAction 'result.numOfCats=in.numOfCats;
result.numOfSubCats=in.numOfSubCats;
result.numOfCasesPerSubCat=in.numOfCasesPerSubCat;
result.attachToBussinessCase=in.attachToBusinessCase;
' #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f0 53 85 22 22 14 0 #rect
Ds0 f0 @|RichDialogInitStartIcon #fIcon
Ds0 f1 type ch.ivy.addon.portalkit.test.DataCreation.DataCreationData #txt
Ds0 f1 53 213 22 22 14 0 #rect
Ds0 f1 @|RichDialogProcessEndIcon #fIcon
Ds0 f2 expr out #txt
Ds0 f2 64 107 64 213 #arcP
Ds0 f3 guid 153CBA4AEE5C64EB #txt
Ds0 f3 type ch.ivy.addon.portalkit.test.DataCreation.DataCreationData #txt
Ds0 f3 actionDecl 'ch.ivy.addon.portalkit.test.DataCreation.DataCreationData out;
' #txt
Ds0 f3 actionTable 'out=in;
' #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ds0 f3 149 85 22 22 14 0 #rect
Ds0 f3 @|RichDialogProcessStartIcon #fIcon
Ds0 f4 type ch.ivy.addon.portalkit.test.DataCreation.DataCreationData #txt
Ds0 f4 guid 153CBA4AEE5B7162 #txt
Ds0 f4 149 213 22 22 14 0 #rect
Ds0 f4 @|RichDialogEndIcon #fIcon
Ds0 f5 expr out #txt
Ds0 f5 160 107 160 213 #arcP
>Proto Ds0 .type ch.ivy.addon.portalkit.test.DataCreation.DataCreationData #txt
>Proto Ds0 .processKind HTML_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f0 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
Ds0 f3 mainOut f5 tail #connect
Ds0 f5 head f4 mainIn #connect

[Ivy]
[>Created: Wed Feb 11 14:16:35 ICT 2015]
14B7775CB7A22AF4 3.17 #module
>Proto >Proto Collection #zClass
Cs0 ConfirmationProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogProcessStart f3 '' #zField
Cs0 @RichDialogEnd f6 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @RichDialogProcessStart f5 '' #zField
Cs0 @RichDialogEnd f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
>Proto Cs0 Cs0 ConfirmationProcess #zField
Cs0 f0 guid 14B7775CB8651CD0 #txt
Cs0 f0 type ch.ivy.addon.portalkit.test.Confirmation.ConfirmationData #txt
Cs0 f0 method start(String,String) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String message,java.lang.String title> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.message=param.message;
out.title=param.title;
' #txt
Cs0 f0 outParameterDecl '<java.lang.Boolean approved> result;
' #txt
Cs0 f0 outParameterMapAction 'result.approved=in.approved;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 86 54 20 20 13 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.test.Confirmation.ConfirmationData #txt
Cs0 f1 86 150 20 20 13 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 96 74 96 150 #arcP
Cs0 f3 guid 14B7779EAF2248BE #txt
Cs0 f3 type ch.ivy.addon.portalkit.test.Confirmation.ConfirmationData #txt
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.test.Confirmation.ConfirmationData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'out.approved = true;' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onClickYes</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 278 54 20 20 13 0 #rect
Cs0 f3 @|RichDialogProcessStartIcon #fIcon
Cs0 f6 type ch.ivy.addon.portalkit.test.Confirmation.ConfirmationData #txt
Cs0 f6 guid 14B777A35D06E7DE #txt
Cs0 f6 278 150 20 20 13 0 #rect
Cs0 f6 @|RichDialogEndIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 288 74 288 150 #arcP
Cs0 f5 guid 14B777E67484A564 #txt
Cs0 f5 type ch.ivy.addon.portalkit.test.Confirmation.ConfirmationData #txt
Cs0 f5 actionDecl 'ch.ivy.addon.portalkit.test.Confirmation.ConfirmationData out;
' #txt
Cs0 f5 actionTable 'out=in;
' #txt
Cs0 f5 actionCode 'out.approved = false;' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onClickNo</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f5 406 54 20 20 13 0 #rect
Cs0 f5 @|RichDialogProcessStartIcon #fIcon
Cs0 f7 type ch.ivy.addon.portalkit.test.Confirmation.ConfirmationData #txt
Cs0 f7 guid 14B777ED8FED856C #txt
Cs0 f7 406 150 20 20 13 0 #rect
Cs0 f7 @|RichDialogEndIcon #fIcon
Cs0 f8 expr out #txt
Cs0 f8 416 74 416 150 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.test.Confirmation.ConfirmationData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f4 tail #connect
Cs0 f4 head f6 mainIn #connect
Cs0 f5 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect

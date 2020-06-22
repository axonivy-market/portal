[Ivy]
172CAF291EB6C4D8 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CreateTaskWidgetDialogProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @UdEvent f3 '' #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @UdMethod f6 '' #zField
Cs0 @UdProcessEnd f7 '' #zField
Cs0 @GridStep f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f8 '' #zField
>Proto Cs0 Cs0 CreateTaskWidgetDialogProcess #zField
Cs0 f0 guid 172CAF291F5C429A #txt
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
Cs0 f0 83 51 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 211 51 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f2 109 64 211 64 #arcP
Cs0 f3 guid 172CAF291FE24DF8 #txt
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
Cs0 f6 guid 172CC049A51294A8 #txt
Cs0 f6 method initialize(ch.ivy.addon.portalkit.dto.TaskDashboardWidget) #txt
Cs0 f6 inParameterDecl '<ch.ivy.addon.portalkit.dto.TaskDashboardWidget widget> param;' #txt
Cs0 f6 inParameterMapAction 'out.widget=param.widget;
' #txt
Cs0 f6 outParameterDecl '<> result;' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initialize(TaskDashboardWidget)</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 243 26 26 -25 15 #rect
Cs0 f6 @|UdMethodIcon #fIcon
Cs0 f7 339 243 26 26 0 12 #rect
Cs0 f7 @|UdProcessEndIcon #fIcon
Cs0 f9 actionTable 'out=in;
' #txt
Cs0 f9 actionCode 'in.widget.id = null;' #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set ID to null</name>
    </language>
</elementInfo>
' #txt
Cs0 f9 168 234 112 44 -34 -8 #rect
Cs0 f9 @|StepIcon #fIcon
Cs0 f10 109 256 168 256 #arcP
Cs0 f8 280 256 339 256 #arcP
>Proto Cs0 .type ch.ivy.addon.portal.generic.prototype.CreateTaskWidgetDialog.CreateTaskWidgetDialogData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f6 mainOut f10 tail #connect
Cs0 f10 head f9 mainIn #connect
Cs0 f9 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect

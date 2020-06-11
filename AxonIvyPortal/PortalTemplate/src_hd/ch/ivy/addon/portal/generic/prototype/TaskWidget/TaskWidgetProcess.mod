[Ivy]
1706725B0593DA17 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskWidgetProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @UdEvent f3 '' #zField
Ts0 @UdExitEnd f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @UdProcessEnd f7 '' #zField
Ts0 @CallSub f8 '' #zField
Ts0 @PushWFArc f9 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @GridStep f11 '' #zField
Ts0 @PushWFArc f12 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @UdMethod f13 '' #zField
Ts0 @UdProcessEnd f14 '' #zField
Ts0 @PushWFArc f15 '' #zField
>Proto Ts0 Ts0 TaskWidgetProcess #zField
Ts0 f0 guid 1706725B11A36A72 #txt
Ts0 f0 method start() #txt
Ts0 f0 inParameterDecl '<> param;' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 51 26 26 -16 15 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 339 51 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f3 guid 1706725B136A149D #txt
Ts0 f3 actionTable 'out=in;
' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 83 147 26 26 -15 15 #rect
Ts0 f3 @|UdEventIcon #fIcon
Ts0 f4 211 147 26 26 0 12 #rect
Ts0 f4 @|UdExitEndIcon #fIcon
Ts0 f5 109 160 211 160 #arcP
Ts0 f6 guid 170672A73A55F17D #txt
Ts0 f6 method initialize() #txt
Ts0 f6 inParameterDecl '<> param;' #txt
Ts0 f6 outParameterDecl '<> result;' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initialize()</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 243 26 26 -25 15 #rect
Ts0 f6 @|UdMethodIcon #fIcon
Ts0 f7 403 243 26 26 0 12 #rect
Ts0 f7 @|UdProcessEndIcon #fIcon
Ts0 f8 processCall 'Ivy Data Processes/TaskService:findTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria,Integer,Integer)' #txt
Ts0 f8 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria,Integer startIndex,Integer count> param;' #txt
Ts0 f8 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
param.taskSearchCriteria.adminQuery=true;
param.startIndex=0;
param.count=-1;
' #txt
Ts0 f8 responseMappingAction 'out=in;
out.tasks=result.tasks;
' #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Ivy Data Processes/TaskService</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 160 234 192 44 -87 -8 #rect
Ts0 f8 @|CallSubIcon #fIcon
Ts0 f9 109 256 160 256 #arcP
Ts0 f10 352 256 403 256 #arcP
Ts0 f11 actionTable 'out=in;
' #txt
Ts0 f11 actionCode 'import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
in.taskSearchCriteria = new TaskSearchCriteria();


in.columns.add("Prio");
in.columns.add("Task Name");
in.columns.add("Description");' #txt
Ts0 f11 168 42 112 44 0 -8 #rect
Ts0 f11 @|StepIcon #fIcon
Ts0 f12 109 64 168 64 #arcP
Ts0 f2 280 64 339 64 #arcP
Ts0 f13 guid 1707B2749415710E #txt
Ts0 f13 method addColumn(String) #txt
Ts0 f13 inParameterDecl '<String name> param;' #txt
Ts0 f13 inActionCode out.columns.add(param.name); #txt
Ts0 f13 outParameterDecl '<> result;' #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addColumn(String)</name>
    </language>
</elementInfo>
' #txt
Ts0 f13 83 371 26 26 -25 15 #rect
Ts0 f13 @|UdMethodIcon #fIcon
Ts0 f14 403 371 26 26 0 12 #rect
Ts0 f14 @|UdProcessEndIcon #fIcon
Ts0 f15 109 384 403 384 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.prototype.TaskWidget.TaskWidgetData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f6 mainOut f9 tail #connect
Ts0 f9 head f8 mainIn #connect
Ts0 f8 mainOut f10 tail #connect
Ts0 f10 head f7 mainIn #connect
Ts0 f0 mainOut f12 tail #connect
Ts0 f12 head f11 mainIn #connect
Ts0 f11 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f13 mainOut f15 tail #connect
Ts0 f15 head f14 mainIn #connect

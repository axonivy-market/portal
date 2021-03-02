[Ivy]
1706725B0593DA17 9.2.0 #module
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
Ts0 @PushWFArc f2 '' #zField
Ts0 @CallSub f7 '' #zField
Ts0 @UdMethod f3 '' #zField
Ts0 @PushWFArc f4 '' #zField
>Proto Ts0 Ts0 TaskWidgetProcess #zField
Ts0 f0 guid 1706725B11A36A72 #txt
Ts0 f0 method start() #txt
Ts0 f0 inParameterDecl '<> param;' #txt
Ts0 f0 inParameterMapAction 'out.currentPortalPage="DASHBOARD";
' #txt
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
Ts0 f1 323 51 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f2 109 64 323 64 #arcP
Ts0 f7 processCall 'Functional Processes/OpenPortalTaskDetailsHook:callFromDashboard(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean)' #txt
Ts0 f7 requestActionDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Ts0 f7 requestMappingAction 'param.task=in.selectedTask;
param.dataModel=in.dataModel;
param.portalPage=ch.ivy.addon.portalkit.enums.PortalPage.valueOf(in.currentPortalPage);
param.isFromTaskList=true;
' #txt
Ts0 f7 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItem.TaskItemData out;
' #txt
Ts0 f7 responseMappingAction 'out=in;
' #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTaskDetails</name>
    </language>
</elementInfo>
' #txt
Ts0 f7 320 138 144 44 -64 -8 #rect
Ts0 f7 @|CallSubIcon #fIcon
Ts0 f3 guid 1773DE3D406A808F #txt
Ts0 f3 method openDetails(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel) #txt
Ts0 f3 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel dataModel> param;' #txt
Ts0 f3 inParameterMapAction 'out.dataModel=param.dataModel;
out.selectedTask=param.task;
' #txt
Ts0 f3 outParameterDecl '<> result;' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openDetails(ITask,DashboardTaskLazyDataModel)</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 83 147 26 26 -25 15 #rect
Ts0 f3 @|UdMethodIcon #fIcon
Ts0 f4 109 160 320 160 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.dashboard.TaskWidget.TaskWidgetData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f3 mainOut f4 tail #connect
Ts0 f4 head f7 mainIn #connect

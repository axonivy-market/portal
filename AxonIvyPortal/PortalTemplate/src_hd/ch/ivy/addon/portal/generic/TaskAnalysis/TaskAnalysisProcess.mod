[Ivy]
163DD37A38FA2BC8 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskAnalysisProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @UdEvent f3 '' #zField
Ts0 @UdExitEnd f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @GridStep f6 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @PushWFArc f2 '' #zField
>Proto Ts0 Ts0 TaskAnalysisProcess #zField
Ts0 f0 guid 163DD37A3BC5BFD2 #txt
Ts0 f0 method start(String) #txt
Ts0 f0 inParameterDecl '<String menuState> param;' #txt
Ts0 f0 inParameterMapAction 'out.menuState=param.menuState;
' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 51 26 26 -16 15 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 339 51 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f3 guid 163DD37A3E43A70E #txt
Ts0 f3 actionTable 'out=in;
' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 83 147 26 26 -15 12 #rect
Ts0 f3 @|UdEventIcon #fIcon
Ts0 f4 211 147 26 26 0 12 #rect
Ts0 f4 @|UdExitEndIcon #fIcon
Ts0 f5 expr out #txt
Ts0 f5 109 160 211 160 #arcP
Ts0 f6 actionTable 'out=in;
' #txt
Ts0 f6 actionCode 'import ch.ivy.addon.portal.generic.view.TaskAnalysisView;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.datamodel.TaskAnalysisLazyDataModel;
	
in.menuState = ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
TaskAnalysisLazyDataModel dataModel = new TaskAnalysisLazyDataModel();
dataModel.getCriteria().setNewQueryCreated(true);
dataModel.setAdminQuery(true);
dataModel.setSortField(TaskSortField.PRIORITY.toString(), false);
	
String pageTitle = ivy.cms.co("/Labels/Task");	
String noTaskMessage = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskWarning/taskNotFound");
out.taskView = TaskAnalysisView.create().dataModel(dataModel).pageTitle(pageTitle).hideTaskFilter(false).showHeaderToolbar(false).noTaskFoundMessage(noTaskMessage).chunkSize(20).createNewTaskView();
' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 168 42 112 44 -8 -8 #rect
Ts0 f6 @|StepIcon #fIcon
Ts0 f7 expr out #txt
Ts0 f7 109 64 168 64 #arcP
Ts0 f2 expr out #txt
Ts0 f2 280 64 339 64 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.TaskAnalysis.TaskAnalysisData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f0 mainOut f7 tail #connect
Ts0 f7 head f6 mainIn #connect
Ts0 f6 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect

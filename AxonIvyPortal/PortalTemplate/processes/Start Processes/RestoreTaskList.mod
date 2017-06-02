[Ivy]
[>Created: Fri Jun 02 17:34:48 ICT 2017]
15C685B434B201D6 3.20 #module
>Proto >Proto Collection #zClass
Rt0 RestoreTaskList Big #zClass
Rt0 B #cInfo
Rt0 #process
Rt0 @TextInP .resExport .resExport #zField
Rt0 @TextInP .type .type #zField
Rt0 @TextInP .processKind .processKind #zField
Rt0 @AnnotationInP-0n ai ai #zField
Rt0 @MessageFlowInP-0n messageIn messageIn #zField
Rt0 @MessageFlowOutP-0n messageOut messageOut #zField
Rt0 @TextInP .xml .xml #zField
Rt0 @TextInP .responsibility .responsibility #zField
Rt0 @StartRequest f25 '' #zField
Rt0 @GridStep f4 '' #zField
Rt0 @CallSub f35 '' #zField
Rt0 @PushWFArc f26 '' #zField
Rt0 @PushWFArc f0 '' #zField
>Proto Rt0 Rt0 RestoreTaskList #zField
Rt0 f25 outLink restorePortalTaskList.ivp #txt
Rt0 f25 type ch.ivy.addon.portal.generic.PortalStartData #txt
Rt0 f25 inParamDecl '<> param;' #txt
Rt0 f25 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Rt0 f25 guid 15C685E3B5D62746 #txt
Rt0 f25 requestEnabled true #txt
Rt0 f25 triggerEnabled false #txt
Rt0 f25 callSignature restorePortalTaskList() #txt
Rt0 f25 persist false #txt
Rt0 f25 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Rt0 f25 caseData businessCase.attach=true #txt
Rt0 f25 showInStartList 0 #txt
Rt0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>restorePortalTaskList.ivp</name>
        <nameStyle>25,5,7
</nameStyle>
        <desc>Display the same task list after task is finished</desc>
    </language>
</elementInfo>
' #txt
Rt0 f25 @C|.responsibility Everybody #txt
Rt0 f25 78 113 30 30 -49 15 #rect
Rt0 f25 @|StartRequestIcon #fIcon
Rt0 f4 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Rt0 f4 actionTable 'out=in;
' #txt
Rt0 f4 actionCode 'import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portal.generic.navigation.PortalPage;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

if (SecurityServiceUtils.getSessionAttribute(SessionAttribute.LAST_PAGE.toString()) is initialized) {
	in.dataModel = SecurityServiceUtils.getSessionAttribute(SessionAttribute.TASK_DATA_MODEL.toString()) as TaskLazyDataModel;
	in.portalPage = in.#dataModel is initialized ? in.portalPage = SecurityServiceUtils.getSessionAttribute(SessionAttribute.LAST_PAGE.toString()) as PortalPage : PortalPage.HOME_PAGE;
	
	SecurityServiceUtils.removeSessionAttribute(SessionAttribute.TASK_DATA_MODEL.toString());
} else {
	in.portalPage = PortalPage.HOME_PAGE;
	SecurityServiceUtils.setSessionAttribute(SessionAttribute.LAST_PAGE.toString(), in.portalPage);
}' #txt
Rt0 f4 security system #txt
Rt0 f4 type ch.ivy.addon.portal.generic.PortalStartData #txt
Rt0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Recover task
list''s configuration</name>
        <nameStyle>33
</nameStyle>
    </language>
</elementInfo>
' #txt
Rt0 f4 243 116 36 24 20 -2 #rect
Rt0 f4 @|StepIcon #fIcon
Rt0 f35 type ch.ivy.addon.portal.generic.PortalStartData #txt
Rt0 f35 processCall 'Functional Processes/PortalStartPageCallable:call(ch.ivy.addon.portal.generic.PortalStartData)' #txt
Rt0 f35 doCall true #txt
Rt0 f35 requestActionDecl '<ch.ivy.addon.portal.generic.PortalStartData data> param;
' #txt
Rt0 f35 requestMappingAction 'param.data=in;
' #txt
Rt0 f35 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Rt0 f35 responseMappingAction 'out=in;
' #txt
Rt0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalStartPageCallable</name>
    </language>
</elementInfo>
' #txt
Rt0 f35 417 106 144 44 -66 -8 #rect
Rt0 f35 @|CallSubIcon #fIcon
Rt0 f26 expr out #txt
Rt0 f26 108 128 243 128 #arcP
Rt0 f26 0 0.5369666661485084 0 0 #arcLabel
Rt0 f0 expr out #txt
Rt0 f0 279 128 417 128 #arcP
>Proto Rt0 .type ch.ivy.addon.portal.generic.PortalStartData #txt
>Proto Rt0 .processKind NORMAL #txt
>Proto Rt0 0 0 32 24 18 0 #rect
>Proto Rt0 @|BIcon #fIcon
Rt0 f25 mainOut f26 tail #connect
Rt0 f26 head f4 mainIn #connect
Rt0 f4 mainOut f0 tail #connect
Rt0 f0 head f35 mainIn #connect

[Ivy]
16E1584824AEC16C 9.2.0 #module
>Proto >Proto Collection #zClass
Bs0 TaskTemplateIFrameProcess Big #zClass
Bs0 RD #cInfo
Bs0 #process
Bs0 @TextInP .type .type #zField
Bs0 @TextInP .processKind .processKind #zField
Bs0 @TextInP .xml .xml #zField
Bs0 @TextInP .responsibility .responsibility #zField
Bs0 @UdInit f0 '' #zField
Bs0 @UdProcessEnd f1 '' #zField
Bs0 @GridStep f3 '' #zField
Bs0 @PushWFArc f4 '' #zField
Bs0 @PushWFArc f2 '' #zField
Bs0 @UdEvent f5 '' #zField
Bs0 @UdProcessEnd f6 '' #zField
Bs0 @GridStep f7 '' #zField
Bs0 @PushWFArc f8 '' #zField
Bs0 @PushWFArc f9 '' #zField
>Proto Bs0 Bs0 TaskTemplateIFrameProcess #zField
Bs0 f0 guid 16E11EC8F88D54A6 #txt
Bs0 f0 method start(String,Number) #txt
Bs0 f0 inParameterDecl '<String url,Number taskId> param;' #txt
Bs0 f0 inParameterMapAction 'out.taskId=param.taskId;
out.url=param.url;
' #txt
Bs0 f0 outParameterDecl '<> result;' #txt
Bs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String,Number)</name>
    </language>
</elementInfo>
' #txt
Bs0 f0 83 51 26 26 -15 15 #rect
Bs0 f1 339 51 26 26 0 12 #rect
Bs0 f3 actionTable 'out=in;
' #txt
Bs0 f3 actionCode 'out.task = ivy.wf.findTask(in.taskId);' #txt
Bs0 f3 security system #txt
Bs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find task by id</name>
    </language>
</elementInfo>
' #txt
Bs0 f3 200 42 112 44 -37 -8 #rect
Bs0 f4 109 64 200 64 #arcP
Bs0 f2 312 64 339 64 #arcP
Bs0 f5 guid 17891D5D3838F795 #txt
Bs0 f5 actionTable 'out=in;
' #txt
Bs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>displayPortalGrowlMessage</name>
    </language>
</elementInfo>
' #txt
Bs0 f5 83 115 26 26 -14 15 #rect
Bs0 f6 339 115 26 26 0 12 #rect
Bs0 f7 actionTable 'out=in;
' #txt
Bs0 f7 actionCode 'import javax.faces.context.ExternalContext;
import ch.ivy.addon.portal.generic.bean.IFrameTaskTemplateBean;
import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import ch.ivy.addon.portalkit.util.GrowlMessageUtils;
import ch.ivyteam.ivy.workflow.ITask;
import javax.faces.context.FacesContext;
ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

String taskId = externalContext.getRequestParameterMap().get(IFrameTaskTemplateBean.TASK_ID_PARAM).toString();
boolean overridePortalGrowl = Boolean.valueOf(externalContext.getRequestParameterMap().get(GrowlMessageUtils.OVERRIDE_PORTAL_GROWL).toString());
if (overridePortalGrowl) {
	String portalGlobalGrowlMessage = externalContext.getRequestParameterMap().get(IFrameTaskTemplateBean.PORTAL_GROWL_MESSGE_PARAM).toString();

	FacesMessage message = new FacesMessage(portalGlobalGrowlMessage, "");
	FacesContext.getCurrentInstance().addMessage(GrowlMessageUtils.PORTAL_GLOBAL_GROWL_MESSAGE, message);

	Flash flash = externalContext.getFlash();
	flash.put(GrowlMessageUtils.OVERRIDE_PORTAL_GROWL, true);
}

ITask task = ivy.wf.findTask(Long.valueOf(taskId));
boolean isTaskFinished = #task is initialized && task.getEndTimestamp() is initialized;
GrowlMessageUtils.addFeedbackMessage(isTaskFinished, task.getCase());' #txt
Bs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message</name>
    </language>
</elementInfo>
' #txt
Bs0 f7 200 106 112 44 -48 -8 #rect
Bs0 f8 109 128 200 128 #arcP
Bs0 f9 312 128 339 128 #arcP
>Proto Bs0 .type ch.ivy.addon.portal.generic.iframe.TaskTemplateIFrame.TaskTemplateIFrameData #txt
>Proto Bs0 .processKind HTML_DIALOG #txt
>Proto Bs0 -8 -8 16 16 16 26 #rect
Bs0 f0 mainOut f4 tail #connect
Bs0 f4 head f3 mainIn #connect
Bs0 f3 mainOut f2 tail #connect
Bs0 f2 head f1 mainIn #connect
Bs0 f5 mainOut f8 tail #connect
Bs0 f8 head f7 mainIn #connect
Bs0 f7 mainOut f9 tail #connect
Bs0 f9 head f6 mainIn #connect

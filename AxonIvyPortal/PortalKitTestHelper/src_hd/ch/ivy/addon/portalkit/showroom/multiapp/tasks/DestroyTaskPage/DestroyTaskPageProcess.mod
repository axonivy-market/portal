[Ivy]
1559AE0A76284C44 7.5.0 #module
>Proto >Proto Collection #zClass
Ds0 DestroyTaskPageProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @UdInit f0 '' #zField
Ds0 @UdProcessEnd f1 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @UdEvent f3 '' #zField
Ds0 @UdExitEnd f4 '' #zField
Ds0 @PushWFArc f5 '' #zField
Ds0 @UdMethod f6 '' #zField
Ds0 @UdProcessEnd f7 '' #zField
Ds0 @GridStep f9 '' #zField
Ds0 @PushWFArc f10 '' #zField
Ds0 @PushWFArc f8 '' #zField
>Proto Ds0 Ds0 DestroyTaskPageProcess #zField
Ds0 f0 guid 1559AE0A7933579C #txt
Ds0 f0 method start() #txt
Ds0 f0 inParameterDecl '<> param;' #txt
Ds0 f0 outParameterDecl '<> result;' #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ds0 f0 53 85 22 22 14 0 #rect
Ds0 f0 @|UdInitIcon #fIcon
Ds0 f1 53 213 22 22 14 0 #rect
Ds0 f1 @|UdProcessEndIcon #fIcon
Ds0 f2 expr out #txt
Ds0 f2 64 107 64 213 #arcP
Ds0 f3 guid 1559AE0A7AEA915D #txt
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
Ds0 f3 @|UdEventIcon #fIcon
Ds0 f4 149 213 22 22 14 0 #rect
Ds0 f4 @|UdExitEndIcon #fIcon
Ds0 f5 expr out #txt
Ds0 f5 160 107 160 213 #arcP
Ds0 f6 guid 1559AE82C10CF58D #txt
Ds0 f6 method destroyTask(java.lang.Long) #txt
Ds0 f6 inParameterDecl '<Long taskId> param;' #txt
Ds0 f6 outParameterDecl '<> result;' #txt
Ds0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>destroyTask(Long)</name>
    </language>
</elementInfo>
' #txt
Ds0 f6 245 85 22 22 14 0 #rect
Ds0 f6 @|UdMethodIcon #fIcon
Ds0 f7 245 213 22 22 14 0 #rect
Ds0 f7 @|UdProcessEndIcon #fIcon
Ds0 f9 actionTable 'out=in;
' #txt
Ds0 f9 actionCode 'import ch.ivyteam.ivy.workflow.ITask;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.ISecurityDescriptor;

ISecurityDescriptor securityDescriptor = ivy.request.getApplication().getSecurityDescriptor();
if(ivy.session.hasPermission(securityDescriptor,ch.ivyteam.ivy.security.IPermission.TASK_READ) ){
			ITask task = ivy.wf.findTask(in.taskId);
			if(task != null) {
			if(ivy.session.hasPermission(securityDescriptor,ch.ivyteam.ivy.security.IPermission.TASK_DESTROY) && (task.getState().toString() != "DESTROYED")) {
			ivy.wf.findTask(in.taskId).destroy();	
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Info: ","Successful!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Warning!","You don''t have permission or task state is Destroyed"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Warning!","Task is not exist"));
		}
	} else {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Warning!","You don''t have permission"));
}
' #txt
Ds0 f9 238 148 36 24 20 -2 #rect
Ds0 f9 @|StepIcon #fIcon
Ds0 f10 expr out #txt
Ds0 f10 256 107 256 148 #arcP
Ds0 f8 expr out #txt
Ds0 f8 256 172 256 213 #arcP
>Proto Ds0 .type ch.ivy.addon.portalkit.showroom.multiapp.tasks.DestroyTaskPage.DestroyTaskPageData #txt
>Proto Ds0 .processKind HTML_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f0 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
Ds0 f3 mainOut f5 tail #connect
Ds0 f5 head f4 mainIn #connect
Ds0 f6 mainOut f10 tail #connect
Ds0 f10 head f9 mainIn #connect
Ds0 f9 mainOut f8 tail #connect
Ds0 f8 head f7 mainIn #connect

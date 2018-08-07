[Ivy]
[>Created: Mon Jul 06 15:37:09 ICT 2015]
14C6E9367EBFCE20 3.17 #module
>Proto >Proto Collection #zClass
Cs0 CaseStatusInformationProcess Big #zClass
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
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @GridStep f3 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @RichDialogMethodStart f5 '' #zField
Cs0 @RichDialogProcessEnd f6 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @RichDialogProcessStart f8 '' #zField
Cs0 @RichDialogProcessEnd f9 '' #zField
Cs0 @GridStep f11 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @RichDialogMethodStart f13 '' #zField
Cs0 @RichDialogProcessEnd f14 '' #zField
Cs0 @GridStep f16 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @PushWFArc f15 '' #zField
Cs0 @RichDialogMethodStart f18 '' #zField
Cs0 @RichDialogProcessEnd f19 '' #zField
Cs0 @GridStep f21 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @PushWFArc f20 '' #zField
>Proto Cs0 Cs0 CaseStatusInformationProcess #zField
Cs0 f1 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f1 86 182 20 20 13 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'import ch.ivy.addon.portalkit.bo.Contact;
import ch.ivy.addon.portalkit.util.ConverterUtils;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
in.caseInfo = CaseUtils.getCaseById(in.caseId);

String email = CaseUtils.getEmailAddress(in.caseInfo);

in.contact = new Contact(email);
in.contact.phone = ivy.session.getSessionUser().getProperty(UserUtils.PHONE);
in.contact.mobilePhone = ivy.session.getSessionUser().getProperty(UserUtils.MOBILE);
if(in.caseInfo!=null){
	  in.tasks= TaskUtils.filterCurrentTasksByCase(in.caseInfo);
	  if(in.caseInfo.getCreatorUser()!=null){
			in.creatorName = CaseUtils.getDisplayNameInFormat(in.caseInfo.getCreatorUser().getDisplayName(),in.caseInfo.getCreatorUserName());
		}
}


' #txt
Cs0 f3 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f3 78 116 36 24 20 -2 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 96 140 96 182 #arcP
Cs0 f0 guid 14C6F34C8BCE860B #txt
Cs0 f0 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f0 method start(Number) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Number caseId> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Number)</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 86 46 20 20 13 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 96 66 96 116 #arcP
Cs0 f5 guid 14CC007D031BC3B4 #txt
Cs0 f5 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f5 method taskDetail(ch.ivyteam.ivy.workflow.ICase,java.lang.Long) #txt
Cs0 f5 disableUIEvents false #txt
Cs0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ICase iCase,java.lang.Long taskId> param = methodEvent.getInputArguments();
' #txt
Cs0 f5 inActionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
out.selectedTask = TaskUtils.convertToITask(param.iCase,param.taskId);' #txt
Cs0 f5 outParameterDecl '<> result;
' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>taskDetail(ICase,Long)</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f5 294 46 20 20 13 0 #rect
Cs0 f5 @|RichDialogMethodStartIcon #fIcon
Cs0 f6 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f6 294 182 20 20 13 0 #rect
Cs0 f6 @|RichDialogProcessEndIcon #fIcon
Cs0 f7 expr out #txt
Cs0 f7 304 66 304 182 #arcP
Cs0 f8 guid 14D230A03E3B7AB6 #txt
Cs0 f8 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f8 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData out;
' #txt
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 502 46 20 20 13 0 #rect
Cs0 f8 @|RichDialogProcessStartIcon #fIcon
Cs0 f9 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f9 502 174 20 20 13 0 #rect
Cs0 f9 @|RichDialogProcessEndIcon #fIcon
Cs0 f11 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData out;
' #txt
Cs0 f11 actionTable 'out=in;
' #txt
Cs0 f11 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

in.tasks= TaskUtils.filterCurrentTasksByCase(in.caseInfo);
' #txt
Cs0 f11 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reload data when 
dialog close</name>
        <nameStyle>17,7
1,7
12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f11 494 108 36 24 20 -17 #rect
Cs0 f11 @|StepIcon #fIcon
Cs0 f12 expr out #txt
Cs0 f12 512 66 512 108 #arcP
Cs0 f10 expr out #txt
Cs0 f10 512 132 512 174 #arcP
Cs0 f13 guid 14E626D79294A4F4 #txt
Cs0 f13 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f13 method selectTaskShowNow(ch.ivy.addon.portalkit.vo.TaskVO,ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f13 disableUIEvents false #txt
Cs0 f13 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.vo.TaskVO taskVo,ch.ivyteam.ivy.workflow.ICase caseInfo> param = methodEvent.getInputArguments();
' #txt
Cs0 f13 inActionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
out.selectedTask = TaskUtils.convertToITask(param.caseInfo,param.taskVo.id);' #txt
Cs0 f13 outParameterDecl '<> result;
' #txt
Cs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>selectTaskShowNow(TaskVO)</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f13 293 261 22 22 14 0 #rect
Cs0 f13 @|RichDialogMethodStartIcon #fIcon
Cs0 f14 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f14 293 437 22 22 14 0 #rect
Cs0 f14 @|RichDialogProcessEndIcon #fIcon
Cs0 f16 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData out;
' #txt
Cs0 f16 actionTable 'out=in;
' #txt
Cs0 f16 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import org.primefaces.context.RequestContext;
RequestContext.getCurrentInstance().execute("PF(''confirmation'').show()");' #txt
Cs0 f16 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>confirm show now</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f16 286 340 36 24 20 -2 #rect
Cs0 f16 @|StepIcon #fIcon
Cs0 f17 expr out #txt
Cs0 f17 304 283 304 340 #arcP
Cs0 f15 expr out #txt
Cs0 f15 304 364 304 437 #arcP
Cs0 f18 guid 14E6278389D1D4FC #txt
Cs0 f18 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f18 method submitResetTask() #txt
Cs0 f18 disableUIEvents false #txt
Cs0 f18 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f18 outParameterDecl '<> result;
' #txt
Cs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>submitResetTask()</name>
    </language>
</elementInfo>
' #txt
Cs0 f18 277 485 22 22 14 0 #rect
Cs0 f18 @|RichDialogMethodStartIcon #fIcon
Cs0 f19 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f19 277 613 22 22 14 0 #rect
Cs0 f19 @|RichDialogProcessEndIcon #fIcon
Cs0 f21 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData out;
' #txt
Cs0 f21 actionTable 'out=in;
' #txt
Cs0 f21 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
TaskUtils.removeTaskDelay(in.selectedTask);' #txt
Cs0 f21 type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
Cs0 f21 270 548 36 24 20 -2 #rect
Cs0 f21 @|StepIcon #fIcon
Cs0 f22 expr out #txt
Cs0 f22 288 507 288 548 #arcP
Cs0 f20 expr out #txt
Cs0 f20 288 572 288 613 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.singleapp.cases.CaseStatusInformation.CaseStatusInformationData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>starts</swimlaneLabel>
        <swimlaneLabel>methods</swimlaneLabel>
        <swimlaneLabel>events</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>248</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-3342388</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f0 mainOut f4 tail #connect
Cs0 f4 head f3 mainIn #connect
Cs0 f5 mainOut f7 tail #connect
Cs0 f7 head f6 mainIn #connect
Cs0 f8 mainOut f12 tail #connect
Cs0 f12 head f11 mainIn #connect
Cs0 f11 mainOut f10 tail #connect
Cs0 f10 head f9 mainIn #connect
Cs0 f13 mainOut f17 tail #connect
Cs0 f17 head f16 mainIn #connect
Cs0 f16 mainOut f15 tail #connect
Cs0 f15 head f14 mainIn #connect
Cs0 f18 mainOut f22 tail #connect
Cs0 f22 head f21 mainIn #connect
Cs0 f21 mainOut f20 tail #connect
Cs0 f20 head f19 mainIn #connect

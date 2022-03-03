[Ivy]
17982F86EF698C57 9.4.0 #module
>Proto >Proto Collection #zClass
Ps0 ProcessInformationProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @CallSub f7 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @GridStep f6 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @PushWFArc f2 '' #zField
>Proto Ps0 Ps0 ProcessInformationProcess #zField
Ps0 f0 guid 17982F86F32B7C49 #txt
Ps0 f0 method start(String) #txt
Ps0 f0 inParameterDecl '<String processId> param;' #txt
Ps0 f0 inParameterMapAction 'out.processId=param.processId;
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 84 52 24 24 -16 15 #rect
Ps0 f1 595 51 26 26 0 12 #rect
Ps0 f3 guid 17982F86F5BE8724 #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 147 26 26 -15 15 #rect
Ps0 f4 211 147 26 26 0 12 #rect
Ps0 f5 109 160 211 160 #arcP
Ps0 f7 processCall 'Ivy Data Processes/ProcessService:findProcesses()' #txt
Ps0 f7 requestActionDecl '<> param;' #txt
Ps0 f7 responseActionDecl 'ch.ivy.addon.portalkit.component.CompactProcessWidget.CompactProcessWidgetData out;
' #txt
Ps0 f7 responseMappingAction 'out=in;
out.userProcesses=result.processes;
' #txt
Ps0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ProcessService</name>
    </language>
</elementInfo>
' #txt
Ps0 f7 208 42 112 44 -43 -8 #rect
Ps0 f8 108 64 208 64 #arcP
Ps0 f6 actionTable 'out=in;
' #txt
Ps0 f6 actionCode 'import ch.ivy.addon.portal.generic.util.ProcessStepUtils;
import ch.ivy.addon.portalkit.mapper.UserProcessMapper;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

for (IWebStartable process : in.userProcesses) {
  if (process.getId().contentEquals(in.processId)) {
    in.setSelectedProcess(UserProcessMapper.toUserProcess(process));
  }
}

if (in.selectedProcess is initialized) {
  in.processSteps = ProcessStepUtils.findProcessStepsOfProcess(in.selectedProcess);
}' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get selected&#13;
user process</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 384 42 112 44 -36 -16 #rect
Ps0 f9 320 64 384 64 #arcP
Ps0 f2 496 64 595 64 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.component.ProcessInformation.ProcessInformationData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f0 mainOut f8 tail #connect
Ps0 f8 head f7 mainIn #connect
Ps0 f7 mainOut f9 tail #connect
Ps0 f9 head f6 mainIn #connect
Ps0 f6 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect

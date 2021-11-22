[Ivy]
1799D21E68885CA5 9.3.1 #module
>Proto >Proto Collection #zClass
Cs0 CustomizedProcessInformationProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @CallSub f7 '' #zField
Cs0 @GridStep f6 '' #zField
Cs0 @UdEvent f3 '' #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @PushWFArc f5 '' #zField
>Proto Cs0 Cs0 CustomizedProcessInformationProcess #zField
Cs0 f7 processCall 'Ivy Data Processes/ProcessService:findProcesses()' #txt
Cs0 f7 requestActionDecl '<> param;' #txt
Cs0 f7 responseActionDecl 'ch.ivy.addon.portalkit.component.CompactProcessWidget.CompactProcessWidgetData out;
' #txt
Cs0 f7 responseMappingAction 'out=in;
out.userProcesses=result.processes;
' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ProcessService</name>
    </language>
</elementInfo>
' #txt
Cs0 f7 208 42 112 44 -43 -8 #rect
Cs0 f6 actionTable 'out=in;
' #txt
Cs0 f6 actionCode 'import ch.ivy.addon.portal.generic.util.ProcessStepUtils;
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
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get selected&#13;
user process</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 384 42 112 44 -36 -16 #rect
Cs0 f3 guid 1799D2EF1BD10786 #txt
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
Cs0 f0 guid 1799D2EF1BDA68E9 #txt
Cs0 f0 method start(String) #txt
Cs0 f0 inParameterDecl '<String processId> param;' #txt
Cs0 f0 inParameterMapAction 'out.processId=param.processId;
' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 84 52 24 24 -16 15 #rect
Cs0 f4 211 147 26 26 0 12 #rect
Cs0 f1 595 51 26 26 0 12 #rect
Cs0 f9 320 64 384 64 #arcP
Cs0 f8 108 64 208 64 #arcP
Cs0 f2 496 64 595 64 #arcP
Cs0 f5 109 160 211 160 #arcP
>Proto Cs0 .type com.axonivy.portal.developerexamples.customization.CustomizedProcessInformation.CustomizedProcessInformationData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f0 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
Cs0 f7 mainOut f9 tail #connect
Cs0 f9 head f6 mainIn #connect
Cs0 f6 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect

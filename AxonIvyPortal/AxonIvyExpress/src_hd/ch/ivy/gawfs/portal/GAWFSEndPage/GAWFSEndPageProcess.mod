[Ivy]
[>Created: Thu Mar 23 16:12:57 CET 2017]
158AC95123E59763 3.18 #module
>Proto >Proto Collection #zClass
Gs0 GAWFSEndPageProcess Big #zClass
Gs0 RD #cInfo
Gs0 #process
Gs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Gs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Gs0 @TextInP .resExport .resExport #zField
Gs0 @TextInP .type .type #zField
Gs0 @TextInP .processKind .processKind #zField
Gs0 @AnnotationInP-0n ai ai #zField
Gs0 @MessageFlowInP-0n messageIn messageIn #zField
Gs0 @MessageFlowOutP-0n messageOut messageOut #zField
Gs0 @TextInP .xml .xml #zField
Gs0 @TextInP .responsibility .responsibility #zField
Gs0 @RichDialogInitStart f0 '' #zField
Gs0 @RichDialogProcessEnd f1 '' #zField
Gs0 @RichDialogProcessStart f3 '' #zField
Gs0 @RichDialogEnd f4 '' #zField
Gs0 @PushWFArc f5 '' #zField
Gs0 @GridStep f6 '' #zField
Gs0 @PushWFArc f7 '' #zField
Gs0 @GridStep f8 '' #zField
Gs0 @PushWFArc f9 '' #zField
Gs0 @PushWFArc f2 '' #zField
>Proto Gs0 Gs0 GAWFSEndPageProcess #zField
Gs0 f0 guid 158AC951265B814B #txt
Gs0 f0 type ch.ivy.gawfs.portal.GAWFSEndPage.GAWFSEndPageData #txt
Gs0 f0 method start(gawfs.EndPageProcessData) #txt
Gs0 f0 disableUIEvents true #txt
Gs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<gawfs.EndPageProcessData endPageProcessData> param = methodEvent.getInputArguments();
' #txt
Gs0 f0 inParameterMapAction 'out.endPageProcessData=param.endPageProcessData;
' #txt
Gs0 f0 outParameterDecl '<gawfs.EndPageProcessData endPageProcessData> result;
' #txt
Gs0 f0 outParameterMapAction 'result.endPageProcessData=in.endPageProcessData;
' #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(EndPageProcessData)</name>
    </language>
</elementInfo>
' #txt
Gs0 f0 83 51 26 26 -77 15 #rect
Gs0 f0 @|RichDialogInitStartIcon #fIcon
Gs0 f1 type ch.ivy.gawfs.portal.GAWFSEndPage.GAWFSEndPageData #txt
Gs0 f1 595 51 26 26 0 12 #rect
Gs0 f1 @|RichDialogProcessEndIcon #fIcon
Gs0 f3 guid 158AC95126D5BA17 #txt
Gs0 f3 type ch.ivy.gawfs.portal.GAWFSEndPage.GAWFSEndPageData #txt
Gs0 f3 actionDecl 'ch.ivy.gawfs.portal.GAWFSEndPage.GAWFSEndPageData out;
' #txt
Gs0 f3 actionTable 'out=in;
' #txt
Gs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Gs0 f3 83 147 26 26 -15 12 #rect
Gs0 f3 @|RichDialogProcessStartIcon #fIcon
Gs0 f4 type ch.ivy.gawfs.portal.GAWFSEndPage.GAWFSEndPageData #txt
Gs0 f4 guid 158AC95126EB0755 #txt
Gs0 f4 595 147 26 26 0 12 #rect
Gs0 f4 @|RichDialogEndIcon #fIcon
Gs0 f5 expr out #txt
Gs0 f5 109 160 595 160 #arcP
Gs0 f6 actionDecl 'ch.ivy.gawfs.portal.GAWFSEndPage.GAWFSEndPageData out;
' #txt
Gs0 f6 actionTable 'out=in;
' #txt
Gs0 f6 actionCode 'Long taskId = in.endPageProcessData.taskId;

in.taskx = ivy.wf.findTask(in.endPageProcessData.taskId);

for(int i=1; i<2; i++){
	
	try{
		in.casex= ivy.wf.findTask(in.endPageProcessData.taskId).getCase();
		ivy.log.debug("Got the Case " + in.casex.getId());
		i=12;		
	}catch(Exception x){
		ivy.log.debug("Wait for Case " + i);
		Thread.sleep(1000);
	}

}
' #txt
Gs0 f6 security system #txt
Gs0 f6 type ch.ivy.gawfs.portal.GAWFSEndPage.GAWFSEndPageData #txt
Gs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Task and Case</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f6 192 42 112 44 -52 -8 #rect
Gs0 f6 @|StepIcon #fIcon
Gs0 f7 expr out #txt
Gs0 f7 109 64 192 64 #arcP
Gs0 f8 actionDecl 'ch.ivy.gawfs.portal.GAWFSEndPage.GAWFSEndPageData out;
' #txt
Gs0 f8 actionTable 'out=in;
' #txt
Gs0 f8 actionCode '

if(in.casex != null && in.taskx != null){
	
	ivy.log.debug("EndPage: TASK" + in.taskx.getName() + "|" + in.taskx.getDescription() +  " | " + in.taskx.getCustomVarCharField1() + " | " + in.taskx.getCustomVarCharField2());
	ivy.log.debug("EndPage: CASE" + in.casex.getName() + "|" + in.casex.getDescription() +  " | " + in.casex.getCustomVarCharField1() + " | " + in.casex.getCustomVarCharField2());

	
	if(in.casex.getCustomVarCharField1()== null){
		//handle null 
		in.title = "Task erfolgreich abgeschlossen!";
		in.subtitle = "Der letzte Task \"" + in.taskx.getName() + "\" wurde erfolgreich abgeschlossen";
		
	}else if(in.casex.getCustomVarCharField1()=="CREATE_WF"){
		//Workflow created
		in.title = "Prozess wurde gespeichert!";
		in.subtitle = "Der Prozess wurde erfolgreich erstellt und gespeichert!";
	
	}else if(in.casex.getCustomVarCharField1()=="EDITING_WF"){
		//Workflow edited
		in.title = "Prozess gespeichert!";
		in.subtitle = "Der Prozess wurde erfolgreich editiert und gespeichert!";
		
	}else if(in.casex.getCustomVarCharField1()=="DELETE_WF"){
		//Workflow deleted
		in.title = "Prozess gelöscht";
		in.subtitle = "Der Prozess wurde erfolgreich gelöscht";
	
	
	}else if(in.casex.getCustomVarCharField1()=="ADHC_WF"){
		//Ad-hoc Workflow created and started
		in.title = "Task erfolgreich abgeschlossen!";
		in.subtitle = "Der Ad-Hoc Workflow \"" + in.casex.getName() + "\" wurde erfolgreich gestarted";
	
	}else if(in.casex.getCustomVarCharField1()=="EXECUTION_WF"){
		//Workflow executed
		
		if(in.casex.getCustomVarCharField2()== null ){
			//handle if field is null
			in.title = "Task erfolgreich abgeschlossen!";
			in.subtitle = "Die Aufgabe \"" + in.taskx.getName() + "\" wurde erfolgreich abgeschlossen";
		
		}else if(in.taskx.getCustomVarCharField1()=="INIT_WF"){
			//Existing workflow started
			in.title = "Der Prozess \"" + in.casex.getName() + "\" wurde erfolgreich mit der Aufgabe \"" + in.taskx.getName() + "\" gestartet!";
			in.subtitle = "Der Prozess \"" + in.casex.getName() + " (" + in.casex.getDescription() + ") " + "\" wurde erfolgreich gestarted";
		
		}else if(in.taskx.getCustomVarCharField1()=="TASK_WF"){
			//Existing workflow task
			in.title = "Die Aufgabe \"" + in.taskx.getName() + "\" wurde erfolgreich abgeschlossen!";
			in.subtitle = "Die Aufgabe \"" + in.taskx.getName() + "\" im Prozess \"" + in.casex.getName() +  + " (" + in.casex.getDescription() + ") " + "\" wurde erfolgreich abgeschlossen";

		}else if(in.taskx.getCustomVarCharField1()=="REVIEW_WF"){
			//Existing workflow task
			in.title = "Die Aufgabe \"" + in.taskx.getName() + "\" wurde erfolgreich abgeschlossen!";
			in.subtitle = "Der Prozess \"" + in.casex.getName() +  + " (" + in.casex.getDescription() + ") " + "\" ist damit beendet";
		
		}else{
			in.title = "Aufgabe erfolgreich abgeschlossen!";
			in.subtitle = "Die letzte Aufgabe wurde erfolgreich abgeschlossen";
			
		}
		
	}else{
		in.title = "Aufgabe erfolgreich abgeschlossen!";
		in.subtitle = "Der letzte Task \"" + in.taskx.getName() + "\" im Workflow \"" + in.casex.getName() + "\" wurde erfolgreich abgeschlossen";
	}
	

}else{
	
	if(in.endPageProcessData.caseType== null){
		//handle null 
		in.title = "Aufgabe erfolgreich abgeschlossen!";
		in.subtitle = "Die letzte Aufgabe wurde erfolgreich abgeschlossen";
	

		
	}else if(in.endPageProcessData.caseType=="EXECUTION_WF"){
			in.title = "Aufgabe erfolgreich abgeschlossen!";
			in.subtitle = "Der letzte Aufgabe \"" + in.taskx.getName() + "\" im Workflow " + in.casex.getName() + "\" wurde erfolgreich abgeschlossen";
		
	}else{
		in.title = "Aufgabe erfolgreich abgeschlossen!";
		in.subtitle = "Die letzte Aufgabe wurde erfolgreich abgeschlossen";
	}
	

}' #txt
Gs0 f8 security system #txt
Gs0 f8 type ch.ivy.gawfs.portal.GAWFSEndPage.GAWFSEndPageData #txt
Gs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set title and subtitle </name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f8 352 42 128 44 -54 -8 #rect
Gs0 f8 @|StepIcon #fIcon
Gs0 f9 expr out #txt
Gs0 f9 304 64 352 64 #arcP
Gs0 f2 expr out #txt
Gs0 f2 480 64 595 64 #arcP
>Proto Gs0 .type ch.ivy.gawfs.portal.GAWFSEndPage.GAWFSEndPageData #txt
>Proto Gs0 .processKind HTML_DIALOG #txt
>Proto Gs0 -8 -8 16 16 16 26 #rect
>Proto Gs0 '' #fIcon
Gs0 f3 mainOut f5 tail #connect
Gs0 f5 head f4 mainIn #connect
Gs0 f0 mainOut f7 tail #connect
Gs0 f7 head f6 mainIn #connect
Gs0 f6 mainOut f9 tail #connect
Gs0 f9 head f8 mainIn #connect
Gs0 f8 mainOut f2 tail #connect
Gs0 f2 head f1 mainIn #connect

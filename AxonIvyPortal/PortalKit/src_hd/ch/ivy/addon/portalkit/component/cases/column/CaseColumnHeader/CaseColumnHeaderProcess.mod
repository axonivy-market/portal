[Ivy]
164B1B767672DF33 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseColumnHeaderProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @UdEvent f3 '' #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @GridStep f35 '' #zField
Cs0 @UdMethod f15 '' #zField
Cs0 @UdProcessEnd f36 '' #zField
Cs0 @PushWFArc f38 '' #zField
Cs0 @PushWFArc f39 '' #zField
>Proto Cs0 Cs0 CaseColumnHeaderProcess #zField
Cs0 f0 guid 164B1B76780607CC #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 211 51 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 109 64 211 64 #arcP
Cs0 f3 guid 164B1B767A1B8883 #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 83 147 26 26 -15 12 #rect
Cs0 f3 @|UdEventIcon #fIcon
Cs0 f4 211 147 26 26 0 12 #rect
Cs0 f4 @|UdExitEndIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 109 160 211 160 #arcP
Cs0 f35 actionTable 'out=in;
' #txt
Cs0 f35 actionCode in.dataModel.setSorting(in.sortedField,in.isSortingDescending); #txt
Cs0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set sorting
to lazy model</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f35 302 212 36 24 20 14 #rect
Cs0 f35 @|StepIcon #fIcon
Cs0 f15 guid 164B1DA81F9E6733 #txt
Cs0 f15 method sort(String,Boolean) #txt
Cs0 f15 inParameterDecl '<String sortedField,Boolean isSortingDescending> param;' #txt
Cs0 f15 inParameterMapAction 'out.isSortingDescending=param.isSortingDescending;
out.sortedField=param.sortedField;
' #txt
Cs0 f15 outParameterDecl '<> result;' #txt
Cs0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort(CaseSortedField,Boolean)</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f15 85 213 22 22 14 0 #rect
Cs0 f15 @|UdMethodIcon #fIcon
Cs0 f36 501 213 22 22 14 0 #rect
Cs0 f36 @|UdProcessEndIcon #fIcon
Cs0 f38 expr out #txt
Cs0 f38 107 224 302 224 #arcP
Cs0 f39 expr out #txt
Cs0 f39 338 224 501 224 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.cases.column.CaseColumnHeader.CaseColumnHeaderData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f15 mainOut f38 tail #connect
Cs0 f38 head f35 mainIn #connect
Cs0 f35 mainOut f39 tail #connect
Cs0 f39 head f36 mainIn #connect

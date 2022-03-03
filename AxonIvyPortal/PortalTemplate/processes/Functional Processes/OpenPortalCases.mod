[Ivy]
1540379C4B7261E4 9.4.0 #module
>Proto >Proto Collection #zClass
Ot0 OpenPortalCases Big #zClass
Ot0 B #cInfo
Ot0 #process
Ot0 @TextInP .type .type #zField
Ot0 @TextInP .processKind .processKind #zField
Ot0 @AnnotationInP-0n ai ai #zField
Ot0 @MessageFlowInP-0n messageIn messageIn #zField
Ot0 @MessageFlowOutP-0n messageOut messageOut #zField
Ot0 @TextInP .xml .xml #zField
Ot0 @TextInP .responsibility .responsibility #zField
Ot0 @StartSub f0 '' #zField
Ot0 @EndSub f1 '' #zField
Ot0 @UserDialog f3 '' #zField
Ot0 @PushWFArc f4 '' #zField
Ot0 @PushWFArc f2 '' #zField
Ot0 @StartSub f5 '' #zField
Ot0 @EndSub f6 '' #zField
Ot0 @UserDialog f7 '' #zField
Ot0 @PushWFArc f8 '' #zField
Ot0 @PushWFArc f9 '' #zField
Ot0 @InfoButton f10 '' #zField
Ot0 @AnnotationArc f11 '' #zField
Ot0 @InfoButton f12 '' #zField
Ot0 @AnnotationArc f13 '' #zField
>Proto Ot0 Ot0 OpenPortalCases #zField
Ot0 f0 inParamDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;' #txt
Ot0 f0 inParamTable 'out.view=param.view;
' #txt
Ot0 f0 outParamDecl '<> result;' #txt
Ot0 f0 callSignature useView(ch.ivy.addon.portal.generic.view.CaseView) #txt
Ot0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useView(CaseView)</name>
    </language>
</elementInfo>
' #txt
Ot0 f0 179 115 26 26 -41 -31 #rect
Ot0 f1 851 115 26 26 14 0 #rect
Ot0 f3 dialogId ch.ivy.addon.portal.generic.PortalCases #txt
Ot0 f3 startMethod useView(CaseView) #txt
Ot0 f3 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView caseView> param;' #txt
Ot0 f3 requestMappingAction 'param.caseView=in.view;
' #txt
Ot0 f3 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalCasesData out;
' #txt
Ot0 f3 responseMappingAction 'out=in;
' #txt
Ot0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalCases</name>
    </language>
</elementInfo>
' #txt
Ot0 f3 456 106 112 44 -34 -8 #rect
Ot0 f4 expr out #txt
Ot0 f4 205 128 456 128 #arcP
Ot0 f2 expr out #txt
Ot0 f2 568 128 851 128 #arcP
Ot0 f5 inParamDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;' #txt
Ot0 f5 inParamTable 'out.view=param.view;
' #txt
Ot0 f5 outParamDecl '<> result;' #txt
Ot0 f5 callSignature useViewInFrame(ch.ivy.addon.portal.generic.view.CaseView) #txt
Ot0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useViewInFrame(CaseView)</name>
    </language>
</elementInfo>
' #txt
Ot0 f5 179 523 26 26 -54 -32 #rect
Ot0 f6 851 523 26 26 14 0 #rect
Ot0 f7 dialogId ch.ivy.addon.portal.component.iframe.PortalCaseListInFrame #txt
Ot0 f7 startMethod useView(ch.ivy.addon.portal.generic.view.CaseView) #txt
Ot0 f7 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView caseView> param;' #txt
Ot0 f7 requestMappingAction 'param.caseView=in.view;
' #txt
Ot0 f7 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalCasesData out;
' #txt
Ot0 f7 responseMappingAction 'out=in;
' #txt
Ot0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalCaseListInFrame</name>
    </language>
</elementInfo>
' #txt
Ot0 f7 440 514 144 44 -64 -8 #rect
Ot0 f8 expr out #txt
Ot0 f8 584 536 851 536 #arcP
Ot0 f9 expr out #txt
Ot0 f9 205 536 440 536 #arcP
Ot0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>**Deprecated Note**&#13;
&#13;
**This callable will be removed from Portal 11.**&#13;
To override Portal case widget''s UI: you should use Axon Ivy HTMLOverride Dialog to override the PortalCases Html dialog.</name>
    </language>
</elementInfo>
' #txt
Ot0 f10 264 194 672 76 -332 -34 #rect
Ot0 f11 264 232 199 138 #arcP
Ot0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>**Deprecated Note**&#13;
&#13;
**This callable will be removed from Portal 11.**&#13;
To override Portal case widget''s UI: you should use Axon Ivy HTMLOverride Dialog to override the PortalCaseListInFrame Html dialog.</name>
    </language>
</elementInfo>
' #txt
Ot0 f12 264 610 736 76 -362 -34 #rect
Ot0 f13 264 648 199 546 #arcP
>Proto Ot0 .type ch.ivy.addon.portal.generic.OpenPortalCasesData #txt
>Proto Ot0 .processKind CALLABLE_SUB #txt
>Proto Ot0 0 0 32 24 18 0 #rect
>Proto Ot0 @|BIcon #fIcon
Ot0 f0 mainOut f4 tail #connect
Ot0 f4 head f3 mainIn #connect
Ot0 f3 mainOut f2 tail #connect
Ot0 f2 head f1 mainIn #connect
Ot0 f5 mainOut f9 tail #connect
Ot0 f9 head f7 mainIn #connect
Ot0 f7 mainOut f8 tail #connect
Ot0 f8 head f6 mainIn #connect
Ot0 f10 ao f11 tail #connect
Ot0 f11 head f0 @CG|ai #connect
Ot0 f12 ao f13 tail #connect
Ot0 f13 head f5 @CG|ai #connect

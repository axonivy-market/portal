[Ivy]
1705118A05E4FB9D 9.3.1 #module
>Proto >Proto Collection #zClass
Us0 UserSelectionProcess Big #zClass
Us0 RD #cInfo
Us0 #process
Us0 @TextInP .type .type #zField
Us0 @TextInP .processKind .processKind #zField
Us0 @TextInP .xml .xml #zField
Us0 @TextInP .responsibility .responsibility #zField
Us0 @UdInit f0 '' #zField
Us0 @UdProcessEnd f1 '' #zField
Us0 @PushWFArc f2 '' #zField
>Proto Us0 Us0 UserSelectionProcess #zField
Us0 f0 guid 1705118A1557E73F #txt
Us0 f0 method start() #txt
Us0 f0 inParameterDecl '<> param;' #txt
Us0 f0 outParameterDecl '<> result;' #txt
Us0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Us0 f0 83 51 26 26 -16 15 #rect
Us0 f1 211 51 26 26 0 12 #rect
Us0 f2 109 64 211 64 #arcP
>Proto Us0 .type ch.ivy.addon.portalkit.component.UserSelection.UserSelectionData #txt
>Proto Us0 .processKind HTML_DIALOG #txt
>Proto Us0 -8 -8 16 16 16 26 #rect
Us0 f0 mainOut f2 tail #connect
Us0 f2 head f1 mainIn #connect

# Absence Management Bean Migration

## Overview

Migrated create/edit absence logic from the Axon Ivy process (`AbsenceManagementProcess.p.json`) into the JSF managed bean (`AbsenceManagementBean.java`) and updated `Absence.xhtml` to bind directly to the bean instead of process `data.*` / `logic.*` references. Also added a deputy role type selection component to `SelectDeputy.xhtml` and various UI improvements.

---

## Files Modified

| File | Type | Description |
|------|------|-------------|
| `AxonIvyPortal/portal/src/ch/ivy/addon/portal/generic/bean/AbsenceManagementBean.java` | Java | Main bean — added fields, methods |
| `AxonIvyPortal/portal/src_hd/ch/ivy/addon/portal/setting/AbsenceManagement/Absence.xhtml` | XHTML | Absence dialog — updated bindings |
| `AxonIvyPortal/portal/src_hd/ch/ivy/addon/portal/setting/AbsenceManagement/SelectDeputy.xhtml` | XHTML | Select deputy dialog — added role type dropdown with labels |
| `AxonIvyPortal/portal/src_hd/ch/ivy/addon/portal/setting/AbsenceManagement/AbsenceManagement.xhtml` | XHTML | Main page — fixed star icon display, updated dialog title |
| `AxonIvyPortal/portal/cms/cms.yaml` | CMS | Added new labels |
| `AxonIvyPortal/portal/cms/cms_en.yaml` | CMS | Added new labels |
| `AxonIvyPortal/portal/cms/cms_de.yaml` | CMS | Added new labels |
| `AxonIvyPortal/portal/cms/cms_fr.yaml` | CMS | Added new labels |
| `AxonIvyPortal/portal/cms/cms_es.yaml` | CMS | Added new labels |
| `AxonIvyPortal/portal/cms/cms_ja.yaml` | CMS | Added new labels |

**Reference only (not modified):**
- `AxonIvyPortal/portal/src_hd/ch/ivy/addon/portal/setting/AbsenceManagement/AbsenceManagementProcess.p.json`

---

## Key Design Decisions

### No `Sudo` wrapping needed in the bean
`AbsenceService` and `SubstituteService` already call `Sudo.get(...)` internally. The bean methods call them directly.

### API difference: `ivy.cms.co()` → `Ivy.cms().co()`
Process scripts use `ivy.cms.co(...)`. In Java beans, use `Ivy.cms().co(...)`.

### `pojoConverter` used for deputy role dropdown
The existing `pojoConverter` (`@FacesConverter("pojoConverter")`) in `portal-components` handles arbitrary object binding in `<p:selectOneMenu>` via identity hash code stored in the view map. No custom converter needed.

---

## Changes to `AbsenceManagementBean.java`

### New Imports Added

```java
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.dto.DeputyRole;
import ch.ivy.addon.portalkit.enums.DeputyRoleType;
import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.ivydata.service.impl.AbsenceService;
import ch.ivy.addon.portalkit.ivydata.service.impl.SubstituteService;
import ch.ivy.addon.portalkit.util.AbsenceAndSubstituteUtils;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivy.addon.portalkit.util.DeputyRoleUtils;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
```

### New Fields Added

```java
// Absence form state
private IvyAbsence selectedAbsence;
private IvyAbsence backupAbsence;
private UserDTO selectedUser;
private boolean createMode;
private boolean validationError;
private Map<String, Set<IvyAbsence>> absencesByUser;
private List<IvyAbsence> displayedAbsences;
private boolean absenceInThePastShown;
private List<DeputyRole> deputyRoles;
private DeputyRole selectedDeputyRole;
private UserDTO selectedDeputy;
private List<ISecurityMember> selectedDeputies;
private List<ISecurityMember> onAbsenceDeputies;
private List<ISecurityMember> permanentDeputies;
private List<IvySubstitute> substitutes;
private boolean canCreateAbsenceForOtherUsers;
private UserDTO selectedAbsenceUser;
private String message;
```

All fields initialized in `@PostConstruct init()`.

### New Methods Added

#### `initDeputyLists()` (private)
Extracted shared logic for initializing `onAbsenceDeputies`, `permanentDeputies`, and `selectedDeputies` from `deputyRoles`. Called by `initAbsenceForm()`, `editAbsence()`, and `init()` to eliminate duplication.

```java
private void initDeputyLists() {
    onAbsenceDeputies = new ArrayList<>();
    permanentDeputies = new ArrayList<>();

    DeputyRole duringAbsenceRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    if (duringAbsenceRole != null) {
        onAbsenceDeputies = DeputyRoleUtils.cloneDeputyList(duringAbsenceRole.getDeputies());
    }

    DeputyRole permanentRole = DeputyRoleUtils.findDeputyRoleByType(deputyRoles, DeputyRoleType.PERSONAL_TASK_PERMANENT);
    if (permanentRole != null) {
        permanentDeputies = new ArrayList<>(permanentRole.getDeputies());
    }

    selectedDeputies = new ArrayList<>();
    selectedDeputies.addAll(onAbsenceDeputies);
    selectedDeputies.addAll(permanentDeputies);
}
```

#### `initAbsenceForm()`
Opens the dialog in **create mode**. Initializes a blank `IvyAbsence`, sets `createMode = true`, resolves current user, sets `selectedDeputyRole` to `PERSONAL_TASK_DURING_ABSENCE`, then calls `initDeputyLists()`.

Maps to process nodes: `f8 (Init form)`.

#### `editAbsence(IvyAbsence absence)`
Opens the dialog in **edit mode**. Backs up the absence fields into `backupAbsence`, sets `createMode = false`, then calls `initDeputyLists()`.

Maps to process nodes: `f37 (Backup absence)`.

#### `saveAbsence()`
Delegates to `saveCreateAbsence()` or `saveEditAbsence()` based on `createMode`.

Maps to process nodes: `f52 → f98 (branching)`.

**Create path (`saveCreateAbsence`):**
1. Sets end-of-day on `selectedAbsence.until` via `Dates.toEndOfDate()`
2. Fresh-fetches `absencesByUser` from `AbsenceService`
3. Validates: from > until check, overlap check — sets `validationError` and calls `validationFailed()` on failure
4. Calls `AbsenceService.newInstance().createAbsence(selectedAbsence)`
5. Builds substitutes separately from `onAbsenceDeputies` (DURING_ABSENCE role) and `permanentDeputies` (PERMANENT role)
6. Calls `SubstituteService.newInstance().saveSubstitutes(selectedAbsenceUser, substitutes)`
7. Conditionally adds new absence to `displayedAbsences` (respects `absenceInThePastShown` flag)
8. Calls `showMessage()` with success CMS string

**Edit path (`saveEditAbsence`):**
1. Sets end-of-day on `selectedAbsence.until`
2. Calls `AbsenceService.newInstance().updateAbsences(username, absencesByUser.get(username))`
3. Updates in-memory `absencesByUser` and sorts `displayedAbsences`
4. Sets `selectedDeputyRole.deputies = selectedDeputies`, builds substitutes, calls `saveSubstitutes`
5. Calls `showMessage()` with update CMS string

#### `cancelAbsence()`
Restores `selectedAbsence` fields from `backupAbsence`. Resets `selectedDeputies` to the original DURING_ABSENCE role deputies. Clears `permanentDeputies`.

Maps to process node: `f55 (Reset/Cancel)`.

#### `addDeputy(String deputyRoleTypeName)`
Resolves `selectedDeputy` (UserDTO) to an `ISecurityMember` via `ISecurityContext.current().members().find(selectedDeputy.getMemberName())`.

Validation checks (in order):
1. `selectedAssignee == null` or already in `selectedDeputies` → error: invalid deputy
2. Adding to DURING_ABSENCE role but member already in PERMANENT role → cross-role conflict error
3. Adding to PERMANENT role but member already in DURING_ABSENCE role → cross-role conflict error
4. Otherwise → `selectedDeputies.add(selectedAssignee)`

Maps to process nodes: `f34 → f44 (Add deputy)`.

#### `getSelectableDeputyRoles()`
Returns `deputyRoles` filtered by user permissions via `getDeputyRolesBasedOnPermissions()`. Used as the options source for the deputy role type dropdown in `SelectDeputy.xhtml`.

```java
public List<DeputyRole> getSelectableDeputyRoles() {
    return getDeputyRolesBasedOnPermissions(deputyRoles, selectedAbsenceUser);
}
```

#### `onDeputyRoleTypeChanged()`
Called when the user switches the deputy role type in the dropdown. Re-clones deputies from the newly selected `selectedDeputyRole` into `selectedDeputies` and resets `selectedDeputy`. Maps to process node `f74 (Initialize dialog)`.

```java
public void onDeputyRoleTypeChanged() {
    if (selectedDeputyRole != null) {
        selectedDeputies = DeputyRoleUtils.cloneDeputyList(selectedDeputyRole.getDeputies());
    } else {
        selectedDeputies = new ArrayList<>();
    }
    selectedDeputy = null;
}
```

#### `showMessage()` (private)
Adds a `FacesMessage.SEVERITY_INFO` message to `"absences-management-info"` via `PrimeFacesContext`.

---

## Changes to `Absence.xhtml`

### Summary of binding replacements (16 total)

| Old binding | New binding |
|-------------|-------------|
| `data.isCreateMode` | `absenceManagementBean.createMode` |
| `logic.cancel` (actionListener) | `absenceManagementBean.cancelAbsence()` |
| `logic.saveAbsence` (actionListener) | `absenceManagementBean.saveAbsence()` |
| `data.selectedAbsence.from` | `absenceManagementBean.selectedAbsence.from` |
| `data.selectedAbsence.until` | `absenceManagementBean.selectedAbsence.until` |
| `data.selectedAbsence.comment` | `absenceManagementBean.selectedAbsence.comment` |
| `data.selectedUser` | `absenceManagementBean.selectedUser` |
| `data.selectedDeputy` | `absenceManagementBean.selectedDeputy` |
| `data.selectedDeputies` | `absenceManagementBean.selectedDeputies` |
| `data.permanentDeputies` | `absenceManagementBean.permanentDeputies` |
| `data.canCreateAbsenceForOtherUsers` | `absenceManagementBean.canCreateAbsenceForOtherUsers` |
| `data.selectedAbsenceUser` | `absenceManagementBean.selectedAbsenceUser` |
| `data.validationError` | `absenceManagementBean.validationError` |
| `logic.addDeputy('PERSONAL_TASK_DURING_ABSENCE')` | `absenceManagementBean.addDeputy('PERSONAL_TASK_DURING_ABSENCE')` |
| `canCreateSubstitute(data.selectedAbsenceUser)` | `canCreateSubstitute(absenceManagementBean.selectedAbsenceUser)` |
| `canReadSubstitute(data.selectedAbsenceUser)` | `canReadSubstitute(absenceManagementBean.selectedAbsenceUser)` |

### Remaining `logic.*` calls (not yet migrated)

These still call the Axon Ivy process and are pending migration:

| `logic.*` call | Location | Purpose |
|----------------|----------|---------|
| `logic.autoCompleteForUserOnApp` | `completeMethod` on UserSelection | Autocomplete user search |
| `logic.userSelectInAbsence` | `p:ajax itemSelect` on UserSelection | User selected — reload deputies |
| `logic.fromDateChange` | `p:ajax dateSelect` on start date | Validate from date, update end date min |
| `logic.dateChange` | `p:ajax dateSelect` on end date | Validate end date |
| `logic.setAsPermanent(deputy, isPermanent)` | `p:menuitem` action | Toggle permanent deputy flag |
| `logic.removeDeputy(deputy)` | `p:menuitem` actionListener | Remove deputy from list |

---

## Changes to `SelectDeputy.xhtml`

Redesigned the dialog to match the "Add substitute" UI mockup.

- Replaced `p:panelGrid` with `h:panelGroup` for cleaner layout
- Added **"Substitute type"** label and `<p:selectOneMenu>` bound to `absenceManagementBean.selectedDeputyRole` using `pojoConverter`; options come from `absenceManagementBean.selectableDeputyRoles`
- On type change, fires `absenceManagementBean.onDeputyRoleTypeChanged()` and updates the deputy list
- Added **"Substitute name"** label above the user selection autocomplete with a placeholder
- The Add button is kept beside the user selection input
- Deputies table is rendered inside `p:fieldset`
- Cancel link removed; only Save button shown at the bottom

---

## Changes to `AbsenceManagement.xhtml`

- Fixed permanent deputy star icon: changed `h:panelGroup layout="block"` to `layout="inline"` so the star renders inline with the avatar instead of on a new line
- Updated the "Add substitute" dialog title from `selectDeputies` CMS key to `addSubstitute`

---

## New CMS Entries

Added to all locale files (`cms.yaml`, `cms_en.yaml`, `cms_de.yaml`, `cms_fr.yaml`, `cms_es.yaml`, `cms_ja.yaml`) under `ch.ivy.addon.portalkit.ui.jsf.AbsenceAndDeputy`:

| Key | English value |
|-----|---------------|
| `addSubstitute` | Add substitute |
| `selectSubstitute` | Select substitute |
| `substituteType` | Substitute type |
| `substituteName` | Substitute name |

---

## Pending Migration (next steps)

### `removeDeputy(ISecurityMember deputy)`
Simple — remove from the appropriate list:
```java
public void removeDeputy(ISecurityMember deputy) {
    selectedDeputies.remove(deputy);
    onAbsenceDeputies.remove(deputy);
    permanentDeputies.remove(deputy);
}
```
XHTML: `logic.removeDeputy(deputy)` → `absenceManagementBean.removeDeputy(deputy)`

### `setAsPermanent(ISecurityMember deputy, boolean isPermanent)`
Toggle between `permanentDeputies` and `onAbsenceDeputies` lists based on `isPermanent` flag.

### `fromDateChange` / `dateChange`
Validate dates and set `validationError`. May also update end date minimum.

### `userSelectInAbsence`
Re-fetch deputies for the newly selected user; update `deputyRoles`, `selectedDeputies`, etc.

### `autoCompleteForUserOnApp` / autocomplete for substitutes
Delegates to `SecurityService.findUsers(...)` — can call a service directly from the bean.


---

## Key Design Decisions

### No `Sudo` wrapping needed in the bean
`AbsenceService` and `SubstituteService` already call `Sudo.get(...)` internally. The bean methods call them directly.

### API difference: `ivy.cms.co()` → `Ivy.cms().co()`
Process scripts use `ivy.cms.co(...)`. In Java beans, use `Ivy.cms().co(...)`.

---

## Changes to `AbsenceManagementBean.java`

### New Imports Added

```java
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.dto.DeputyRole;
import ch.ivy.addon.portalkit.enums.DeputyRoleType;
import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.ivydata.service.impl.AbsenceService;
import ch.ivy.addon.portalkit.ivydata.service.impl.SubstituteService;
import ch.ivy.addon.portalkit.util.AbsenceAndSubstituteUtils;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivy.addon.portalkit.util.DeputyRoleUtils;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
```

### New Fields Added

```java
// Absence form state
private IvyAbsence selectedAbsence;
private IvyAbsence backupAbsence;
private UserDTO selectedUser;
private boolean createMode;
private boolean validationError;
private Map<String, Set<IvyAbsence>> absencesByUser;
private List<IvyAbsence> displayedAbsences;
private boolean absenceInThePastShown;
private List<DeputyRole> deputyRoles;
private DeputyRole selectedDeputyRole;
private UserDTO selectedDeputy;
private List<ISecurityMember> selectedDeputies;
private List<ISecurityMember> onAbsenceDeputies;
private List<ISecurityMember> permanentDeputies;
private List<IvySubstitute> substitutes;
private boolean canCreateAbsenceForOtherUsers;
private UserDTO selectedAbsenceUser;
private String message;
```

All fields initialized in `@PostConstruct init()`.

### New Methods Added

#### `initAbsenceForm()`
Opens the dialog in **create mode**. Initializes a blank `IvyAbsence`, sets `createMode = true`, resolves current user, clones existing deputies from `deputyRoles` into `onAbsenceDeputies` and `permanentDeputies`, then merges them into `selectedDeputies`.

Maps to process nodes: `f8 (Init form)`.

#### `editAbsence(IvyAbsence absence)`
Opens the dialog in **edit mode**. Backs up the absence fields into `backupAbsence`, sets `createMode = false`, resolves `PERSONAL_TASK_DURING_ABSENCE` role and clones its deputies into `selectedDeputies`.

Maps to process nodes: `f37 (Backup absence)`.

#### `saveAbsence()`
Delegates to `saveCreateAbsence()` or `saveEditAbsence()` based on `createMode`.

Maps to process nodes: `f52 → f98 (branching)`.

**Create path (`saveCreateAbsence`):**
1. Sets end-of-day on `selectedAbsence.until` via `Dates.toEndOfDate()`
2. Fresh-fetches `absencesByUser` from `AbsenceService`
3. Validates: from > until check, overlap check — sets `validationError` and calls `validationFailed()` on failure
4. Calls `AbsenceService.newInstance().createAbsence(selectedAbsence)`
5. Builds substitutes separately from `onAbsenceDeputies` (DURING_ABSENCE role) and `permanentDeputies` (PERMANENT role)
6. Calls `SubstituteService.newInstance().saveSubstitutes(selectedAbsenceUser, substitutes)`
7. Conditionally adds new absence to `displayedAbsences` (respects `absenceInThePastShown` flag)
8. Calls `showMessage()` with success CMS string

**Edit path (`saveEditAbsence`):**
1. Sets end-of-day on `selectedAbsence.until`
2. Calls `AbsenceService.newInstance().updateAbsences(username, absencesByUser.get(username))`
3. Updates in-memory `absencesByUser` and sorts `displayedAbsences`
4. Sets `selectedDeputyRole.deputies = selectedDeputies`, builds substitutes, calls `saveSubstitutes`
5. Calls `showMessage()` with update CMS string

#### `cancelAbsence()`
Restores `selectedAbsence` fields from `backupAbsence`. Resets `selectedDeputies` to the original DURING_ABSENCE role deputies. Clears `permanentDeputies`.

Maps to process node: `f55 (Reset/Cancel)`.

#### `addDeputy(String deputyRoleTypeName)`
Resolves `selectedDeputy` (UserDTO) to an `ISecurityMember` via `ISecurityContext.current().members().find(selectedDeputy.getMemberName())`.

Validation checks (in order):
1. `selectedAssignee == null` or already in `selectedDeputies` → error: invalid deputy
2. Adding to DURING_ABSENCE role but member already in PERMANENT role → cross-role conflict error
3. Adding to PERMANENT role but member already in DURING_ABSENCE role → cross-role conflict error
4. Otherwise → `selectedDeputies.add(selectedAssignee)`

Maps to process nodes: `f34 → f44 (Add deputy)`.

#### `showMessage()` (private)
Adds a `FacesMessage.SEVERITY_INFO` message to `"absences-management-info"` via `PrimeFacesContext`.

---

## Changes to `Absence.xhtml`

### Summary of binding replacements (16 total)

| Old binding | New binding |
|-------------|-------------|
| `data.isCreateMode` | `absenceManagementBean.createMode` |
| `logic.cancel` (actionListener) | `absenceManagementBean.cancelAbsence()` |
| `logic.saveAbsence` (actionListener) | `absenceManagementBean.saveAbsence()` |
| `data.selectedAbsence.from` | `absenceManagementBean.selectedAbsence.from` |
| `data.selectedAbsence.until` | `absenceManagementBean.selectedAbsence.until` |
| `data.selectedAbsence.comment` | `absenceManagementBean.selectedAbsence.comment` |
| `data.selectedUser` | `absenceManagementBean.selectedUser` |
| `data.selectedDeputy` | `absenceManagementBean.selectedDeputy` |
| `data.selectedDeputies` | `absenceManagementBean.selectedDeputies` |
| `data.permanentDeputies` | `absenceManagementBean.permanentDeputies` |
| `data.canCreateAbsenceForOtherUsers` | `absenceManagementBean.canCreateAbsenceForOtherUsers` |
| `data.selectedAbsenceUser` | `absenceManagementBean.selectedAbsenceUser` |
| `data.validationError` | `absenceManagementBean.validationError` |
| `logic.addDeputy('PERSONAL_TASK_DURING_ABSENCE')` | `absenceManagementBean.addDeputy('PERSONAL_TASK_DURING_ABSENCE')` |
| `canCreateSubstitute(data.selectedAbsenceUser)` | `canCreateSubstitute(absenceManagementBean.selectedAbsenceUser)` |
| `canReadSubstitute(data.selectedAbsenceUser)` | `canReadSubstitute(absenceManagementBean.selectedAbsenceUser)` |

### Remaining `logic.*` calls (not yet migrated)

These still call the Axon Ivy process and are pending migration:

| `logic.*` call | Location | Purpose |
|----------------|----------|---------|
| `logic.autoCompleteForUserOnApp` | `completeMethod` on UserSelection | Autocomplete user search |
| `logic.userSelectInAbsence` | `p:ajax itemSelect` on UserSelection | User selected — reload deputies |
| `logic.fromDateChange` | `p:ajax dateSelect` on start date | Validate from date, update end date min |
| `logic.dateChange` | `p:ajax dateSelect` on end date | Validate end date |
| `logic.setAsPermanent(deputy, isPermanent)` | `p:menuitem` action | Toggle permanent deputy flag |
| `logic.removeDeputy(deputy)` | `p:menuitem` actionListener | Remove deputy from list |

---

## Pending Migration (next steps)

### `removeDeputy(ISecurityMember deputy)`
Simple — remove from the appropriate list:
```java
public void removeDeputy(ISecurityMember deputy) {
    selectedDeputies.remove(deputy);
    onAbsenceDeputies.remove(deputy);
    permanentDeputies.remove(deputy);
}
```
XHTML: `logic.removeDeputy(deputy)` → `absenceManagementBean.removeDeputy(deputy)`

### `setAsPermanent(ISecurityMember deputy, boolean isPermanent)`
Toggle between `permanentDeputies` and `onAbsenceDeputies` lists based on `isPermanent` flag.

### `fromDateChange` / `dateChange`
Validate dates and set `validationError`. May also update end date minimum.

### `userSelectInAbsence`
Re-fetch deputies for the newly selected user; update `deputyRoles`, `selectedDeputies`, etc.

### `autoCompleteForUserOnApp` / autocomplete for substitutes
Delegates to `SecurityService.findUsers(...)` — can call a service directly from the bean.

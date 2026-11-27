# Implementation Plan: Sidebar Visibility in `Portal.Menu.Order`

## 1. Goal & Scope

Replace the deprecated permission-based design with a `visibility` field on entries in the existing `Portal.Menu.Order` JSON variable. Keep this filename for continuity; it no longer describes the implementation.

* Hide/Show actions change menu visibility for every sidebar row, not engine permissions.
* Visibility is application-wide configuration, not per-role or per-user policy. For Processes, `AccessFullProcessList` is checked first; manifest visibility in `Portal.Menu.Order` is evaluated after it.
* Preserve the requested three-dot actions, Status column immediately before Actions, existing Permissions column, built-in rows, and reorder controls.
* Apply manifest visibility to all matched menu entries, including `source: "dashboard"` entries. Every configuration row receives Hide/Show actions in this scope; existing custom/dashboard editing actions remain unchanged.
* Keep homepage choices and automatic login fallback aligned with visible, accessible navigation entries.
* In the Sidebar configuration screen, prevent hiding or deleting the last visible menu row. Use a small guard in the existing handlers.
* This is a plan revision only. The implementation steps below include replacing the permission-based code already present in the repository.

### Explicitly Removed from the Previous Design

* No `AccessDashboard` / `PortalPermission.ACCESS_DASHBOARD` permission, default grant, initialization change, permission helper, or permission documentation.
* No grant, deny, or ungrant operations on `Everybody` or any other role/user.
* No mutation of the existing `AccessFullProcessList` permission when hiding/showing Processes.
* No new authorization rule for direct URLs, dashboard data, drill-downs, or private dashboard management based on menu visibility.
* No additional global enabled flag or separate visibility variable.

**Security boundary:** hiding a menu is not revoking access. Direct URLs remain governed by existing page, process, and per-dashboard authorization. A visible Processes entry still requires `AccessFullProcessList`; showing it does not grant that permission. Hiding Dashboard does not revoke access to its public/private dashboards or their configuration.

## 2. JSON Contract & Compatibility

Use the existing variable `Portal.Menu.Order` (`Menu.Order` in the request), preserving the ordered `menus` array and versioned wrapper:

```json
{
    "menus": [
        {
            "id": "DASHBOARD",
            "source": "standard",
            "visibility": "false"
        },
        {
            "id": "PROCESS",
            "source": "standard",
            "visibility": "false"
        },
        {
            "id": "default-case-list-dashboard",
            "source": "dashboard",
            "visibility": "true"
        },
        {
            "id": "296daaf38d1b46fdb384a70ea3b30db3",
            "source": "dashboard",
            "visibility": "true"
        }
    ],
    "version": "14.0.0"
}
```

* Identity remains the pair `(source, id)`, never the translated title, row index, or ID alone.
* Serialize `visibility` as the strings `"true"` / `"false"` to match the supplied JSON, not JSON booleans.
* Add a string property to `MenuOrderEntry`, initialized to `"true"`, with a single effective-visibility interpretation shared by consumers. Missing, null, or blank values mean visible for backward compatibility; only `"false"` hides an entry. Writes emit canonical `"true"` / `"false"` values.
* Existing manifests without this property, blank/unset variables, and newly discovered source items remain visible by default. No startup rewrite or permission migration is required.
* Preserve lowercase source serialization and the existing version mechanism (`MenuOrderService.save()` stamps `AbstractJsonVersion.LATEST`). The example uses `14.0.0`; do not hardcode a second version constant.
* `visibility: "false"` retains the entry and its position. It is not deletion and must not cause the loader to append the item again as a newly discovered source item.
* Runtime rendering must never persist reconciliation changes to this shared variable.

### Keep One Menu Visible

* At least one row in the shared Sidebar configuration must stay visible. Count existing top-level rows, including default-visible rows; do not count private entries or fixed utility links.
* Disable Hide on the last visible row. If that row allows Delete, disable Delete too. Explain: "At least one menu item must remain visible."
* Recheck in the existing Hide/Delete handlers before changing anything. If the action would remove the last visible row, show the same message and leave the configuration unchanged. Show remains available.
* Keep this change local to `MenuManagementBean`, the existing XHTML actions, CMS text, and their tests. Reuse the existing loader and visibility state; add no new validation framework or locking infrastructure.
* This guard covers Sidebar actions, not package imports, source changes elsewhere, direct variable edits, or simultaneous writes across engine nodes. Those paths keep their existing behavior. Existing permission checks and empty-homepage handling remain unchanged.

## 3. Repository Baseline & Required Changes

Paths below are relative to `AxonIvyPortal/portal` unless another root is specified.

### Step 1: Manifest Model, Loading & Persistence

**Files**

* `src/com/axonivy/portal/dto/menu/MenuOrderEntry.java`
* `src/com/axonivy/portal/dto/menu/MenuOrder.java`
* `src/com/axonivy/portal/dto/menu/PortalMenuItemDefinition.java`
* `src/com/axonivy/portal/service/MenuOrderService.java`
* `src/com/axonivy/portal/menu/management/MenuLoader.java`

**Current behavior:** `MenuOrderEntry` contains only ID/source. `MenuLoader.loadMenuDefinitions()` reconciles source items with the manifest without writing it. `persistManifest()` reconstructs entries from ID/source only, so simply adding a DTO field would lose visibility on subsequent saves.

**Implementation**

1. Extend the entry model and its schema comments with the string visibility contract.
2. Carry effective visibility on the loaded menu definition as runtime metadata, defaulting to visible. Do not duplicate this setting into dashboard JSON or other source configurations.
3. Apply manifest visibility when matching definitions in `loadMenuDefinitions()`. Continue returning the full ordered inventory, including invisible entries; keep `buildStandardItems()` unconditional.
4. Preserve `(source, id)`, order, and visibility through every manifest writer:
   * Reorder, Hide/Show, and removal in `MenuManagementBean`.
   * Create/edit persistence in `MenuDetailsBean`.
   * `MenuLoader.persistManifest()` and `MenuOrderService.save()`.
5. Use the Sidebar configuration list as the save snapshot. `persistManifest()` serializes each row's `(source, id)` and `isVisible()` in list order. Do not reread or merge the persisted manifest during this save; Sidebar configuration assumes a single administrator.
6. For Hide/Show, resolve the selected row by `(source, id)`, set its requested visibility, and save through the same `persistManifest()` used by reorder. Newly discovered rows default to visible until explicitly hidden.
7. Retain existing source-removal reconciliation and explicit deletion behavior. Toggling must not invoke `MenuRemovalHandler`, delete source data, or persist a permission-filtered runtime list as the configuration inventory.
8. Keep package export/import compatible: `PortalPackageService` already exports the raw order variable and imports the `MenuOrder` wrapper. Verify that visibility survives this path and that older packages without it retain default-visible behavior. No import validation changes are required for the last-visible-row rule.

### Step 2: Runtime Sidebar Visibility

**File:** `src/ch/addon/portal/generic/menu/MenuView.java`

1. Skip invisible definitions before building their menu elements; keep existing role, process-startability, and dashboard-access checks.
2. Remove the deprecated dashboard permission check from `buildDashboardItem()`.
3. Hiding standard `DASHBOARD` hides its parent and all nested sidebar entries, without removing or filtering the underlying public/private dashboard data.
4. Hiding standard `PROCESS` hides Processes regardless of the session user's existing grant. A visible Processes entry still passes `checkAccessFullProcessListPermission()`.
5. Independently honor visibility on top-level dashboard and other manifest-backed definitions. Hiding standard Dashboard must not hide visible `MAIN_DASHBOARD` entries such as Cases or customer dashboards.
6. Private third-party entries remain outside the shared manifest, as in the existing loader; do not create shared visibility settings from per-user source data.

### Step 3: Sidebar Configuration Actions & Status

**Files**

* `src/com/axonivy/portal/bean/menu/MenuManagementBean.java`
* `dialog/ch/ivy/addon/portal/generic/dashboard/PortalDashboardConfiguration/PortalDashboardConfiguration.xhtml`
* Existing localized entries under `cms/`.

**Implementation**

1. Replace `canToggleMenuPermission()` and the permission mapping with a visibility-oriented predicate, such as `canToggleMenuVisibility()`. Support every shared configuration row, including callable rows; resolve targets by trusted source/ID identity. Existing Edit/Delete exclusions remain separate.
2. Replace the ACL state reader and standard-only handler with generic visibility state and explicit-state mutation for shared rows; migrate all callers. Their implementation must no longer resolve permissions/roles, use `Sudo`, or modify the security descriptor.
3. Authorize every mutation server-side with `verifySidebarManagementPermission()` before saving. Resolve the supported target from trusted source/type identity; reject unsupported or forged targets.
4. Persist an explicit requested state, not an inversion: Hide writes `"false"` and Show writes `"true"`. Repeated requests for the same state are idempotent.
5. Render Hide when visible or Show when hidden on every row. Disable Hide and any permitted Delete action for the last visible row, using the rule in Section 2. Expose Edit/Delete only where existing rules allow: custom and dashboard rows, never standard or callable rows.
6. Keep Status immediately before Actions. Display Visible/Hidden and the Hide/Show action on every row, based on manifest visibility—not effective permissions or `Everybody` ACLs.
7. Preserve the Permissions column, including existing role labels/tooltips. Status describes manifest visibility only; Visible does not change access. For Processes, users still need `AccessFullProcessList`.
8. Keep hidden built-in rows and reorder handles in the configuration table. Sidebar administrators must retain access to this inventory and be able to re-enable both entries.
9. After successful persistence, refresh the table and show the existing Sidebar Settings reload notice. Other sessions pick up visibility on their next menu rebuild; already rendered pages do not update live. If the save fails, restore the row's previous state and do not show a success notice.
10. Remove the permission-specific refresh coupling from the current XHTML actions where it is no longer needed. Use localized Visible/Hidden status and Hide/Show action labels; replace the current CMS help text describing role-wide grant/deny operations. New CMS entries must be capitalized and stay in `cms/`; no inline XHTML styles.
11. Add one small shared check in `MenuManagementBean` for Hide and Delete. Reload definitions through the existing loader before checking the requested action; reject removing the last visible row before any visibility write or source deletion. Compute the UI visible-row count once when loading/refreshing the table, not by reloading menus for each row. Do not change Show, reorder, edit, import, or source-management flows for this rule.

### Step 4: Homepage Choices & Login Fallback

**Files**

* `src/ch/addon/portal/generic/userprofile/homepage/HomepageUtils.java`
* `src/ch/addon/portal/generic/menu/PortalMenuNavigator.java`
* `dialog/ch/ivy/addon/portal/setting/UserProfile/UserProfile.xhtml`
* `process/Start Processes/PortalStart.p.json` (routing audit and empty-homepage handling).

**Current behavior:** `HomepageUtils` already has personal → global → first available fallback and empty-list guards. However, Dashboard inclusion currently uses the deprecated permission, while `PortalMenuNavigator` independently assembles Processes, main dashboards, and custom entries rather than following manifest order.

**Implementation**

1. Include the default Dashboard homepage only when the standard Dashboard entry is visible.
2. Build homepage candidates in reconciled manifest order, using the same visibility metadata as the runtime sidebar and existing access checks. Avoid a second independently maintained visibility/order list and avoid parsing the manifest once per entry.
3. Map candidates to the existing homepage identities and supported homepage types. Preserve exclusions such as external links and do not introduce new homepage types merely because an item exists in the manifest.
4. Exclude hidden Processes and hidden top-level dashboard/custom entries from homepage selectors and automatic fallback, even when the user retains authorization to their direct URLs.
5. Preserve precedence: valid visible personal/Teams selection → valid visible global homepage → first visible accessible supported homepage in sidebar order.
6. Do not erase saved personal/global choices when temporarily hidden. Treat them as unavailable during resolution; they can become eligible again after Show.
7. Preserve empty-list guards in profile/default selection and hide the homepage selector when there are no candidates.
8. If no homepage is available, reuse the existing Dashboard template and its localized `dashboard/noPermission` message. Do not load a dashboard, redirect, or show a separate configuration link.
9. Keep explicit Dashboard URLs distinct from automatic homepage fallback. Explicit navigation remains subject to existing authorization, not the menu visibility setting. Verify standard and Teams start paths.

### Step 5: Remove the Implemented Deprecated Permission Design

The repository already contains parts of the old design. Replace them during implementation; do not leave aliases, dead helpers, or ACL writes behind.

* `src/ch/ivy/addon/portalkit/enums/PortalPermission.java`: remove the deprecated enum constant.
* `src/ch/ivy/addon/portalkit/security/PortalSecurity.java`: remove its default `Everybody` grant.
* `src/ch/ivy/addon/portalkit/util/PermissionUtils.java`: remove the dashboard access helper and the access-permission requirement from private dashboard management. Where `canManagePrivateDashboard()` becomes only a redundant wrapper, migrate callers to `hasDashboardWriteOwnPermission()` and remove it.
* `src/ch/ivy/addon/portalkit/util/DashboardUtils.java`: remove the deprecated permission filter from submenu dashboard retrieval; do not replace it with a visibility filter on dashboard data.
* `src/ch/ivy/addon/portal/generic/bean/DashboardBean.java`: remove the deprecated permission-based dashboard filtering/no-permission logic. Keep actual per-dashboard authorization and main/hidden/drill-down behavior.
* `src/ch/ivy/addon/portal/generic/bean/DashboardConfigurationBean.java`, `UserMenuBean.java`, and `process/Start Processes/PortalStart.p.json`: remove the deprecated permission requirement for private dashboard configuration and restore the existing write-own/public/sidebar/package permission decisions.
* Replace permission logic in `MenuView`, `HomepageUtils`, and `MenuManagementBean` as described above.
* Remove permission-specific initialization changes introduced solely for the abandoned design, if present. Do not change initialization policy for unrelated permissions.
* Do not delete deployed security-descriptor records or infer visibility from old ACLs. The new manifest state is authoritative; existing `AccessFullProcessList` restrictions remain unchanged. Document this cutover if a preview build using the deprecated permission was distributed.

## 4. Focused Verification Plan

Extend the existing tests, replacing old permission-toggle assertions rather than preserving two contracts.

### Model, Service & Handler Tests

* `AxonIvyPortal/portal-test/src_test/com/axonivy/portal/service/TestMenuOrderService.java`: supplied JSON round trip, canonical string serialization, missing/null/blank visibility defaults, unset variable, and older package/config compatibility.
* `AxonIvyPortal/portal-test/src_test/com/axonivy/portal/menu/management/TestMenuVisibility.java`: cover explicit visibility updates by `(source, id)`, unchanged order, independent states, and shared persistence with reorder.
* Loader/persistence coverage: hidden entries stay in the configuration inventory, do not reappear through auto-append, and preserve visibility through reorder, create/edit, removal of another row, and package round trips.
* Last-visible-row tests: Hide/Delete reject removing the only visible row without changing visibility or source data; hiding one of two visible rows succeeds; Show on another row allows the original row to be hidden. Verify the handlers reload current state rather than trusting the displayed count.
* `AxonIvyPortal/portal-test/src_test/ch/addon/portal/generic/userprofile/homepage/TestHomepageUtils.java`: visible/accessible personal and global precedence, manifest-ordered fallback, hidden candidates, showing saved choices again, and zero candidates despite at least one configured visible item.
* Update directly affected dashboard and private-management tests to remove the abandoned permission dependency while retaining actual access checks.

### Selenium & UI Verification

* Extend `AxonIvyPortal/portal-selenium-test/src_test/com/axonivy/portal/selenium/test/dashboard/MenuManagementTest.java` and its existing page objects.
* Replace the deprecated dashboard-permission scenario/setup in `PortalPermissionTest.java`; retain coverage of existing permissions.
* Verify each row offers Hide or Show with Visible/Hidden status; standard and callable rows never offer Edit/Delete. Status changes after a successful save and survives reload.
* Hide both built-ins using the supplied JSON shape. Verify both disappear from runtime navigation, while the visible Cases/customer dashboard entries remain usable and determine automatic homepage fallback in manifest order.
* Verify shared configuration rows, reorder controls, custom editing actions, callable Edit/Delete exclusions, and the Permissions column remain unchanged.
* Verify showing Processes does not bypass an existing `AccessFullProcessList` denial; hiding/showing any shared item does not mutate any ACL.
* Verify explicitly opened authorized dashboard/process URLs still work when their menu is hidden; unauthorized requests still fail under existing security.
* Verify private dashboard management remains governed by existing write permissions, and main/hidden/navigation-widget/chart drill-down dashboards are unaffected by hiding the standard Dashboard menu.
* Verify homepage selectors, stale personal/global choices, zero-candidate login, Teams routing, and administrator recovery without a redirect loop.
* When Processes is the only visible row and the user lacks `AccessFullProcessList`, verify that login shows the Dashboard access message without a missing `main-menu` widget error and that the topbar user menu still opens.
* Hide all but one row: verify Hide and any permitted Delete action are disabled with the explanation. Show another row and verify Hide becomes available again. Verify a direct handler request cannot bypass the check.
* Restore the exact original `Portal.Menu.Order` value in teardown, including an initially unset variable. Restore any independently changed permissions, dashboards, and homepage settings.
* After the final implementation edit, run only the affected model/service/handler/homepage/dashboard tests and their affected callers, then the focused sidebar Selenium scenarios. Inspect both dropdown states and navigation in the running UI.

## 5. Documentation Updates

Under `Documentation/portal-guide/source`:

* `portal-developer-guide/permissions/index.rst`: remove the deprecated permission entry and its private-dashboard requirement. Keep `AccessFullProcessList` documented as authorization, separate from visibility.
* `portal-developer-guide/settings/index.rst`: remove the deprecated permission-summary row; document the manifest visibility field, default-visible compatibility, scope, and example under the relevant menu configuration setting.
* `portal-user-guide/new-dashboard/index.rst`: remove the deprecated access requirement; explain that hiding the built-in entry does not revoke dashboard permissions.
* `portal-user-guide/portal-configuration/index.rst`: replace the current `Everybody` grant/deny instructions with manifest-based Hide/Show, Visible/Hidden status, retained configuration rows, homepage fallback, and direct-URL semantics. Explain that the Sidebar screen prevents hiding/deleting its last visible row; this does not guarantee individual user access.
* Follow RST heading/directive rules and Portal product naming. Validate the affected documentation without Sphinx warnings when implementing.

## 6. Acceptance Contract

| Shared row state | Configuration action | Result |
| :--- | :--- | :--- |
| Visible, with another existing shared item remaining visible | Hide | Selected entry gets `"visibility": "false"` |
| Last visible shared item | Hide/Delete disabled; handlers also reject | Configuration unchanged |
| Hidden, including a legacy all-hidden configuration | Show | Selected entry gets `"visibility": "true"` |

The same visibility rules apply to standard, dashboard, custom, and callable shared rows. Existing Edit/Delete restrictions remain unchanged; hidden entries stay configurable and reorderable.

Done means the supplied manifest hides both built-ins without hiding independently visible dashboard entries, Sidebar Hide/Delete actions cannot remove the last visible row, and Show makes another row available. Preserve existing security and safe homepage fallback. No new dashboard-access permission or role-wide permission mutation remains.

## 7. Plan Changes

Keep this section as a short, dated decision log. Update the main sections to the latest agreed requirements; record what changed and why here instead of keeping conflicting implementation instructions. Append a new dated entry for later revisions; do not duplicate the full plan.

### 2026-11-27 — Keep One Menu Visible (Simplified)

* Prevent hiding/deleting the last visible row in the Sidebar screen: disable the actions and recheck in the existing handlers.
* Limit the implementation to one shared handler check, action rendering, a localized message, and focused tests.
* This replaces the earlier broader proposal: no new import validation, source-management changes, or cross-node locking. Existing permissions and homepage fallback remain unchanged.

### 2026-11-27 — Share Sidebar Persistence

* Assume one Sidebar administrator. Hide/Show and drag/drop save order and visibility from the same configuration list; no separate visibility writer or stale-view merge. The last-visible guard still checks freshly loaded rows.

### 2026-11-27 — Reuse Dashboard Fallback

* Reuse the existing `dashboard/noPermission` message in the Dashboard template instead of a separate dialog. Leave `AccessFullProcessList` behavior unchanged.
* The fallback shows only the existing message, without a dedicated configuration link.

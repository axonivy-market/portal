package com.axonivy.portal.menu.management.enums;

public enum ContentState {

  DASHBOARD_CREATE(
      true,  // showDashboardSelection
      false, // showProcessSelection
      false, // showExternalLinkInput
      false, // showStaticPageInput

      true,  // enableDashboardSelection
      false, // enableProcessSelection
      false, // enableExternalLinkInput
      false, // enableStaticPageInput
      false, // enableIconSelection
      false, // enableTitleInput
      false, // enablePermissionSelection
      true   // enableMultiLanguage
  ), DASHBOARD_EDIT(
      true,  // showDashboardSelection
      false, // showProcessSelection
      false, // showExternalLinkInput
      false, // showStaticPageInput

      true, // enableDashboardSelection
      false, // enableProcessSelection
      false, // enableExternalLinkInput
      false, // enableStaticPageInput
      false, // enableIconSelection
      false, // enableTitleInput
      false, // enablePermissionSelection
      true   // enableMultiLanguage
  ), EXTERNAL_LINK_CREATE(
      false, // showDashboardSelection
      false, // showProcessSelection
      true,  // showExternalLinkInput
      false, // showStaticPageInput

      false, // enableDashboardSelection
      false, // enableProcessSelection
      true,  // enableExternalLinkInput
      false, // enableStaticPageInput
      true,  // enableIconSelection
      true,  // enableTitleInput
      false, // enablePermissionSelection
      false  // enableMultiLanguage
  ), EXTERNAL_LINK_EDIT(
      false, // showDashboardSelection
      false, // showProcessSelection
      true,  // showExternalLinkInput
      false, // showStaticPageInput

      false, // enableDashboardSelection
      false, // enableProcessSelection
      true,  // enableExternalLinkInput
      false, // enableStaticPageInput
      true,  // enableIconSelection
      true,  // enableTitleInput
      false, // enablePermissionSelection
      false  // enableMultiLanguage
  ), CUSTOM_MENU_CALLABLE_EDIT(
      false, // showDashboardSelection
      true,  // showProcessSelection
      false, // showExternalLinkInput
      false, // showStaticPageInput

      false, // enableDashboardSelection
      false, // enableProcessSelection
      false, // enableExternalLinkInput
      false, // enableStaticPageInput
      false, // enableIconSelection
      false, // enableTitleInput
      false, // enablePermissionSelection
      false  // enableMultiLanguage
  ), CUSTOM_MENU_VARIABLE_CREATE(
      false, // showDashboardSelection
      true,  // showProcessSelection
      false, // showExternalLinkInput
      false, // showStaticPageInput

      false, // enableDashboardSelection
      true,  // enableProcessSelection
      false, // enableExternalLinkInput
      false, // enableStaticPageInput
      true,  // enableIconSelection
      true,  // enableTitleInput
      false, // enablePermissionSelection
      false // enableMultiLanguage
  ), CUSTOM_MENU_VARIABLE_EDIT(
      false, // showDashboardSelection
      true,  // showProcessSelection
      false, // showExternalLinkInput
      false, // showStaticPageInput

      false, // enableDashboardSelection
      true,  // enableProcessSelection
      false, // enableExternalLinkInput
      false, // enableStaticPageInput
      true,  // enableIconSelection
      true,  // enableTitleInput
      false, // enablePermissionSelection
      false  // enableMultiLanguage
  ), THIRD_PARTY_APPLICATION_EDIT(
      false, // showDashboardSelection
      false, // showProcessSelection
      true,  // showExternalLinkInput
      false, // showStaticPageInput

      false, // enableDashboardSelection
      false, // enableProcessSelection
      true,  // enableExternalLinkInput
      false, // enableStaticPageInput
      true,  // enableIconSelection
      true,  // enableTitleInput
      true,  // enablePermissionSelection
      true   // enableMultiLanguage
  ), THIRD_PARTY_APPLICATION_CREATE(
      false, // showDashboardSelection
      false, // showProcessSelection
      true,  // showExternalLinkInput
      false, // showStaticPageInput

      false, // enableDashboardSelection
      false, // enableProcessSelection
      true,  // enableExternalLinkInput
      false, // enableStaticPageInput
      true,  // enableIconSelection
      true,  // enableTitleInput
      true,  // enablePermissionSelection
      true   // enableMultiLanguage
  ), STATIC_PAGE_CREATE(
      false, // showDashboardSelection
      false, // showProcessSelection
      false, // showExternalLinkInput
      true,  // showStaticPageInput

      false, // enableDashboardSelection
      false, // enableProcessSelection
      false, // enableExternalLinkInput
      true,  // enableStaticPageInput
      true,  // enableIconSelection
      true,  // enableTitleInput
      true,  // enablePermissionSelection
      false  // enableMultiLanguage
  ), STATIC_PAGE_EDIT(
      false, // showDashboardSelection
      false, // showProcessSelection
      false, // showExternalLinkInput
      true,  // showStaticPageInput

      false, // enableDashboardSelection
      false, // enableProcessSelection
      false, // enableExternalLinkInput
      true,  // enableStaticPageInput
      true,  // enableIconSelection
      true,  // enableTitleInput
      true,  // enablePermissionSelection
      false  // enableMultiLanguage
  );

  private final boolean showDashboardSelection;
  private final boolean showProcessSelection;
  private final boolean showExternalLinkInput;
  private final boolean showStaticPageInput;

  private final boolean enableDashboardSelection;
  private final boolean enableProcessSelection;
  private final boolean enableExternalLinkInput;
  private final boolean enableStaticPageInput;
  private final boolean enableIconSelection;
  private final boolean enableTitleInput;
  private final boolean enablePermissionSelection;
  private final boolean enableMultiLanguage;

  ContentState(boolean showDashboardSelection, boolean showProcessSelection, boolean showExternalLinkInput,
      boolean showStaticPageInput, boolean enableDashboardSelection, boolean enableProcessSelection,
      boolean enableExternalLinkInput, boolean enableStaticPageInput, boolean enableIconSelection,
      boolean enableTitleInput, boolean enablePermissionSelection, boolean enableMultiLanguage) {

    this.showDashboardSelection = showDashboardSelection;
    this.showProcessSelection = showProcessSelection;
    this.showExternalLinkInput = showExternalLinkInput;
    this.showStaticPageInput = showStaticPageInput;

    this.enableDashboardSelection = enableDashboardSelection;
    this.enableProcessSelection = enableProcessSelection;
    this.enableExternalLinkInput = enableExternalLinkInput;
    this.enableStaticPageInput = enableStaticPageInput;
    this.enableIconSelection = enableIconSelection;
    this.enableTitleInput = enableTitleInput;
    this.enablePermissionSelection = enablePermissionSelection;
    this.enableMultiLanguage = enableMultiLanguage;
  }

  public boolean isShowDashboardSelection() {
    return showDashboardSelection;
  }

  public boolean isShowProcessSelection() {
    return showProcessSelection;
  }

  public boolean isShowExternalLinkInput() {
    return showExternalLinkInput;
  }

  public boolean isShowStaticPageInput() {
    return showStaticPageInput;
  }

  public boolean isEnableDashboardSelection() {
    return enableDashboardSelection;
  }

  public boolean isEnableProcessSelection() {
    return enableProcessSelection;
  }

  public boolean isEnableExternalLinkInput() {
    return enableExternalLinkInput;
  }

  public boolean isEnableStaticPageInput() {
    return enableStaticPageInput;
  }

  public boolean isEnableIconSelection() {
    return enableIconSelection;
  }

  public boolean isEnableTitleInput() {
    return enableTitleInput;
  }

  public boolean isEnablePermissionSelection() {
    return enablePermissionSelection;
  }

  public boolean isEnableMultiLanguage() {
    return enableMultiLanguage;
  }
}
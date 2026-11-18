package com.axonivy.portal.bean;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.ivy.addon.portal.generic.bean.AbsenceManagementBean;
import ch.ivy.addon.portalkit.dto.DeputyRole;
import ch.ivy.addon.portalkit.enums.DeputyRoleType;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.SubstitutionType;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.user.NewUser;

@IvyTest
class TestAbsenceManagementBean {
  private static final String USERNAME = "absence_filter_owner";
  private final AbsenceManagementBean bean = new AbsenceManagementBean();
  private IUser owner;

  @BeforeEach
  void setup() {
    owner = Sudo.get(() -> ISecurityContext.current().users()
        .create(NewUser.create(USERNAME).fullName("Searchable Substitute").toNewUser()));
  }

  @AfterEach
  void cleanup() {
    Sudo.get(() -> {
      ISecurityContext.current().users().delete(USERNAME);
      return null;
    });
  }

  @Test
  void emptyGlobalFilterMatchesEveryRow() {
    assertThat(bean.filterSubstitutes(null, null, Locale.ENGLISH)).isTrue();
    assertThat(bean.filterSubstituteForSelectedUser(null, "  ", Locale.ENGLISH)).isTrue();
  }

  @Test
  void substituteFilterMatchesRoleAndDeputyDisplayName() {
    DeputyRole row = new DeputyRole();
    row.setDeputyRoleType(DeputyRoleType.PERSONAL_TASK_PERMANENT);
    row.addDeputy(owner);

    assertThat(bean.filterSubstitutes(row, "  SEARCHABLE SUBSTITUTE  ", Locale.ENGLISH)).isTrue();
    assertThat(bean.filterSubstitutes(row, row.getDeputyRoleDisplayName(), Locale.ENGLISH)).isTrue();
    assertThat(bean.filterSubstitutes(row, "no such deputy", Locale.ENGLISH)).isFalse();
  }

  @Test
  void substitutionFilterMatchesOwnerDisplayNameUsernameAndRole() {
    IvySubstitute row = new IvySubstitute(SubstitutionType.PERMANENT);
    row.setOwnerUser(owner);

    assertThat(bean.filterSubstituteForSelectedUser(row, " SEARCHABLE SUBSTITUTE ", Locale.ENGLISH)).isTrue();
    assertThat(bean.filterSubstituteForSelectedUser(row, USERNAME.toUpperCase(Locale.ENGLISH), Locale.ENGLISH)).isTrue();
    assertThat(bean.filterSubstituteForSelectedUser(row, row.getSubstitionRoleDisplayName(), Locale.ENGLISH)).isTrue();
    assertThat(bean.filterSubstituteForSelectedUser(row, "no such owner", Locale.ENGLISH)).isFalse();
  }

  @Test
  void substitutionFilterHandlesMissingOwner() {
    IvySubstitute row = new IvySubstitute(SubstitutionType.PERMANENT);

    assertThat(bean.filterSubstituteForSelectedUser(row, "no such owner", Locale.ENGLISH)).isFalse();
    assertThat(bean.filterSubstituteForSelectedUser(row, row.getSubstitionRoleDisplayName(), Locale.ENGLISH)).isTrue();
  }
}

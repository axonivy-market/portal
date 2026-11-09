package ch.ivy.addon.portalkit.test.util;

import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.ivydata.service.impl.SubstituteService;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.SubstitutionType;

public class SubstituteTestData {
  private static final int TARGET_SUBSTITUTES_PER_USER = 12;

  public static int createSubstitutes() {
    ISecurityContext securityContext = ISecurityContext.current();
    IUser marissa = UserUtils.findUserByUsername("marissa");
    if (marissa == null) {
      throw new IllegalStateException("Marissa Accountant (marissa) must exist before creating substitute test data.");
    }

    String systemUserId = securityContext.users().system().getSecurityMemberId();
    List<IUser> users = securityContext.users().paged().stream()
        .filter(user -> !systemUserId.equals(user.getSecurityMemberId()))
        .collect(Collectors.toList());
    if (users.size() < 2) {
      throw new IllegalStateException("At least two non-system users must exist before creating substitute test data.");
    }

    int created = 0;
    SubstituteService substituteService = SubstituteService.newInstance();
    for (int ownerIndex = 0; ownerIndex < users.size(); ownerIndex++) {
      IUser owner = users.get(ownerIndex);
      List<IRole> roles = substituteService.getAllRoles(owner);
      if (!owner.equals(marissa)) {
        if (roles.isEmpty()) {
          created += createPersonalSubstitute(owner, marissa);
        } else {
          for (IRole role : roles) {
            created += createSubstitute(owner, marissa, role);
          }
        }
      }

      int missing = Math.max(0, TARGET_SUBSTITUTES_PER_USER - owner.getSubstitutes().size());
      if (roles.isEmpty()) {
        for (SubstitutionType type : SubstitutionType.values()) {
          for (int deputyOffset = 1; deputyOffset < users.size() && missing > 0; deputyOffset++) {
            IUser deputy = users.get((ownerIndex + deputyOffset) % users.size());
            int added = createPersonalSubstitute(owner, deputy, type);
            created += added;
            missing -= added;
          }
        }
      }
      for (int roleRound = 0; roleRound < roles.size() && missing > 0; roleRound++) {
        for (int deputyOffset = 1; deputyOffset < users.size() && missing > 0; deputyOffset++) {
          IUser deputy = users.get((ownerIndex + deputyOffset) % users.size());
          IRole role = roles.get((deputyOffset - 1 + roleRound) % roles.size());
          int added = createSubstitute(owner, deputy, role);
          created += added;
          missing -= added;
        }
      }
    }
    return created;
  }

  private static int createPersonalSubstitute(IUser owner, IUser deputy) {
    return createPersonalSubstitute(owner, deputy, SubstitutionType.PERMANENT);
  }

  private static int createPersonalSubstitute(IUser owner, IUser deputy, SubstitutionType type) {
    boolean exists = owner.getSubstitutes().stream()
        .anyMatch(substitute -> substitute.isPersonallyOnly()
            && type.equals(substitute.getSubstitutionType())
            && deputy.equals(substitute.getSubstituteUser()));
    if (exists) {
      return 0;
    }
    owner.createSubstitute(deputy, "", type);
    return 1;
  }

  private static int createSubstitute(IUser owner, IUser deputy, IRole role) {
    boolean exists = owner.getSubstitutes().stream()
        .anyMatch(substitute -> role.equals(substitute.getSubstitutionRole())
            && deputy.equals(substitute.getSubstituteUser()));
    if (exists) {
      return 0;
    }
    owner.createSubstitute(deputy, role, "Portal substitute test data");
    return 1;
  }
}

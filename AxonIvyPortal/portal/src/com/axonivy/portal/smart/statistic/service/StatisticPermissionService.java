package com.axonivy.portal.smart.statistic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.util.RoleUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivy.addon.portalkit.util.UserUtils;

/**
 * Who may see a chart, and who the form's autocompletes may offer.
 *
 * A chart stores permissions as member names; the form works in SecurityMemberDTOs. Converting
 * between the two - and keeping them from drifting apart on save - is all that lives here.
 */
public class StatisticPermissionService {

  private StatisticPermissionService() {}

  /** Resolves the stored member names into the DTOs the autocomplete binds to. */
  public static void applyDtos(Statistic chart) {
    chart.setPermissionDTOs(Optional.ofNullable(chart).map(Statistic::getPermissions)
        .orElse(new ArrayList<>()).stream().filter(Objects::nonNull).distinct()
        .map(StatisticPermissionService::findByName).collect(Collectors.toList()));
  }

  /** The names the form tracks as already picked, so the autocomplete stops offering them. */
  public static List<String> selectedNames(Statistic chart) {
    return Optional.ofNullable(chart).map(Statistic::getPermissionDTOs).orElse(new ArrayList<>())
        .stream().map(SecurityMemberDTO::getName).collect(Collectors.toList());
  }

  /** A leading # marks a user; everything else is a role. */
  public static SecurityMemberDTO findByName(String permission) {
    return permission.startsWith("#") ? new SecurityMemberDTO(UserUtils.findUserByUsername(permission))
        : new SecurityMemberDTO(RoleUtils.findRole(permission));
  }

  /**
   * Drops duplicate members the form may have collected and mirrors what is left back onto the
   * stored name list, so the chart is saved with exactly what the form shows.
   */
  public static void dedupeAndApply(Statistic chart) {
    List<SecurityMemberDTO> responsibles = chart.getPermissionDTOs();
    if (CollectionUtils.isEmpty(responsibles)) {
      return;
    }
    Collection<SecurityMemberDTO> distinctPermissionDTOs = responsibles.stream()
        .collect(Collectors.toMap(SecurityMemberDTO::getMemberName, responsible -> responsible,
            (responsible1, _) -> responsible1))
        .values();
    responsibles.clear();
    responsibles.addAll(distinctPermissionDTOs);
    chart.setPermissions(responsibles.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList()));
  }

  public static List<SecurityMemberDTO> completeRoles(List<String> alreadySelected, String query) {
    return RoleUtils.findRoles(null, alreadySelected, query).stream().map(SecurityMemberDTOMapper::mapFromRoleDTO)
        .collect(Collectors.toList());
  }

  public static List<SecurityMemberDTO> completeMembers(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
  }

  /** Creators are users, never roles. */
  public static List<SecurityMemberDTO> completeUsers(String query) {
    return completeMembers(query).stream().filter(SecurityMemberDTO::isUser).collect(Collectors.toList());
  }
}

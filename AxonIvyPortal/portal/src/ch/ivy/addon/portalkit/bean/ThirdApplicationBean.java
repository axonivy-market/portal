// package ch.ivy.addon.portalkit.bean;
//
// import java.io.Serializable;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Objects;
// import java.util.Optional;
// import java.util.stream.Collectors;
//
// import javax.faces.bean.ManagedBean;
//
// import com.axonivy.portal.components.dto.SecurityMemberDTO;
// import com.axonivy.portal.components.util.RoleUtils;
//
// import ch.ivy.addon.portalkit.configuration.Application;
// import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
// import ch.ivy.addon.portalkit.util.UserUtils;
//
// @ManagedBean
// public class ThirdApplicationBean implements Serializable {
//
// protected List<String> selected3rdApplicationPermissions;
//
//
// private static final long serialVersionUID = -1562635740928514918L;
//
//
// private void init3rdApplicationPermissions(Application application) {
// application.setPermissionDTOs(Optional.ofNullable(application).map(Application::getPermissions)
// .orElse(new ArrayList<>()).stream().filter(Objects::nonNull).distinct()
// .map(permission -> findSecurityMemberDtoByName(permission)).collect(Collectors.toList()));
//
// this.selected3rdApplicationPermissions = Optional.ofNullable(application).map(Application::getPermissionDTOs)
// .orElse(new ArrayList<>()).stream().map(SecurityMemberDTO::getName).collect(Collectors.toList());
// }
//
// private SecurityMemberDTO findSecurityMemberDtoByName(String permission) {
// return permission.startsWith("#") ? new SecurityMemberDTO(UserUtils.findUserByUsername(permission))
// : new SecurityMemberDTO(RoleUtils.findRole(permission));
// }
//
// public List<SecurityMemberDTO> completePermissions(String query) {
// return RoleUtils.findRoles(null, selected3rdApplicationPermissions, query).stream()
// .map(SecurityMemberDTOMapper::mapFromRoleDTO).collect(Collectors.toList());
// }
// }

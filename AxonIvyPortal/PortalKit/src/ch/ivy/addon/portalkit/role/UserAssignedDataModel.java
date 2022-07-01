package ch.ivy.addon.portalkit.role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;

public class UserAssignedDataModel extends LazyDataModel<UserHolder> {

  private static final long serialVersionUID = 6372582470269188087L;
  private static final String DEFAULT_FILTER_KEY = "displayName";
  private StringBuilder queryBuilder;
  private String fromRole;
  private Set<String> excludedUsernames;
  private Set<UserHolder> sources;
  private Set<UserHolder> addNewUsers;
  private boolean skipQueryData;

  public UserAssignedDataModel() {
    this.queryBuilder = new StringBuilder();
    this.sources = new HashSet<>();
  }

  public UserAssignedDataModel(String fromRole) {
    this.fromRole = fromRole;
    this.queryBuilder = new StringBuilder();
    this.sources = new HashSet<>();
    this.excludedUsernames = new HashSet<>();
  }

  public UserAssignedDataModel(boolean skipQueryData) {
    this.skipQueryData = skipQueryData;
    this.queryBuilder = new StringBuilder();
    this.sources = new HashSet<>();
    this.addNewUsers = new HashSet<>();
    this.excludedUsernames = new HashSet<>();
  }

  @Override
  public String getRowKey(UserHolder customer) {
    return customer.getName();
  }

  @Override
  public UserHolder getRowData(String rowKey) {
    for (UserHolder customer : this.sources) {
      if (customer.getName().equals(rowKey)) {
        return customer;
      }
    }
    return null;
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    if (skipQueryData) {
      List<UserHolder> resutl = new ArrayList<>();
      resutl.addAll(new ArrayList<>(getAddNewUsers()));
      if (filterBy != null && !filterBy.isEmpty()) {
        extractFilterValue(filterBy);
        resutl = resutl.stream().filter(filterUserByNameOrDisplayName()).collect(Collectors.toList());
      }
      return resutl.size();
    }
    return findUserByCriteria(0, -1, filterBy).size();
  }

  @Override
  public List<UserHolder> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    List<UserHolder> resutl = new ArrayList<>();
    if (skipQueryData) {
      if (Objects.isNull(addNewUsers)) {
        addNewUsers = new HashSet<UserHolder>();
      }
      resutl.addAll(new ArrayList<>(addNewUsers));
      if (filterBy != null && !filterBy.isEmpty()) {
        extractFilterValue(filterBy);
        resutl = resutl.stream().filter(filterUserByNameOrDisplayName()).collect(Collectors.toList());
      }
      resutl = resutl.stream().skip(first).limit(pageSize).collect(Collectors.toList());
    } else {
      resutl = findUserByCriteria(first, pageSize, filterBy);
    }
    this.sources.addAll(resutl);
    CollectionUtils.emptyIfNull(getExcludedUsernames()).addAll(resutl.stream()
        .map(UserHolder::getName).collect(Collectors.toList()));
    return resutl;
  }

  private Predicate<? super UserHolder> filterUserByNameOrDisplayName() {
    return user -> StringUtils.containsIgnoreCase(user.getName(), this.queryBuilder.toString())
        || StringUtils.containsIgnoreCase(user.getDisplayName(), this.queryBuilder.toString());
  }

  private List<UserHolder> findUserByCriteria(int first, int pageSize, Map<String, FilterMeta> filterBy) {
    extractFilterValue(filterBy);
    var result = SecurityService.newInstance()
        .findUsers(this.queryBuilder.toString(), first, pageSize, Arrays.asList(fromRole), null)
        .getUsers().stream()
        .map(UserHolder::new).collect(Collectors.toList());
    return result;
  }

  private void extractFilterValue(Map<String, FilterMeta> filterBy) {
    this.queryBuilder = new StringBuilder("");
    filterBy.values().stream()
        .filter(filter -> filter.getField().equalsIgnoreCase(DEFAULT_FILTER_KEY))
        .forEach(filterMeta -> {
          this.queryBuilder.append(filterMeta.getFilterValue());
        });
  }

  public void assignUserToHolderList(boolean skipQueryData, UserHolder addingUser) {
    if (Objects.isNull(addingUser)) {
      return;
    }
    setSkipQueryData(skipQueryData);
    getAddNewUsers().add(addingUser);
    this.sources.addAll(addNewUsers);
    getExcludedUsernames().add(addingUser.getName());
  }

  public void removeUserOutOfHolderList(UserHolder selectedUser) {
    if (Objects.isNull(selectedUser)) {
      return;
    }
    getAddNewUsers().remove(selectedUser);
    this.sources.remove(selectedUser);
  }

  public boolean isSkipQueryData() {
    return skipQueryData;
  }

  public void setSkipQueryData(boolean skipQueryData) {
    this.skipQueryData = skipQueryData;
  }

  public String getFromRole() {
    return fromRole;
  }

  public void setFromRole(String fromRole) {
    this.fromRole = fromRole;
  }

  public Set<String> getExcludedUsernames() {
    return excludedUsernames;
  }

  public void setExcludedUsernames(Set<String> excludedUsernames) {
    this.excludedUsernames = excludedUsernames;
  }

  public Set<UserHolder> getAddNewUsers() {
    if (Objects.isNull(addNewUsers)) {
      addNewUsers = new HashSet<>();
    }
    return addNewUsers;
  }

  public void setAddNewUsers(Set<UserHolder> addNewUsers) {
    this.addNewUsers = addNewUsers;
  }
}

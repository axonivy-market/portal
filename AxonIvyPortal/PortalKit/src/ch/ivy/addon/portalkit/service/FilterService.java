package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.RemoteCase;

public class FilterService {

  private String keyword;

  public FilterService(String keyword) {
    this.keyword = keyword;
  }

  public List<RemoteCase> filterCases(List<RemoteCase> cases) {
    Stream<RemoteCase> filteredStream =
        cases.stream().filter(
            remoteCase -> containsKeyword(remoteCase.getName()) || containsKeyword(remoteCase.getDescription())
                || containsKeyword(remoteCase.getCustomVarCharField1())
                || containsKeyword(remoteCase.getCustomVarCharField2())
                || containsKeyword(remoteCase.getCustomVarCharField3())
                || containsKeyword(remoteCase.getCustomVarCharField4())
                || containsKeyword(remoteCase.getCustomVarCharField5()));

    return filteredStream.collect(Collectors.toList());
  }

  private boolean containsKeyword(String stringToBeChecked) {
    return StringUtils.containsIgnoreCase(stringToBeChecked, keyword);
  }
}

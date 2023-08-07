package com.axonivy.portal.components.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivyteam.ivy.process.call.SubProcessCallStart;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter.SearchScope;
import ch.ivyteam.ivy.security.exec.Sudo;

/**
 * This class is to implement method to get information of Ivy
 */
public class IvyAdapterService {

  /**
   * Find the sub process in application scope then calls it with the given signature
   * with the given params. Exactly one sub process with the given signature is expected.
   *
   * @param signature The signature of the sub process to be triggered.
   * @param params The parameters to pass to the process.
   * @return The response of the process execution.
   */

  public static Map<String, Object> startSubProcessInApplication(String signature, Map<String, Object> params) {
    return startSubProcess(signature, params, SearchScope.APPLICATION);
  }

  /**
   * Find the sub process in security context scope then calls it with the given signature
   * with the given params. Exactly one sub process with the given signature is expected.
   *
   * @param signature The signature of the sub process to be triggered.
   * @param params The parameters to pass to the process.
   * @return The response of the process execution.
   */

  public static Map<String, Object> startSubProcessInSecurityContext(String signature, Map<String, Object> params) {
    return startSubProcess(signature, params, SearchScope.SECURITY_CONTEXT);
  }

  /**
   * Find the sub process in the given scope then calls it with the given signature
   * with the given params. Exactly one sub process with the given signature is expected.
   *
   * @param signature The signature of the sub process to be triggered.
   * @param params The parameters to pass to the process.
   * @param scope The search scope to find the process
   * @return The response of the process execution.
   */

  public static Map<String, Object> startSubProcessWithScope(String signature, Map<String, Object> params, SearchScope scope) {
    return startSubProcess(signature, params, scope);
  }

  private static Map<String, Object> startSubProcess(String signature, Map<String, Object> params, SearchScope scope) {
    return Sudo.get(() -> {
      var filter = SubProcessSearchFilter.create()
          .setSearchScope(scope)
          .setSignature(signature).toFilter();

      // Find sub process
      var subProcessStartList = SubProcessCallStart.find(filter);
      if (CollectionUtils.isEmpty(subProcessStartList)) {
        return null;
      }
      var subProcessStart = subProcessStartList.get(0);

      // Add param to the sub process and execute
      return Optional.ofNullable(params).map(Map::entrySet).isEmpty() ?
        subProcessStart.call().asMap() : 
          startSubProcessWithParams(subProcessStart, params);
    });
  }

  private static Map<String, Object> startSubProcessWithParams(SubProcessCallStart subProcess, Map<String, Object> params) {
    Map<String, Object> result = null;
    List<Entry<String, Object>> entryList = new ArrayList<>(params.entrySet());

    for(Entry<String, Object> entry : entryList) {
      if (entryList.indexOf(entry) != entryList.size() - 1) {
        subProcess.withParam(entry.getKey(), entry.getValue());
      } else {
        result = subProcess.withParam(entry.getKey(), entry.getValue()).call().asMap();
      }
    }

    return result;
  }
}

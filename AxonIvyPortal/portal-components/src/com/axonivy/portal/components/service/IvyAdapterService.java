package com.axonivy.portal.components.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;

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
   * Find the subprocess in application scope then calls it with the given signature
   * with the given params. Exactly one subprocess with the given signature is expected.
   *
   * @param signature The signature of the subprocess to be triggered.
   * @param params The parameters to pass to the process.
   * @return The response of the process execution.
   */

  public static Map<String, Object> startSubProcessInApplication(String signature, Map<String, Object> params) {
    return startSubProcess(signature, params, SearchScope.APPLICATION);
  }

  /**
   * Find the subprocess in security context scope then calls it with the given signature
   * with the given params. Exactly one subprocess with the given signature will be called.
   *
   * @param signature The signature of the subprocess to be triggered.
   * @param params The parameters to pass to the process.
   * @return The response of the process execution.
   */

  public static Map<String, Object> startSubProcessInSecurityContext(String signature, Map<String, Object> params) {
    return startSubProcess(signature, params, SearchScope.SECURITY_CONTEXT);
  }

  /**
   * Find subprocesses in security context then calls them with the given signature
   * with the given params. All subprocesses have the given signature will be called.
   * 
   * @param signature The signature of the subprocesses to be triggered.
   * @param params params The parameters to pass to the process.
   * @return The list of responses from the process executions.
   */
  public static List<Map<String, Object>> startSubProcessesInSecurityContext(String signature, Map<String, Object> params) {
    var x = Sudo.get(() -> {
      var result = new ArrayList<Map<String, Object>>();

      var filter = SubProcessSearchFilter.create()
          .setSearchScope(SearchScope.SECURITY_CONTEXT)
          .setSignature(signature).toFilter();

      var subProcessStartList = SubProcessCallStart.find(filter);
      if (CollectionUtils.isEmpty(subProcessStartList)) {
        return null;
      }

      subProcessStartList.forEach(subProcessStart -> {
        result.add(Optional.ofNullable(params).map(Map::entrySet).isEmpty() ?
            subProcessStart.call().asMap() : 
              startSubProcessWithParams(subProcessStart, params));
      });

      return result;
    });
    return x;
  }

  /**
   * Find subprocesses in security context then calls them with the given signature
   * with the given params until the collect condition is meet.
   * Then return the response of the subprocess which meet the collect condition
   * 
   * @param signature The signature of the subprocesses to be triggered.
   * @param params params The parameters to pass to the process.
   * @param collectCondition condition for the process to be collect.
   * @return The list of responses from the process executions.
   */
  public static Map<String, Object> startSubProcessesInSecurityContext(String signature, Map<String, Object> params, Predicate<Map<String, Object>> collectCondition) {
    return Sudo.get(() -> {
      var filter = SubProcessSearchFilter.create()
          .setSearchScope(SearchScope.SECURITY_CONTEXT)
          .setSignature(signature).toFilter();

      var subProcessStartList = SubProcessCallStart.find(filter);
      if (CollectionUtils.isEmpty(subProcessStartList)) {
        return new HashMap<>();
      }

      for (SubProcessCallStart subProcess : subProcessStartList) {
        Map<String, Object> result =  Optional.ofNullable(params).map(Map::entrySet).isEmpty() ?
            subProcess.call().asMap() : startSubProcessWithParams(subProcess, params);
        if (collectCondition.test(result)) {
          return result;
        }
      }

      return new HashMap<>();
    });
  }

  /**
   * Find the subprocess in current project and all required projects recursively scope then calls it with the given signature
   * with the given params. Exactly one subprocess with the given signature is expected.
   *
   * @param signature The signature of the subprocess to be triggered.
   * @param params The parameters to pass to the process.
   * @return The response of the process execution.
   */

  public static Map<String, Object> startSubProcessInProjectAndAllRequired(String signature, Map<String, Object> params) {
    return startSubProcess(signature, params, SearchScope.PROJECT_AND_ALL_REQUIRED);
  }
 
  private static Map<String, Object> startSubProcess(String signature, Map<String, Object> params, SearchScope scope) {
    return Sudo.get(() -> {
      var filter = SubProcessSearchFilter.create()
          .setSearchScope(scope)
          .setSignature(signature).toFilter();

      // Find subprocess
      var subProcessStartList = SubProcessCallStart.find(filter);
      if (CollectionUtils.isEmpty(subProcessStartList)) {
        return null;
      }
      var subProcessStart = subProcessStartList.get(0);

      // Add param to the subprocess and execute
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

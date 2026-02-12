package com.axonivy.portal.components.service;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.GlobalVariable;
import com.google.inject.servlet.SessionScoped;

import ch.ivyteam.di.restricted.DiCore;
import ch.ivyteam.ivy.environment.Ivy;

public class GlobalSettingService {

  private static GlobalSettingService instance;

  private GlobalSettingService() {}

  public static GlobalSettingService getInstance() {
    if (instance == null) {
      synchronized (GlobalSettingService.class) {
        if (instance == null) {
          instance = new GlobalSettingService();
        }
      }
    }
    return instance;
  }

  public String findGlobalSettingValue(GlobalVariable variable) {
    String key = variable.getKey();
    return DiCore.getGlobalInjector().getInstance(Cache.class).findValue(key);
  }

  public Boolean findGlobalSettingValueAsBoolean(GlobalVariable variable, boolean defaultIfEmpty) {
    String valueStr = findGlobalSettingValue(variable);
    return StringUtils.isEmpty(valueStr) ? defaultIfEmpty : Boolean.valueOf(valueStr);
  }

  public String findGlobalSettingValueAsString(GlobalVariable variable, String defaultIfEmpty) {
    String valueStr = findGlobalSettingValue(variable);
    return StringUtils.isEmpty(valueStr) ? defaultIfEmpty : valueStr;
  }

  @SessionScoped
  public static class Cache {

    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    String findValue(String key) {
      return cache.computeIfAbsent(key, k -> Ivy.var().get(k));
    }
  }
}

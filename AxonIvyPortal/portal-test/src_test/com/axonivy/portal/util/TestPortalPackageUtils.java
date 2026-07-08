package com.axonivy.portal.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.UserMenu;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;

import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.components.enums.MenuKind;

public class TestPortalPackageUtils {

  private TestPortalPackageUtils() {}

  public static String toJson(Object entity) {
    return BusinessEntityConverter.entityToJsonValue(entity);
  }

  public static UserMenu buildUserMenu(String id, String title, String url) {
    UserMenu userMenu = new UserMenu();
    userMenu.setId(id);
    userMenu.setTitles(List.of(new DisplayName(Locale.ENGLISH, title)));
    userMenu.setUrl(url);
    userMenu.setMenuKind(MenuKind.EXTERNAL_LINK);
    return userMenu;
  }

  public static Dashboard buildDashboard(String id, String title, String widgetId, String widgetName) {
    TaskDashboardWidget widget = new TaskDashboardWidget();
    widget.setId(widgetId);
    widget.setNames(List.of(new DisplayName(Locale.ENGLISH, widgetName)));

    Dashboard dashboard = new Dashboard();
    dashboard.setId(id);
    dashboard.setVersion(DashboardJsonVersion.LATEST_VERSION.getValue());
    dashboard.setTitles(List.of(new DisplayName(Locale.ENGLISH, title)));
    dashboard.setWidgets(List.of(widget));
    return dashboard;
  }

  public static byte[] buildZip(Map<String, String> entries) throws IOException {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos)) {
      for (Map.Entry<String, String> entry : entries.entrySet()) {
        zos.putNextEntry(new ZipEntry(entry.getKey()));
        zos.write(entry.getValue().getBytes(StandardCharsets.UTF_8));
        zos.closeEntry();
      }
      zos.finish();
      return baos.toByteArray();
    }
  }

  public static byte[] buildZipWithDirectory(String dirName, Map<String, String> entries) throws IOException {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos)) {
      zos.putNextEntry(new ZipEntry(dirName + "/"));
      zos.closeEntry();
      for (Map.Entry<String, String> entry : entries.entrySet()) {
        zos.putNextEntry(new ZipEntry(entry.getKey()));
        zos.write(entry.getValue().getBytes(StandardCharsets.UTF_8));
        zos.closeEntry();
      }
      zos.finish();
      return baos.toByteArray();
    }
  }

  public static List<String> zipEntryNames(StreamedContent content) throws IOException {
    List<String> names = new ArrayList<>();
    try (ZipInputStream zis = new ZipInputStream(content.getStream().get())) {
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        names.add(entry.getName());
      }
    }
    return names;
  }

  public static String zipEntryContent(StreamedContent content, String entryName) throws IOException {
    try (ZipInputStream zis = new ZipInputStream(content.getStream().get())) {
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        if (entry.getName().equals(entryName)) {
          return new String(zis.readAllBytes(), StandardCharsets.UTF_8);
        }
        zis.closeEntry();
      }
    }
    return null;
  }

  public static int zipEntryCount(StreamedContent content) throws IOException {
    int count = 0;
    try (ZipInputStream zis = new ZipInputStream(content.getStream().get())) {
      while (zis.getNextEntry() != null) {
        count++;
      }
    }
    return count;
  }
}

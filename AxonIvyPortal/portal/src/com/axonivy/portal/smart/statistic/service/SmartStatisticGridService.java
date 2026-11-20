package com.axonivy.portal.smart.statistic.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.smart.statistic.constant.SmartStatisticConstants;
import com.axonivy.portal.smart.statistic.dto.SmartStatisticGridItem;
import com.axonivy.portal.enums.statistic.ChartType;

import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

/**
 * Stores the user's personal chart list in an Ivy user property.
 *
 * The list holds both sections of the page. A pinned entry is rendered in the fixed row at the
 * top and keeps no geometry; an unpinned one goes into the draggable grid below and does. One
 * list rather than two means pinning is a flag flip, and a chart can never end up in both
 * sections or in neither.
 *
 * A user property is the right home here for the same reasons Portal stores private dashboards
 * that way: the data is small, only ever read for the current session user, and never queried
 * across users.
 */
public class SmartStatisticGridService {

  /**
   * The pinned row is a promise that those charts are the ones worth looking at first. Three
   * would already be a list, and a list is what the grid below is for.
   */
  public static final int MAX_PINNED = 2;

  private SmartStatisticGridService() {}

  public static List<SmartStatisticGridItem> load() {
    IUser user = sessionUser();
    if (user == null) {
      return new ArrayList<>();
    }
    String json = user.getProperty(SmartStatisticConstants.PORTAL_SMART_STATISTIC_GRID);
    if (StringUtils.isBlank(json)) {
      return new ArrayList<>();
    }
    return new ArrayList<>(
        BusinessEntityConverter.jsonValueToEntities(json, SmartStatisticGridItem.class));
  }

  public static void save(List<SmartStatisticGridItem> items) {
    IUser user = sessionUser();
    if (user == null) {
      return;
    }
    user.setProperty(SmartStatisticConstants.PORTAL_SMART_STATISTIC_GRID,
        BusinessEntityConverter.entityToJsonValue(items == null ? new ArrayList<>() : items));
  }

  /**
   * Appends a chart to the bottom of the grid, or leaves the list untouched when the chart is
   * already on it - re-saving an edited chart must not add a second copy.
   */
  public static List<SmartStatisticGridItem> addIfAbsent(List<SmartStatisticGridItem> items,
      String chartId, ChartType chartType) {
    List<SmartStatisticGridItem> all = nullSafe(items);
    if (StringUtils.isBlank(chartId) || find(all, chartId).isPresent()) {
      return all;
    }
    SmartStatisticGridItem item = new SmartStatisticGridItem(chartId);
    if (ChartType.NUMBER == chartType) {
      item.setWidth(SmartStatisticGridItem.NUMBER_WIDTH);
      item.setHeight(SmartStatisticGridItem.NUMBER_HEIGHT);
    }
    item.setAxisX(0);
    item.setAxisY(nextFreeRow(all));
    all.add(item);
    return all;
  }

  /**
   * Keeps a proposal exactly where its draft was standing: top left, at the draft's own size.
   *
   * Colliding cards are not pushed down here. {@link #unpinned} orders by row and column, and the
   * grid renders in that order, so gridstack gives this one (0,0) and resolves the rest on load -
   * which also compacts whatever no longer needs to move, something a blanket shift would get
   * wrong for cards standing beside the draft rather than under it.
   *
   * Inserted at the head of the list, not appended: the sort is stable, so an existing card also
   * sitting at (0,0) would otherwise win the tie, render first, and take the corner back.
   */
  public static List<SmartStatisticGridItem> addAtDraftPosition(List<SmartStatisticGridItem> items,
      String chartId) {
    List<SmartStatisticGridItem> all = nullSafe(items);
    if (StringUtils.isBlank(chartId) || find(all, chartId).isPresent()) {
      return all;
    }
    SmartStatisticGridItem item = new SmartStatisticGridItem(chartId);
    item.setAxisX(0);
    item.setAxisY(0);
    item.setWidth(SmartStatisticGridItem.DRAFT_WIDTH);
    item.setHeight(SmartStatisticGridItem.DRAFT_HEIGHT);
    all.add(0, item);
    return all;
  }

  public static List<SmartStatisticGridItem> remove(List<SmartStatisticGridItem> items,
      String chartId) {
    List<SmartStatisticGridItem> all = nullSafe(items);
    all.removeIf(item -> Objects.equals(item.getId(), chartId));
    return all;
  }

  /** Does nothing when the pinned row is already full - the caller warns, this stays honest. */
  public static List<SmartStatisticGridItem> pin(List<SmartStatisticGridItem> items, String chartId) {
    List<SmartStatisticGridItem> all = nullSafe(items);
    if (pinnedCount(all) >= MAX_PINNED) {
      return all;
    }
    find(all, chartId).ifPresent(item -> item.setPinned(true));
    return all;
  }

  /**
   * Unpinning drops the chart back into the grid below everything already there, because its
   * stored geometry is from before it was pinned and would likely collide.
   */
  public static List<SmartStatisticGridItem> unpin(List<SmartStatisticGridItem> items, String chartId) {
    List<SmartStatisticGridItem> all = nullSafe(items);
    find(all, chartId).ifPresent(item -> {
      item.setPinned(false);
      item.setAxisX(0);
      item.setAxisY(nextFreeRow(all));
    });
    return all;
  }

  public static int pinnedCount(List<SmartStatisticGridItem> items) {
    return (int) nullSafe(items).stream().filter(SmartStatisticGridItem::isPinned).count();
  }

  public static boolean isPinFull(List<SmartStatisticGridItem> items) {
    return pinnedCount(items) >= MAX_PINNED;
  }

  public static List<SmartStatisticGridItem> pinned(List<SmartStatisticGridItem> items) {
    return nullSafe(items).stream().filter(SmartStatisticGridItem::isPinned)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /** Sorted the way gridstack lays out, so the DOM order matches the visual order. */
  public static List<SmartStatisticGridItem> unpinned(List<SmartStatisticGridItem> items) {
    return nullSafe(items).stream().filter(item -> !item.isPinned())
        .sorted(Comparator.comparingInt(SmartStatisticGridItem::getAxisY)
            .thenComparingInt(SmartStatisticGridItem::getAxisX))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Applies the geometry gridstack posted back. Entries the browser does not know about are
   * left alone rather than dropped, so a stale request cannot silently shrink the list - and a
   * pinned chart, which is not in the grid at all, keeps whatever it had.
   */
  public static List<SmartStatisticGridItem> applyGeometry(List<SmartStatisticGridItem> items,
      List<WidgetLayout> posted) {
    List<SmartStatisticGridItem> all = nullSafe(items);
    if (CollectionUtils.isEmpty(posted)) {
      return all;
    }
    for (WidgetLayout update : posted) {
      find(all, update.getId()).ifPresent(item -> {
        item.setAxisX(update.getAxisX());
        item.setAxisY(update.getAxisY());
        item.setWidth(update.getWidth());
        item.setHeight(update.getHeight());
      });
    }
    return all;
  }

  private static Optional<SmartStatisticGridItem> find(List<SmartStatisticGridItem> items, String chartId) {
    return items.stream().filter(item -> Objects.equals(item.getId(), chartId)).findFirst();
  }

  private static int nextFreeRow(List<SmartStatisticGridItem> items) {
    return items.stream().filter(item -> !item.isPinned())
        .mapToInt(item -> item.getAxisY() + item.getHeight()).max().orElse(0);
  }

  private static List<SmartStatisticGridItem> nullSafe(List<SmartStatisticGridItem> items) {
    return items == null ? new ArrayList<>() : items;
  }

  private static IUser sessionUser() {
    if (Ivy.session().isSessionUserUnknown()) {
      return null;
    }
    return Optional.ofNullable(Ivy.session().getSessionUser()).orElse(null);
  }
}

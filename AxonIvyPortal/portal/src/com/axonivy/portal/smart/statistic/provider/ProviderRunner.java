package com.axonivy.portal.smart.statistic.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.service.IvyAdapterService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.StartParameter;
import ch.ivyteam.ivy.process.call.SubProcessCallStartEvent;

/**
 * Runs a {@code portal-statistic} provider and hands back the JSON it produced.
 *
 * The call goes through {@link IvyAdapterService} rather than {@code start.call()} so it inherits
 * the {@code Sudo} wrapper: a provider reads business data the logged-in user has no Ivy permission
 * on, and without elevation it would fail for everyone but an administrator.
 */
public class ProviderRunner {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private ProviderRunner() {}

  /**
   * The signature {@link IvyAdapterService} looks a callable up by, which is
   * {@code name(ParamType,ParamType)} - see {@code PortalCustomSignature}. Providers normally take
   * no parameters, so this is usually just {@code name()}.
   */
  public static String signatureOf(SubProcessCallStartEvent start) {
    var description = start.description();
    String params = description.in().stream()
        .map(StartParameter::typeName)
        .collect(Collectors.joining(","));
    return description.name() + "(" + params + ")";
  }

  /**
   * Runs the provider and parses its result.
   *
   * @return the parsed JSON, or empty when the provider is gone, failed, or returned nothing
   *         parseable. Never throws - a broken provider must degrade to an empty chart, not to a
   *         broken page.
   */
  public static Optional<JsonNode> fetchData(SubProcessCallStartEvent start) {
    String signature = signatureOf(start);
    try {
      Map<String, Object> response = IvyAdapterService.startSubProcessInApplication(signature, null);
      if (response == null) {
        Ivy.log().warn("Smart statistic: provider " + signature + " could not be started");
        return Optional.empty();
      }
      return parse(readPayload(start, response), signature);
    } catch (Exception e) {
      Ivy.log().error("Smart statistic: provider " + signature + " failed", e);
      return Optional.empty();
    }
  }

  /** The rows a provider returned, whether it wrapped them in an object or not. */
  public static List<JsonNode> rowsOf(JsonNode data) {
    List<JsonNode> rows = new ArrayList<>();
    if (data == null) {
      return rows;
    }
    if (data.isArray()) {
      data.forEach(rows::add);
      return rows;
    }
    if (data.isObject()) {
      // A provider that wraps its list, as {"invoices": [...]}. The first array wins.
      var names = data.fieldNames();
      while (names.hasNext()) {
        JsonNode child = data.get(names.next());
        if (child.isArray()) {
          child.forEach(rows::add);
          return rows;
        }
      }
      rows.add(data);
    }
    return rows;
  }

  /**
   * The application a resolved provider belongs to.
   *
   * {@link SubProcessCallStartEvent} carries no application of its own, but
   * {@link ProviderCollector} searches in APPLICATION scope, so anything it finds is in
   * this one. Stored on the chart because a signature is only unique within an application.
   */
  public static Long currentApplicationId() {
    return Ivy.request().getApplication().id();
  }

  public static String currentApplicationName() {
    return Ivy.request().getApplication().name();
  }

  public static Optional<JsonNode> fetchData(String signature) {
    return new ProviderCollector().providerStarts().stream()
        .filter(start -> signature.equals(signatureOf(start)))
        .findFirst()
        .flatMap(ProviderRunner::fetchData);
  }

  /**
   * Reads the result the provider declared. The declared name is preferred over guessing so a
   * provider returning several values still resolves to the one it documented; any single String
   * is accepted as a fallback for a provider that declares nothing.
   */
  private static String readPayload(SubProcessCallStartEvent start, Map<String, Object> response) {
    for (StartParameter out : start.description().out()) {
      Object value = response.get(out.name());
      if (value instanceof String text && StringUtils.isNotBlank(text)) {
        return text;
      }
    }
    return response.values().stream()
        .filter(String.class::isInstance)
        .map(String.class::cast)
        .filter(StringUtils::isNotBlank)
        .findFirst()
        .orElse(null);
  }

  private static Optional<JsonNode> parse(String json, String signature) {
    if (StringUtils.isBlank(json)) {
      Ivy.log().warn("Smart statistic: provider " + signature + " returned no data");
      return Optional.empty();
    }
    try {
      return Optional.of(MAPPER.readTree(json));
    } catch (Exception e) {
      Ivy.log().error("Smart statistic: provider " + signature + " returned unparseable JSON", e);
      return Optional.empty();
    }
  }
}

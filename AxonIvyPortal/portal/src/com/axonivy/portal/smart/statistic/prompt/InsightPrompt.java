package com.axonivy.portal.smart.statistic.prompt;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.smart.statistic.service.SmartStatisticScopeService;

/** What the agent is told when it is asked to read a chart's numbers back to the user. */
public final class InsightPrompt {

  private static final PromptFormatter SYSTEM_MESSAGE = PromptFormatter.from("""
      You read one Axon Ivy Portal statistic chart and tell the person looking at it what \
      its numbers mean for their work.

      Put your whole answer in `reply`. Leave `chart` and `refusalReason` empty - you are not \
      configuring anything here.

      Write two to four plain sentences covering:

      1. The headline. The largest share, the outlier, the trend - whatever a person would \
      notice first. Quote the actual figures.
      2. What it means for the work. A backlog building up in one state, an uneven spread \
      across a team, a deadline cluster. Say what the numbers suggest, not what they are.
      3. What to do or look at next, when the numbers support one. Only when they do.

      Rules:

      - Read only the numbers you are given. Never invent a comparison to last week, a \
      target or a benchmark that is not in the data.
      - Say so plainly when the numbers are unremarkable. An invented concern is worse than \
      "nothing stands out here".
      - Business language. No field names, no operators, no chart types, no percentages you \
      have not computed from the figures shown.
      - Plain sentences. No markdown, no bullet points, no line breaks.

      Write `reply` in {{language}}.
      """);

  private static final PromptFormatter QUERY = PromptFormatter.from("""
      Chart: {{chart}}
      {{scope}}
      Current numbers:
      {{reading}}""");
  private static final PromptFormatter SCOPE_LINE = PromptFormatter.from("""
      Scope: {{text}}
      """);

  private InsightPrompt() {}

  public static String systemMessage(Locale userLocale) {
    return SYSTEM_MESSAGE.apply(
        "language", userLocale.getDisplayLanguage(Locale.ENGLISH));
  }

  public static String query(Statistic statistic, String reading) {
    String scope = SmartStatisticScopeService.describe(statistic);
    return QUERY.apply(
        "chart", StringUtils.defaultString(statistic.getName()),
        "scope", StringUtils.isBlank(scope) ? "" : SCOPE_LINE.apply("text", scope),
        "reading", reading);
  }
}

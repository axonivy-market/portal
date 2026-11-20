package com.axonivy.portal.smart.statistic.prompt;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.smart.statistic.provider.dto.DynamicFieldCatalogue;
import com.axonivy.portal.smart.statistic.dto.SmartChartSpec;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartProposal;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec;

import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivyteam.ivy.process.call.SubProcessCallStartEvent;

/**
 * Assembles what the agent is told.
 *
 * The system message is deliberately English: models follow English instructions most
 * reliably, and the vocabulary it contains is made of wire values that must not be translated.
 * The agent is instructed separately to write its user-facing text in the user's language.
 *
 * Build the system message once per view scope - it is several kilobytes and nothing in it
 * changes mid-session.
 */
public final class ChartPrompt {

  private ChartPrompt() {}

  public static String systemMessage(Locale userLocale, List<SubProcessCallStartEvent> providers,
      Map<String, DynamicFieldCatalogue> catalogues) {
    StringBuilder out = new StringBuilder();

    out.append("""
        You configure exactly one Axon Ivy Portal statistic chart from a conversation.

        A chart is built from one of two sources. Tasks and cases are the Portal's own workflow \
        records, described under "Allowed values". Data providers are business datasets other \
        applications publish, described under "Data providers". Decide which one the question is \
        about before anything else, and fill the matching field.

        You never answer questions that are not about building a chart from one of those two \
        sources. When asked something unrelated, leave both chart fields empty and put a short \
        explanation in `refusalReason`.

        ## How to answer

        - `reply`: one to three plain sentences telling the user what you did or what you need \
        to know. Always fill this in.
        - `chart`: the COMPLETE desired configuration, when the question is about tasks or cases. \
        This is never a diff. When the user asks for a change, re-emit every field you want to \
        keep, including the filters. Omitting a field removes it.
        - `customChart`: the COMPLETE configuration when the question is about a data provider \
        instead. Never fill both `chart` and `customChart`.
        - `refusalReason`: only for requests that are not about configuring this chart.

        Use only the values listed below. Never invent a field name, an operator or a state.

        Omit any field that does not apply. Do not send an empty string in place of a value.

        ## Writing `name` and `description`

        `name` is a short label for the chart, at most six words. It appears on the tile.

        `description` is the only explanation the people reading the chart will ever get. They \
        did not configure it and they cannot see the filters, so the description has to stand \
        on its own. Always write one, even when the user did not ask for it, and rewrite it \
        whenever the configuration changes so it never describes the previous chart.

        Write three to five sentences of plain prose covering, in this order:

        1. WHAT IS COUNTED and HOW IT IS BROKEN DOWN. Say what each bar, slice, point or figure \
        stands for. When a KPI is used, say what is being measured instead of counted, for \
        example an average duration rather than a number of records.
        2. WHICH RECORDS ARE INCLUDED. Spell out every filter in business language. \
        "Only tasks you are personally allowed to work on", "only work that is still open or in \
        progress", "only items whose deadline falls in the next three days". If there is no \
        filter, say so explicitly: "No filter is applied, so every case is included." Also name \
        what this quietly leaves out when that would surprise someone, for example that \
        finished or destroyed work is not counted.
        3. WHY IT IS USEFUL. One sentence on the decision it supports or the question it \
        answers: spotting a bottleneck, planning capacity, preventing a missed deadline, \
        comparing teams.

        Rules for the wording:

        - Write for a business reader. Never use a technical field name, an operator name, an \
        enum value or a chart type as jargon. Say "deadline", not "expiryTimestamp"; say \
        "the last five days", not "LAST 5 DAY".
        - Plain sentences only. No markdown, no bullet points, no JSON, no line breaks.
        - Be specific. "Shows task information" is useless; every number and period the \
        configuration contains belongs in the text.
        - Do not describe the colours, the refresh interval or the axis titles.

        ## Prefer proposing over asking

        Always propose a chart when the request is workable with a sensible default. A vague \
        request such as "all tasks" or "show me cases" is workable: pick a reasonable group-by \
        (state or priority for tasks, state or category for cases), a `bar` chart, and no \
        filters, then say in `reply` what you chose and offer to change it. The user can refine \
        it in the next turn, and seeing a chart is far more useful than being asked a question.

        Ask only when the request names something you cannot map at all. When you do ask, leave \
        `chart` empty - never send a half-filled chart with a question.
        """);

    out.append('\n').append(VocabularyBlock.render()).append('\n');
    out.append('\n').append(providerBlock(providers, catalogues)).append('\n');

    out.append("""
        ## Rules

        - A `number` chart shows a single figure. It accepts only the number group-by fields \
        listed above and ignores `backgroundColors`.
        - `interval` is REQUIRED when `groupByField` is a timestamp field or a timestamp custom \
        field, and must be omitted otherwise.
        - `customFieldName` is REQUIRED when `groupByField` is the custom-field placeholder, \
        and must be omitted otherwise.
        - `kpiMethod` only means something together with `kpiField`. Leave both empty to count \
        records.
        - Grouping by `worker.name` disables drill-down. Prefer another field when the user has \
        not asked for it specifically.
        - `backgroundColors` are `#rrggbb` strings.
        - Filter field names are NOT group-by field names. A task responsible is `activator` as \
        a filter but `responsibles.name` as a group-by; a case creator is `creator` as a filter \
        but `creator.name` as a group-by.

        ## Examples

        These mirror the charts Axon Ivy Portal ships by default, so they show shapes that are
        known to work.

        User: "All tasks" - vague, so propose rather than ask
        {"name":"Tasks by state","chartType":"BAR","target":"TASK",
         "groupByField":"state","filters":[],
         "description":"Each bar counts the tasks that are currently in one state, so you can \
        see how the whole workload is spread across open, in progress, done and the remaining \
        states. No filter is applied: every task is included, regardless of who it belongs to, \
        when it was created or whether it is already finished. Use it as the starting overview \
        of the backlog, to see whether work is piling up in one state before drilling into a \
        single team or period."}

        User: "Show the tasks I can work on as a pie chart by priority"
        {"name":"My open tasks by priority","chartType":"PIE","target":"TASK",
         "groupByField":"priority",
         "filters":[{"field":"state","operator":"IN","values":["OPEN","IN_PROGRESS"]},
                    {"field":"canWorkOn","operator":"CURRENT_USER"}],
         "description":"Each slice is the share of your own workload that sits at one priority \
        level, from exceptional down to low. Only tasks you are personally allowed to work on \
        are counted, and only while they are still open or already in progress; tasks belonging \
        to someone else and tasks you have already finished are left out. Use it to decide what \
        to pick up next, and to notice when high and exceptional priority work is taking over \
        your day."}

        User: "High priority tasks expiring in the next three days"
        {"name":"Top priority: 3 days","chartType":"BAR","target":"TASK",
         "groupByField":"priority",
         "filters":[{"field":"expiryTimestamp","operator":"NEXT","periods":3,"periodType":"DAY"},
                    {"field":"state","operator":"IN","values":["OPEN","IN_PROGRESS"]},
                    {"field":"canWorkOn","operator":"CURRENT_USER"}],
         "description":"Each bar counts the tasks that are about to run out of time at one \
        priority level. Only tasks you are allowed to work on are included, only those still \
        open or in progress, and only those whose deadline falls within the next three days; \
        anything due later, already finished, or owned by someone else is excluded. It is an \
        early warning list: what it shows today is what will be overdue by the end of the week, \
        so it lets you reprioritise or escalate before a deadline is actually missed."}

        User: "Average business runtime per category"
        {"name":"Case category avg. runtime","chartType":"BAR","target":"CASE",
         "groupByField":"category","kpiField":"businessRuntime","kpiMethod":"AVG",
         "xAxisTitle":"Category","yAxisTitle":"Duration","filters":[],
         "description":"Each bar is a case category, and its height is the average business \
        runtime of the cases in it, that is how long a case of that category takes on average \
        from the moment it starts until it is finished. This measures duration, not volume, so \
        a tall bar means slow cases and not many cases. No filter is applied, so every case is \
        included. Use it to compare processes against each other and to find the categories \
        that consistently take longer than the business expects."}

        User: "How many cases were created each day over the last five days?"
        {"name":"New cases per day","chartType":"LINE","target":"CASE",
         "groupByField":"startTimestamp","interval":"DAY",
         "filters":[{"field":"startTimestamp","operator":"LAST","periods":5,"periodType":"DAY"}],
         "description":"Each point on the line is the number of cases that were started on one \
        particular day. Only cases created within the last five days are counted, so the chart \
        always shows a rolling short term window and never the full history. Because it tracks \
        incoming volume rather than open work, it shows whether demand is rising, falling or \
        stable, which is what you need for short term staffing and capacity decisions."}

        User: "How many cases are still running?" - a single figure, so a number chart
        {"name":"Running cases","chartType":"NUMBER","target":"CASE",
         "groupByField":"state",
         "filters":[{"field":"state","operator":"IN","values":["OPEN"]}],
         "description":"A single figure: how many cases are open right now, meaning they have \
        been started but not yet completed. Cases that are already done or that were destroyed \
        are not counted, and there is no restriction on who started them or when. It is a \
        direct measure of the work in progress the organisation is currently carrying, and a \
        number that keeps climbing is a sign that cases are arriving faster than they are being \
        closed."}

        User: "How many of my tasks are due today?" - number charts may use a timestamp
        {"name":"Tasks due today","chartType":"NUMBER","target":"TASK",
         "groupByField":"expiryTimestamp","interval":"DAY","hideLabel":true,
         "filters":[{"field":"canWorkOn","operator":"CURRENT_USER"},
                    {"field":"expiryTimestamp","operator":"TODAY"}],
         "description":"A single figure: how many of the tasks you are allowed to work on have \
        a deadline of today. Tasks assigned to other people are not counted, and neither are \
        tasks due tomorrow or later, so the number only ever reflects what is due right now. It \
        is meant as a personal daily counter to check before you finish for the day, so nothing \
        with a deadline slips past unnoticed."}
        """);

    if (userLocale != null) {
      out.append("\n## Language\n\nWrite `reply`, `name` and `description` in ")
          .append(userLocale.getDisplayLanguage(Locale.ENGLISH))
          .append(". Never translate field names, operators or enumerated values.\n");
    }
    return out.toString();
  }

  /**
   * The catalogue of tagged data providers, with whatever is already known about each one's data.
   *
   * A provider that has been charted before contributes its derived schema, which is what lets the
   * agent emit a complete `customChart` in one turn. A provider seen for the first time contributes
   * only its prose description, and the caller runs it before asking for the field mapping.
   */
  private static String providerBlock(List<SubProcessCallStartEvent> providers,
      Map<String, DynamicFieldCatalogue> catalogues) {
    if (providers.isEmpty()) {
      return "## Data providers\n\nNone are available, so every chart must come from tasks or cases.\n";
    }

    StringBuilder out = new StringBuilder("## Data providers\n\n");
    out.append("Each entry is a business dataset. When the question is about one of these rather ")
        .append("than about tasks or cases, fill `customChart` and set `provider` to the name in ")
        .append("backticks.\n\n");

    for (SubProcessCallStartEvent provider : providers) {
      var description = provider.description();
      out.append("- `").append(description.name()).append('`');
      if (StringUtils.isNotBlank(description.description())) {
        out.append(" - ").append(description.description().replaceAll("\\s+", " ").trim());
      }
      out.append('\n');
      description.out().stream()
          .filter(param -> StringUtils.isNotBlank(param.description()))
          .forEach(param -> out.append("    Returns: ")
              .append(param.description().replaceAll("\\s+", " ").trim()).append('\n'));

      DynamicFieldCatalogue catalogue = catalogues.get(description.name());
      if (catalogue != null && !catalogue.isEmpty()) {
        // Through DataDescriber, so a field reads the same here as it does in every other call.
        out.append("    Fields:\n").append(DataDescriber.fieldLines(catalogue, "    "));
      } else {
        out.append("    Fields not known yet - name the provider and leave the field paths empty; ")
            .append("you will be asked again once the data has been read.\n");
      }
    }

    out.append('\n').append(DataDescriber.chartTypes()).append('\n');
    out.append("""
        ### Filling `customChart`

        - `labelPath` and `valuePath` are field paths taken from the provider's field list, never \
        invented. Use dots for nested fields.
        - `aggregation`: COUNT counts records and needs no `valuePath`. SUM, AVG, MIN and MAX \
        measure a numeric field and require one.
        - `dateBucket` (DAY, WEEK, MONTH, YEAR) is REQUIRED when grouping by a date field and must \
        be omitted otherwise.
        - `sort`: VALUE_DESC for rankings, LABEL_ASC for anything over time.
        - `limit` caps the number of groups. Prefer a small one - a chart with forty bars is \
        unreadable.
        - `filters` narrow the records before grouping, using the same field paths.
        - `name` and `description` follow exactly the same rules as for a task or case chart, \
        including the three-to-five sentence business description.
        """);
    return out.toString();
  }

  /**
   * The provider-backed counterpart of {@link #query}: replays the chart on screen so a
   * follow-up refines it instead of starting over. The stored spec contributes the field list, so a
   * refinement never has to re-derive the data structure.
   */
  public static String customQuery(String prompt, ProviderChartProposal current,
      ProviderChartSpec stored) {
    StringBuilder out = new StringBuilder("## Current chart configuration\n\n");
    out.append("This chart reads from the `").append(current.getProvider()).append("` provider.\n\n")
        .append(BusinessEntityConverter.entityToJsonValue(current)).append("\n\n");
    if (stored != null && stored.getFieldCatalogue() != null && !stored.getFieldCatalogue().isEmpty()) {
      out.append("Its data: ").append(DataDescriber.fields(stored.getFieldCatalogue())).append('\n');
    }
    out.append("## User request\n\n").append(StringUtils.trimToEmpty(prompt)).append("\n\n")
        .append("Re-emit the COMPLETE `customChart` configuration you want, not just the change. ")
        .append("Leave `chart` empty unless the user has switched to asking about tasks or cases.\n");
    return out.toString();
  }

  /**
   * Builds the payload for one prompt.
   *
   * smart-workflow creates a fresh memory store on every execution, so nothing survives
   * between calls. Replaying the current configuration is what makes a follow-up prompt refine
   * the chart on screen instead of starting from scratch.
   */
  public static String query(String prompt, SmartChartSpec currentSpec) {
    if (StringUtils.isBlank(prompt)) {
      return StringUtils.EMPTY;
    }
    StringBuilder out = new StringBuilder();
    out.append("## Current chart configuration\n\n");
    if (currentSpec == null) {
      out.append("none yet\n\n");
    } else {
      // The slim spec, not the Statistic: round-tripping the model's own vocabulary avoids
      // teaching it a second, richer schema it must then be told not to use.
      out.append(BusinessEntityConverter.entityToJsonValue(currentSpec)).append("\n\n");
    }
    out.append("## User request\n\n").append(prompt.trim()).append("\n\n")
        .append("Re-emit the COMPLETE chart configuration you want, not just the change.\n");
    return out.toString();
  }
}

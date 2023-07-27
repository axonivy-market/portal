package ch.ivy.addon.portalkit.statistics;

public enum NewStatisticAggregate {
  STATE("state"), BUSINESS_STATE("businessState"), PRIORITY("priority "), CATEGORY("category"), STAGE(
      "stage"), IS_BUSINESS_CASE("isBusinessCase"), CREATOR_NAME("creator.name"), OWNER_NAME(
          "owner.name"), BUSINESS_RUNTIME("businessRuntime"), WORKING_TIME("workingTime"), START_TIME_STAMP(
              "startTimestamp"), MODIFIED_TIME_STAMP("modifiedTimestamp"), END_TIME_STAMP(
                  "endTimestamp"), STRING_CUSTOM_FIELDS("customFields.strings.*"), NUMBERS_CUSTOM_FIELD(
                      "customFields.numbers.*"), TIME_STAMPS_CUSTOM_FIELD("customFields.timestamps.*");

  private String agg;

  NewStatisticAggregate(String agg) {
    this.agg = agg;
  }

  public String getAggregate() {
    return this.agg;
  }
}

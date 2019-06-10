package ch.ivy.gawfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.IParameter;
import ch.ivyteam.ivy.process.call.ISubProcessStart;
import ch.ivyteam.ivy.process.call.SubProcessRunner;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;

public class DataProvider {
  private static final String RESULT_PARAMETER_TYPE = "List<String>";
  private static final String RESULT_PARAMETER_NAME = "data";
  private final List<String> optionStrs;

  private DataProvider(List<String> optionStrs) {
    this.optionStrs = optionStrs;
  }

  public static DataProvider create(List<String> optionStrs) {
    return new DataProvider(optionStrs);
  }

  public List<String> execute() {
    Ivy.log().debug("executeDataProvider");
    ISubProcessStart subProcessStart = findSubProcessStart(optionStrs);
    if (subProcessStart == null) {
      return optionStrs;
    }
    Ivy.log().debug("call data provider " + subProcessStart.getProjectName() + "/" + subProcessStart.getProcessPath() + "/"
            + subProcessStart.getSignature());
    List<String> values = executeSubProcess(subProcessStart);
    Ivy.log().debug("provided data is " + values);
    return values;
  }

  private ISubProcessStart findSubProcessStart(List<String> optionStrs) {

    if (optionStrs == null || optionStrs.size() < 2) {
      Ivy.log().debug("no data provider configured");
      return null;
    }

    SubProcessSearchFilter configuration =
        SubProcessSearchFilter.create().setLibraryId(optionStrs.get(0)).setSignature(optionStrs.get(1)).toFilter();

    List<ISubProcessStart> subProcessStarts = SubProcessRunner.findSubProcessStarts(configuration);
    return subProcessStarts.stream().filter(this::hasCorrectResultParameter).findAny().orElse(null);
  }

  private boolean hasCorrectResultParameter(ISubProcessStart start) {
    List<IParameter> outputParameters = start.getOutputParameters();
    if (outputParameters.size() != 1) {
      return false;
    }
    IParameter outputParameter = outputParameters.get(0);
    return outputParameter.getName().equals(RESULT_PARAMETER_NAME)
        && outputParameter.getType().equals(RESULT_PARAMETER_TYPE);
  }

  private List<String> executeSubProcess(ISubProcessStart subProcessStart) {
    Map<String, Object> result = SubProcessRunner.execute(subProcessStart);
    Object data = result.get(RESULT_PARAMETER_NAME);
    if (data == null || !(data instanceof List)) {
      Ivy.log().debug("no data provided");
      return optionStrs;
    }
    @SuppressWarnings("unchecked")
    List<String> values = new ArrayList<>((List<String>) data);
    return values;
  }
}

package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

@IvyProcessTest
class TestTaskService {

  private TaskService service;
  private String createdCaseUuid;

  private static final BpmProcess CREATE_TEST_DATA = BpmProcess.name("CreateTestData");

  @BeforeEach
  void setUp(BpmClient client) {
    service = TaskService.newInstance();
    var result = client.start().process(CREATE_TEST_DATA.elementName("CategoriedLeaveRequest.ivp")).execute();
    createdCaseUuid = result.workflow().activeCase().uuid();
  }

  @AfterEach
  void tearDown() {
    Sudo.get(() -> {
      var theCase = Ivy.wf().getCaseQueryExecutor()
          .getFirstResult(CaseQuery.create().where().uuid().isEqual(createdCaseUuid));
      if (theCase != null) {
        theCase.destroy();
      }
      return null;
    });
  }

  @ParameterizedTest
  @MethodSource("taskQueryScenarios")
  void findTasksByCriteria(boolean isAdmin, int expectedDoneCount, int expectedStandardCount) {
    assertThat(service.findTasksByCriteria(criteriaFor(TaskState.DONE, isAdmin), 0, 10).getTasks()).hasSize(expectedDoneCount);
    assertThat(service.findTasksByCriteria(criteriaFor(TaskSearchCriteria.STANDARD_STATES, isAdmin), 0, 10).getTasks()).hasSize(expectedStandardCount);
  }

  @ParameterizedTest
  @MethodSource("taskQueryScenarios")
  void countTasksByCriteria(boolean isAdmin, int expectedDoneCount, int expectedStandardCount) {
    assertThat(service.countTasksByCriteria(criteriaFor(TaskState.DONE, isAdmin)).getTotalTasks()).isEqualTo(expectedDoneCount);
    assertThat(service.countTasksByCriteria(criteriaFor(TaskSearchCriteria.STANDARD_STATES, isAdmin)).getTotalTasks()).isEqualTo(expectedStandardCount);
  }

  @SuppressWarnings("unused")
  static Stream<Arguments> taskQueryScenarios() {
    return Stream.of(
        Arguments.of(true,  1, 3),   // admin: sees done tasks and all suspended
        Arguments.of(false, 0, 0)    // normal user: sees no tasks without involvement
    );
  }

  private static TaskSearchCriteria criteriaFor(TaskState state, boolean isAdmin) {
    return criteriaFor(List.of(state), isAdmin);
  }

  private static TaskSearchCriteria criteriaFor(List<TaskState> states, boolean isAdmin) {
    var criteria = new TaskSearchCriteria();
    criteria.setIncludedStates(states);
    criteria.setAdminQuery(isAdmin);
    return criteria;
  }
}

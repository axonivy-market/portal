package com.axonivy.portal.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;
import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.dto.AISearchResponse;
import com.axonivy.portal.enums.PortalCustomSignature;
import com.axonivy.portal.enums.PortalSystemMessage;
import com.axonivy.portal.page.AISearch.SearchResult;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class AISearchBean {

    private static final double CONFIDENCE_THRESHOLD = 0.7;
    private static final int MAX_CLARIFICATION_ROUNDS = 2;

    private String rephrased;
    private String clarification;
    private String clarificationAnswer;
    private boolean needsClarification;
    private int clarificationCount;
    private double confidence;
    private boolean searched;
    private List<SearchResult> results = new ArrayList<>();

    public void searchWithAI(String keyword) {
        searched = true;
        clarificationCount = 0;
        needsClarification = false;
        clarificationAnswer = null;
        results.clear();

        try {
            AISearchResponse response = getAIResponse(keyword);
            applyResponse(response);
        } catch (Exception e) {
            Ivy.log().error("AI search failed for keyword: " + keyword, e);
        }
    }

    public void answerClarification(String originalKeyword) {
        int currentRound = ++clarificationCount;
        String combinedQuery = "Original query: \"" + originalKeyword + "\"\nClarification answer: \"" + clarificationAnswer + "\"";
        clarificationAnswer = null;

        try {
            AISearchResponse response = getAIResponse(combinedQuery);

            if (currentRound >= MAX_CLARIFICATION_ROUNDS) {
                // Force apply filters regardless of confidence
                needsClarification = false;
                rephrased = response.getRephrased();
                confidence = response.getConfidence();
                clarification = null;
                populateResults(response);
            } else {
                applyResponse(response);
            }
        } catch (Exception e) {
            Ivy.log().error("AI clarification failed", e);
        }
    }

    private void applyResponse(AISearchResponse response) {
        rephrased = response.getRephrased();
        confidence = response.getConfidence();

        if (response.getConfidence() < CONFIDENCE_THRESHOLD) {
            needsClarification = true;
            clarification = response.getClarification();
        } else {
            needsClarification = false;
            clarification = null;
            populateResults(response);
        }
    }

    private void populateResults(AISearchResponse response) {
        results.clear();
        // TODO: apply taskFilters and caseFilters from response to populate results
        Ivy.log().info("Applying filters — taskFilters: " + response.getTaskFilters() + ", caseFilters: " + response.getCaseFilters());
    }

    private AISearchResponse getAIResponse(String query) throws Exception {
        String portalSignature = PortalCustomSignature.INVOKE_SMART_WORKFLOW_AGENT.getSignature();
        String systemMessage = PortalSystemMessage.FILTER_CREATION_INSTRUCTION1.getMessage();

        Map<String, Object> params = new HashMap<>();
        params.put("query", query);
        params.put("systemMessage", systemMessage);
        params.put("resultType", String.class);

        Map<String, Object> result = IvyAdapterService.startSubProcessInSecurityContext(portalSignature, params);
        String resultJson = result.get("resultObject").toString();

        return BusinessEntityConverter.jsonValueToEntity(resultJson, AISearchResponse.class);
    }

    public String getRephrased() { return rephrased; }
    public void setRephrased(String rephrased) { this.rephrased = rephrased; }

    public String getClarification() { return clarification; }
    public void setClarification(String clarification) { this.clarification = clarification; }

    public String getClarificationAnswer() { return clarificationAnswer; }
    public void setClarificationAnswer(String clarificationAnswer) { this.clarificationAnswer = clarificationAnswer; }

    public boolean isNeedsClarification() { return needsClarification; }
    public void setNeedsClarification(boolean needsClarification) { this.needsClarification = needsClarification; }

    public int getClarificationCount() { return clarificationCount; }
    public void setClarificationCount(int clarificationCount) { this.clarificationCount = clarificationCount; }

    public double getConfidence() { return confidence; }
    public void setConfidence(double confidence) { this.confidence = confidence; }

    public boolean isSearched() { return searched; }
    public void setSearched(boolean searched) { this.searched = searched; }

    public List<SearchResult> getResults() { return results; }
    public void setResults(List<SearchResult> results) { this.results = results; }
}



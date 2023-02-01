package com.github.thefuckingcode.choerodonplugin.vo;

import java.util.ArrayList;
import java.util.List;

public class SubmitWorkTimeVO {
    private String issueId;
    private String projectId;
    private String residualPrediction;
    private String startDate;
    private List<String> workHoursAttachments;
    private Integer workTime;

    public SubmitWorkTimeVO() {
        this.workHoursAttachments = new ArrayList<>();
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getResidualPrediction() {
        return residualPrediction;
    }

    public void setResidualPrediction(String residualPrediction) {
        this.residualPrediction = residualPrediction;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<String> getWorkHoursAttachments() {
        return workHoursAttachments;
    }

    public void setWorkHoursAttachments(List<String> workHoursAttachments) {
        this.workHoursAttachments = workHoursAttachments;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }
}

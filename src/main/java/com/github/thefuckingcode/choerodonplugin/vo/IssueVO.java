package com.github.thefuckingcode.choerodonplugin.vo;

public class IssueVO {
    private String issueNum;
    private String summary;
    private String status;
    private String registeredHours;
    private String remainHours;

    public String getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(String issueNum) {
        this.issueNum = issueNum;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegisteredHours() {
        return registeredHours;
    }

    public void setRegisteredHours(String registeredHours) {
        this.registeredHours = registeredHours;
    }

    public String getRemainHours() {
        return remainHours;
    }

    public void setRemainHours(String remainHours) {
        this.remainHours = remainHours;
    }
}

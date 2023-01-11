package com.github.thefuckingcode.choerodonplugin.vo;

import javax.swing.*;
import java.math.BigDecimal;

import static com.intellij.icons.AllIcons.ToolbarDecorator.AddIcon;

public class IssueVO {
    private String issueNum;
    private String summary;
    private String status;
    private BigDecimal spentWorkTime;
    private BigDecimal remainingTime;
    private JLabel registerWorkTimeLabel;

    public IssueVO() {
        this.registerWorkTimeLabel = new JLabel();
        registerWorkTimeLabel.setIcon(AddIcon);
        registerWorkTimeLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public JLabel getRegisterWorkTimeLabel() {
        return registerWorkTimeLabel;
    }

    public void setRegisterWorkTimeLabel(JLabel registerWorkTimeLabel) {
        this.registerWorkTimeLabel = registerWorkTimeLabel;
    }

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

    public BigDecimal getSpentWorkTime() {
        return spentWorkTime;
    }

    public void setSpentWorkTime(BigDecimal spentWorkTime) {
        this.spentWorkTime = spentWorkTime;
    }

    public BigDecimal getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(BigDecimal remainingTime) {
        this.remainingTime = remainingTime;
    }
}

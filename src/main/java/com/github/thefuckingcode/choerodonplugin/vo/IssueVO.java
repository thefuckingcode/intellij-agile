package com.github.thefuckingcode.choerodonplugin.vo;

import com.github.thefuckingcode.choerodonplugin.ui.WorkHourDialog;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

import static com.intellij.icons.AllIcons.ToolbarDecorator.AddIcon;

public class IssueVO {
    private String issueId;
    private String issueNum;
    private String summary;
    private String status;
    private BigDecimal spentWorkTime;
    private BigDecimal remainingTime;
    private JButton submitWorkTimeButton;

    public IssueVO() {
        submitWorkTimeButton = new JButton();
        submitWorkTimeButton.setIcon(AddIcon);
        submitWorkTimeButton.setHorizontalAlignment(JLabel.CENTER);
    }

    public JButton getSubmitWorkTimeButton() {
        return submitWorkTimeButton;
    }

    public void setSubmitWorkTimeButton(JButton submitWorkTimeButton) {
        this.submitWorkTimeButton = submitWorkTimeButton;
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

    public static class SubmitWorkHourEditor extends DefaultCellEditor {
        private JButton submitButton;

        public SubmitWorkHourEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        public SubmitWorkHourEditor(JComboBox comboBox) {
            super(comboBox);
        }

        public SubmitWorkHourEditor(JTextField textField) {
            super(textField);
        }


        /*
        重写编辑器方法，返回一个按钮给JTable
        */
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            submitButton = (JButton) value;
            return submitButton;
        }

        public JButton getSubmitButton() {
            return submitButton;
        }

        public void setSubmitButton(JButton submitButton) {
            this.submitButton = submitButton;
        }
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }
}

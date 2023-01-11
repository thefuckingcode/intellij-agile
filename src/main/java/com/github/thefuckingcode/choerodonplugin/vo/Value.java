package com.github.thefuckingcode.choerodonplugin.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * @author superlee
 * @since 2022-11-02
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Value implements Serializable {
    private String valueStr;
    private List<String> valueStrList;
    private String valueId;
    private List<String> valueIdList;
    private String noEncryptId;
    private List<String> noEncryptIdList;

    private BigDecimal valueDecimal;
    private Date valueDate;
    private String valueSpecial;
    private List<? extends Object> objectList;
    private List<IssueTypeProject> issueTypeProjectList;

    public List<IssueTypeProject> getIssueTypeProjectList() {
        return issueTypeProjectList;
    }

    public void setIssueTypeProjectList(List<IssueTypeProject> issueTypeProjectList) {
        this.issueTypeProjectList = issueTypeProjectList;
    }

    public List<? extends Object> getObjectList() {
        return objectList;
    }

    public Value setObjectList(List<? extends Object> objectList) {
        this.objectList = objectList;
        return this;
    }

    public String getValueStr() {
        return valueStr;
    }

    public Value setValueStr(String valueStr) {
        this.valueStr = valueStr;
        return this;
    }

    public List<String> getValueStrList() {
        return valueStrList;
    }

    public Value setValueStrList(List<String> valueStrList) {
        this.valueStrList = valueStrList;
        return this;
    }

    public String getValueId() {
        return valueId;
    }

    public Value setValueId(String valueId) {
        this.valueId = valueId;
        return this;
    }

    public List<String> getValueIdList() {
        return valueIdList;
    }

    public Value setValueIdList(List<String> valueIdList) {
        this.valueIdList = valueIdList;
        return this;
    }

    public String getNoEncryptId() {
        return noEncryptId;
    }

    public Value setNoEncryptId(String noEncryptId) {
        this.noEncryptId = noEncryptId;
        return this;
    }

    public List<String> getNoEncryptIdList() {
        return noEncryptIdList;
    }

    public Value setNoEncryptIdList(List<String> noEncryptIdList) {
        this.noEncryptIdList = noEncryptIdList;
        return this;
    }

    public BigDecimal getValueDecimal() {
        return valueDecimal;
    }

    public Value setValueDecimal(BigDecimal valueDecimal) {
        this.valueDecimal = valueDecimal;
        return this;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public Value setValueDate(Date valueDate) {
        this.valueDate = valueDate;
        return this;
    }

    public String getValueSpecial() {
        return valueSpecial;
    }

    public Value setValueSpecial(String valueSpecial) {
        this.valueSpecial = valueSpecial;
        return this;
    }

    public static class IssueTypeProject {
        private String projectId;
        private String issueTypeId;

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getIssueTypeId() {
            return issueTypeId;
        }

        public void setIssueTypeId(String issueTypeId) {
            this.issueTypeId = issueTypeId;
        }

        @Override
        public String toString() {
            return "IssueTypeProject{" +
                    "projectId=" + projectId +
                    ", issueTypeId=" + issueTypeId +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Value{" +
                "valueStr='" + valueStr + '\'' +
                ", valueStrList=" + valueStrList +
                ", valueId=" + valueId +
                ", valueIdList=" + valueIdList +
                ", noEncryptId=" + noEncryptId +
                ", noEncryptIdList=" + noEncryptIdList +
                ", valueDecimal=" + valueDecimal +
                ", valueDate=" + valueDate +
                ", valueSpecial='" + valueSpecial + '\'' +
                ", objectList=" + objectList +
                ", issueTypeProjectList=" + issueTypeProjectList +
                '}';
    }
}

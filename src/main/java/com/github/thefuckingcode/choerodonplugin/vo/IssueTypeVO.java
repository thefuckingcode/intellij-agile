package com.github.thefuckingcode.choerodonplugin.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 */

public class IssueTypeVO {


    private Long id;


    private String name;

    private String icon;

    private String description;

    private Long organizationId;

    private String colour;

    private String typeCode;

    private Boolean initialize;

    private Long objectVersionNumber;

    private IssueTypeVO referenceIssueType;

    /**
     * 用于做状态机方案的配置时
     */

    private String stateMachineName;


    private Long stateMachineId;

    private Long projectId;


    private Boolean enabled;

    private Boolean referenced;

    private Integer usageCount;

    private String source;

    /**
     * 是否可以被删除
     */

    private Boolean deleted;

    private Long referenceId;

    private Boolean copyStatusMachine;

    private Boolean copyCustomField;

    private String rank;

    private ProjectVO projectInfo;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public IssueTypeVO getReferenceIssueType() {
        return referenceIssueType;
    }

    public void setReferenceIssueType(IssueTypeVO referenceIssueType) {
        this.referenceIssueType = referenceIssueType;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(Integer usageCount) {
        this.usageCount = usageCount;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getReferenced() {
        return referenced;
    }

    public void setReferenced(Boolean referenced) {
        this.referenced = referenced;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Long objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStateMachineName() {
        return stateMachineName;
    }

    public void setStateMachineName(String stateMachineName) {
        this.stateMachineName = stateMachineName;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Boolean getInitialize() {
        return initialize;
    }

    public void setInitialize(Boolean initialize) {
        this.initialize = initialize;
    }

    public Long getStateMachineId() {
        return stateMachineId;
    }

    public void setStateMachineId(Long stateMachineId) {
        this.stateMachineId = stateMachineId;
    }

    public Boolean getCopyStatusMachine() {
        return copyStatusMachine;
    }

    public void setCopyStatusMachine(Boolean copyStatusMachine) {
        this.copyStatusMachine = copyStatusMachine;
    }

    public Boolean getCopyCustomField() {
        return copyCustomField;
    }

    public void setCopyCustomField(Boolean copyCustomField) {
        this.copyCustomField = copyCustomField;
    }

    /**
     *
     */
    public ProjectVO getProjectInfo() {
        return projectInfo;
    }

    public IssueTypeVO setProjectInfo(ProjectVO projectInfo) {
        this.projectInfo = projectInfo;
        return this;
    }


    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("icon", icon)
                .append("description", description)
                .append("organizationId", organizationId)
                .append("colour", colour)
                .append("typeCode", typeCode)
                .append("initialize", initialize)
                .append("objectVersionNumber", objectVersionNumber)
                .append("referenceIssueType", referenceIssueType)
                .append("stateMachineName", stateMachineName)
                .append("stateMachineId", stateMachineId)
                .append("projectId", projectId)
                .append("enabled", enabled)
                .append("referenced", referenced)
                .append("usageCount", usageCount)
                .append("source", source)
                .append("deleted", deleted)
                .append("referenceId", referenceId)
                .append("copyStatusMachine", copyStatusMachine)
                .append("copyCustomField", copyCustomField)
                .append("rank", rank)
                .append("projectInfo", projectInfo)
                .toString();
    }
}

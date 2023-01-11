package com.github.thefuckingcode.choerodonplugin.vo;


import java.util.List;

/**
 *
 */

public class StatusVO {


    private Long id;

    private String name;
    /**
     * code是用来识别是否是初始化状态
     */

    private String code;

    private String description;

    private String type;

    private Long organizationId;

    private Long objectVersionNumber;

    private Boolean canDelete;

    private Boolean completed;

    private Boolean defaultStatus;

    private Boolean transferAll;


    private List<Long> issueTypeIds;


    private Long issueStatusId;

    private Long issueStatusObjectVersionNumberId;

    public StatusVO() {
    }

    public StatusVO(String name, String description, String type, Long organizationId) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.organizationId = organizationId;
    }

    public Long getIssueStatusObjectVersionNumberId() {
        return issueStatusObjectVersionNumberId;
    }

    public void setIssueStatusObjectVersionNumberId(Long issueStatusObjectVersionNumberId) {
        this.issueStatusObjectVersionNumberId = issueStatusObjectVersionNumberId;
    }

    public Long getIssueStatusId() {
        return issueStatusId;
    }

    public void setIssueStatusId(Long issueStatusId) {
        this.issueStatusId = issueStatusId;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
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

    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Long objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(Boolean defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public Boolean getTransferAll() {
        return transferAll;
    }

    public void setTransferAll(Boolean transferAll) {
        this.transferAll = transferAll;
    }

    public List<Long> getIssueTypeIds() {
        return issueTypeIds;
    }

    public void setIssueTypeIds(List<Long> issueTypeIds) {
        this.issueTypeIds = issueTypeIds;
    }
}

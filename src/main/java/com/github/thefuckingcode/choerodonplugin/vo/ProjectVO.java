package com.github.thefuckingcode.choerodonplugin.vo;

import java.util.Map;

public class ProjectVO {
    private String id;
    private String name;
    private String code;
    private String tenantId;

    private Map<String, Object> additionalInfo;

    public ProjectVO() {
    }

    public ProjectVO(String name) {
        this.name = name;
    }

    public ProjectVO(String name, String tenantId) {
        this.name = name;
        this.tenantId = tenantId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return name;
    }

    public Map<String, Object> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}

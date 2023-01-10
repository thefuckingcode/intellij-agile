package com.github.thefuckingcode.choerodonplugin.vo;

public class OrganizationVO {
    private String tenantId;
    private String tenantName;
    private String tenantNum;

    public OrganizationVO() {
    }

    public OrganizationVO(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantNum() {
        return tenantNum;
    }

    public void setTenantNum(String tenantNum) {
        this.tenantNum = tenantNum;
    }

    @Override
    public String toString() {
        return tenantName;
    }
}

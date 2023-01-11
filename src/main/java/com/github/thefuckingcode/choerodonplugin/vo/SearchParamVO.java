package com.github.thefuckingcode.choerodonplugin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author superlee
 * @since 2022-11-02
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchParamVO {

    public static final String FIELD_CONDITIONS = "conditions";
    public static final String FIELD_ADVANCED_CONDITIONS = "advancedConditions";
    private List<Condition> conditions;
    private Boolean treeFlag;

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public Boolean getTreeFlag() {
        return treeFlag;
    }

    public void setTreeFlag(Boolean treeFlag) {
        this.treeFlag = treeFlag;
    }

    @Override
    public String toString() {
        return "SearchParamVO{" +
                "conditions=" + conditions +
                ", treeFlag=" + treeFlag +
                '}';
    }
}

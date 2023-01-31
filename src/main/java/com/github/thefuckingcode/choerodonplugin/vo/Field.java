package com.github.thefuckingcode.choerodonplugin.vo;

import java.io.Serializable;

/**
 * @author superlee
 * @since 2022-11-02
 */
public class Field implements Serializable {
    private String fieldCode;
    private Long fieldId;
    private String fieldType;
    private Boolean predefined;
    private String name;
    private Boolean noEncryptFlag;

    public String getName() {
        return name;
    }

    public Field setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getNoEncryptFlag() {
        return noEncryptFlag;
    }

    public Field setNoEncryptFlag(Boolean noEncryptFlag) {
        this.noEncryptFlag = noEncryptFlag;
        return this;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public Field setFieldId(Long fieldId) {
        this.fieldId = fieldId;
        return this;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public Field setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
        return this;
    }

    public String getFieldType() {
        return fieldType;
    }

    public Field setFieldType(String fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public Boolean getPredefined() {
        return predefined;
    }

    public Field setPredefined(Boolean predefined) {
        this.predefined = predefined;
        return this;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldCode='" + fieldCode + '\'' +
                ", fieldId=" + fieldId +
                ", fieldType='" + fieldType + '\'' +
                ", predefined=" + predefined +
                ", noEncryptFlag=" + noEncryptFlag +
                '}';
    }
}

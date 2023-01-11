package com.github.thefuckingcode.choerodonplugin.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author superlee
 * @since 2022-11-02
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Condition implements Serializable {
    private Field field;
    private String relationship;
    private String operation;
    private Value value;
    private Pair<Value, Value> betweenValues;
    private Integer order;
    private List<Condition> subConditions;

    public Field getField() {
        return field;
    }

    public Condition setField(Field field) {
        this.field = field;
        return this;
    }

    public String getRelationship() {
        return relationship;
    }

    public Condition setRelationship(String relationship) {
        this.relationship = relationship;
        return this;
    }

    public String getOperation() {
        return operation;
    }

    public Condition setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public Value getValue() {
        return value;
    }

    public Condition setValue(Value value) {
        this.value = value;
        return this;
    }

    public Pair<Value, Value> getBetweenValues() {
        return betweenValues;
    }

    public Condition setBetweenValues(Pair<Value, Value> betweenValues) {
        this.betweenValues = betweenValues;
        return this;
    }

    public Integer getOrder() {
        return order;
    }

    public Condition setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public List<Condition> getSubConditions() {
        return subConditions;
    }

    public Condition setSubConditions(List<Condition> subConditions) {
        this.subConditions = subConditions;
        return this;
    }

    public static Condition filterByFieldCode(String fieldCode,
                                              List<Condition> conditions) {
        if (ObjectUtils.isEmpty(conditions)) {
            return null;
        }
        Condition result = null;
        for (Condition condition : conditions) {
            Field field = condition.getField();
            if (Objects.equals(fieldCode, field.getFieldCode())) {
                result = condition;
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "field=" + field +
                ", relationship='" + relationship + '\'' +
                ", operation='" + operation + '\'' +
                ", value=" + value +
                ", betweenValues=" + betweenValues +
                ", order=" + order +
                ", subConditions=" + subConditions +
                '}';
    }
}

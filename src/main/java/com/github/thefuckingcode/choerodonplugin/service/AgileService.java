package com.github.thefuckingcode.choerodonplugin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.thefuckingcode.choerodonplugin.feign.AgileClientApi;
import com.github.thefuckingcode.choerodonplugin.feign.ClientBuilder;
import com.github.thefuckingcode.choerodonplugin.util.JsonHelper;
import com.github.thefuckingcode.choerodonplugin.util.ResponseUtil;
import com.github.thefuckingcode.choerodonplugin.vo.*;
import com.intellij.ide.util.PropertiesComponent;
import feign.Response;

import java.util.ArrayList;
import java.util.List;

public class AgileService {
    public PageVO<IssueListFieldKVVO> pageIssues(String projectId, int page) {

        SearchParamVO searchParamVO = new SearchParamVO();
        searchParamVO.setTreeFlag(false);

        Field field = new Field();
        field.setFieldCode("assignee");
        field.setFieldType("member");
        field.setName("经办人");
        field.setPredefined(true);

        Value value = new Value();
        List<String> valueIdList = new ArrayList<>();
        valueIdList.add(PropertiesComponent.getInstance().getValue("userId"));
        value.setValueIdList(valueIdList);

        Condition condition = new Condition();
        condition.setOperation("IN");
        condition.setRelationship("AND");
        condition.setField(field);
        condition.setValue(value);

        List<Condition> conditionList = new ArrayList<>();

        conditionList.add(condition);

        searchParamVO.setConditions(conditionList);


        Response response = ClientBuilder.buildClient(AgileClientApi.class).pageIssues(PropertiesComponent.getInstance().getValue("accessToken"), projectId, JsonHelper.marshalByJackson(searchParamVO), page);
        return ResponseUtil.getResult(response, new TypeReference<PageVO<IssueListFieldKVVO>>() {
        });
    }
}

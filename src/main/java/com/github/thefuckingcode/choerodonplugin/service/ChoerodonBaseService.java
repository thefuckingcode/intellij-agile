package com.github.thefuckingcode.choerodonplugin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.thefuckingcode.choerodonplugin.feign.ChoerodonBaseClientApi;
import com.github.thefuckingcode.choerodonplugin.feign.ClientBuilder;
import com.github.thefuckingcode.choerodonplugin.util.JsonHelper;
import com.github.thefuckingcode.choerodonplugin.util.ResponseUtil;
import com.github.thefuckingcode.choerodonplugin.vo.PageVO;
import com.github.thefuckingcode.choerodonplugin.vo.ProjectStatusVO;
import com.github.thefuckingcode.choerodonplugin.vo.ProjectVO;
import com.intellij.ide.util.PropertiesComponent;
import feign.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChoerodonBaseService {

    public List<String> queryAllNoneDisableProjectStatusIds(String orgId) {
        ChoerodonBaseClientApi choerodonBaseClientApi = ClientBuilder.buildClient(ChoerodonBaseClientApi.class);
        Response response = choerodonBaseClientApi.pageStatus(PropertiesComponent.getInstance().getValue("accessToken"), orgId);
        PageVO<ProjectStatusVO> result = ResponseUtil.getResult(response, new TypeReference<PageVO<ProjectStatusVO>>() {
        });
        return result.getContent().stream().filter(s -> !s.getName().equals("停用")).map(ProjectStatusVO::getId).collect(Collectors.toList());
    }

    public PageVO<ProjectVO> pageProjects(String orgId, int page) {
        List<String> allNoneDisableProjectStatusIds = queryAllNoneDisableProjectStatusIds(orgId);
        ChoerodonBaseClientApi choerodonBaseClientApi = ClientBuilder.buildClient(ChoerodonBaseClientApi.class);
        Map<String, Object> searchBody = new HashMap<>();
        searchBody.put("statusIds", allNoneDisableProjectStatusIds);
        Response response = choerodonBaseClientApi.pageProjects(PropertiesComponent.getInstance().getValue("accessToken"), orgId, PropertiesComponent.getInstance().getValue("userId"), page, 10, JsonHelper.marshalByJackson(searchBody));
        return ResponseUtil.getResult(response, new TypeReference<PageVO<ProjectVO>>() {
        });
    }
}

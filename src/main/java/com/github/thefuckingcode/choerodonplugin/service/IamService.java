package com.github.thefuckingcode.choerodonplugin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.thefuckingcode.choerodonplugin.config.ChoerodonPluginOauthConfigState;
import com.github.thefuckingcode.choerodonplugin.feign.ClientBuilder;
import com.github.thefuckingcode.choerodonplugin.feign.IamClientApi;
import com.github.thefuckingcode.choerodonplugin.util.ResponseUtil;
import com.github.thefuckingcode.choerodonplugin.vo.OrganizationVO;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.ApplicationManager;
import feign.Response;

import java.util.List;

public class IamService {

    public List<OrganizationVO> listOrganizations() {
        IamClientApi iamClientApi = ClientBuilder.buildClient(ApplicationManager.getApplication().getService(ChoerodonPluginOauthConfigState.class).getChoerodonHost(), IamClientApi.class);

        Response response = iamClientApi.selfTenants(PropertiesComponent.getInstance().getValue("accessToken"));
        return ResponseUtil.getResult(response, new TypeReference<List<OrganizationVO>>() {
        });
    }
}

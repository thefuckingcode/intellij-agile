package com.github.thefuckingcode.choerodonplugin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.thefuckingcode.choerodonplugin.config.ChoerodonPluginOauthConfigState;
import com.github.thefuckingcode.choerodonplugin.feign.ClientBuilder;
import com.github.thefuckingcode.choerodonplugin.feign.OauthClientApi;
import com.github.thefuckingcode.choerodonplugin.util.ResponseUtil;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.ApplicationManager;
import feign.Response;

import java.util.Map;

public class LoginService {
    private ChoerodonPluginOauthConfigState choerodonPluginOauthConfigState;


    public LoginService() {
        choerodonPluginOauthConfigState = ApplicationManager.getApplication().getService(ChoerodonPluginOauthConfigState.class);
    }

    public void Login() {
        OauthClientApi oauthClientApi = ClientBuilder.buildClient(choerodonPluginOauthConfigState.getChoerodonHost(), OauthClientApi.class);
        Response response = oauthClientApi.login(choerodonPluginOauthConfigState.getClientId(),
                choerodonPluginOauthConfigState.getClientSecret(),
                choerodonPluginOauthConfigState.getGrantType(),
                choerodonPluginOauthConfigState.getUsername(),
                choerodonPluginOauthConfigState.getPassword());
        Map<String, String> oauthResult = ResponseUtil.getResult(response, new TypeReference<Map<String, String>>() {
        });
        PropertiesComponent.getInstance().setValue("accessToken", oauthResult.get("access_token"));
        PropertiesComponent.getInstance().setValue("refreshToken", oauthResult.get("refresh_token"));
    }
}

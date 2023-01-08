package com.github.thefuckingcode.choerodonplugin.service;

import com.github.thefuckingcode.choerodonplugin.config.ChoerodonPluginOauthConfigState;
import com.github.thefuckingcode.choerodonplugin.feign.ClientBuilder;
import com.github.thefuckingcode.choerodonplugin.feign.OauthClientApi;
import com.intellij.openapi.application.ApplicationManager;
import feign.Response;

public class LoginService {
    private ChoerodonPluginOauthConfigState choerodonPluginOauthConfigState;


    public LoginService() {
        choerodonPluginOauthConfigState = ApplicationManager.getApplication().getService(ChoerodonPluginOauthConfigState.class);
    }

    public void Login() {
        try {
            OauthClientApi oauthClientApi = ClientBuilder.buildClient(choerodonPluginOauthConfigState.getChoerodonHost(), OauthClientApi.class);
            Response response = oauthClientApi.login(choerodonPluginOauthConfigState.getClientId(),
                    choerodonPluginOauthConfigState.getClientSecret(),
                    choerodonPluginOauthConfigState.getGrantType(),
                    choerodonPluginOauthConfigState.getUsername(),
                    choerodonPluginOauthConfigState.getPassword());
            response.body().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

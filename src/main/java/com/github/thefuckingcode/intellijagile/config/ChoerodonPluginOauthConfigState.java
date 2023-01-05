package com.github.thefuckingcode.intellijagile.config;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
@State(name = "ChoerodonPluginOauthConfig", storages = {@Storage("ChoerodonPluginOauthConfig.xml")})
public class ChoerodonPluginOauthConfigState implements PersistentStateComponent<ChoerodonPluginOauthConfigState> {

    private String clientId="";
    private String clientSecret="";
    private String grantType="";
    private String username="";
    private String password="";

    public static ChoerodonPluginOauthConfigState getInstance() {
        return ApplicationManager.getApplication().getService(ChoerodonPluginOauthConfigState.class);
    }

    @Nullable
    @Override
    public ChoerodonPluginOauthConfigState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull ChoerodonPluginOauthConfigState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

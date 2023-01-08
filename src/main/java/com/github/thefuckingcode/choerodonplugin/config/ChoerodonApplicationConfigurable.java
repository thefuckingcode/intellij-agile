package com.github.thefuckingcode.choerodonplugin.config;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

public class ChoerodonApplicationConfigurable implements Configurable {
    private OauthConfigComponent oauthConfigComponent;

    @Override
    public String getDisplayName() {
        return "Choerodon Plugin";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        oauthConfigComponent = new OauthConfigComponent();
        oauthConfigComponent.init();
        return oauthConfigComponent.getMainPanel();
    }

    @Override
    public boolean isModified() {
        ChoerodonPluginOauthConfigState settings = ChoerodonPluginOauthConfigState.getInstance();
        return !Objects.equals(settings.getChoerodonHost(),oauthConfigComponent.getChoerodonHost())||
                !Objects.equals(settings.getUsername(), oauthConfigComponent.getUsername()) ||
                !Objects.equals(settings.getPassword(), oauthConfigComponent.getPassword()) ||
                !Objects.equals(settings.getClientId(), oauthConfigComponent.getClientId()) ||
                !Objects.equals(settings.getClientSecret(), oauthConfigComponent.getClientSecret()) ||
                !Objects.equals(settings.getGrantType(), oauthConfigComponent.getGrantType());
    }

    @Override
    public void apply() throws ConfigurationException {
        ChoerodonPluginOauthConfigState settings = ChoerodonPluginOauthConfigState.getInstance();
        settings.setUsername(oauthConfigComponent.getUsername());
        settings.setPassword(oauthConfigComponent.getPassword());
        settings.setClientId(oauthConfigComponent.getClientId());
        settings.setClientSecret(oauthConfigComponent.getClientSecret());
        settings.setGrantType(oauthConfigComponent.getGrantType());
        settings.setChoerodonHost(oauthConfigComponent.getChoerodonHost());
    }

    @Override
    public void reset() {

    }

    @Override
    public void disposeUIResources() {
        oauthConfigComponent = null;
    }
}

package com.github.thefuckingcode.choerodonplugin.config;


import com.intellij.util.ui.FormBuilder;

import javax.swing.*;

public class OauthConfigComponent {
    private JPanel mainPanel;
    private JPanel userConfigPanel;
    private JLabel Username;
    private JLabel Password;
    private JLabel ClientId;
    private JLabel ClientSecret;
    private JLabel GrantType;
    private JLabel ChoerodonHost;
    private JTextField username;
    private JTextField password;
    private JTextField grantType;
    private JTextField clientId;
    private JTextField clientSecret;
    private JTextField choerodonHost;


    public OauthConfigComponent() {
        userConfigPanel = FormBuilder.createFormBuilder().getPanel();
    }

    public void init() {
        ChoerodonPluginOauthConfigState settings = ChoerodonPluginOauthConfigState.getInstance();
        username.setText(settings.getUsername());
        password.setText(settings.getPassword());
        clientId.setText(settings.getClientId());
        clientSecret.setText(settings.getClientSecret());
        grantType.setText(settings.getGrantType());
        choerodonHost.setText(settings.getChoerodonHost());
    }

    public String getUsername() {
        return username.getText();
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public String getPassword() {
        return password.getText();
    }

    public void setPassword(JTextField password) {
        this.password = password;
    }

    public String getGrantType() {
        return grantType.getText();
    }

    public String getChoerodonHost() {
        return choerodonHost.getText();
    }

    public void setGrantType(JTextField grantType) {
        this.grantType = grantType;
    }

    public String getClientId() {
        return clientId.getText();
    }

    public void setClientId(JTextField clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret.getText();
    }

    public void setClientSecret(JTextField clientSecret) {
        this.clientSecret = clientSecret;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getUserConfigPanel() {
        return userConfigPanel;
    }

    public void setUserConfigPanel(JPanel userConfigPanel) {
        this.userConfigPanel = userConfigPanel;
    }
}

package com.github.thefuckingcode.choerodonplugin.util;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class ProxyUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyUtil.class);
    private static ProxyProperties proxyProperties;

    public static ProxyProperties parseProxy() {
        if (proxyProperties != null) {
            return proxyProperties;
        }
        String proxy = System.getenv("http_proxy");
        if (ObjectUtils.isEmpty(proxy)) {
            proxy = System.getenv("https_proxy");
        }

        if (ObjectUtils.isEmpty(proxy)) {
            return null;
        }
        LOGGER.info("use proxy:{}", proxy);
        URL url;
        // http://username:password@example.com:18118
        try {
            url = new URL(proxy);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        ProxyProperties temp = new ProxyProperties();
        temp.setHost(url.getHost());
        temp.setPort(url.getPort());
        String[] userInfo = url.getUserInfo().split(":");
        temp.setUsername(userInfo[0]);
        temp.setPassword(userInfo[1]);

        proxyProperties = temp;

        return temp;
    }


    public static class ProxyProperties {
        private String host;
        private Integer port;
        private String username;
        private String password;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
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
}
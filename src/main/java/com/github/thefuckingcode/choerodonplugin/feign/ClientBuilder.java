package com.github.thefuckingcode.choerodonplugin.feign;

import com.github.thefuckingcode.choerodonplugin.util.ProxyUtil;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.Retryer;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

public class ClientBuilder {
    public static <T> T buildClient(String choerodonHost, Class<T> tClass) {
        ProxyUtil.ProxyProperties proxyProperties = ProxyUtil.parseProxy();
        Client client = new Client.Default(null, null);
        if (proxyProperties != null) {
            SocketAddress address =
                    new InetSocketAddress(proxyProperties.getHost(), proxyProperties.getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
            client = new Client.Proxied(null, null, proxy, proxyProperties.getUsername(), proxyProperties.getPassword());
        }

        return Feign.builder()
                .client(client)
                .retryer(Retryer.NEVER_RETRY)
                .logLevel(Logger.Level.HEADERS)
                .target(tClass, choerodonHost);
    }
}

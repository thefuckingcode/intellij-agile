package com.github.thefuckingcode.choerodonplugin.feign;

import com.github.thefuckingcode.choerodonplugin.util.ProxyUtil;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.slf4j.Slf4jLogger;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ClientBuilder {
    private static Map<Class, Object> clientMap = new HashMap();

    public static <T> T buildClient(String choerodonHost, Class<T> tClass) {
        Object clientObject = clientMap.get(tClass);
        if (clientObject != null) {
            return (T) clientObject;
        }
        ProxyUtil.ProxyProperties proxyProperties = ProxyUtil.parseProxy();
        Client client = new Client.Default(null, null);
        if (proxyProperties != null) {
            SocketAddress address =
                    new InetSocketAddress(proxyProperties.getHost(), proxyProperties.getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
            client = new Client.Proxied(null, null, proxy, proxyProperties.getUsername(), proxyProperties.getPassword());
        }

        T clientI = Feign.builder()
                .client(client)
                .retryer(Retryer.NEVER_RETRY)
                .logger(new Slf4jLogger("downloader"))
                .logLevel(Logger.Level.HEADERS)
                .target(tClass, choerodonHost);
        clientMap.put(tClass, clientI);
        return clientI;
    }
}

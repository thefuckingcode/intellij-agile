package com.github.thefuckingcode.choerodonplugin.feign;

import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface OauthClientApi {
    @RequestLine("POST /oauth/oauth/token?client_id={clientId}&client_secret={clientSecret}&grant_type={grantType}&username={username}&password={password}")
    Response login(@Param("clientId") String clientId,
                   @Param("clientSecret") String clientSecret,
                   @Param("grantType") String grantType,
                   @Param("username") String username,
                   @Param("password") String password);
}

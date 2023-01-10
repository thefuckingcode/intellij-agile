package com.github.thefuckingcode.choerodonplugin.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface IamClientApi {
    @RequestLine("GET /iam/choerodon/v1/users/self-tenants")
    @Headers("authorization: {token}")
    Response selfTenants(@Param("token") String token);

    @RequestLine("GET /iam/choerodon/v1/users/self")
    @Headers("authorization: {token}")
    Response self(@Param("token") String token);
}

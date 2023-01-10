package com.github.thefuckingcode.choerodonplugin.feign;

import feign.*;

public interface ChoerodonBaseClientApi {

    @RequestLine("POST /cbase/choerodon/v1/organizations/{orgId}/users/{userId}/projects/paging?page={page}&size={size}")
    @Headers({"authorization: {token}", "Content-Type: application/json"})
    @Body("{body}")
    Response pageProjects(@Param("token") String token,
                          @Param("orgId") String orgId,
                          @Param("userId") String userId,
                          @Param("page") int page,
                          @Param("size") int size,
                          @Param("body") String body);


    @RequestLine("GET /cbase/choerodon/v1/organizations/{orgId}/project_status/paging?page=0&size=100")
    @Headers("authorization: {token}")
    Response pageStatus(@Param("token") String token, @Param("orgId") String orgId);
}

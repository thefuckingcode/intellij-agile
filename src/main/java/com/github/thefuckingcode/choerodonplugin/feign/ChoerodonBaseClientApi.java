package com.github.thefuckingcode.choerodonplugin.feign;

import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface ChoerodonBaseClientApi {

    @RequestLine("POST /cbase/choerodon/v1/organizations/{orgId}/users/{userId}/projects/paging?page={page}&size={size}")
    Response pageProjects(@Param("orgId") Long orgId,
                          @Param("userId") Object userId,
                          @Param("page") int page,
                          @Param("size") int size);
}

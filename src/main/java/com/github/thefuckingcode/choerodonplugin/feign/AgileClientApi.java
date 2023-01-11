package com.github.thefuckingcode.choerodonplugin.feign;

import feign.*;

public interface AgileClientApi {
    @RequestLine("POST /agile/v2/projects/{projectId}/issues/work_list?page={page}&size=10")
    @Headers({"authorization: {token}", "Content-Type: application/json"})
    @Body("{body}")
    Response pageIssues(@Param("token") String token, @Param("projectId") String projectId, @Param("body") String body, @Param("page") int page);
}

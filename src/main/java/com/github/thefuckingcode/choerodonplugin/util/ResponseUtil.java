package com.github.thefuckingcode.choerodonplugin.util;

import com.fasterxml.jackson.core.type.TypeReference;
import feign.Response;
import feign.Util;

public class ResponseUtil {
    public static <T> T getResult(Response response, Class<T> tClass) {
        if (response.status() == 200) {
            try {
                return JsonHelper.unmarshalByJackson(response.body().toString(), tClass);
            } catch (Exception e) {
                throw new RuntimeException(String.format("Failed to parse result,status code :%d ,response: %s", response.status(), response.body().toString()));
            }
        } else {
            throw new RuntimeException(String.format("Failed to request,status code :%d ,response: %s", response.status(), response.body().toString()));
        }
    }

    public static <T> T getResult(Response response, TypeReference<T> typeReference) {
        String body;
        try {
            body = new String(Util.toByteArray(response.body().asInputStream()), Util.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read body");
        }
        if (response.status() == 200) {
            try {

                return JsonHelper.unmarshalByJackson(body, typeReference);
            } catch (Exception e) {
                throw new RuntimeException(String.format("Failed to parse result,status code :%d ,response: %s", response.status(), body));
            }
        } else {
            throw new RuntimeException(String.format("Failed to request,status code :%d ,response: %s", response.status(), body));
        }
    }
}
